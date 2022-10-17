<template>
	<view class="use-stepper border-radius-big" :class="direction">
		<view class="use-stepper-minus" 
			@click="_calcValue('subtract')"
		>
			<text class="iconfont iconjian fs-sm" :class="minDisabled?'use-stepper-disabled': ''" ></text>
		</view>
		<input 
			class="use-stepper-value" 
			type="number" 
			:disabled="disabled"
			:value="inputValue" 
			
			@blur="_onBlur"
		>
		<view 
			class="use-stepper-plus"
			@click="_calcValue('add')"
		>
			<text class="iconfont iconjia fs-sm" :class="maxDisabled?'use-stepper-disabled': ''" ></text>
		</view>
	</view>
</template>
<script>
	export default {
		props: {
			isMax: {
				type: Boolean,
				default: false
			},
			isMin: {
				type: Boolean,
				default: false
			},
			index: {
				type: Number,
				default: 0
			},
			value: {
				type: Number,
				default: 0
			},
			min: {
				type: Number,
				default: -Infinity
			},
			max: {
				type: Number,
				default: Infinity
			},
			step: {
				type: Number,
				default: 1
			},
			disabled: {
				type: Boolean,
				default: false
			},
			direction: {
				type: String,
				default: 'left'
			}
		},
		data() {
			return {
				inputValue: this.value,
				minDisabled: false,
				maxDisabled: false
			}
		},
		created(){
			this.maxDisabled = this.isMax;
			this.minDisabled = this.isMin;
		},
		computed: {

		},
		watch: {
			inputValue(number) {
				const data = {
					number: number,
					index: this.index
				}
				this.$emit('eventChange', data);
			}
		},
		methods: {
			_calcValue(type) {
				const scale = this._getDecimalScale();
				let value = this.inputValue * scale;
				let newValue = 0;
				let step = this.step * scale;
				
				if(type === 'subtract'){
					newValue = value - step;
					if (newValue <= this.min){
						this.minDisabled = true;
					}
					if(newValue < this.min){
						newValue = this.min
					}
					if(newValue < this.max && this.maxDisabled === true){
						this.maxDisabled = false;
					}
				}else if(type === 'add'){
					newValue = value + step;
					if (newValue >= this.max){
						this.maxDisabled = true;
					}
					if(newValue > this.max){
						newValue = this.max
					}
					if(newValue > this.min && this.minDisabled === true){
						this.minDisabled = false;
					}
				}
				if(newValue === value){
					return;
				}
				this.inputValue = newValue / scale;
			},
			_getDecimalScale() {
				let scale = 1;
				// 浮点型
				if (~~this.step !== this.step) {
					scale = Math.pow(10, (this.step + '').split('.')[1].length);
				}
				return scale;
			},
			_onBlur(event) {
				let value = event.detail.value;
				if (!value) {
					this.inputValue = 0;
					return
				}
				value = +value;
				if (value > this.max) {
					value = this.max;
				} else if (value < this.min) {
					value = this.min
				}

				this.inputValue = value
			}
		}
	}
</script>
<style>
	.use-stepper {
		position:absolute;
		display: flex;
		justify-content: flex-start;
		align-items: center;
		height: 70rpx;
		background:#f5f5f5;
	}
	
	.use-stepper.left{
		left: 30upx;
		bottom: 0;
	}
	.use-stepper.right{
		right: 0;
		bottom: 0;
	}

	.use-stepper-minus,
	.use-stepper-plus {
		margin: 0;
		background-color: #f5f5f5;
		width: 70rpx;
		height: 100%;
		line-height: 70rpx;
		text-align: center;
		position: relative;
	}
	.use-stepper-minus .iconfont,
	.use-stepper-plus .iconfont {
		color: #555;
		font-weight: 700;
	}

	.use-stepper-minus {
		border-right: none;
		border-top-left-radius: 6upx;
		border-bottom-left-radius: 6upx;
	}

	.use-stepper-plus {
		border-left: none;
		border-top-right-radius: 6upx;
		border-bottom-right-radius: 6upx;
	}

	.use-stepper-value {
		position: relative;
		background-color: #f5f5f5;
		width: 66rpx;
		height: 50rpx;
		text-align: center;
		padding: 0;
	}

	.use-stepper-disabled.yticon {
		color: #d6d6d6;
	}
</style>
