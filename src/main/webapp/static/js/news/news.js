function initTimeInput() {
	$("#publishTime").datetimepicker({
		format : "yyyy-mm-dd hh:ii:ss",
		startView : "month",
		minView : "hour",
		language : "zh-CN"
	});
}

function loadSubNavigation(parentCode, obj, code) {
	if (!parentCode || parentCode == "") {
		return;
	}
	$(obj).empty();
	$.ajax({
		url : webRoot + "/subNavigation/getSubNavigation?navigationCode=" + parentCode + "&v=" + Math.random(),
		type : "get",
		success : function(data) {
			if (data.resultCode == "999999") {
				var subNavigationList = data.resultData;
				if (subNavigationList && subNavigationList.length > 0) {
					for (var i = 0; i < subNavigationList.length; i++) {
						var subNavigation = subNavigationList[i];
						if (subNavigation.code == code) {
							$(obj).append("<option value='" + subNavigation.code + "' selected='selected'>" + subNavigation.name + "</option>");
						} else {
							$(obj).append("<option value='" + subNavigation.code + "'>" + subNavigation.name + "</option>");
						}
					}
				}
			}
		},
		error : function(a, b, c) {
		}
	});
}

function save() {
	var dataStr = {
		"id" : $("#newsId").val(),
		"title" : $("#title").val(),
		"summary" : $("#summary").val(),
		"coverPicture" : $("#coverPicture").val(),
		"author" : $("#author").val(),
		"keyWords" : $("#keyWords").val(),
		"navigationCode" : $("#navigationCode").val(),
		"navigationName" : $("#navigationCode option:selected").text(),
		"subNavigationCode" : $("#subNavigationCode").val(),
		"subNavigationName" : $("#subNavigationCode option:selected").text(),
		"readCount" : $("#readCount").val(),
		"likeCount" : $("#likeCount").val(),
		"markCount" : $("#markCount").val(),
		"publishTime" : $("#publishTime").val(),
		"content" : editor.txt.html()
	};
	$.ajax({
		url : webRoot + "/news/save?v=" + Math.random(),
		contentType : "application/json",
		type : "post",
		dataType : "json",
		data : JSON.stringify(dataStr),
		success : function(data) {
			if (data.resultCode == "999999") {
				$("#newsId").val(data.resultData);
				alert("保存成功！");
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("操作失败！");
		}
	});
}

function deleteNews() {
	var checkedBox = $("input[name='box']:checked");
	if (!checkedBox || checkedBox.length <= 0) {
		alert("请选择记录！");
		return;
	}
	var idStr = "";
	$(checkedBox).each(function() {
		idStr += $(this).val() + ",";
	});
	if (idStr == "") {
		alert("请选择记录！");
		return;
	}
	if (confirm("确定删除选中的记录？")) {
		$.ajax({
			url : webRoot + "/news/delete?idStr=" + idStr + "&v=" + Math.random(),
			type : "post",
			success : function(data) {
				if (data.resultCode == "999999") {
					alert("操作成功！");
					location.reload();
				} else {
					alert(data.resultDesc);
				}
			},
			error : function() {
				alert("操作失败！");
			}
		});
	}
}

function putOnLine() {
	var id = $("#newsId").val();
	if (id == "" || id == 0) {
		alert("保存后才允许上架和下架！");
		return;
	}
	$.ajax({
		url : webRoot + "/news/putOnLine?id=" + id + "&v=" + Math.random(),
		type : "post",
		success : function(data) {
			if (data.resultCode == "999999") {
				alert("操作成功！");
				return;
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("操作失败！");
		}
	});
}

function putOffLine() {
	var id = $("#newsId").val();
	if (id == "" || id == 0) {
		alert("保存后才允许上架和下架！");
		return;
	}
	$.ajax({
		url : webRoot + "/news/putOffLine?id=" + id + "&v=" + Math.random(),
		type : "post",
		success : function(data) {
			if (data.resultCode == "999999") {
				alert("操作成功！");
				return;
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("操作失败！");
		}
	});
}

/**
 * 上传文件
 * 
 * @param busiCode 业务编码
 */
function chooseFile(busiCode) {
	curBusiCode = busiCode;
	$("#busiCode").val(busiCode);
	$("#fileUploadBtn").click();
}
var curBusiCode;
function upload() {
	$("#fileUploadForm").ajaxSubmit({
		url : webRoot + "/components/fileUpload?v=" + Math.random(),
		type : "post",
		success : function(data) {
			if (data.resultCode == "999999") {
				var path = data.resultData;
				$("#coverPicture").val(path);
				$("#coverPictureImg").attr("src", path);
			} else {
				alert(data.resultDesc);
			}
		},
		error : function(a, b, c) {

			alert("上传失败！");
		}
	});
}

function addBabieta() {
	var html = "<div class='form-group tr' >";
	html += "<label class='sr-only title' style='margin-right: 25px;'>内容：</label><input type='text' name='babietaContent' class='form-control' placeholder='请输入内容' style='width: 80%;' /><a  style='margin-left: 50px;' onclick='deleteBabieta(this, 0);'>删除</a>";
	html += "</div>";
	$("#babietaContentDIV").append(html);
}

function saveBabieta() {
	var dataStr = {
		"id" : $("#newsId").val(),
		"title" : $("#title").val(),
		"coverPicture" : $("#coverPicture").val()
	};
	var lstBabieta=[];
	$("input[name='babietaContent']").each(function(){
		var id=$(this).attr("babietaId");
		var content=$(this).val();
		if(content.trim()!=""){
			lstBabieta.push({"id":id,"content":content});
		}
	});
	dataStr.lstBabieta=lstBabieta;
	$.ajax({
		url : webRoot + "/news/saveBabieta?v=" + Math.random(),
		contentType : "application/json",
		type : "post",
		dataType : "json",
		data : JSON.stringify(dataStr),
		success : function(data) {
			if (data.resultCode == "999999") {
				var news=data.resultData;
				alert("保存成功！");
				location.href=webRoot + "/news/initUpdateBabieta/"+news.id;
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("操作失败！");
		}
	});
	
}


function deleteBabieta(obj, id){
	if(id != "0"){
		$.ajax({
			url : webRoot + "/news/deleteBabieta/" + id + "?v=" + Math.random(),
			type : "post",
			async: false,
			success : function(data) {
				if (data.resultCode == "999999") {
					alert("操作成功！");
					return;
				} else {
					alert(data.resultDesc);
				}
			},
			error : function() {
				alert("操作失败！");
			}
		});
	}
	$(obj).parent().remove();
}














