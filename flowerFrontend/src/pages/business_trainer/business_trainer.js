// pages/business_trainer/business_trainer.js
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		active : 0,
	},

	navToQA(e)
	{
		wx.navigateTo({
		  url: '../business_trainer/QA',
		})
	},
	navToInvest(e)
	{
		wx.navigateTo({
		  url: '../business_trainer/invest',
		})
	},

	onChange(event) {
		this.setData({
			active : event.detail
		})
	},
	onLoad : function(e)
	{
		this.setData({
			active : 0
		})
	}
})