// pages/daily_condition/daily_condition.js
Page({

	data: {
		tarOpen_ID : null,
		
	},

	

	navToNew(e)
	{
		wx.navigateTo({
		  url: '../daily_condition/new_record',
		})
	},
	
	navToQuery(e)
	{
		wx.navigateTo({
			url: '../daily_condition/query',
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