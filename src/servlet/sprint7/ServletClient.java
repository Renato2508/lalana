package servlet.sprint7;
import java.io.IOException;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import voyage.Bouquet;
import voyage.sprint7.Client;

import java.util.List;

@WebServlet("/client")
public class ServletClient  extends HttpServlet{
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String nom =request.getParameter("nom");
            String sexe =request.getParameter("sexe");

            Client b =new Client (nom, sexe);
            try {
                b.save();
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            response.sendRedirect("sprint7/post_client.html");
        }
}
