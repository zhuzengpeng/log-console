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
<input id="return-button" type="button" value="返回" style="position: absolute;top: 10px;left: 503px;z-index: 9999999">

<div id="main" style="width: 600px;height:400px;">
</div>
</body>
</html>

<script type="text/javascript">
    $('#return-button').on('click',function(){
        var  var1= 1;
        // 基于准备好的dom，初始化echarts实例
        var dom=document.getElementById('main');
        var myChart = echarts.init(dom);
        //定义option
        var option="";
        var option1 = "";
        var option2 = "";
        var disButton = document.getElementById("return-button");
        disButton.style.display="none";
        $.ajax({
            type : "POST",
            url : '${ctx}/count1/count1l/getEcharts3/',
            contentType : "application/json; charset=utf-8",
            dataType : 'json',
            cache : false,
            success : function(data) {
                // 使用刚指定的配置项和数据显示图表。
                option=data;
                myChart.setOption(option);
                myChart.on('click',function(object) {
                    // 销毁之前的echarts实例
                    echarts.dispose(dom);
                    // 初始化一个新的实例
                    var myChart = echarts.init(dom);
                    disButton.style.display="block";
                    $.ajax({
                        type: "get",
                        url: '${ctx}/count1/count1l/getEcharts4' + '?departId='+object.name, // 此处可以替换为你的接口地址
                        dataType: 'json',
                        success: function (data) {
                            option1=data;
                            myChart.setOption(option1, true);
                        }
                    });
                })
            }
        });
    });
    // 基于准备好的dom，初始化echarts实例
	var dom=document.getElementById('main');
    var myChart = echarts.init(dom);
    //定义option
	var option="";
    var option1 = "";
    var disButton = document.getElementById("return-button");
    disButton.style.display="none";
    $.ajax({
        type : "POST",
        url : '${ctx}/count1/count1l/getEcharts3/',
        contentType : "application/json; charset=utf-8",
        dataType : 'json',
        cache : false,
        success : function(data) {
            // 使用刚指定的配置项和数据显示图表。
			option=data;
            myChart.setOption(option);
            myChart.on('click',function(object) {
                // 销毁之前的echarts实例

                echarts.dispose(dom);
                // 初始化一个新的实例
                var myChart = echarts.init(dom);
                disButton.style.display="block";
                $.ajax({
                    type: "get",
                    url: '${ctx}/count1/count1l/getEcharts4' + '?departId='+object.name, // 此处可以替换为你的接口地址
                    dataType: 'json',
                    success: function (data) {
                        option1=data;
                        myChart.setOption(option1, true);

                    }
                });
			})
		}
    });

</script>