<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String account = "";
	Cookie[] cookies = request.getCookies();
	try{
		for(Cookie cookie :cookies){
			if(cookie.getName().trim().equalsIgnoreCase("account")){
				account = cookie.getValue().trim();
				cookie.setValue("");
				cookie.setMaxAge(0);
			}
		}
	}catch (NullPointerException e){
		account = "";
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%if(account == "" || account == null){ %>
    <jsp:forward page="/test.jsp"></jsp:forward>
    <%} %>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<script language="javascript" src="D:/jar/jquery-3.2.1/jquery-3.2.1.min.js"></script>-->
	<script language="javascript" src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
	
  </head>
  <script language="javascript">
		$(function(){
			$("#btn").click(function(){
				var pwd1 = $("#pwd").val();
				var pwd2 = $("#pwd1").val();
				if(pwd1 != pwd2){
					alert("两次输入的密码不一致");
					return false;
				}
				if(pwd1.length < 6){
					alert("密码位数不得小于6位");
					return false;
				}
				var param = {pwd:"",flag:"",account:"<%=account %>"};
				param.pwd = pwd1;
				param.flag = "reg";
				$.ajax({
					type:"POST",
					url		:	"http://localhost:8080/henrybear1.0/servlet/HttpServiceHttp",
					data	:	param,
					dataType:	"text",
					success	:	function(data){
						alert(data);
					},
					error	:	function(XMLHttpRequest,textStatus,errorThrow){
						alert(textStatus);
					}
				});
				
			})
		})
	</script>
  <body>
    <div id="main" style="position:absolute;left:30px;top:20px;z-index:1000;">
    	<fieldset>
    		<legend>注册</legend>
	    	<form name="form1">
		    	<table align="center">
		    		<tr><td align="right">密码*：</td><td align="left"><input type="password" placeholder="请设置登陆密码" id="pwd" name="pwd" style="width:200px;"/></td></tr>
		    		<tr><td align="right">密码*：</td><td align="left"><input type="password" placeholder="请再次设置登陆密码" id="pwd1" name="pwd1" style="width:200px;"/></td></tr>
		    		<tr><td colspan="2" align="center"><input type="button" id="btn" name="btn" value="提&nbsp;&nbsp;&nbsp;交" style="width:100px;"/></td></tr>
		    	</table>
	    	</form>
    	</fieldset>
    </div>
  </body>
</html>
