<template>
	<view>
		<view class="dflex-a bg-drak padding-lr padding-tb-sm" style="height: 100rpx;">
			<view class="padding-sm state-area ft-dark" :class="{active: tabCurrentIndex == index}" @click="switchover(index)" v-for="(item, index) in navList"
			 :key="index">
				{{item.state}}
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius bg-drak margin-top-sm padding-tb dflex-a" @click="toDetail('付款')">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_2.jpg" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="">
					<view class="fwbd">
						咨询服务
					</view>
					<view class="margin-bottom-xxs">
						×1
					</view>
					<view class="ft-dark fs-xs">
						2020/01/15
					</view>
				</view>
				<view class="dflex-c" style="flex-direction: column;">
					<view class="price margin-bottom-sm">
						1859
					</view>
					<view class="ft-white border-radius-sm padding-tb-xs padding-lr bg-base tac bg-main" @click.stop="payment">
						付款
					</view>
				</view>
			</view>
			<view class="border-radius bg-drak margin-top-sm padding-tb dflex-a" @click="toDetail('已完成')">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_13.jpg" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="">
					<view class="fwbd">
						咨询服务
					</view>
					<view class="margin-bottom-xxs">
						×1
					</view>
					<view class="ft-dark fs-xs">
						2020/01/15
					</view>
				</view>
				<view class="dflex-c" style="flex-direction: column;">
					<view class="price margin-bottom-sm">
						1859
					</view>
					<view class="ft-dark">
						已完成
					</view>
				</view>
			</view>
			<view class="border-radius bg-drak margin-top-sm padding-tb dflex-a" @click="toDetail('已完成')">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_12.jpg" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="">
					<view class="fwbd">
						咨询服务
					</view>
					<view class="margin-bottom-xxs">
						×1
					</view>
					<view class="ft-dark fs-xs">
						2020/01/15
					</view>
				</view>
				<view class="dflex-c" style="flex-direction: column;">
					<view class="price margin-bottom-sm">
						1859
					</view>
					<view class="ft-dark">
						进行中
					</view>
				</view>
			</view>
			<view class="border-radius bg-drak margin-top-sm padding-tb dflex-a" @click="toDetail('评论')">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_13.jpg" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="">
					<view class="fwbd">
						咨询服务
					</view>
					<view class="margin-bottom-xxs">
						×1
					</view>
					<view class="ft-dark fs-xs">
						2020/01/15
					</view>
				</view>
				<view class="dflex-c" style="flex-direction: column;">
					<view class="price margin-bottom-sm">
						1859
					</view>
					<view class="ft-white border-radius-sm padding-tb-xs padding-lr bg-base tac bg-main" @click.stop="evaluate">
						评价
					</view>
				</view>
			</view>
			<view class="border-radius bg-drak margin-top-sm padding-tb dflex-a" @click="toDetail('退款中')">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_5.jpg" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="">
					<view class="fwbd">
						咨询服务
					</view>
					<view class="margin-bottom-xxs">
						×1
					</view>
					<view class="ft-dark fs-xs">
						2020/01/15
					</view>
				</view>
				<view class="dflex-c" style="flex-direction: column;">
					<view class="price margin-bottom-sm">
						1859
					</view>
					<view class="ft-dark">
						退款中
					</view>
				</view>
			</view>
			<view class="border-radius bg-drak margin-tb-sm padding-tb dflex-a" @click="toDetail('已完成')">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_6.jpg" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="">
					<view class="fwbd">
						咨询服务
					</view>
					<view class="margin-bottom-xxs">
						×1
					</view>
					<view class="ft-dark fs-xs">
						2020/01/15
					</view>
				</view>
				<view class="dflex-c" style="flex-direction: column;">
					<view class="price margin-bottom-sm">
						1859
					</view>
					<view class="ft-dark">
						已完成
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
				tabCurrentIndex: 0,
				navList: [{
						id: 0,
						state: '全部',
						orderList: []
					},
					{
						id: 1,
						state: '待付款',
						orderList: []
					},
					{
						id: 2,
						state: '进行中',
						orderList: []
					},
					{
						id: 3,
						state: '待评价',
						orderList: []
					},
					{
						id: 4,
						state: '售后',
						orderList: []
					},
				]
			}
		},
		methods: {
			// 顶部切换
			switchover(index) {
				this.tabCurrentIndex = index
			},
			// 付款
			payment(){
				this.$api.msg('付款')
			},
			// 评价
			evaluate(){
				this.$api.msg('评价')
			},
			// 详情
			toDetail(name){
				let res = ''
				uni.navigateTo({
				    url: `/pages/user/order/order-detail?res=${name}`
				});
			}
		}
	}
</script>

<style>
	.state-area {
		/* transition: font-size .35s; */
		width: 24vw;
		text-align: center;
		padding: 10rpx 20rpx;
		background-color: #f5f5f5;
		border-radius: 60rpx;
	}
	.active {
		border-radius: 60rpx;
		background-color: #f46f4e;
		color: #fff !important;
	}
	.price {
		color: #333;
		font-weight: bold;
	}
</style>
