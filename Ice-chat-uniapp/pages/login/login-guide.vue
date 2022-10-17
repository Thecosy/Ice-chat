<template>
	<view class="wh-full">
		<view class="dflex-c w-full padding-top-big">
			<image src="/static/images/login-bg.png" style="height: 42vh;" mode="heightFix"></image>
		</view>
		<view class="w-full dflex-b fixed" style="bottom: 90rpx;padding: 0 12vw;">
			<button open-type="getUserInfo" class="border-radius-sm" lang="zh_CN" @getuserinfo="wxGetUserInfo">
				<view class="dflex-b wh-full">
					<view class="iconfont iconweixin tac padding-tb-xs flex1 bg-base"></view>
				</view>
			</button>
			<view class="dflex-b border-radius-sm" style="width: 35vw; height: 90rpx;background-color: #f46f4d; color: #fff;" @click="tologin">
				<view class="tac flex1 fs">手机登录</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userData: {}
			};
		},
		methods: {
			// 登录
			tologin() {
				uni.navigateTo({
					url: '/pages/login/login'
				});
			},
			// 微信授权登录，获取用户信息
			wxGetUserInfo(wx_userinfo) {
				console.log(wx_userinfo);
				this.userData = wx_userinfo.detail.userInfo
				console.log(this.userData);
				
				if (this.userData) {
					uni.setStorage({
						key: 'user',
						data: this.userData,
						success: function() {
							console.log('success');
						}
					});
					uni.navigateTo({
						url: '/pages/chat/home'
					});
					this.$api.msg('微信登录成功')
				}
			},

		}
	}
</script>

<style lang="scss">
	page {
		width: 100%;
		height: 100%;
	}

	.input {
		input {
			border: 0px;
			padding-left: 2rem;
		}

		input:focus {
			border-color: #fff;
			outline: 0;
		}
	}

	button {
		width: 35vw;
		padding: 0;
		margin: 0;
		height: 90rpx;
		background: #4d9ff3;

		&:last-child {
			background: #26a92e;
			color: #fff;
		}
		
		.iconfont {
			font-size: 26px;
			line-height: 1;
		}
	}

	button:after {
		border: 0 none;
	}
</style>
