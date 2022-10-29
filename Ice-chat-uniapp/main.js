import Vue from 'vue'
import App from './App'

import $config from './common/config.js'
import $api from './common/common.js'
import $db from './common/db.js'
import $func from './common/func.js'
import $request from './common/request.js'
import $store from './common/store.js'
// main.js
import aLoading from "@/components/a-loading.vue";
Vue.component('a-loading',aLoading)

// #ifdef H5 || MP-360
import $iscroll from './common/iscroll.js'
Vue.prototype.$iscroll = $iscroll

Vue.prototype.serviceUrl = "http://157.245.86.35:9090"

uni.canIUse = function(name) {
	return name in uni;
}
// #endif

// 测试阶段
$config.debug = process.env.NODE_ENV === 'development';

if (!$config.debug) {
	// 取消日志打印功能
	console.log = () => { }
}

// 注册当前环境 
$api.register_env((env) => {
	Vue.prototype.$env = env;
});

// 全局 request 配置
$request.config.baseurl = $config.get_url();
// 测试阶段，开启 debug 日志
$request.config.debug = $config.debug;

// 全局 request 拦截器
// 全局请求拦截器 
$request.interceptor.request = (config => {
	// 添加 access_token 访问令牌
	if (!config.header['access_token']) {
		config.header['access_token'] = uni.getStorageSync('access_token') || '';
	}

	// 默认提示异常信息
	if (config.err_tip === undefined) {
		config.err_tip = true
	}

	// 默认加上 loading
	if (config.loading == undefined)
		config.loading = '加载中';

	return config;
})

// 全局响应拦截器
$request.interceptor.response = ((res, config) => {

	if (res.code === 401) {
		$api.msg(res.msg);
		// token 失效，需要重新登录
		// uni.navigateTo({
		// 	url: '/pages/public/login'
		// })

		return false;
	}
	
	if(config && config.header && config.header['access_token']) config.header['access_token'] = '';
	
	return true;
})

// 全局错误异常处理
$request.interceptor.fail = ((res, config) => {
	let ret = res;
	let msg = ''

	if (res.statusCode === 200) { 
		msg = res.data.msg
		ret = res.data
	} else if (res.statusCode > 0) { 
		msg = '服务器异常[' + res.statusCode + ']'
	} else { 
		msg = res.errMsg
	}

	if (config.err_tip) {
		uni.showToast({
			title: msg,
			duration: 5000,
			icon: 'none'
		})
	}

	return ret;
})


Vue.config.productionTip = false

Vue.prototype.$config = $config
Vue.prototype.$api = $api
Vue.prototype.$db = $db
Vue.prototype.$dbcmd = uniCloud.database().command;
Vue.prototype.$func = $func
Vue.prototype.$request = $request
Vue.prototype.$store = $store

App.mpType = 'app'

const app = new Vue({
	...App
})

app.$mount()
