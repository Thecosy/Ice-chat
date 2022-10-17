<template>
	<view class="page-container box-sizing-b w-full">
		<!-- 工程师-类型 -->
		<view class="dflex  bg-main" style="height: 120rpx;">
			<view class="dflex-c dflex-flow-c line-height-1" style="width: 25vw;">
				<view class="fwb fs-lg margin-bottom-xs">20</view>
				<text>剩余次数</text>
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
		
		<view class="padding-bottom-sm padding-lr margin-bottom">
			<view class="use-hover dflex-b im-list-item" @click="tochat" v-for="(item, idx) in [0,1,2,3,4]" :key="idx">
				<view class="istop"><view class="iconfont iconzhiding"></view></view>
				<view class="dflex">
					<view class="pos-r margin-right">
						<image class="headimg" src="/static/images/headimg/face_12.jpg"></image>
						<view class="pos-a bg-base border-radius-xs tac fs-xs" style="bottom: -16rpx; left: 10rpx; right: 10rpx;">3次</view>
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
				<view class="dflex-c dflex-flow-c">
					<view class="d-price">9.9起/次</view>
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
				scrollTop: 0,
				navHeight: 0,
				navUserHeight: 70,
			};
		},
		// 监听页面加载
		onLoad() {
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
		methods: {
			
			tochat(){
				uni.navigateTo({
					url: `/pages/chat/chat`
				});
			},
			
			// 搜索
			tosearch() {
				console.log('home search');
				
				uni.navigateTo({
					url: `/pages/chat/search`
				})
			},
			// 工程师
			toengineer() {
				console.log('home engineer');
				
				uni.navigateTo({
					url: `/pages/engineer/list`
				})
			},


			wxOAccountLoad(options) {
				console.log('wxOAccountLoad', options);
			},
			wxOAccountErr(options) {
				console.log('wxOAccountErr', options);
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
			margin-right: 20rpx;
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
		}
	}
	.oper-area-sm {
		view {
			padding: 6rpx 20rpx;
			background: #fff;
			margin-bottom: 20rpx;
		}
	}
	
	.im-list-item {
		background-color: #f5f5f5;
		border-radius: 14rpx;
		padding: 30rpx;
		margin-bottom: 20rpx;
		position: relative;
		overflow: hidden;
		
		.istop{
			background-color: #4d9ff3;
			border-radius: 100%;
			position: absolute;
			top: -20rpx;
			left: -20rpx;
			width: 60rpx;
			height: 60rpx;

			.iconfont{
				color: #fff;
				font-size: 10px;
				position: absolute;
				right: 12rpx;
				bottom: 6rpx;
			}
		}
		
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
