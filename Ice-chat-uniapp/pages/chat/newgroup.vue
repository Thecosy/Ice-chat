<template>
	<view>
		<use-header title="新建群聊">
			<view class="use-hover dflex">
				<view class="" style="width: 25vw;"></view>
			</view>
		</use-header>
		<view class="margin-bottom-xl">
			<view class="tac margin-top-xl margin-bottom-xl">
				<image src="../../static/images/headimg/face_12.jpg" class="border-radius-lg" style="width: 100rpx; height: 100rpx;"
				 mode=""></image>
				<view class="fwb fs">
					{{sitmap.name}}
				</view>
			</view>
			<view class="padding-lr">
				<view class="padding-tb-sm bg-drak dflex padding-lr border-radius">
					<view class="" style="width: 8vw; align-self: flex-start;">
						名称
					</view>
					<view class="padding-left flex1" style="color: #a0a0a0;">
						<input v-model="sitmap.name"/>
					</view>
				</view>
			</view>
			
			<view class="padding-lr">
				<view class="margin-tb-sm padding-tb-sm bg-drak padding-lr border-radius" style="height: 10%; ">
					<view class="dflex">
						<view class="" style="width: 8vw; align-self: flex-start;">
							描述
						</view>
						<view class="padding-left flex1" style="color: #a0a0a0; height: 12vh;">
							<textarea style="max-height: 100px; " v-model="sitmap.ditel"/>
						</view>
					</view>
				</view>
			</view>
		</view>
		
		<view class="gap"></view>
		
		<view class="border-radius padding-lr">
			<view class="dflex-b padding-tb border-bottom " @click="recommend">
				<view class="">
					把TA推荐给好友
				</view>
				<view class="">
					<view class="iconfont iconjiantou ft-dark"></view>
				</view>
			</view>
			<view class="dflex-b padding-tb border-bottom" @click="search">
				<view class="">
					查看聊天记录
				</view>
				<view class="">
					<view class="iconfont iconjiantou ft-dark"></view>
				</view>
			</view>
			<view class="dflex-b padding-tb border-bottom">
				<view class="">
					消息免打扰
				</view>
				<view class="">
					<switch checked color="#4399FC" @change="disturbing" />
				</view>
			</view>
			<view class="dflex-b padding-tb border-bottom">
				<view class="">
					置顶聊天
				</view>
				<view class="">
					<switch checked color="#4399FC" @change="stick" />
				</view>
			</view>
			<view class="dflex-b padding-tb border-bottom" @click="remove">
				<view class="">
					删除聊天记录
				</view>
			</view>
			<view class="dflex-b padding-tb border-bottom" @click="complaint">
				<view class="">
					投诉
				</view>
				<view class="">
					<view class="iconfont iconjiantou ft-dark"></view>
				</view>
			</view>
		</view>
		<view class="padding-top-xl padding-lr-sm padding-bottom">
			<view class="padding-tb-sm bg-main border-radius-lg tac padding-lr-sm bg_color" @click="newGroup">
				<view class=" ">
					创建群聊
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myUserInfo: '',
				sitmap: {
					
					name: '',
					ditel: '',
					
				},
			};
		},
		onLoad(res) {
			let _this = this;
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					_this.myUserInfo = res.data
				}
			});
			
		},
		methods: {
			// 消息免打扰
			disturbing(e) {
				console.log('免打扰状态' + e.target.value);
			},
			// 置顶聊天
			stick(e) {
				console.log('置顶状态' + e.target.value);
			},
			// 推荐给好友
			recommend() {
				this.$api.msg('推荐给好友')
			},
			// 查看聊天记录
			search() {
				this.$api.msg('查看聊天记录')
			},
			// 删除聊天记录
			remove() {
				uni.showModal({
					title: '提示',
					content: '你确定要删除聊天记录么',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});

			},
			// 投诉
			complaint() {
				this.$api.msg('投诉')
			},
			// 创建群聊
			newGroup() {
				let that = this;
				uni.showModal({
					
					title: '提示',
					content: '创建成功之后不可随意修改',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定',that.myUserInfo);
							
							uni.request({
								url: this.serviceUrl + '/group/addGroupById/' + that.myUserInfo.id +'/' +that.sitmap.name, //仅为示例，并非真实接口地址。
								method: 'GET', //请求方式  或GET，必须为大写
								success: res => {
									
									console.log("获取数据",res.data.code)
									if(res.data.code == 400){
									
									
									uni.switchTab({
										url: '/pages/chat/home'
									});
									return;
									
									console.log('返回', that.UserList);
									} else if(res.data.code == 401){
										console.log("失败")
										uni.showModal({
										                title: "提示",
										                content: "创建失败",
										                showCancel:false,
										                success: function (res) {
										                    if (res.confirm) {
										                        console.log('用户点击确定');
										                    } else if (res.cancel) {
										                        console.log('用户点击取消');
										                    }
										                }
										            });
										uni.$emit("创建失败！")
										
									}
								}
							})
							
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #fff;
	}

	.back_color {
		background-color: #f5f5f5;
	}
	
	.img {
		width: 50rpx;
		height: 50rpx;
	}
	.bg_color{
		background-color: #4D9FF4;
		color: #fff;
	}
</style>
