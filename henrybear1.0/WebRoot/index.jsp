<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工程管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript"
	src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>

<style>
	.content{width: 100%; height: 100%; }
	form{display:block; margin: 30px 20px;}
	input{padding: 5px;}
</style>
	
  </head>
  
  <body>
    This is my JSP page. <br>
    
    <a href="${pageContext.request.contextPath}/info/register">注册</a><br>
    <a href="${pageContext.request.contextPath}/project/add">新增</a><br>
    <a href="${pageContext.request.contextPath}/project/search">查询</a><br>
    <br><br><br>
    <center>
<div class="content">
</div>
</center>
<script>
//  level分为 YM YMD H HM 四个有效值，分别表示年月 年月日 年月日时 年月日时分,less表示是否不可小于当前时间。年-月-日 时:分 时为24小时制
//  为确保控件结构只出现一次，在有需要的时候进行一次调用。
    onLoadTimeChoiceDemo();

    borainTimeChoice({
        start:".start",
        end:".end",
        level:"YM",
        less:true
    });

    borainTimeChoice({
        start:".start-two",
        end:"",
        level:"H",
        less:false
    });


</script>
    
    
  </body>
</html>
