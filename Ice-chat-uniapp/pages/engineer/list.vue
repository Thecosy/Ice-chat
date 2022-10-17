<template>
	<view class="page-container box-sizing-b w-full">
		<use-header title="工程师"></use-header>
		
		<!-- 工程师-类型 -->
		<view class="dflex pos-f bg-main" style="height: 120rpx; z-index: 99;" :style="{ 'top': 'calc(44px + ' + statusBarHeight + ')'}">
			<view class="dflex-c dflex-flow-c line-height-1" style="width: 25vw;">
				<view class="fwb fs-lg margin-bottom-xs">20</view>
				<text class="fs-xs">剩余次数</text>
			</view>
			
			<scroll-view class="padding-right" scroll-x style="width: 75vw; white-space: nowrap;">
				<view class="dflex oper-area oper-area-big fs-30">
					<view class="use-hover active">全部</view>
					<view class="use-hover">前端</view>
					<view class="use-hover">后端</view>
					<view class="use-hover">服务端</view>
					<view class="use-hover">小程序</view>
					<view class="use-hover">H5</view>
				</view>
			</scroll-view>
		</view>
		<view style="height: 120rpx;"></view>
		
		<view class="padding-bottom-sm padding-lr margin-bottom">
			<view class="use-hover dflex-b im-list-item" @click="toChat" v-for="(item, idx) in [0,1,2,3,4]" :key="idx">
				<view class="dflex">
					<view class="pos-r margin-right">
						<image class="headimg" src="/static/images/headimg/face_12.jpg"></image>
						<view v-if="idx < 3" class="pos-a bg-base border-radius-xs tac fs-xs" style="bottom: -16rpx; left: 10rpx; right: 10rpx;">3次</view>
					</view>
					<view>
						<view class="pos-r dflex">
							<view class="fwb fs margin-right">工程师 · 花木兰</view>
						</view>
						<view class="dflex">
							<view class="iconfont iconpingjia02 padding-right-xs active"></view>
							<text>5.0分</text>
							<text class="margin-left">总协助次数：999</text>
						</view>
						<view class="oper-area oper-area-sm dflex dflex-wrap-w fs-xs">
							<view>前端</view>
							<view>后端</view>
							<view>服务端</view>
						</view>
					</view>
				</view>
				<view class="dflex-c dflex-flow-c" @click="toChat">
					<view class="d-price fs-xs">9.9起/次</view>
					<view class="bg-base border-radius-sm margin-top-xs" style="padding: 16rpx 20rpx;">请求协助</view>
				</view>
			</view>
		</view>
		
		<view class="use-placeholder"></view>
		
		<!-- 置顶 -->
		<use-totop :style="{ marginBottom: navHeight + 'px' }" :scroll-top="scrollTop"></use-totop>

		<!-- #ifdef MP-WEIXIN -->
		<official-account @bindload="wxOAccountLoad" @binderror="wxOAccountErr"></official-account>
		<!-- #endif -->
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';

	export default {
		computed: { ...mapState(['member']) },
		data() {
			return {
				__env: {},

				// 头部参数
				searchAuto: !0,
				searchTip: '请输入搜索关键字',

				scrollTop: 0,
				navHeight: 0,
				navUserHeight: 70,
				statusBarHeight: '20px'
			};
		},
		// 监听页面加载
		onLoad() {
			this.$api.get_env((res) => {
				this.__env = res;
				console.log('home env', this.__env);
				this.is_mp = this.__env.is_mp;
				this.platform = this.__env.platform;
				this.platform_icon = this.__env.platform_icon;
				this.platform_name = this.__env.platform_name;
			});
			this.statusBarHeight = this.$env.statusBarHeight + 'px';
			
			if (uni.canIUse('showTabBarRedDot')) {
				uni.showTabBarRedDot({
					index: 3,
					success(res) {
						console.log(res);
					}
				});
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
		},
		// 监听页面显示。页面每次出现在屏幕上都触发，包括从下级页面点返回露出当前页面
		onShow() {
			// this.loadData();
		},
		// 监听用户下拉刷新
		onPullDownRefresh() {
			return;
			
			this.loadData(() => {
				uni.stopPullDownRefresh();
			});
		},
		// 用户点击右上角分享
		// https://uniapp.dcloud.io/api/plugins/share?id=showsharemenu
		onShareAppMessage: function(ops) {
			let _this = this,
				mid = 0;

			if (_this.member && _this.member.id) {
				mid = _this.member.id;
			}

			return {
				title: '用云 · IM',
				path: `/pages/engineer/list?mid=${mid}`,
				// imageUrl: 'https://mall-os-api.use-cloud.com/files/upload/image/20200408/200408115587860242.jpg',
				success: function(res) {
					// 转发成功
					console.log('转发成功', res);
				},
				fail: function(res) {
					// 转发失败
					console.log('转发失败', res);
				}
			};
		},

		methods: {
			// 加载数据
			async loadData(callback) {
				
			},
			
			toChat(){
				uni.navigateTo({
					url: `/pages/chat/chat`
				});
			},
			
			toback() {
				uni.navigateBack();
			},
			
		},
		mounted() {
			// #ifdef H5 || MP-360
			this.navHeight = 50;
			// #endif
		}
	};
</script>

<style lang="scss">
	page {
		width: 100%;
		min-height: 100%;
		overflow-x: hidden;
	}
	
	/* #ifdef H5 || MP-360 */
	uni-page-body {
		touch-action:none;
	}
	/* #endif */
	
	.oper-area {
		view {
			border-radius: 100rpx;
			margin-right: 14rpx;
			text-align: center;
		}
	}
	
	.oper-area-big {
		view {
			padding-top: 16rpx;
			padding-bottom: 16rpx;
			background: #f5f5f5;
			min-width: 180rpx;
			color: #b4b4b4;
			margin-right: 20rpx;
		}
		
		.active {
			background: #f46f4d;
			color: #fff !important;
		}
	}
	.oper-area-sm {
		view {
			padding: 6rpx 20rpx;
			background: #fff;
			margin-bottom: 20rpx;
		}
	}
	
	.active {
		color: #f46f4d !important;
	}
	
	.im-list-item {
		background-color: #f5f5f5;
		border-radius: 14rpx;
		padding: 30rpx;
		margin-bottom: 20rpx;
		position: relative;
		overflow: hidden;
		
		.headimg {
			width: 100rpx;
			height: 100rpx;
			border-radius: 50%;
		}
		
		text {
			font-size: 24rpx;
			color: #c0c0c0;
		}
	}
	
	::-webkit-scrollbar {
		width: 0;
		height: 0;
		color: transparent;
		display: none;
	}
</style>
