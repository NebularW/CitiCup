// pages/health/health.js
const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl
Page({

	data: {
		open_ID : null,
		hasHealthRecords : false,
		healthRecords : '',
		tarOpen_ID : ''
	},

	navTo3 : function (e) {
		wx.navigateToMiniProgram({
			appId: 'wxd912763a5a92ea33',
		})
	},
	navToPE: function(e)
	{
		// wx.navigateTo({
		//   url: '../physical_examination/physical_examination',
		// })
	},

	navtoHealthRecords : function(e)
	{
		wx.navigateTo({
		  url: '../health_records/health_records',
		})
	},

	navToCreateHealthRecords : function(e)
	{
		wx.navigateTo({
		  url: '../new_health_records/new_health_records',
		})
	},

	onLoad: function (options) {
		var that = this
		this.setData({
			open_ID : wx.getStorageSync('id')
		})
		this.setData({
			tarOpen_ID : that.data.open_ID
		})
		console.log(that.data)
	},

	onReady: function () {

	},

	/**
	 * 生命周期函数--监听页面显示
	 */
	onShow: function () {
		var that = this
		wx.request({
			url: serverUrl + '/health/getBasicInfo',
			data : {
				identity : that.data.open_ID
			},
			method : "GET",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},

			success : function (res) {
				console.log("获取健康档案成功")
				if (res.data != "")
				{
					that.setData({
						healthRecords : res.data,
						hasHealthRecords : true
					})
				}
				console.log(res)
				console.log(that.data)
			},
			fail : function (res) {
				console.log("获取健康档案失败")
				console.log(res)
			}
		})
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