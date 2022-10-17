'use strict';

class Request {

	config = {
		debug: false,
		baseurl: '',
		encoding: 'utf-8',
		content_type: 'json',
		loading: 'nav-bar',
		loading_duration: 0,
		data: {},
		header: {}
	}
	
	req_config = {
		url: '',
		data: {},
		header: {},
		method: 'GET',
		timeout: 30000,
		dataType: 'json',
		responseType: 'text'
	}

	static isurl(url) {
		return /(http|https):\/\/([\w.]+\/?)\S*/.test(url);
	}

	static geturl(config) {
		return Request.isurl(config.url) ? (config.url || '') : (config.baseurl + config.url);
	}

	static get_content_type(config) {
		switch (config.content_type){
			case 'json':
				return 'application/json';
			case 'form':
				return 'application/x-www-form-urlencoded';
			case 'file':
				return 'multipart/form-data';
			case 'text':
				return 'text/plain';
			case 'html':
				return 'text/html';
			default:
				throw new Error('content type error: ' + type);
		}
	}

	/**
	 * 拦截器
	 * 
	 */
	interceptor = {
		request: undefined,
		response: undefined,
		fail: undefined
	}

	/**
	 * request 请求
	 * 
	 */
	request(options = {}) {
		let that = this;
		let config = Object.assign({}, this.config, options);

		config.url = Request.geturl(config);
		
		if (!config.header['Content-Type']) {
			config.header['Content-Type'] = Request.get_content_type(config);
		}
		
		if (typeof that.interceptor.request === 'function') {
			config = that.interceptor.request(config);
		}
		
		let task = undefined
		let promise = new Promise((resolve, reject) => {

			let extras = {}
			that._prepare(that, config, extras)

			if (config.content_type === 'file') {
				task = uni.uploadFile({
					...config,
					success: res => {
						that._success(that, config, res, resolve, reject)
					},
					fail: res => {
						that._fail(that, config, res, resolve, reject)
					},
					complete: (res) => {
						that._complete(that, config, res, extras)
					}
				})
				if (typeof config.progress === 'function') {
					task.onProgressUpdate(_res => {
						config.progress(_res, task)
					})
				}
			} else {
				
				this._set_req_config(config);
				task = uni.request({
					...this.req_config,
					success: res => {
						that._success(that, config, res, resolve, reject)
					},
					fail: res => {
						that._fail(that, config, res, resolve, reject)
					},
					complete: (res) => {
						that._complete(that, config, res, extras)
					}
				})
			}
		})
		
		if (config.success || config.fail || config.complete) {
			return task;
		}
		
		return promise;
	}

	get(options = {}) {
		options.method = 'GET'
		return this.request(options)
	}

	post(options = {}) {
		options.method = 'POST'
		return this.request(options)
	}

	put(options = {}) {
		options.method = 'PUT'
		return this.request(options)
	}

	delete(options = {}) {
		options.method = 'DELETE'
		return this.request(options)
	}

	upload(options = {}) {
		options.method = 'POST'
		options.content_type = 'file'
		return this.request(options)
	}

	_success = function(that, config, res, resolve, reject) {
		if (config.debug) {
			console.log('response success res: ', res)
		}
		
		if (res.statusCode >= 200 && res.statusCode <= 302) {
			let result = res.data
			if (typeof result !== 'object') {
				try{
					result = JSON.parse(res.data);
				}catch(e){
				}
			}

			let is_success = !0;
			if (typeof that.interceptor.response === 'function' && !config.skip_interceptor_response) {
				is_success = that.interceptor.response(result, config)
			}
			
			if (is_success) {
				if (config.debug) {
					console.log('response success: ', result)
				}
				
				config.success ? config.success(result) : resolve(result)
				return;
			}
			
			return;
		}
		
		that._fail(that, config, res, resolve, reject)
	}

	_fail = function(that, config, res, resolve, reject) {
		if (config.debug) {
			console.error('response failure: ', res)
		}
		
		if (res.errMsg === 'request:fail abort') {
			return
		}
		
		if (typeof that.interceptor.fail === 'function') {
			res = that.interceptor.fail(res, config)
		}
		
		config.fail ? config.fail(res) : reject(res)
	}

	_prepare = function(that, config, obj = {}) {
		obj.startTime = Date.now()
		if (config.loading) {
			if (config.loading === 'nav-bar') {
				uni.showNavigationBarLoading();
			} else {
				uni.showLoading({
					title: config.loading
				})
			}
		}
		if (config.content_type === 'file') {
			if (config.formData === undefined || config.formData === null) {
				config.formData = config.data
				delete config.data
			}			
			config.method = 'POST'
		}
		
		if (config.debug) {
			console.log('request: ', config)
		}
	}

	_complete = function(that, config, res, obj = {}) {
		obj.endTime = Date.now()
		let diff = obj.endTime - obj.startTime;
		if (config.debug) {
			console.log('request completed in ' + (obj.endTime - obj.startTime) + ' ms')
		}
		if (config.loading) {
			let duration = config.loading_duration || 0
			if (diff < duration) {
				diff = duration - diff
			} else {
				diff = 0
			}

			let __timer = setTimeout(function() {
				if (config.loading === 'nav-bar') {
					uni.hideNavigationBarLoading()
				} else {
					uni.hideLoading()
				}
				
				clearTimeout(__timer);
			}, diff)
		}
	}
	
	_set_req_config = function(config){
		for(let key in this.req_config){
			if (config.hasOwnProperty(key)){
				this.req_config[key] = config[key]
			}
		}
	}
}

export default new Request()
