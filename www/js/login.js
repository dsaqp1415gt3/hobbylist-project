var API_BASE_URL = "http://localhost:8080";

$.ajaxSetup({
    headers: { 'Authorization': "Basic "}
});

$("#btnLogin").click(function(e){
    login();   
});

$("#btnRegister").click(function(e){
    window.location="register.html";   
});

$("#btnEntrar").click(function(e){
    window.location="index.html";   
});


function login(){
    $("#result").text('');
    var url = API_BASE_URL + "/hobbylist-api/users/login";
    console.log(url);
    var usuario = {username:$("#name").val(), password:$("#password").val()};
    var data = JSON.stringify(usuario);
    
    $.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
        contentType: 'application/vnd.hobbylist.api.user+json',
		data : data,
	}).done(function(data, status, jqxhr) {			
        if(data.loginSuccessful == true){
            $.cookie('username', $("#name").val());
            $.cookie('password', $("#password").val());
            $.cookie();
            window.location="index.html";
        }else{
            $.removeCookie('username');
            $.removeCookie('password');  
            $(("#result")).html('<div class="alert alert-danger"> <strong>Oh!</strong> Nombre de usuario y contraseña ya usados </div>');
        }
  	}).fail(function() {
            $.removeCookie('username');
            $.removeCookie('password');  
            $(("#result")).html('<div class="alert alert-danger"> <strong>Oh!</strong> Nombre de usuario y contraseña ya usados </div>');
	});
}
