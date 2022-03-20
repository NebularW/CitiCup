const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		active : 0,
		tarOpen_ID : null,
		year : null,
		month : null,
		day : null,
		value : null,
		
	},

	onChange1(event) {
		var that = this
		this.setData({
			year : event.detail
		})
	},
	onChange2(event) {
		var that = this
		this.setData({
			month : event.detail
		})
	},
	onChange3(event) {
		var that = this
		this.setData({
			day : event.detail
		})
	},
	onChange4(event) {
		var that = this
		this.setData({
			value : event.detail
		})
	},

	onChange(event)
	{

		this.setData({
			year : null,
			month : null,
			day : null,
			value : null,
			active : event.detail.index
		})
		
	},

	confirm(e)
	{
		var that = this
		if (that.data.year == null || that.data.month == null || that.data.day == null || that.data.value == null)
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
			if (that.data.month.length == 1)
				that.data.month = "0" + that.data.month
			
			if (that.data.day.length == 1)
				that.data.day = "0" + that.data.day

			wx.request({
				url : serverUrl + '/health/insertBloodInfo',
				data : {
					identity : that.data.tarOpen_ID,
					type : that.data.active,
					level : that.data.value,
					timestamp : "" + that.data.year + "-" + that.data.month + "-" + that.data.day
				},
				method : "POST",
				header : {
					'Content-Type':'application/x-www-form-urlencoded'
				},
				success : function (res) {
					console.log("新建成功")
					Dialog.alert({
						context : this,
						selector:"#van-dialog",
						message: '上传成功！',
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
	},

	onLoad: function (options) {
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		console.log(prevPage.data);
		this.setData({
			tarOpen_ID : prevPage.data.tarOpen_ID
		})
	},


	onShow: function () {

	},

})