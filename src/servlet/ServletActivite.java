package servlet;

import java.io.IOException;
import java.util.List;

import generalisation.genericDAO.GenericDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import voyage.Bouquet;
import voyage.Activite;
import java.util.ArrayList;
import java.sql.Connection;

@WebServlet("/activite")
public class ServletActivite extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String nom = request.getParameter("nomActivite");
            Activite activite = new Activite(nom);
            try {
                ArrayList<Bouquet> bouquetsValides = new ArrayList<Bouquet>();
                List<Bouquet> bouquets = GenericDAO.getAll(Bouquet.class);
                for(Bouquet bouquet: bouquets){
                    if(request.getParameter(bouquet.getNomBouquet()) != null){
                        bouquetsValides.add(bouquet);
                    }
                }
                activite.setBouquets(bouquetsValides);
                Connection c = GenericDAO.getConnection();
                activite.save(c);
                c.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}
