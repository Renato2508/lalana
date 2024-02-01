<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%  
    HashMap<Integer, Activite> acts = Activite.getAll();
    Set<Map.Entry<Integer,Activite>> entries = acts.entrySet();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acheter des billets </title>
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
    <h1>Acheter des billets pour un activte</h1>
</head>
    <form action="sprint4_activite" method="POST">


        <select name="id_activite" >
            <%for(Map.Entry e : entries){
                Integer cle = (Integer)e.getKey();
                Activite v = (Activite)e.getValue();
                String nom = v.getNomActivite();
            %>
            <option value="<%= cle %>"><% out.print(nom); %></option>
            <%}%>
        </select>
        <p>Nombre de billets: <input type="text" name="nb"></p>
        <p>Date: <input type="date" name = "date"></p>
        <p><input type="submit" value="OK"></p>
    </form>
    <p><a href="index.html">Accueil</a></p>
<body>
    
</body>
</html>