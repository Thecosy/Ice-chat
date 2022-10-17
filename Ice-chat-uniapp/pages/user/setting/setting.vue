<template>
	<view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					关于我们
				</view>
				<view class="dflex-c">
					<view class="iconfont iconjiantou">

					</view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					版本号
				</view>
				<view class="dflex-c">
					<view class="ft-dark padding-right-sm">
						v1.0.0
					</view>
				
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>
		<view class="padding-lr margin-top">
			<view class="border-radius padding-lr bg-drak dflex-b padding-tb" style="align-items: center;">
				<view class=" ">
					投诉与建议
				</view>
				<view class="dflex-c">
					<view class="iconfont iconjiantou"></view>
				</view>
			</view>
		</view>

		<view class=" padding-lr padding-bottom w-full fixed pos-bottom">
			<view v-if="islogin" class="border-radius margin-top-sm padding-sm dflex-c bg-main log-out-btn"
				@click="openActionSheet">
				<text class="cell-tit">退出登录</text>
			</view>
			<view v-else class="padding-tb bg-main border-radius-lg tac padding-lr-sm bg-base" @click="to_login">
				<view class=" ">
					清除缓存
				</view>
			</view>
		</view>
		<!-- 操作菜单 -->
		<use-action-sheet v-model="actionSheetShow" :list="actionSheetList" :tips="actionSheetTips"
			@click="actionSheetClick" @close="actionSheetClose"></use-action-sheet>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex';
	export default {
		computed: {
			...mapState(['islogin', 'member'])
		},
		data() {
			return {
				actionSheetShow: false,
				actionSheetList: [],
				actionSheetTips: {
					text: "退出登录 | 切换账号",
					color: "#9a9a9a",
					size: 24
				},
				is_mp: false,
				is_alipay: false,
			};
		},
		onLoad() {
			this.$nextTick(() => {
				this.is_mp = this.$env.is_mp;
				this.is_alipay = this.$env.platform == 'alipay';
			})
		},
		methods: {
			...mapMutations(['logout', 'putMember']),
			to_login(){
				uni.clearStorage();
			},
			// 打开操作菜单
			openActionSheet() {
				this.actionSheetShow = true;

				this.$api.timerout(() => {
					this.actionSheetList = [{
						text: "退出登录",
						color: "#333"
					}, {
						text: "切换账号",
						color: "#333"
					}, ];

					console.log('actionSheet', {
						show: this.actionSheetShow,
						list: this.actionSheetList
					});
				}, 0);
			},
			// 关闭操作菜单
			actionSheetClose() {
				console.log(this.actionSheetShow);
			},
			// 点击操作菜单
			actionSheetClick(index) {
				switch (index) {
					case 0:
						this.$api.msg('退出成功');
						this.logout();
						this.$api.timerout(() => {
							this.$api.tohome();
						}, 200);
						break;
					case 1:
						this.$api.tologin();
						break;
				}
			},
		}
	}
</script>

<style lang="scss">
	.iconfont {
		font-size: 14px;
		color: #c0c0c0;
	}
</style>
