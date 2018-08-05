$("#user-sign-up").on("click","#sign-up-form button", signUp);
$(document).on("click", "#login-btn", login);

function signUp(e){
e.preventDefault();

var url = $("#sign-up-form").attr("action");
console.log("url : " + url);

var data = {
username : $("#username").val(),
password : $("#password").val(),
email : $("#email").val(),
phoneNumber : $("#phoneNumber").val(),
socialId : $("#socialId").val(),
socialCode : $("#socialCode").val()
};

console.log("queryString : " + JSON.stringify(data));
$.ajax({
type : 'post',
url : url,
contentType : "application/json",
data : JSON.stringify(data),
error : function (xhr, status){
alert("error");
},
success : function(data, status){
console.log("success : " + data);
location.href = "/";
}
});
};

function login(e){
e.preventDefault();
console.log("Hi");

var url = $("#login-form").attr("action");
console.log("url :" + url);
//console.log("name :" + $("#login-username").val());
//console.log("password :" + $("#login-password").val());

var data = $("#login-form").serialize();
console.log("data :" + data);

$.ajax({
type : 'post',
url : url,
dataType : "text",
data : data,
success : function(data, status){
console.log("data :" + data);
console.log("status :" + status);
location.href = "/";
},
error : function (xhr, status){
alert("error");
}
});
}