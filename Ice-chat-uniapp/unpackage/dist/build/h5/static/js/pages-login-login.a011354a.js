(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-login-login"],{2314:function(t,i,n){"use strict";n.r(i);var e=n("71c4"),a=n.n(e);for(var o in e)"default"!==o&&function(t){n.d(i,t,(function(){return e[t]}))}(o);i["default"]=a.a},2962:function(t,i,n){var e=n("24fb");i=e(!1),i.push([t.i,'@charset "UTF-8";\n/* 页面左右间距 */\n/* 文字尺寸 */\n/*文字颜色*/\n/* 边框颜色 */\n/* 图片加载中颜色 */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */uni-page-body[data-v-78f3e4f9]{width:100%;height:100%}.input uni-input[data-v-78f3e4f9]{border:0;padding-left:2rem}.input uni-input[data-v-78f3e4f9]:focus{border-color:#fff;outline:0}',""]),t.exports=i},"71c4":function(t,i,n){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var e={data:function(){return{account:"",verification:"",show_again:!0,count:0,timer:null}},methods:{acquire:function(){var t=this,i=60;this.timer||(this.count=i,this.show_again=!1,this.timer=setInterval((function(){t.count>0&&t.count<=i?t.count--:(t.show_again=!0,clearInterval(t.timer),t.timer=null)}),1e3)),this.$api.msg("获取验证码")},tologin:function(){if(console.log("登陆"),""!==this.account)if(""!==this.verification){uni.request({url:this.serviceUrl+"/user/phoneLogin/"+this.account+"/0000",method:"GET",success:function(t){if(console.log("获取数据",t.data.code),400==t.data.code)return uni.setStorage({key:"userLogin",data:t.data.data.user,success:function(){console.log("存储成功")}}),void uni.switchTab({url:"/pages/chat/home"});401==t.data.code&&(console.log("失败"),uni.showModal({title:"提示",content:"登陆失败，请重新输入",showCancel:!1,success:function(t){t.confirm?console.log("用户点击确定"):t.cancel&&console.log("用户点击取消")}}),uni.$emit("登陆失败！"))}})}else this.$api.msg("请输入验证码");else this.$api.msg("请输入手机号")}}};i.default=e},a5b2:function(t,i,n){"use strict";n.r(i);var e=n("dc84"),a=n("2314");for(var o in a)"default"!==o&&function(t){n.d(i,t,(function(){return a[t]}))}(o);n("d9e5");var c,s=n("f0c5"),l=Object(s["a"])(a["default"],e["b"],e["c"],!1,null,"78f3e4f9",null,!1,e["a"],c);i["default"]=l.exports},a65c:function(t,i,n){var e=n("2962");e.__esModule&&(e=e.default),"string"===typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);var a=n("4f06").default;a("48f77858",e,!0,{sourceMap:!1,shadowMode:!1})},d9e5:function(t,i,n){"use strict";var e=n("a65c"),a=n.n(e);a.a},dc84:function(t,i,n){"use strict";var e;n.d(i,"b",(function(){return a})),n.d(i,"c",(function(){return o})),n.d(i,"a",(function(){return e}));var a=function(){var t=this,i=t.$createElement,n=t._self._c||i;return n("v-uni-view",{staticClass:"wh-full"},[n("v-uni-view",{staticClass:"dflex-c w-full padding-top-xl"},[n("v-uni-image",{staticStyle:{height:"50vh"},attrs:{src:"/static/images/login-bg.png",mode:""}})],1),n("v-uni-view",{staticClass:"fixed pos-bottom w-full bg-base tac padding-top-big",staticStyle:{height:"50vh","border-radius":"120rpx 120rpx 0 0"}},[n("v-uni-view",{staticStyle:{width:"70%",margin:"0 auto"}},[n("v-uni-view",{staticClass:"dflex padding-tb",staticStyle:{"border-bottom":"1px solid #fff"}},[n("v-uni-view",{staticClass:"iconfont iconzhanghao"}),n("v-uni-input",{staticClass:"tal padding-left-sm",attrs:{type:"text",value:"",placeholder:"请输入手机号","placeholder-style":"color: #fff;"},model:{value:t.account,callback:function(i){t.account=i},expression:"account"}})],1),n("v-uni-view",{staticClass:"dflex-b padding-tb",staticStyle:{"border-bottom":"1px solid #fff"}},[n("v-uni-view",{staticClass:"dflex"},[n("v-uni-view",{staticClass:"iconfont iconmima"}),n("v-uni-view",{staticClass:"padding-left-sm"},[n("v-uni-input",{staticClass:"tal",attrs:{type:"text",value:"",placeholder:"请输入验证码","placeholder-style":"color: #fff;"},model:{value:t.verification,callback:function(i){t.verification=i},expression:"verification"}})],1)],1),t.show_again?n("v-uni-view",{staticClass:"border-radius dflex-c fs-xs",staticStyle:{width:"170rpx",height:"60rpx","background-color":"#fff",color:"#4d9ff4"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.acquire.apply(void 0,arguments)}}},[t._v("获取验证码")]):n("v-uni-view",{staticClass:"border-radius dflex-c",staticStyle:{width:"170rpx",height:"60rpx","background-color":"#fff",color:"#4d9ff4"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.acquire.apply(void 0,arguments)}}},[t._v(t._s(t.count)+"s")])],1),n("v-uni-view",{staticClass:"w-full margin-top-big"},[n("v-uni-view",{staticClass:"dflex-b border-radius-lg"},[n("v-uni-view",{staticClass:"tac padding-tb-sm flex1  fs",staticStyle:{"background-color":"#a6cff9"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.tologin.apply(void 0,arguments)}}},[t._v("登录")])],1)],1)],1)],1)],1)},o=[]}}]);