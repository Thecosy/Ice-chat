<template>
	<view>
		<view class="use-search dflex padding-lr w-full bg-drak padding-tb-sm pos-f">
			<!-- 搜索 -->
			<input type="text" class="bg-main padding-lr-lg wh-full border-radius-lg" maxlength="20"
				:placeholder="placeholder" v-model="keyword" />

			<view class="use-hover dflex-c bg-base pos-a padding-lr-lg border-radius-lg"
				style="top: 20rpx; bottom: 20rpx; right: 30rpx;z-index: 9;" @click="tosearch">
				<view class="iconfont iconsousuo"></view>
			</view>
		</view>
		<view class="padding-lr-sm margin-top-sm margin-bottom-sm pos-f w-full" style="top: 152rpx;">
			<view class="">
				好友
			</view>
			<view class="dflex margin-top-sm bg-color w-full padding-lr-sm padding-tb-sm border-radius" @click="linkman"
				v-for="(item,index) in data" :key="index">
				<view class="">
					<image :src="item.img" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
				</view>
				<view class="margin-left-sm w-full dflex-b">
					<view class="">
						{{item.name}}
					</view>
					<view class="" style="color: #fff; background-color: #4d9ff4;padding: 20rpx 50rpx;border-radius: 20rpx;" @click.stop="addhaoy">
						添加
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myUserInfo: '',
				type: '',
				title: '添加好友',
				placeholder: '请输入关键字',
				data: [{
						img: 'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg',
						name: 'Usecloud',
						id: 0
					},

				]
			};
		},
		onPageScroll(e) {
			// this.scrollTop = e.scrollTop;
		},
		onLoad(options) {
			let _this = this;
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					_this.myUserInfo = res.data
				}
			});
			console.log(this.myUserInfo)

		},
		onShow(options) {},

		methods: {
			onFocus(e) {
				console.log('onFocus', e);
			},
			onKeyboardChange(e) {
				console.log('onKeyboardChange', e);
			},


			tosearch() {
				uni.navigateTo({
					url: `/pages/search/search`
				});
			},
			// 添加好友
			addhaoy(){
				uni.navigateTo({
					url: `/pages/search/apply`
				});
			}
		}
	};
</script>

<style lang="scss">
	.use-search {
		height: 120rpx;
		line-height: 120rpx;

		.iconfont {
			font-size: 44rpx;
			color: #fff;
		}
	}

	.bg-color {
		background-color: #f5f5f5;
	}

	.oper-area {
		view {
			border-radius: 100rpx;
			margin-right: 20rpx;
			text-align: center;
			padding-top: 16rpx;
			padding-bottom: 16rpx;
			background: #f5f5f5;
			min-width: 180rpx;
			color: #333;

			&:last-child {
				margin-right: 0;
			}
		}
	}
</style>
