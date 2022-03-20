// pages/anticipate_result/anticipate_result.js
var wxCharts = require('../../utils/wxcharts.js');
var lineChart = null;
Page({
  data:{
    textcolor1:'#014f8e',
    textcolor2:'#bfbfbf',
    riskdata:'',
    timedata:''
  },
  onLoad:function(){
    var riskdata=wx.getStorageSync('alpha')
    var timedata=wx.getStorageSync('beta')
    console.log(riskdata)
    console.log(timedata)
    if(timedata>0.922&&timedata<=0.935)
    this.setData({
      timedata:"强时间偏好者"
    })
    if(timedata>0.935&&timedata<=0.949)
    this.setData({
      timedata:"中等时间偏好者"
    })
    if(timedata>0.949&&timedata<=0.962)
    this.setData({
      timedata:"弱时间偏好者"
    })
    if(riskdata>=17&&riskdata<=42)
    this.setData({
      riskdata:"保守型"
    })
    if(riskdata>=43&&riskdata<=55)
    this.setData({
      riskdata:"中庸保守型"
    })
    if(riskdata>=56&&riskdata<=70)
    this.setData({
      riskdata:"中庸型"
    })
    if(riskdata>=71&&riskdata<=84)
    this.setData({
      riskdata:"中庸进取型"
    })
    if(riskdata>=85&&riskdata<=95)
    this.setData({
      riskdata:"进取型"
    })
    //下面是图表一显示的数据，只需替换掉数据折现就会发生变化实现动态生成
    var x_data=["2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031"]
    var y_data= wx.getStorageSync('array')
    //绘制折线图
    this.OnWxChart(x_data,y_data)
  },
  //图表点击事件
  touchcanvas:function(e){
    lineChart.showToolTip(e, {
      format: function (item, category) {
        return category + ' ' + item.name + ':' + item.data
      }
    });
  },

  onSubmit: function(e){
    var amount=e.detail.value.money
    wx.setStorageSync('investmoney', amount)
    console.log(amount)
    var tempid=wx.getStorageSync('id')
    var flag=true;
    console.log(tempid)
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 1000
        })
        setTimeout(function(){
          wx.navigateTo({
            url: '../recommendation/recommendation'
          })  
        },1000)
  },


  //折线图绘制方法
  OnWxChart:function(x_data,y_data,name){
    var windowWidth = '', windowHeight='';    //定义宽高
    try {
      var res = wx.getSystemInfoSync();    //试图获取屏幕宽高数据
      windowWidth = res.windowWidth / 750 * 690;   //以设计图750为主进行比例算换
      windowHeight = res.windowWidth / 1000 * 500    //以设计图750为主进行比例算换
    } catch (e) {
      console.error('getSystemInfoSync failed!');   //如果获取失败
    }
    lineChart = new wxCharts({
      canvasId: 'lineCanvas',     //输入wxml中canvas的id
      type: 'line',     
      categories:x_data,    //模拟的x轴横坐标参数
      animation: true,  //是否开启动画
     
      series: [{
        name: "高风险资产占比",
        data: y_data,
        format: function (val, name) {
          return val + '%';
        }
      }
      ],
      xAxis: {   //是否隐藏x轴分割线
        disableGrid: true,
      },
      yAxis: {      //y轴数据
        title: '',  //标题
        format: function (val) {  //返回数值
          return val.toFixed(2);
        },
        min: 400000.00,   //最小值
        gridColor: '#D8D8D8',
      },
      width: windowWidth*1.1,  //图表展示内容宽度
      height: windowHeight,  //图表展示内容高度
      dataLabel: false,  //是否在图表上直接显示数据
      dataPointShape: true, //是否在图标上显示数据点标志
      extra: {
        lineStyle: 'Broken'  //曲线
      },
    });
  }
})