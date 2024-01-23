package servlet.sprint2;

import java.io.IOException;
import java.sql.Connection;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Composition;

@WebServlet("/composition")
public class ServletComposition extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    int idduree = Integer.valueOf(request.getParameter("idduree"));
    int idlocalisation = Integer.valueOf(request.getParameter("idlocalisation"));
    int idbouquet = Integer.valueOf(request.getParameter("idbouquet"));
    int idactivite = Integer.valueOf(request.getParameter("idactivite"));
    int frequence = Integer.valueOf(request.getParameter("frequence"));

    Composition c = new Composition(idbouquet, idactivite, idlocalisation, idduree, frequence);

    try {
        c.save();
    } catch (Exception e) {
        e.printStackTrace();   
    }
    response.sendRedirect("donnee_composition");

    }
}
