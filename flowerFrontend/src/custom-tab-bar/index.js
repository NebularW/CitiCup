// custom-tab-bar/index.js
Component({
	data: {
		active: 0,
		list: [{
				text: "主页",
				icon: "home-o",
				url: "/pages/homepage/homepage"
			},
			{
				text: "理财",
				icon: "balance-o",
				url: "/pages/invest/invest"
			},
			{
				text: "家",
				icon: "friends-o",
				url: "/pages/home/home"
			},
			{
				text: "服务",
				icon: "apps-o",
				url: "/pages/other_service/other_service"
			},
			{
				text: "我的",
				icon: "user-o",
				url: "/pages/me/me"
			},
		]
	},
	methods: {
		onChange(e) {
			console.log(e, 'e')
			wx.switchTab({
				url: this.data.list[e.detail].url
			});
			this.setData({
				active: e.detail
			});
		},
		init() {
			const page = getCurrentPages().pop();
			this.setData({
				active: this.data.list.findIndex(item => item.url === `/${page.route}`)
			});
		}
	}
});