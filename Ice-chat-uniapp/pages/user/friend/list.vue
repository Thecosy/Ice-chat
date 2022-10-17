<template>
	<view>
		<view class="">
			<!-- 	<view class="margin-top-xs dflex-c">
				<view class="dflex-c padding-tb-16 border-radius-lg bg-drak" style="width: 95%;" @click="search">
					<view class="iconfont iconsousuo margin-right-sm"></view>
					<text class="color">搜索</text>
				</view>
			</view> -->
			<view class="dflex-c padding-tb-lg padding-lr bg-drak">
				<view class="tab-item margin-lr-sm" v-for="(item,index) in navList" :key="index" :class="{ active: tabCurrentIndex === index }" @click="add_active(index)">
					<view class="son_color">
						{{item.state}}
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
						style="width: 80rpx;height: 80rpx;"></image>
					<view class="margin-left-sm">
						新的朋友
					</view>
				</view>
			</view>
		</view>
		<view class="">
			<view class="padding-lr bg-drak">
				<view class="padding-tb-xs" style="width: 100%;">
					A
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm" @click="linkman">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="">
			<view class="padding-lr bg-drak">
				<view class="padding-tb-xs" style="width: 100%;">
					B
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="">
			<view class="padding-lr bg-drak">
				<view class="padding-tb-xs" style="width: 100%;">
					C
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="">
			<view class="padding-lr bg-drak">
				<view class="padding-tb-xs" style="width: 100%;">
					D
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="">
			<view class="padding-lr bg-drak">
				<view class="padding-tb-xs" style="width: 100%;">
					E
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top-sm margin-bottom-sm">
				<view class="dflex">
					<view class="">
						<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg"
							style="width: 80rpx;height: 80rpx;"></image>
					</view>
					<view class="margin-left-sm w-full">
						<view class="">
							新的朋友
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 侧边abc -->
		<view class="fixed dflex-c padding-lr-sm fs-xs ft-dark" style="right: 0rpx; flex-direction: column; top: 0;bottom: 0;">
			<view class="" v-for="(item, idx) in capitals" :key="idx">
				{{item}}
			</view>
			<view class="">
				#
			</view>
		</view>
		<view class="use-hover use-user fixed-top distance" :style="{ marginBottom: 10 + 'px' }"
			@click="friend">
			<view class="message dflex-c pos-a fs-xs">
				2
			</view>
			<text class="fs-xs">新好友</text>
		</view>
		<view class="use-placeholder"></view>
		<!-- 置顶 -->
		<use-totop style="margin: 0 28rpx 252rpx 0" :scroll-top="scrollTop"></use-totop>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				tabCurrentIndex:0,
				scrollTop: 0,
				navHeight: 230,
				capitals: [],
				navList: [{
						id: 0,
						state: '好友',
						cnt: 0,
						loadingType: 'more',
						datas: []
					},
					{
						id: 1,
						state: '群聊',
						cnt: 0,
						loadingType: 'more',
						datas: []
					},
					{
						id: 2,
						state: '工程师',
						cnt: 0,
						loadingType: 'more',
						datas: []
					}
				],
			};
		},
		onLoad(res) {
			this.capitals = [];

			for (let i = 0; i < 26; i++) {
				this.capitals.push(String.fromCharCode((65 + i)));
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
		},
		methods: {
			// 搜索
			search() {
				uni.navigateTo({
					url: `/pages/user/search`
				});
			},
			// 顶部tab点击
			add_active(index){
				this.tabCurrentIndex = index;
			},
			// 新朋友
			friend() {
				this.$api.msg('新的朋友')
			},
			linkman() {
				// if (form === 'iot') {
				// 	uni.navigateTo({
				// 		url: `/pages/chat/iot`
				// 	});
				// 	return;
				// }

				uni.navigateTo({
					url: `/pages/chat/chat`
				});
			}
		}
	}
</script>

<style lang="scss">
	.distance {
		background: #4d9ff3;
		color: #fff;
		box-shadow: 0px 0px 10px 1px #4d9ff3;
		right: 60rpx;
		bottom: 155rpx;
	}

	.message {
		width: 35rpx;
		height: 35rpx;
		background-color: #f36f4d;
		color: #fff;
		border-radius: 50%;
		right: 0;
		top: 0;
	}
	
	.tab-item {
		padding: 10rpx 50rpx;
	}
	
	.active{
		background-color: #f36e4e;
		border-radius: 50rpx;
		
		.son_color {
			color: #fff !important;
		}
	}
	.son_color{
		color: #a4a4a4;
	}
</style>
