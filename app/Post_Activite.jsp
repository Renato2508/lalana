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
        <label for="nomActivite">Nom de l'activité</label>
        <input type="text">
        <p>Bouquets rattachés</p>
        <label for="rattaché">
            <input type="checkbox" id="premium" name="rattaché"> Premium
        </label>
        <br>
        <label for="rattaché">
            <input type="checkbox" id="gold" name="rattaché"> Gold
        </label>
        <br>
        <label for="rattaché">
            <input type="checkbox" id="decouverte" name="rattaché"> Découverte
        </label>
        <br>
        <label for="rattaché">
            <input type="checkbox" id="VIP" name="rattaché"> V.I.P
        </label>

        <p>
            <input type="submit" value="Enregistrer">
        </p>

    </form>
</body>
</html>