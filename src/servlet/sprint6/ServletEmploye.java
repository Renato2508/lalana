package servlet.sprint6;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.sprint6.Employe;

@WebServlet("/sprint6_Employe")
public class ServletEmploye extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Nom = request.getParameter("Nom");
        String idProfil = request.getParameter("profil");
        String dateEmbauche = request.getParameter("date");
        System.out.println("------> DATE: "+dateEmbauche);
        Employe employe = new Employe(Nom, dateEmbauche, idProfil);
        try {
            employe.save();
        } catch (Exception e) {
            e.printStackTrace();        
        }
        response.sendRedirect("index.html");
    }
}