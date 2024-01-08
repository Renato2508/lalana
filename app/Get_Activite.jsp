<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %><!DOCTYPE html>

<%  Bouquet b = (Bouquet)request.getAttribute("bouquet");
    List<Activite> acts = b.getActivites();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste Bouquets</title>
    <h1>Liste d'Activités pour le bouquet</h1>
</head>
<style>
    body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

h1 {
    text-align: center;
    color: #333;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    background-color: #fff;
    margin: 5px;
    padding: 10px;
    border-radius: 4px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

li:hover {
    background-color: #f0f0f0;
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.2);
}

</style>
<body>
    <p><h2>Liste des activités pour le bouquet: <% out.print(b.getNomBouquet()); %> </h2></p>
    <ul>
        <%for(Activite activite : acts) { %>
        <li><% out.print(activite.getNomActivite()); %></li>
        <%}%>
    </ul>
    <button><a href="index.html">Retour</a></button>
</body>
</html>