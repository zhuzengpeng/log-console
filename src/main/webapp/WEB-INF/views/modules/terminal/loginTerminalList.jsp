<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据分析管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        //$("#searchForm").attr("action","${ctx}/terminal/loginTerminal/export");
                        //$("#searchForm").submit();
                        $("#downloadForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>

    <div hidden="true">
        <form:form id="downloadForm" action="${ctx}/terminal/loginTerminal/export" method="post" class="breadcrumb form-search" />
    </div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/terminal/loginTerminal/">数据列表</a></li>
		<%--<shiro:hasPermission name="terminal:loginTerminal:edit"><li><a href="${ctx}/terminal/loginTerminal/form">数据分析添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="loginTerminal" action="${ctx}/terminal/loginTerminal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 95px;text-align:right;">终端登录时间：</label>
				<input name="beginLoginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${loginTerminal.beginLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endLoginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${loginTerminal.endLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>终端IP：</label>
				<form:input path="terminalIp" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>终端登录时间</th>
				<th>终端IP</th>
				<th>登录账户数</th>
				<th>页面访问总数</th>
				<th>生产网访问数</th>
				<th>旅游网访问数</th>
				<th>学习网访问数</th>
				<th>访问成功数</th>
				<th>访问失败数</th>
				<shiro:hasPermission name="terminal:loginTerminal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="loginTerminal">
			<tr>
				<td>
					<fmt:formatDate value="${loginTerminal.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${loginTerminal.terminalIp}
				</td>
				<td>
					${loginTerminal.accountNumber}
				</td>
				<td>
					${loginTerminal.visitNumber}
				</td>
				<td>
					${loginTerminal.manuVisitNumber}
				</td>
				<td>
					${loginTerminal.travelVisitNumber}
				</td>
				<td>
					${loginTerminal.studyVisitNumber}
				</td>
				<td>
					${loginTerminal.winNumber}
				</td>
				<td>
					${loginTerminal.failNumber}
				</td>
				<shiro:hasPermission name="terminal:loginTerminal:edit"><td>
					<a>查看</a>
    				<%--<a href="${ctx}/terminal/loginTerminal/form?id=${loginTerminal.id}">修改</a>--%>
					<%--<a href="${ctx}/terminal/loginTerminal/delete?id=${loginTerminal.id}" onclick="return confirmx('确认要删除该数据分析吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>