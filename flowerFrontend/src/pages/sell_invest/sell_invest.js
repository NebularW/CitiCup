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

  },

  onSubmit: function(e){
    var amount=e.detail.value.money
    console.log(amount)
    if(amount>=20000)
    {
    //   wx.showToast({
    //     title: '超出总资产',
    //     icon: 'error',
    //     duration: 1000
    //   })
    //   setTimeout(function(){
    //     wx.navigateTo({
    //       url: '../sell_invest/sell_invest'
    //     })  
	//   },1000)
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
    else{
      var tempid=wx.getStorageSync('id')
      var flag=true;
      console.log(tempid)
      wx.request({
        url: serverUrl + '/invest/sell',
        data: {
          identity:tempid,
          amount:amount
        }, 
        method: 'POST',
        header: {'content-type': 'application/json;charset=UTF-8'},
        success: function (res) {
          console.log(res.data)
          wx.showToast({
            title: '卖出成功',
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
  }
})