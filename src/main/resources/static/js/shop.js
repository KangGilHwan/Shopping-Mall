$("#user-sign-up").on("click","#sign-up-form button", signUp);
$(document).on("click", "#login-btn", login);
$("#product-form").on("click", "#product-btn", product);

function signUp(e){
e.preventDefault();

var url = $("#sign-up-form").attr("action");
console.log("url : " + url);

var data = {
username : $("#username").val(),
password : $("#password").val(),
email : $("#email").val(),
phoneNumber : $("#phoneNumber").val()
//socialId : $("#socialId").val(),
//socialCode : $("#socialCode").val()
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
};

function product(e){
e.preventDefault();
console.log("Hello");
var url = $("#product-form").attr("action");

console.log("url : " + $("#product-name").val());
console.log("price : " + $("#product-price").val());
console.log("file : " + $("#product-file")[0].files[0]);
console.log("description : " + $("#product-description").val());
var formData = new FormData();
formData.append("name", $("#product-name").val());
formData.append("price", $("#product-price").val());
formData.append("image", $("#product-file")[0].files[0]);
formData.append("description", $("#product-description").text());
console.log("url : " + url);
var image = $("#product-file").val();
console.log("image : " + image);

$.ajax({
type : 'post',
url : url,
data : formData,
dataType : "json",
processData: false,
contentType: false,
success : function(data, status){
console.log("data :" + data);
console.log("status :" + status);
//location.href = "/";
},
error : function (xhr, status){
alert("error");
}

})
};