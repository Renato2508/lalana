package servlet.sprint2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import generalisation.genericDAO.GenericDAO;
import java.util.List;
import voyage.*;

@WebServlet("/donnee_composition")
public class ServletDonneeComposition extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // liste des bouquets
            List<Bouquet> bouquets = GenericDAO.getAll(Bouquet. class);
            System.out.println("****: "+bouquets);

            //liste des localisations
            List<Localisation> localisations = GenericDAO.getAll    (Localisation.class);
            System.out.println("****: "+localisations);
            // liste des durées
            List<Duree> durees = GenericDAO.getAll(Duree.class);
            System.out.println("****: "+durees);

            request.setAttribute("bouquets", bouquets);
            request.setAttribute("localisations", localisations);
            request.setAttribute("durees", durees);

            RequestDispatcher rd = request.getRequestDispatcher("sprint2/post_composition.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

}
