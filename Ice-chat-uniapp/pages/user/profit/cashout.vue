<template>
	<view>
		<view class="dflex-c" style="margin-top: 150rpx; flex-direction: column;">
			<view class="dflex box padding-tb-xs">
				<text class="fs-34 padding-right-sm" style="font-weight: bold;">￥</text>
				<input type="text" value="" v-model="money" placeholder="输入提现金额"/>
			</view>
			<view class="dflex-flow-c dflex-c padding-top-xs" style="align-self: stretch;width: 67%;">
				可提现收益￥580
			</view>
		</view>
		<view class=" padding-lr-sm padding-bottom w-full fixed pos-bottom">
			<view class="tac padding-tb">
				提现金额自动转入微信零钱
			</view>
			<view class="padding-tb bg-main border-radius-lg tac padding-lr-sm bg-base" @click="withdraw">
				<view class=" ">
					提现
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				money:''
			}
		},
		methods: {
			// 提现
			withdraw(){
				this.$api.msg('提现')
			}
		}
	}
</script>

<style>
.box{
	width: 60%;
	border-bottom: 2rpx solid #d4d4d4;
}
</style>
