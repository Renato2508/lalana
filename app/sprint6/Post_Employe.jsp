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
    <form action="sprint6_Employe" method="post">
        <p>
            <label for="Nom">
                Nom
            </label>
            <input type="text" name="Nom">
        </p>
        <p>
             <label for="Profil">
                 Profil 
             </label>
             <select name="profil" id="">
                 <%for(Profil e : profils){
                 %>
                 <option value="<%= e.getIdprofil() %>"><%= e.getNom() %></option>
                 <%}%>
             </select> 
        </p>  

        <p>    
        <label for="DateEmb">
            Date embauche
        </label>
        
        <input type="date" name="date">
        </p>

        <p> 
        <input type="submit" value="OK">
        </p>
    </form>
    <p><a href="../index.html">Accueil</a></p>

</body>
</html>