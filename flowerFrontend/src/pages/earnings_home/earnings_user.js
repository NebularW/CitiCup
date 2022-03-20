var util = require('../../utils/util');
var date = util.formatDate(new Date());
const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({
  data: {
	tarOpen_ID:null,
    date: date,
    show: false,
    minDate: new Date(2022, 0, 1).getTime(),
    maxDate: new Date(2022, 12, 31).getTime(),
    total_income: '0.00',
    open_ID: wx.getStorageSync('id'),
    fundsBenefit: [],
    records: [],
    sale: '买入',

    // recordFundName: [1, 2],
    // recordStatus: [0, 0],
    // recordCategory: ["股票", ],
    // recordAmount: [0, 1],

    // benefitFundName: null,
    // benefitCategory: "股票",
    // benefit: 0,
  },
  onLoad: function () {
	var that = this
	var Pages = getCurrentPages();
	var prevPage = Pages[Pages.length - 2];
	var serverUrl = app.globalData.serverUrl
	this.setData({
		tarOpen_ID : prevPage.data.tarOpen_ID
	})
    var tmp = this.formatDate(this.data.date).split("/")
    var timeStamp = new Date().getFullYear()
    tmp[0] = tmp[0].length == 1 ? "0" + tmp[0] : tmp[0]
    timeStamp = timeStamp + "-" + tmp[0] + "-" + tmp[1]
    console.log(timeStamp)
    wx.request({
      url: serverUrl + '/asset/history',
      data: {
        identity: that.data.tarOpen_ID,
        timestamp: new Date(timeStamp),
      },
      method: 'POST',
      header: {
        'content-type': 'application/json;charset=utf-8',
      },

      success: function (res) {
        console.log(new Date(date))
        console.log('成功')
        that.setData({
          total_income: res.data[0].benefitsDaySum,
          fundsBenefit: res.data[0].investedThingsDailyFundBenefits,
          records: res.data[0].investedThingsRecordList,
		})
        // console.log(that.data.records)
        // var len = that.data.records.length
        // that.data.recordFundName = new Array(len)
        // that.data.recordAmount = new Array(len)
        // that.data.recordCategory = new Array(len)
        // that.data.recordStatus = new Array(len)
        // for (var i = 0; i < len; ++i) {
        //   that.data.sale[i] = that.data.records[i].status==0?'买入':'卖出'
        // }
        console.log(that.data)
      },
      fail: function (err) {
        console.log('失败！')
      }
    })
	
  },


  onShow: function () {

  },
  onDisplay() {
    this.setData({
      date: date,
      show: true
    });
  },
  onClose() {
    this.setData({
      show: false
    });
  },
  formatDate(date) {
    date = new Date(date);
    return `${date.getMonth() + 1}/${date.getDate()}`;
  },
  onConfirm(event) {
    this.setData({
      show: false,
      date: this.formatDate(event.detail),
    });
    var that = this
    var timeStamp = new Date().getFullYear()
    var tmp = this.data.date.split("/")
    tmp[0] = tmp[0].length == 1 ? "0" + tmp[0] : tmp[0]
    timeStamp = timeStamp + "-" + tmp[0] + "-" + tmp[1]
    console.log(timeStamp)
    wx.request({
      url: serverUrl + '/asset/history',
      data: {
        identity: that.data.open_ID,
        timestamp: new Date(timeStamp),
      },
      method: 'POST',
      header: {
        'content-type': 'application/json;charset=utf-8',
      },

      success: function (res) {
        console.log('成功')
        that.setData({
          total_income: res.data[0].benefitsDaySum.toFixed(2),
          fundsBenefit: res.data[0].investedThingsDailyFundBenefits,
          records: res.data[0].investedThingsRecordList,
        })

        console.log(that.data)
      },
      fail: function (err) {
        console.log('失败！')
      }
    })
  },


});