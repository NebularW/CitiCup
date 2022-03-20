// pages/new_health_records/new_health_records.js
const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl

Page({

	data: {
		tarOpen_ID : null,
		name : null,
		age : null,
		sex : null,
		sexType : 0,
		IDCardNumber : null,
		height : null,
		weight : null
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

	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		that.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})
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

	onShow: function () {

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