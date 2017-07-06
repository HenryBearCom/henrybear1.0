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
				var param = {account:"",name:"",idcard:"",tel:"",email:"",flag:""};
				param.account = $("#account").val();
				param.name = $("#name").val();
				param.idcard= $("#idcard").val();
				param.tel = $("#tel").val();
				param.email = $("#email").val();
				param.flag = "";
				$.ajax({
					type:"POST",
					url		:	"http://localhost:8080/henrybear1.0/servlet/HttpServiceHttp",
					data	:	param,
					dataType:	"text",
					success	:	function(data){
						 alert(data); 
						 var datas = data.split("|");
						 param.account = datas[1];
						 location.href ="http://localhost:8080/henrybear1.0"+ datas[0];
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
		    		<tr><td align="right">账号*：</td><td align="left"><input type="text" id="account" name="account" style="width:200px;"/></td></tr>
		    		<tr><td align="right">姓名*：</td><td align="left"><input type="text" id="name" name="name" style="width:200px;"/></td></tr>
		    		<tr><td align="right">身份证号*：</td><td align="left"><input type="text" id="idcard" name="idcard" style="width:200px;"/></td></tr>
		    		<tr><td align="right">电话*：</td><td align="left"><input type="text" id="tel" name="tel" style="width:200px;"/></td></tr>
		    		<tr><td align="right">邮箱*：</td><td align="left"><input type="text" id="email" name="email" style="width:200px;"/></td></tr>
		    		<tr><td align="right">个人介绍 :</td><td align="left"><textarea rows="5" cols="40"></textarea></td></tr>
		    		<tr><td colspan="2" align="center"><input type="button" id="btn" name="btn" value="下&nbsp;一&nbsp;步" style="width:100px;"/></td></tr>
		    	</table>
	    	</form>
    	</fieldset>
    </div>
  </body>
</html>
