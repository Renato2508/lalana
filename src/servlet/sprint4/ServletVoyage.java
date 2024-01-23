package servlet.sprint4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Voyage;
import voyage.exception.LackExceptions;

@WebServlet("/sprint4_voyage")
public class ServletVoyage extends HttpServlet{
    public void init() throws ServletException {
        try {
            Connection c = GenericDAO.getConnection();
            Voyage.setAll_voyage(Voyage.getAll_voyage(c));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = Date.valueOf(request.getParameter("date"));
        int nb = Integer.valueOf(request.getParameter("nb"));
        String id_voyage = request.getParameter("id_voyage");
        Voyage v = Voyage.getAll_voyages().get(id_voyage);
        try {
            v.reserver(nb, date);
        } catch (LackExceptions le) {
            request.setAttribute("erreur",le);
            RequestDispatcher rd = request.getRequestDispatcher("sprint4/erreur_reservation.jsp");
            rd.forward(request, response);
        }
        catch(Exception e
        ){
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("sprint4/post_reservation.jsp");
        rd.forward(request, response);
    }

    
}
