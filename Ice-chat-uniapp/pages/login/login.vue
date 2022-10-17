<template>
	<view class="wh-full">
		<view class="dflex-c w-full padding-top-xl">
			<image src="/static/images/login-bg.png" style="height: 50vh;" mode=""></image>
		</view>
		<view class="fixed pos-bottom w-full bg-base tac padding-top-big"
			style="height: 50vh; border-radius: 120rpx 120rpx 0 0;">
			<view class="" style="width: 70%; margin: 0 auto;">
				<view class="dflex padding-tb" style="border-bottom: 1px solid #fff;">
					<view class="iconfont iconzhanghao"></view>
					<input type="text" value="" class="tal padding-left-sm" placeholder="请输入手机号" v-model="account"
						placeholder-style="color: #fff;" />
				</view>
				<view class="dflex-b padding-tb" style="border-bottom: 1px solid #fff;">
					<view class="dflex">
						<view class="iconfont iconmima"></view>
						<view class="padding-left-sm">
							<input type="text" value="" class="tal" v-model="verification" placeholder="请输入验证码"
								placeholder-style="color: #fff;" />
						</view>
					</view>
					<view class="border-radius dflex-c fs-xs" v-if="show_again" @click="acquire"
						style="width: 170rpx; height: 60rpx; background-color: #fff; color: #4d9ff4;">
						获取验证码
					</view>
					<view class="border-radius dflex-c" v-else @click="acquire"
						style="width: 170rpx; height: 60rpx; background-color: #fff; color: #4d9ff4;">
						{{count}}s
					</view>
					<!-- 	<view class="border-radius dflex-c" v-if="count == 0" @click="acquire" style="width: 170rpx; height: 60rpx; background-color: #fff; color: #4d9ff4;">
						重新获取
					</view> -->
				</view>
				<view class="w-full margin-top-big">
					<view class="dflex-b border-radius-lg">
						<view class="tac padding-tb-sm flex1  fs" style="background-color: #a6cff9;" @click="tologin">登录
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
				// 账号
				account: '',
				// 验证码
				verification: '',
				show_again: true, //  显示发送验证码||请稍后按钮
				count: 0, // 等待时间
				timer: null, //定时器

			};
		},
		methods: {
			acquire() {
				let count = 60;
				if (!this.timer) {
					// 吧data里的数据 赋值给60 秒
					this.count = count
					this.show_again = false
					this.timer = setInterval(() => {
						if (this.count > 0 && this.count <= count) {
							this.count--;
						} else {
							this.show_again = true
							clearInterval(this.timer)
							this.timer = null
						}
					}, 1000)
				}

				this.$api.msg('获取验证码')
			},
			// 登录
			tologin() {
				console.log("登陆")
				if (this.account === '') {
					this.$api.msg('请输入手机号')
					return;
				}
				if (this.verification === '') {
					this.$api.msg('请输入验证码')
					return;
				}
				let that = this;
				uni.request({
					url: 'http://localhost:9090/user/phoneLogin/' + this.account +'/0000', //仅为示例，并非真实接口地址。
					method: 'GET', //请求方式  或GET，必须为大写
					success: res => {
						
						console.log("获取数据",res.data.code)
						if(res.data.code == 400){
						
						uni.setStorage({
							key: "userLogin",
							data: res.data.data.user,
							success() {
								console.log("存储成功");
							}
						})
						uni.switchTab({
							url: '/pages/chat/home'
						});
						return;
						
						console.log('返回', that.UserList);
						} else if(res.data.code == 401){
							console.log("失败")
							uni.showModal({
							                title: "提示",
							                content: "登陆失败，请重新输入",
							                showCancel:false,
							                success: function (res) {
							                    if (res.confirm) {
							                        console.log('用户点击确定');
							                    } else if (res.cancel) {
							                        console.log('用户点击取消');
							                    }
							                }
							            });
							uni.$emit("登陆失败！")
							
						}
					}
				})
			}
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
</style>
