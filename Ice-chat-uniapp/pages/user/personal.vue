<template>
	<view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<image :src="this.datas.profile" class="border-radius-lg" style="width: 70rpx; height: 70rpx;"
				 mode=""></image>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm">
						更换头像
					</view>
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					昵称
				</view>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm">
						{{datas.name}}
					</view>
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					性别
				</view>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm">
						{{datas.gender}}
					</view>
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					真实姓名
				</view>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm">
						Usecloud
					</view>
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					手机号
				</view>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm" v-if="member.member_mobile">18825280123</view>
					<button class="cell-tit btn ft-dark" v-else open-type="getPhoneNumber" @getphonenumber="wxPhoneNumber"
					 withCredentials="true">自动获取</button>
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					二维码
				</view>
				<view class="dflex-c">
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					所在地区
				</view>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm">
						{{datas.addr}}
					</view>

					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		
		
		<view class=" padding-lr padding-bottom w-full fixed pos-bottom">
			<view v-if="islogin" class="border-radius margin-top-sm padding-sm dflex-c bg-main log-out-btn"
				@click="openActionSheet">
				<text class="cell-tit">退出登录</text>
			</view>
			<view v-else class="padding-tb bg-main border-radius-lg tac padding-lr-sm bg-base" @click="toLogin">
				<view class=" ">
					推出登陆
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
				datas:{},
				member: {}
			};
		},
		onLoad() {
			let _this = this;
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					console.log(res.data);
					_this.datas = res.data
				}
			});
		
		},
		methods: {
			wxPhoneNumber(wx_phonenumber) {
				// console.log(wx_phonenumber);
				if (!wx_phonenumber.detail.iv) {
					uni.showToast({
						title: '您取消了授权,操作失败',
						icon: 'none'
					});
					return false;
				}
				console.log('-------用户授权，并获取用户基本信息和加密数据------');
				console.log(wx_phonenumber.detail);

			},
			toLogin(){
				uni.clearStorage();
				uni.setStorage({
					key: "userLogin",
					data: '',
					success() {
						console.log("推出成功");
					}
				})
				uni.switchTab({
				    url: '/pages/chat/home'
				});
			},
			// 打开操作菜单
			openActionSheet() {
				this.actionSheetShow = true;

				this.$api.timerout(() => {
					this.actionSheetList = [{
						text: "退出登录",
						color: "#333"
					}, {
						text: "切换账号",
						color: "#333"
					}, ];

					console.log('actionSheet', {
						show: this.actionSheetShow,
						list: this.actionSheetList
					});
				}, 0);
			},
			// 关闭操作菜单
			actionSheetClose() {
				console.log(this.actionSheetShow);
			},
			// 点击操作菜单
			actionSheetClick(index) {
				switch (index) {
					case 0:
						this.$api.msg('退出成功');
						this.logout();
						this.$api.timerout(() => {
							this.$api.tohome();
						}, 200);
						break;
					case 1:
						this.$api.tologin();
						break;
				}
			},
		}
	}
</script>

<style lang="scss">
	
	.iconfont {
		font-size: 14px;
		color: #c0c0c0;
	}
	
	button {
		background-color: #fff;
		padding: 0;
		margin: 0;
		padding-right: 20rpx !important;
		line-height: inherit;
		font-size: 30rpx;
	}

	button::after {
		border: none;
	}
</style>
