<template>
	<view class="use-empty-container tac" :class="eStyle == 'round' ? 'padding-sm' : ''" :style="{ height: height }">
		<view class="use-empty h-full dflex-c dflex-flow-c" :class="eStyle == 'round' ? 'round border-radius bg-main' : ''">
			<image v-if="imgurl" :src="imgurl"></image>
			<view v-if="tip" class="title padding-sm">{{tip}}</view>
			<button class="no-border use-btn" @click="to">{{btnTip}}</button>
		</view>
	</view>
</template>

<script>
export default {
	props: {
		eStyle: {
			type: String,
			default: 'square'
		},
		eType: {
			type: String,
			default: 'other'
		},
		tip: {
			type: String,
			default: '暂无数据'
		},
		btnTip: {
			type: String,
			default: '去逛逛'
		},
		btnGoto: {
			type: String,
			default: '/pages/tabbar/home'
		},
		auto: {
			type: Boolean,
			default: !0
		},
		height: {
			type: String,
			default: '100vh'
		}
	},
	computed: {
		dtype: {
			get(){
				console.log('get dtype', arguments);
				let imgobj = this.imgs.find(x => x.type == this.eType);
				this.imgurl = imgobj ? imgobj.url : this.imgurl_dft;
				return this.eType
			},
			set(val){
				console.log('set dtype', arguments);
			}
		}
	},
	data() {
		return {
			imgurl_dft: '/static/images/empty/empty.jpg',
			imgurl: '',
			imgs: [
				{ type: 'cart', url: '/static/images/empty/cart.jpg' },
				{ type: 'search', url: '/static/images/empty/search.jpg' },
				{ type: 'other', url: '/static/images/empty/empty.jpg' }
			]
		};
	},
	methods: {
		to() {
			this.$emit('goto', {
				type: 'goto'
			});

			if (this.auto) {
				// 跳转指定页
				uni.switchTab({
					url: this.btnGoto
				})
			}
		}
	}
};
</script>

<style lang="scss">
.use-empty-container{
	.use-empty {
		.round {
			padding: 25% 50rpx;

			button{
				width: 220rpx;
			}	
		}
		image {
			width: 160rpx;
			height: 160rpx;
		}

		.title {
			color: #c0c0c0;
		}

		.use-btn {
			font-size: $font-base + 2upx;
			display: inline-block;
		}
	}
}
</style>
