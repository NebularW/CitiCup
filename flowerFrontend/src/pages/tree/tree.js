// pages/tree/tree.js
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		familyName : '',
		show: false,
		isInFamily : false,
		isFamilyAdmin : false,
		FamilyAdminOpen_ID : '',
		familyID : -1,
		family : [],
		numOfFamily : 0,
		open_ID : ''
	},

	onChange(event) {
		var that = this
		this.setData({
			familyName : event.detail
		})
	},
	
	onClose() {
		this.setData({ show: false });
	  },

	showPopup() {
		this.setData({ show: true });
	},

	exitFamily(e){
		var that = this
		wx.request({
			url : serverUrl + '/family/delMember',
			data : {
				identity : that.data.open_ID,
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
					message: '退出成功',
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

	navToAddFamilyMember(e){
		wx.navigateTo({
			url: '../add_family_member/add_family_member',
		  })
	},

	navToAddMember(e)
	{
		wx.navigateTo({
			url: '../add_member/add_member',
		})
	},

	navToDelMember(e)
	{
		wx.navigateTo({
			url: '../delete_member/delete_member',
		})
	},

	delFamily(e)
	{
		var that = this
		wx.request({
			url : serverUrl + '/family/deleteFamily',
			data : {
				houseHolderIdentity : that.data.open_ID,
			},
			method : "POST",
			header : {
				'Content-Type':'application/x-www-form-urlencoded'
			},
			success : function (res) {
				console.log("删除家庭成功")
				console.log(res)
				Dialog.alert({
					context : this,
					selector:"#van-dialog",
					message: '删除家庭成功',
				}).then(() => {
					wx.navigateBack({
					  delta: 1,
					})
				});
			},
			fail : function (res) {
				console.log("删除家庭失败")
				console.log(res)
			}
		})
	},
	createFamily(e)
	{
		var that = this
		// if (that.data.phoneNumber == null)
		// {
		// 	Dialog.alert({
		// 		context : this,
		// 		selector:"#van-dialog",
		// 		message: '无该用户',
		// 	}).then(() => {
		// 		// on close
		// 	});
		// }
		// else
		{
			console.log(that.data.familyName)
			wx.request({
				url : serverUrl + '/family/createFamily',
				data : {
					houseHolderIdentity : that.data.open_ID,
					familyName : that.data.familyName
				},
				method : "POST",
				header : {
					'Content-Type':'application/x-www-form-urlencoded'
				},
				success : function (res) {
					console.log("创建家庭成功")
					console.log(res)
					Dialog.alert({
						context : this,
						selector:"#van-dialog",
						message: '创建家庭成功',
					}).then(() => {
						wx.navigateBack({
						  delta: 1,
						})
					});
				},
				fail : function (res) {
					console.log("创建家庭失败")
					console.log(res)
				}
			})
		}
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		var that = this
		var Pages = getCurrentPages();
		var prevPage = Pages[Pages.length - 2];
		this.setData({
			family : prevPage.data.family,
			numOfFamily : prevPage.data.numOfFamily,
			isInFamily : prevPage.data.isInFamily,
			isFamilyAdmin : prevPage.data.isFamilyAdmin,
			FamilyAdminOpen_ID : prevPage.data.FamilyAdminOpen_ID,
			familyID : prevPage.data.familyID,
			open_ID : prevPage.data.open_ID
		})
		console.log("Tree页面数据")
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
		var that = this
		if (that.data.isInFamily)
		{
			//获取家庭成员
			wx.request({
				url : serverUrl + '/family/allMembers',
				data : {
					identity : that.data.open_ID
				},
				method : "GET",
				header : {
					'Content-Type':'text/plain;charset:utf-8;'
				},
				success : function (res) {
					console.log("获取家庭成员成功")
					that.setData({
						family : res.data,
						numOfFamily : res.data.length
					})

					wx.request({
						url : serverUrl + '/family/allRelation',
						data : {
							identity : that.data.open_ID
						},
						method : "GET",
						header : {
							'Content-Type':'text/plain;charset:utf-8;'
						},
						success : function (res) {
							console.log("获取授权关系成功")
							for (var i = 0; i < that.data.numOfFamily - 1; ++i)
							{
								if (that.data.family[i].identity == that.data.open_ID) continue
								that.data.family[i].checked = res.data[that.data.family[i].identity] == 1 ? true : false
							}
						},
						fail : function (res) {
							console.log("获取授权关系失败")
							console.log(res)
						}
					})
				},
				fail : function (res) {
					console.log("获取家庭成员失败")
					console.log(res)
				}
			})
			
			
		}
	},
})