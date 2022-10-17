<template>
	<view class="use-login wh-full">
		<!-- 弹出框 -->
		<view v-if="is_show" class="l-mask"></view>
		<view v-if="is_show" class="box-container">
			<view class="title fs-lg"><text>{{title}}</text></view>
			<view class="dflex dflex-flow-c margin-top">
				<image class="image-sm" mode="aspectFill" src="../../static/images/user/default.png"></image>
				<text class="">微信授权</text>
			</view>
			<view class="btn-contaer margin-top-lg">
				<button class="no-border" @click="cancel">取消</button>
				<button class="no-border" :open-type="type" :lang="lang" @getuserinfo="getUserInfo" @getphonenumber="getPhoneNumber" :withCredentials="withCredentials">确定</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			// getUserInfo getPhoneNumber
			type: {
				type: String,
				default: 'getUserInfo'
			},
			lang: {
				type: String,
				default: 'zh_CN'
			},
			withCredentials: {
				type: Boolean,
				default: true
			},
			title: {
				type: String,
				default: '获取授权信息'
			},
			show: {
				type: [String, Number, Boolean],
				default: 0
			}
		},
		computed: {
			__show: {
				get(){
					console.log('is_show', this.show);
					this.is_show = this.show;
					return this.show;
				},
				set(){
					
				}
			}
		},
		data() {
			return {
				is_show: this.show
			};
		},
		methods: {
			cancel() {
				this.is_show = false;
				this.$emit('cancel', {
					type: 'cancel'
				});
			},
			getUserInfo(wx_userinfo){
				this.is_show = false;
				let _this = this;
				if (!wx_userinfo.detail.iv) {
					this.$emit('auth', {
						type: 'userinfo',
						result: 'cancel',
					});
					return false;
				}
				console.log('-------用户授权，并获取用户基本信息和加密数据------');
				
			},
			getPhoneNumber(wx_phonenumber){
				this.is_show = false;
				uni.showLoading({
					title: '加载中'
				});
				if (!wx_phonenumber.detail.iv) {
					this.$emit('auth', {						
						type: 'phonenumber',
						result: 'cancel',
					});
					return false;
				}
				
				console.log('-------用户授权，并获取用户基本信息和加密数据------');
				console.log(wx_phonenumber.detail);
				
			}
		}
	};
</script>

<style lang="scss">
	.use-login{
		overflow: hidden;
	}
	
	.use-login .l-mask {
		position: absolute;
		top: 0;
		left: 0;
		bottom: 0;
		right: 0;
		background: rgba(51, 51, 51, 0.5);
		z-index: 999;
	}
	
	.use-login .box-container {
		position: absolute;
		width: 80vw;
		background: #fff;
		left: 50%;
		transform: translate(-50%, -50%);
		top: 50%;
		z-index: 999;
		border-radius: 20rpx;
		text-align: center;
		padding: 30rpx;
	
		.title {
			margin-top: 30rpx;
			font-size: 34rpx;
			font-weight: 600;
		}
	
		.btn-contaer {
			display: flex;
		}
	
		button {
			background: #eee;
			color: #333;
			width: 50%;
			border-radius: 50rpx;
			border: 1px solid #eee;
			font-size: 30rpx;
			padding: 16rpx 0;
			line-height: inherit;
			
			&:last-child {
				margin-left: 10px;
				background: $base-color;
				border: 1px solid $base-color;
				color: #fff;
			}
		}
	}
</style>
