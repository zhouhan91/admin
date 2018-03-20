
function doLogin(){
	var phone=$("#phone").val();
	var password=$("#password").val();
	if(phone=="" || password==""){
		alert("请填写登录信息！");
		return ;
	}
	//提交登录
	$.ajax({
		url:webRoot+"/sysUser/doLogin?v="+Math.random(),
		contentType:"application/json;charset=UTF-8",
		dataType:"json",
		type:"post",
		data:JSON.stringify({"phone":phone, "password":password}),
		success:function(data){
			if("999999"==data.resultCode){
				// 跳转
				location.href = webRoot+"/frame";
			}else{
				alert(data.resultDesc);
				return;
			}
		},
		error:function(){
		}
	});
}