<template>
	<view class="use-totop fixed-top animated" :class="visible ? 'fade-in' : 'dn'" :style="{ bottom: bottom + 'rpx', right: right + 'rpx' }" @tap.stop="totop">
		<text class="iconfont iconzhiding"></text>
	</view>
</template>

<script>
	export default {
		props: {
			top: {
				type: Number,
				default: 100
			},
			
			right: {
				type: String,
				default: '30'
			},
			bottom: {
				type: String,
				default: '30'
			},
			
			duration:{
				type: Number,
				default: 120
			},
			scrollTop: {
				type: Number,
				default: 0
			}
		},
		watch: {
			scrollTop(nv, ov) {
				this.s_top = nv;
				this.change();
			}
		},
		data() {
			return {
				s_top: 0,
				visible: false
			};
		},
		methods: {
			totop: function() {
				uni.pageScrollTo({
					scrollTop: 0,
					duration: this.duration
				})
				
				this.$emit('to', {
					type: 'to',
					scrollTop: this.s_top 
				});
			},
			change() {
				if(this.scrollTop > this.top) {
					if(!this.visible) this.visible = true;
				} else {
					if(this.visible) this.visible = false;
				}
				// console.log('change', this.visible);
			}
		}
	}
</script>

<style lang="scss">
	.use-totop {}
</style>