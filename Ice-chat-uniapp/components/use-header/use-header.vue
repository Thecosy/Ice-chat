<template>
	<view>
		<!-- 头部组件 -->
		<view class="use-header dflex dflex-b bg-drak w-full overflow-hidden" :class="fixed ? 'fixed' : ''" :style="{'padding-top': statusBarHeight}">
			<view class="use-hover dflex" style="font-weight: 550;" @click="toback">
				<view v-if="icon" class="iconfont padding-tb padding-lr-sm rotate-180 pos-r" :class="icon" style="top: -4rpx"></view>
				<text class="fs-lg pos-r" style="top: -4rpx; left: 22rpx;">{{ title }}</text>
			</view>
			
			<slot />
		</view>
		
		<!-- 头部组件占位符 -->
		<view v-if="fixed && placeholder" class="use-header-placeholder" :style="{'padding-top': statusBarHeight}"></view>
	</view>
</template>

<script>
export default {
	props: {
		icon: {
			type: [String, Boolean],
			default: "iconjiantou"
		},
		fixed: {
			type: [Number, Boolean],
			default: !0
		},
		placeholder: {
			type: [Number, Boolean],
			default: !0
		},
		back: {
			type: [Number, Boolean],
			default: !0
		},
		title: {
			type: String,
			default: "Ice·Chat"
		}
	},
	data() {
		return {
			statusBarHeight: '20px'
		};
	},
	created() {
		this.$api.get_env((res) => {
			this.statusBarHeight = res.statusBarHeight + 'px';
		});
	},
	methods: {
		toback() {
			this.$emit('back', {
				type: 'back'
			});

			if (this.back) {
				// 返回
				uni.navigateBack();
			}
		}
	}
};
</script>

<style lang="scss">
.use-header, .use-header-placeholder {
	height: 44px;
    box-sizing: content-box;
}

</style>
