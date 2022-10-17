<template>
	<view class="use-loadmore padding-tb-sm dflex-c">
		<view v-if="type !== 'nomore'" class="iconfont iconxiangqing animated rotate margin-right-sm ft-base"></view>
		<text class="ft-dark">{{ tip || defaults[type] }}</text>
	</view>
</template>

<script>
export default {
	props: {
		type: {
			type: String,
			default: "more"
		},
		tip: {
			type: String,
			default: ""
		},
		defaults: {
			type: Object,
			default () {				
				return {
					more: '上拉显示更多',
					loading: '用云 · 让开发更简单',
					nomore: '已经到底了'
				}
			}
		} 
	},
	data() {
		return {
			
		};
	},
	computed: {
		
	},
	methods: {
		
	}
};
</script>

<style lang="scss">
.use-loadmore {}
</style>
