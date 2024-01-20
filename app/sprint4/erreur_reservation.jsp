<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "voyage.*" %>
<%@ page import = "voyage.exception.*" %>


<%  
    LackExceptions le = (LackExceptions)request.getAttribute("erreur");
    ArrayList<Composit> compos = le.getCompo();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Réserver un voyage</title>
    <h1>Réserver un voyage</h1>
</head>

<body>
    <h1>!!! ERREUR  !!!</h1>
    <h2>Stock insuffisant pour les Activités suivantes</h2>

    <table border ="1">
        <tr>
            <th>Actvite</th>
            <th>Stock manquant</th>
            
        </tr>

        <%for(Composit compo : compos){%>
            <tr>
                <td><% out.print(compo.getActivite().getNomActivite()); %></td>
                <td><% out.print(compo.getFrequence()); %></td>
            </tr>            
        <%}%>

    </table>
</body>
</html>