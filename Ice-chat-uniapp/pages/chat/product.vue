<template>
	<view>
		<use-header title="产品列表">
			<view class="use-hover dflex">
				<view class="padding pos-r padding-tb-xs bg-base border-radius" style="top: 16rpx" @click="send">发送</view>
				<view class="" style="width: 25vw;"></view>
			</view>
		</use-header>
		<view class="padding-lr-sm margin-tb-sm dflex-b" v-for="(item,index) in datas" :key="index">
			<view class="padding-lr-sm padding-tb-sm back_color border-radius dflex-b" style="width: 87%;">
				<view class="dflex">
					<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius" style="width: 110rpx; height: 110rpx;"></image>
					<view class="padding-left-lg">
						<view class="">
							{{item.title}}
						</view>
					</view>
				</view>
				<view class="">
					<view class="">
						￥1859
					</view>
				</view>
			</view>
			<view class="h-full padding" @click="activate_a(item)">
				<view class="iconfont" :class="item.checkedClass"></view>
			</view>
			<!-- 	<view class="margin-right-sm" @click="activate">
				<view class="iconfont iconxuanzhongzhuangtai" style="color: #4d9ff4;"></view>
			</view> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				datas: [],
				// 切换状态
				activate: ''
			};
		},
		onShow() {

		},
		onLoad() {
			this.loadData()
		},
		methods: {
			loadData() {

				let datas = [{
					id: 1,
					title: '某某小程序VIP',
				}, {
					id: 2,
					title: '某某小程序VIP',
				}, {
					id: 3,
					title: '某某小程序VIP',
				}, {
					id: 4,
					title: '某某小程序VIP',
				}];

				datas.forEach((data) => {
					data.checkedClass = 'iconweixuanzhongzhuangtai';
				})

				this.datas = datas;
			},
			// 激活
			activate_a(res) {
				this.$api.msg('点击激活')

				this.datas.forEach((data) => {
					if (data.id == res.id) {
						if (data.checkedClass == 'iconweixuanzhongzhuangtai') {
							data.checkedClass = 'iconxuanzhongzhuangtai';
						} else {
							data.checkedClass = 'iconweixuanzhongzhuangtai';
						}
					}
				})
			},
			// 点击发送
			send() {
				this.$api.msg('发送')
				let ids = [];
				this.datas.forEach((data) => {
					if (data.checkedClass == 'iconxuanzhongzhuangtai') {
						ids.push(data.id);
					}
				})

				if (ids.length <= 0) {
					this.$api.msg('请选择发送项');
					return;
				}

				this.$api.msg(JSON.stringify(ids));

			}
		}
	}
</script>

<style lang="scss">
	.back_color {
		background-color: #f5f5f5;
	}

	.iconfont.iconxuanzhongzhuangtai {
		color: #4d9ff4;
	}

	.iconfont.iconweixuanzhongzhuangtai {
		color: #aaaaaa;
	}
</style>
