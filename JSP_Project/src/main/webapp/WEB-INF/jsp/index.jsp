<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<title>Prova webSevice</title>
		
		<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
		
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css">
		 
		 <!-- 
		<link rel="stylesheet" href="css/bootstrap.css">
		 -->
		 
		<!-- regole di stile css -->
		<style>
				
		</style>
		
		
		<script>
			/*
				javascript può accedere ai tag html e modificarne gli attributi
				può fare chiamate asincrone a service esterni
				può definire variabili function e oggetti
			*/
			a = 10
			let nome = "pippo"
			var eta = 10
			
			// gli script dentro la ready function vengono eseguito dopo aver completato il load della pagina
			$(document).ready(function () {
				let load = false
				// verifichiamo che posso accedere ad un tag html senza problemi perchè jquery
				// attende il completamento del caricamento della pagina prima di eseguire lo script
				
				// let ref = document.getElementById("output")
				// ref.innerHTML = "Testo iniettato da javascript"
				
				// mi riferisco ad un tag html con id
				$("#output").html("<h2>Testo iniettato da jQuery</h2>")
				
				function loadDati(){
			        $.ajax({
			            url: 'http://localhost:8050/api/utenti/all',
			            type: 'GET',
			            async: true,
			            dataType: 'json',
			            contentType: 'application/json',
			            data: '',
			            success: function(result){
			                console.log(result)
			                console.log(result.length)  
	 						
			                let campi = ["ID", "ADMIN", "NOME", "COGNOME", "INDIRIZZO", "CAP", "CITTA"]
			                
			                $("#output").html(impagina(campi, result))
			            },
			            error: function(xhr, resp, text){
			                $("#output").html("Dati utente al momento non disponibili, errore del server")
			                console.log(xhr, resp, text)
			            }
			        })				
				}
				
		        $("#output").hide()
		        
				$("#mostradati").click(function(){
					// alert("cliccato pulsante")
					if(!load)
					{
						loadDati()
						load = true						
					}	

					if($('#mostradati').text() == "Carica dati dal server")
						$('#mostradati').text("Nascondi i dati");
					else 
						$("#mostradati").text('Carica dati dal server')
					
					// mostra / nasconte il selettore output
					// $("#output").toggle()
					// $("#output").fadeToggle( "slow", "linear" )
					$("#output").slideToggle("slow")
						
				})
				

	        		        				
			});
			
			/*
				riceve un json di dati e restituisce una tabella html con i dati del json
			*/
			function impagina(fields, dati){
				let t = "<table class=table>"
				
				t += "<tr>"
				for(i=0; i< fields.length; i++)
				{
					t += "<th>" + fields[i] + "</th>"
				}
				t += "</tr>"
				
				/*
				$.each(dati[0],function(k, v){
					console.log(k + " " +v)
				})
				*/
				
				for(i=0; i < dati.length; i++)
				{
					// apro il tag nuova riga
					t += "<tr>"
					
					// prendo la i-esima riga di valori da dati e la trasformo in Object
					let rigaDati = Object.values(dati[i])
					console.log(dati[i])
					console.log(rigaDati)
					
					for(j=0; j < fields.length; j++)
					{
						t += "<td>" + rigaDati[j] + "</td>"
					}
					
					t +="</tr>"
					
				}
				
				
				t += "</table>"
				
				// alert(t)
				console.log(t)
				
				return t
			}

		</script>
		
	</head>
	<body>
		<div class="container">
			<h1>Sono il client</h1>
			<hr>
			<a href="dati.html">Dati</a> &nbsp;&nbsp;&nbsp;&nbsp;     <a href="about.html">Info</a>
			<hr>
			
			<!--  img è un tag in-line e non ha la chiusura del tag -->
			<div>
				
				<img width=100% src="https://thumbs.dreamstime.com/z/paesaggio-toscana-con-alberi-da-strada-castelli-e-cipressi-italia-un-bellissimo-di-strade-160658932.jpg">

			</div>
			
			<hr>
			
			<button id="mostradati" class="btn btn-primary">Carica dati dal server</button>
			<br>
			
			<div id="output"></div>
			
			
			
			<!-- 
			<div>Ciao
				<p>Prova di testo ....</p>
				<p>Prova2 di testo ....</p>
			</div>
			 -->
			 
			<!-- Tag con righe e colonne di dati allineati 
			<table class="table">
				
				<tr>
					<td>r1 Colonna 1</td>
					<td>r1 Colonna 2</td>
					<td>r1 Colonna 3</td>
				</tr>
				
				<tr>	
					<td>r2 Colonna 1</td>
					<td>r2 Colonna 2</td>
					<td>r2 Colonna 3</td>
				</tr>
				
			</table>
			-->
			 
			<div>
			
				<form class="form-horizontal" action="elabora.html" method="get">
					<fieldset>
					
					<!-- Form Name -->
					<legend>Registrazione utente</legend>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="nome"></label>  
					  <div class="col-md-5">
					  <input id="nome" name="nome" type="text" placeholder="digitare il nome" class="form-control input-md" required="">
					    
					  </div>
					</div>

					<!-- Text input-->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="nome"></label>  
					  <div class="col-md-5">
					  <input id="cognome" name="cognome" type="text" placeholder="digitare il cognome" class="form-control input-md" required="">
					    
					  </div>
					</div>

					
					<!-- Button -->
					<div class="form-group">
					  <label class="col-md-4 control-label" for="salva"></label>
					  <div class="col-md-4">
					    <button id="salva" name="salva" class="btn btn-primary">Salva</button>
					  </div>
					</div>
					
					</fieldset>
				</form>
			
			</div>
		</div>
		
	</body>
</html>