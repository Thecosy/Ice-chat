<template>
	<view>
		<view class="padding-lr margin-top" v-if="tltie === '付款'">
			<view class="bg-base tac border-radius padding-tb-sm dflex-c">
				<text class="iconfont iconshijian padding-right-xs fs-xl"></text>
				待付款
			</view>
		</view>
		<view class="padding-lr margin-top" v-if="tltie === '已完成'">
			<view class="bg-base tac border-radius padding-tb-sm dflex-c">
				<text class="iconfont iconshijian padding-right-xs fs-xl"></text>
				已付款
			</view>
		</view>
		<view class="padding-lr margin-top" v-if="tltie === '退款中'">
			<view class="bg-base tac border-radius padding-tb dflex-c">
				退款已申请,后台审核中
			</view>
		</view>
		<view class="padding-lr margin-top" v-if="tltie === '评论'">
			<view class="bg-base tac border-radius padding-tb dflex-c">
				待评论
			</view>
		</view>
		<view class="padding-lr margin-top-sm">
			<view class="border-radius padding-lr bg-drak  padding-tb dflex">
				<view class="dflex" style="flex-direction: column;">
					<image src="/static/images/headimg/face_2.jpg" mode="" class="border-radius-lg"
						style="width: 88rpx;height: 88rpx;"></image>
					<view class="margin-top-xs fs-xs">
						工程师·欧阳
					</view>
				</view>
				<view class="" style="margin-left: 100rpx;">
					<view class="">
						咨询服务
					</view>
					<view class="">
						x1
					</view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top-sm">
			<view class="border-radius padding-lr bg-drak  padding-tb">
				<view class="">
					<view class="">
						订单编号: <text class="margin-left">1322432423432</text>
					</view>
					<view class="">
						付款时间: <text class="margin-left">20/21/21</text>
					</view>
					<view class="">
						支付方式: <text class="margin-left">微信支付</text>
					</view>
					<view class="">
						支付编号: <text class="margin-left">1322432423432</text>
					</view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top-sm">
			<view class="border-radius padding-lr bg-drak  padding-tb dflex" @click="particulars">
				<view class="">
					<view class="">
						总计: <text class="margin-left">￥1987</text>
					</view>
					<view class="">
						优惠: <text class="margin-left">￥1987</text>
					</view>
					<view class="">
						实付款: <text class="font_color margin-left">￥1987</text>
					</view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top-sm">
			<view class="border-radius padding-lr bg-drak  padding-tb dflex" @click="particulars">
				<view class="">
					<view class="">
						总次数: <text class="margin-left">5次</text>
					</view>
					<view class="">
						已使用: <text class="margin-left">2次</text>
					</view>
					<view class="">
						剩余: <text class="font_color margin-left">3次</text>
					</view>
				</view>
			</view>
		</view>
		<view class="padding-lr padding-bottom w-full fixed pos-bottom" v-if="tltie === '付款'">
			<view class="tac padding-tb" @click="cancel">
				取消订单
			</view>
			<view class="padding-tb bg-main border-radius-lg tac padding-lr bg-rgb" @click="payment">
				<view class=" ">
					付款
				</view>
			</view>
		</view>
		<view class="padding-lr padding-bottom w-full fixed pos-bottom" v-if="titie === '退款中'">
			<view class="padding-tb bg-main border-radius-lg tac padding-lr bg-rgb" @click="payment">
				<view class=" ">
					取消申请
				</view>
			</view>
		</view>
		<view class="padding-lr padding-bottom w-full fixed pos-bottom" v-if="tltie === '评论'">
			<view class="padding-tb bg-main border-radius-lg tac padding-lr bg-rgb" @click="comment">
				<view class=" ">
					去评论
				</view>
			</view>
		</view>
		<view class="dflex-b padding-lr margin-top-xl" v-if="tltie === '已完成'">
			<view class="border-radius dflex-c left_anj" @click="reimburse">
				<text class="iconfont iconwodeshouyi-01 padding-right-xs fs-xl"></text>
				<view class="">
					申请退款
				</view>
			</view>
			<view class="border-radius dflex-c right_anj" @click="forward">
				<view class="dflex-c">
					<text class="iconfont iconzhuanfa-01 padding-right-xs fs-xl"></text>
					<view class="">
						转发订单
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
				tltie: ''

			}
		},
		onLoad(res) {
			console.log(res.res);
			this.tltie = res.res

		},
		methods: {
			// 取消订单
			cancel() {
				this.$api.msg('取消订单')
			},
			// 付款
			payment() {
				this.$api.msg('付款')
			},
			// 申请退款
			reimburse() {
				this.$api.msg('申请退款')
			},
			// 转发订单
			forward() {
				this.$api.msg('转发订单')
			},
			// 去评论
			comment(){
				this.$api.msg('去评论')
			}

		}
	}
</script>

<style>
	.font_color {
		color: #000;
		font-weight: bold;
	}

	.bg-rgb {
		background-color: #f46f4e;
		color: #fff;
	}

	.left_anj {
		padding: 15rpx 70rpx;
		background-color: #464646;
		color: #fff;
	}

	.right_anj {
		padding: 15rpx 70rpx;
		background-color: #f46f4e;
		color: #fff;
	}
</style>
