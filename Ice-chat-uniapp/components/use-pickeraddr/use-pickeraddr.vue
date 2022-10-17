<template>
	<view>
		<picker @change="bindPickerChange" @columnchange="columnchange" :range="array" range-key="name" :value="value" mode="multiSelector">
			<slot></slot>
		</picker>
	</view>
</template>

<script>
	import AllAddress from './data.js'
	let selectVal = ['', '', '']

	export default {
		data() {
			return {
				value: [0, 0, 0],
				array: [],
				index: 0
			}
		},
		created() {
			this.initSelect()
		},
		methods: {
			// 初始化地址选项
			initSelect() {
				this.updateSourceDate() // 更新源数据
					.updateAddressDate() // 更新结果数据
					.$forceUpdate() // 触发双向绑定
			},
			// 地址控件改变控件
			columnchange(d) {
				this.updateSelectIndex(d.detail.column, d.detail.value) // 更新选择索引
					.updateSourceDate() // 更新源数据
					.updateAddressDate() // 更新结果数据
					.$forceUpdate() // 触发双向绑定
			},

			/**
			 * 更新源数据
			 * */
			updateSourceDate() {
				this.array = []
				this.array[0] = AllAddress.map(obj => {
					return {
						name: obj.name
					}
				})
				this.array[1] = AllAddress[this.value[0]].city.map(obj => {
					return {
						name: obj.name
					}
				})
				this.array[2] = AllAddress[this.value[0]].city[this.value[1]].area.map(obj => {
					return {
						name: obj
					}
				})
				return this
			},

			/**
			 * 更新索引
			 * */
			updateSelectIndex(column, value) {
				let arr = JSON.parse(JSON.stringify(this.value))
				arr[column] = value
				if (column === 0) {
					arr[1] = 0
					arr[2] = 0
				}
				if (column === 1) {
					arr[2] = 0
				}
				this.value = arr
				return this
			},

			/**
			 * 更新结果数据 
			 * */
			updateAddressDate() {
				selectVal[0] = this.array[0][this.value[0]].name
				selectVal[1] = this.array[1][this.value[1]].name
				selectVal[2] = this.array[2][this.value[2]].name
				return this
			},

			/**
			 * 点击确定
			 * */
			bindPickerChange(e) {
				this.$emit('change', {
					index: this.value,
					data: selectVal
				})
				return this
			}

		}
	}
</script>

<style>
</style>
