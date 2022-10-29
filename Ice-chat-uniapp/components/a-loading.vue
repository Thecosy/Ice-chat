<template>
	<!-- 复制该文件到components,并在全局或者所需页面引入 -->
	<view class="mask" :style="{'background':mask?maskBackground:''}" :class="mask ? 'mask-show' : ''" @click="aClose" v-if="show" :tabMask="tabMask" :navMask="navMask">
		
		<!-- 加载动画开始 需要替换的内容：从loading?.vue中复制替换以下内容 -->
		<view class="tree">
		  <view class="tree__light" style="--appear: 0; --y: 0; --rotate: 1440; --radius: 12.5; --speed: 6.400434662074517; --delay: -5.729518378221366;"></view>
		  <view class="tree__light" style="--appear: 1; --y: 2; --rotate: 1411.2; --radius: 12.25; --speed: 8.289118880758405; --delay: -9.821339538073875;"></view>
		  <view class="tree__light" style="--appear: 2; --y: 4; --rotate: 1382.4; --radius: 12; --speed: 2.762214438568402; --delay: -9.82553991641267;"></view>
		  <view class="tree__light" style="--appear: 3; --y: 6; --rotate: 1353.6000000000001; --radius: 11.75; --speed: 5.2169599789222705; --delay: -1.146313414741591;"></view>
		  <view class="tree__light" style="--appear: 4; --y: 8; --rotate: 1324.8; --radius: 11.5; --speed: 8.665042244953177; --delay: -2.232614726172797;"></view>
		  <view class="tree__light" style="--appear: 5; --y: 10; --rotate: 1296; --radius: 11.25; --speed: 6.1841290939752; --delay: -3.2948672174559213;"></view>
		  <view class="tree__light" style="--appear: 6; --y: 12; --rotate: 1267.2; --radius: 11; --speed: 0.2386221851841408; --delay: -0.1565400802638761;"></view>
		  <view class="tree__light" style="--appear: 7; --y: 14; --rotate: 1238.4; --radius: 10.75; --speed: 3.4180669002414676; --delay: -1.108204498301666;"></view>
		  <view class="tree__light" style="--appear: 8; --y: 16; --rotate: 1209.6000000000001; --radius: 10.5; --speed: 4.495713975214219; --delay: -7.16630082687522;"></view>
		  <view class="tree__light" style="--appear: 9; --y: 18; --rotate: 1180.8; --radius: 10.25; --speed: 2.050188705878939; --delay: -8.897558901485858;"></view>
		  <view class="tree__light" style="--appear: 10; --y: 20; --rotate: 1152; --radius: 10; --speed: 0.879341071588815; --delay: -1.372285887568998;"></view>
		  <view class="tree__light" style="--appear: 11; --y: 22; --rotate: 1123.2; --radius: 9.75; --speed: 6.529368844448258; --delay: -5.810729343334522;"></view>
		  <view class="tree__light" style="--appear: 12; --y: 24; --rotate: 1094.4; --radius: 9.5; --speed: 5.980504355359351; --delay: -8.0343744180381;"></view>
		  <view class="tree__light" style="--appear: 13; --y: 26; --rotate: 1065.6000000000001; --radius: 9.25; --speed: 3.730059917047488; --delay: -6.6048171919530745;"></view>
		  <view class="tree__light" style="--appear: 14; --y: 28; --rotate: 1036.8; --radius: 9; --speed: 8.447176433821193; --delay: -2.930795225206919;"></view>
		  <view class="tree__light" style="--appear: 15; --y: 30; --rotate: 1008; --radius: 8.75; --speed: 2.8356860377165605; --delay: -4.288005150545953;"></view>
		  <view class="tree__light" style="--appear: 16; --y: 32; --rotate: 979.2; --radius: 8.5; --speed: 4.901932888595965; --delay: -7.760337395934407;"></view>
		  <view class="tree__light" style="--appear: 17; --y: 34; --rotate: 950.4; --radius: 8.25; --speed: 2.4410718943359044; --delay: -1.6426251216499121;"></view>
		  <view class="tree__light" style="--appear: 18; --y: 36; --rotate: 921.6; --radius: 8; --speed: 2.0493973792725373; --delay: -9.01034351819967;"></view>
		  <view class="tree__light" style="--appear: 19; --y: 38; --rotate: 892.8000000000001; --radius: 7.75; --speed: 4.090147886483462; --delay: -0.789389136081029;"></view>
		  <view class="tree__light" style="--appear: 20; --y: 40; --rotate: 864; --radius: 7.5; --speed: 1.3590459446093006; --delay: -9.876738535965146;"></view>
		  <view class="tree__light" style="--appear: 21; --y: 42; --rotate: 835.2; --radius: 7.25; --speed: 9.50349709646419; --delay: -0.2852392609827459;"></view>
		  <view class="tree__light" style="--appear: 22; --y: 44; --rotate: 806.4; --radius: 7; --speed: 3.0721768487078704; --delay: -4.567820117936774;"></view>
		  <view class="tree__light" style="--appear: 23; --y: 46; --rotate: 777.6; --radius: 6.75; --speed: 0.49456860352296017; --delay: -0.1164418214056262;"></view>
		  <view class="tree__light" style="--appear: 24; --y: 48; --rotate: 748.8000000000001; --radius: 6.5; --speed: 4.655998318405867; --delay: -8.80328724436508;"></view>
		  <view class="tree__light" style="--appear: 25; --y: 50; --rotate: 720; --radius: 6.25; --speed: 6.13195185823562; --delay: -7.575161141790594;"></view>
		  <view class="tree__light" style="--appear: 26; --y: 52; --rotate: 691.2; --radius: 6; --speed: 4.358165499421438; --delay: -9.247144771643343;"></view>
		  <view class="tree__light" style="--appear: 27; --y: 54; --rotate: 662.4; --radius: 5.75; --speed: 0.05097926779642714; --delay: -8.691745044020614;"></view>
		  <view class="tree__light" style="--appear: 28; --y: 56; --rotate: 633.6; --radius: 5.5; --speed: 8.742641210620313; --delay: -6.5295280902741855;"></view>
		  <view class="tree__light" style="--appear: 29; --y: 58; --rotate: 604.8000000000001; --radius: 5.25; --speed: 5.80185251364155; --delay: -7.609251669692513;"></view>
		  <view class="tree__light" style="--appear: 30; --y: 60; --rotate: 576; --radius: 5; --speed: 7.467315091727784; --delay: -8.634544168186384;"></view>
		  <view class="tree__light" style="--appear: 31; --y: 62; --rotate: 547.2; --radius: 4.75; --speed: 4.266345571182885; --delay: -5.264029758958855;"></view>
		  <view class="tree__light" style="--appear: 32; --y: 64; --rotate: 518.4; --radius: 4.5; --speed: 9.045502005126004; --delay: -2.690996107144532;"></view>
		  <view class="tree__light" style="--appear: 33; --y: 66; --rotate: 489.6; --radius: 4.25; --speed: 8.157526793190122; --delay: -7.994804794945352;"></view>
		  <view class="tree__light" style="--appear: 34; --y: 68; --rotate: 460.8; --radius: 4; --speed: 9.713885657603182; --delay: -3.1004172346881154;"></view>
		  <view class="tree__light" style="--appear: 35; --y: 70; --rotate: 432; --radius: 3.75; --speed: 2.4675983602710794; --delay: -6.147402437252795;"></view>
		  <view class="tree__light" style="--appear: 36; --y: 72; --rotate: 403.2; --radius: 3.5; --speed: 6.334757806600053; --delay: -4.68726311130985;"></view>
		  <view class="tree__light" style="--appear: 37; --y: 74; --rotate: 374.40000000000003; --radius: 3.25; --speed: 5.810077494998955; --delay: -0.5421184684789382;"></view>
		  <view class="tree__light" style="--appear: 38; --y: 76; --rotate: 345.6; --radius: 3; --speed: 3.1348261661348698; --delay: -1.8762379030616438;"></view>
		  <view class="tree__light" style="--appear: 39; --y: 78; --rotate: 316.8; --radius: 2.75; --speed: 1.7539981598254406; --delay: -0.39031038560793707;"></view>
		  <view class="tree__light" style="--appear: 40; --y: 80; --rotate: 288; --radius: 2.5; --speed: 2.5539355188740087; --delay: -4.450657635233652;"></view>
		  <view class="tree__light" style="--appear: 41; --y: 82; --rotate: 259.2; --radius: 2.25; --speed: 2.3603559140315844; --delay: -9.410370278145297;"></view>
		  <view class="tree__light" style="--appear: 42; --y: 84; --rotate: 230.4; --radius: 2; --speed: 3.3112783371999055; --delay: -0.7523735956343902;"></view>
		  <view class="tree__light" style="--appear: 43; --y: 86; --rotate: 201.6; --radius: 1.75; --speed: 1.7060416013304724; --delay: -9.109653019612438;"></view>
		  <view class="tree__light" style="--appear: 44; --y: 88; --rotate: 172.8; --radius: 1.5; --speed: 6.344643090661945; --delay: -6.583050358454807;"></view>
		  <view class="tree__light" style="--appear: 45; --y: 90; --rotate: 144; --radius: 1.25; --speed: 1.7389609219137059; --delay: -7.326224146231651;"></view>
		  <view class="tree__light" style="--appear: 46; --y: 92; --rotate: 115.2; --radius: 1; --speed: 8.606030099788386; --delay: -1.8678573148711752;"></view>
		  <view class="tree__light" style="--appear: 47; --y: 94; --rotate: 86.4; --radius: 0.75; --speed: 9.49674248980797; --delay: -3.592334423342276;"></view>
		  <view class="tree__light" style="--appear: 48; --y: 96; --rotate: 57.6; --radius: 0.5; --speed: 0.6238837869676761; --delay: -9.031351447299665;"></view>
		  <view class="tree__light" style="--appear: 49; --y: 98; --rotate: 28.8; --radius: 0.25; --speed: 0.25891891876878903; --delay: -3.4291538146903444;"></view>
		  <!-- 切记这块是个星星图片 -->
		  <image src="@/static/star.png" class="tree__star" style="--delay: 50"></image>
		</view>
		<!-- 加载动画结束 -->
		
		<!-- 文字提示 -->
		<view class="title" :style="{
			'color':titleColor,
			'fontSize':titleSize,
			'marginTop':titleTop
		}">
			{{title}}
		</view>
	</view>
</template>

<script scoped="true">
	export default {
		name: "a-loading",
		props: {
			//遮罩层 true有 false无
			mask: {
				type: Boolean,
				default: true
			},
			// 遮罩颜色
			maskBackground:{
				type: String,
				default: "rgba(7, 17, 27, .9)"
			},
			//点击遮罩是否可关闭  true可以 false不行
			isClick: {
				type: Boolean,
				default: false
			},
			// 提示文字内容
			title: {
				type: String,
				default: ""
			},
			//提示文字颜色
			titleColor: {
				type: String,
				default: "#fff"
			},
			//提示文字大小
			titleSize: {
				type: String,
				default: "28rpx"
			},
			//提示文字距离动画位置
			titleTop:{
				type: String,
				default: ""
			},
			// 是否显示原生底部遮罩
			tabMask:{
				type: Boolean,
				default: true
			},
			// 是否显示原生导航遮罩
			navMask:{
				type: Boolean,
				default: true
			}
		},
		data() {
			return {
				// 加载动画显示隐藏
				show: false,
				// 原生底部遮罩
				tabViewObj:null,
				// 原生导航栏遮罩
				navbarViewObj:null,
			};
		},
		methods: {
			// 点击遮罩关闭加载动画
			aClose() {
				if (this.isClick) {
					this.close(); 
				}
			},
			// 打开加载动画
			open() {
				this.show = true
				if(this.tabMask && this.mask){
					this.bottonMask();
				}
				if(this.navMask && this.mask){
					this.navbarMask()
				}
			},
			// 关闭加载动画
			close() {
				this.show = false 
				if(this.tabMask && this.mask){
					this.tabViewObj.hide() //隐藏原生遮罩
				}
				if(this.navMask && this.mask){
					this.navbarViewObj.hide() //隐藏原生遮罩
				}
			},
			// 原生底部遮罩
			bottonMask(){
				this.tabViewObj = new plus.nativeObj.View('viewObj', {
					bottom: '0px',
					left: '0px',
					height: '50px',
					width: '100%',
					backgroundColor: this.maskBackground
				});
				this.tabViewObj.show() //显示原生遮罩
			},
			// 原生顶部导航遮罩
			navbarMask(){
				this.navbarViewObj = new plus.nativeObj.View('viewObj', {
					top: '0px',
					left: '0px',
					height: uni.getSystemInfoSync().statusBarHeight+45+"px",
					width: '100%',
					backgroundColor: this.maskBackground
				});
				this.navbarViewObj.show() //显示原生遮罩
			}
		}
	};
</script>

<style>
	/* 遮罩样式 */
	.mask {
		position: fixed;
		z-index: 99999;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		height: 100vh;
		width: 100vw;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}
	
	/* 动画样式 开始 （以下是需要替换内容）*/
	/* 动画 3 样式 */
	
	.tree {
	  position: relative;
	  height: 50vmin;
	  width: 25vmin;
	  transform-style: preserve-3d;
	  -webkit-animation: spin 2s infinite linear;
	          animation: spin 2s infinite linear;
	}
	.tree__light {
	  transform-style: preserve-3d;
	  position: absolute;
	  height: 1vmin;
	  width: 1vmin;
	  border-radius: 50%;
	  -webkit-animation: flash calc(var(--speed) * 1s) calc(var(--delay) * 1s) infinite steps(4), appear 0.5s calc(var(--appear) * 0.05s) both;
	          animation: flash calc(var(--speed) * 1s) calc(var(--delay) * 1s) infinite steps(4), appear 0.5s calc(var(--appear) * 0.05s) both;
	  left: 50%;
	  transform: translate(-50%, 50%) rotateY(calc(var(--rotate, 0) * 1deg)) translate3d(0, 0, calc(var(--radius, 0) * 1vmin));
	  bottom: calc(var(--y, 0) * 1%);
	}
	.tree__star {
	  stroke-width: 5vmin;
	  stroke: #f5e0a3;
	  filter: drop-shadow(0 0 2vmin #fcf1cf);
	  height: 5vmin;
	  width: 5vmin;
	  overflow: visible !important;
	  bottom: 100%;
	  left: 50%;
	  transform: translate(-50%, 0);
	  position: absolute;
	  stroke-dasharray: 1000 1000;
	  fill: none;
	  -webkit-animation: stroke 1s calc((var(--delay) * 0.95) * 0.05s) both;
	          animation: stroke 1s calc((var(--delay) * 0.95) * 0.05s) both;
	}
	@-webkit-keyframes stroke {
	  from {
	    stroke-dashoffset: -1000;
	  }
	}
	@keyframes stroke {
	  from {
	    stroke-dashoffset: -1000;
	  }
	}
	@-webkit-keyframes spin {
	  to {
	    transform: rotateY(360deg);
	  }
	}
	@keyframes spin {
	  to {
	    transform: rotateY(360deg);
	  }
	}
	@-webkit-keyframes appear {
	  from {
	    opacity: 0;
	  }
	}
	@keyframes appear {
	  from {
	    opacity: 0;
	  }
	}
	@-webkit-keyframes flash {
	  0%, 100% {
	    background: #4f60f6;
	  }
	  20% {
	    background: #f64f4f;
	  }
	  40% {
	    background: #4fecf6;
	  }
	  60% {
	    background: #f6db4f;
	  }
	  80% {
	    background: #f64fe5;
	  }
	}
	@keyframes flash {
	  0%, 100% {
	    background: #4f60f6;
	  }
	  20% {
	    background: #f64f4f;
	  }
	  40% {
	    background: #4fecf6;
	  }
	  60% {
	    background: #f6db4f;
	  }
	  80% {
	    background: #f64fe5;
	  }
	}
	/* 动画样式结束 */
	
</style>
