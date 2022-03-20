const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl
Page({

	data: {
		tarOpen_ID : null,
		record : []
	},

	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		console.log(prevPage.data);
		this.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})

		wx.request({
			url : serverUrl + '/health/getMedicalHistoryInfo',
			data : {
				identity : that.data.tarOpen_ID,
			},
			method : "GET",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			success : function (res) {
				console.log("查询成功")
				that.setData({
					records : res.data
				})
				// for (var i = 0; i < records.length; ++i)
				// {
				// 	if (records[i].announce == null)
				// 	{
				// 		records[i].announce ="无"
				// 	}
				// }

			},
			fail : function (res) {
				console.log("添加失败")
				console.log(res)
			}
		})
	},



	onShow: function () {

	},
})