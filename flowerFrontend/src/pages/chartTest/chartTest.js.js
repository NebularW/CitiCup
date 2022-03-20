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
		redNum: 0
	},
    onShow:function(){
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
			width: windowWidth,
			height: 150,
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
				disableGrid: true
			},
			yAxis: {
				title: '收益金额 (万元)',
				format: function (val) {
					return val.toFixed(2);
				},
				min: 0
			},
			width: windowWidth,
			height: 150,
			dataLabel: false,
			dataPointShape: true,
			extra: {
				lineStyle: 'curve'
			}
		});
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
			categories.push('2016-' + (i + 1));
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