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
import voyage.sprint6.Profil;

@WebServlet("/sprint6_Profil")
public class ServletProfil extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String NomProfil = String.valueOf(request.getParameter("NomProfil"));
        String MinExp =String.valueOf(request.getParameter("MinExp"));
        String MaxExp =String.valueOf(request.getParameter("MaxExp"));
        String TauxAug =String.valueOf(request.getParameter("TauxAug"));
        String salaireHor =String.valueOf(request.getParameter("salaireHor"));
        String type = request.getParameter("salaire");
        Profil profil = new Profil(NomProfil, MinExp, MaxExp, TauxAug, type, salaireHor);
        try {
            profil.save();
        } catch (Exception e) {
            e.printStackTrace();        
        }
        response.sendRedirect("index.html");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            List <Profil> l = Profil.getAll();
            request.setAttribute("liste",l);
            RequestDispatcher rd = request.getRequestDispatcher("sprint6/Post_Employe.jsp");
            rd.forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }        
    }
}