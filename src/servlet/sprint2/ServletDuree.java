package servlet.sprint2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voyage.Duree;
import voyage.Localisation;

@WebServlet("/sejour")
public class ServletDuree extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                String nom = request.getParameter("nom");
                String min = request.getParameter("min");
                String max = request.getParameter("max");

                System.out.println("****NOM: "+nom);
                System.out.println("****MIN: "+min);
                System.out.println("****MAX: "+max);

                Duree loc = new Duree(nom, Integer.valueOf(min), Integer.valueOf(max));
                loc.save();
                response.sendRedirect("index.html");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
              
    }     
    
}
