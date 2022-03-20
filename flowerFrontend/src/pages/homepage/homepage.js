// pages/homepage/homepage.js
var wxCharts = require('../../utils/wxcharts.js');
const app = getApp()
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
var serverUrl = app.globalData.serverUrl
var lineChart = null;
var pieChart = null;
Page({
	data: {
		myasset: '',
		yester: '',
		sumasset: '',
		hasInvestment: true,
		open_ID: null,
		show: true,
		isfrozen: false,
		riskActivities: [],
		redNum: 0,
		investList: []
	},

	accept(e) {
		var that = this
		console.log(e.currentTarget.dataset.index)
		wx.request({
			url: serverUrl + '/risk/response',
			data: {
				fromIdentity: that.data.open_ID,
				toIdentity: that.data.riskActivities[e.currentTarget.dataset.index].identity,
				actionType: 1
			},
			method: "POST",
			header: {
				'Content-Type': 'application/json;charset=utf-8'
			},
			success: function (res) {
				console.log("获取成功")
				console.log("66666666")
			},
			fail: function (res) {
				console.log("获取失败")
				console.log(res)
			}
		})
	},

	reject(e) {
		var that = this
		console.log(e.currentTarget.dataset.index)
		wx.request({
			url: serverUrl + '/risk/response',
			data: {
				fromIdentity: that.data.open_ID,
				toIdentity: that.data.riskActivities[e.currentTarget.dataset.index].identity,
				actionType: 0
			},
			method: "POST",
			header: {
				'Content-Type': 'application/json;charset=utf-8'
			},
			success: function (res) {
				console.log("获取成功")
				console.log("66666666")
			},
			fail: function (res) {
				console.log("获取失败")
				console.log(res)
			}
		})
	},

	onLoad: function (e) {
		var that = this
		var tempid = wx.getStorageSync('id')
		that.data.open_ID = wx.getStorageSync('id')
		wx.request({
			url: serverUrl + '/risk/process',
			data: {
				identity: that.data.open_ID,
			},
			method: "GET",
			header: {
				'Content-Type': 'text/plain;charset:utf-8;'
			},

			success: function (res) {
				console.log("获取风险信息成功")
				console.log(res)
				that.setData({
					isfrozen: res.data.frozen,
					redNum: res.data.redNum,
					riskActivities: res.data.riskActVoList
				})

				console.log(res)
				if (that.data.isfrozen) {
					console.log(that.data)
					Dialog.alert({
						context: this,
						selector: "#van-dialog",
						message: '您的账户已被冻结，请等待三天自动解冻',
					}).then(() => {
						wx.exitMiniProgram()
					});
				} else {
					// that.data.redNum = 2
					// that.data.riskActivities=[1,2]
					// console.log(that.data)
				}
			},
			fail: function (res) {
				console.log("获取授权关系失败")
				console.log(res)
			}
		})
		wx.request({
			url: serverUrl + '/asset/financial',
			data: {
				identity: tempid,
			},
			method: 'POST',
			// 携带的参数会以url格式传到服务器，信息头我们设置为url编码，utf8编码
			header: {
				'content-type': 'application/x-www-form-urlencoded'
			},
			success: function (res) {
				console.log(res.data.assets)
				console.log(res.data.benefitsSum)
				console.log(res.data.benefitsYesterday)
				console.log(res.data.investedThingsList)
				var myasset = res.data.assets
				var sumasset = res.data.benefitsSum
				var yester = res.data.benefitsYesterday.toFixed(2)
				var investList = res.data.investedThingsList
				console.log(investList.length)
				that.setData({
					myasset: myasset,
					yester: yester,
					sumasset: sumasset,
					investList: investList,
				})
				
				if (investList.length == 0) {
					that.setData({
						hasInvestment: false
					})
				} else {
					that.setData({
						hasInvestment: true
					})
				}
				console.log(that.data.hasInvestment)
			}
		})
		
		//绘图
		var windowWidth = 360;
		try {
			var res = wx.getSystemInfoSync();
			windowWidth = res.windowWidth;
		} catch (e) {
			console.error('getSystemInfoSync failed!');
		}

		pieChart = new wxCharts({
			animation: true,
			canvasId: 'pieCanvas',
			type: 'pie',
			series: [{
				name: '南方金利A',
				data: 10000,
			}, {
				name: '国泰上证',
				data: 3500,
			}, {
				name: '嘉实原油',
				data: 8000,
			}, ],
			// series: [{
			// 	name: '工银瑞信',
			// 	data: 2000,
			// }, {
			// 	name: '国泰上证',
			// 	data: 6000,
			// }, {
			// 	name: '万家上证',
			// 	data: 8000,
			// }, ],
			width: windowWidth,
			height: 140,
			dataLabel: false,
		});
		var simulationData = this.createSimulationData();
		lineChart = new wxCharts({
			canvasId: 'lineCanvas',
			type: 'line',
			categories: simulationData.categories,
			animation: true,
			// background: '#f5f5f5',
			series: [{
				name: '股票',
				data: simulationData.data,
				format: function (val, name) {
					return val.toFixed(2) + '万';
				}
			}, {
				name: '债券',
				data: [2, 0, 0, 3, 5, 4, 4, 5, 6, 5],
				format: function (val, name) {
					return val.toFixed(2) + '万';
				}
			}],
			xAxis: {
				disableGrid: true,
				disabled:true
			},
			yAxis: {
				title: '收益金额 (万元)',
				format: function (val) {
					return val.toFixed(2);
				},
				min: 0
			},
			width: windowWidth,
			height: 140,
			dataLabel: false,
			dataPointShape: true,
			extra: {
				lineStyle: 'curve'
			}
		});
		console.log("##############")
		console.log(this.data.hasInvestment)
	},

	onShow: function (e) {
		this.getTabBar().init();
		var that = this
		var windowWidth = 320;
		try {
			var res = wx.getSystemInfoSync();
			windowWidth = res.windowWidth;
		} catch (e) {
			console.error('getSystemInfoSync failed!');
		}

		that.data.open_ID = wx.getStorageSync('id')
		wx.request({
			url: serverUrl + '/risk/process',
			data: {
				identity: that.data.open_ID,
			},
			method: "GET",
			header: {
				'Content-Type': 'text/plain;charset:utf-8;'
			},

			success: function (res) {
				console.log("获取风险信息成功")
				console.log(res)
				that.setData({
					isfrozen: res.data.frozen,
					redNum: res.data.redNum,
					riskActivities: res.data.riskActVoList
				})

				console.log(res)
				if (that.data.isfrozen) {
					console.log(that.data)
					Dialog.alert({
						context: this,
						selector: "#van-dialog",
						message: '您的账户已被冻结，请等待三天自动解冻',
					}).then(() => {
						wx.exitMiniProgram()
					});
				} else {
					// that.data.redNum = 2
					// that.data.riskActivities=[1,2]
					// console.log(that.data)
				}
			},
			fail: function (res) {
				console.log("获取授权关系失败")
				console.log(res)
			}
		})
		//请求投资组合

	},
	navToAI_Query(e) {
		wx.navigateTo({
			url: '../AI_query/AI_query',
		})
	},

	navToHistory(e) {
		wx.navigateTo({
			url: '../my_history/my_history',
		})
	},

	navToInvest(e) {
		wx.navigateTo({
			url: '../smart_invest/smart_invest',
		})
	},

	touchHandler: function (e) {
		console.log(lineChart.getCurrentDataIndex(e));
		lineChart.showToolTip(e, {
			// background: '#7cb5ec',
			format: function (item, category) {
				return category + ' ' + item.name + ':' + item.data
			}
		});
	},
	createSimulationData: function () {
		var categories = [];
		var data = [];
		for (var i = 0; i < 10; i++) {
			categories.push('2022-' + (i + 1));
			data.push(Math.random() * (20 - 10) + 10);
		}
		// data[4] = null;
		return {
			categories: categories,
			data: data
		}
	},
	updateData: function () {
		var simulationData = this.createSimulationData();
		var series = [{
			name: '成交量1',
			data: simulationData.data,
			format: function (val, name) {
				return val.toFixed(2) + '万';
			}
		}];
		lineChart.updateData({
			categories: simulationData.categories,
			series: series
		});
	},

})