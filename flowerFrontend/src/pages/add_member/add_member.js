// pages/add_member/add_member.js
const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		phoneNumber : null,
		open_ID : '',
		isInFamily : false,
		isFamilyAdmin : false,
		FamilyAdminOpen_ID : '',
		familyID : -1,
		family : [],
		numOfFamily : 0,
	},

	onChange(event) {
		var that = this
		this.setData({
			phoneNumber : event.detail
		})
		// wx.request({
		// 	url: app.globalData.serverUrl + '/addMember',
		// 	data : {
		// 		phoneNumber : that.data.phoneNumber,
		// 		identity : 
		// 	}
		// })
	},

	confirm  : function (e)
	{
		var that = this
		console.log(that.data.phoneNumber)
		if (that.data.phoneNumber == null)
		{
			Dialog.alert({
				context : this,
				selector:"#van-dialog",
				message: '请输入有效手机号',
			}).then(() => {
				// on close
			});
		}
		else
		{
			if (that.data.isFamilyAdmin == false)
			{
				Dialog.alert({
					context : this,
					selector:"#van-dialog",
					message: '您没有权限',
				}).then(() => {
					// on close
				});
			}
			else
			{
				wx.request({
					url : serverUrl + '/family/addMember',
					data : {
						phoneNumber : that.data.phoneNumber,
						familyID : that.data.familyID
					},
					method : "POST",
					header : {
						'Content-Type':'application/x-www-form-urlencoded'
					},
					success : function (res) {
						console.log("获取成功")
						if (res.data == -1)
						{
							Dialog.alert({
								context : this,
								selector:"#van-dialog",
								message: '用户不存在',
							}).then(() => {
								// on close
							});
						}
						else if (res.data == 0)
						{
							Dialog.alert({
								context : this,
								selector:"#van-dialog",
								message: '该用户已在家庭中',
							}).then(() => {
								// on close
							});
						}
						else if (res.data == 1)
						{
							Dialog.alert({
								context : this,
								selector:"#van-dialog",
								message: '添加成功',
							}).then(() => {
								wx.navigateBack({
									delta: 1,
								  })
							});
						}
						else if (res.data == 2)
						{
							Dialog.alert({
								context : this,
								selector:"#van-dialog",
								message: '用户已加入其它家庭',
							}).then(() => {
								
							});
						}
						else if (res.data == 3)
						{
							Dialog.alert({
								context : this,
								selector:"#van-dialog",
								message: '没有这个家庭',
							}).then(() => {
								
							});
						}
					},
					fail : function (res) {
						console.log("获取失败")
						console.log(res)
					}
				})
			}
		}
	},

	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		console.log(prevPage.data);
		this.setData({
			family : prevPage.data.family,
			numOfFamily : prevPage.data.numOfFamily,
			isInFamily : prevPage.data.isInFamily,
			isFamilyAdmin : prevPage.data.isFamilyAdmin,
			FamilyAdminOpen_ID : prevPage.data.FamilyAdminOpen_ID,
			familyID : prevPage.data.familyID,
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