const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		tarOpen_ID : null,
		records0 : [],
		records1 : [],
		records2 : [],
		active : 0

	},

	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		console.log(prevPage.data);
		this.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})

		wx.request({
			url : serverUrl + '/health/getBloodInfo',
			data : {
				identity : that.data.tarOpen_ID,
				type : 0
			},
			method : "GET",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			success : function (res) {
				console.log("查询成功")
				that.setData({
					records0 : res.data
				})

			},
			fail : function (res) {
				console.log("添加失败")
				console.log(res)
			}
		})

		wx.request({
			url : serverUrl + '/health/getBloodInfo',
			data : {
				identity : that.data.tarOpen_ID,
				type : 1,
			},
			method : "GET",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			success : function (res) {
				console.log("查询成功")
				that.setData({
					records1 : res.data
				})

			},
			fail : function (res) {
				console.log("添加失败")
				console.log(res)
			}
		})

		wx.request({
			url : serverUrl + '/health/getBloodInfo',
			data : {
				identity : that.data.tarOpen_ID,
				type : 2,
			},
			method : "GET",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			success : function (res) {
				console.log("查询成功")
				that.setData({
					records2 : res.data
				})

			},
			fail : function (res) {
				console.log("添加失败")
				console.log(res)
			}
		})
	},

})