function initTimeInput() {
	$("#roomDateAllowedStart").datetimepicker({
		format : "yyyy-mm-dd",
		startView : "month",
		minView : "month",
		language : "zh-CN"
	});
	$("#roomDateAllowedEnd").datetimepicker({
		format : "yyyy-mm-dd",
		startView : "month",
		minView : "month",
		language : "zh-CN"
	});
}

/**
 * 删除房源
 */
function deleteCommunity(){
	var communitys=$("input[name='box']:checked");
	if(!communitys || communitys.length<=0){
		return ;
	}
	var idStr="";
	$(communitys).each(function(){
		idStr+=$(this).val()+",";
	});
	if(confirm("是否要删除选中的记录？")){
		$.ajax({
			url:webRoot+"/community/delete?idStr="+idStr+"&v="+Math.random(),
			type:"post",
			success:function(data){
				if(data.resultCode=="999999"){
					alert("操作成功！");
					location.reload();
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}

/**
 * 保存房源信息
 */
function saveCommunityInfo() {
	var id = $("#communityId").val();
	var coverPicture=$("#coverPicture").val();
	var supplier = $("#supplier").val();
	var supplierName = $("#supplier option:selected").text();
	var sourceTypeKey = $("#sourceType").val();
	var sourceType = $("#sourceType option:selected").text();
	var link = $("#link").val();
	var name = $("#name").val();
	var localName = $("#localName").val();
	var typeKey = $("#type").val();
	var type = $("#type option:selected").text();
	var leaseTypeKey = $("#leaseType").val();
	var leaseType = $("#leaseType option:selected").text();
	var depositTypeKey = $("#depositType").val();
	var depositType = $("#depositType option:selected").text();
	var leaseLimit = $("#leaseLimit").val();
	var owner = $("#owner").val();
	var countryCode = $("#countryCode").val();
	var countryName = $("#countryCode option:selected").text();
	var cityCode = $("#cityCode").val();
	var cityName = $("#cityCode option:selected").text();
	var districtCode = $("#districtCode").val();
	var districtName = $("#districtCode option:selected").text();
	var street = $("#street").val();
	var address = $("#address").val();
	var longitude = $("#longitude").val();
	var latitude = $("#latitude").val();
	var routeDescription = $("#routeDescription").val();
	var advantage = $("#advantage").val();
	var description = $("#description").val();
	var tip = $("#tip").val();
	var videoUrl = $("#videoUrl").val();
	var sortNum = $("#sortNum").val();
	var keyWords = $("#keyWords").val();
	var ownerPhone = $("#ownerPhone").val();
	var ownerWechat = $("#ownerWechat").val();
	var ownerEmail = $("#ownerEmail").val();

	var privateFacilities = getDictionaryCheckBox("privateFacilities");
	var privateFacilitiesKey = privateFacilities[0];

	var commonFacilities = getDictionaryCheckBox("commonFacilities");
	var commonFacilitiesKey = commonFacilities[0];

	var rentFacilities = getDictionaryCheckBox("rentFacilities");
	var rentFacilitiesKey = rentFacilities[0];

	// 租期(短租/长租)
	var arrLeaseModel = getDictionaryCheckBox("leaseModel");
	var leaseModelKey = arrLeaseModel[0];
	var leaseModel = arrLeaseModel[1];
	// 房型(一房一厅/套房整租)
	var arrRoomType = getDictionaryCheckBox("communityRoomType");
	var roomTypeKey = arrRoomType[0];
	var roomType = arrRoomType[1];
	// 卫浴设施
	var arrBathroomType = getDictionaryCheckBox("bathroomType");
	var bathroomTypeKey = arrBathroomType[0];
	var bathroomType = arrBathroomType[1];

	var dataStr = {
		"id" : id,
		"coverPicture":coverPicture,
		"name" : name,
		"localName" : localName,
		"sourceTypeKey" : sourceTypeKey,
		"sourceType" : sourceType,
		"countryCode" : countryCode,
		"countryName" : countryName,
		"cityCode" : cityCode,
		"cityName" : cityName,
		"districtCode" : districtCode,
		"districtName" : districtName,
		// "price":price,
		// "discountPrice":discountPrice,
		"title" : name,
		"localTitle" : localName,
		"description" : description,
		"address" : address,
		"grade" : 5,
		"leaseModelKey" : leaseModelKey,
		"leaseModel" : leaseModel,
		"typeKey" : typeKey,
		"type" : type,
		"leaseTypeKey" : leaseTypeKey,
		"leaseType" : leaseType,
		"depositTypeKey" : depositTypeKey,
		"depositType" : depositType,
		"bathroomTypeKey" : bathroomTypeKey,
		"bathroomType" : bathroomType,
		"privateFacilities" : privateFacilitiesKey,
		"commonFacilities" : commonFacilitiesKey,
		"rentFacilities" : rentFacilitiesKey,
		"owner" : owner,
		"supplierId" : supplier,
		"supplierName" : supplierName,
		"roomTypeKey" : roomTypeKey,
		"roomType" : roomType,
		"routeDescription" : routeDescription,
		"advantage" : advantage,
		"tip" : tip,
		"longitude" : longitude,
		"latitude" : latitude,
		"videoUrl":videoUrl,
		"sortNum":sortNum,
		"keyWords":keyWords,
		"ownerPhone":ownerPhone,
		"ownerWechat":ownerWechat,
		"ownerEmail":ownerEmail
	};

	$.ajax({
		url : webRoot + "/community/save?v=" + Math.random(),
		contentType : "application/json",
		type : "post",
		dataType : "json",
		data : JSON.stringify(dataStr),
		success : function(data) {
			if (data.resultCode == "999999") {
				alert("保存成功！");
				location.href=webRoot+"/community/initUpdate?id="+data.resultData+"&v="+Math.random();
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("保存失败！");
		}
	});
}

function getDictionaryCheckBox(name) {
	var arr = [];
	var keys = "";
	var values = "";
	if ($("input[name='" + name + "']:checked").length <= 0) {
		arr.push(keys);
		arr.push(values);
		return arr;
	}
	$("input[name='" + name + "']:checked").each(function() {
		keys += "," + $(this).val();
		values += " " + $(this).attr("tname");
	});
	keys += ",";
	arr.push(keys);
	arr.push(values);
	return arr;
}

/**
 * 点击国家，显示城市
 * 
 * @param country 国家
 */
function initAddress(selectedCountryCode, selectedCityCode, selectedDistrictCode) {
	if (selectedCountryCode == "") {
		selectedCountryCode=$("#countryCode option:selected").val();
	}
	$("#cityCode").empty();
	// 显示国家的城市
	$.ajax({
		url : webRoot + "/city/getCityListByCountryCode/" + selectedCountryCode + "?v=" + Math.random(),
		type : "get",
		dataType : "json",
		success : function(data) {
			if (data.resultCode == "999999") {
				var cityList = data.resultData;
				if (cityList && cityList.length > 0) {
					for (var i = 0; i < cityList.length; i++) {
						var city = cityList[i];
						if (city.code == selectedCityCode) {
							$("#cityCode").append("<option value='" + city.code + "' selected='selected'>" + city.name + "-" + city.chineseName + "</option>");
						} else {
							$("#cityCode").append("<option value='" + city.code + "'>" + city.name + "-" + city.chineseName + "</option>");
						}
					}
					if (selectedCityCode == "") {
						selectedCityCode = cityList[0].code;
					}
				}
				showDistrict(selectedCityCode, selectedDistrictCode)
			}
		},
		error : function() {
		}
	});
}

function showDistrict(cityCode, selectedDistrictCode) {
	$("#districtCode").empty();
	$.ajax({
		url : webRoot + "/district/getDistrictListByCityCode/" + cityCode + "?v=" + Math.random(),
		type : "get",
		dataType : "json",
		success : function(data) {
			if (data.resultCode == "999999") {
				var districtList = data.resultData;
				if (districtList && districtList.length > 0) {
					for (var i = 0; i < districtList.length; i++) {
						var district = districtList[i];
						if (district.code == selectedDistrictCode) {
							$("#districtCode").append("<option value='" + district.code + "' selected='selected'>" + district.name + "-" + district.chineseName + "</option>");
						} else {
							$("#districtCode").append("<option value='" + district.code + "'>" + district.name + "-" + district.chineseName + "</option>");
						}
					}
				}
			}
		}
	});
}

/**
 * 上架
 */
function communityOnLine() {
	var id=$("#communityId").val();
	if(id=="" || id<=0){
		alert("请先保存后上架！");
		return ;
	}
	if(confirm("确定上架？")){
		$.ajax({
			url:webRoot+"/community/putOnLine?id="+id+"&v="+Math.random(),
			type:"post",
			success:function(data){
				if(data.resultCode=="999999"){
					alert("操作成功！");
				}else{
					alert(data.resultDesc);
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}

/**
 * 下架
 */
function communityOffLine() {
	var id=$("#communityId").val();
	if(id=="" || id<=0){
		alert("请先保存后下架！");
		return ;
	}
	if(confirm("确定下架？")){
		$.ajax({
			url:webRoot+"/community/putOffLine?id="+id+"&v="+Math.random(),
			type:"post",
			success:function(data){
				if(data.resultCode=="999999"){
					alert("操作成功！");
				}else{
					alert(data.resultDesc);
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}

/**
 * 显示图片框
 * 
 * @param src
 */
function showImgdialog(src) {
	$("#modalImg").attr("src", src);
	$("#img-modal-form").modal();
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
				if ("communityImg" == curBusiCode) {
					// 房源轮播图片
					addCommunityImg(path);
				} else if ("communityCover" == curBusiCode) {
					// 房源封面图片
					addCommunityCover(path);
				} else if ("roomImg" == curBusiCode) {
					// 房型轮播图片
					addRoomImg(path);
				}
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("上传失败！");
		}
	});
}

/**
 * 上传房源封面图片
 * @param path
 */
function addCommunityCover(path){
	$("#coverPicture").val(path);
	$("#coverPictureImg").attr("src", path);
}

/**
 * 上传房源图片
 * 
 * @param path
 */
function addCommunityImg(path) {
	if (path == "") {
		return;
	}
	$.ajax({
		url : webRoot + "/communityImg/addImg?v=" + Math.random(),
		dataType : "json",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify({
			"busiId" : $("#communityId").val(),
			"busiCode" : "community",
			"sortNum" : 1,
			"path" : path
		}),
		success : function(data) {
			if (data.resultCode == "999999") {
				createCommunityImg(data.resultData);
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("上传失败！");
		}
	});

}

/**
 * 加载房源图片
 */
function loadCommunityImgs() {
	var communityId = $("#communityId").val();
	if (communityId == "" || communityId == 0) {
		return;
	}
	$.ajax({
		url : webRoot + "/communityImg/queryCommunityImgList?busiId=" + communityId + "&busiCode=community&v=" + Math.random(),
		type : "get",
		success : function(data) {
			if (data.resultCode == "999999") {
				var imgs = data.resultData;
				if (imgs && imgs.length > 0) {
					$("#communityImgs").empty();
					for (var i = 0; i < imgs.length; i++) {
						createCommunityImg(imgs[i]);
					}
				}
			}
		}
	});
}

/**
 * 创建房源图片
 * 
 * @param img
 */
function createCommunityImg(img) {
	var html = "<tr id='communityImg_" + img.id + "'>";
	html += "<td><a href='javascript:showImgdialog(\"" + img.path + "\")'><img class='tdImg' src='" + img.path + "'/></a></a></td>";
	html += "<td><input name='communityImgSort' imgId='" + img.id + "' type='text' maxlength='2' style='width: 100px;' value='" + img.sortNum + "'/></td>";
	html += "<td><button type='button' class='btn btn-warning' onclick='deleteCommunityImg(" + img.id + ")'>删除</button></td>";
	html += "</tr>";
	$("#communityImgs").append(html);
}

/**
 * 删除房源图片
 * 
 * @param id
 */
function deleteCommunityImg(id) {
	if (id == "") {
		return;
	}
	if (confirm("确定要删除该图片？")) {
		$.ajax({
			url : webRoot + "/communityImg/deleteImg?id=" + id + "&v=" + Math.random(),
			type : "post",
			success : function(data) {
				if (data.resultCode == "999999") {
					$("#communityImg_" + id).remove();
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

/**
 * 保存房源图片
 */
function saveCommunityImg() {
	var imgIdStr = "";
	var sortNumStr = "";
	if ($("input[name='communityImgSort']").length <= 0) {
		return;
	}
	$("input[name='communityImgSort']").each(function() {
		imgIdStr += $(this).attr("imgId") + ",";
		sortNumStr += $(this).val() + ",";
	});
	$.ajax({
		url : webRoot + "/communityImg/updateImgSort?idStr=" + imgIdStr + "&sortNumStr=" + sortNumStr + "&v=" + Math.random(),
		type : "post",
		success : function(data) {
			if (data.resultCode == "999999") {
				alert("保存成功！");
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("保存失败！");
		}
	});
}

/**
 * 加载房型信息
 */
function loadRoomList() {
	var communityId = $("#communityId").val();
	if (communityId == "" || communityId == 0) {
		return;
	}
	$("#roomList").empty();
	$.ajax({
		url : webRoot + "/room/queryRoomList?communityId=" + communityId + "&v=" + Math.random(),
		type : "get",
		success : function(data) {
			if (data.resultCode == "999999") {
				var roomList = data.resultData;
				if (roomList && roomList.length >= 0) {
					$("#roomList").empty();
					for (var i = 0; i < roomList.length; i++) {
						var room = roomList[i];
						var html = "<tr>";
						html += "<td><input type='checkbox' name='roomBox' value='" + room.id + "'></td>";
						html += "<td><a href='javascript:initUpdateRoom(" + room.id + ");'>" + room.name + "</a></td>";
						html += "<td>" + room.bedType + "</td>";
						html += "<td>" + room.area + "</td>";
						html += "<td>" + room.price + "</td>";
						html += "<td>" + room.discountPrice + "</td>";
						html += "<td>" + room.dateAllowedStart + "</td>";
						html += "<td>" + room.dateAllowedEnd + "</td>";
						html += "<td>" + room.leaseLimit + room.leaseUnit + "起租</td>";
						var status = room.status == "10" ? "新建" : room.status == "20" ? "已上架" : "已下架"
						html += "<td>" + status + "</td>";
						$("#roomList").append(html);
					}
				}
			}
		}
	});
}

/**
 * 初始化修改房型
 */
function initUpdateRoom(id) {
	if (!id || id == "") {
		return;
	}
	$.ajax({
		url : webRoot + "/room/readRoomInfo?id=" + id + "&v=" + Math.random(),
		type : "get",
		success : function(data) {
			if (data.resultCode == "999999") {
				var room = data.resultData;
				if (room) {
					// 赋值
					$("#roomId").val(room.id);
					$("#roomType").val(room.typeCode);
					$("#roomName").val(room.name);
					$("#roomArea").val(room.area);
					$("#roomPrice").val(room.price);
					$("#roomDiscountPrice").val(room.discountPrice);
					$("#roomLeaseModel").val(room.leaseModelKey);
					$("#roomLeaseLimit").val(room.leaseLimit);
					$("#roomLeaseUnit").val(room.leaseUnit);
					$("#roomDateAllowedStart").val(room.dateAllowedStart);
					$("#roomDateAllowedEnd").val(room.dateAllowedEnd);
					$("#roomBedType").val(room.bedTypeCode);
					$("#roomTipPrice").val(room.tipPrice);
					$("#roomSortNum").val(room.sortNum);
					if (room.payFlag == "Y") {
						$($("input[name='payFlag']")[0]).attr("checked", "checked")
					} else {
						$($("input[name='payFlag']")[1]).attr("checked", "checked");
					}
					if(room.tipFlag=="Y"){
						$($("input[name='tipFlag']")[0]).attr("checked","checked")
					}else{
						$($("input[name='tipFlag']")[1]).attr("checked","checked");
					}
					$("#roomTipPrice").val(room.tipPrice);
					$("#firstDepositMonth").val(room.firstDepositMonth);
					$("#roomDepositPrice").val(room.depositPrice);
					$("#firstRentMonth").val(room.firstRentMonth);
					
					var imgs = room.images;
					$("img[name='roomImg']").attr("src", webRoot+"/static/images/jiahao.png");
					$("img[name='roomImg']").attr("imgId", "");
					if (imgs && imgs.length > 0) {
						for (var i = 0; i < imgs.length; i++) {
							var img = imgs[i];
							$("#roomImg_" + i).attr("src", img.path);
							$("#roomImg_" + i).attr("imgId", img.id);
							// 最多显示7张图片
							if (i >= 7) {
								break;
							}
						}
					}
					// 初始化支付信息
					initPayFlag();
					// 显示
					$("#room-edit-modal-form").modal();
				}
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("加载失败！");
		}
	});
}

/**
 * 上传房型图片
 * 
 * @param path 图片地址
 */
var curRoomgImgIndex = 0;
function addRoomImg(path) {
	var imgId = $("#roomImg_" + curRoomgImgIndex).attr("imgId");
	var action = (imgId && imgId >= 0) ? "update" : "add";
	var url = action == "update" ? "/communityImg/updateImg" : "/communityImg/addImg";
	$.ajax({
		url : webRoot + url + "?v=" + Math.random(),
		dataType : "json",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify({
			"id" : imgId,
			"busiId" : $("#roomId").val(),
			"busiCode" : "room",
			"sortNum" : curRoomgImgIndex + 1,
			"path" : path
		}),
		success : function(data) {
			if (data.resultCode == "999999") {
				$("#roomImg_" + curRoomgImgIndex).attr("src", path);
				if (action == "add") {
					var img = data.resultData;
					$("#roomImg_" + curRoomgImgIndex).attr("imgId", img.id);
				}
			} else {
				alert(data.resultDesc);
			}
		},
		error : function() {
			alert("上传失败！");
		}
	});
}

/**
 * 删除房型图片
 * 
 * @param roomgImgIndex
 */
function deleteRoomImg(roomgImgIndex) {
	var imgId = $("#roomImg_" + roomgImgIndex).attr("imgId");
	if (imgId && imgId >= 0) {
		$.ajax({
			url : webRoot + "/communityImg/deleteImg?id=" + imgId + "&v=" + Math.random(),
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.resultCode == "999999") {
					$("#roomImg_" + curRoomgImgIndex).attr("imgId", "");
					$("#roomImg_" + roomgImgIndex).attr("src", webRoot + "/static/images/jiahao.png");
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

function saveRoom() {
	var roomId = $("#roomId").val();
	var action = (roomId && roomId > 0) ? "/room/saveUpdate" : "/room/saveInsert";
	$.ajax({
		url : webRoot + action + "?v=" + Math.random(),
		type : "post",
		contentType : "application/json",
		data : JSON.stringify({
			"id":roomId,
			"communityId" : $("#communityId").val(),
			"name":$("#roomName").val(),
			"type":$("#roomType option:selected").text(),
			"typeCode":$("#roomType").val(),
			"roomBedType":$("#roomBedType").val(),
			"price":$("#roomPrice").val(),
			"discountPrice":$("#roomDiscountPrice").val(),
			"area":$("#roomArea").val(),
			"leaseModelKey":$("#roomLeaseModel").val(),
			"leaseModel":$("#roomLeaseModel option:selected").text(),
			"leaseLimit":$("#roomLeaseLimit").val(),
			"leaseUnit":$("#roomLeaseUnit").val(),
			"bedTypeCode":$("#roomBedType").val(),
			"bedType":$("#roomBedType option:selected").text(),
			"dateAllowedStart":$("#roomDateAllowedStart").val(),
			"dateAllowedEnd":$("#roomDateAllowedEnd").val(),
			"sortNum":$("#roomSortNum").val(),
			"payFlag":$("input[name='payFlag']:checked").val(),
			"tipFlag":$("input[name='tipFlag']:checked").val(),
			"tipPrice":$("#roomTipPrice").val(),
			"firstRentMonth":$("#firstRentMonth").val(),
			"depositPrice":$("#roomDepositPrice").val(),
			"firstDepositMonth":$("#firstDepositMonth").val()
		}),
		success : function(data) {
			if(data.resultCode=="999999"){
				alert("保存成功！");
				loadRoomList();
			}else{
				alert(data.resultDesc);
			}
		},
		error:function(){
			alert("保存失败！");
		}
	});
}

/**
 * 初始化新增
 */
function initInsertRoom(){
	$("#roomId").val(0);
	$("#roomName").val("");
	$("#roomArea").val("");
	$("#roomPrice").val("");
	$("#roomDiscountPrice").val("");
	$("#roomLeaseLimit").val("");
	$("#roomLeaseUnit").val("");
	$("#roomTipPrice").val(0);
	$("#roomSortNum").val(1);
	$($("input[name='payFlag']")[0]).attr("checked","checked");
	$($("input[name='tipFlag']")[1]).attr("checked","checked");
	$("#roomTipPrice").val("");
	$("#firstDepositMonth").val("");
	$("#roomDepositPrice").val("");
	$("#firstRentMonth").val("");
	initPayFlag();
	for (var i = 0; i < 7; i++) {
		$("#roomImg_" + i).attr("src", webRoot + "/static/images/jiahao.png");
	}
	$("#room-edit-modal-form").modal();
}


function deleteRooms(){
	var rooms=$("input[name='roomBox']:checked");
	if(!rooms || rooms.length<=0){
		return ;
	}
	var idStr="";
	$(rooms).each(function(){
		idStr+=$(this).val()+",";
	});
	if(confirm("是否要删除选中的记录？")){
		$.ajax({
			url:webRoot+"/room/deleteRoom?idStr="+idStr+"&v="+Math.random(),
			type:"post",
			success:function(data){
				if(data.resultCode=="999999"){
					alert("操作成功！");
					loadRoomList();
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}

/**
 * 房型上架
 */
function putRoomOnLine(){
	var rooms=$("input[name='roomBox']:checked");
	if(!rooms || rooms.length<=0){
		alert("请选择记录！");
		return ;
	}
	var idStr="";
	$(rooms).each(function(){
		idStr+=$(this).val()+",";
	});
	if(confirm("上架选中的记录？")){
		$.ajax({
			url:webRoot+"/room/putOnLine?idStr="+idStr+"&v="+Math.random(),
			type:"post",
			success:function(data){
				if(data.resultCode=="999999"){
					alert("操作成功！");
					loadRoomList();
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}

/**
 * 房型下架
 */
function putRoomOffLine(){
	var rooms=$("input[name='roomBox']:checked");
	if(!rooms || rooms.length<=0){
		alert("请选择记录！");
		return ;
	}
	var idStr="";
	$(rooms).each(function(){
		idStr+=$(this).val()+",";
	});
	if(confirm("上架选中的记录？")){
		$.ajax({
			url:webRoot+"/room/putOffLine?idStr="+idStr+"&v="+Math.random(),
			type:"post",
			success:function(data){
				if(data.resultCode=="999999"){
					alert("操作成功！");
					loadRoomList();
				}
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
}

function checkLeaseModel(){
	if("yuezu"==$("#roomLeaseModel").val()){
		$("#payDIV").show();
	}else{
		$("#payDIV").hide();
	}
}

/**
 * 支付信息-是否需要付款
 */
function checkPayFlag(){
	if($("input[name='payFlag']:checked").val()=='Y'){
		$("#tipFlagDIV").show();
		if ($("input[name='tipFlag']:checked").val() == 'N') {
			$("#firstDepositDIV").show();
			$("#firstRentDIV").show();
		}
	}else{
		$("#tipFlagDIV").hide();
		$("#firstDepositDIV").hide();
		$("#firstRentDIV").hide();
	}
}

/**
 * 支付信息-是否需要押金
 */
function checkTipFlag(){
	if($("input[name='tipFlag']:checked").val()=='Y'){
		$("#firstDepositDIV").hide();
		$("#firstRentDIV").hide();
		$("#roomTipPriceDIV").show();
	}else{
		$("#firstDepositDIV").show();
		$("#firstRentDIV").show();
		$("#roomTipPriceDIV").hide();
	}
}

/**
 * 初始化支付信息
 */
function initPayFlag(){
	checkLeaseModel();
	checkTipFlag();
	checkPayFlag();
}
