<template>
	<view>
		<view class="use-search dflex padding-lr w-full bg-drak padding-tb-sm pos-f">
			<!-- 搜索 -->
			<input type="text" class="bg-main padding-lr-lg wh-full border-radius-lg" maxlength="20"
				:placeholder="placeholder" v-model="keyword" />

			<view class="use-hover dflex-c bg-base pos-a padding-lr-lg border-radius-lg"
				style="top: 20rpx; bottom: 20rpx; right: 30rpx;z-index: 9;">
				<view class="iconfont iconsousuo"></view>
			</view>
		</view>
		<view class="" style="padding-top: 130rpx;">
			<view class="padding-lr-sm margin-top-sm margin-bottom-sm  w-full" style="top: 152rpx;"
				v-for="(item,index) in data" :key="index">
				<view class="">
					{{item.title || ''}}
				</view>
				<view class="dflex margin-top-sm bg-color w-full padding-lr-sm padding-tb-sm border-radius"
					@click="toChat" v-for="(datas,index) in item.lid" :key="index">
					<view class="">
						<image :src="datas.img" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;">
						</image>
					</view>
					<view class="margin-left-sm w-full dflex-b">
						<view class="">
							<view class="">
								{{datas.name}}
							</view>
							<view class="" style="color: #aeaeae;">
								{{datas.conent || ''}}
							</view>
						</view>
						<view class="" style="color: #b4b4b4;">
							{{datas.time || ''}}
						</view>
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
				type: '',
				title: '搜索',
				placeholder: '请输入关键字',
				data: [{
						title: '好友',
						lid: [{
								img: 'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg',
								name: 'Usecloud',
								id: 0,
								time: '05/29'

							}, {
								img: 'https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1830914723,3154965800&fm=26&gp=0.jpg',
								name: '李白',
								id: 1,
								time: '05/29'
							},
							{
								img: 'https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3942751454,1089199356&fm=26&gp=0.jpg',
								name: '白白',
								id: 2,
								time: '05/29'
							},
						]

					},
					{
						title: '工程师',
						lid: [{
								img: 'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg',
								name: 'Usecloud',
								id: 0,
							}
						]
					},
					{
						title: '聊天',
						lid: [{
								img: 'https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg',
								name: 'Usecloud',
								id: 0,
								conent: '前端开发',
								time: '05/29'

							}, {
								img: 'https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1830914723,3154965800&fm=26&gp=0.jpg',
								name: '李白',
								conent: '前端后端',
								id: 1,
								time: '05/29'
							},
							{
								img: 'https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3942751454,1089199356&fm=26&gp=0.jpg',
								name: '白白',
								conent: '前端端',
								id: 2,
								time: '05/29'
							},
						]
					},
				]
			};
		},
		onPageScroll(e) {
			// this.scrollTop = e.scrollTop;
		},
		onLoad(options) {

		},
		onShow(options) {},

		methods: {
			onFocus(e) {
				console.log('onFocus', e);
			},
			onKeyboardChange(e) {
				console.log('onKeyboardChange', e);
			},

			toChat() {
				uni.navigateTo({
					url: `/pages/chat/chat`
				});
			},
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
