<template>
	<view class="use-numbox pos-a dflex-c border-radius-big" :class="direction">
		<view class="use-numbox-minus pos-r tac h-full" 
			@tap.stop="_calcValue('subtract')"
		>
			<text class="iconfont iconjian fs-sm" :class="minDisabled?'use-numbox-disabled': ''" ></text>
		</view>
		<input 
			class="use-numbox-value pos-r tac" 
			type="number" 
			:disabled="disabled"
			:value="inputValue" 
			
			@blur="_onBlur"
		>
		<view 
			class="use-numbox-plus pos-r tac h-full"
			@tap.stop="_calcValue('add')"
		>
			<text class="iconfont iconjia fs-sm" :class="maxDisabled?'use-numbox-disabled': ''" ></text>
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
	.use-numbox {
		position:absolute;
		background:#f5f5f5;
	}
	
	.use-numbox.left{
		left: 30rpx;
		bottom: 0;
	}
	.use-numbox.right{
		right: 0;
		bottom: 0;
	}

	.use-numbox-minus,
	.use-numbox-plus {
		margin: 0;
		background-color: #f5f5f5;
		padding: 12rpx 20rpx;
	}
	.use-numbox-minus .iconfont,
	.use-numbox-plus .iconfont {
		color: #555;
		font-weight: 700;
	}

	.use-numbox-minus {
		border-right: none;
		border-top-left-radius: 6upx;
		border-bottom-left-radius: 6upx;
	}

	.use-numbox-plus {
		border-left: none;
		border-top-right-radius: 6upx;
		border-bottom-right-radius: 6upx;
	}

	.use-numbox-value {
		background-color: #f5f5f5;
		width: 66rpx;
		height: 50rpx;
		padding: 0;
	}

	.use-numbox-disabled.iconfont {
		color: #bbb;
	}
</style>
