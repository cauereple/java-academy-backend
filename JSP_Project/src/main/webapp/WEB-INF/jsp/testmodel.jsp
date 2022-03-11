<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- importiamo jstl -->    

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		int a = 10;
		ArrayList<String> l = new ArrayList<String>();
		
	%>
	<!-- 
		con ${nome} accediamo alle variabili definite dal model
		bisogna conoscere i nomi delle variabili definite dal model
		l'accesso alle variabili del model funziona anche senza 
		importare la direttiva taglib uri
	 -->
	<h1>Ciao ${nome}</h1>
	
	<h2>${lista[0]}</h2>
	<h2>${lista.size()}</h2>
	
	<!-- per accedere ai tag di jstl (eventualmente anche con prefix 
		in questo esempio prefix = "c")  occorre importare lal direttiva taglib uri
		
	-->
	<c:forEach items="${lista}" var="item">
		<p>${item}</p>
	</c:forEach> 
</body>
</html>