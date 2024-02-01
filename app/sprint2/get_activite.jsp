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
    <style>
        form {
    margin: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f5f5f5;
    color: #333;
}

/* Styles généraux pour les tableaux */
table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: left;
}
p:empty {
    /* Ajoutez ici les styles que vous souhaitez appliquer aux paragraphes vides */
    background-color: #f2f2f2;
    padding: 10px;
    border: 1px solid #ccc;
}

/* Styles pour les liens à l'intérieur des paragraphes avec sauts de ligne */
p:empty a {
    /* Ajoutez ici les styles que vous souhaitez appliquer aux liens dans les paragraphes vides */
    color: #007bff;
    text-decoration: underline;
}
    </style>
    <title>Document</title>
    <h1>Liste des Activités possibles</h1>
</head>

<body>
    <h2>Choisir une activité à détailler</h2>
            <%for(Activite bouquet : bouquets){%>
                <p><a href="sprint2_voyage?idactivite=<% out.print(bouquet.getIdActivite()); %>"> <% out.print(bouquet.getNomActivite()); %></a></p>
            <%}%>
    <p><a href="index.html">Accueil</a></p>
</body>
</html>