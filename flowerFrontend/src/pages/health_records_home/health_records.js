// pages/health_records/health_records.js
Page({

	data: {
		tarOpen_ID : null
	},

	navToBasicInfo(e)
	{
		wx.navigateTo({
		  url: '../basic_info/basic_info',
		})
	},

	navToDailyCondition(e)
	{
		wx.navigateTo({
		  url: '../daily_condition/daily_condition',
		})
	},

	navToMedicineRecord(e)
	{
		wx.navigateTo({
			url: '../medicine_records/medicine_records',
		})
	},

	navToIllnessRecord(e)
	{
		wx.navigateTo({
		  url: '../illness_records/illness_records',
		})
	},

	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		console.log(prevPage.data);
		that.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})
		console.log(that.data.tarOpen_ID)
	},


})