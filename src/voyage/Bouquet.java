package voyage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.transform.Source;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;
import java.sql.Statement;

@DBTable(name = "bouquet", autoIncrement = true)
public class Bouquet {
    @DBField(name="idbouquet", isPrimaryKey = true)
    Integer idBouquet;

    @DBField(name="nombouquet")
    String nomBouquet;

    ArrayList<Activite> activites = new ArrayList<Activite>();

    
    
    public Bouquet() {
    }


    public Bouquet(String nomBouquet) {
        this.nomBouquet = nomBouquet;
    }


    public Bouquet(Integer idBouquet) {
        this.idBouquet = idBouquet;
    }


    public Bouquet(Integer idBouquet, String nomBouquet) {
        this.idBouquet = idBouquet;
        this.nomBouquet = nomBouquet;
    }


    public static List<Bouquet> getAll() throws Exception{
        try {
            return GenericDAO.getAll(Bouquet.class);
        } catch (Exception e) {
            throw e;        }
    }


    public static Bouquet getById(String id, Connection conn) throws Exception{
        int id_req = Integer.valueOf(id);
        try {
            String sql = String.format("SELECT * FROM v_activitebouquet where idbouquet = %d order by idbouquet asc ", id_req);

            System.out.println("******* SOURCE: "+sql);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Bouquet bouquet;
            Activite activite = new Activite();
            //batiment.setIdBatiment(-1);
            rs.next();
            bouquet = new Bouquet(rs.getInt("idbouquet"),rs.getString("nomBouquet"));
            while (rs.next()) {
                
                activite = new Activite(rs.getInt("idactivite"), rs.getString("nomactivite"));
                bouquet.getActivites().add(activite);

            }

            return bouquet;
            
        } catch (Exception e) {
            throw e  ;      
        }
           
    }


    public void save() throws Exception{
        try {
            GenericDAO.save(this);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Integer getIdBouquet() {
        return idBouquet;
    }
    public void setIdBouquet(Integer idBouquet) {
        this.idBouquet = idBouquet;
    }
    public String getNomBouquet() {
        return nomBouquet;
    }
    public void setNomBouquet(String nomBouquet) {
        this.nomBouquet = nomBouquet;
    }
    public ArrayList<Activite> getActivites() {
        return activites;
    }
    public void setActivites(ArrayList<Activite> activites) {
        this.activites = activites;
    }

    
}
