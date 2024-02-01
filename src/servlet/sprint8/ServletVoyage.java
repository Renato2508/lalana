package servlet.sprint8;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import voyage.Bouquet;
import voyage.Voyage;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/sprint8_voyage")
public class ServletVoyage  extends HttpServlet{
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                 List<Voyage> v = Voyage.get_all_with_revient();
                request.setAttribute("voyages", v);
                RequestDispatcher rd = request.getRequestDispatcher("sprint8/post_prix_vente.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();            }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String idvoyage = request.getParameter("idvoyage");
            String vente = request.getParameter("vente");
            System.out.println("----> idvoyage "+idvoyage);
            System.out.println("----> vente "+vente);

            try {
                
                Voyage.setPrixVente(idvoyage, vente);

            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("sprint8_voyage");

        }

}
