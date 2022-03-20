import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';
Page({
	data: {
		ratio1: 0,
		ratio2: 0,
		ratio3: 0,
		ratio4: 0,
		ratio5: 0,
		score : 0,
		active : 0,
		q : [ 
			{id : 0, question : '', answer : 0} ,
			{id : 0, question : '', answer : 0} ,
			{id : 0, question : '', answer : 0} ,
			{id : 0, question : '', answer : 0} ,
			{id : 0, question : '', answer : 0} ,
		],
		steps: [
			{
				text: '问题1',
			},
			{
				text: '问题2',
			},
			{
				text: '问题3',
			},
			{
				text: '问题4',
			},
			{
				text: '问题5'
			},
		],

		questions : 
		[
			'存款保险是指国家通过立法的形式设立专门的存款保险基金，明确当个别金融机构经营出现问题时，依照规定对存款人进行及时偿付保障存款人权益。这个说法（）',
			'公平对待消费者的原则是：依法合规、诚实守信、公开透明、公平公正、文明规范。这个说法（）',
			'享有知情权是银行消费者在消费过程中做出自由选择并实现公平交易的前提条件。这个说法（）',
			'商业银行可以向客户提供与其真实需求和风险承受能力不相符合的产品和服务。这个说法（）',
			'金融机构、相关社会组织要加强研究，综合运用多种方式，推动金融消费者宣传教育工作深入开展。这体现了充分保障消费者的受教育权。这个说法（）',
			'金融机构应当以通俗易懂的语言，及时、真实、准确、全面地向金融消费者披露可能影响其决策的信息，充分提示风险。这体现了充分保障消费者的知情权。这个说法（）',
			'客户明确表示不接受相关服务价格的，不得强制客户接受服务，体现了充分尊重客户的受尊重权。这个说法（）',
			'为保护金融消费者个人信息，金融机构应建立个人信息保护机制，涵盖个人信息收集、使用、保存等全流程管控。这个说法（）',
			'同一存款人在同一家投保机构所有被保险存款账户的存款本金和利息合并计算的资金数额在最高偿付限额以内的，实行部分偿付。这个说法（）',
			'金融消费者在识别金融广告真实性、合法性时，可以从金融广告的基本特征和自身风险防范意识及能力两个方面入手。这个说法（）'
		],
		answers : [
			'1',
			'1',
			'1',
			'2',
			'1',
			'1',
			'2',
			'1',
			'2',
			'1'
		]
	},
	submit(e)
	{
		console.log("##################")
		var that = this
		that.setData({
			score : 0
		})


		if (this.data.ratio1 == this.data.q[0].answer)
		{
			that.setData({
				score : that.data.score + 1
			})
		}

		if (this.data.ratio2 == this.data.q[1].answer)
		{
			that.setData({
				score : that.data.score + 1
			})
			console.log(this.data)

		}

		if (this.data.ratio3 == this.data.q[2].answer)
		{
			that.setData({
				score : that.data.score + 1
			})
		}

		if (this.data.ratio4 == this.data.q[3].answer)
		{
			that.setData({
				score : that.data.score + 1
			})
		}

		if (this.data.ratio5 == this.data.q[4].answer)
		{
			that.setData({
				score : that.data.score + 1
			})
		}
		
		Dialog.alert({
			context : this,
			selector:"#van-dialog",
			message: "您的最终得分为：" + `${that.data.score}` + "分",
		}).then(() => {
			wx.navigateBack({
			  delta: 1,
			})
		});
	},

	onChange1(event) {
		var that = this
		that.setData({
			ratio1 : event.detail
		})
	},
	onChange2(event) {
		var that = this
		that.setData({
			ratio2 : event.detail
		})
	},
	onChange3(event) {
		var that = this
		that.setData({
			ratio3 : event.detail
		})
	},
	onChange4(event) {
		var that = this
		that.setData({
			ratio4 : event.detail
		})
	},
	onChange5(event) {
		var that = this
		that.setData({
			ratio5 : event.detail
		})
	},
	last(e)
	{
		this.setData({
			active : this.data.active - 1
		})
	},

	next(e)
	{
		this.setData({
			active : this.data.active + 1
		})
		console.log("active" + ":" + this.data.active)
	},

	randomNum(e){ 
		return Math.floor(Math.random()*10);
		// return 1
	},
	
	onLoad: function (options) {
		var that = this
		var cnt = 0
		var id = 0;
		while (cnt < 5)
		{
			id = that.randomNum();
			// console.log(cnt + " : " + id)
			var flag = false;
			for (var i = 0; i < cnt; ++i)
			{
				if (that.data.q[i].id == id)
					flag = true;
			}
			if (flag == true) continue;
			
			// console.log(that.data.q)
			that.data.q[cnt].id = id;
			that.data.q[cnt].question = that.data.questions[id];
			that.data.q[cnt].answer = that.data.answers[id];
			++cnt;
		} 
		console.log(cnt)
		console.log(that.data.q)
		this.setData({
			q : that.data.q
		})
	},

	onShow: function () {

	},
})