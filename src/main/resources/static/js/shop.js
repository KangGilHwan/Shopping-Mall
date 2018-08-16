$("#user-sign-up").on("click","#sign-up-form button", signUp);
$("#product-form").on("click", "#product-btn", product);
$(document).on("click", "#login-btn", login);
$(document).on("click", "#logoutBtn", logout);
$(document).on("click", "#cartBtn", addCart);

function signUp(e){
e.preventDefault();

var url = $("#sign-up-form").attr("action");
console.log("url : " + url);

var data = {
username : $("#username").val(),
password : $("#password").val(),
email : $("#email").val(),
phoneNumber : $("#phoneNumber").val()
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
alert("아이디와 비밀번호를 확인해주세요.");
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
formData.append("description", $("#product-description").val());
console.log("url : " + url);
var image = $("#product-file").val();
console.log("image : " + image);

$.ajax({
type : 'post',
url : url,
data : formData,
processData: false,
contentType: false,
success : function(data, status){
console.log("data :" + data);
console.log("status :" + status);
location.href = "/products";
},
error : function (xhr, status){
alert("error");
}

})
};

function logout(e){
e.preventDefault();
console.log("Bye");

var url = document.getElementById('logoutBtn').getAttribute('href');
console.log("url :" + url);

$.ajax({
type : 'post',
url : url,
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

function addCart(e){
e.preventDefault();
console.log("Hello");
var id = $("#cartId").val();
var url = '/api/products/' + id + '/cart';
console.log("url :" + url);
var size = document.getElementById('size_select');
console.log("size value : " + size);
var sizeValue = size[size.selectedIndex].value;
console.log("size value : " + sizeValue);
var amount = $('#itemAmount').val();
console.log("amount value : " + amount);


var data = {
size : sizeValue,
amount : amount
};

$.ajax({
type : 'post',
url : url,
data : JSON.stringify(data),
contentType : "application/json",
success : function(data, status){
console.log("data :" + data);
console.log("status :" + status);
location.href = "/products";
},
error : function (xhr, status){
alert("error");
}
});
};