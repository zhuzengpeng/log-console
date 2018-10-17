<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>echart-测试1</title>
    <script src="${ctxStatic}/jquery/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="${ctxStatic}/echart/echarts.js" type="text/javascript"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<br>
<div id="main2" style="width: 600px;height:400px;"></div>
<br>
<div id="main3" style="width: 600px;height:400px;"></div>
<br>
<div id="main4" style="width: 600px;height:400px;"></div>
<br>
<div id="main5" style="width: 600px;height:400px;"></div>
<br>
<div id="main6" style="width: 600px;height:400px;"></div>
<br>
<div id="main7" style="width: 600px;height:400px;"></div>
</body>
</html>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
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


    var myChart2 = echarts.init(document.getElementById('main2'));
    $.ajax({
        type : "POST",
        url : '${ctx}/account/accountLogin/getEcharts2/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart2.setOption(data);
        }
    });


    var myChart3 = echarts.init(document.getElementById('main3'));
    $.ajax({
        type : "POST",
        url : '${ctx}/account/accountLogin/getEcharts3/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart3.setOption(data);
        }
    });


    var myChart4 = echarts.init(document.getElementById('main4'));
    $.ajax({
        type : "POST",
        url : '${ctx}/account/accountLogin/getEcharts4/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart4.setOption(data);
        }
    });


    var myChart5 = echarts.init(document.getElementById('main5'));
    $.ajax({
        type : "POST",
        url : '${ctx}/account/accountLogin/getEcharts5/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart5.setOption(data);
        }
    });


    var myChart6 = echarts.init(document.getElementById('main6'));
    $.ajax({
        type : "POST",
        url : '${ctx}/account/accountLogin/getEcharts6/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart6.setOption(data);
        }
    });


    var myChart7 = echarts.init(document.getElementById('main7'));
    $.ajax({
        type : "POST",
        url : '${ctx}/account/accountLogin/getEcharts7/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
            myChart7.setOption(data);
        }
    });
</script>
