// pages/time/time.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentFatherIndex: 0,
    questionnaireArray: [
      {
        "type": "SCQ",
        "content": {
          "description": "假设您今天进行了1000元的投资，您希望何时获得何种收益？",
          "options":
            [
              { "id": 1, "name": "A:三个月后获得1010元", "isSelected": false },
              { "id": 2, "name": "B:半年后获得1025元", "isSelected": false },
              { "id": 3, "name": "C:一年后获得1060元", "isSelected": false },
              { "id": 4, "name": "D:两年后获得1145元", "isSelected": false },
              { "id": 5, "name": "E:三年后获得1260元", "isSelected": false }
            ]
        }
      },
      {
        "type": "SCQ",
        "content": {
          "description": "您认为今天的1000元与一年后的1060元哪个更有价值？",
          "options":
            [
              { "id": 1, "name": "A:今天的1000元", "isSelected": false },
              { "id": 2, "name": "B:一年后的1060元", "isSelected": false }
            ]
        }
      },
      {
        "type": "SCQ",
        "content": {
          "description": "您认为今天的1000元与一年后的1040元哪个更有价值？",
          "options":
            [
              { "id": 1, "name": "A:今天的1000元", "isSelected": false },
              { "id": 2, "name": "B:一年后的1040元", "isSelected": false }
            ]
        }
      },
      {
        "type": "SCQ",
        "content": {
          "description": "您认为今天的1000元与一年后的1080元哪个更有价值？",
          "options":
            [
              { "id": 1, "name": "A:今天的1000元", "isSelected": false },
              { "id": 2, "name": "B:一年后的1080元", "isSelected": false }
            ]
        }
      },
      {
        "type": "SCQ",
        "content": {
          "description": "您认为今天的1000元与一年后的1050元哪个更有价值？",
          "options":
            [
              { "id": 1, "name": "A:今天的1000元", "isSelected": false },
              { "id": 2, "name": "B:一年后的1050元", "isSelected": false }
            ]
        }
      },
      {
        "type": "SCQ",
        "content": {
          "description": "您认为今天的1000元与一年后的1070元哪个更有价值？",
          "options":
            [
              { "id": 1, "name": "A:今天的1000元", "isSelected": false },
              { "id": 2, "name": "B:一年后的1070元", "isSelected": false }
            ]
        }
      }
    ],
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
    this.setData({
      questionnaireArray: tempArray,
    });
  },

  complete :function(){
    var arr=this.data.questionnaireArray;
    var a=0.0,b=0.0,c=0.0;
    if(arr[0].content.options[0].isSelected==true) a=0.9615384615
    if(arr[0].content.options[1].isSelected==true) a=0.9523809524
    if(arr[0].content.options[2].isSelected==true) a=0.9433962264
    if(arr[0].content.options[3].isSelected==true) a=0.9345794393
    if(arr[0].content.options[4].isSelected==true) a=0.9259259259
    if(arr[2].content.options[1].isSelected==true) b=0.9615384615
    if(b==0&&arr[3].content.options[0].isSelected==true) b=0.9259259259
    if(b==0&&arr[4].content.options[0].isSelected==true) b=0.9478885894
    if(b==0&&arr[4].content.options[1].isSelected==true) b=0.956959707
    if(b==0&&arr[5].content.options[0].isSelected==true) b=0.9302526826
    if(b==0&&arr[5].content.options[1].isSelected==true) b=0.9389878328
    var c=(a+b)/2
    if(c<=0.935&&c>0.922) console.log("强时间偏好者，更喜欢及时收益")
    if(c>0.935&&c<=0.949) console.log("中等时间偏好者")
    if(c>0.949&&c<=0.962) console.log("弱时间偏好者，更喜欢未来收益")
    wx.setStorageSync('beta',c)
    wx.redirectTo({
      url: '../questionnare/questionnare',
    })
  },
})
