const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl

Page({

  /**
   * 页面的初始数据
   */
  data: {
    myasset:'',
    yester:'',
    sumasset:'',
    money:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
    var tempid=wx.getStorageSync('id')
    wx.request({
		url: serverUrl + '/asset/financial',
      data: {
        identity:tempid,
      }, 
      method: 'POST',
    // 携带的参数会以url格式传到服务器，信息头我们设置为url编码，utf8编码
    header: {'content-type': 'application/x-www-form-urlencoded'},
       success: function (res) {
      console.log(res.data.assets)
      console.log(res.data.benefitsSum)
      console.log(res.data.benefitsYesterday)
     var myasset=res.data.assets
     var sumasset=res.data.benefitsSum
     var yester=res.data.benefitsYesterday
     that.setData({
      myasset:myasset,
      yester:yester,
      sumasset:sumasset
    })
     }
    })
  },

  onShow: function () {

  },




  onSubmit: function(e){
	var amount=e.detail.value.money
	if (amount < 50000 && amount <= this.data.myasset)
	{
		console.log(amount)
		var tempid=wx.getStorageSync('id')
		var flag=true;
		console.log(tempid)
		wx.request({
			url: serverUrl + '/invest/add',
		data: {
			identity:tempid,
			amount:amount,
			confirmed:flag
		}, 
		method: 'POST',
		header: {'content-type': 'application/json;charset=UTF-8'},
		success: function (res) {
			console.log(res.data)
			wx.showToast({
			title: '追加投资成功',
			icon: 'success',
			duration: 1000
			})
			setTimeout(function(){
			wx.switchTab({
				url: '../invest/invest'
			})  
			},1000)
		},
		})
	}
	else
	{
		Dialog.alert({
			context : this,
			selector:"#van-dialog",
			message: '操作存在风险，您的账户已被冻结',
		}).then(() => {
			wx.reLaunch({
			  url: '../homepage/homepage',
			})
		});
	}

	}	

})