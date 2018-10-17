<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>echart-测试3</title>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/echarts/echarts.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/echarts/china.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="main1" style="width: 600px;height:400px;"></div>
<div id="main2" style="width: 600px;height:400px;"></div>
<div id="main3" style="width: 600px;height:400px;"></div>
</body>
</html>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
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

    var myChart1 = echarts.init(document.getElementById('main1'));
    $.ajax({
        type : "POST",
        url : '${ctx}/elsedata/loginAccountNum/getSexNum/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // alert(JSON.stringify(data1));
           var myData=data;
            // alert(JSON.stringify(myData));
            var option = {
                geo: {
                    map: 'china'
                },
                backgroundColor: '#404a59',

                series: [
                    {
                        name: '销量', // series名称
                        type: 'scatter', // series图表类型
                        coordinateSystem: 'geo', // series坐标系类型

                        data: myData, // series数据内容

                        label: {
                            normal: {
                                formatter:  function (param) {
                                    return param.data[2];
                                },
                                position: 'right',
                                show: true
                            },
                            emphasis: {
                                show: true,
                                formatter: function (param) {
                                    return param.data[3];
                                },
                                position: 'top'
                            }
                        }
                    }
                ]
            }
            myChart1.setOption(option);

        }
    });

/////////////////////////////////////////////
    var myChart2 = echarts.init(document.getElementById('main2'));
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
                    left: 'center',
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



    ///////////////////////////
    var myChart3 = echarts.init(document.getElementById('main3'));
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
                myChart3.setOption(option, true);
            }
            /*pie*/
            addPie(myChart3, data);
            myChart3.setOption(option);

        }
    });


</script>