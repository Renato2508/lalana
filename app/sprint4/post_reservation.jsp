<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>
<%@ page import = "voyage.sprint7.*" %>

<%  
    HashMap<String, Voyage> voyages = Voyage.getAll_voyages();
    Set<Map.Entry<String,Voyage>> entries = voyages.entrySet();
    List<Client> clients = (List<Client> )request.getAttribute("clients");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Réserver un voyage</title>
    <h1>Réserver un voyage</h1>
</head>
    <form action="sprint4_voyage" method="POST">


        <select name="id_voyage" >
            <%for(Map.Entry e : entries){
                String cle = (String)e.getKey();
                Voyage v = (Voyage)e.getValue();
                String nom = String.format("Voyage %s-%s-%s", v.getBouquet().getNomBouquet(), v.getLocalisation().getNomLocalisation(), v.getDuree().getNomDuree());
            %>
            <option value="<%= cle %>"><% out.print(nom); %></option>
            <%}%>
        </select>

        <select name="idclient" >
            <%for(Client c: clients){
            %>
                <option value="<%= c.getIdClient() %>">
                    <%= c.getNom() %>
                </option>
            <%    
            }
            %>
            
        </select>
        <p>Nombre de personnes: <input type="text" name="nb"></p>
        <p>Date: <input type="date" name = "date"></p>
        <p><input type="submit" value="OK"></p>
    </form>
<body>
    
</body>
</html>