var API_BASE_URL = "http://localhost:8080";
var username = $.cookie('username');

$.ajaxSetup({
    headers: { 'Authorization': "Basic "}
});

$(document).ready(function(){

    var url = API_BASE_URL + '/hobbylist-api/games';

    if(username == "null"){
        $('#Salir').html("Login");
        $('#Salir').attr("href", "/login.html");
        $('#Favs').html("");
    }else{
        $('#Salir').html("Logout");
        $('#Salir').attr("href", "#");
        $('#user').html(username);
    }
	getList(url);
    
    $('#Salir').click(function(){
        $.cookie('username', null);
        window.location="login.html";   
    });
    $('#plataforma').click(function(){
        var platform =  $('#plataforma').val();
        getListPlat(platform);
    });
    $('#genero').click(function(){
        var genre =  $('#genero').val();
        getListGen(genre);
    });
    
    $("#buscar").keyup(function(e){
        var key = event.keyCode || event.wich;
        if(key != 13){
            return
        }
        var url = API_BASE_URL + '/hobbylist-api/games/title/' + $("#buscar").val();
        console.log(url);
        $("#result").text('');
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html=" <table class='table'><tbody>";
                var hobbies = data.games;
				$.each(hobbies, function(i, v) {
					var hobbie = v;
                    html+='<tr>';
                    html+="<td><img src='" + hobbie.imageurl + "' width='250' height='250'></td>";
                    html+='<td><strong> Title: </strong>' + hobbie.title + '<br />';
				    html+='<strong> Compañía: </strong>' + hobbie.company + '<br />';
				    html+='<strong> Género: </strong>' + hobbie.genrename + '<br />';
                    html+='<strong> Año: </strong>' + hobbie.year + '<br />';
				    html+='<strong> Sinopsis: </strong> ' + hobbie.synopsis + '<br />';
                    html+='<strong> Plataforma: </strong>' + hobbie.plataformname + '<br />';
                    html+='<strong> ID: </strong>' + hobbie.gameid + '<br />';
                    html+='<br /></td>';
                    html+="<td><a href='#' onclick=postFavs(" + hobbie.gameid + "); class='btn addfav'>Añadir a favoritos</a><br /><a href='#' class='btn addfav'>Recomendar a un amigo</a><br /></td>";
                    html+='</tr>';
				});
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});

    });
});




function getList(url) {
	$("#result").text('');
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html=" <table class='table'><tbody>";
                var hobbies = data.games;
				$.each(hobbies, function(i, v) {
					var hobbie = v;
                    html+='<tr>';
                    html+="<td><img src='" + hobbie.imageurl + "' width='250' height='250'></td>";
                    html+='<td><strong> Title: </strong>' + hobbie.title + '<br />';
				    html+='<strong> Compañía: </strong>' + hobbie.company + '<br />';
				    html+='<strong> Género: </strong>' + hobbie.genrename + '<br />';
                    html+='<strong> Año: </strong>' + hobbie.year + '<br />';
				    html+='<strong> Sinopsis: </strong> ' + hobbie.synopsis + '<br />';
                    html+='<strong> Plataforma: </strong>' + hobbie.plataformname + '<br />';
                    html+='<strong> ID: </strong>' + hobbie.gameid + '<br />';
                    html+='<br /></td>';
                    html+="<td><a href='#' onclick=\"postFavs(" + hobbie.gameid + ", 9);\" class='btn addfav'>Añadir a favoritos</a><br /><a href='#' class='btn addfav'>Recomendar a un amigo</a><br /></td>";
                    html+='</tr>';
				});
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});
}

function getListPlat(platformname) {
    var url = API_BASE_URL + '/hobbylist-api/games/platform/' + platformname;
	$("#result").text('');
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html=" <table class='table'><tbody>";
                var hobbies = data.games;
				$.each(hobbies, function(i, v) {
					var hobbie = v;
                    html+='<tr>';
                    html+="<td><img src='" + hobbie.imageurl + "' width='250' height='250'></td>";
                    html+='<td><strong> Title: </strong>' + hobbie.title + '<br />';
				    html+='<strong> Compañía: </strong>' + hobbie.company + '<br />';
				    html+='<strong> Género: </strong>' + hobbie.genrename + '<br />';
                    html+='<strong> Año: </strong>' + hobbie.year + '<br />';
				    html+='<strong> Sinopsis: </strong> ' + hobbie.synopsis + '<br />';
                    html+='<strong> Plataforma: </strong>' + hobbie.plataformname + '<br />';
                    html+='<strong> ID: </strong>' + hobbie.gameid + '<br />';
                    html+='<br /></td>';
                    html+="<td><a href='#' class='btn addfav'>Añadir a favoritos</a><br /><a href='#' class='btn addfav'>Recomendar a un amigo</a><br /></td>";
                    html+='</tr>';
				});
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});
}

function getListGen(genrename) {
    var url = API_BASE_URL + '/hobbylist-api/games/genre/' + genrename;
	$("#result").text('');
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html=" <table class='table'><tbody>";
                var hobbies = data.games;
				$.each(hobbies, function(i, v) {
					var hobbie = v;
                    html+='<tr>';
                    html+="<td><img src='" + hobbie.imageurl + "' width='250' height='250'></td>";
                    html+='<td><strong> Title: </strong>' + hobbie.title + '<br />';
				    html+='<strong> Compañía: </strong>' + hobbie.company + '<br />';
				    html+='<strong> Género: </strong>' + hobbie.genrename + '<br />';
                    html+='<strong> Año: </strong>' + hobbie.year + '<br />';
				    html+='<strong> Sinopsis: </strong> ' + hobbie.synopsis + '<br />';
                    html+='<strong> Plataforma: </strong>' + hobbie.plataformname + '<br />';
                    html+='<strong> ID: </strong>' + hobbie.gameid + '<br />';
                    html+='<br /></td>';
                    html+="<td><a href='#' class='btn addfav'>Añadir a favoritos</a><br /><a href='#' class='btn addfav'>Recomendar a un amigo</a><br /></td>";
                    html+='</tr>';
				});
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});
}





