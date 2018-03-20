// JavaScript Document
document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.2 + 'px';
window.addEventListener('resize', function () {
      document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.2 + 'px'; 
}); 

var mainhost='http://admin.wemecity.net'; 
var loginhost='http://localhost:8080/admin/templates/communityOrder/';
//弹出显示
function showvaguealert(con){
	$('.vaguealert').show();
	$('.vaguealert').find('p').html(con);
	setTimeout(function(){
		$('.vaguealert').hide();
	},1000);
} 