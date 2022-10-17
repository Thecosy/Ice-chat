<template>
	<view>
		<view class="margin-top-big">
			<view class="dflex-c">
				<image :src="imguse" mode="" class="border-radius-big" style="width: 130rpx; height: 130rpx;"></image>
			</view>
			<view class="dflex-c margin-top-xl">
				<view class="border-radius padding-tb-16 tac margin-right-sm" :class="{'bg-base' : this_class == 0}" style="border: 1px solid #d6d6d6; width: 20%;" @click="pitch(0)">
					10元
				</view>
				<view class="border-radius padding-tb-16 tac margin-right-sm" :class="{'bg-base' : this_class == 1}" style="border: 1px solid #d6d6d6; width: 20%;" @click="pitch(1)">
					50元
				</view>
				<view class="border-radius padding-tb-16 tac margin-right-sm" :class="{'bg-base' : this_class == 2}" style="border: 1px solid #d6d6d6; width: 20%;" @click="pitch(2)">
					100元
				</view>
			</view>
			<view class="dflex-c margin-top-xl">
				<view class="border-radius padding-tb-16 tac margin-right-sm" :class="{'bg-base' : this_class == 3}" style="border: 1px solid #d6d6d6; width: 20%;" @click="pitch(3)">
					200元
				</view>
				<view class="border-radius padding-tb-16 tac margin-right-sm" :class="{'bg-base' : this_class == 4}" style="border: 1px solid #d6d6d6; width: 20%;" @click="pitch(4)">
					500元
				</view>
				<view class="border-radius padding-tb-16 tac margin-right-sm dflex-c" :class="{'bg-base' : this_class == 5}" style="border: 1px solid #d6d6d6; width: 20%;" @click="pitch(5)">
					<input class="margin-right-xs" v-model="text" type="text" value="" placeholder="请输入" style="width: 80rpx;" />元
				</view>
			</view>
		</view>
		<view class="dflex-c w-full fixed" style="bottom: 80rpx;">
			<view class="bg-base border-radius-lg padding-tb-sm tac" style="width: 68%;" @click="clicks">
				付款
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imguse: '/static/images/headimg/face_13.jpg',
				text:'',
				this_class:0
			};
		},
		methods:{
			pitch(id){
				this.this_class = id
				this.$api.msg('选中了' + id)
			},
			// 点击付款
			clicks(){
				this.$api.msg('付款')
			}
		}
	}
</script>

<style lang="scss">
	.wh {
		width: 50%;
		padding: 20rpx 0;
		border: 1px solid #d6d6d6;
	}
</style>
