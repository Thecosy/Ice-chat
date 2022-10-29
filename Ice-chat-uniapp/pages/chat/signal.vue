<template>
	<view class="wh-full">
		<use-header title="暗号"></use-header>
	
		<view class="main">
		  <view class="title">和身边朋友输入同一暗号，进入交流。</view>
		  <view style="" class="item" data-key='0'  @click='KeyboarOpen'>
		    <password-input  :numLng='valArr[0].val'></password-input>
		  </view>
		  <number-keyboard ref='KeyboarHid' tabBar v-model="value" @input='onInput' :psdLength='valArr[currentKey].lng'></number-keyboard>
		</view>
		<div v-if="showUserList" class="hm-friends-requests-card">
		  <div class="box">
		    <div class="hd">
		      <text class="friendRequest">{{ this.friendRequest }}</text>
		      <image class="titleClass" :src="this.titleClass" />
		    </div>
		 
		    <div class="group"  v-for="(user,index) in UserList" :key = "index">
		        <div class="container">
		          <image class="userpng" :src="user.profile" />
		          <div class="outer">
		            <text class="name">{{ user.name }}</text>
		            <!-- <text class="address1">{{ this.address1 }}</text> -->
		          </div>
		        </div>
				<div v-if="user.id != myUserInfo.id" class="add">
				  <div class="">
					  <div class="row1">
						<image class="erroimg" :src="erroimg" />
					  </div>
					 
					</div>
					<div class="">
					  <div class="addClass">
						<image @click="toChat(user.id)" class="addimg" :src="addimg" />
					  </div>
					</div>
		        </div>
				<div v-else class="add">
				  
				</div>
		      </div>
		    
		
		  </div>
		</div>
		<view v-else class="dflex-c w-full padding-top-big">
			<image src="/static/images/login-bg.png" style="height: 42vh;" mode="heightFix"></image>
		</view>
		<a-loading ref="loading" title="匹配中..." :tabMask="true" :mask="true" :isClick="true"></a-loading>
		
		<!-- 	<view class="dflex-c w-full padding-top-big">
				<button open-type="getUserInfo" class="border-radius-sm" lang="zh_CN" @getuserinfo="wxGetUserInfo">
					<view class="dflex-b wh-full">
						<view class="iconfont iconweixin tac padding-tb-xs flex1 bg-base"></view>
					</view>
				</button>
			</view> -->
		
	</view>
</template>

<script>
	import numberKeyboard from '@/components/number-keyboard/number-keyboard.vue'
	import passwordInput from '@/components/password-input/password-input.vue'
	
	export default {
		components: {
		  numberKeyboard,
		  passwordInput
		},
		data() {
			return {
				showUserList:false,
				UserList:[],
				myUserInfo:[],
				friendRequest: '好友列表',
				titleClass:
				  '/static/hm-friends-requests-card/images/img_24029_0_5.png',
		
				erroimg:
				  '/static/hm-friends-requests-card/images/img_24029_0_2.png',
				addimg:
				  '/static/hm-friends-requests-card/images/img_24029_0_1.png',
			
				password: "", //输入的内容
				value: "",
				currentKey: 0,
				valArr: [{val:'',lng:6},{val:'',lng:11},{val:'',lng:4}],
				userData: {}
			};
		},
		onLoad() {
			let _this = this;
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					_this.myUserInfo = res.data
				}
			});
		  //因为此时实例没有挂载完成，需要延迟操作
		  setTimeout(() => {
		    this.$refs.KeyboarHid.open()
		  }, 50)
		},
		methods: {
			toChat(id) {
				
					uni.navigateTo({
						url: `/pages/chat/chat?data=` +id
					});
			
				
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
			//打开键盘
			KeyboarOpen(e) {
			  console.log(e)
			  let eq = e.currentTarget.dataset.key;
			console.log(eq)
			  this.currentKey = eq;
			  this.value = this.valArr[eq].val
			  this.$refs.KeyboarHid.open()
			},
			//输入的值
			onInput(val) {
			  let that = this;
			  this.valArr[this.currentKey].val=val
			  if(this.value.length == 4){
			  	console.log("输入完成")
				this.$refs.KeyboarHid.close()
			  	//打开加载动画
			  	
			  	//关闭加载动画
			  	setTimeout(() => {
			  	  // this.$refs.loading.close();
				  console.log("请求",that.myUserInfo.id);
				  uni.request({
				  	url: this.serviceUrl + '/friend/setSignal/' + that.myUserInfo.id + '/' + that.value, //仅为示例，并非真实接口地址。
				      method:'GET',//请求方式  或GET，必须为大写
				  	success: res => {
				  		// console.log(res);
				  		that.UserList = res.data.data.result;
						console.log(that.UserList)
				  		this.showUserList = true;
				  		}
				  	})
					//启动定时器
					this.timer = setInterval(() => {
					console.log("查询UserList")
					//查询UserList
					uni.request({
						url: this.serviceUrl + '/friend/setSignal/' + that.myUserInfo.id + '/' + that.value, //仅为示例，并非真实接口地址。
					    method:'GET',//请求方式  或GET，必须为大写
						success: res => {
							// console.log(res);
							that.UserList = res.data.data.result;
											console.log(that.UserList)
							this.showUserList = true;
							}
						})
					}, 1000)
			  	}, 2000)
				// this.$refs.loading.open();
			  }
			},
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
			// background: #26a92e;
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
  .main {
    padding: 0rpx 40rpx;
  }

  .ipt {
    border-bottom: 1rpx solid #CCCCCC;
  }

  .item {
	  display: flex;
	      padding: 7px 0px;
	      justify-content: center;
	      flex-wrap: nowrap;
	      flex-direction: row;
	      align-content: center;
	      align-items: center;
  }

  .title {
	  margin-top: 30rpx;
    display: flex;
        speak: di;
        speak: dis;
        padding: 0px 29px;
        flex-wrap: nowrap;
        justify-content: center;
    
  }
  .hm-friends-requests-card {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
	margin-top: 40rpx;
	}
  
  .box {
    display: flex;
    align-items: flex-start;
    flex-direction: column;
    
    border-radius: 20px;
    box-shadow: 0px 10px 30px rgba(209, 213, 223, 0.5);
    background-color: #ffffff;
    width: 370px;
    
  }
  
  .hd {
	  margin: 15rpx;
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding-right: 32px;
    padding-left: 30px;
    width: 370px;
  }
  
  .friendRequest {
    opacity: 1;
    width: 64px;
    height: 24px;
    line-height: 24px;
    white-space: nowrap;
    color: #858997;
    font-size: 16px;
    font-weight: 400;
  }
  
  .titleClass {
    margin-top: 10px;
    width: 18px;
    height: 4px;
  }
  
  .bd {
    box-sizing: border-box;
    display: flex;
    align-items: center;
    flex-direction: row;
    margin-top: 30px;
    padding-right: 27px;
    padding-left: 27px;
    height: 135px;
  }
  .add{
	  display: flex;
	  padding: 15rpx;
  }
  .group {
	  margin-top: 10rpx;
	  margin-bottom: 20rpx;
    box-sizing: border-box;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        padding-right: 32px;
        padding-left: 30px;
        width: 370px;
  }
  
  .container {
    display: flex;
    align-items: center;
    flex-direction: row;
    height: 60px;
  }
  
  .userpng {
    margin-left: 3px;
    border-radius: 30px;
    width: 60px;
    height: 60px;
    overflow: hidden;
  }
  
  .outer {
    display: flex;
    align-items: flex-start;
    flex-direction: column;
    justify-content: center;
    margin-left: 15px;
    height: 60px;
  }
  
  .name {
    opacity: 1;
    line-height: 24px;
    white-space: nowrap;
    color: #141821;
    font-size: 16px;
    font-weight: 400;
  }
  
  .address1 {
    opacity: 1;
    margin-top: 1px;
    line-height: 21px;
    white-space: nowrap;
    color: #858997;
    font-size: 14px;
    font-weight: 400;
  }
  
  .blockTwo {
    display: flex;
    align-items: center;
    flex-direction: row;
    margin-top: 15px;
    height: 60px;
  }
  
  .userImage {
    margin-left: 3px;
    border-radius: 30px;
    width: 60px;
    height: 60px;
    overflow: hidden;
  }
  
  .block {
    display: flex;
    align-items: flex-start;
    flex-direction: column;
    justify-content: center;
    margin-left: 15px;
    height: 60px;
  }
  
  .wrap {
    display: flex;
    flex-direction: row;
  }
  
  .name2 {
    opacity: 1;
    width: 32px;
    height: 24px;
    line-height: 24px;
    white-space: nowrap;
    color: #141821;
    font-size: 16px;
    font-weight: 400;
  }
  
  .address2 {
    opacity: 1;
    margin-top: 1px;
    max-width: 75px;
    height: 21px;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 21px;
    white-space: nowrap;
    color: #858997;
    font-size: 14px;
    font-weight: 400;
  }
  
  .list {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    margin-left: 60px;
    width: 45px;
    height: 135px;
  }
  
  .row1 {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    border-radius: 20px;
    background-color: #fec4b0;
    width: 40px;
    height: 40px;
  }
  
  .erroimg {
    width: 23px;
    height: 23px;
  }
  
  .row2 {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    margin-top: 35px;
    border-radius: 20px;
    background-color: #fec4b0;
    width: 40px;
    height: 40px;
  }
  
  .erroimg {
    width: 23px;
    height: 23px;
  }
  
  .listTwo {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    width: 45px;
    height: 135px;
  }
  
  .addClass {
	  margin-left: 10rpx;
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    border-radius: 20px;
    background-color: #d9f2ec;
    width: 40px;
    height: 40px;
  }
  
  .addimg {
    width: 16px;
    height: 16px;
  }
  
  .rowClass {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    margin-top: 35px;
    border-radius: 20px;
    background-color: #d9f2ec;
    width: 40px;
    height: 40px;
  }
  
  .addimg {
    width: 16px;
    height: 16px;
  }
  
  .threeClass {
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-top: 15px;
    padding-right: 29px;
    padding-left: 30px;
    width: 370px;
  }
  
  .blockThree {
    display: flex;
    flex-direction: row;
  }
  
  .avator {
    border-radius: 30px;
    width: 60px;
    height: 60px;
    overflow: hidden;
  }
  
  .containerTwo {
    display: flex;
    align-items: flex-start;
    flex-direction: column;
    margin-left: 15px;
    height: 60px;
  }
  
  .groupTwo {
    display: flex;
    flex-direction: row;
  }
  
  .name3 {
    opacity: 1;
    width: 48px;
    height: 24px;
    line-height: 24px;
    white-space: nowrap;
    color: #141821;
    font-size: 16px;
    font-weight: 400;
  }
  
  .address3 {
    opacity: 1;
    margin-top: 1px;
    width: 126px;
    height: 21px;
    line-height: 21px;
    white-space: nowrap;
    color: #858997;
    font-size: 14px;
    font-weight: 400;
  }
  
  .groupThree {
    display: flex;
    align-items: center;
    flex-direction: row;
    height: 60px;
  }
  
  .erroClass {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    border-radius: 20px;
    background-color: #fec4b0;
    width: 40px;
    height: 40px;
  }
  
  .erroimg {
    width: 23px;
    height: 23px;
  }
  
  .tianjiaWrap {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    margin-left: 5px;
    border-radius: 20px;
    background-color: #d9f2ec;
    width: 40px;
    height: 40px;
  }
  
  .addimg {
    width: 16px;
    height: 16px;
  }
  
</style>

