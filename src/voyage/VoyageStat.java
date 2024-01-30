package voyage;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import generalisation.genericDAO.GenericDAO;

public class VoyageStat implements Serializable{
    String nom;
    int nbhom;
    int nbfem;

    
    public VoyageStat(String nom, int nbfem) {
        this.nom = nom;
        this.nbfem = nbfem;
    }

    public static List<VoyageStat> getStat()throws Exception{
        try {
            Connection c = GenericDAO.getConnection();
            List<VoyageStat> res = new ArrayList<VoyageStat>();
        String query = "SELECT * FROM v_stat order by idbouquet, idlocalisation, idduree, sexe asc";
        
        // Preparation de la requête SQL paramétrée
        PreparedStatement statement = c.prepareStatement(query);            
        // Exécution de la requête et récupération des résultats
        ResultSet rst = statement.executeQuery();
        

        VoyageStat v = null;
        int c1 = 1;
        String nom;
        while(rst.next()){

            if(c1%2 == 1){
                nom = String.format("Voyage %s %s %s", rst.getString("nombouquet"), rst.getString("nomlocalisation"), rst.getString("nomduree"));
                v = new VoyageStat(nom, rst.getInt("vendu"));
                res.add(v);
            }
            else{
                v.setNbhom(rst.getInt("vendu"));
            }
            c1++;
        }
        return res;

        } catch (Exception e) {
            throw e;
        }
        
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getNbhom() {
        return nbhom;
    }


    public void setNbhom(int nbhom) {
        this.nbhom = nbhom;
    }


    public int getNbfem() {
        return nbfem;
    }


    public void setNbfem(int nbfem) {
        this.nbfem = nbfem;
    }




    


}
