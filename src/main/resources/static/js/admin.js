$(document).on("click", "#coupon-register", createCoupon);

function createCoupon(e){
e.preventDefault();

var url = $("#coupon-form").attr("action");
console.log("url : " + url);

var data = {
name : $("#coupon-name").val(),
discount : $("#coupon-discount").val()
};

console.log("queryString : " + JSON.stringify(data));
$.ajax({
type : 'post',
url : url,
contentType : "application/json",
data : JSON.stringify(data),
error : function (xhr, status){
alert("등록이 되지 않았습니다.");
},
success : function(data, status){
console.log("success : " + data);
location.href = "/admin/coupons";
}
});
};