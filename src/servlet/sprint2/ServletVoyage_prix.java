package servlet.sprint2;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Composition;

@WebServlet("/sprint2_voyage_prix")
public class ServletVoyage_prix extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            try {
                String min = request.getParameter("min");
                String max = request.getParameter("max");

                Connection c = GenericDAO.getConnection();
                List<Composition> compo = Composition.getByFourchette(Integer.valueOf(min), Integer.valueOf(max), c);
                c.close();
                request.setAttribute("voyages", compo);
                RequestDispatcher rd = request.getRequestDispatcher("sprint2/get_voyage_by_prix.jsp");
                rd.forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
