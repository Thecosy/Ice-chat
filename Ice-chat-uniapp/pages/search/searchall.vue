<template>
	<view>
		<use-header :title="title"></use-header>

		<view class="use-search dflex padding-lr w-full bg-drak padding-tb-sm pos-f">
			<!-- 搜索 -->
			<input type="text" class="bg-main padding-lr-lg wh-full border-radius-lg" maxlength="20" :placeholder="placeholder" v-model="keyword" />

			<view class="use-hover dflex-c bg-base pos-a padding-lr-lg border-radius-lg" style="top: 20rpx; bottom: 20rpx; right: 30rpx;z-index: 9;" @click="addFriend">
				<view class="iconfont iconsousuo"></view>
			</view>
		<!-- 	<view v-else class="use-hover dflex-c bg-base pos-a padding-lr-lg border-radius-lg" style="top: 20rpx; bottom: 20rpx; right: 30rpx;z-index: 9;" @click="toSearch">
				<view class="iconfont iconsousuo"></view>
			</view> -->
		</view>
		<view class="use-placeholder"></view>

		<view v-if="showTemp" class="oper-area dflex-c margin-top-big">
			<view class="use-hover">好友</view>
			<view class="use-hover">群聊</view>
			<view class="use-hover">工程师</view>
		</view>
		
		<view class="padding-lr-sm margin-top-sm margin-bottom-sm pos-f w-full" style="top: 152rpx;">
			<view class="">
				<!-- 好友 -->
			</view>
			<view style="height: 50rpx;display: flex;" v-show="!showTemp">
				<text style="margin: 25rpx 20rpx;font-size: 900;font-weight: 900;">群聊</text>
			</view>
			<view class="dflex margin-top-sm bg-color w-full padding-lr-sm padding-tb-sm border-radius" @click="linkman"
				v-for="(item,index) in UserList" :key="index">
				<view class="">
					<image :src="item.profile" mode="" class="border-radius-lg" style="width: 88rpx;height: 88rpx;"></image>
				</view>
				<view class="margin-left-sm w-full dflex-b">
					<view class="">
						{{item.groupName}}
					</view>
					<view class="" style="color: #fff; background-color: #4d9ff4;padding: 20rpx 50rpx;border-radius: 20rpx;" @click.stop="addqunliao(item.id)">
						添加
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
			showTemp:true,
			keyword:'',
			myUserInfo:[],
			UserList: [],
			type: '',
			title: '搜索',
			placeholder: '请输入关键字',
			// +号更多
			moreShow: false,
			moreDatas: [],
			// 服务标签
			tagShow: false,
			tagDatas: [],
			scrollTop: 0,
			scrollToView: '',
			scrollAnimation: false
		};
	},
	onPageScroll(e) {
		// this.scrollTop = e.scrollTop;
	},
	onLoad(options) {
		console.log('chat-search', options);
		this.type = options.type;
		
		let _this = this;
		uni.getStorage({
			key: 'userLogin',
			success: function(res) {
				console.log("获取成功",res);
				_this.myUserInfo = res.data
			}
		});

		switch (this.type) {
			case 'group-chat':
				this.title = '创建群聊';
				break;
			case 'friend':
				this.title = '添加朋友';
				this.placeholder = '请输入手机号';
				break;
			default:
				break;
		}
	},
	onShow(options) {
		let _this = this;
		this.scrollToView = 'msg99';
	},

	methods: {
		addqunliao(id){
			let that = this;
			uni.request({
				url: 'http://localhost:9090/group/addGroupMemberByUserId/'+ this.myUserInfo.id + '/' + id, //仅为示例，并非真实接口地址。
			    method:'GET',//请求方式  或GET，必须为大写
				success: res => {
					console.log('res', res);
					if(res.data.code == 400){
					uni.showModal({
					                title: "添加成功",
					                content: "请等待管理员同意之后加入",
					                showCancel:false,
					                success: function (res) {
					                    if (res.confirm) {
					                        console.log('用户点击确定');
					                    } else if (res.cancel) {
					                        console.log('用户点击取消');
					                    }
					                }
					            });
					
					} else{
					
						console.log("失败")
						uni.showModal({
						                title: "提示",
						                content: "查询失败，请重新输入",
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
		},
		// 图片预览
		preview(imgs, cur) {
			if (!imgs) return;

			uni.previewImage({
				urls: imgs,
				current: cur,
				longPressActions: {
					itemList: ['发送给朋友', '保存图片', '收藏'],
					success: function(data) {
						console.log(res);
					},
					fail: function(err) {
						console.log(err);
					}
				}
			});
		},

		onFocus(e) {
			console.log('onFocus', e);
		},
		onKeyboardChange(e) {
			console.log('onKeyboardChange', e);
		},
		
		toSearch() {
			uni.navigateTo({
				url: `/pages/search/search-result`
			});
		},
		addFriend(){
			this.UserList = null;
			this.showTemp = false;
			let that = this;
			uni.request({
				url: 'http://localhost:9090/group/searchGroupByName/'+ this.keyword, //仅为示例，并非真实接口地址。
			    method:'GET',//请求方式  或GET，必须为大写
				success: res => {
					console.log('res', res);
					if(res.data.code == 400){
					that.UserList = res.data.data;
					
					console.log('返回', that.UserList);
					
					} else if(res.data.code == 402){
						this.showTemp = true;
						console.log("失败")
						uni.showModal({
						                title: "提示",
						                content: "查询失败，请重新输入",
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
			
		},

		toback() {
			uni.navigateBack();
		},

	}
};
</script>

<style lang="scss">
.use-search {
	height: 120rpx;
	line-height: 120rpx;

	.iconfont {
		font-size: 44rpx;
		color: #fff;
	}
}

.oper-area {
	view {
		border-radius: 100rpx;
		margin-right: 20rpx;
		text-align: center;
		padding-top: 16rpx;
		padding-bottom: 16rpx;
		background: #f5f5f5;
		min-width: 180rpx;
		color: #333;

		&:last-child {
			margin-right: 0;
		}
	}
}
</style>

<style lang="scss">
	.use-search {
		height: 120rpx;
		line-height: 120rpx;

		.iconfont {
			font-size: 44rpx;
			color: #fff;
		}
	}

	.bg-color {
		background-color: #f5f5f5;
	}

	.oper-area {
		view {
			border-radius: 100rpx;
			margin-right: 20rpx;
			text-align: center;
			padding-top: 16rpx;
			padding-bottom: 16rpx;
			background: #f5f5f5;
			min-width: 180rpx;
			color: #333;

			&:last-child {
				margin-right: 0;
			}
		}
	}
</style>
