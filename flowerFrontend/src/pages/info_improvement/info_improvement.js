// pages/info_improvement/info_improvement.js
// pages/yaoxh6/item/item.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentFatherIndex: 0,
    questionnaireArray: [
      {
        "type": "SAQ",
        "content": {
          "description": "您的退休年份?",
          "answer": ""
        }
      },
      {
        "type": "SAQ",
        "content": {
          "description": "您的退休年龄?",
          "answer": ""
        }
      },
      {
        "type": "SAQ",
        "content": {
          "description": "您在产品上的花费占您总工资的？（如0.32）",
          "answer": ""
        }
      },
      {
        "type": "SAQ",
        "content": {
          "description": "您希望您的退休养老金占工资的？（如0.71）",
          "answer": ""
        }
      }
    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.id)
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
  // fun : function(){
  //   var q = {
  //     test: this.data.test,
  //     test2: this.data.test2
  //   }
  //   wx.cloud.callFunction({
  //     name: 'release_questionnaire',
  //     data: {
  //       content: JSON.stringify(q)
  //     },
  //     success: res => {
  //       // test = JSON.stringify(res)
  //       // this.setData({
  //       //   test : JSON.stringify(res.result.results.data[0].description)
  //       // })
  //       console.log('success')
  //     }
  //   })
  // },

  // fun2 : function(){
  //   wx.cloud.callFunction({
  //     name: 'get_all_questionnaire',
  //     success: res => {
  //       console.log(res)
  //       var last = res.result.results.data[8].content
  //       this.setData({
  //         test: JSON.parse(last).test
  //       })
  //       console.log('success')
  //     }
  //   })
  // }
  goBack : function(){
    console.log('to task page')
    wx.switchTab({
      url: '../task/task',
    })
  },

  getTempFatherIndex: function (input) {
    var tempFatherIndex = input.currentTarget.dataset.id;
    //console.log('currentFatherIndex: ' + tempFatherIndex);
    this.setData({
      currentFatherIndex: tempFatherIndex,
    });
  },
  
  radioChangeSCQ:function(input){
    var tempFatherIndex = this.data.currentFatherIndex;
    var tempArray = this.data.questionnaireArray;
    for (var i in tempArray[tempFatherIndex].content.options){
      if (tempArray[tempFatherIndex].content.options[i].name == input.detail.value){
        tempArray[tempFatherIndex].content.options[i].isSelected = true;
      }
      else{
        tempArray[tempFatherIndex].content.options[i].isSelected = false;
      }
    }
    this.setData({
      questionnaireArray: tempArray,
    });
  },

  checkboxChangeMCQ:function(input){
    // console.log(input.detail.value);
    var flag = false;
    var tempFatherIndex = this.data.currentFatherIndex;
    var tempArray = this.data.questionnaireArray;
    for (var i in tempArray[tempFatherIndex].content.options) {
      flag = false;
      for(var j in input.detail.value){
        if (tempArray[tempFatherIndex].content.options[i].name == input.detail.value[j]){
          flag = true;
        }
      }
      if(flag == true){
        tempArray[tempFatherIndex].content.options[i].isSelected = true;
      }
      else{
        tempArray[tempFatherIndex].content.options[i].isSelected = false;
      }
    }
    this.setData({
      questionnaireArray: tempArray,
    });
  },

  bindblurAnswerOfSAQ: function (input) {
    var tempIndex = input.currentTarget.dataset.id;
    var tempArray = this.data.questionnaireArray;
    tempArray[tempIndex].content.answer = input.detail.value;
    // console.log(tempArray[tempIndex].content);
    this.setData({
      questionnaireArray: tempArray,
    });
  },


  complete :function(){
    console.log(this.data.questionnaireArray[0].content.answer);
    console.log(this.data.questionnaireArray[1].content.answer);
    console.log(this.data.questionnaireArray[2].content.answer);
    console.log(this.data.questionnaireArray[3].content.answer);
    var sex=wx.getStorageSync('sex')
    var alpha=wx.getStorageSync('alpha')
    var beta=wx.getStorageSync('beta')
    var retire_year=this.data.questionnaireArray[0].content.answer
    var M=this.data.questionnaireArray[1].content.answer
    var c=this.data.questionnaireArray[2].content.answer
    var s=this.data.questionnaireArray[3].content.answer
    console.log(alpha)
    console.log(beta)
    console.log(retire_year)
    console.log(c)
    if(sex==0) sex='Male'
    else sex='Female'
    console.log(sex)
    console.log(M)
    console.log(s)
    wx.request({
      url: 'http://47.113.191.64:9001/model/suggest',
      data: {
        alpha:alpha,
        beta:beta,
        retire_year:retire_year,
        c:c,
        gender:sex,
        M:M,
        s:s
      }, 
      method: 'POST',
      header: {'content-type': 'application/json;charset=UTF-8'},
       success: function (res) {
         console.log(res)
         console.log(res.data.length)
         wx.setStorageSync('size', res.data.length)
         console.log(res.data[0].fundName)
         console.log("success!")
         var weight=new Array()
         var list=new Array()
         for(var i=0;i<res.data.length;i++)
         {
           weight.push(res.data[i].weight)
            list.push(res.data[i].fundName)
         }
         console.log(list)
         wx.setStorageSync('list', list)
         wx.setStorageSync('weight', weight)
         wx.request({
          url: 'http://47.113.191.64:9001/model/glide',
          data: {
            alpha:alpha,
            beta:beta,
            retire_year:retire_year,
            c:c,
            gender:sex,
            M:M,
            s:s
          }, 
          method: 'POST',
          header: {'content-type': 'application/json;charset=UTF-8'},
           success: function (res) {
             var code=new Array();
             console.log(res.data)
             for(var i=0;i<=9;i++)
             {
                code.push(res.data[i]*100)
             }
             wx.setStorageSync('array', code)
             console.log(code)
           }
        })

      }
    })
    wx.redirectTo({
      url: '../anticipate_result/anticipate_result',
    })
  }
})
