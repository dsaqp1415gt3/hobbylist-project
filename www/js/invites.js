var API_BASE_URL = "http://147.83.7.159:8080";
var username = $.cookie('username');
var password = $.cookie('password');

var temporal;


$.ajaxSetup({
headers : {
'Authorization' : "Basic " + btoa(username + ':' + password)}
});

$(document).ready(function(){
	var url = API_BASE_URL + '/hobbylist-api/invites/' + username;
	getInvites(url);
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html="<h1>Invitaciones</h1> <table class='table'><tbody>";
                var hobbies = data;
        console.log(data);
                    html+='<tr>';
                    html+='<td><strong> Sender: </strong>' + hobbies.sender + '<br />';
				    html+='<strong> Receiver: </strong>' + hobbies.receiver + '<br />';
				    html+='<strong> Sender: </strong>' + hobbies.gameid + '<br />';
                    html+='<a onClick="aceptarInvitacion('+hobbies.invid+')">Aceptar Invitacion</a>';
                    html+='</tr>';
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);
        temporal = hobbies;

	}).fail(function() {
		$("#result").text("No files.");
	});
});
function aceptarInvitacion(invitID){
        var url = API_BASE_URL + '/hobbylist-api/invites/' + invitID;
    var data = JSON.stringify(temporal);
    $.ajax({
		url : url,
		type : 'PUT',
		crossDomain : true,
		dataType : 'json',
		data : data,
		contentType: "application/vnd.hobbylist.api.invite+json",
	}).done(function(invite, status, jqxhr) {
			console.log(invite);
            postFavs(invite.gameid, 0);        
  	}).fail(function(jqXHR, textStatus) {
        console.log(jqXHR);
        console.log(textStatus);
	});
}

$("#btnInvit").click(function(e) {
	e.preventDefault();
    var newInvite = new Object();
    newInvite.sender = username; //el sender es el username
    newInvite.receiver = $("#receiver").val();
    newInvite.gameid = $("#gameid").val(); //esto deberá pillarlo del juego que quieras recomendar
	
	createInvite(newInvite);
});

$("#btnReturn").click(function(e) {
e.preventDefault();
window.location="index.html";
});

function getInvites(url) {
	$("#result").text('');
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
           	    var html=" <table class='table'><tbody>";
                var hobbies = data.invites;
				$.each(hobbies, function(i, v) {
					var hobbie = v;
                    html+='<tr>';
                    html+='<td><strong> Sender: </strong>' + hobbie.sender + '<br />';
				    html+='<strong> Sender: </strong>' + hobbie.receiver + '<br />';
				    html+='<strong> Sender: </strong>' + hobbie.gameid + '<br />';
                    html+='</tr>';
				});
                html+='</tbody></table>';
                console.log(html);
				$("#result").html(html);

	}).fail(function() {
		$("#result").text("No files.");
	});
}



function createInvite(invite) {
	var url = API_BASE_URL + '/hobbylist-api/invites';
	var data = JSON.stringify(invite);
    console.log(invite);

	$("#result2").text('');

	$.ajax({
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
		contentType: "application/vnd.hobbylist.api.invite+json",
		statusCode: {						
						409: function() {$('<div class="alert alert-danger"> <strong>Oh!</strong> Ese ID ya existe </div>').appendTo($("#result"));}
						}
	}).done(function(invite, status, jqxhr) {
		$('<strong> Sender: </strong>' + invite.sender + '<br>').appendTo($('#result2'));
		$('<strong> Receiver: </strong>' + invite.receiver + '<br>').appendTo($('#result2'));
		$('<strong> GameId: </strong>' + gameid + '<br>').appendTo($('#result2'));				
  	}).fail(function(jqXHR, textStatus) {
        console.log(jqXHR);
        console.log(textStatus);
	});

}
