<template>
	<view class="wh-full pos-r">
		<view class="pos-a pos-tl-c tac" style="top: 30%;">
			<image :src="profile" mode="" class="imgWh"></image>
			<view class="">
				<view class="">
					好友申请已发送
				</view>
				<view class="">
					等待对方验证通过
				</view>
			</view>
		</view>
		<view class="pos-a pos-tl-c" @click="toback"
			style="color: #fff; top: 75%; background-color: #4d9ff4;padding: 20rpx 70rpx;border-radius: 20rpx;">
			返回
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				profile:''

			};
		},
		onLoad(options) {
			
			this.profile = options.profile;
			console.log("options",this.profile)
			// this.getData();
		},
		methods: {
			// 返回
			toback() {
				uni.navigateBack();
			}
		}
	}
</script>

<style lang="scss">
	page {
		width: 100%;
		height: 100%;
	}

	.imgWh {
		width: 300rpx;
		height: 300rpx;
	}
</style>
