package servlet.sprint2;

import java.io.IOException;
import java.util.List;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Bouquet;
import voyage.Activite;

@WebServlet("/sprint2_activite")
public class ServletActivite extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            try {
                // liste de tous les bouquets existantsp
                List<Activite> bouquets = GenericDAO.getAll(Activite.class);
                request.setAttribute("activites", bouquets);
                RequestDispatcher rd = request.getRequestDispatcher("sprint2/get_activite.jsp");
                rd.forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
