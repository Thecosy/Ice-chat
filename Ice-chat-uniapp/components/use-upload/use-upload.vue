<template>
	<view class="use-upload dflex dflex-wrap-w">
		<view class="item pos-r" v-for="(item, index) in imgs" @click="preImage(item.url, imgs)" :key="index">
			<image :src="item.url" mode="scaleToFill"></image>
			<view class="del pos-a bg-main dflex-c border-radius-c iconfont iconlajitong-01 ft-dark" @tap.stop="delImage(index)"></view>
		</view>

		<view class="item dflex-c" v-if="imgs.length < limit" @tap="chooseImage">
			<view class="iconfont iconxiangji fs-big ft-dark"></view>
		</view>
	</view>
</template>

<script>
export default {
	props: {
		limit: {
			type: Number,
			default: 3
		}
	},
	data() {
		return {
			imgs: []
		};
	},
	methods: {
		chooseImage() {
			let _this = this;
			uni.chooseImage({
				count: _this.limit,
				// 可以指定是原图|压缩图，默认二者都有
				sizeType: ['original', 'compressed'], 
				success: function(res) {
					uni.showLoading({
						title: '上传中',
						mask: true
					});

					for (var i = 0; i < res.tempFilePaths.length; i++) {
						uni.uploadFile({
							url: _this.$config.get_url() + 'system/uploadimg', 
							filePath: res.tempFilePaths[i],
							name: 'file',
							success(_res) {
								let _obj = JSON.parse(_res.data);
								console.log(_obj);
								if (_obj.code == 200) {
									_this.imgs.push(_obj.datas);
									_this.sendData()
								} else {
									_this.$api.msg(_obj.msg);
								}
							}
						});
					}

					// console.log(_this.imgs);
					uni.hideLoading();
				}
			});
		},
		preImage(url, urls) {
			let _urls = urls.map(ele => {
				return ele.url;
			});
			console.log(url, urls);
			uni.previewImage({
				current: url,
				urls: _urls,
				longPressActions: {
					itemList: ['发送给朋友', '保存图片', '收藏'],
					success: function(data) {
						console.log('选中了第' + (data.tapIndex + 1) + '个按钮,第' + (data.index + 1) + '张图片');
					},
					fail: function(err) {
						console.log(err.errMsg);
					}
				}
			});
		},

		delImage(idx) {
			this.imgs.splice(idx, 1);
			this.sendData();
		},

		sendData() {
			this.$emit('upload', this.imgs);
		}
	}
};
</script>

<style lang="scss">
.use-upload {
	.item {
		width: 23%;
		margin-right: 2%;
		height: 150rpx;
		border: 1px solid #f0f0f0;
		image {
			width: 100%;
			height: 100%;
		}
	}

	.del {
		top: -30rpx;
		right: -10rpx;
		width: 50rpx;
		height: 50rpx;
		z-index: 99;
		box-shadow: 0px 3px 5px #f0f0f0;
	}
}
</style>
