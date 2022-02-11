<%@page import="model.Autore"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <%@ include file ="header.jsp" %>
</head>
<body>


 <div class="container">
	<table class="table table-hover">
	<tr> 
		<th class="w3-grey">ID</th>	
		<th class="w3-grey">Nome</th>
		<th class="w3-grey">Cognome</th>
		<th class="w3-grey">Nazionalità</th>
		<th class="w3-grey">Modifica</th>	
		<th class="w3-grey">Elimina</th>		
	</tr>
 
	<%
 		List<Autore> lista = (List<Autore>) request.getAttribute("listaAutori");
		if(lista!=null){
			for(Autore p : lista){
				out.print("<tr>");
				out.print("<td>" + p.getId() + 	 "</td>");
				out.print("<td>" + p.getNome() + "</td>");
				out.print("<td>" + p.getCognome() + "</td>");
				out.print("<td>" + p.getNazionalita() + "</td>");
 
				out.print("<td>" + 
							"<form method = \"post\" action=\"deleteAutore?idAutore=" + p.getId() + "\">" +
							"<input type=\"submit\" value=\"Elimina\"></input>" +
							"</form> </td>");
				out.print("<td>" + 
						"<form method = \"get\" action=\"modificaAutore\">"+
						"<input type=\"text\" hidden name=\"modificaId\" value=\""+p.getId()+ "\" >" +
						"<input type=\"submit\"value=\"Modifica\"></input>" +
						"</form> </td>");
				out.print("</tr>");
			}
		}else{
			out.print("<br> Non ci sono autori in lista <br>");
		}
 
 
	%>
 
	</table>
 </div>
	<% 
	String msg=(String)request.getAttribute("avvisoMessaggio");
	if(msg!=null){
		out.print("<script>alert(\""+msg+"\");</script>");
	}
	%>
 
</body>
</html>