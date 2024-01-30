<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>
<%@ page import = "voyage.sprint6.*" %>

<%  
    List<Profil> profils = (List<Profil>)request.getAttribute("liste");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Embauche Employe</h1>
    <form action="#" method="post">
        <label for="Nom">
            Nom
        </label>
        <input type="text" name="Nom">
        <label for="Profil">
            Profil 
        </label>
        <select name="profil" id="" disabled="disabled">
            <%for(Profil e : profils){

            %>
            <option value="<%= e.getIdprofil() %>"><% e.getNom(); %></option>
            <%}%>
        </select>        
        <label for="DateEmb">
            Date embauche
        </label>
        <input type="date" name="DateEmb">
        <input type="button" value="OK">
    </form>
</body>
</html>