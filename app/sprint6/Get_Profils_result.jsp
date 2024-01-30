<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Profils et salaires actuels</h1>
    <table>
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
                <td><% out.print(voyage.getNom()); %></td>
                <td><% out.print(voyage.getProfil().getNomProfil()); %></td>
                <td><% out.print(voyage.getProfil().getTauxHoraire()); %></td>
            </tr>            
        <%}%>
        </tbody>
    </table>
</body>
</html>