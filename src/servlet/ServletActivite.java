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

@WebServlet("/activite")
public class ServletActivite extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String nom = request.getParameter("nomActivite");
            String prix = request.getParameter("prix");
            String date = request.getParameter("date");

            Activite activite = new Activite(nom, prix, date);
            try {
                ArrayList<Bouquet> bouquetsValides = new ArrayList<Bouquet>();
                List<Bouquet> bouquets = GenericDAO.getAll(Bouquet.class);
                for(Bouquet bouquet: bouquets){
                    //System.out.println("CB: "+ request.getParameter(String.valueOf(bouquet.getIdBouquet())));
                    if(request.getParameter(String.valueOf(bouquet.getIdBouquet()))!= null){
                        bouquetsValides.add(bouquet);
                    }
                }
                activite.setBouquets(bouquetsValides);
                activite.save();
                response.sendRedirect("index.html");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}
