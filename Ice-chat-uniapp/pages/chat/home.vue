<template>
	<view class="page-container box-sizing-b w-full">
		<!-- 头部组件 -->
		<use-header icon=""></use-header>
		<view class="dflex padding-left padding-right-xs w-full bg-drak padding-tb-sm">
			<!-- 搜索 -->
			<view class="use-hover use-search dflex-c border-radius-lg padding-lr w-full" @click="toSearchall">
				<view class="iconfont iconsousuo margin-right-sm"></view>
				<text>搜索</text>
			</view>

			<view class="use-hover dflex-c" @click="toEngineer">
				<image class="margin-lr-sm" src="../../static/images/pg-02.png" style="width: 60rpx;height: 60rpx;">
				</image>
			</view>
			<view class="use-hover iconfont icongengduo padding-right" @click="toMore"></view>
		</view>
		<!-- <view class="use-placeholder"></view> -->
		<use-popup mode="top" v-model="moreShow">
			<view class="more-area dflex dflex-wrap-w" :style="{ 'top': 'calc(180rpx + '+ statusBarHeight +')' }">

				<view class="use-hover more-item dflex-c dflex-flow-c bg-main border-radius-sm margin-left-sm"
					@click="toScan">
					<view class="dflex-c bg-main">
						<view class="iconfont iconsaoma ft-base"></view>
					</view>
					<text>扫一扫</text>
				</view>
				<button  @click="toSignal" class="no-border btn" open-type="share">
					<view class="use-hover more-item dflex-c dflex-flow-c bg-main border-radius-sm margin-left-sm">
						<view class="dflex-c bg-main">
							<view class="iconfont iconweixin ft-base"></view>
						</view>
						<text>暗号</text>
					</view>
				</button>
				<view class="use-hover more-item dflex-c dflex-flow-c bg-main border-radius-sm margin-left-sm"
					data-type="group-chat" @click="toNewGroup">
					<view class="dflex-c bg-main">
						<view class="iconfont iconqunliao ft-base"></view>
					</view>
					<text>创建群聊</text>
				</view>
				<view
					class="use-hover more-item dflex-c dflex-flow-c bg-main border-radius-sm margin-left-sm triangle-up"
					data-type="friend" @click="toSearch">
					<view class="dflex-c bg-main">
						<view class="iconfont icontianjia ft-base"></view>
					</view>
					<text>添加朋友</text>
				</view>
			</view>
		</use-popup>
	
		<view v-if="!UserListShow"  class="margin-home" >
			<image class="image-size" src="../../static/images/empty/message.03a0abb5.png"></image>
		</view>
		<mescroll-body-diy ref="mescrollRef" @init="mescrollInit" top="0" bottom="0" @down="downCallback" @up="upCallback">
				<!-- 模拟的内容 -->
			<!-- 	<image src="https://www.mescroll.com/img/beibei/beibei1.jpg" mode="widthFix"/>
				<image src="https://www.mescroll.com/img/beibei/beibei2.jpg" mode="widthFix"/>
			 -->	<!-- 分页的数据列表 -->
			<!-- 	<good-list :list="goods"></good-list> -->
			
		<view class="padding-top-sm padding-lr margin-bottom">
		<!-- 	<view class="use-hover dflex-b im-chat-item" @click="toChat">
				<view class="istop">
					<view class="iconfont iconzhiding"></view>
				</view>
				<view class="dflex">
					<image class="headimg margin-right" src="../../static/images/headimg/face_12.jpg"></image>
					<view>
						<view class="pos-r dflex">
							<view class="fwb fs margin-right">花木兰</view>
						</view>
						<text>还有一个BUG需要处理</text>
					</view>
				</view>
				<view class="dflex-e dflex-flow-c">
					<text>18:00</text>
					<view class="badge badge-none">2</view>
				</view>
			</view> -->
			
			<view v-for="(user,index) in UserList" :key = "index" class="use-hover dflex-b im-chat-item" @click="toChat(user.id,user.type)">
				<view class="dflex">
					<image style="will-change: transform;" class="headimg margin-right" :src="user.profile"></image>
					<view>
						<view class="pos-r dflex">
							<view class="fwb fs margin-right">{{user.name}}</view>
						</view>
						<text>{{user.newMessages}}</text>
					</view>
				</view>
				<view class="dflex-e dflex-flow-c">
					<text>{{getTime(user.newTime)}}</text>
					<view v-if="user.unMessages == 0" ></view>
					<view v-else class="badge badge-none">{{user.unMessages}}</view>
				</view>
			</view>
			
		<!-- 	<view class="dflex-b im-chat-item" @click="toChat">
				<view class="dflex">
					<image class="headimg margin-right" src="../../static/images/headimg/face_4.jpg"></image>
					<view>
						<view class="pos-r dflex">
							<view class="fwb fs margin-right">马可波罗</view>
						</view>
						<text>还有一个BUG需要处理</text>
					</view>
				</view>
				<view class="dflex-e dflex-flow-c">
					<text>20/01/01</text>
					<view class="badge badge-none">99+</view>
				</view>
			</view> -->
		</view>
		</mescroll-body-diy>
		<view class="use-placeholder"></view>

		<view class="use-hover use-user fixed-top" :style="{ marginBottom: navUserHeight + 'px' }" @click="toUser">
			<text class="iconfont icongerenzhongxin"></text>
		</view>

		<!-- 置顶 -->
		<use-totop :style="{ marginBottom: navHeight + 'rpx' }" :scroll-top="scrollTop"></use-totop>
	</view>
</template>

<script>
	import MescrollBodyDiy from "@/uni_modules/mescroll-uni/components/mescroll-diy/beibei/mescroll-body.vue";
	import MescrollMixin from "@/uni_modules/mescroll-uni/components/mescroll-uni/mescroll-mixins.js";
	import { formatDate } from "@/utils/date.js";

	import {
		mapState
	} from 'vuex';

	export default {
		mixins: [MescrollMixin], // 使用mixin
		components: {
			MescrollBodyDiy // 避免与main.js注册的全局组件名称相同,否则注册组件失效(iOS真机 APP HBuilderX2.7.9)
		},
		computed: {
			...mapState(['member'])
		},
		data() {
			return {
				UserListShow: false,
				myUserInfo:[],
				UserList:[],
				__env: {},

				moreShow: false,

				// 头部参数
				searchAuto: !0,
				searchTip: '请输入搜索关键字',

				scrollTop: 0,
				navHeight: 0,
				navUserHeight: 70,
				statusBarHeight: '20px'
			};
		},
		// 监听页面加载
		onLoad() {
			let _this = this;
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					_this.myUserInfo = res.data
				}
			});
			this.getData()

			this.$api.get_env((res) => {
				this.__env = res;
				console.log('home env', this.__env);
				this.is_mp = this.__env.is_mp;
				this.platform = this.__env.platform;
				this.platform_icon = this.__env.platform_icon;
				this.platform_name = this.__env.platform_name;
				this.statusBarHeight = this.__env.statusBarHeight + 'px';
			});

			if (uni.canIUse('showTabBarRedDot')) {
				uni.showTabBarRedDot({
					index: 3,
					success(res) {
						console.log(res);
					}
				});
			}

			uni.$on('__on_im_receive_msg', res => {
				console.log('__on_im_receive_msg list', res);

				uni.$emit('__on_im_send_msg', '已接收 msg: ' + res);
			});
		},
		onUnload() {
			// uni.$off('__on_im_receive_msg', fn);
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
		},
		// 监听页面显示。页面每次出现在屏幕上都触发，包括从下级页面点返回露出当前页面
		onShow() {
			// this.loadData();
		},
		// 监听用户下拉刷新
		// onPullDownRefresh() {
		// 	return;
		// 	this.loadData(() => {
		// 		uni.stopPullDownRefresh();
		// 	});
		// },
		// 用户点击右上角分享
		// https://uniapp.dcloud.io/api/plugins/share?id=showsharemenu
		onShareAppMessage: function(ops) {
			let _this = this,
				mid = 0;

			if (_this.member && _this.member.id) {
				mid = _this.member.id;
			}

			return {
				title: '用云 · IM',
				path: `/pages/chat/home?mid=${mid}`,
				// imageUrl: 'https://mall-os-api.use-cloud.com/files/upload/image/20200408/200408115587860242.jpg',
				success: function(res) {
					// 转发成功
					console.log('转发成功', res);
				},
				fail: function(res) {
					// 转发失败
					console.log('转发失败', res);
				}
			};
		},

		methods: {
			toSignal() {
				uni.navigateTo({
					url: `/pages/chat/signal` 
				});
			},
			/*下拉刷新的回调 */
			downCallback() {
				// 这里加载你想下拉刷新的数据, 比如刷新轮播数据
				this.getData();
				// 下拉刷新的回调,默认重置上拉加载列表为第一页 (自动执行 page.num=1, 再触发upCallback方法 )
				this.mescroll.resetUpScroll()
			},
			  //时间如果是最近一天，则显示时间，如果是最近一周，则显示周几
			    getTime(time) {
			      let now = new Date();
			      let nowTime = now.getTime();
			      let timeTime = new Date(time).getTime();
			      let timeDiff = nowTime - timeTime;
			      let dayDiff = Math.floor(timeDiff / (24 * 3600 * 1000));
			      let weekDiff = Math.floor(timeDiff / (24 * 3600 * 1000 * 7));
			      let monthDiff = Math.floor(timeDiff / (24 * 3600 * 1000 * 30));
			      let yearDiff = Math.floor(timeDiff / (24 * 3600 * 1000 * 365));
			      //如果时间在最近一天显示小时
			      if (dayDiff == 0) {
			
			        //显示分钟
			        if (Math.floor(timeDiff / (3600 * 1000)) == 0) {
			          if (Math.floor(timeDiff / (60 * 1000)) == 0) {
			            return "刚刚";
			          } else {
			            return Math.floor(timeDiff / (60 * 1000)) + "分钟前";
			          }
			        } else { return Math.floor(timeDiff / (3600 * 1000)) + "小时前"; }
			      } else if (dayDiff == 1) {
			        return "昨天";
			      } else if (dayDiff == 2) {
			        return "前天";
			      } else if (dayDiff > 2 && dayDiff < 7) {
			        return "最近一周";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 7 && dayDiff < 30) {
			        return "一个月内";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 30 && dayDiff < 60) {
			        return "一个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 60 && dayDiff < 90) {
			        return "二个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 90 && dayDiff < 120) {
			        return "三个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 120 && dayDiff < 160) {
			        return "四个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 160 && dayDiff < 180) {
			        return "五个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 180 && dayDiff < 210) {
			        return "六个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 240 && dayDiff < 270) {
			        return "七个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 300 && dayDiff < 330) {
			        return "八个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 330 && dayDiff < 365) {
			        return "九个月前";
			        return formatDate(data, "yyyy-MM-dd");
			      } else if (dayDiff >= 365) {
			        let data = new Date(time);
			        return formatDate(data, "yyyy-MM-dd");
			      }
			},
			formatDate(time) {
			      let data = new Date(time);
			      return formatDate(data, "yyyy-MM-dd");
			},
			getData(){
				let that = this;
				//查询UserList
				uni.request({
					url: this.serviceUrl + '/friend/getFriendByUserId/' + that.myUserInfo.id, //仅为示例，并非真实接口地址。
				    method:'GET',//请求方式  或GET，必须为大写
					success: res => {
						
						that.UserList = res.data.data.result.user;
						this.UserListShow = true;
						}
					})
				  //启动定时器
				this.timer = setInterval(() => {
				console.log("查询UserList")
				//查询UserList
				uni.request({
				        url: 'http://localhost:9090/friend/getFriendByUserId/' + that.myUserInfo.id, //仅为示例，并非真实接口地址。
				        method:'GET',//请求方式  或GET，必须为大写
				        success: res => {
							that.UserList = res.data.data.result.user;
				        }
				    })
				}, 1000)
				
			},
			// 加载数据
			async loadData(callback) {
				
			},
			 isEqual(obj1,obj2) {
			    if(!isObject(obj1) && !isObject(obj2)) {
			        return obj1 === obj2
			    }
			    const len1 = Object.keys(obj1).length
			    const len2 = Object.keys(obj2).length
			    if(len1!==len2) {
			        return false
			    }
			    for(let key in obj1) {
			        if(!isEqual(obj1[key],obj2[key])) {
			            return false
			        }
			    }
			    return true
			},
			toScan() {
				uni.scanCode({
					success(res) {
						console.log(res);

						uni.showModal({
							content: res.result
						})
					}
				})
			},

			toChat(id,type) {
				console.log(type)
				if(type == 1){
					uni.navigateTo({
						url: `/pages/chat/chat?data=` +id
					});
				}else if(type == 2){
					uni.navigateTo({
						url: `/pages/chat/groupchat?data=` + id,
					});
				}
				
				//重置未读信息数量
				// let paramData={"chatGroupId":chatGroupId,"userId":currentUserId,"groupType":groupType};
				// that.$u.api.chatGroup.clearUnReadNum(paramData).then(res => {
				//   if (res.code===200) {
				//     uni.hideLoading();
				//     let param="?chatGroupId="+chatGroupId;
				//     let _url="/pages/chat/chatGroup/chatting";
				//     let chatUrl=_url+param;
				//     uni.navigateTo({
				//       url: chatUrl,
				//       success() {
				// 					//that.resetUnReadNum(paramData);
				//       }
				//     });
				//   }
				//   else{
				//     console.log("清除未读数量失败",res);
				//     that.globalUtil.utilAlert(res.data.msg,"none");
				//     uni.hideLoading();
				//     return;
				//   }
				//   // 获得数据
				// }).catch(res => {
				//   // 失败进行的操作
				//   console.log("清除未读数量接口请求失败");
				// });
			},
			toNewGroup(){
				uni.navigateTo({
					url:  `/pages/chat/newgroup`
				})
			},
			// 搜索
			toSearch(options) {
				console.log('home search', options);

				uni.navigateTo({
					url: `/pages/search/search?type=${options.currentTarget.dataset.type || ''}`
				})
			},
			toSearchall() {
				uni.navigateTo({
					url: `/pages/search/searchall`
				})
			},
			// 工程师
			toEngineer() {
				console.log('home engineer');

				uni.navigateTo({
					// url: `/pages/engineer/list`,
					url: `/pages/user/personal`,
				})
			},
			// 个人中心
			toUser() {
				console.log('home user');

				uni.navigateTo({
					url: `/pages/user/user`
				})
			},

			toMore(e) {
				console.log(e);

				this.moreShow = true;
			}
		},
		mounted() {
			// #ifdef H5 || MP-360
			this.navHeight = 230;
			// #endif
		}
	};
</script>

<style lang="scss">
	page {
		width: 100%;
		min-height: 100%;
		overflow-x: hidden;
	}

	/* #ifdef H5 || MP-360 */
	uni-page-body {
		touch-action: none;
	}

	/* #endif */

	.use-search {
		height: 76rpx;
		line-height: 76rpx;
		background-color: #fff;

		text {
			color: #c0c0c0;
		}

		.iconfont {
			font-size: $font-lg + 8upx;
			color: #c0c0c0;
		}
	}

	.more-area {
		position: absolute;
		right: 30rpx;

		.more-item {
			width: 20vw;
			height: 19vw;
		}

		.iconfont {
			line-height: 1;
			font-size: 48rpx;
		}

		text {
			margin-top: 6rpx;
			font-size: 24rpx;
		}
	}
	
	.badge {
		background: #f46f4d;
	}
	
	.use-user {
		background: #4d9ff3;
		color: #fff;
		box-shadow: 0px 0px 10px 1px #4d9ff3;
	}
	
	.im-chat-item {
		background-color: #f5f5f5;
		border-radius: 14rpx;
		padding: 30rpx;
		margin-bottom: 20rpx;
		position: relative;
		overflow: hidden;

		.istop {
			background-color: #4d9ff3;
			border-radius: 100%;
			position: absolute;
			top: -20rpx;
			left: -20rpx;
			width: 60rpx;
			height: 60rpx;

			.iconfont {
				color: #fff;
				font-size: 10px;
				position: absolute;
				right: 12rpx;
				bottom: 6rpx;
			}
		}

		.headimg {
			width: 88rpx;
			height: 88rpx;
			border-radius: 50%;
		}

		text {
			font-size: 24rpx;
			color: #c0c0c0;
		}
	}

	::-webkit-scrollbar {
		width: 0;
		height: 0;
		color: transparent;
		display: none;
	}
.margin-home{
	    display: flex;
	    margin-top: 81px;
	    flex-wrap: wrap;
	    justify-content: center;
	    align-content: center;
	    align-items: center;
}
.image-size{
	width: 500rpx;
}
	image{width: 100%;vertical-align: bottom;height:auto}
	.header{z-index: 9900;position: fixed;top: --window-top;left: 0;height: 180upx;background: white;}
	.footer{z-index: 9900;position: fixed;bottom: 0;left: 0;height: 100upx;background: white;}
</style>
