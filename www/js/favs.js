var API_BASE_URL = "http://147.83.7.159:8080";
var username = $.cookie('username');
var password = $.cookie('password');

$.ajaxSetup({
    headers: { 'Authorization': "Basic " + btoa(username + ':' + password)}
});


$(document).ready(function(){

    var url = API_BASE_URL + '/hobbylist-api/favs/users/' + username;
    console.log(url);
    if(username == "null"){
        $('#Salir').html("Login");
        $('#Salir').attr("href", "/login.html");
        window.location="index.html";
    }else{
        $('#Salir').html("Logout");
        $('#Salir').attr("href", "#");
    }
	getList(url);
    
    $('#Salir').click(function(){
        $.cookie('username', null);
        window.location="login.html";   
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
                var hobbies = data.favs;
				$.each(hobbies, function(i, v) {
					var hobbie = v;
                    console.log(hobbie);
                    html+='<tr>';
                    html+="<td><img src='" + hobbie.imageurl + "' width='250' height='250'></td>";
                    html+='<td><strong> Title: </strong>' + hobbie.title + '<br />';
				    html+='<strong> Compañía: </strong>' + hobbie.company + '<br />';
				    html+='<strong> Género: </strong>' + hobbie.genrename + '<br />';
                    html+='<strong> Año: </strong>' + hobbie.year + '<br />';
				    html+='<strong> Sinopsis: </strong> ' + hobbie.synopsis + '<br />';
                    html+='<strong> Plataforma: </strong>' + hobbie.plataformname + '<br />';
                    html+='<strong> ID: </strong>' + hobbie.gameid + '<br />';
                     html+='<strong> ID de Favorito: </strong>' + hobbie.favid + '<br />';
                     html+='<strong> Rank: </strong>' + hobbie.rank+ '<br />';
                    
                    html+='<br /></td>';
                    html+="<td><a href='#' onclick=deleteGame(" + hobbie.favid +"); class='btn delfav'>Borrar de favoritos</a><br /><a href='#' onclick=updateGame(" + hobbie.favid +"); class='btn update'>Actualizar Videojuego</a><br />Rank:<input type='text' id='rank"+hobbie.favid+"'><br /><a href='invites.html'class='btn recom'>Recomendar a un amigo</a><br /></td>";
                    html+='</tr>';
				});
                html+='</tbody></table>';
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});
}

function deleteGame(favid) {


	var url = API_BASE_URL + '/hobbylist-api/favs/' + favid;
	$("#result").text('');

	$
			.ajax(
        {
        url : url,
						type : 'DELETE',
						crossDomain : true,
						dataType : 'json',
						statusCode: {
    		202: function() {$('<div class="alert alert-danger"> <strong>Ok!</strong> Juego ya borrado </div>').appendTo($("#result"));},
			404: function() {$('<div class="alert alert-danger"> <strong>Oh!</strong> Juego no encontrado </div>').appendTo($("#result"));}
    	}
					})
			.done(
					function(data, status, jqxhr) {
						$(
								'<div class="alert alert-success"> <strong>Ok!</strong> Juego satisfactoriamente borrado</div>')
								.appendTo($("#result"));
                        setTimeout(function(){ window.location="favs.html"; }, 3000);
					})

}

function updateGame(favid) {
	var url = API_BASE_URL + '/hobbylist-api/favs/' + favid;
    console.log(url);
    var newGame = new Object();
    var rank = $("#rank" +favid).val();
    console.log(rank);
    newGame.username = username;
    newGame.rank = rank;
    
   
	var data = JSON.stringify(newGame);
    console.log(data);
	$("#result").text('');

	$.ajax({
        headers : {
			'Authorization' : "Basic " + btoa(username + ':' + password)
		},
		url : url,
		type : 'PUT',
		crossDomain : true,
		dataType : 'json',
		data : data,
		contentType: "application/vnd.hobbylist.api.favs+json",
		
	}).done(function(game, status, jqxhr) {
         location.reload();
         $("#result").html(html);
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#result"));
	});

}






