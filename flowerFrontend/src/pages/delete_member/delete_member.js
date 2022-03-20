// pages/delete_member/delete_member.js
const app = getApp()
var serverUrl = app.globalData.serverUrl
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		family : [],
		numOfFamily : '',
		isInFamily : '',
		isFamilyAdmin : '',
		FamilyAdminOpen_ID : '',
		familyID : '',
		open_ID : ''
	},

	delMember(e)
	{
		var that = this
		wx.request({
			url : serverUrl + '/family/delMember',
			data : {
				identity : that.data.family[e.currentTarget.dataset.index].identity,
				familyID : that.data.familyID
			},
			method : "POST",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			success : function (res) {
				console.log("删除成员成功")
				Dialog.alert({
					context : this,
					selector:"#van-dialog",
					message: '删除成功',
				}).then(() => {
					wx.navigateBack({
					  delta: 1,
					})
				});
			},
			fail : function (res) {
				console.log("获取授权关系失败")
				console.log(res)
			}
		})
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		var serverUrl = app.globalData.serverUrl
		console.log(prevPage.data);
		this.setData({
			family : prevPage.data.family,
			numOfFamily : prevPage.data.numOfFamily,
			isInFamily : prevPage.data.isInFamily,
			isFamilyAdmin : prevPage.data.isFamilyAdmin,
			FamilyAdminOpen_ID : prevPage.data.FamilyAdminOpen_ID,
			familyID : prevPage.data.familyID,
			open_ID : prevPage.data.open_ID
		})
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