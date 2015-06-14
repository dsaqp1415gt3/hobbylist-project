var API_BASE_URL = "http://147.83.7.159:8080";
var username = $.cookie('username');
var password = $.cookie('password');
/*var id = $.cookie('gameid');*/

$.ajaxSetup({
    headers: { 'Authorization': "Basic " + btoa(username + ':' + password)}
});

$("#btnReturn").click(function(e) {
e.preventDefault();
window.location="index.html";
});

$("#btnAddgame").click(function(e) {
    var genero = document.getElementById("genreid");
	var selgenero = genero.options[genero.selectedIndex].text;
    if (selgenero== 'Shooter'){
        selgenero = 1;
    }
    if (selgenero== 'Plataformas'){
        selgenero = 2;
    }
     if (selgenero== 'RPG'){
        selgenero = 3;
    }
    if (selgenero== 'Puzle'){
        selgenero = 4;
    }
    if (selgenero== 'Acción'){
        selgenero = 5;
    }
    if (selgenero== 'RTS'){
        selgenero = 6;
    }
    if (selgenero== 'Deportes'){
        selgenero = 7;
    }
     if (selgenero== 'Conducción'){
        selgenero = 8;
    }
   /* var plataforma = document.getElementById("platformid");
	var selplataforma = plataforma.options[plataforma.selectedIndex].text;
    if (selplataforma== 'PS3'){
        selplataforma = 1;
    }
    if (selplataforma== 'PS4'){
        selplataforma = 2;
    }
     if (selplataforma== '3DS'){
        selplataforma = 3;
    }
    if (selplataforma== 'Wii U'){
        selplataforma = 4;
    }
    if (selplataforma== 'PS Vita'){
        selplataforma = 5;
    }
    if (selplataforma== 'Xbox 360'){
        selplataforma = 6;
    }
    if (selplataforma== 'Xbox One'){
        selplataforma = 7;
    }*/
	e.preventDefault();
	$("#result").text('');
	if($("#title").val() == "" || $("#synopsis").val() == "" || $("#genreid").val() == "" || $("#company").val() == "" || $("#year").val() == "" || $("#imageurl").val() == "")/* || $("#platformid").val() == "")*/ {
		$('<div class="alert alert-danger"> <strong>Error!</strong>Faltan parámetros importantes por rellenar</div>').appendTo($("#result"));	
	} else {
    var newGame = new Object();
    var newPlatform = new Object();
	
	newGame.title = $("#title").val();
	newGame.synopsis = $("#synopsis").val();
	newGame.genreid=selgenero;	
	newGame.company=$("#company").val();
	newGame.year=$("#year").val();	
	newGame.imageurl=$("#imageurl").val();	
    if (newGame.year > 1800 && newGame.year < 2050 && isValidUrl(newGame.imageurl))
	   createGame(newGame);
        else $('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#result"));
    /*var id= $("#id").val();
    newPlatform.platformid = selplataforma;
    newPlatform.gameid = id;
    createPlatform(newPlatform);*/
	}
    
    
});

function isValidUrl(url)
{
    if(url=="")
        return false;
    var pattern = /^(http|https)\:\/\/[a-z0-9\.-]+\.[a-z]{2,4}/gi;
    if(url.match(pattern))
        return true;
    else
        return false;
}

function createGame(newGame) {
	var url = API_BASE_URL + '/hobbylist-api/games';
	var data = JSON.stringify(newGame);
    console.log(data);
	$("#result").text('');

	$.ajax({
        headers : {
			'Authorization' : "Basic " + btoa(username + ':' + password)
		},
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
		contentType: "application/vnd.hobbylist.api.game+json",
		
	}).done(function(game, status, jqxhr) {
        console.log(newGame);
         var html=" <table class='table'><tbody>";
         html+='<tr>';
       	 html+="<td><img src='" + game.imageurl + "' width='250' height='250'></td>";
         html+='<td><strong> Title: </strong>' + game.title + '<br />';
         html+='<strong> Compañía: </strong>' + game.company + '<br />';
         html+='<strong> Género: </strong>' + game.genreid + '<br />';
         html+='<strong> Año: </strong>' + game.year + '<br />';
         html+='<strong> Sinopsis: </strong> ' + game.synopsis + '<br />';
         html+='<br /></td>';

         html+='</tr>';
         html+='</tbody></table>';
         $("#result").html(html);
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#result"));
	});

}

/*function createPlatform(newPlatform) {
	var url = API_BASE_URL + '/hobbylist-api/platformgames';
	var data = JSON.stringify(newPlatform);
    console.log(data);
	$("#result").text('');

	$.ajax({
        headers : {
			'Authorization' : "Basic " + btoa(username + ':' + password)
		},
		url : url,
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : data,
		contentType: "application/vnd.hobbylist.api.platformgame+json",
		
	}).done(function(platformgame, status, jqxhr) {
         var html=" <table class='table'><tbody>";
         html+='<tr>';
         html+='<td><strong> Plataforma: </strong>' + platformgame.platformid + '<br />';
         html+='<strong> Juego: </strong>' + platformgame.gameid + '<br />';
         html+='<br /></td>';

         html+='</tr>';
         html+='</tbody></table>';
         $("#result").html(html);
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error </div>').appendTo($("#result"));
	});

}*/













