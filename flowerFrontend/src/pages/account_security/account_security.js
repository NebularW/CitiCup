const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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

  to_bank: function(opotions){
    var identity=wx.getStorageSync('id')
    console.log(identity)
    wx.request({
      url: serverUrl + '/user/hascard',
      data: {
        identity:identity,
      }, 
      method: 'POST',
    // 携带的参数会以url格式传到服务器，信息头我们设置为url编码，utf8编码
    header: {
      'content-type': 'application/json;charset=utf-8' 
    },
       success: function (res) {
         if(res.data[0]==null){
          wx.navigateTo({
            url: '../bind_bankcard/bind_bankcard',
          })
         }
         else{
         wx.navigateTo({
           url: '../display_card/display_card',
         })
        }
       }
      })
	},
})