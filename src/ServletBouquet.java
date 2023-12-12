import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bouquet")
public class ServletBouquet  extends HttpServlet{
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String nomBouquet=request.getParameter("nomBouquet");
            Bouquet b =new Bouquet (nomBouquet);
            try {
                b.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute(nomBouquet, nomBouquet);
            response.sendRedirect("Post_Bouquet");
    }
}
