<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>count6laccountmessage管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/count6laccountmessage/count6lAccountMesssage/">count6laccountmessage列表</a></li>
		<li class="active"><a href="${ctx}/count6laccountmessage/count6lAccountMesssage/form?id=${count6lAccountMesssage.id}">count6laccountmessage<shiro:hasPermission name="count6laccountmessage:count6lAccountMesssage:edit">${not empty count6lAccountMesssage.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="count6laccountmessage:count6lAccountMesssage:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="count6lAccountMesssage" action="${ctx}/count6laccountmessage/count6lAccountMesssage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">account：</label>
			<div class="controls">
				<form:input path="account" htmlEscape="false" maxlength="25" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">depart：</label>
			<div class="controls">
				<form:input path="depart" htmlEscape="false" maxlength="25" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">load_date：</label>
			<div class="controls">
				<input name="loadDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${count6lAccountMesssage.loadDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">load_state：</label>
			<div class="controls">
				<form:select path="loadState" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">source_address：</label>
			<div class="controls">
				<form:input path="sourceAddress" htmlEscape="false" maxlength="25" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">target_address：</label>
			<div class="controls">
				<form:input path="targetAddress" htmlEscape="false" maxlength="25" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="count6laccountmessage:count6lAccountMesssage:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>