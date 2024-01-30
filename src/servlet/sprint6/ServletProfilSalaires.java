package servlet.sprint6;
import java.io.IOException;
import java.sql.Date;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.sprint6.Employe;

@WebServlet("/sprint6_ProfSalaire")
public class ServletProfilSalaires extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date daty = Date.valueOf(request.getParameter("daty"));
        try {
            Employe.getWithActualProfiles();
            request.setAttribute("employes", Employe.getWithActualProfiles());
            RequestDispatcher rd = request.getRequestDispatcher("sprint6/Get_Profils_result");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}