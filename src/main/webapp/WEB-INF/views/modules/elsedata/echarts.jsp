<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>echart</title>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/echarts/echarts.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/echarts/china.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/common/common.css">
	<script type="text/javascript">
        $(document).ready(function() {
                    // 加载数据
                    echarts_01();
            		echarts_03();
                    echarts_02();

                    echarts_04();
                    echarts_05();
                    echarts_06();
                    echarts_07();
                    echarts_08();
                    echarts_09();
                    echarts_10();
        });
		//一级部分登陆次数分布（柱状图）
		function echarts_01() {
            var myChart = echarts.init(document.getElementById('echarts_01'));
            $.ajax({
                type : "POST",
                url : '${ctx}/account/accountLogin/getEcharts3/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);
                }
            });
        }
		//二级部门账户分布（柱状图）
        function echarts_02() {
            var myChart = echarts.init(document.getElementById('echarts_02'));
            $.ajax({
                type : "POST",
                url : '${ctx}/account/accountLogin/getEcharts1/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);

                }
            });
        }
		//生产网IP登陆过的用户数分布（饼状图）
        function echarts_03() {
            var myChart = echarts.init(document.getElementById('echarts_03'));
            $.ajax({
                type : "POST",
                url : '${ctx}/pie/loginIpuserDis/list/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // alert(JSON.stringify(data));
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);

                }
            });
            <%--$.ajax({--%>
                <%--type : "POST",--%>
                <%--url : '${ctx}/pie/loginIpuserDis/data/',--%>
                <%--contentType : "application/json; charset=utf-8",--%>
                <%--dataType : 'json',--%>
                <%--cache : false,--%>
                <%--success : function(data) {--%>
                    <%--// alert(JSON.stringify(data));--%>
                    <%--$("#loginSum").text(data[0]);--%>
                    <%--$("#IPSum").text(data[1]);--%>
                    <%--$("#maxLodinNum").text(data[2]);--%>
                    <%--$("#maxIPNum").text(data[3]);--%>

                <%--}--%>
            <%--});--%>
        }
		//员工登陆成功失败测次数（散点图）
        function echarts_04() {
            var myChart = echarts.init(document.getElementById('echarts_04'));
            $.ajax({
                type : "POST",
                url : '${ctx}/elsedata/loginAccountNum/errorData/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);
                }
            });

            <%--$.ajax({--%>
                <%--type : "POST",--%>
                <%--url : '${ctx}/elsedata/loginAccountNum/getdata/',--%>
                <%--contentType : "application/json; charset=utf-8",--%>
                <%--dataType : 'json',--%>
                <%--cache : false,--%>
                <%--success : function(data) {--%>
                    <%--// alert(JSON.stringify(data));--%>
                    <%--$("#winSum").text(data[0]);--%>
                    <%--$("#errorSum").text(data[1]);--%>
                    <%--$("#maxWinNum").text(data[2]);--%>
                    <%--$("#maxErrorNum").text(data[3]);--%>

                <%--}--%>
            <%--});--%>
        }
		//每周登陆情况分布（柱状图）
        function echarts_05() {
            var myChart = echarts.init(document.getElementById('echarts_05'));
            $.ajax({
                type : "POST",
                url : '${ctx}/count3/count3l/getEcharts3/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // alert(JSON.stringify(data));
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);

                }
            });
        }
		//百分百（柱状图）
        function echarts_06() {
            var myChart = echarts.init(document.getElementById('echarts_06'));
            $.ajax({
                type : "POST",
                url : '${ctx}/count4/count4l/getEcharts3/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // alert(JSON.stringify(data));
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);

                }
            });
        }
		// 账户情况性别分布（地图）
        function echarts_07() {
            var myChart = echarts.init(document.getElementById('echarts_07'));
            $.ajax({
                type : "POST",
                url : '${ctx}/elsedata/loginAccountNum/getSexNum2/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(dataz) {
                    var myData=dataz;
                    // alert(JSON.stringify(myData));
                    var typeIndex = 1;
                    var selectedRange = null;
                    var option = null;
                    var str = "";
                    var data = [];
                    var geoCoordMap = {};
                    var name = "账号情况性别分布";
                    var mapName = 'china';
                    var geoMap = {};
                    // 地图特征
                    var mapFeatures = echarts.getMap(mapName).geoJson.features;
                    for (var i = 0; i < myData.length; i++) {
                        data.push({
                            "name": myData[i][2],
                            "value": [{
                                "name": '男',
                                value: myData[i][3]
                            },
                                {
                                    "name": '女',
                                    value: myData[i][4]
                                }
                            ]
                        });
                        geoMap[myData[i][2]]=[myData[i][0],myData[i][1]];
                    }
                    var geoCoordMap=geoMap;
                    // alert(JSON.stringify(geoCoordMap));

                    /*变换地图数据（格式）：pie*/
                    function convertMapDta(type, data) {
                        var mapData = [];
                        for (var i = 0; i < myData.length; i++) {
                            mapData.push({
                                'name': myData[i][2],
                                "value": myData[i][5]
                            })
                        }
                        return mapData;
                    }
                    /*addPie*/
                    function addPie(chart, data) {
                        var op = chart.getOption();
                        var sd = option.series;
                        for (var i = 0; i < data.length; i++) {
                            var randomValue = myData[i][5]/70;
                            var radius = randomValue;
                            var geoCoord = geoCoordMap[data[i].name];
                            if (geoCoord) {
                                var vr = [];
                                (data[i].value).map(function(v) {
                                    vr.push({
                                        name: v.name,
                                        value: v.value,
                                        visualMap: false
                                    }); //饼图的数据不进行映射
                                });
                                var p = chart.convertToPixel({
                                    seriesIndex: 0
                                }, geoCoord);
                                sd.push({
                                    name: data[i].name,
                                    type: 'pie',
                                    radius: radius,
                                    center: p,
                                    data: vr,
                                    zlevel: 4,
                                    tooltip: {
                                        formatter: '{a}<br/>{b}: {c}万人 ({d}%) <br/>总数：'+myData[i][5]+'万人'
                                    },
                                    label: {
                                        normal: {
                                            show: false,
                                        },
                                    },
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    itemStyle: {
                                        opacity: 0.8
                                    }
                                });
                            }
                        }
                        return sd;
                    };


                    /* 指定图表的配置项和数据:pie*/
                    var option = {
                        title: {
                            text: name,
                            left: 'center',
                            textStyle: {
                                color: 'black'
                            }
                        },
                        legend: {
                            data: [ '男', '女'],
                            orient: 'vertical',
                            top: '10%',
                            left: 'left',
                            zlevel: 4
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: function () {
                                return null;
                            }
                        },
                        series: [{
                            name: 'chinaMap',
                            type: 'map',
                            mapType: mapName,
                            roam: false,
                            geo: {
                                show: true,
                                map: mapName,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: false,
                                    }
                                },
                                roam: true,
                                itemStyle: {
                                    normal: {
                                        areaColor: '#031525',
                                        borderColor: '#3B5077',
                                    },
                                    emphasis: {
                                        areaColor: 'black',
                                    }
                                }
                            },
                            zlevel: 3
                        }]
                    };
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                    /*pie*/
                    addPie(myChart, data);
                    myChart.setOption(option);

                }
            });
            $.ajax({
                type : "POST",
                url : '${ctx}/elsedata/loginAccountNum/getAccountNum/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // alert(JSON.stringify(data));
                    $("#accountNumMax").text(data[0]);
                    $("#ctAccountSum").text(data[1]);
                }
            });

        }
		// 登陆情况性别分布（地图）
        function echarts_08() {
            var myChart2 = echarts.init(document.getElementById('echarts_08'));
            $.ajax({
                type : "POST",
                url : '${ctx}/elsedata/loginAccountNum/getSexNum1/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    var myData=data;
                    // alert(JSON.stringify(myData));
                    var option = null;
                    var data = [];
                    var name = "登录情况性别分布";
                    var mapName = 'china';
                    var geoMap = {};
                    // 地图特征
                    var mapFeatures = echarts.getMap(mapName).geoJson.features;
                    for (var i = 0; i < myData.length; i++) {
                        data.push({
                            "name": myData[i][2],
                            "value": [{
                                "name": '男',
                                value: myData[i][3]
                            },
                                {
                                    "name": '女',
                                    value: myData[i][4]
                                }
                            ]
                        });
                        // alert(JSON.stringify(myData[i][3]));
                        geoMap[myData[i][2]]=[myData[i][0],myData[i][1]];
                        // alert(JSON.stringify(geoMap));
                    }
                    var geoCoordMap=geoMap;
                    // alert(JSON.stringify(geoCoordMap));
                    // alert(JSON.stringify(data));
                    /*addPie*/
                    function addPie(chart, data) {
                        var op = chart.getOption();
                        var sd = option.series;
                        for (var i = 0; i < data.length; i++) {
                            var randomValue = myData[i][5]/ 50;
                            var radius = randomValue;
                            var geoCoord = geoCoordMap[data[i].name];
                            if (geoCoord) {
                                var vr = [];
                                (data[i].value).map(function(v) {
                                    vr.push({
                                        name: v.name,
                                        value: v.value,
                                        visualMap: false
                                    }); //饼图的数据不进行映射
                                });
                                var p = chart.convertToPixel({
                                    seriesIndex: 0
                                }, geoCoord);
                                sd.push({
                                    name: data[i].name,
                                    type: 'pie',
                                    radius: radius,
                                    center: p,
                                    data: vr,
                                    zlevel: 4,
                                    tooltip: {
                                        formatter: '{a}<br/>{b}: {c}万人 ({d}%)'
                                    },
                                    label: {
                                        normal: {
                                            show: false,
                                        },
                                    },

                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    color:['#f63121','#1a49e8'],
                                    itemStyle: {
                                        opacity: 0.8
                                    }
                                });
                            }
                        }
                        return sd;
                    };


                    /* 指定图表的配置项和数据:pie*/
                    var option = {
                        title: {
                            text: name,
                            left: '60%',
                            textStyle: {
                                color: 'black'
                            }
                        },
                        legend: {
                            data: ['女', '男'],
                            orient: 'vertical',
                            top: '10%',
                            left: 'left',
                            zlevel: 4
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: function () {
                                return null;
                            }
                        },
                        series: [{
                            name: 'chinaMap',
                            type: 'map',
                            mapType: mapName,
                            roam: false,
                            geo: {
                                show: true,
                                map: mapName,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: false,
                                    }
                                },
                                roam: false,
                                itemStyle: {
                                    normal: {
                                        areaColor: '#f0de46',
                                        borderColor: '#77261d',
                                    },
                                    emphasis: {
                                        areaColor: 'black',
                                    }
                                }
                            },
                            zlevel: 3
                        }]
                    };
                    if (option && typeof option === "object") {
                        myChart2.setOption(option, true);
                    }
                    /*pie*/
                    addPie(myChart2, data);

                    myChart2.setOption(option);

                }
            });
            $.ajax({
                type : "POST",
                url : '${ctx}/elsedata/loginAccountNum/getLoginNum/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // alert(JSON.stringify(data));
                    $("#loginNumMax").text(data[0]);
                    $("#ctLoginSum").text(data[1]);
                }
            });
        }

		//二级部门登陆次数分布（柱状图）
        function echarts_09() {
            var myChart = echarts.init(document.getElementById('echarts_09'));
            $.ajax({
                type : "POST",
                url : '${ctx}/account/accountLogin/getEcharts2/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);
                }
            });
        }

		//用户活跃天数分布（柱状图）
        function echarts_10() {
            var myChart = echarts.init(document.getElementById('echarts_10'));
            $.ajax({
                type : "POST",
                url : '${ctx}/account/accountLogin/getEcharts7/',
                contentType : "application/json; charset=utf-8",
                dataType : 'json',
                cache : false,
                success : function(data) {
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(data);
                }
            });

        }





	</script>
</head>
<body>
<div class="box">
	<div class="left">
		<div class="left_01">
			<div class="echarts_01">
				<p class="title">部门登录次数分布</p>
				<div id="echarts_01"></div>
			</div>
			<div class="echarts_02">
				<p class="title">部门登录次数分布</p>
				<div id="echarts_02"></div>
			</div>
			<div class="line"></div>
		</div>
		<div class="left_box">
			<div class="left_02">
				<p class="title">生产IP登录过用户分布</p>
				<div id="echarts_03">

				</div>
			</div>

			<div class="left_03">
				<p class="title">员工登录成功失败分布</p>
				<div id="echarts_04">

				</div>
			</div>

			<div class="left_05">
				<p class="title">每周登录情况分布</p>
				<div id="echarts_05"></div>
			</div>

			<div class="left_06">
				<p class="title">每周登录情况百分比</p>
				<div id="echarts_06"></div>
			</div>
		</div>
	</div>
	<div class="right">
		<div class="right_box">
			<p class="title">中国城市分布</p>
			<div class="right_01">
				<div id="echarts_07">

				</div>
				<ul class="right_list1">
					<li>
						<label>账号数量最多</label>
						<label id="accountNumMax"></label>
					</li>
					<li class="margT">
						<label>现有账号总数</label>
						<label id="ctAccountSum"></label>
					</li>
				</ul>
				<div id="echarts_08">

				</div>
				<ul class="right_list">
					<li class="">
						<label>登陆次数最多</label>
						<label id="loginNumMax"></label>
					</li>
					<li class="margT">
						<label>登陆的总次数</label>
						<label id="ctLoginSum"></label>
					</li>
				</ul>
			</div>
		</div>

		<div class="right_02">
			<div class="echarts_09">
				<p class="title">总体登录统计</p>
				<div id="echarts_09"></div>
			</div>
			<div class="echarts_10">
				<p class="title">用户活跃天数统计</p>
				<div id="echarts_10">

				</div>
			</div>
			<div class="line"></div>
		</div>
	</div>

</div>
</body>
</html>
<style>
	.box{
		width: 100%;
		min-height: 1080px;
		background-color: #e6eeff;
	}
	.left,.right{
		width: 50%;
		float: left;
		height: 100%;
		box-sizing: border-box;
	}
	.left_01{
		height: 273px;
		width: 97%;
		background-color: #fff;
		border-radius: 15px;
		margin-top: 26px;
		margin-left: 2%;
		overflow: hidden;
		position: relative;
	}
	.line{
		position: absolute;
		width: 1px;
		height: 200px;
		left: 50%;
		top: 58px;
		background-color: #e6eeff;
	}
	.echarts_01,.echarts_02,.echarts_09,.echarts_10{
		width: 50%;
		float: left;
		box-sizing: border-box;
	}
	.title{
		background-color: #acc6fd;
		line-height: 40px;
		text-align: center;
		width: 100%;
		color: #fff;
		font-size: 18px;
		font-weight: 600;
	}
	.margT{
		margin-top: 10px;
	}
	#echarts_01,#echarts_02,#echarts_09,#echarts_10{
		width: 100%;
		height: 232px;
	}
	.left_box{
		height: 733px;
		width: 97%;
		margin-top: 26px;
		margin-left: 2%;
		overflow: hidden;
		position: relative;
	}
	.left_02,.left_03{
		width: 48%;
		height: 60%;
		border-radius: 15px;
		background-color: #fff;
		float: left;
		overflow: hidden;
	}
	.left_03{
		position: absolute;
		right: 0;
	}
	#echarts_03,#echarts_04{
		width: 100%;
		height: 417px;
	}

	.info{
		width: 82%;
		margin: 8px auto 0;
		height: 265px;
	}
	.info li{
		float: left;
		width: 92px;
		height: 108px;
		background-color: #f8e367;
		border-radius: 10px;
	}
	.info .float_right{
		float: right;
	}
	.info .mat{
		margin-top: 28px;
	}
	.left_06,.left_05{
		width: 48%;
		height: 275px;
		background-color: #fff;
		border-radius: 15px;
		float: left;
		overflow: hidden;
		position: absolute;
		right: 0;
		bottom: 0;
	}
	.left_05{
		left: 0;
	}
	.left_06{
		right: 0;
	}
	#echarts_06,#echarts_05{
		height: 280px;
		width: 100%;
		margin-top: -29px;
		margin-left: 10px;
	}
	.right_box{
		width: 97%;
		height: 730px;
		border-radius: 15px;
		overflow: hidden;
		background-color: #fff;
		margin-left: 1%;
		margin-top: 26px;
	}
	.right_01{
		width: 100%;
		height: 690px;
		position: relative;
	}
	.right_02{
		height: 273px;
		width: 97%;
		background-color: #fff;
		border-radius: 15px;
		margin-top: 26px;
		margin-left: 1%;
		overflow: hidden;
		position: relative;
	}
	#echarts_07{
		position: absolute;
		width: 490px;
		height: 390px;
		top: 0;
		left: 0;
		/* background-color: #acc6fd;*/
	}
	#echarts_08{
		position: absolute;
		width: 490px;
		height: 390px;
		bottom: 0;
		right: 0;
		/*background-color: #cccccc;*/
	}
	.right_info{
		position: absolute;
		width: 300px;
		height: 180px;
		top: 0;
		right: 0;
	}
	.right_list{
		width: 115px;
		height: 170px;
		position: absolute;
		left: 20px;
		bottom: 20px;
	}
	.right_list li{
		width: 100px;
		height: 70px;
		border: 2px solid #8ecb9b;
		/* background-color: #f8e367; */
		border-radius: 10px;
	}
	.right_list li label{
		display: inline-block;
		width: 100%;
		text-align: center;
	}
	.right_list li label:first-child{
		line-height: 35px;
		font-size: 14px;
		color: #8ecb9b;
	}
	.right_list li label:last-child{
		font-size: 18px;
		color: #acc6fd;
	}
	.right_list1{
		width: 110px;
		height: 270px;
		position: absolute;
		right: 20px;
		top: 20px;
	}
	.right_list1 li{
		width: 100px;
		height: 70px;
		border: 2px solid #8ecb9b;
		float: left;
		/* background-color: #f8e367; */
		border-radius: 10px;
	}
	.right_list1 li label{
		display: inline-block;
		width: 100%;
		text-align: center;
	}
	.right_list1 li label:first-child{
		line-height: 35px;
		font-size: 14px;
		color: #8ecb9b;
	}
	.right_list1 li label:last-child{
		font-size: 18px;
		color: #acc6fd;
	}
</style>