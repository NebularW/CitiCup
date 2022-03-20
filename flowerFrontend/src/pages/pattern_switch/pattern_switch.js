var app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl
Page({


	data: {

	},

	pt1(e)
	{
		app.globalData.pattern = false
		Dialog.alert({
			context : this,
			selector:"#van-dialog",
			message: '切换成功！',
		}).then(() => {
			wx.navigateBack({
			  delta: 1,
			})
		});
	},

	pt2(e)
	{
		app.globalData.pattern = true
		Dialog.alert({
			context : this,
			selector:"#van-dialog",
			message: '切换成功！',
		}).then(() => {
			wx.navigateBack({
			  delta: 1,
			})
		});
	},

	onLoad: function (options) {

	},

	onShow: function () {

	},


})