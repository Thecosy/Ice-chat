<template>
	<view class="use-rate dflex">
		<view class="pos-r margin-left-sm" v-for="(star,index) in stars" :key="index" 
			:style="{ marginLeft: margin+'px' }" @click="onClick(index)">
			<view class="iconfont iconshoucang-01 ft-dark"></view>
			<view class="pos-a active" :style="{ width: star.activeWitch }">
				<view class="iconfont iconshoucang- ft-base"></view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			value: { //当前评分
				type: [Number, String],
				default: 0
			},
			margin: { //评分间距
				type: [Number, String],
				default: 0
			},
			max: { //最大评分
				type: [Number, String],
				default: 5
			},
			disabled: { //是否可点击
				type: [Boolean, String],
				default: false
			}
		},
		data() {
			// console.log('data')
			return {
				maxSync: this.max,
				valueSync: this.value
			}
		},
		computed: {
			stars() {
				const max = Number(this.maxSync) ? Number(this.maxSync) : 5
				const value = Number(this.valueSync) ? Number(this.valueSync) : 0
				const starList = []
				const floorValue = Math.floor(value)
				const ceilValue = Math.ceil(value)
				for (let i = 0; i < max; i++) {
					if (floorValue > i) {
						starList.push({
							activeWitch: '100%'
						})
					} else if (ceilValue - 1 === i) {
						starList.push({
							activeWitch: (value - floorValue) * 100 + '%'
						})
					} else {
						starList.push({
							activeWitch: '0'
						})
					}
				}
				return starList
			}
		},
		methods: {
			onClick(index) {
				if (this.disabled || this.disabled === 'true') {
					return
				}
				
				this.valueSync = index + 1
				this.$emit('change', {
					value: this.valueSync
				})
			}
		}
	}
</script>

<style lang="scss">
	.use-rate {
		.active {
			overflow: hidden;
			top: 0;
		}
	}
</style>