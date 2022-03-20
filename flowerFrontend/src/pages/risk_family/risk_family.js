const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({

	data: {
		hasAuth : [true, false],
		family : [],
		numOfFamily : 0,
		open_ID : null,
		isInFamily : false
	},

	
	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		var serverUrl = app.globalData.serverUrl
		console.log(prevPage.data);
		this.setData({
			family : prevPage.data.family,
			numOfFamily : prevPage.data.numOfFamily,
			open_ID : prevPage.data.open_ID,
			isInFamily : prevPage.data.isInFamily
		})

		console.log(that.data.open_ID)
		wx.request({
			url: serverUrl + '/risk/family',
			data : {
				identity : that.data.open_ID,
			},
			method : "GET",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},

			success : function (res) {
				console.log("获取风险成功")
				that.setData({
					family : res.data
				})
			},
			fail : function (res) {
				console.log("获取风险失败")
				console.log(res)
			}
		})
	},

	onShow: function () {

	},
})