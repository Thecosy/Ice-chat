<template>
	<view>
		<use-header :title="ToUserInfo.groupName" >
			
			<view class="use-hover" >
				<view class="iconfont iconhaoyouziliao padding-sm pos-r" style="z-index: 999;top: 0rpx; font-size: 52rpx;"
					@click="toUser"></view>
				<view class="" style="width: 26vw;"></view>
			</view>
			
		</use-header>

		<!-- 内容区 -->
		<view class="chat-content pos-a w-full" :style="{ 'top': 'calc(44px + ' + statusBarHeight + ')'}">
			<scroll-view class="chat-scroll wh-full padding"  scroll-y="true"
				 :scroll-top="scrollTop" :scroll-into-view="scrollIntoIndex" :scroll-with-animation="scrollAnimation">
				<view v-for="(item,index) in messageList" :id="'chatItem_' + index" :key="index" >
					<view class="chat-item dflex-c fs-xs">{{item.time}}</view>

					<view v-if="!item.meFlag" class="chat-item dflex-s">
						<image class="use-hover headimg margin-right-sm" :src="item.profile" @click="toUser(item)">
						</image>
						<view class="padding-tb-sm padding-lr bg-drak border-radius-sm"><text>{{item.text}}</text></view>
					</view>

					<view v-else class="chat-item dflex-e">
						<view class="padding-tb-sm padding-lr bg-base border-radius-sm"><text>{{item.text}}</text></view>
						<image class="use-hover headimg margin-left-sm" :src="myUserInfo.profile"></image>
					</view>
					
				</view>
			</scroll-view>
			<!-- <scroll-view class="chat-scroll wh-full" v-for="(item,index) in chattingd" :key="index" scroll-y="true"
				:scroll-top="scrollTop" :scroll-into-view="scrollToView" :scroll-with-animation="scrollAnimation">
				<view class="padding">
					<view class="chat-item dflex-c fs-xs">{{item.time}}</view>
			
					<view class="chat-item dflex-s">
						<image class="use-hover headimg margin-right-sm" :src="ToUserInfo.profile" @click="toUser(item)">
						</image>
						<view class="padding-tb-sm padding-lr bg-drak border-radius-sm"><text>{{item.a}}</text></view>
					</view>
			
					<view class="chat-item dflex-e">
						<view class="padding-tb-sm padding-lr bg-base border-radius-sm"><text>{{item.b}}</text></view>
						<image class="use-hover headimg margin-left-sm" :src="myUserInfo.profile"></image>
					</view>
				</view>
			</scroll-view> -->
		</view>

		<!-- 输入栏 -->
		<view class="chat-input pos-f dflex padding-tb-sm padding-left safe-area-inset-bottom w-full bg-drak"
			:style="{ bottom: moreShow ? '29vh' :  emojiShow ? '29vh' : '0'}">
			<view class="use-input dflex-c border-radius-sm padding-lr-sm w-full" @click="scrollToBottom()">
				<input type="text" confirm-type="send" :confirm-hold="true" auto-height="true" fixed="true"
					cursor-spacing="20" v-model="textMsg" @focus="onFocus" @confirm="send(); textMsg = null"
					@keyboardheightchange="onKeyboardChange" />
			</view>

			<view class="use-hover iconfont iconbiaoqing margin-lr-sm" @click="emojiShow = true;moreShow=false;"></view>
			<view class="use-hover iconfont icontianjia margin-right-sm" @click="moreShow = true;emojiShow=false;"></view>
		</view>
		<view style="height: 110rpx;"></view>

		<!-- 表情 -->
		<use-popup mode="bottom" v-model="emojiShow">
			<scroll-view :scroll-y="true" style="height: 29vh;">
				<view class="face-area tac padding-sm dflex dflex-wrap-w">
					<text class="expression expression-dianzan"></text>
					<text class="expression expression-bushufu"></text>
					<text class="expression expression-bianbian"></text>
					<text class="expression expression-fankun1"></text>
					<text class="expression expression-feiwen"></text>
					<text class="expression expression-ganmao"></text>
					<text class="expression expression-huaixiao"></text>
					<text class="expression expression-liuhan"></text>
					<text class="expression expression-outu"></text>
					<text class="expression expression-keshui"></text>
					<text class="expression expression-renzhe"></text>
					<text class="expression expression-santiaoxian"></text>
					<text class="expression expression-guaiwu"></text>
					<text class="expression expression-shoushang"></text>
					<text class="expression expression-tianshi"></text>
					<text class="expression expression-shuai"></text>
					<text class="expression expression-xianwen"></text>
					<text class="expression expression-xiaodiaodaya"></text>
					<text class="expression expression-xiong"></text>
					<text class="expression expression-yiwen"></text>
					<text class="expression expression-yun"></text>
					<text class="expression expression-liubixie"></text>
					<text class="expression expression-shimo"></text>
					<text class="expression expression-ku"></text>
					<text class="expression expression-shengqi"></text>
					<text class="expression expression-yousiliao"></text>
					<text class="expression expression-en"></text>
					<text class="expression expression-a"></text>
					<text class="expression expression-bizui"></text>
					<text class="expression expression-baiyan"></text>
					<text class="expression expression-aixin"></text>
					<text class="expression expression-dajing"></text>
					<text class="expression expression-ziya"></text>
					<text class="expression expression-daxiao"></text>
					<text class="expression expression-esi"></text>
					<text class="expression expression-fadai"></text>
					<text class="expression expression-fankun"></text>
					<text class="expression expression-ganga"></text>
					<text class="expression expression-fennu"></text>
					<text class="expression expression-hanyan"></text>
					<text class="expression expression-jingkong"></text>
					<text class="expression expression-haochi"></text>
					<text class="expression expression-emo"></text>
					<text class="expression expression-jingsong"></text>
					<text class="expression expression-jingya"></text>
					<text class="expression expression-kaixin"></text>
					<text class="expression expression-lengku"></text>
					<text class="expression expression-danao"></text>
					<text class="expression expression-liukoushui"></text>
					<text class="expression expression-liulei"></text>
					<text class="expression expression-mengbi"></text>
					<text class="expression expression-mianwubiaoqing"></text>
					<text class="expression expression-nanguo"></text>
					<text class="expression expression-shuizhuo"></text>
					<text class="expression expression-taoyan"></text>
					<text class="expression expression-tanchi"></text>
					<text class="expression expression-siliao"></text>
					<text class="expression expression-tiaopi"></text>
					<text class="expression expression-xiaochulei"></text>
					<text class="expression expression-wuliao"></text>
					<text class="expression expression-xingxingyan"></text>
					<text class="expression expression-xieyan"></text>
					<text class="expression expression-xiasi"></text>
					<text class="expression expression-xiaolian"></text>
				</view>
			</scroll-view>
		</use-popup>

		<!-- +号更多 -->
		<use-popup mode="bottom" v-model="moreShow">
			<view class="more-area dflex dflex-wrap-w">
				<view class="use-hover more-item dflex-c dflex-flow-c" @click="picture">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont icontupian"></view>
					</view>
					<text>图片</text>
				</view>
				<view class="use-hover more-item dflex-c dflex-flow-c">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont iconyuyin"></view>
					</view>
					<text>语音</text>
				</view>
				<view class="use-hover more-item dflex-c dflex-flow-c">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont iconshipin"></view>
					</view>
					<text>视频</text>
				</view>
				<view class="use-hover more-item dflex-c dflex-flow-c" @click="order">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont icondingdan"></view>
					</view>
					<text>订单</text>
				</view>

				<view class="use-hover more-item dflex-c dflex-flow-c" @click="product">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont iconchanpin"></view>
					</view>
					<text>产品</text>
				</view>
				<view class="use-hover more-item dflex-c dflex-flow-c" @click="payment">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont iconfukuan"></view>
					</view>
					<text>付款</text>
				</view>
				<view class="use-hover more-item dflex-c dflex-flow-c">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont iconqunliao1"></view>
					</view>
					<text>创建群聊</text>
				</view>
				<view class="use-hover more-item dflex-c dflex-flow-c" @click="finish">
					<view class="dflex-c bg-drak border-radius-c">
						<view class="iconfont iconshijian02"></view>
					</view>
					<text>结束服务</text>
				</view>
			</view>
		</use-popup>

		<!-- 操作层 -->
		<use-popup class="oper-area-parent" mode="top" v-model="operShow">
			<view class="oper-area dflex pos-a padding-lr-sm"
				:style="{ top: operOffset.top, right: operOffset.right, left: operOffset.left }">
				<view class="use-hover dflex-c dflex-flow-c" @click="toOper('复制')">
					<view class="iconfont iconfuzhi-01"></view>
					<text>复制</text>
				</view>
				<view class="use-hover dflex-c dflex-flow-c" @click="toOper('转发')">
					<view class="iconfont iconzhuanfa-01"></view>
					<text>转发</text>
				</view>
				<view class="use-hover dflex-c dflex-flow-c" @click="toOper('收藏')">
					<view class="iconfont iconshoucang-01"></view>
					<text>收藏</text>
				</view>
				<view class="use-hover dflex-c dflex-flow-c" @click="toOper('删除')">
					<view class="iconfont iconshanchu-01"></view>
					<text>删除</text>
				</view>
				<view class="use-hover dflex-c dflex-flow-c" @click="toOper('多选')">
					<view class="iconfont iconduoxuan-011"></view>
					<text>多选</text>
				</view>

				<view class="pos-a" :class="[operTriangle]"
					:style="{ top: operTriangleOffset.top, right: operTriangleOffset.right, left: operTriangleOffset.left }">
				</view>
			</view>
		</use-popup>
	</view>
</template>
<script>
	let socket;

	import expression from '../../components/expression/expression.css'
	export default {
		data() {
			return {
				scrollIntoIndex:'',
				messageList: [],
				myUserInfo:{},
				textMsg:'',
				ToUserInfo:{},
				toUserid: "",
				emojiShow: false,
				// +号更多
				moreShow: false,
				// 长按操作
				operShow: false,
				operOffset: {
					top: '0',
					left: '30rpx',
					right: 'initial'
				},
				operTriangleOffset: {
					top: '100%',
					left: '20%',
					right: 'initial'
				},
				operTriangle: 'triangle-down',
				// chattingd: [{
				// 	time: '9.09',
				// 	imges: '/static/images/headimg/face_2.jpg',
				// 	useImg: '/static/images/headimg/face_12.jpg',
				// 	qqw: '请问QQ群多少？',
				// 	qq: '1020565856',
				// 	a: '你好！请问有什么可以帮您？',
				// 	b: '想咨询相关流程',
				// 	c: '一套 js 解决前端、后端、数据库的全栈开发 serverless 模式。持续更新中，欢迎加QQ群。用云产品版权归属用云，开源产品个人使用永久免费',
				// 	img: 'https://mall-os-api.use-cloud.com/files/upload/image/20200419/200419103944530621.png'
				// }],
				imgs: [
					'https://mall-os-api.use-cloud.com/files/upload/image/20200419/200419103944530621.png',
					'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1776601493,3966748998&fm=26&gp=0.jpg',
					'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3446442004,2207547936&fm=26&gp=0.jpg'
				],

				moreDatas: [],
				// 服务标签
				tagShow: false,
				tagDatas: [],
				scrollTop: 0,
				scrollToView: '',
				scrollAnimation: false,
				statusBarHeight: '20px'
			};
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onLoad(options) {
			let _this = this;
			uni.getStorage({
				key: 'userLogin',
				success: function(res) {
					console.log("获取成功",res);
					_this.myUserInfo = res.data
				}
			});
			this.getData(options.data);
			this.init(options.data);
			// //获取历史消息
			let toUser = parseInt(options.data)
			console.log(_this.messageList.length)
			if(_this.messageList.length == 0){
				console.log("获取历史消息",toUser);
				uni.getStorage({
					key: "messageList" + toUser,
					success(res) {
						console.log("获取历史消息成功");
						_this.messageList = res.data;
						
					}
				})
			};
		},
		onShow(options) {
			this.scrollToBottom();
		},

		methods: {
			// 跳转页面底部
			scrollToBottom() {
				this.$nextTick(() => {
					//这里将 最后一条聊天的下标'chatItem_' + index 赋值给 刚刚绑定的scrollIntoIndex。这样就可以实现滚动到底部
					let index = this.messageList.length - 1;
					this.scrollIntoIndex = 'chatItem_' + index;
				});
			},
			// //获取历史消息,带分页
			// getHistoryMessage:function(){
			// 	let that = this;
			// 	that.messageList=[];
			// 	that.globalUtil.utilLoading("页面加载中...");
			// 	let param = {
			// 		chatGroupId: that.currentChatGroup.id,
			// 		pageNum:1,
			// 		pageSize:that.pageSize,
			// 	};
			// 	that.$u.api.chatGroup.getChatGroupHistoryMessage(param).then(res => {
			// 		if (res.code == 200) {
			// 			let data = res.data;
			// 			if(data.length>0){
			// 				for (var i = 0; i < data.length; i++) {
			// 					let contentType= data[i].contentType;
			// 					if(contentType==4){
			// 						 data[i].anmitionPlay=false;
			// 					}
			// 				}
			// 				//console.log("信息列表",data);
			// 				that.messageList=data.reverse();
			// 			}
			// 		    //渲染完成以后让滚动到底部
			// 			setTimeout(function(){
			// 				that.scrollIntoView="msg_"+that.messageList[that.messageList.length-1].id;
			// 				uni.hideLoading();
			// 			},500);
			// 		} else {
			// 			that.globalUtil.utilAlert("群信息获取失败");
			// 			uni.hideLoading();
			// 			return;
			// 		}
			// 	})
			// },
			// //向上滑动加载更多历史信息数据
			// loadMoreHistoryMsg:function(){
			// 	let that=this;
			// 	/* 防止滑动太快,导致记录重复 */
			// 	if(that.loadingmMore){
			// 		that.loadingmMore=false;
			// 		console.log("=========防止滑动太快,导致记录重复=============");
			// 		return;
			// 	}
			// 	that.pageNum++;
			// 	let param = {
			// 		chatGroupId: that.currentChatGroup.id,
			// 		pageNum:that.pageNum,
			// 		pageSize:that.pageSize,
			// 	};
			// 	console.log("上划加载更多历史数据分页参数",param);
			// 	let oldScrollViewId=that.messageList[0].id;
			// 	that.loadingmMore=true;
			// 	that.$u.api.chatGroup.getChatGroupHistoryMessage(param).then(res => {
			// 		if (res.code == 200) {
			// 			let data = res.data;
			// 			if(data!=null&&data.length>0){
			// 				for (var i = 0; i < data.length; i++) {
			// 					let contentType= data[i].contentType;
			// 					if(contentType==4){
			// 						 data[i].anmitionPlay=false;
			// 					}
			// 				}
			// 				let moreData= data.reverse();
			// 				that.messageList=moreData.concat(that.messageList);
			// 				setTimeout(function(){
			// 					that.scrollIntoView="msg_"+oldScrollViewId;
			// 					setTimeout(function(){
			// 						that.loadingmMore=false;
			// 					},500);
			// 				},500)
			// 			}
			// 			else{
			// 				that.pageNum--;
			// 				that.loadingmMore=false;
			// 				return;
			// 			}
			// 		} else {
			// 			that.pageNum--;
			// 			that.loadingmMore=false;
			// 			//that.globalUtil.utilAlert("暂无更多信息记录");
			// 			return;
			// 		}
			// 	})
			// },
			msgToScreen(remoteUser,nowUser,text,profile) {
				console.log("构建消息")
				 // 当前用户消息
					let that = this;
					let toUser = parseInt(that.toUserid)
				    if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
						let msgFromServer = {"name": nowUser, "text": text ,"meFlag": true};
						this.messageList.push(msgFromServer);
						uni.setStorage({
							key: "messageList" + toUser,
							data: this.messageList,
							success() {
								console.log("存储成功");
							}
						})
				
						console.log("nowUser",this.messageList)
					}
					else if (remoteUser) {  // remoteUser表示远程用户聊天消息，蓝色的气泡
						let msgFromServer = { "name": remoteUser, "text": text, "profile": profile,}
						this.messageList.push(msgFromServer);
						console.log("准备存储消息")
						uni.setStorage({
							key: "messageList" + toUser,
							data: this.messageList,
							success() {
								console.log("存储成功");
							}
						})
						console.log(this.messageList)
					}
					// setTimeout(()=>{
					// 				this.scrollTop ++;
					// 				console.log('回最底部',this.scrollTop)	
					// },200)
					this.scrollToBottom();
			},
			send() {
			      if (!this.textMsg) {
			       console.log("请输入内容")
			      } else {
			        if (typeof (WebSocket) == "undefined") {
			          console.log("您的浏览器不支持WebSocket");
			        } else {
			          console.log("您的浏览器支持WebSocket");
					  
					  let toUser = parseInt(this.toUserid)
			          // 组装待发送的消息 json
			          // {"from": "zhang", "to": "admin", "text": "聊天文本"}
			          let message = {from:this.toUserid, to:toUser, text:this.textMsg, type:2}
			          socket.send(JSON.stringify(message));  // 将组装好的json发送给服务端，由服务端进行转发
						//TODO: 构建消息内容，本人消息
			          // this.messages.push({user: this.myUserInfo.name, text: this.textMsg})
			          this.msgToScreen(null, this.myUserInfo.name, this.textMsg,null)
			          this.textMsg = '';
					  //回最底部
					  // location.reload()//刷新页面太暴力了，有机会改改
			        }
			      }
			    },
			  init(id) {
				  let userid = this.myUserInfo.id;
			      let _this = this;
			      if (typeof (WebSocket) == "undefined") {
			        console.log("您的浏览器不支持WebSocket");
			      } else {
			        console.log("您的浏览器支持WebSocket");
			        let socketUrl = "ws://localhost:9090/imserver/" + userid;
			        if (socket != null) {
			          socket.close();
			          socket = null;
			        }
			        // 开启一个websocket服务
			        socket = new WebSocket(socketUrl);
			        //打开事件
			        socket.onopen = function () {
			          console.log("websocket已打开");
			        };
			        //  浏览器端收消息，获得从服务端发送过来的文本消息
			        socket.onmessage = function (msg) {
			          console.log("收到数据====" + msg.data)
			          let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
			          if (data.users) {  // 获取在线人员信息
			            _this.users = data.users.filter(user => user.username !== userid)  // 获取当前连接的所有用户信息，并且排除自身，自己不会出现在自己的聊天列表里
			          } else {
						  console.log("准备构建消息内容")
			            // 如果服务器端发送过来的json数据 不包含 users 这个key，那么发送过来的就是聊天文本json数据
			            //  // {"from": "zhang", "text": "hello"}
						let toUser = parseInt(_this.toUserid)
						console.log(toUser)
			           
							console.log("构建消息内容")
							console.log("data.profile",data.profile)
			              // _this.messages.push(data)
			              // 构建消息内容
			              _this.msgToScreen(data.from, null, data.text,data.profile)
			            
			          }
			        };
			        //关闭事件
			        socket.onclose = function () {
			          console.log("websocket已关闭");
			        };
			        //发生了错误事件
			        socket.onerror = function () {
			          console.log("websocket发生了错误");
					  console.log('WebSocket异常，请检查！');
					  that.closeWebScoket();
					  //异常断开,重连服务;
					  //that.reconnect();
			        }
			      }
			    },
				//主动关闭websocket
				closeWebScoket: function() {
					let that = this;
					that.wsOnlion = false;
					that.socketTask.close({
					  success(res) {
					    that.wsOnlion = false;
					    console.log("列表界面websocket关闭成功", res)
					  },
					  fail(err) {
					    console.log("列表界面websocket关闭失败", err)
					  }
					})
				},
				// 重连
				reconnect:function(){
					  let that=this;
				      // 重新连接
				     that.initWebScoket(that.url);
				},
			getData(id){
				let that = this;
				this.toUserid = id;
		
				//获取目标用户信息
				uni.request({
					url: this.serviceUrl + '/group/getGroupinfoByUserId' + '/' + id, //仅为示例，并非真实接口地址。
				    method:'GET',//请求方式  或GET，必须为大写
					success: res => {
						that.ToUserInfo = res.data.data.result;
						console.log('返回',that.toUserid, res);
						}
				})
				//获取用户未读信息  /{username}/{type}/{faceName}
				console.log('获取用户未读信息','/'+ this.myUserInfo.id + '/' + '2' + '/' +this.toUserid)
				uni.request({
					url: this.serviceUrl + '/message/getUnMessage/'+ this.myUserInfo.id + '/' + '2' + '/' +this.toUserid, //仅为示例，并非真实接口地址。
				    method:'GET',//请求方式  或GET，必须为大写
					success: res => {
						console.log('获取用户未读信息', res);
						console.log("构建消息未读信息内容")
						// that.messages.push(data)
						// 构建消息内容
						if(res.data.code == 400){
						console.log(res.data.data.data.length)
							for(var i = 0 ; i < res.data.data.data.length ; i++){
								console.log(i)
								that.msgToScreen(res.data.data.data[i].from, null, res.data.data.data[i].text, res.data.data.data[i].profile)
							}
						
						console.log('获取用户未读信息', res.data.data.data);
						}
					}
				})
				
				// let urls = this.serviceUrl + '/user/GetUserInfoById' + '/' + options.data;
				// console.log(urls);
			},
			onFocus(e) {
				console.log('onFocus', e);
				this.scrollToView = 'msg99';
			},
			onKeyboardChange(e) {
				console.log('onKeyboardChange', e);
			},

			// 长按聊天内容
			onChatContent(options) {
				console.log('onChatContent', options);
				console.log('onChatContent scrollTop', this.scrollTop);
				let dataset = options.currentTarget.dataset;
				let type = dataset.type;

				let view = uni
					.createSelectorQuery()
					.in(this)
					.selectAll(`#${options.currentTarget.id}, .oper-area-parent >>> .oper-area`);

				view.boundingClientRect(datas => {
					console.log('得到布局位置信息', datas);
					let data = datas[0];
					console.log('节点离页面顶部的距离为' + data.top);

					// 判断呈现位置 （上|下）
					if (data.top < 145) {
						// 下
						this.operOffset.top = data.top + data.height + 10 + 'px';
						this.operTriangle = 'triangle-up';
						this.operTriangleOffset.top = '-10px';
					} else {
						// 上
						this.operOffset.top = data.top - 85 + 'px';
						this.operTriangle = 'triangle-down';
						this.operTriangleOffset.top = '100%';
					}

					this.operShow = true;
				}).exec();

				if (type == '1') {
					this.operOffset.left = '30rpx';
					this.operOffset.right = 'initial';

					this.operTriangleOffset.left = '20%';
					this.operTriangleOffset.right = 'initial';
				} else if (type == '2') {
					this.operOffset.left = 'initial';
					this.operOffset.right = '30rpx';

					this.operTriangleOffset.left = 'initial';
					this.operTriangleOffset.right = '20%';
				}
			},

			tomore() {},

			toback() {
				uni.navigateBack();
			},

			toUser(res) {
				console.log(res);
				uni.navigateTo({
					url: `/pages/chat/user?data=${res}`
				});
			},

			toOper(form) {
				uni.$emit('__on_im_send_msg', '发送数据 msg: ' + form);

				this.operShow = false;
			},
			// 发送图片
			picture() {
				uni.chooseImage({
					count: 6, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						console.log(JSON.stringify(res.tempFilePaths));
					}
				});
			},
			// 查看图片
			checkPicture(res, cur) {
				console.log('我被点击了 啊~~~~~~~~' + cur);
				// 预览图片
				uni.previewImage({
					urls: this.imgs,
					longPressActions: {
						itemList: ['发送给朋友', '保存图片', '收藏'],
						success: function(data) {
							console.log('选中了第' + (data.tapIndex + 1) + '个按钮,第' + (data.index + 1) + '张图片');
						},
						fail: function(err) {
							console.log(err.errMsg);
						}
					},
					success: res => {
						console.log(res);
					}
				});
			},
			// 我的订单
			order() {
				uni.navigateTo({
					url: `/pages/chat/order`
				});
			},
			// 结束服务
			finish() {
				let _this = this
				uni.showModal({
					title: '提示',
					content: '确定结束服务吗？',
					confirmText: '结束',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							uni.navigateTo({
								url: '/pages/chat/home'
							});
							_this.$api.msg('结束服务')
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			// 产品
			product() {
				uni.navigateTo({
					url: '/pages/chat/product'
				});
			},
			// 付款
			payment() {
				uni.navigateTo({
					url: '/pages/chat/payment'
				});

			}
		}
	};
</script>

<style lang="scss">
	.chat-content {
		bottom: 110rpx;
	}

	.expression {
		font-size: 56rpx;
		width: 12.5%;
	}

	.chat-scroll {
		.chat-item {
			margin-top: 20rpx;
			margin-bottom: 20rpx;

			.headimg {
				width: 88rpx;
				height: 88rpx;
				border-radius: 50%;
			}

			.chat-item-max {
				max-width: 66vw;
			}

			text {}
		}
	}

	.chat-input {
		z-index: 10090;
		bottom: 0;
		height: 100rpx;

		.iconfont {
			font-size: 58rpx;
		}
	}

	.chat-item text,
	.use-input input {
		font-size: 30rpx;
		/* font-weight: 550; */
	}

	.use-input {
		height: 76rpx;
		line-height: 76rpx;
		background-color: #fff;

		input {
			width: 100%;
			height: 76rpx;
			line-height: 76rpx;
		}

		text {
			color: #c0c0c0;
		}

		.iconfont {
			font-size: 44rpx;
			color: #c0c0c0;
		}
	}
	.face-area, .more-area {
		height: 29vh;
	}
	.more-area {
		padding: 2vw;

		.border-radius-c {
			width: 100rpx;
			height: 100rpx;
		}

		.iconfont {
			font-size: 54rpx;
		}

		text {
			margin-top: 6rpx;
			font-size: 26rpx;
		}

		.more-item {
			width: 24vw;
		}
	}

	.oper-area {
		border-radius: 10rpx;
		background: #fff;

		.use-hover {
			padding: 20rpx 30rpx;
		}
		
		.iconfont {
			font-size: 40rpx;
			line-height: 1;
		}
		
		text {
			margin-top: 6rpx;
			font-size: 24rpx;
		}

		.triangle-up,
		.triangle-down {
			width: 20px;
			height: 10px;

			&:after {
				top: 0;
				right: 0;
			}
		}
	}
</style>
