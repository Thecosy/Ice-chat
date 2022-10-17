<template>
	<view class="container">

		<view class="pos-f w-full navbar-area bg-main">
			<view class="state-area dflex-a">
				<view v-for="(item, index) in navList" :key="index" :class="{ active: tabCurrentIndex === index }" class="nav-item dflex-c pos-r fs padding-lr-lg h-full"
				 @click="tabClick(index)">
					{{ item.state }}
					<text v-if="item.cnt > 0">({{ item.cnt }})</text>
				</view>
			</view>
		</view>

		<swiper :current="tabCurrentIndex" :disable-touch="true" class="swiper-box swiper-area h-full" duration="300" @change="changeTab">
			<swiper-item class="tab-content" v-for="(tabItem, tabIndex) in navList" :key="tabIndex">
				<scroll-view class="list-scroll-content h-full" scroll-y @scrolltolower="loadData">
					<!-- 空白页 -->
					<use-empty v-if="tabItem.datas.length === 0 && tabItem.loaded" e-style="round" e-type="cart" tip="暂无协助券" btn-tip=" " height="93vh"></use-empty>
					<view class="padding-lr" @click="use(item)" v-for="(item, index) in tabItem.datas" :key="index">
						<view class="coupon_box border-radius margin-top-sm bg-main" :class="[{ 'disabled': tabItem.state != '已领取' }]">
							<view class="dflex-b">
								<view class="left pos-a h-full dflex-c dflex-flow-c">
									<view v-if="item.type == '满减'">
										<text class="price fs-big">{{ item.price }}</text>
									</view>
									<view v-if="item.type == '折扣'">
										<text class="discount fs-big">{{ item.price }}</text>
									</view>
									<view class="fs-sm" v-if="item.order_amount > 0">满{{ item.order_amount }}元使用</view>
									<view class="fs-sm" v-else>全场通用</view>
								</view>
								<view class="right padding left_t flex1">
									<view class="dflex-b padding-bottom-xs">
										<view class="fwb fs">{{ item.name }}</view>
									</view>
									<view v-if="tabItem.state == '已使用'" class="ft-dark iconfont iconyishiyong"></view>
									<view v-if="tabItem.state == '已过期'" class="ft-dark iconfont iconyiguoqi"></view>
									<view class="dflex-b ft-dark fs-xs padding-bottom border-line">
										<view class="">有效期至 {{ item.end_time }}</view>
									</view>
									<view class="fs-xs ft-dark padding-top-xs">{{ item.remark || '详细信息' }}</view>
								</view>
							</view>
						</view>
					</view>

					<!-- 上拉加载更多 -->
					<use-loadmore v-if="tabItem.datas.length > 0 && tabItem.loaded && tabItem.hasmore" :type="tabItem.loadingType"></use-loadmore>
				</scroll-view>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	export default {
		data() {
			return {
				tabCurrentIndex: 0,
				navData: {},
				navList: [{
						id: 0,
						state: '已领取',
						cnt: 0,
						loadingType: 'more',
						datas: []
					},
					{
						id: 1,
						state: '已使用',
						cnt: 0,
						loadingType: 'more',
						datas: []
					},
					{
						id: 2,
						state: '已过期',
						cnt: 0,
						loadingType: 'more',
						datas: []
					}
				],
				reqdata: {
					page: 1,
					rows: 10
				},
				scrollLeft: 0
			};
		},
		watch: {
			tabCurrentIndex(nv, ov) {
				this.loadData('tab_change', 1);
			}
		},
		onLoad(options) {
			this.loadData();
		},
		// 下拉刷新
		onPullDownRefresh() {
			this.loadData('refresh');
		},
		// 上拉加载更多
		onReachBottom() {
			this.loadData();
		},
		methods: {
			// 加载数据
			loadData(source = 'add', loading) {
				// 获取当前 nav
				let cur_nav = this.navList[this.tabCurrentIndex];
				
				if (cur_nav.loadingType === 'loading') {
					//防止重复加载
					return;
				}

				this.reqdata.state = cur_nav.state;
				if (loading == 1 || source == 'refresh') {
					this.reqdata.page = 1;
				}
				if (source.type) { source.type = source.type.toLowerCase(); }
				if (source === 'add' || source.type == 'scrolltolower') {
					if (cur_nav.loadingType == 'nomore') {
						return;
					}
					cur_nav.loadingType = 'loading';
				} else {
					cur_nav.loadingType = 'more';
				}
				cur_nav.datas = [];
				cur_nav.loaded = true;
			},

			// swiper 切换
			changeTab(e) {
				this.tabCurrentIndex = e.target.current;
			},
			// 顶部tab点击
			tabClick(index) {
				this.tabCurrentIndex = index;
			},
			// 去使用
			use(options) {
				if (options.state == '已领取') 
					this.$api.togoodslist({
						coupon_id: options.coupon_id
					});
			},
		}
	};
</script>

<style lang="scss">
	page,
	.container {
		min-height: 100%;
		background: $page-color-base;
	}

	.container {
		padding-top: 7vh;
	}

	/* 协助券状态区 */
	.navbar-area {
		top: 0;
		white-space: nowrap;

		.state-area {
			height: 7vh;
			box-shadow: 0 1px 5px rgba(0, 0, 0, 0.06);
			z-index: 10;
		}

		.nav-item {
			flex: 1;

			&.active {
				&:after {
					content: '';
					position: absolute;
					left: 50%;
					transform: translate(-50%);
					bottom: 0;
					width: 44px;
					height: 0;
					border-bottom: 2px solid $base-color;
				}
			}
		}
	}

	/* #ifdef H5 */
	.navbar-area {
		margin-top: calc(44px + env(safe-area-inset-top));
	}

	/* #endif */

	/* 协助券轮播区 */
	.swiper-area {
		height: 93vh;
	}

	.coupon_box {
		position: relative;
		&:last-child {
			margin-bottom: 20rpx;
		}
		.left {
			background-color: $base-color;
			color: #fff;
			width: 30%;

			.price {
				color: #fff !important;
			}
		}

		.right {
			margin-left: 30%;
		}

		.discount {
			font-weight: 580;
		}

		.discount::after {
			content: '折';
			font-size: 24rpx;
			margin-left: 6rpx;
		}

		.border-line {
			border-bottom: 1px dotted #ededed;
		}
	}

	.disabled {
		.left {
			background-color: #d9d9d9;
			color: #b2b2b2 !important;

			.price {
				color: #b2b2b2 !important;
			}
		}

		.iconfont {
			position: absolute;
			top: 0rpx;
			right: 30rpx;
			font-size: 110rpx;
		}
	}
</style>
