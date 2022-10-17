<script>
	/**
	 * vuex 管理登陆状态，具体可以参考官方登陆模板示例 
	 */
	import { mapState, mapMutations } from 'vuex';
	const db = uniCloud.database();
	
	export default {
		data() {
			return {
				wss_url: '',
				wss_created: false,
				wss_conn_cnt: 1,
				wsState: 'off-line',
				socketTask: null,
			};
		},
		computed: {
			...mapState(['member'])
		},
		methods: {
			...mapMutations(['token', 'loadToken']),
			
			closews() {
				if (this.socketTask) {
					console.log('关闭 socket 连接 ....');
					
				}
			},
			createws() {
				let _this = this;
			
				if (this.wsState == 'off-line') {
					// 创建链接
					
					console.log(this.socketTask);
				}
			
				if (!this.wss_created) {
					this.wss_created = true;
					
				}
			}
		},
		onLaunch: function(options) {
			let _this = this;
			// console.log(_this);
			
			if (uni.canIUse('getUpdateManager')) {
				const updateManager = uni.getUpdateManager();
			
				updateManager.onCheckForUpdate(function(res) {
					// 请求完新版本信息的回调
					console.log(res.hasUpdate);
					if (res.hasUpdate) {
						updateManager.onUpdateReady(function(res) {
							uni.showModal({
								title: '更新提示',
								content: '新版本已经准备好，是否重启应用？',
								success(res) {
									if (res.confirm) {
										// 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
										updateManager.applyUpdate();
									}
								}
							});
						});
			
						updateManager.onUpdateFailed(function(res) {
							// 新的版本下载失败
						});
					}
				});
			}
			
			// 自动刷新新的 token 
			// db.on('refreshToken', ({token, tokenExpired}) => {
			// 	console.log("refreshToken: ", {token, tokenExpired});
			// 	this.token(token, tokenExpired);
			// });
			
			this.loadToken();
		},
		onShow: function(options) {
			console.log('App Show');
			
			if ([1011, 1012, 1013].includes(options.scene) && options.query) {
				uni.setStorageSync('__scene_query_q', options.query.q);
			}
			
			// #ifdef MP-WEIXIN
			let lopts = uni.getLaunchOptionsSync();
			console.log(options, lopts);
			
			// 
			if(lopts && lopts.query && lopts.query.share){
				// is_share
				// todo: 调用分享获积分服务-api
				
			}
			// #endif			
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/* 全局字体图标和公共样式 */
	@import url("/components/iconfont/iconfont.css");
	@import url("/common/common.scss");
	
	// tab图标
		.uni-tabbar .uni-tabbar__icon {
			width: 35px !important;
	        height: 35px !important;
		}
		// ios底部安全距离-padding
		.savepadding{
		    padding-bottom: constant(safe-area-inset-bottom);  
		    padding-bottom: env(safe-area-inset-bottom); 
		    box-sizing: content-box;
		}
		
		// ios底部安全距离-bottom
		.savebottom{
		    bottom: constant(safe-area-inset-bottom);  
		    bottom: env(safe-area-inset-bottom); 
		}
		.submit-warp{position: fixed;bottom: var(--window-bottom);left: 0;right: 0;height: 0.5*200upx;line-height: 0.5*200upx;background: #fff;}
</style>
