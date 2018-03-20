var webRoot = getRootPath();

function getRootPath() {
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	if (projectName != "/admin") {
		projectName = "";
	}
	return projectName;
}

/**
 * 获取url参数
 * 
 * @param name
 * @returns
 */
function getQueryParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

