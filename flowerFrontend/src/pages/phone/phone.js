// pages/addsign/addsign.js
var util = require('../../utils/util');
const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({
  /**
   * 页面的初始数据
   */
  data: {
    globalid: [],
    array: ['男', '女'],
    sex: 0,
    phone: '',
    show: false,
	currentChoose: '',
	name : ''
  },
  //日期选择器的处理
  openPicker() {
    this.setData({
      show: true
    })
  },
  onConfirm(e) {
    this.setData({
      show: false,
      currentChoose: this.formatDate(new Date(e.detail))
    })
  },
  onClose() {
    this.setData({
      show: false
    })
  },
  onCancel() {
    this.setData({
      show: false
    })
  },

  //男女
  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      sex: e.detail.value
    })
    console.log(e.detail.value)
    wx.setStorageSync('sex', e.detail.value)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // 调用函数时，传入new Date()参数，返回值是日期和时间
    var time = util.formatTime(new Date());
    this.setData({
      currentChoose: time
    });
    wx.setStorageSync('sex', 0)
  },
  setInput: function(e) {
    console.log(e.detail.value)
	wx.setStorageSync('phone', e.detail.value)
  },
  setInput2: function(e) {
    console.log(e.detail.value)
	this.setData({
		name : e.detail.value
	})
  },
  confirmPublish: function() {
      var that = this;
      if (wx.getStorageSync('phone').length==0) {
        console.log("手机号为空")
        wx.showToast({
          title: '手机号不能为空',
          icon: 'none',
          image: '',
          duration: 1000
        })
      }
      else if (wx.getStorageSync('phone').length != 11) {
        console.log("长度问题")
        wx.showToast({
          title: '手机号长度有误',
          icon: 'none',
          image: '',
          duration: 1000
        })
        return false;
      }
      
      else {
        var tempsex=wx.getStorageSync('sex')
        var tempphone=wx.getStorageSync('phone')
        var tempname=that.data.name
        var tempimage=wx.getStorageSync('userimage')
        var tempid=wx.getStorageSync('id')
        console.log(tempid)
        console.log(tempname)
        console.log(tempsex)
        console.log(tempphone)
        wx.request({
          url: serverUrl + '/user/register',
          data: {
            identity:tempid,
            userName:tempname,
            avatarUrl:tempimage,
            gender:tempsex,
            phone:tempphone
          }, 
          method: 'POST',
        // 携带的参数会以url格式传到服务器，信息头我们设置为url编码，utf8编码
        header: {'content-type': 'application/json;charset=UTF-8'},
           success: function (res) {
          console.log(res.data)
          console.log("成功")
          wx.switchTab({
            url: '../homepage/homepage',
          })
          },
          fail:function(err){
            console.log("失败")
            wx.switchTab({
              url: '../homepage/homepage',
            })
        }
        })
      }
    // const data = {}
    // data.sex = this.data.sex
    // data.phone = this.data.phone
    // console.log(data.sex)
    // console.log(data.phone)
    // wx.setStorageSync('phone',data.phone)
    // let temp=wx.getStorageSync('phone')
    // console.log("缓存"+temp)
    // console.log(JSON.stringify(data))
 
 
    // wx.request({
    //   url: 'http://192.xxx.4.1xx:8093/cs-applet/subscribe/addCallerSubscribe',
    //   method: 'POST',
    //   data: data,
    //   header: {
    //     'content-type': 'application/json',
    //   },
    //   success: function(res) {
 
    //   },
    //   fail: function(error) {
    //     wx.showToast({
    //       title: error.message || '保存失败'
    //     })
    //     console.log(error)
    //   }
    // })
  },

  bindViewTapsuccess: function(opotions){
    wx.switchTab({
      url: '../homepage/homepage'
    })
	},
})