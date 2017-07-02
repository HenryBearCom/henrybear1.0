<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
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
				var param = {account:"",name:"",idcard:"",tel:"",email:""};
				param.account = document.form1.account.value;
				param.name = document.form1.name.value;
				param.idcard= document.form1.idcard.value;
				param.tel = document.form1.tel.value;
				param.email = document.form1.email.value;
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
    <div id="main" style="position:absolute;z-index:1000;">
    	<form name="form1">
	    	<table align="center">
	    		<tr><td align="right">账号：</td><td align="left"><input type="text" id="account" name="account" style="width:200px;"/></td></tr>
	    		<tr><td align="right">姓名：</td><td align="left"><input type="text" id="name" name="name" style="width:200px;"/></td></tr>
	    		<tr><td align="right">身份证号：</td><td align="left"><input type="text" id="idcard" name="idcard" style="width:200px;"/></td></tr>
	    		<tr><td align="right">电话：</td><td align="left"><input type="text" id="tel" name="tel" style="width:200px;"/></td></tr>
	    		<tr><td align="right">邮箱：</td><td align="left"><input type="text" id="email" name="email" style="width:200px;"/></td></tr>
	    		<tr><td colspan="2" align="left">xmlString:</td></tr>
	    		<tr><td colspan="2" align="left"><textarea rows="5" cols="40"></textarea></td></tr>
	    		<tr><td colspan="2" align="center"><input type="button" id="btn" name="btn" value="提&nbsp;&nbsp;&nbsp;交" style="width:100px;"/></td></tr>
	    	</table>
    	</form>
    </div>
  </body>
</html>
