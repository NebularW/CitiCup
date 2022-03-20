// app.js
App({

  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
	})
	
  },
	globalData: {
		pattern : true,
		// serverUrl : "http://localhost:9001"
		serverUrl : "http://47.113.191.64:9001"
	}
})
