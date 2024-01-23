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

@WebServlet("/sprint2_voyage")
public class ServletVoyage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            try {
                String idactivite = request.getParameter("idactivite");
                Connection c = GenericDAO.getConnection();
                List<Composition> compo = Composition.getListByAct(idactivite, c);
                c.close();
                request.setAttribute("voyages", compo);
                RequestDispatcher rd = request.getRequestDispatcher("sprint2/get_voyage_by_act.jsp");
                rd.forward(request, response);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}
