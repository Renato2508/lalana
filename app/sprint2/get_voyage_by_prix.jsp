<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%
    List<Composition> voyages = (List<Composition>)request.getAttribute("voyages");
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
    </style>
    <title>Recherche par prix</title>
</head>

<body>
    <h2>Liste des voyages correspondants à l fourchette</h2>
    <table border="1">
        <tr>
            <th>Bouquet</th>
            <th>Localisation</th>
            <th>Durée</th>
            <th>Prix</th>
        </tr>

        <%for(Composition voyage : voyages){%>
            <tr>
                <td><% out.print(voyage.getBouquet().getNomBouquet()); %></td>
                <td><% out.print(voyage.getLocalisation().getNomLocalisation()); %></td>
                <td><% out.print(voyage.getDuree().getNomDuree()); %></td>
                <td><% out.print(voyage.getPrix()); %></td>
            </tr>            
        <%}%>
    <p><a href="index.html">Accueil</a></p>
    </table>
</body>
</html>