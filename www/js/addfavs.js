var API_BASE_URL = "http://localhost:8080";
var url = API_BASE_URL + '/hobbylist-api/favs/';
var username = $.cookie('username');
var password = $.cookie('password');

$.ajaxSetup({
    headers: { 'Authorization': "Basic " + btoa(username + ':' + password)}
});

$(document).ready(function(){

   
   
});




function postFavs(gameID, rank) {
	$("#result").text('');
    var data = {
        "gameid":gameID,
        "rank":rank
    }
	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html=" <table class='table'><tbody>";
                var hobbies = data.favs;
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
                    html+="<td><a href='#' class='btn addfav'>Borrar de favoritos</a><br /><a href='#' class='btn addfav'>Recomendar a un amigo</a><br /></td>";
                    html+='</tr>';
				});
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});
}





