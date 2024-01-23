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

@WebServlet("/sprint6_ProfSalaire")
public class ServletProfilSalaires extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date daty = Date.valueOf(request.getParameter("daty"));
        Employe.getWithActualProfiles(daty);
        request.setAttribute("employes", Employe.getWithActualProfiles(daty));
        RequestDispatcher rd = request.getRequestDispatcher("sprint6/Get_Profils_result");
    }
}