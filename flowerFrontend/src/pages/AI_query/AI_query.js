// pages/AI_query/AI_query.js
var app = getApp(),
    api = 'https://op.juhe.cn/robot/index',
    appKey = 'd3b7f81ac8efa513bcb95639ac9dee99';

Page({
    data: {
		active: 1,
		value : '',
        question_list: [{
                id: 1,
                text: "什么是家庭关联账户"
            },
            {
                id: 2,
                text: "如何申请家庭关联账户"
            },
            {
                id: 3,
                text: "医疗健康界面从哪里进入",
            },
            {
                id: 4,
                text: "可用家庭积分明细在哪里查看",
            },
            {
                id: 5,
                text: "中年人和老年人的界面有什么不同",
            },
            {
                id: 6,
                text: "什么是“长辈协助”功能",
            },
            {
                id: 7,
                text: "如何开通“长辈协助”功能",
            },
            {
                id: 8,
                text: "什么是长辈守护开关",
            },
            {
                id: 9,
                text: "“一起看”功能有什么作用",
            },
            {
                id: 10,
                text: "什么是“风险管控”功能",
            },
            {
                id: 11,
                text: "触发“风险熔断”之后如何实现风险解除",
            },
            {
                id: 12,
                text: "若有人点击 reject，后续如何解冻个人账户",
            },
            {
                id: 13,
                text: "如何明确是由哪笔交易导致的熔断触发",
            },
            {
                id: 14,
                text: "哪里可以看到家庭成员的账户收益",
            },
            {
                id: 15,
                text: "如何不让家人看到本人账户收益情况",
            },
            {
                id: 16,
                text: "在哪里可以查看平台上的历史记录",
            },
            {
                id: 17,
                text: "家庭群聊有什么功能",
            },
            {
                id: 18,
                text: "“健康档案”有什么作用",
            },
            {
                id: 19,
                text: "如何提高家庭共享积分",
            }
        ],
        title: "你来提问，我来回答",
        hidden: true,
        toastHidden: true,
        errMsg: '',
        user: []
    },
    navigate_page1(e) {
        wx.navigateTo({
            url: "./answers/" + 1
        });
	},
	navigate_page2(e) {
        wx.navigateTo({
            url: "./answers/" + 2
        });
	},
	
	onChange(e)
	{
		console.log(e)
		this.setData({
			active : e.detail.index
		})
	},

	search(e)
	{
		console.log(this.data.value)
		console.log(e)
		this.setData({
			value : e.detail
		})
	}
})