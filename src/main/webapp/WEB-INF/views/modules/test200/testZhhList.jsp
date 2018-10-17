<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>zzz管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test200/testZhh/">zzz列表</a></li>
		<shiro:hasPermission name="test200:testZhh:edit"><li><a href="${ctx}/test200/testZhh/form">zzz添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testZhh" action="${ctx}/test200/testZhh/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名子：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>蛋蛋：</label>
				<form:input path="tt" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>名子</th>
				<th>蛋蛋</th>
				<shiro:hasPermission name="test200:testZhh:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testZhh">
			<tr>
				<td><a href="${ctx}/test200/testZhh/form?id=${testZhh.id}">
					${testZhh.id}
				</a></td>
				<td>
					${testZhh.name}
				</td>
				<td>
					${testZhh.tt}
				</td>
				<shiro:hasPermission name="test200:testZhh:edit"><td>
    				<a href="${ctx}/test200/testZhh/form?id=${testZhh.id}">修改</a>
					<a href="${ctx}/test200/testZhh/delete?id=${testZhh.id}" onclick="return confirmx('确认要删除该zzz吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>