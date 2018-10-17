<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>echart-测试1</title>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/echarts/echarts.min.js" type="text/javascript"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="main1" style="width: 600px;height:400px;"></div>
</body>
</html>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        type : "POST",
        url : '${ctx}/test/echarts/getEcharts2Data/',
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
        url : '${ctx}/pie/loginIpuserDis/list/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(data);
        }
    });
</script>
