// pages/nurse/nurse.js
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		active:1
	},
	
	updTo1(e)
	{
		this.setData({
			active : 1
		})
	},

	updTo2: function(e)
	{
		this.setData({
			active : 2
		})
	},

	updTo3: function(e)
	{
		this.setData({
			active : 3
		})
	},

	navTo2 : function (e) {
		wx.navigateToMiniProgram({
			appId: 'wxde8ac0a21135c07d',
		})
	},
	navTo1: function(e)
	{
		wx.navigateToMiniProgram({
		  appId: 'wx56f4459e89f63e7a',
		})
	},
	
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {

	},

	/**
	 * 生命周期函数--监听页面初次渲染完成
	 */
	onReady: function () {

	},

	/**
	 * 生命周期函数--监听页面显示
	 */
	onShow: function () {

	},

	/**
	 * 生命周期函数--监听页面隐藏
	 */
	onHide: function () {

	},

	/**
	 * 生命周期函数--监听页面卸载
	 */
	onUnload: function () {

	},

	/**
	 * 页面相关事件处理函数--监听用户下拉动作
	 */
	onPullDownRefresh: function () {

	},

	/**
	 * 页面上拉触底事件的处理函数
	 */
	onReachBottom: function () {

	},

	/**
	 * 用户点击右上角分享
	 */
	onShareAppMessage: function () {

	}
})