const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array:[],
    myasset:'',
    yester:'',
    sumasset:'',
    fundA1:"0",
    fundA2:"0",
    fundA3:"0",
    fundA4:"0",
    fundA5:"0",
    fundB1:"0",
    fundB2:"0",
    fundB3:"0",
    fundB4:"0",
    fundB5:"0"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this
      var tempid=wx.getStorageSync('id')
      console.log(tempid)
			wx.request({
				url: serverUrl + '/asset/financial',
				data: {
					identity:tempid,
				}, 
				method: 'POST',
			// 携带的参数会以url格式传到服务器，信息头我们设置为url编码，utf8编码
			header: {'content-type': 'application/x-www-form-urlencoded'},
				 success: function (res) {
            var myasset=res.data.assets.toFixed(2)
            var sumasset=res.data.benefitsSum.toFixed(2)
            var yester=res.data.benefitsYesterday.toFixed(2)
            that.setData({
              myasset:myasset,
              yester:yester,
              sumasset:sumasset
            })
        }
      })
      var size=wx.getStorageSync('size')
      wx.request({
				url: serverUrl + '/asset/homepage',
				data: {
					identity:tempid,
				}, 
				method: 'POST',
			  header: {'content-type': 'application/x-www-form-urlencoded'},
				 success: function (res) {
            console.log(res.data)
            console.log(res.data.investedThingsList)
            console.log(res.data.investedThingsList[1].fundName)
          for(var i=0;i<size;i++)
       {
        if(res.data.investedThingsList[i].fundName=='南方金利A')
        that.setData({
          fundA1:"1"
        })
        if(res.data.investedThingsList[i].fundName=='泰信增强收益A')
        that.setData({
          fund2:"1"
        })
        if(res.data.investedThingsList[i].fundName=='华安日日鑫货币H')
        that.setData({
          fundA3:"1"
        })
        if(res.data.investedThingsList[i].fundName=='海富通上证城投债ETF')
        that.setData({
          fundA4:"1"
        })
        if(res.data.investedThingsList[i].fundName=='国泰上证5年期国债ETF')
        that.setData({
          fundA5:"1"
        })
        if(res.data.investedThingsList[i].fundName=='富国中证煤炭A')
        that.setData({
          fundB1:"1"
        })
        if(res.data.investedThingsList[i].fundName=='嘉实原油')
        that.setData({
          fundB2:"1"
        })
        if(res.data.investedThingsList[i].fundName=='工银瑞信生态环境')
        that.setData({
          fundB3:"1"
        })
        if(res.data.investedThingsList[i].fundName=='华安创业板50ETF')
        that.setData({
          fundB4:"1"
        })
        if(res.data.investedThingsList[i].fundName=='万家上证50ETF')
        that.setData({
          fundB5:"1"
        })
      }
       }
      })
    
  },


  onShow: function () {
	this.getTabBar().init();
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

  complete :function(){
    wx.navigateTo({
      url: '../add_invest/add_invest',
    })
  },

  another :function(){
    wx.navigateTo({
      url: '../sell_invest/sell_invest',
    })
  },

  FA1: function (options) {
    wx.showModal({
      title: '南方金利A',
      content: '21铁投01 6.66%, 20际华01 6.65%, 19长城 6.59%, 19广发 6.59%, 21国君 6.59%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FA2: function (options) {
    wx.showModal({
      title: '泰信增强收益A',
      content: '21附息 52.55%, 21附息 26.45%, 21进出 7.84%, 20附息 5.24%, 19国开 5.23%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FA3: function (options) {
    wx.showModal({
      title: '华安日日鑫货币H',
      content: '21南京银行CD109 3.09%, 21宁波银行CD358 1.84%, 21民生银行CD370 1.22%, 21渤海银行CD491 1.22%, 21徽商银行CD159 1.10%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FA4: function (options) {
    wx.showModal({
      title: '海富通上证城投债ETF',
      content: '19南建02 1.81%, 19武管廊 1.63%, 17苏新02 1.51%, 20明城02 1.51%, 19椒江01 1.37%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FA5: function (options) {
    wx.showModal({
      title: '国泰上证5年期国债ETF',
      content: '21附息国债11 26.85%, 21国债11 24.02%, 19附息国债16 21.89%, 19国债16 13.07%, 21国开03 5.14%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FB1: function (options) {
    wx.showModal({
      title: '富国中证煤炭A',
      content: '美锦能源 9.72%, 中国神华 9.60%, 永泰能源 9.31%, 陕西煤业 8.76%, 兖矿能源 5.57%, 山西焦煤 4.49%, 华阳股份 3.78%, 潞安环能 3.59%, 中煤能源 3.05%, 电投能源 3.03%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FB2: function (options) {
    wx.showModal({
      title: '嘉实原油',
      content: '货币基金 100.00%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FB3: function (options) {
    wx.showModal({
      title: '工银瑞信生态环境',
      content: '杉杉股份 4.68%, 天合光能 4.56%, 天齐锂业 4.51%, 诺德股份 4.03%, 鹏辉能源 3.65%, 宁德时代 3.35%, 捷佳伟创 3.18%, 德业股份 3.00%, 晶澳科技 2.93%, 阳光电源 2.60%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FB4: function (options) {
    wx.showModal({
      title: '华安创业板50ETF',
      content: '宁德时代 22.20%, 东方财富 9.62%, 迈瑞医疗 5.26%, 亿纬锂能 4.81%, 阳光电源 4.61%, 汇川技术 3.86%, 爱尔眼科 3.17%, 智飞生物 2.96%, 沃森生物 2.93%, 先导智能 2.42%',
      success: function (res) {
        if (res.confirm) {//这里是点击了确定以后
          console.log('用户点击确定')
        } else {//这里是点击了取消以后
          console.log('用户点击取消')
        }
      }
    })  
  },

  FB5: function (options) {
    wx.showModal({
      title: '万家上证50ETF',
      content: '贵州茅台 15.33%, 招商银行 7.40%, 中国平安 6.69%, 隆基股份 4.59%, 兴业银行 3.39%, 长江电力 3.16%, 伊利股份 3.11%, 药明康德 3.00%, 药明康德 2.77%, 中信证券 2.75%',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
        } else {
          console.log('用户点击取消')
        }
      }
    })  
  }
})
