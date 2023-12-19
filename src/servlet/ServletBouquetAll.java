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

import java.util.List;

@WebServlet("/bouquet_all")
public class ServletBouquetAll extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // liste des bouquets  pour l'id donne

        try {

            Connection c = GenericDAO.getConnection();
            List<Bouquet> bouquets = Bouquet.getAll();
            
            RequestDispatcher rd = request.getRequestDispatcher("Get_Bouquet.jsp");
            request.setAttribute("bouquets", bouquets);
            rd.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        // passer cette liste à la page 
        
    }




}
