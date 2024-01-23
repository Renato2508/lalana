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
import voyage.Activite;
import voyage.Voyage;
import voyage.exception.LackExceptions;

@WebServlet("/sprint4_activite")
public class ServletActivite extends HttpServlet{
    public void init() throws ServletException {
        try {
            Activite.setAll(Activite.generateHashMap());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = Date.valueOf(request.getParameter("date"));
        int nb = Integer.valueOf(request.getParameter("nb"));
        int id_activite = Integer.valueOf(request.getParameter("id_activite"));
        Activite v = Activite.getAll().get(id_activite);
        try {
            v.achat(nb, date);
            response.sendRedirect("index.html");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("sprint4/achat_activites.jsp");
        rd.forward(request, response);
    }
}