<template>
	<use-popup mode="bottom" v-model="value" 
		length="auto" 
		:border-radius="borderRadius" 
		:popup="false" 
		:maskCloseAble="maskCloseAble"
		:safeAreaInsetBottom="safeAreaInsetBottom" 
		:z-index="zIndex"
		@close="popupClose">
		<view v-if="tips.text" class="fs-sm padding-tb tac border-bottom" :style="[tipsStyle]">
			{{tips.text}}
		</view>
		<block v-for="(item, index) in datas" :key="index">
			<view class="dflex-c padding-tb line-height-1" :class="[index < datas.length - 1 ? 'border-bottom' : '']" :style="[itemStyle(index)]" hover-class="use-hover-class" :hover-stay-time="150" @touchmove.stop.prevent @tap="itemClick(index)">
				{{item.text}}
			</view>
		</block>
		<view v-if="cancelBtn" class="gap"></view>
		<view v-if="cancelBtn" class="dflex-c padding-tb line-height-1" hover-class="use-hover-class" :hover-stay-time="150" @touchmove.stop.prevent @tap="closeClick">取消</view>
	</use-popup>
</template>

<script>
	export default {
		props: {
			// 点击遮罩是否可以关闭actionsheet
			maskCloseAble: {
				type: Boolean,
				default: true
			},
			// 按钮的文字数组，可以自定义颜色和字体大小，字体单位为rpx
			list: {
				type: Array,
				default () {
					// 如下
					// return [{
					// 	text: '确定',
					// 	color: '',
					// 	fontSize: ''
					// }]
					return [];
				}
			},
			// 顶部的提示文字
			tips: {
				type: Object,
				default () {
					return {
						text: '',
						color: '',
						fontSize: '26'
					}
				}
			},
			// 底部的取消按钮
			cancelBtn: {
				type: Boolean,
				default: true
			},
			// 是否开启底部安全区适配，开启的话，会在iPhoneX机型底部添加一定的内边距
			safeAreaInsetBottom: {
				type: Boolean,
				default: true
			},
			// 通过双向绑定控制组件的弹出与收起
			value: {
				type: Boolean,
				default: false
			},
			// 弹出的顶部圆角值
			borderRadius: {
				type: [String, Number],
				default: 0
			},
			// 弹出的z-index值
			zIndex: {
				type: [String, Number],
				default: 10030
			}
		},
		watch: {
			list(nv, ov) {
				console.log('use-action-sheet', {nv:nv, ov:ov});
				this.datas = nv;
			}
		},
		data() {
			return {
				datas: []
			}
		},
		computed: {
			// 顶部提示的样式
			tipsStyle() {
				let style = {};
				if (this.tips.color) style.color = this.tips.color;
				if (this.tips.fontSize) style.fontSize = this.tips.fontSize + 'rpx';
				return style;
			},
			// 操作项目的样式
			itemStyle(index) {
				return (index) => {
					let style = {};
					if (this.list[index].color) style.color = this.list[index].color;
					if (this.list[index].fontSize) style.fontSize = this.list[index].fontSize + 'rpx';
					return style;
				}
			}
		},
		methods: {
			// 弹窗关闭回执事件
			popupClose() {
				this.$emit('input', false);
				this.$emit('close');
			},
			// 手动点击取消按钮
			closeClick() {
				this.$emit('input', false);
			},
			// 操作菜单 item 点击事件
			itemClick(index) {
				this.$emit('click', index);
				this.$emit('input', false);
			}
		}
	}
</script>

<style lang="scss"></style>
