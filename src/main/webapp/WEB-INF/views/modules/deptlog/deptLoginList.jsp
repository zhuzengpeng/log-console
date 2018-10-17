<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看登录管理</title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#treeTable").treeTable({expandable: true,expandLevel : 3}).show();
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        //$("#searchForm").attr("action","${ctx}/deptlog/deptLogin/export");
                        //$("#searchForm").submit();
                        $("#downloadForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
        });
		/*function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }*/
	</script>
</head>
<body>

    <div hidden="true">
        <form:form id="downloadForm" action="${ctx}/deptlog/deptLogin/export" method="post" class="breadcrumb form-search" />
    </div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/deptlog/deptLogin/">查看登录列表</a></li>
		<shiro:hasPermission name="deptlog:deptLogin:edit"><li><a href="${ctx}/deptlog/deptLogin/form">查看登录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deptLogin" action="${ctx}/deptlog/deptLogin/" method="post" class="breadcrumb form-search">
		<%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
		<ul class="ul-form">
			<li><label>部门名：</label>
				<form:input path="state" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>登录状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
            <li class="clearfix"></li>
			<li><label>登录账户：</label>
				<form:input path="account" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>登录时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${deptLogin.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${deptLogin.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            </li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th>部门</th>
                <th>生产网地址</th>
                <th>源地址</th>
                <th>登录状态</th>
                <th>登录账户</th>
                <th>登录时间</th>
                <shiro:hasPermission name="deptlog:deptLogin:edit"><th>操作</th></shiro:hasPermission>
            </tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="deptLogin">
			<tr id="${deptLogin.deptId}"  pId="${deptLogin.parent.id ne '212123'?deptLogin.parent.id:'null'}">
                <td nowrap>${deptLogin.state}</td>
                <td>${deptLogin.productIp}</td>
                <td>${deptLogin.sourceIp}</td>
                <td>${deptLogin.status eq '1'?'success':'fail'}</td>
                <td>${deptLogin.account}</td>
                <td><fmt:formatDate value="${deptLogin.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="deptlog:deptLogin:edit"><td>
    				<a href="${ctx}/deptlog/deptLogin/form?id=${deptLogin.id}">修改</a>
					<a href="${ctx}/deptlog/deptLogin/delete?id=${deptLogin.id}" onclick="return confirmx('确认要删除该查看登录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>