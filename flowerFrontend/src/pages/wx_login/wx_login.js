// pages/wx_login/wx_login.js

const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl
Page({
  data:{
  },

  onLoad(){
  },


  login(){
    var that = this
    var identity='';
    var userName='';
    var avatarUrl=''
    wx.getUserProfile({
      desc: '必须授权才能使用',
      success:res=>{
        let user=res.userInfo
        console.log('成功',user)
        userName=user.nickName;
        avatarUrl=user.avatarUrl;      
        console.log(userName)
        console.log(avatarUrl)
              wx.login({
                success (res) {
                  if (res.code) {
          wx.request({
               url: 'https://api.weixin.qq.com/sns/jscode2session?appid=wx1aa63d33e9daf0e6&secret=71d7bed7695d61d4f896230701f4a094&js_code=' + res.code + '&grant_type=authorization_code',
               success: res => {
                // 获取到用户的 openid
                console.log("用户的openid:" + res.data.openid);
                identity=res.data.openid
                    console.log("1"+identity)
                    wx.setStorageSync('id', identity)
                    wx.setStorageSync('username', userName)
                    wx.setStorageSync('userimage', avatarUrl)
                    console.log('2'+userName)
                    console.log('3'+avatarUrl)
                    wx.request({
                      url: serverUrl + '/user/login',
                      data: {
                        identity:identity,
                        userName:userName,
                        avatarUrl:avatarUrl
                      }, 
                      method: 'POST',

                    // 携带的参数会以url格式传到服务器，信息头我们设置为url编码，utf8编码
                    header: {
                      'content-type': 'application/json;charset=utf-8' 
                    },
                       success: function (res) {
                      console.log(res.data)
                      if(res.data.flag==false)
                      {
                        console.log("输入手机号")
                        wx.navigateTo({
                          url:"../phone/phone"
                          })
                      }
                      else{
                        wx.switchTab({
                          url: '../homepage/homepage',
                        })
                      }
                      },
                      fail:function(err){
                        console.log("失败")
                    }
                    })

              }
            })  
                  } else {
                    console.log('登录失败！' + res.errMsg)
                  }
                }
              })
    }
  })
}
})

