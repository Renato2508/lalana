<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %><!DOCTYPE html>

<%  Bouquet b = (Bouquet)request.getAttribute("bouquet");
    List<Activite> acts = b.getActivites();
    int  idduree = (Integer)request.getAttribute("idduree");
    int  idlocalisation = (Integer)request.getAttribute("idlocalisation");

%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>(2)Créer des compostions </title>
</head>

<body>

    <form action="composition" method="post">

            <p>Activite:
                <select name="idactivite">
                    <% for(Activite act : acts) { %>
                        <option value="<%= act.getIdActivite() %>"> <% out.print(act.getNomActivite()); %></option>           
                    <%}%>
                </select>
            </p>
            <p>Fréquence: <input name="frequence" type="text" ></p>

            <p><input type="hidden" name="idduree" value="<% out.print(idduree); %>"></p>
            <p><input type="hidden" name="idlocalisation" value="<% out.print(idlocalisation); %>"></p>
            <p><input type="hidden" name="idbouquet" value="<% out.print(b.getIdBouquet()); %>"></p>



            <p><input type="submit" value="OK"></p>
        </form>
!
    <p><h2>Liste des activités pour le bouquet: <% out.print(b.getNomBouquet()); %> </h2></p>
    <ul>
        <%for(Activite activite : acts) { %>
        <li><% out.print(activite.getNomActivite()); %></li>
        <%}%>
    </ul>
    <button><a href="../index.html">Retour</a></button>
</body>
</html>