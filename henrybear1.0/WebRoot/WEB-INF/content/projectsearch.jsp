<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
<script language="javascript"
	src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
</head>
<script language="javascript">

var params=new Array();
var param = {
		serial:"",
		serialname:"",
		description:"",
		fuzeren:"",
		fuzerentel:"",
		starttime:"",
		deadtime:"",
		bak:"",
		bak2:""
};
function mod(){
	var radios = document.getElementsByName("caozuo");
	for(var i =0;i<radios.length;i++){
		if(radios[i].checked==true){
			var param = params[i];
			window.location.href="${pageContext.request.contextPath}/project/mod?q="+param.serial;
			return;
		}
	}
	alert("请选择需要修改的项");
}
function del(){
	var radios = document.getElementsByName("caozuo");
	for(var i =0;i<radios.length;i++){
		if(radios[i].checked==true){
			var param = params[i];
			window.location.href="${pageContext.request.contextPath}/project/del?q="+param.serial;
			return;
		}
	}
	alert("请选择需要修改的项");
}

function optioned(){
	/* alert("hello world"); */
	var radios = document.getElementsByName("caozuo");
	for(var i =0;i<radios.length;i++){
		if(radios[i].checked==true){
			var obj = document.getElementById("tr"+i);
			obj.style.backgroundColor="red";
			return;
		}
	}
}


		$(function(){
			$("#btn").click(function(){
				param.serial = $("#serial").val();
				param.serialname = $("#serialname").val();
				param.description = $("#description").val();
				param.fuzeren = $("#fuzeren").val();
				param.fuzerentel = $("#fuzerentel").val();
				param.starttime = $("#starttime").val();
				param.deadtime = $("#deadtime").val();
				var n = 0;
				/* alert(JSON.stringify(param)); */
				$.ajax("${pageContext.request.contextPath}/project/search",// 发送请求的URL字符串。
						{
						dataType : "json", // 预期服务器返回的数据类型。
			   			type : "post", //  请求方式 POST或GET
					   contentType:"application/json", //  发送信息至服务器时的内容编码类型
					   // 发送到服务器的数据。
					   data:JSON.stringify(param),
					   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
					   // 请求成功后的回调函数。
					   success :function(data){
						   var listable = "<fieldset><legend>查询结果</legend><table border='1' style='border-collapse: collapse;'><tr align='center'><th>序号</th><th>项目序号</th><th>项目名称</th><th>项目描述</th><th>负责人</th><th>联系号码</th><th>项目开始时间</th><th>项目截至时间</th><th>其他</th><th>登记时间</th><th>操作</th></tr>";
						   $.each(data,function(){
							   param.serial = this.serial;
								param.serialname = this.serialname;
								param.description = this.description;
								param.fuzeren = this.fuzeren;
								param.fuzerentel = this.fuzerentel;
								param.starttime = this.starttime;
								param.deadtime = this.deadtime;
								param.bak2 = this.bak2;
								param.bak = this.bak;
								n=n+1; 
							   params.push(param);
							   param = {
										serial:"",
										serialname:"",
										description:"",
										fuzeren:"",
										fuzerentel:"",
										starttime:"",
										deadtime:"",
										bak:"",
										bak2:""
								};
							   listable = listable+"<tr align='center' id='tr"+n+"'/><td>"+n+"</td> <td>"+this.serial+"</td> <td>"+this.serialname+"</td>"
							   +"<td>"+this.description+"</td><td>"+this.fuzeren+"</td>"
							   +"<td>"+this.fuzerentel+"</td><td>"+this.starttime+"</td><td>"+this.deadtime+"</td><td>"
							   +this.bak2+"</td><td>"+this.bak+"</td><td>"+"<input type='radio' id='caozuo"+n+"' name='caozuo' value=\""+n+"\" onclick='optioned();'/></td></tr>";
							   
						   })
					        listable = listable +"<tr><td colspan='11' align='center'><input type='button' value='修改' name='mod' id='mod' onclick='mod()' />&nbsp;&nbsp;<input type='button' value='删除' name='del' id='del' onclick='del()' /></td></tr></table></fieldset>";
					        $("#projectlist").html(listable);
					   },
					   // 请求出错时调用的函数
					   error:function(){
						   alert("数据发送失败");
					   }
				});
				
			});
			
			
		})
	</script>
<body>
	<div id="main" align="center">
		<table>
			<tr>
				<td align="right">项目序号 :</td>
				<td align="left"><input type="text" id="serial" name="serial"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="right">项目名称 :</td>
				<td align="left"><input type="text" id="serialname"
					name="serialname" style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="right">负责人 :</td>
				<td align="left"><input type="text" id="fuzeren" name="fuzeren"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="right">项目开始时间 :</td>
				<td align="left"><input type="text" id="starttime"
					name="starttime" style="width: 200px;" /></td>
			</tr>
			<tr>
				<td align="right">项目截至时间 :</td>
				<td align="left"><input type="text" id="deadtime"
					name="deadtime" style="width: 200px;" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" id="btn"
					name="btn" value="查询" style="width: 100px;" /></td>
			</tr>

		</table>
	</div>
	<div align="center" id="projectlist">
		<!-- <fieldset>
			<legend>查询结果</legend>
			<table id="projectlist" border="1" style="border-collapse: collapse;">
				<tr align="center">
					<th>项目序号</th>
					<th>项目名称</th>
					<th>项目描述</th>
					<th>负责人</th>
					<th>联系号码</th>
					<th>项目开始时间</th>
					<th>项目截至时间</th>
					<th>其他</th>
					<th>登记时间</th>
				</tr>
			</table>
		</fieldset> -->
	</div>
</body>
</html>