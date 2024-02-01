<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%
    List<Voyage> voyages = (List<Voyage>)request.getAttribute("voyages");
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
    <title>Recherche par benefice</title>
</head>

<body>
    <h2>Liste des voyages correspondants à l fourchette</h2>
    <table border="1">
        <tr>
            <th>Voyage</th>
            <th>Prix de revient</th>
            <th>Prix de vente</th>
            <th>Bénéfice</th>
        </tr>

        <%for(Voyage voyage : voyages){%>
            <tr>
                <td><% out.print(voyage.getBDLNom()); %></td>
                <td><% out.print(voyage.getRevient()); %></td>
                <td><% out.print(voyage.getPrix_vente()); %></td>
                <td><% out.print(voyage.getBenefice()); %></td>
            </tr>            
        <%}%>
    
    </table>
    
    <p><a href="index.html">Accueil</a></p>


</body>
</html>