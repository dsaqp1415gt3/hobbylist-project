var API_BASE_URL = "http://147.83.7.159:8080";
var url = API_BASE_URL + '/hobbylist-api/favs/';
var username = $.cookie('username');
var password = $.cookie('password');

$.ajaxSetup({
    headers: { 'Authorization': "Basic " + btoa(username + ':' + password)}
});


function postFavs(gameid, rank) {
	$("#result").text('');
    var data = {
        "gameid":gameid,
        "rank":rank
    }
    var dj = JSON.stringify(data);
	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : dj,
		contentType: "application/vnd.hobbylist.api.favs+json",
	}).done(function(data, status, jqxhr) {
                
				$("#result").html("Juego añadido a la lista de favoritos");
        setTimeout(function(){ window.location="favs.html"; }, 3000);

	}).fail(function(jqXHR, textStatus) {
        console.log(jqXHR);
        console.log(textStatus);
		$("#result").text("No files.");
	});
}





