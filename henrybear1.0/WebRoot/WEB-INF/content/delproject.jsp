<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.henrybear.bean.ProjectBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ProjectBean project = (ProjectBean) request.getAttribute("modproj");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除</title>
<script language="javascript"
	src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
</head>
<script type="text/javascript">
	var projmanage = [ "serial", "serialname", "description", "fuzeren",
			"fuzerentel", "starttime", "deadtime", "bak", "bak2" ];

	var arrs = [ "serial", "serialname", "description", "fuzeren",
			"fuzerentel", "starttime", "deadtime", "bak2", "bak" ];
	var dic = {
		serial : "项目编号",
		serialname : "项目名称",
		description : "项目描述",
		fuzeren : "负责人",
		fuzerentel : "联系号码",
		starttime : "开始时间",
		deadtime : "截至时间",
		bak2 : "其他",
		bak:"最后登记时间"
	};

	$(document).ready(function() {
		loading();
		for(var i=0;i<arrs.length;i++){
			$("#"+arrs[i]).attr("disabled",true);
		} 
	});
	function loading() {
		var t = "<table border='1' style='border-collapse: collapse;'><tr align='center'><th></th><th>修改前</th></tr>";
		var t1;
		var modproj =
<%=project.toJson()%>
	;
		for (var i = 0; i < arrs.length; i++) {
			t1 += "<tr><td>" + dic[arrs[i]] + "</td><td><input type='text' id='"+arrs[i]+"' value='"+modproj[arrs[i]]+"'></td></tr>";
		}
		t += t1
				+ "<tr><td align='center' colspan='2'><input type='button' value='提交' onclick='onSubmit()'>&nbsp;&nbsp;<input type='button' value='重置' onclick='reSet()'></td></tr></table>"
		$("#main").html(t);
	}

	function onSubmit() {
		var param = "{";
		for (var i = 0; i < projmanage.length; i++) {
			if (projmanage.length - i > 1) {
				param += "\"" + projmanage[i] + "\":\""
						+ $("#" + projmanage[i]).val() + "\","
			} else {
				param += "\"" + projmanage[i] + "\":\""
						+ $("#" + projmanage[i]).val() + "\"";
			}
		}
		param += "}";
		/* alert(param); */

		$.ajax("${pageContext.request.contextPath}/project/del",// 发送请求的URL字符串。
		{
			dataType : "json", // 预期服务器返回的数据类型。
			type : "post", //  请求方式 POST或GET
			contentType : "application/json", //  发送信息至服务器时的内容编码类型
			// 发送到服务器的数据。
			data : param,
			async : true, // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
			// 请求成功后的回调函数。
			success : function(data) {
				if (data.retcode == 0) {
					alert("ok");
					window.location.href="${pageContext.request.contextPath}/project/search";
				} else {
					alert(data.retMsg);
				}
			},
			// 请求出错时调用的函数
			error : function() {
				alert("数据发送失败");
			}
		});
	}
</script>

<body>

	<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
	<a href="${pageContext.request.contextPath}/project/search">查询</a>
	<a href="${pageContext.request.contextPath}/project/add">新增</a>
	<br>
	<fieldset>
		<legend>删除</legend>
		<div id="main" align="center"></div>
	</fieldset>
</body>
</html>