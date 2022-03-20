// pages/medicine_records/medicine_records.js
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		tarOpen_ID : null,
	},

	navToNew(e)
	{
		wx.navigateTo({
		  url: '../medicine_records/new_record',
		})
	},
	
	navToQuery(e)
	{
		wx.navigateTo({
			url: '../medicine_records/query',
		})
	},

	onLoad: function (options) {
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		console.log(prevPage.data);
		this.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})
	},

	onShow: function () {

	},

})