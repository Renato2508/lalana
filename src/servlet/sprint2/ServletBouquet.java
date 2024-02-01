package servlet.sprint2;

import java.io.IOException;
import java.sql.Connection;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Bouquet;

@WebServlet("/sprint2_bouquet_by_id")
public class ServletBouquet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // liste des bouquets  pour l'id donne
        String idbouquet = request.getParameter("idbouquet");
        String idlocalisation = request.getParameter("idlocalisation");
        String idduree = request.getParameter("idduree");
        
        try {

            Connection c = GenericDAO.getConnection();
            Bouquet bouquet = Bouquet.getById(idbouquet, c);
            
            RequestDispatcher rd = request.getRequestDispatcher("sprint2/post_composition2.jsp");
            request.setAttribute("bouquet", bouquet);
            request.setAttribute("idlocalisation", Integer.valueOf(idlocalisation));
            request.setAttribute("idduree", Integer.valueOf(idduree));

            rd.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        // passer cette liste à la page 
        
    }

}
