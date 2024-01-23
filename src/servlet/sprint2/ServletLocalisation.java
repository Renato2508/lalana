package servlet.sprint2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Localisation;

@WebServlet("/localisation")
public class ServletLocalisation extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String nom = request.getParameter("nom");
                Localisation loc = new Localisation(nom);
                loc.save();
                response.sendRedirect("index.html");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
              
    }     
    
}
