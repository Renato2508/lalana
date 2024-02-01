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

@WebServlet("/sprint8_voyage_benefice")
public class ServletVoyageBenefice  extends HttpServlet{
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String min = request.getParameter("min");
            String max = request.getParameter("max");

            try {
                 List<Voyage> v = Voyage.get_all_with_benefice_bet(min, max);
                request.setAttribute("voyages", v);
                RequestDispatcher rd = request.getRequestDispatcher("sprint8/get_voyage_by_benefice.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();            }
        }
}