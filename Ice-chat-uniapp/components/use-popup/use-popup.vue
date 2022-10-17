<template>
	<view v-if="visibleSync" class="pos-f pos-full overflow-hidden use-popup" :class="{ 'use-popup-visible': showPopup }" :style="[customStyle]">
		<use-mask :show="showPopup && mask" :maskClickAble="maskCloseAble" @click="maskClick"></use-mask>
		<view class="pos-a use-popup-content" 
			:class="[
				bgclass,
				safeAreaInsetBottom ? 'safe-area-inset-bottom' : '',
				'use-popup-' + mode,
				showPopup ? 'use-popup-content-visible' : '',
				zoom && mode == 'center' ? 'use-animation-zoom' : ''
			]" :style="[style]" @tap.stop.prevent @touchmove.stop.prevent @tap="modeCenterClose(mode)">
			<view v-if="mode == 'center'" class="bg-main pos-r use-mode-center-box" :style="[centerStyle]" @tap.stop.prevent @touchmove.stop.prevent>
				<slot />
			</view>
			<block v-else>
				<slot />
			</block>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			/**
			 * 显示状态
			 */
			show: {
				type: Boolean,
				default: false
			},
			/**
			 * 弹出方向，left|right|top|bottom|center
			 */
			mode: {
				type: String,
				default: 'left'
			},
			/**
			 * 是否显示遮罩
			 */
			mask: {
				type: Boolean,
				default: true
			},
			/**
			 * 背景 class 样式
			 * */
			bgclass: {
				type: String,
				default: 'bg-main'
			},
			// 抽屉的宽度(mode=left|right)，或者高度(mode=top|bottom)，单位rpx，或者"auto"
			// 或者百分比"50%"，表示由内容撑开高度或者宽度
			length: {
				type: [Number, String],
				default: 'auto'
			},
			// 是否开启缩放动画，只在mode=center时有效
			zoom: {
				type: Boolean,
				default: true
			},
			// 是否开启底部安全区适配，开启的话，会在iPhoneX机型底部添加一定的内边距
			safeAreaInsetBottom: {
				type: Boolean,
				default: false
			},
			// 是否可以通过点击遮罩进行关闭
			maskCloseAble: {
				type: Boolean,
				default: true
			},
			// 用户自定义样式
			customStyle: {
				type: Object,
				default () {
					return {};
				}
			},
			value: {
				type: Boolean,
				default: false
			},
			// 此为内部参数，不在文档对外使用，为了解决Picker和keyboard等融合了弹窗的组件
			// 对v-model双向绑定多层调用造成报错不能修改props值的问题
			popup: {
				type: Boolean,
				default: true
			},
			// 显示显示弹窗的圆角，单位rpx
			borderRadius: {
				type: [Number, String],
				default: 0
			},
			zIndex: {
				type: [Number, String],
				default: '10020'
			}
		},
		data() {
			return {
				visibleSync: false,
				showPopup: false,
			};
		},
		watch: {
			value(val) {
				if (val) {
					this.open();
				} else {
					if (this.showPopup) this.close();
				}
			}
		},
		computed: {
			// 根据mode的位置，设定其弹窗的宽度(mode = left|right)，或者高度(mode = top|bottom)
			style() {
				let style = {};
				let translate = '100%';
				// 判断是否是否百分比或者auto值，是的话，直接使用该值，否则默认为rpx单位的数值
				let length = (/%$/.test(this.length) || this.length == 'auto') ? this.length : uni.upx2px(this.length) + 'px';
				// 如果是左边或者上边弹出时，需要给translate设置为负值，用于隐藏
				if (this.mode == 'left' || this.mode == 'top') { 
					translate = length == 'auto' ? '-100%' : '-' + length; 
				}
				if (this.mode == 'left' || this.mode == 'right') {
					style = {
						width: length,
						height: '100%',
						transform: `translate3D(${translate},0px,0px)`
					};
				} else if (this.mode == 'top' || this.mode == 'bottom') {
					style = {
						width: '100%',
						height: length,
						transform: `translate3D(0px,${translate},0px)`
					};
				}
				style.zIndex = this.zIndex;
				
				// 如果用户设置了borderRadius值，添加弹窗的圆角
				if (this.borderRadius) {
					switch (this.mode) {
						case 'top':
							style.borderRadius = `0 0 ${this.borderRadius}rpx ${this.borderRadius}rpx`;
							break;
						case 'right':
							style.borderRadius = `${this.borderRadius}rpx 0 0 ${this.borderRadius}rpx`;
							break;
						case 'bottom':
							style.borderRadius = `${this.borderRadius}rpx ${this.borderRadius}rpx 0 0`;
							break;
						case 'left':
							style.borderRadius = `0 ${this.borderRadius}rpx ${this.borderRadius}rpx 0`;
							break;
						default:
							break;
					}
					// 不加可能圆角无效
					style.overflow = 'hidden';
				}
				return style;
			},
			// 中部弹窗的特有样式
			centerStyle() {
				let style = {};
				let length = (/%$/.test(this.length) || this.length == 'auto') ? this.length : uni.upx2px(this.length) + 'px';
				style.width = length;
				style.zIndex = this.zIndex;
				if (this.borderRadius) {
					style.borderRadius = `${this.borderRadius}rpx`;
					// 不加可能圆角无效
					style.overflow = 'hidden';
				}
				return style;
			}
		},
		created() {
			// 先让弹窗组件渲染，再改变遮罩和抽屉元素的样式，让其动画其起作用(必须要有延时，才会有效果)
			this.visibleSync = this.value;
			this.$api.timerout(() => {
				this.showPopup = this.value;
			}, 30);
		},
		methods: {
			open() {
				this.change('visibleSync', 'showPopup', true);
			},
			close() {
				this.change('showPopup', 'visibleSync', false);
			},
			// 遮罩被点击
			maskClick() {
				this.close();
			},
			// 中部弹出时，需要.use-popup-content将居中内容，此元素会铺满屏幕，点击需要关闭弹窗
			// 让其只在mode=center时起作用
			modeCenterClose(mode) {
				if (mode != 'center' || !this.maskCloseAble) return;
				this.close();
			},
			// 此处的原理是，关闭时先通过动画隐藏弹窗和遮罩，再移除整个组件
			// 打开时，先渲染组件，延时一定时间再让遮罩和弹窗的动画起作用
			change(param1, param2, state) {
				// 如果this.popup为false，以为着为picker，actionsheet等组件调用了popup组件
				if (this.popup) this.$emit('input', state);
				this[param1] = state;
				
				this.$api.timerout(() => {
					this[param2] = state;
					this.$emit(state ? 'open' : 'close');
				}, state ? 30 : 100);
			}
		}
	};
</script>

<style lang="scss">
	.use-popup {
		z-index: 10010;
	}

	.use-popup-content {
		z-index: 10020;
		transition: all 0.25s ease-in-out;
	}

	.use-popup-top {
		top: 0;
		right: 0;
		left: 0;
	}
	.use-popup-right {
		top: 0;
		right: 0;
		bottom: 0;
	}
	.use-popup-bottom {
		right: 0;
		bottom: 0;
		left: 0;
	}
	.use-popup-left {
		top: 0;
		bottom: 0;
		left: 0;
	}	
	.use-popup-center {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		opacity: 0;
		z-index: 99999;
	}

	.use-mode-center-box {
		min-width: 100rpx;
		min-height: 100rpx;
	}
	.use-popup-content-visible.use-popup-center {
		transform: scale(1);
		opacity: 1;
	}
	.use-animation-zoom {
		transform: scale(1.15);
	}
	.use-popup-content-visible {
		transform: translate3D(0px, 0px, 0px) !important;
	}
</style>
