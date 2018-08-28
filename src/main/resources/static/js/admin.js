$(document).on("click", "#coupon-register", createCoupon);
$(document).on("click", "#coupon-issue", issueCoupon);

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

function issueCoupon(e){
e.preventDefault();

var couponId = $(this).val();
console.log("couponId : " + couponId);

var users = document.getElementById('coupon-issue-select-' + couponId);
var userValue = users[users.selectedIndex].value;
console.log("users value : " + userValue);

var url = makeUrl(couponId, userValue);
console.log("url : " + url);

$.ajax({
type : 'post',
url : url,
error : function (xhr, status){
alert("적용 되지 않았습니다.");
},
success : function(data, status){
console.log("success : " + data);
alert("쿠폰이 발급 되었습니다.");
location.href = "/admin/coupons";
}
});
};

function makeUrl(couponId, userValue){
if(userValue === "all"){
return "/api/coupons/" + couponId + "/users";
}
return "/api/coupons/" + couponId + "/users/" + userValue;
};