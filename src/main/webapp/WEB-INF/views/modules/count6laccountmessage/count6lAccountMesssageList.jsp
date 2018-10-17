<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>count6laccountmessage管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        //$("#searchForm").attr("action","${ctx}/count6laccountmessage/count6lAccountMesssage/export");
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
        <form:form id="downloadForm" action="${ctx}/count6laccountmessage/count6lAccountMesssage/export" method="post" class="breadcrumb form-search" />
    </div>


	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/count6laccountmessage/count6lAccountMesssage/">登录账户列表</a></li>
		<shiro:hasPermission name="count6laccountmessage:count6lAccountMesssage:edit"><li><a href="${ctx}/count6laccountmessage/count6lAccountMesssage/form">count6laccountmessage添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="count6lAccountMesssage" action="${ctx}/count6laccountmessage/count6lAccountMesssage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账户：</label>
				<form:input path="account" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>所属部门：</label>
				<form:input path="depart" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>开始日期：</label>
				<input name="loadDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					<%--value="<fmt:formatDate value="${count6lAccountMesssage.loadDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					   value="${count6lAccountMesssage.loadDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="loadDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					<%--value="<fmt:formatDate value="${count6lAccountMesssage.loadDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					   value="${count6lAccountMesssage.loadDate1}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>load_state：</label>
				<form:select path="loadState" class="input-medium">
					<form:option value="" label="取消"/>
					<form:option value="1" label="登陆成功"/>
					<form:option value="2" label="登陆失败"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>账户</th>
				<th>所属部门</th>
				<th>登陆时间</th>
				<th>登陆状态</th>
				<th>源IP地址</th>
				<th>目标IP地址</th>
				<shiro:hasPermission name="count6laccountmessage:count6lAccountMesssage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="count6lAccountMesssage">
			<tr>
				<td><a href="${ctx}/count6laccountmessage/count6lAccountMesssage/form?id=${count6lAccountMesssage.id}">
					${count6lAccountMesssage.account}
				</a></td>
				<td>
					${count6lAccountMesssage.depart}
				</td>
				<td>
					${count6lAccountMesssage.loadDate}
				</td>
				<td><c:if test="${count6lAccountMesssage.loadState==1}">登陆成功</c:if>
					<c:if test="${count6lAccountMesssage.loadState==2}">登陆失败</c:if>
				</td>
				<td>
					${count6lAccountMesssage.sourceAddress}
				</td>
				<td>
					${count6lAccountMesssage.targetAddress}
				</td>
				<shiro:hasPermission name="count6laccountmessage:count6lAccountMesssage:edit"><td>
    				<a href="${ctx}/count6laccountmessage/count6lAccountMesssage/form?id=${count6lAccountMesssage.id}">修改</a>
					<a href="${ctx}/count6laccountmessage/count6lAccountMesssage/delete?id=${count6lAccountMesssage.id}" onclick="return confirmx('确认要删除该count6laccountmessage吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>