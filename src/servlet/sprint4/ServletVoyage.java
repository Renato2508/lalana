package servlet.sprint4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Bouquet;
import voyage.Voyage;
import voyage.exception.LackExceptions;
import voyage.sprint7.Client;

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
        String id_client = request.getParameter("idclient");
        Voyage v = Voyage.getAll_voyages().get(id_voyage);
        try {
            v.reserver(nb, date, id_client);
                    response.sendRedirect("index.html");

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
        try {
                List<Client> bouquets = GenericDAO.getAll(Client.class);
                request.setAttribute("clients", bouquets);
        RequestDispatcher rd = request.getRequestDispatcher("sprint4/post_reservation.jsp");
        rd.forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
