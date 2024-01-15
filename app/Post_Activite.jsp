<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%
    List<Bouquet> bouquets = (List<Bouquet>)request.getAttribute("bouquets");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
    body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    }

    form {
    max-width: 600px;
    margin: 20px auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
    text-align: center;
    color: #333;
    }

    label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    }

    input[type="text"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 16px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    }

    p {
    margin-bottom: 8px;
    font-weight: bold;
    }

    input[type="checkbox"] {
    margin-right: 8px;
    }

    button {
    background-color: #4caf50;
    color: #fff;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    }

    button:hover {
    background-color: #45a049;
    }


</style>

    <title>Création Activité</title>
    <h1>Créer une activité</h1>
</head>

<body>
    <form action="activite" method="post">
        <p>
            <label for="nomActivite">Nom de l'activité</label>
            <input name="nomActivite" type="text">
        <p>

        <p>
            <label for="nomActivite">P.U. de l'activité</label>
            <input name="prix" type="text">
        <p>

        <p>
            <label for="nomActivite">Date de creaion/MAJ de l'activité</label>
            <input name="date" type="date">
        <p>

        
        <p>Bouquets rattachés</p>
        <label for="rattaché">
            <% for(Bouquet bouquet : bouquets) { %>
            <% out.print(bouquet.getNomBouquet()); %><input type="checkbox" name="<%=bouquet.getIdBouquet() %>" id="premium" >           
            <%}%> 
        </label>
        <label><input type="submit" value="Creer l'activite"> </label>
    </form>
</body>
</html>