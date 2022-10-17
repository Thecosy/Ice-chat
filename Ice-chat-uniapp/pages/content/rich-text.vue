<template>
	<view>
		<rich-text class="pro-detail" :nodes="content"></rich-text>
	</view>
</template>

<script>
	// #ifdef MP-ALIPAY
	import aliParse from 'mini-html-parser2';
	// #endif
	
	export default {
		data() {
			return {
				content: ''
			};
		},
		onShow() {
			let content = uni.getStorageSync('__rich_text');
			
			// #ifdef MP-ALIPAY
			// console.log('MP-ALIPAY', content);
			aliParse(content.replace(/"><*/gi, '"/><'), (err, nodes) => {
				// console.log(arguments);
				if (!err) {
					this.content = nodes;
				}
			});
			// #endif
			
			// #ifndef MP-ALIPAY
			this.content = content;
			// #endif
		},
	}
</script>

<style lang="scss">
	.pro-detail {
		width: 100%;
		overflow: hidden;
		-webkit-touch-callout: none;

		img {
			width: 100%;
			max-width: 100%;
			overflow: hidden;
		}
	}
</style>
