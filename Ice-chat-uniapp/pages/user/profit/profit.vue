<template>
	<view>
		<view class="padding-lr margin-top">
			<view class="padding-tb-sm dflex padding-lr-lg border-radius dflex-b" style="background-color: #4d9ff3;">
				<view class="">
					<view class="ft-white">
						<text class="fs-xs">￥</text>
						<text class="fs-xxl fwb">290</text>
					</view>
					<view class="ft-white margin-left">
						总收益
					</view>
				</view>
				<view class="ft-white border-radius-xs padding-tb-xs tac bg-main" style="width: 19%; color: #4d9ff3;" @click="deposit">
					提现
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="fs-34" style="font-weight: bold;">
				收益明细
			</view>
		</view>
		<view class="padding-lr margin-bottom">
			<view class="padding-tb-sm border-line">
				<view class="dflex-b">
					<view class="">
						打赏
					</view>
					<view class="fs-34 ft-base" style="font-weight: bold;">
						￥50
					</view>
				</view>
				<view class="dflex-b ft-dark">
					<view class="">
						2020-01-20 15:05:36
					</view>
					<view class="">
						余额: ￥290
					</view>
				</view>
			</view>
			<view class="padding-tb-sm border-line">
				<view class="dflex-b">
					<view class="">
						打赏
					</view>
					<view class="fs-34 ft-base" style="font-weight: bold;">
						￥50
					</view>
				</view>
				<view class="dflex-b ft-dark">
					<view class="">
						2020-01-20 15:05:36
					</view>
					<view class="">
						余额: ￥290
					</view>
				</view>
			</view>
			<view class="padding-tb-sm border-line">
				<view class="dflex-b">
					<view class="">
						打赏
					</view>
					<view class="fs-34 ft-base" style="font-weight: bold;">
						￥50
					</view>
				</view>
				<view class="dflex-b ft-dark">
					<view class="">
						2020-01-20 15:05:36
					</view>
					<view class="">
						余额: ￥290
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

			}
		},
		methods: {
			// 提现
			deposit() {
				uni.navigateTo({
				    url: `/pages/user/profit/cashout`
				});
			}
		}
	}
</script>

<style>
	
</style>
