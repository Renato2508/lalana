<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%
    List<Activite> bouquets = (List<Activite>)request.getAttribute("activites");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <h1>Liste des Activités possibles</h1>
</head>

<body>
    <h2>Choisir une activité à détailler</h2>
            <%for(Activite bouquet : bouquets){%>
                <p><a href="sprint2_voyage?idactivite=<% out.print(bouquet.getIdActivite()); %>"> <% out.print(bouquet.getNomActivite()); %></a></p>
            <%}%>
</body>
</html>