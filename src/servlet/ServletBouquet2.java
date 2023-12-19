package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import javax.xml.transform.Source;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import voyage.Bouquet;
import generalisation.genericDAO.GenericDAO;


@WebServlet("/bouquet_by_id")
public class ServletBouquet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // liste des bouquets  pour l'id donne
        String id_bouquet = request.getParameter("id_bouquet");

        try {

            Connection c = GenericDAO.getConnection();
            Bouquet bouquet = Bouquet.getById(id_bouquet, c);
            
            RequestDispatcher rd = request.getRequestDispatcher("Get_Activite.jsp");
            request.setAttribute("bouquet", bouquet);
            rd.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        // passer cette liste à la page 
        
    }




}
