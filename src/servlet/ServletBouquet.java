package servlet;
import java.io.IOException;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import voyage.Bouquet;
import java.util.List;

@WebServlet("/bouquet")
public class ServletBouquet  extends HttpServlet{
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String nomBouquet=request.getParameter("nomBouquet");
            Bouquet b =new Bouquet (nomBouquet);
            try {
                b.save();
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            request.setAttribute(nomBouquet, nomBouquet);
            response.sendRedirect("Post_Bouquet.html");
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            try {
                List<Bouquet> bouquets = GenericDAO.getAll(Bouquet.class);
                request.setAttribute("bouquets", bouquets);
                RequestDispatcher rd = request.getRequestDispatcher("Post_Activite.jsp");
                rd.forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
}
