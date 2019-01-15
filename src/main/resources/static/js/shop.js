$("#user-sign-up").on("click","#sign-up-form button", signUp);
$("#product-form").on("click", "#product-btn", product);
$(document).on("click", "#login-btn", login);
$(document).on("click", "#logoutBtn", logout);
$(document).on("click", "#cartBtn", addCart);
$(document).on("click", "#delete-cart", deleteCart);
$(document).on("click", "#orderBtn", order);

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
var category = document.getElementById('category_select');
var categoryValue = category[category.selectedIndex].value;
console.log("category value : " + categoryValue);
formData.append("name", $("#product-name").val());
formData.append("price", $("#product-price").val());
formData.append("category", categoryValue);
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
dataType : 'json',
contentType : "application/json",
success : function(data, status){
console.log("data :" + data);
console.log("data :" + JSON.stringify(data));
var dataJSON = JSON.stringify(data);

var temp = Handlebars.templates['precompile/cart'];
console.log("template : " + temp);
var cart = {
products : data.cartProducts
};
console.log("cartItem : " + JSON.stringify(cart.products));
var html = temp(cart);
console.log("Html : " + html);
$("#cartList").remove();
$("#cartList-div").prepend(html);
var totalPrice = data.totalPrice;
$("#totalPriceValue").val(totalPrice);
console.log("status :" + status);
document.getElementById('totalPrice').innerHTML="Total: $" + totalPrice;
},
error : function (xhr, status){
alert("error");
}
});
};

function deleteCart(e){
console.log("Delete");
e.preventDefault();
var deleteBtn = $(this);
var url = document.getElementById('delete-cart').getAttribute('href');
var size = $("#cart-size").val();
var amount = $("#cart-amount").val();
var price = $("#cart-product-price").val();
var total = $("#totalPriceValue").val();

console.log("totalPrice :" + total);
console.log("url :" + url);
console.log("size :" + size);
console.log("amount :" + amount);

var option = {
size : size,
amount : amount
};

console.log("option :" + option);

$.ajax({
type : 'delete',
url : url,
data : JSON.stringify(option),
contentType : "application/json",
success : function(data, status){
$(deleteBtn).closest("li").remove();

var totalPriceValue = parseInt(total);
var priceValue = parseInt(price);
var amountValue = parseInt(amount);
alert("zzz");
console.log("price :" + price);
console.log("amount :" + amountValue);
totalPriceValue = totalPriceValue - priceValue;
console.log("totalPrice :" + totalPriceValue);
document.getElementById('totalPrice').innerHTML="Total: $" + totalPriceValue;
},
error : function (xhr, status){
alert("error");
}
});
};

function order(e){
e.preventDefault();
var size = $("#cartSize").val();
console.log("size : " + size);

var list = [];
var data;
for(var i=0; i <size; i++){
var coupon = document.getElementById('coupon-select' + i);
var couponValue = coupon[coupon.selectedIndex].value;
console.log("coupon value : " + couponValue);
data = {
couponId : couponValue,
cartId : i
}
list.push(data);
}

var shipping = {
address : $("#address").val(),
recipient : $("#recipient").val(),
phoneNumber : $("#phoneNumber").val(),
specialNote : $("#specialNote").val()
}

var data2 = {
orderCoupons : list,
shipping : shipping
}

console.log("data : " + JSON.stringify(data2));
$.ajax({
type : 'post',
url : '/api/orders',
contentType : "application/json",
data : JSON.stringify(data2),
error : function (xhr, status){
alert("error");
},
success : function(result, status, xhr){
var url = xhr.getResponseHeader('Location')
console.log("success : " + xhr.getResponseHeader('Location'));
location.href = "/api/kakao/payment" + url.substring(url.lastIndexOf("/"));;
}
});
};