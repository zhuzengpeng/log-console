<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据分析管理</title>
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
		<li><a href="${ctx}/terminal/loginTerminal/">数据分析列表</a></li>
		<li class="active"><a href="${ctx}/terminal/loginTerminal/form?id=${loginTerminal.id}">数据分析<shiro:hasPermission name="terminal:loginTerminal:edit">${not empty loginTerminal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="terminal:loginTerminal:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="loginTerminal" action="${ctx}/terminal/loginTerminal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">终端登录时间：</label>
			<div class="controls">
				<input name="loginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${loginTerminal.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端IP：</label>
			<div class="controls">
				<form:input path="terminalIp" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录账户数：</label>
			<div class="controls">
				<form:input path="accountNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">页面访问总数：</label>
			<div class="controls">
				<form:input path="visitNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产网访问数：</label>
			<div class="controls">
				<form:input path="manuVisitNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">旅游网访问数：</label>
			<div class="controls">
				<form:input path="travelVisitNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学习网访问数：</label>
			<div class="controls">
				<form:input path="studyVisitNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问成功数：</label>
			<div class="controls">
				<form:input path="winNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问失败数：</label>
			<div class="controls">
				<form:input path="failNumber" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="terminal:loginTerminal:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>