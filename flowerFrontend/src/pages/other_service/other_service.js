Page({

	data: {
		
	},

	navToHealth(e) {
		wx.navigateTo({
			url: '../health/health',
		})
	},

	navToCommunity(e)
	{
		wx.navigateTo({
		  url: '../community/community',
		})
	},

	navToNurse(e)
	{
		wx.navigateTo({
			url: '../nurse/nurse'
		})
	},
	onShow: function () {
		this.getTabBar().init();
	},
})