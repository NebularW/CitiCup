// pages/home/home.js
const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		show : false ,
		pattern : true,
		open_ID: null,
		isInFamily: false,
		isFamilyAdmin: false,
		FamilyAdminOpen_ID: '',
		familyID: -1,
		family: [{
				identity: null,
				avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKK7lZKx3UpbNQnjvibLtrVp3pGF1yTqV802bHEVeVSsFibkicPLQhUyIOUAicQSOVWRwxT9eJPwaW9Bg/132",
				userName: "Hare"
			},
			{
				identity: null,
				avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKK7lZKx3UpbNQnjvibLtrVp3pGF1yTqV802bHEVeVSsFibkicPLQhUyIOUAicQSOVWRwxT9eJPwaW9Bg/132",
				userName: "Hare"
			}
		],
		numOfFamily: 2,
	},

	navToEarnings(e)
	{
		wx.navigateTo({
			url: '../earnings_home/earnings_home',
		})
	},

	navToHistory(e)
	{
		wx.navigateTo({
		  url: '../history_home/history_home',
		})
	},
	navToHealth(e)
	{
		wx.navigateTo({
			url: '../health_records_home/health_records_home',
		})
	},

	navToAI_Query(e){
		wx.navigateTo({
			url: '../AI_query/AI_query',
		})
	},
	
	navToBusinessTrainer(e)
	{
		wx.navigateTo({
			url: '../business_trainer/business_trainer',
		})
	},

	navToRisk(e)
	{
		wx.navigateTo({
		  url: '../risk_family/risk_family',
		})

	},

	navToTree(e) {
		wx.navigateTo({
			url: '../tree/tree',
		})
	},

	btnTap1(e) {
		wx.navigateTo({
			url: '../senior-helper/senior-helper',
		})
	},

	imgTap1(e) {
		wx.navigateTo({
			url: '../family_members/family_members',
		})
	},

	navToSeniorHelper(e) {
		wx.navigateTo({
			url: '../senior-helper/senior-helper',
		})
	},

	navToInvite(e)
	{
		wx.navigateTo({
			url: '../invite/invite',
		})
	},

	onLoad: function (options) {
		
		var tmp = app.globalData.pattern
		this.setData({
			pattern : tmp
		})
		var that = this
		var serverUrl = app.globalData.serverUrl
		console.log(wx.getStorageSync('id'))
		this.setData({
			open_ID: wx.getStorageSync('id')
		})


	},
	checkIsHouseHolder: function (i) {
		var that = this
		wx.request({
			url: serverUrl + '/family/isHouseHolder',
			data: {
				houseHolderIdentity: that.data.family[i].identity,
			},
			method: "GET",
			header: {
				'Content-Type': 'text/plain;charset:utf-8;'
			},

			success: function (res) {
				console.log("现在是" + i)
				console.log("获取户主成功")
				if (res.data == true)
				{
					that.setData({
						FamilyAdminOpen_ID: that.data.family[i].identity
					})
					if (that.data.family[i].identity == that.data.open_ID)
					{
						console.log("testinfo")
						console.log(that.data.family[i].identity)
						console.log(that.data.family[i])
						if (that.data.family[i].identity == that.data.open_ID) {
							that.setData({
								isFamilyAdmin: true
							})
						}
						console.log(that.data)
					}
				}
			},
			fail: function (res) {
				console.log("获取户主失败")
				console.log(res)
			}
		})
	},

	onClose() {
		this.setData({ show: false });
	},
	navToChat(e)
	{
		// Dialog.alert({
		// 	context: this,
		// 	selector: "#van-dialog",
		// 	message: '小君邀请您体验声临其境功能',
		// }).then(() => {
		// });

	},

	onShow: function () {
		this.getTabBar().init();
		
		// Dialog.alert({
		// 	context: this,
		// 	selector: "#van-dialog",
		// 	message: '小君邀请您体验声临其境',
		// }).then(() => {
		// });

		var that = this

		wx.request({
			url: serverUrl + '/family/inFamily',
			data: {
				identity: that.data.open_ID
			},
			method: "GET",
			header: {
				'Content-Type': 'text/plain;charset:utf-8;'
			},
			success: function (res) {
				console.log("获取是否在家庭成功")
				console.log(res)
				that.setData({
					isInFamily: res.data == 1 ? true : false
				})
				console.log(that.data)

				if (that.data.isInFamily) {
					wx.request({
						url: serverUrl + '/family/getFamilyID',
						data: {
							identity: that.data.open_ID
						},
						method: "GET",
						header: {
							'Content-Type': 'text/plain;charset:utf-8;'
						},
						success: function (res) {
							console.log("获取家庭ID成功")
							console.log(res)
							that.setData({
								familyID: res.data,
							})
							console.log(that.data)
						},
						fail: function (err) {
							console.log("获取家庭ID失败")
							console.log(err)
						}
					})


					// this.data.family = [{name : "Hare", checked : false}, {name : "Hare2", checked : false}]
					// this.data.numOfFamily = 2

					//获取家庭成员
					wx.request({
						url: serverUrl + '/family/allMembers',
						data: {
							identity: that.data.open_ID
						},
						method: "GET",
						header: {
							'Content-Type': 'text/plain;charset:utf-8;'
						},
						success: function (res) {
							console.log("获取家庭成员成功")
							that.setData({
								family: res.data,
								numOfFamily: res.data.length
							})
							for (var i = 0; i < that.data.numOfFamily; ++i) {
								that.checkIsHouseHolder(i)
							}

							wx.request({
								url: serverUrl + '/family/allRelation',
								data: {
									identity: that.data.open_ID
								},
								method: "GET",
								header: {
									'Content-Type': 'text/plain;charset:utf-8;'
								},
								success: function (res) {
									console.log("获取授权关系成功")
									for (var i = 0; i < that.data.numOfFamily - 1; ++i) {
										if (that.data.family[i].identity == that.data.open_ID) continue
										that.data.family[i].checked = res.data[that.data.family[i].identity] == 1 ? true : false
									}
								},
								fail: function (res) {
									console.log("获取授权关系失败")
									console.log(res)
								}
							})
						},
						fail: function (res) {
							console.log("获取家庭成员失败")
							console.log(res)
						}
					})


				}
			},
			fail: function (err) {
				console.log("获取是否在家庭失败")
				console.log(err)
			}
		})

		console.log(that.data.isInFamily)

	},
})