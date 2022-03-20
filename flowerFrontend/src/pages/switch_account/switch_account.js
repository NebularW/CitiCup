const app = getApp()
var serverUrl = app.globalData.serverUrl
Page({


  data: {
    //表单绑定值，小程序为单向数据绑定 只可js->wxml,不可wxml->js
    chosen: ''
  },
  //登陆方法
  formSubmit(e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value), //小程序表单自带API，e.detail.value即为表单值
    //触发登陆请求
      wx.request({
        method:"POST", //指定为http协议中的POST方法
        url: serverUrl + '/user/login', //后端接口完整URL
        data: {
          name: e.detail.value.name, //将表单中name的值绑定给对象的name属性
          phone: e.detail.value.phone,
          pwd: e.detail.value.pwd  //将表单中pwd的值绑定给对象的pwd属性

        },
        header: {
          'content-type': 'application/json' // 设置传输格式为json格式，默认如此
        },
        success(res) {
          console.log(res.data)
          //如果返回值的code为0 提示登陆成功，否则提示登陆失败
          if(res.data.code == 0)
          { 
            wx.showToast({
              title: '登陆成功',

              duration: 1000
            })

          }
          else
          {
            wx.showToast({
              title: '登陆失败',
              duration: 1000
            })
          }
     
        }
      })
  
    
  },

  formReset(e) {
    console.log('form发生了reset事件，携带数据为：', e.detail.value)
    //通过setData方法清空表单
    this.setData({
      chosen: ''
    })

  },
  register()
  {
    console.log("register"),
    //通过小程序自带路由跳转API进行页面跳转
      wx.redirectTo({
      url: '../homepage/homepage', //注意url为相对路径
      })


  }
})
