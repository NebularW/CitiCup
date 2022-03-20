// pages/basic_info/basic_info.js
const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		tarOpen_ID : null,
		name : null,
		age : null,
		sex : null,
		sexType : null,
		IDCardNumber : null,
		height : null,
		weight : null,
		hasRecords : true
	},

	onChange1(event) {
		var that = this
		this.setData({
			name : event.detail
		})
	},

	onChange2(event) {
		var that = this
		this.setData({
			age : event.detail
		})
	},

	onChange3(event) {
		var that = this
		this.setData({
			sex : event.detail
		})
		if (that.data.sex == "男")
		{
			that.setData({
				sexType: 0
			})
		}
		else
		{
			that.setData({
				sexType : 1
			})
		}
	},

	onChange4(event) {
		var that = this
		this.setData({
			IDCardNumber : event.detail
		})
	},

	onChange5(event) {
		var that = this
		this.setData({
			height : event.detail
		})
	},

	onChange6(event) {
		var that = this
		this.setData({
			weight : event.detail
		})
	},

	confirm1 : function (e) {
		var that = this
		if (that.data.name == null || that.data.sex == null || that.data.age == null || that.data.IDCardNumber == null || that.data.height == null || that.data.weight == null)
		{
			Dialog.alert({
				context : this,
				selector:"#van-dialog",
				message: '输入不能为空',
			}).then(() => {
				// on close
			});
		}
		else
		{
			if (that.data.sex != "男" && that.data.sex != "女")
			{
				Dialog.alert({
					context : this,
					selector:"#van-dialog",
					message: '请输入正确性别',
				}).then(() => {
					// on close
				});
			}
			else
			{
				console.log(that.data)
				wx.request({
					url : serverUrl + '/health/addBasicInfo',
					data : {
						identity : that.data.tarOpen_ID,
						name : that.data.name,
						age : that.data.age,
						sex : that.data.sexType,
						IDCardNumber : that.data.IDCardNumber,
						height : that.data.height,
						weight : that.data.weight
					},
					method : "POST",
					header : {
						'Content-Type':'application/x-www-form-urlencoded'
					},
					success : function (res) {
						console.log("添加成功")
						Dialog.alert({
							context : this,
							selector:"#van-dialog",
							message: '添加成功！',
						}).then(() => {
							wx.navigateBack({
								delta: 1,
							})
						});

					},
					fail : function (res) {
						console.log("添加失败")
						console.log(res)
					}
				})
			}
			
		}
	},

	confirm  : function (e)
	{
		var that = this
		if (that.data.name == null || that.data.sex == null || that.data.age == null || that.data.IDCardNumber == null || that.data.height == null || that.data.weight == null)
		{
			Dialog.alert({
				context : this,
				selector:"#van-dialog",
				message: '输入不能为空',
			}).then(() => {
				// on close
			});
		}
		else
		{
			if (that.data.sex != "男" && that.data.sex != "女")
			{
				Dialog.alert({
					context : this,
					selector:"#van-dialog",
					message: '请输入正确性别',
				}).then(() => {
					// on close
				});
			}
			else
			{
				console.log(that.data)
				wx.request({
					url : serverUrl + '/health/updateBasicInfo',
					data : {
						identity : that.data.tarOpen_ID,
						name : that.data.name,
						age : that.data.age,
						sex : that.data.sexType,
						IDCardNumber : that.data.IDCardNumber,
						height : that.data.height,
						weight : that.data.weight
					},
					method : "POST",
					header : {
						'Content-Type':'application/x-www-form-urlencoded'
					},
					success : function (res) {
						console.log("添加成功")
						Dialog.alert({
							context : this,
							selector:"#van-dialog",
							message: '修改成功！',
						}).then(() => {
							wx.navigateBack({
								delta: 1,
							})
						});

					},
					fail : function (res) {
						console.log("修改失败")
						console.log(res)
					}
				})
			}
			
		}
	},

	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];

		that.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})
		console.log(that.data)
		wx.request({
			url : serverUrl + '/health/getBasicInfo',
			data : {
				identity : that.data.tarOpen_ID,
			},
			method : "GET",
			header : {
				'Content-Type':'text/plain;charset:utf-8;'
			},
			success : (res) => {
				console.log("获取基础信息成功")
				console.log(res)
				that.setData({
					name : res.data.name,
					sexType : res.data.sex,
					age : res.data.age,
					IDCardNumber : res.data.idcardNumber,
					height : res.data.height,
					weight : res.data.weight
				})
				if (that.data.sexType == 0)
				{
					that.setData({
						sex : "男"
					})
				}
				else
				{
					that.setData({
						sex : "女"
					})
				}
				if (that.data.name == null)
				{
					that.setData({
						sex : null,
						hasRecords : false
					})
				}
			},
			fail : function (res) {
				console.log("添加失败")
				console.log(res)
			}
		})
		console.log(that.data)
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