<template>
	<view>
		<view class="">
			<!-- 	<view class="margin-top-xs dflex-c">
				<view class="dflex-c padding-tb-16 border-radius-lg bg-drak" style="width: 95%;" @click="search">
					<view class="iconfont iconsousuo margin-right-sm"></view>
					<text class="color">搜索</text>
				</view>
			</view> -->
			<view class="dflex-c padding-tb-lg padding-lr bg-drak">
				<view class="tab-item margin-lr-sm" v-for="(item,index) in navList" :key="index" :class="{ active: tabCurrentIndex === index }" @click="add_active(index)">
					<view class="son_color">
						{{item.state}}
					</view>
				</view>
			</view>
			
		</view>
		
			<view v-if="this.tabCurrentIndex == 0" >
				<txl @ev="evFunc" :datas="datas" :index="true" name="name"></txl>
			</view>
		
		
			<view v-else >
				<txl @ev="evFunc" :datas="Gdatas" :index="true" name="name"></txl>
			</view>
		
		<!-- 侧边abc -->
	<!-- 	<view class="fixed dflex-c padding-lr-sm fs-xs ft-dark" style="right: 0rpx; flex-direction: column; top: 0;bottom: 0;">
			<view class="" v-for="(item, idx) in capitals" :key="idx">
				{{item}}
			</view>
			<view class="">
				#
			</view>
		</view> -->
		
		<view class="use-hover use-user fixed-top distance" :style="{ marginBottom: 10 + 'px' }"
			@click="friend">
			<view class="message dflex-c pos-a fs-xs">
				2
			</view>
			<text class="fs-xs">新好友</text>
		</view>
		<view class="use-placeholder"></view>
		<!-- 置顶 -->
		<use-totop style="margin: 0 28rpx 252rpx 0" :scroll-top="scrollTop"></use-totop>
	</view>
</template>

<script>
	import txl from '@/components/yt-txl/index.vue';
	export default {
		components:{
			txl
		},
		data() {
			let datas = [{
			 //    "employeeId": "test1",
				// "name": "范冰冰",
				// "companyId": "1",
				// "companyName": "test company",
				// "departmentId": "testdeptid",
				// "intro": "test dept",
				// "postId": "testpostid",
				// "postName": "testpost",
			}]

			return {
				myUserInfo:[],
				Gdatas: [],
				datas: datas,
				tabCurrentIndex:0,
				scrollTop: 0,
				navHeight: 230,
				capitals: [],
				navList: [{
						id: 0,
						state: '好友',
						cnt: 0,
						loadingType: 'more',
						datas: []
					},
					{
						id: 1,
						state: '群聊',
						cnt: 0,
						loadingType: 'more',
						datas: []
					}
					
				],
				
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
			this.getData();
			this.capitals = [];

			for (let i = 0; i < 26; i++) {
				this.capitals.push(String.fromCharCode((65 + i)));
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
		},
		methods: {
			 evFunc(data){
				 if(data.type == 1){
				 	uni.navigateTo({
				 		url: `/pages/chat/chat?data=` +data.id
				 	});
				 }else if(data.type == 2){
				 	uni.navigateTo({
				 		url: `/pages/chat/groupchat?data=` + data.id,
				 	});
				 }
			            console.log()
			        },
			getData(){
				console.log("cahxubn")
				let that = this;
				//查询UserList
				uni.request({
					url: this.serviceUrl + '/friend/getOnlyFriendByUserId/' + that.myUserInfo.id, //仅为示例，并非真实接口地址。
				    method:'GET',//请求方式  或GET，必须为大写
					success: res => {
						console.log(res)
						that.datas = res.data.data.result.user;
						this.UserListShow = true;
						}
					})
					uni.request({
						url: this.serviceUrl + '/group/getOnlyGroupListByUserId/' + that.myUserInfo.id, //仅为示例，并非真实接口地址。
					    method:'GET',//请求方式  或GET，必须为大写
						success: res => {
							console.log(res)
							that.Gdatas = res.data.data.result.user;
							this.UserListShow = true;
							}
						})
			},
			// 搜索
			search() {
				uni.navigateTo({
					url: `/pages/user/search`
				});
			},
			// 顶部tab点击
			add_active(index){
				this.tabCurrentIndex = index;
			},
			// 新朋友
			friend() {
				this.$api.msg('新的朋友')
			},
			linkman() {
				// if (form === 'iot') {
				// 	uni.navigateTo({
				// 		url: `/pages/chat/iot`
				// 	});
				// 	return;
				// }

				uni.navigateTo({
					url: `/pages/chat/chat`
				});
			}
		}
	}
</script>

<style lang="scss">
	.distance {
		z-index: 999999;
		background: #4d9ff3;
		color: #fff;
		box-shadow: 0px 0px 10px 1px #4d9ff3;
		right: 35rpx;
		bottom: 119rpx;
	}

	.message {
		width: 35rpx;
		height: 35rpx;
		background-color: #f36f4d;
		color: #fff;
		border-radius: 50%;
		right: 0;
		top: 0;
	}
	
	.tab-item {
		padding: 10rpx 50rpx;
	}
	
	.active{
		background-color: #f36e4e;
		border-radius: 50rpx;
		
		.son_color {
			color: #fff !important;
		}
	}
	.son_color{
		color: #a4a4a4;
	}
</style>
