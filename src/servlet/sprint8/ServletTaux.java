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
import java.util.List;

@WebServlet("/taux")
public class ServletTaux  extends HttpServlet{
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String taux = request.getParameter("taux");
           
            Connection c = null ; PreparedStatement pst =  null;
            try {
                 c= GenericDAO.getConnection();
                String sql = "update tauxhoraire set tauxbase = ?";
                pst = c.prepareStatement(sql);
                pst.setDouble(1,Double.parseDouble(taux));
                pst.executeUpdate();
                c.commit();
                System.out.println("commit ");
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            finally{
                try {
                    pst.close();
                    c.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                
            }
            response.sendRedirect("sprint8/post_taux_horaire.html");
        }
    }