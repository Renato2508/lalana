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
import voyage.VoyageStat;
import voyage.sprint7.Client;
import java.io.PrintWriter;

import java.util.List;

import com.google.gson.Gson;

@WebServlet("/stat")
public class ServletStat  extends HttpServlet{
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           try {
                List<VoyageStat> l = VoyageStat.getStat();
                Gson objectMapper = new Gson();
       
            String json = objectMapper.toJson(l);

            PrintWriter out = response.getWriter();
            out.print(json);
       
           } catch (Exception e) {
                e.printStackTrace();
           }
        }
}
