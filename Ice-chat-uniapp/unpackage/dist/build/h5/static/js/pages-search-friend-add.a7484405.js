(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-search-friend-add"],{"18de":function(n,e,t){var a=t("24fb");e=a(!1),e.push([n.i,'@charset "UTF-8";\n/* 页面左右间距 */\n/* 文字尺寸 */\n/*文字颜色*/\n/* 边框颜色 */\n/* 图片加载中颜色 */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.use-search[data-v-3ecef597]{height:%?120?%;line-height:%?120?%}.use-search .iconfont[data-v-3ecef597]{font-size:%?44?%;color:#fff}.bg-color[data-v-3ecef597]{background-color:#f5f5f5}.oper-area uni-view[data-v-3ecef597]{border-radius:%?100?%;margin-right:%?20?%;text-align:center;padding-top:%?16?%;padding-bottom:%?16?%;background:#f5f5f5;min-width:%?180?%;color:#333}.oper-area uni-view[data-v-3ecef597]:last-child{margin-right:0}',""]),n.exports=e},"273f":function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a={data:function(){return{myUserInfo:"",type:"",title:"添加好友",placeholder:"请输入关键字",data:[{img:"https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg",name:"Usecloud",id:0}]}},onPageScroll:function(n){},onLoad:function(n){var e=this;uni.getStorage({key:"userLogin",success:function(n){console.log("获取成功",n),e.myUserInfo=n.data}}),console.log(this.myUserInfo)},onShow:function(n){},methods:{onFocus:function(n){console.log("onFocus",n)},onKeyboardChange:function(n){console.log("onKeyboardChange",n)},tosearch:function(){uni.navigateTo({url:"/pages/search/search"})},addhaoy:function(){uni.navigateTo({url:"/pages/search/apply"})}}};e.default=a},"3c68":function(n,e,t){"use strict";t.r(e);var a=t("b20c"),o=t("f82c");for(var i in o)"default"!==i&&function(n){t.d(e,n,(function(){return o[n]}))}(i);t("8e29");var r,s=t("f0c5"),c=Object(s["a"])(o["default"],a["b"],a["c"],!1,null,"3ecef597",null,!1,a["a"],r);e["default"]=c.exports},"8d6d":function(n,e,t){var a=t("18de");a.__esModule&&(a=a.default),"string"===typeof a&&(a=[[n.i,a,""]]),a.locals&&(n.exports=a.locals);var o=t("4f06").default;o("5e3f73fc",a,!0,{sourceMap:!1,shadowMode:!1})},"8e29":function(n,e,t){"use strict";var a=t("8d6d"),o=t.n(a);o.a},b20c:function(n,e,t){"use strict";var a;t.d(e,"b",(function(){return o})),t.d(e,"c",(function(){return i})),t.d(e,"a",(function(){return a}));var o=function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("v-uni-view",[t("v-uni-view",{staticClass:"use-search dflex padding-lr w-full bg-drak padding-tb-sm pos-f"},[t("v-uni-input",{staticClass:"bg-main padding-lr-lg wh-full border-radius-lg",attrs:{type:"text",maxlength:"20",placeholder:n.placeholder},model:{value:n.keyword,callback:function(e){n.keyword=e},expression:"keyword"}}),t("v-uni-view",{staticClass:"use-hover dflex-c bg-base pos-a padding-lr-lg border-radius-lg",staticStyle:{top:"20rpx",bottom:"20rpx",right:"30rpx","z-index":"9"},on:{click:function(e){arguments[0]=e=n.$handleEvent(e),n.tosearch.apply(void 0,arguments)}}},[t("v-uni-view",{staticClass:"iconfont iconsousuo"})],1)],1),t("v-uni-view",{staticClass:"padding-lr-sm margin-top-sm margin-bottom-sm pos-f w-full",staticStyle:{top:"152rpx"}},[t("v-uni-view",{},[n._v("好友")]),n._l(n.data,(function(e,a){return t("v-uni-view",{key:a,staticClass:"dflex margin-top-sm bg-color w-full padding-lr-sm padding-tb-sm border-radius",on:{click:function(e){arguments[0]=e=n.$handleEvent(e),n.linkman.apply(void 0,arguments)}}},[t("v-uni-view",{},[t("v-uni-image",{staticClass:"border-radius-lg",staticStyle:{width:"88rpx",height:"88rpx"},attrs:{src:e.img,mode:""}})],1),t("v-uni-view",{staticClass:"margin-left-sm w-full dflex-b"},[t("v-uni-view",{},[n._v(n._s(e.name))]),t("v-uni-view",{staticStyle:{color:"#fff","background-color":"#4d9ff4",padding:"20rpx 50rpx","border-radius":"20rpx"},on:{click:function(e){e.stopPropagation(),arguments[0]=e=n.$handleEvent(e),n.addhaoy.apply(void 0,arguments)}}},[n._v("添加")])],1)],1)}))],2)],1)},i=[]},f82c:function(n,e,t){"use strict";t.r(e);var a=t("273f"),o=t.n(a);for(var i in a)"default"!==i&&function(n){t.d(e,n,(function(){return a[n]}))}(i);e["default"]=o.a}}]);