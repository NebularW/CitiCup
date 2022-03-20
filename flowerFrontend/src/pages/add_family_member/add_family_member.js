// pages/add_family_member/add_family_member.js
var app = getApp()
Page({

	data: {
		open_ID : null,
		family : [],
		numOfFamily : 0,
	},

	onChange: function(e) {
		var that = this
		var arr = that.data.family
		arr[e.currentTarget.dataset.index].checked = e.detail
		that.setData({
			family : arr
		})
		that.data.family[e.currentTarget.dataset.index].checked = e.detail
		console.log(e.detail)
	},
	onLoad: function (options) {
		let that = this
		var Pages = getCurrentPages()
		var prevPage = Pages[Pages.length - 2]
		console.log(prevPage.data)
		this.setData({
			family : prevPage.data.family,
			numOfFamily : prevPage.data.numOfFamily,
			open_ID : prevPage.data.open_ID
		})
		console.log("加载数据成功")
		
		// for (var i = 0; i < this.data.numOfFamily; ++i)
		// 	this.data.family[i].unique = 'unique_' + i
		// console.log(Pages[Pages.length - 1].data)

	},

	onPullDownRefresh: function () {

	},

})