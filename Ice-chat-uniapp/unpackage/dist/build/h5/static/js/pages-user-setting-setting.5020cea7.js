(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-user-setting-setting"],{"09f7":function(t,e,i){"use strict";var n=i("2579"),a=i.n(n);a.a},"1c1a":function(t,e,i){"use strict";var n=i("4ea4");Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var a=n(i("5530")),o=i("26cb"),s={computed:(0,a.default)({},(0,o.mapState)(["islogin","member"])),data:function(){return{actionSheetShow:!1,actionSheetList:[],actionSheetTips:{text:"退出登录 | 切换账号",color:"#9a9a9a",size:24},is_mp:!1,is_alipay:!1}},onLoad:function(){var t=this;this.$nextTick((function(){t.is_mp=t.$env.is_mp,t.is_alipay="alipay"==t.$env.platform}))},methods:(0,a.default)((0,a.default)({},(0,o.mapMutations)(["logout","putMember"])),{},{to_login:function(){uni.clearStorage()},openActionSheet:function(){var t=this;this.actionSheetShow=!0,this.$api.timerout((function(){t.actionSheetList=[{text:"退出登录",color:"#333"},{text:"切换账号",color:"#333"}],console.log("actionSheet",{show:t.actionSheetShow,list:t.actionSheetList})}),0)},actionSheetClose:function(){console.log(this.actionSheetShow)},actionSheetClick:function(t){var e=this;switch(t){case 0:this.$api.msg("退出成功"),this.logout(),this.$api.timerout((function(){e.$api.tohome()}),200);break;case 1:this.$api.tologin();break}}})};e.default=s},2579:function(t,e,i){var n=i("a614");n.__esModule&&(n=n.default),"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var a=i("4f06").default;a("4d5929fd",n,!0,{sourceMap:!1,shadowMode:!1})},"4a19":function(t,e,i){"use strict";i.r(e);var n=i("74d4"),a=i("b142");for(var o in a)"default"!==o&&function(t){i.d(e,t,(function(){return a[t]}))}(o);var s,c=i("f0c5"),l=Object(c["a"])(a["default"],n["b"],n["c"],!1,null,"54949295",null,!1,n["a"],s);e["default"]=l.exports},"74d4":function(t,e,i){"use strict";i.d(e,"b",(function(){return a})),i.d(e,"c",(function(){return o})),i.d(e,"a",(function(){return n}));var n={usePopup:i("ab4a").default},a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("use-popup",{attrs:{mode:"bottom",length:"auto","border-radius":t.borderRadius,popup:!1,maskCloseAble:t.maskCloseAble,safeAreaInsetBottom:t.safeAreaInsetBottom,"z-index":t.zIndex},on:{close:function(e){arguments[0]=e=t.$handleEvent(e),t.popupClose.apply(void 0,arguments)}},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}},[t.tips.text?i("v-uni-view",{staticClass:"fs-sm padding-tb tac border-bottom",style:[t.tipsStyle]},[t._v(t._s(t.tips.text))]):t._e(),t._l(t.datas,(function(e,n){return[i("v-uni-view",{key:n+"_0",staticClass:"dflex-c padding-tb line-height-1",class:[n<t.datas.length-1?"border-bottom":""],style:[t.itemStyle(n)],attrs:{"hover-class":"use-hover-class","hover-stay-time":150},on:{touchmove:function(e){e.stopPropagation(),e.preventDefault(),arguments[0]=e=t.$handleEvent(e)},click:function(e){arguments[0]=e=t.$handleEvent(e),t.itemClick(n)}}},[t._v(t._s(e.text))])]})),t.cancelBtn?i("v-uni-view",{staticClass:"gap"}):t._e(),t.cancelBtn?i("v-uni-view",{staticClass:"dflex-c padding-tb line-height-1",attrs:{"hover-class":"use-hover-class","hover-stay-time":150},on:{touchmove:function(e){e.stopPropagation(),e.preventDefault(),arguments[0]=e=t.$handleEvent(e)},click:function(e){arguments[0]=e=t.$handleEvent(e),t.closeClick.apply(void 0,arguments)}}},[t._v("取消")]):t._e()],2)},o=[]},"9fd9":function(t,e,i){"use strict";i("a9e3"),Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n={props:{maskCloseAble:{type:Boolean,default:!0},list:{type:Array,default:function(){return[]}},tips:{type:Object,default:function(){return{text:"",color:"",fontSize:"26"}}},cancelBtn:{type:Boolean,default:!0},safeAreaInsetBottom:{type:Boolean,default:!0},value:{type:Boolean,default:!1},borderRadius:{type:[String,Number],default:0},zIndex:{type:[String,Number],default:10030}},watch:{list:function(t,e){console.log("use-action-sheet",{nv:t,ov:e}),this.datas=t}},data:function(){return{datas:[]}},computed:{tipsStyle:function(){var t={};return this.tips.color&&(t.color=this.tips.color),this.tips.fontSize&&(t.fontSize=this.tips.fontSize+"rpx"),t},itemStyle:function(t){var e=this;return function(t){var i={};return e.list[t].color&&(i.color=e.list[t].color),e.list[t].fontSize&&(i.fontSize=e.list[t].fontSize+"rpx"),i}}},methods:{popupClose:function(){this.$emit("input",!1),this.$emit("close")},closeClick:function(){this.$emit("input",!1)},itemClick:function(t){this.$emit("click",t),this.$emit("input",!1)}}};e.default=n},a614:function(t,e,i){var n=i("24fb");e=n(!1),e.push([t.i,'@charset "UTF-8";\n/* 页面左右间距 */\n/* 文字尺寸 */\n/*文字颜色*/\n/* 边框颜色 */\n/* 图片加载中颜色 */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.iconfont[data-v-3b4e88f5]{font-size:14px;color:silver}',""]),t.exports=e},b142:function(t,e,i){"use strict";i.r(e);var n=i("9fd9"),a=i.n(n);for(var o in n)"default"!==o&&function(t){i.d(e,t,(function(){return n[t]}))}(o);e["default"]=a.a},c267:function(t,e,i){"use strict";i.d(e,"b",(function(){return a})),i.d(e,"c",(function(){return o})),i.d(e,"a",(function(){return n}));var n={useActionSheet:i("4a19").default},a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("v-uni-view",[i("v-uni-view",{staticClass:"padding-lr margin-top"},[i("v-uni-view",{staticClass:"border-radius padding-lr bg-drak dflex-b padding-tb",staticStyle:{"align-items":"center"}},[i("v-uni-view",{staticClass:" "},[t._v("关于我们")]),i("v-uni-view",{staticClass:"dflex-c"},[i("v-uni-view",{staticClass:"iconfont iconjiantou"})],1)],1)],1),i("v-uni-view",{staticClass:"padding-lr margin-top"},[i("v-uni-view",{staticClass:"border-radius padding-lr bg-drak dflex-b padding-tb",staticStyle:{"align-items":"center"}},[i("v-uni-view",{staticClass:" "},[t._v("版本号")]),i("v-uni-view",{staticClass:"dflex-c"},[i("v-uni-view",{staticClass:"ft-dark padding-right-sm"},[t._v("v1.0.0")]),i("v-uni-view",{staticClass:"iconfont iconjiantou"})],1)],1)],1),i("v-uni-view",{staticClass:"padding-lr margin-top"},[i("v-uni-view",{staticClass:"border-radius padding-lr bg-drak dflex-b padding-tb",staticStyle:{"align-items":"center"}},[i("v-uni-view",{staticClass:" "},[t._v("投诉与建议")]),i("v-uni-view",{staticClass:"dflex-c"},[i("v-uni-view",{staticClass:"iconfont iconjiantou"})],1)],1)],1),i("v-uni-view",{staticClass:" padding-lr padding-bottom w-full fixed pos-bottom"},[t.islogin?i("v-uni-view",{staticClass:"border-radius margin-top-sm padding-sm dflex-c bg-main log-out-btn",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.openActionSheet.apply(void 0,arguments)}}},[i("v-uni-text",{staticClass:"cell-tit"},[t._v("退出登录")])],1):i("v-uni-view",{staticClass:"padding-tb bg-main border-radius-lg tac padding-lr-sm bg-base",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.to_login.apply(void 0,arguments)}}},[i("v-uni-view",{staticClass:" "},[t._v("清除缓存")])],1)],1),i("use-action-sheet",{attrs:{list:t.actionSheetList,tips:t.actionSheetTips},on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.actionSheetClick.apply(void 0,arguments)},close:function(e){arguments[0]=e=t.$handleEvent(e),t.actionSheetClose.apply(void 0,arguments)}},model:{value:t.actionSheetShow,callback:function(e){t.actionSheetShow=e},expression:"actionSheetShow"}})],1)},o=[]},d811:function(t,e,i){"use strict";i.r(e);var n=i("c267"),a=i("ef83");for(var o in a)"default"!==o&&function(t){i.d(e,t,(function(){return a[t]}))}(o);i("09f7");var s,c=i("f0c5"),l=Object(c["a"])(a["default"],n["b"],n["c"],!1,null,"3b4e88f5",null,!1,n["a"],s);e["default"]=l.exports},ef83:function(t,e,i){"use strict";i.r(e);var n=i("1c1a"),a=i.n(n);for(var o in n)"default"!==o&&function(t){i.d(e,t,(function(){return n[t]}))}(o);e["default"]=a.a}}]);