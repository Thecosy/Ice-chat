<template>
	<view>
	<!-- 	<use-header title="个人中心">
			<view class="use-hover dflex">
				<view class="" style="width: 25vw;"></view>
			</view>
		</use-header> -->
		<view class="">
			<view class="tac margin-top-xl margin-bottom-xl" @click="toUser">
				<image class="border-radius-c" :src="datas.profile || '/static/images/user/default.jpg'" style="width: 100rpx; height: 100rpx;" mode=""></image>
				<view class="fwb fs">
					{{datas.name || "用户名"}}
				</view>
			</view>
			<view class="padding-lr">
				<view class="border-radius padding-tb-lg bg-base pos-r">
					<view class="pos-a iconfont  ft-dark "></view>
					<view class="pos-a pos-full padding-lr-lg dflex-b">
						<view class="" style="height: 88rpx;">
							<view class="">
								VIP会员
							</view>
							<view class="fs-xs">
								2022.05.20到期
							</view>
						</view>
						<view class="iconfont iconVIP line-height-0" style="font-size: 120rpx;"></view>
					</view>
					<view style="height: 88rpx;"></view>
				</view>
			</view>
			<view class="padding-lr margin-top dflex-b">
				
				<view class="border-radius padding-tb-xl item-area" @click="toAssist">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont iconwodexiezhu-01"></view>
						<view class="">
							我的协助
						</view>
					</view>
				</view>
				<view class="border-radius padding-tb-xl item-area" @click="toOrder">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont iconwodedingdan-01"></view>
						<view class="">
							我的订单
						</view>
					</view>
				</view>
				<view class="border-radius padding-tb-xl item-area" @click="toCollect">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont iconshoucang-01"></view>
						<view class="">
							我的收藏
						</view>
					</view>
				</view>
				
			</view>
			<view class="padding-lr margin-top dflex-b">
				<view class="border-radius padding-tb-xl item-area" @click="toAssistCoupon">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont iconyouhuiquan-01"></view>
						<view class="">
							协助券
						</view>
					</view>
				</view>
				<view class="border-radius padding-tb-xl item-area" @click="toFriend">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont icontongxunlu-01"></view>
						<view class="">
							通讯录
						</view>
					</view>
				</view>
				<view class="border-radius padding-tb-xl item-area" @click="toSetting">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont iconshezhi-01"></view>
						<view class="">
							设置
						</view>
					</view>
				</view>
			</view>
			<view class="padding-lr margin-top dflex-b" >
				<view class="border-radius padding-tb-xl item-area" @click="toProfit">
					<view class="dflex-c dflex-flow-c">
						<view class="iconfont iconwodeshouyi-01"></view>
						<view class="">
							我的收益
						</view>
					</view>
				</view>
			</view>
		</view>
		<view style="height: 300rpx;"></view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				LoginTrue: false,
				datas: {
				}
			};
		},
		onShow() {
			let _this = this
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					console.log(res.data);
					_this.datas = res.data
					_this.LoginTrue = true;
				}
			});
		},
		onLoad(res) {
			let _this = this
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					console.log(res.data);
					_this.datas = res.data
					_this.LoginTrue = true;
				}
			});
		},
		methods: {
			// 我的
			toUser(){
				if(this.LoginTrue){
					uni.navigateTo({
					    url: `/pages/user/personal`
					});
				}else{
					uni.navigateTo({
				    url: `/pages/login/login-guide`
				});
				}
			},
			// 通讯录
			toFriend() {
				uni.switchTab({
				    url: `/pages/user/friend/list`
				});
			},
			// 我的协助
			toAssist(){
				uni.navigateTo({
				    url: `/pages/engineer/list`
				});
			},
			// 协助券
			toAssistCoupon(){
				uni.navigateTo({
				    url: `/pages/user/assist/coupon`
				});
			},
			// 收藏 
			toCollect(){
				uni.navigateTo({
				    url: `/pages/user/collect/collect`
				});
			},
			// 我的订单
			toOrder(){
				uni.navigateTo({
				    url: `/pages/user/order/order`
				});
			},
			// 设置
			toSetting(){
				uni.navigateTo({
				    url: `/pages/user/setting/setting`
				});
			},
			// 我的受益
			toProfit(){
				uni.navigateTo({
				    url: `/pages/user/profit/profit`
				});
			},
			
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #fff;
	}

	.vip-bg {
		font-size: 290px;
		line-height: 0;
		top: 50%;
		z-index: 1;
		left: 10%;
		color: #60aaf5;
	}
	
	.item-area {
		width: 28vw;
		background-color: #f2f2f2;
		
		.iconfont {
			font-size: 66rpx;
			line-height: 1;
			color: #4d9ff2;
		}
	}
</style>
