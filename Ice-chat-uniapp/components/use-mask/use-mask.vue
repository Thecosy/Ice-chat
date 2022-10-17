<template>
	<view class="use-mask pos-f pos-full overflow-hidden" :class="[show ? 'use-mask-show' : '']" :style="[maskStyle]" @touchmove.stop.prevent @tap="click">
		<slot />
	</view>
</template>

<script>
	export default {
		props: {
			// 是否显示遮罩
			show: {
				type: Boolean,
				default: false
			},
			// 层级z-index
			zIndex: {
				type: [Number, String],
				default: '10000'
			},
			// 遮罩的动画样式， 是否使用使用zoom进行scale进行缩放
			zoom: {
				type: Boolean,
				default: true
			},
			// 遮罩的过渡时间，单位为ms
			duration: {
				type: [Number, String],
				default: 300
			},
			// 是否可以通过点击遮罩进行关闭
			maskClickAble: {
				type: Boolean,
				default: true
			}
		},
		computed: {
			maskStyle() {
				let style = {
					transition: `all ${this.duration / 1000}s ease-in-out`,
					zIndex: this.zIndex,
				};
				if (this.show) {
					style.zIndex = this.zIndex;
				} else {
					style.zIndex = -1;
				}
				// 缩放
				if (this.zoom) {
					style.transform = 'scale(1.2, 1.2)';
				}
				
				return style;
			}
		},
		methods: {
			click() {
				if (this.maskClickAble) {
					this.$emit('click');
				}
			}
		}
	}
</script>

<style lang="scss">
	.use-mask {
		opacity: 0;
		background-color: rgba(0, 0, 0, 0.6);
	}

	.use-mask-show {
		opacity: 1;
		visibility: visible;
		transform: scale(1);
	}
</style>
