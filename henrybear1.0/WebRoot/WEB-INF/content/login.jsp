<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
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
				var param = {account:"",flag:"",password:""};
				param.account = $("#account").val();
				param.password = $("#pwd").val();
				
				$.ajax("${pageContext.request.contextPath}/info/login",// 发送请求的URL字符串。
						{
						dataType : "json", // 预期服务器返回的数据类型。
			   			type : "post", //  请求方式 POST或GET
					   contentType:"application/json", //  发送信息至服务器时的内容编码类型
					   // 发送到服务器的数据。
					   data:JSON.stringify(param),
					   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
					   // 请求成功后的回调函数。
					   success :function(msg){
							var flag = msg.flag;
							var retcode = msg.retcode;
							var retMsg = msg.retMsg;
							alert(msg.flag+" "+retcode+" "+retMsg); 
							
							/* if(flag == "true" && retcode == 0){
								alert(msg.retMsg+"注册成功");
								location.href ="${pageContext.request.contextPath}/info/login&account"+msg.retMsg;
							}else{
								alert("注册失败，"+msg.retMsg);
								location.href ="${pageContext.request.contextPath}/info/register";
							}  */
						   
					   },
					   // 请求出错时调用的函数
					   error:function(){
						   alert("数据发送失败");
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
		    		<tr><td align="right">账号*：</td>
		    		<td align="left" style="width:400px;"><input type="text" id="account" name="account" style="width:200px;" placeholder="账号由英文字母、数字组成"/>
		    		</tr>
		    		<tr><td align="right">密码 :</td><td align="left"><input type="password" id="pwd" name="password" style="width:200px;"/></td></tr>
		    		<tr><td colspan="2" align="center"><input type="button" id="btn" name="btn" value="下&nbsp;一&nbsp;步" style="width:100px;"/></td></tr>
		    	</table>
	    	</form>
    	</fieldset>
    </div>
  </body>
</html>
