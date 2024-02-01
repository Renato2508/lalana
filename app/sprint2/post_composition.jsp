<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>

<%
    List<Bouquet> bouquets = (List<Bouquet>)request.getAttribute("bouquets");
    List<Localisation> localisations = (List<Localisation>)request.getAttribute("localisations");
    List<Duree> durees = (List<Duree>)request.getAttribute("durees");

%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>(1)Créer des compositions</title>
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
    </head>
    <body>
        <form action="sprint2_bouquet_by_id" method="post">

            <p>Bouquet:
                <select name="idbouquet">
                    <% for(Bouquet bouquet : bouquets) { %>
                        <option value="<%= bouquet.getIdBouquet() %>"> <% out.print(bouquet.getNomBouquet()); %></option>           
                    <%}%>
                </select>
            </p>

            <p>Localisation:
                <select name="idlocalisation">
                    <% for(Localisation localisation : localisations) { %>
                        <option value="<%= localisation.getIdLocalisation() %>"> <% out.print(localisation.getNomLocalisation()); %></option>           
                    <%}%>
                </select>
            </p>

            <p>Duree:
                <select name="idduree">
                    <% for(Duree duree : durees) { %>
                        <option value="<%= duree.getIdDuree() %>"> <% out.print(duree.getNomDuree()); %></option>           
                    <%}%>
                </select>
            </p>
            
            <p><input type="submit" value="OK"></p>
        </form>
    <p><a href="index.html">Accueil</a></p>
    </body>

<html>
