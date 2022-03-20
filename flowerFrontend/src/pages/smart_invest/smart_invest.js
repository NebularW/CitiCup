// pages/smart_invest/smart_invest.js
Page({

  data: {

  },

  onLoad: function (options) {

  },

  onShow: function () {

  },

  toquestionnare: function (options) {
    wx.navigateTo({
          url: '../questionnare/questionnare'
    })  
  },

  torecommendation: function (options) {
    wx.navigateTo({
          url: '../anticipate_result/anticipate_result'
    })  
  }

})