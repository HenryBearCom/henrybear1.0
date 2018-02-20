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
				var param = {account:"",name:"",sex:"",idcard:"",tel:"",email:"",flag:"",password:""};
				param.account = $("#account").val();
				param.name = $("#name").val();
				param.sex = $("#sex").val();
				param.idcard= $("#idcard").val();
				param.tel = $("#tel").val();
				param.email = $("#email").val();
				param.flag = "";

				if(param.account == "" || param.name == "" || param.idcard == ""){
					alert("账号、姓名、身份证号不能为空");
					return false;
				}
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
				param.password = $("#pwd").val();
				
				$.ajax("${pageContext.request.contextPath}/info/register",// 发送请求的URL字符串。
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
							/* alert(msg.flag); */
							
							if(retcode == 0){
								alert(msg.retMsg+"注册成功");
								location.href ="${pageContext.request.contextPath}/info/login";
							}else{
								alert("注册失败，"+msg.retMsg);
								location.href ="${pageContext.request.contextPath}/info/register";
							} 
						   
					   },
					   // 请求出错时调用的函数
					   error:function(){
						   alert("数据发送失败");
					   }
				});
				
			})
			function checks(t){
			   szMsg="[#_%&'/,;:=!^\"]";
			   alertStr="";
			   for(i=1;i<szMsg.length+1;i++){
				    if(t.indexOf(szMsg.substring(i-1,i))>-1){
					    alertStr="请勿包含非法字符如[#_%&'/,;:=!^\"]";
				    	alert(alertStr);
				   		return false;
				    }
			   }
			}
			
			function passwdCheck(){
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
				return pwd1;
			}
		})
	</script>
  <body>
    <a href="${pageContext.request.contextPath}/">首页</a>
    <a href="${pageContext.request.contextPath}/project/add">新增</a>
    <a href="${pageContext.request.contextPath}/project/search">查询</a>
    <br>
    <div id="main" align="center"><!--  style="position:absolute;left:30px;top:20px;z-index:1000;"> -->
    	<fieldset>
    		<legend>注册</legend>
	    	<form name="form1">
		    	<table align="center">
		    		<tr><td align="right">账号*：</td>
		    		<td align="left" style="width:400px;"><input type="text" id="account" name="account" style="width:200px;" placeholder="账号由英文字母、数字组成"/>
		    		</tr>
		    		<tr><td align="right">姓名*：</td><td align="left"><input type="text" id="name" name="name" style="width:200px;"/></td></tr>
		    		<tr><td align="right">性别*：</td>
		    		<td align="left">
					<select id="sex" style="width:80px;">
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
					</td></tr>
		    		<tr><td align="right">身份证号*：</td><td align="left"><input type="text" id="idcard" name="idcard" style="width:200px;"/></td></tr>
		    		<tr><td align="right">电话*：</td><td align="left"><input type="text" id="tel" name="tel" style="width:200px;"/></td></tr>
		    		<tr><td align="right">邮箱*：</td><td align="left"><input type="text" id="email" name="email" style="width:200px;"/></td></tr>
		    		<tr><td align="right">个人介绍 :</td><td align="left"><textarea rows="5" cols="40"></textarea></td></tr>
		    		<tr><td align="right">密码 :</td><td align="left"><input type="password" id="pwd" name="password" style="width:200px;"/></td></tr>
		    		<tr><td align="right">再次输入密码 :</td><td align="left"><input type="password" id="pwd1" name="password" style="width:200px;"/></td></tr>
		    		<tr><td colspan="2" align="center"><input type="button" id="btn" name="btn" value="下&nbsp;一&nbsp;步" style="width:100px;"/></td></tr>
		    	</table>
	    	</form>
    	</fieldset>
    </div>
  </body>
</html>
