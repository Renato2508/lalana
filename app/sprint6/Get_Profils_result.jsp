<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>
<%@ page import = "voyage.sprint6.*" %>


<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
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
    <title>Document</title>
</head>
<body>
    <h1>Profils et salaires actuels</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Profil Actuel</th>
                <th>Taux Horaire</th>
            </tr>
        </thead>
        <tbody>
        <%for(Employe employe : employes){%>
            <tr>
                <td><% out.print(employe.getNom()); %></td>
                <td><% out.print(employe.getProfil().getNom()); %></td>
                <td><% out.print(employe.getProfil().getTauxHoraire()); %></td>
                <td><% out.print(employe.getDateEmbauche()); %></td>

            </tr>            
        <%}%>
        </tbody>
    </table>
        <p><a href="index.html">Accueil</a></p>

</body>
</html>