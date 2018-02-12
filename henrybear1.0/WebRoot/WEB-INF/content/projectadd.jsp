<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增</title>
<script language="javascript"
	src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
</head>
<script language="javascript">
$(document).ready(function(){
	init();
});

var arrs = ["serial","serialname","description","fuzeren","fuzerentel","starttime","deadtime","bak2","bak"];
var dic = {
		serial:"项目序号",
		serialname:"项目名称",
		description:"项目描述",
		fuzeren:"负责人",
		fuzerentel:"联系号码",
		starttime:"项目开始时间",
		deadtime:"项目截至时间",
		bak2:"其他",
		bak:"bak"
};
function init(){
	var str = "<fieldset><legend>新增</legend><table>";
	for(var i=0;i<arrs.length;i++){
		str += "<tr><td align=\"right\"> "+dic[arrs[i]]+":</td><td align=\"left\"><input type=\"text\" id=\""+arrs[i]+"\" name=\""+arrs[i]+"\" style=\"width: 200px;\" /></td>	</tr>"
	}
	str += "<tr><td colspan=\"2\" align=\"center\"><input type=\"button\" id=\"btn\" name=\"btn\" value=\"新增\" style=\"width: 100px;\" /></td>	</tr></table></fieldset>";
	$("#main").html(str);
}

		$(function(){
			$("#btn").click(function(){
				var param = {
						serial:"",
						serialname:"",
						description:"",
						fuzeren:"",
						fuzerentel:"",
						starttime:"",
						deadtime:"",
						others:""
				};
				param.serial = $("#serial").val();
				param.serialname = $("#serialname").val();
				param.description = $("#description").val();
				param.fuzeren = $("#fuzeren").val();
				param.fuzerentel = $("#fuzerentel").val();
				param.starttime = $("#starttime").val();
				param.deadtime = $("#deadtime").val();
				param.others = $("#bak2").val();
				
				$.ajax("${pageContext.request.contextPath}/project/add",// 发送请求的URL字符串。
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
							/* alert(msg.flag+" "+retcode+" "+retMsg); */ 
							if(retcode == 0){
								alert(msg.retMsg+"导入成功");
								location.href ="${pageContext.request.contextPath}/project/add";
							}else{
								alert("导入失败，"+msg.retMsg);
								location.href ="${pageContext.request.contextPath}/project/add";
							}
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

    <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    <a href="${pageContext.request.contextPath}/project/search">search</a>
	<div id="main" align="center">
		<!-- <fieldset>
			<legend>新增</legend>
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
					<td align="right">项目描述 :</td>
					<td align="left"><textarea type="text" id="description"
							name="description" style="width: 200px;" ></textarea></td>
				</tr>
				<tr>
					<td align="right">负责人 :</td>
					<td align="left"><input type="text" id="fuzeren"
						name="fuzeren" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td align="right">联系号码 :</td>
					<td align="left"><input type="text" id="fuzerentel"
						name="fuzerentel" style="width: 200px;" /></td>
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
					<td align="right">其他 :</td>
					<td align="left"><textarea type="text" id="bak2"
							name="bak2" style="width: 200px;"></textarea></td>
				</tr>
				<tr>
					<td align="right">附件 :</td>
					<td align="left"><form action="/project/upload"><input type="file" id="attachment"
							name="attachment" style="width: 200px;"><input type="submit" value="上传" id="upload" ></form></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" id="btn"
						name="btn" value="新增" style="width: 100px;" /></td>
				</tr>

			</table>
		</fieldset> -->
	</div>
</body>
</html>