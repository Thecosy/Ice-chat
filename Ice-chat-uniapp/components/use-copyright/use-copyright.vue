<template>
	<view>
		<!-- #ifndef MP-TOUTIAO -->
		<!-- 用云版权 -->
		<view class="use-copyright dflex-c padding-lr w-full" @click="to">
			<view class="iconfont iconxiangqing animated rotate margin-right-sm ft-base"></view>
			<view class="ft-dark">用云</view><text class="margin-lr-xs">·</text><text>让开发更简单</text>
		</view>
		<!-- #endif -->
	</view>
</template>

<script>
export default {
	props: {
		
	},
	data() {
		return {};
	},
	methods: {
		to() {
			this.$emit('to', {
				type: 'to'
			});
		}
	}
};
</script>

<style lang="scss">
.use-copyright {
	height: 180rpx;
}
.use-copyright {
	text {
		color: #c0c0c0;
	}
}
</style>
