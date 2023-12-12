package voyage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Activite{
    Integer idActivite;
    String nomActivite;
    Bouquet bouquet;
    

    public Activite(String nomActivite, Bouquet bouquet) {
        this.nomActivite = nomActivite;
        this.bouquet = bouquet;
    }

    public Activite(Integer idActivite, String nomActivite) {
        this.idActivite = idActivite;
        this.nomActivite = nomActivite;
    }

    // METHODS

    public static int getMaxId(Connection conn)throws Exception{
        String req = "select max(idactivite) as maxid from activite";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(req);
            int res = -1;
            if(rst.next())
                res = rst.getInt("maxId");
            if(res > 0)
                return res;
            else throw new Exception("### ERREUR LORS E LA RECUPERATION DE L'ID");
            
        } catch (Exception e) {
            throw e;        
        }
    }

    public void save(Connection conn) throws Exception{
        String sql1 = String.format("insert into activite values('%s')", this.nomActivite);
        System.out.println("#### INSERTION ACTIVITE: "+sql1);
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql1);

            int id = Activite.getMaxId(conn);
            String sql2 = String.format("insert into activitebouquet values(%d, %d)", id, this.bouquet.getIdBouquet());
            stmt.executeUpdate(sql2);

        } catch (Exception e) {
            try {
                conn.rollback();
                throw e;
            } catch (Exception e1) {
                throw e1;
            }
        }
    }


    // GET SET

    public Integer getIdActivite() {
        return idActivite;
    }
    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }
    public String getNomActivite() {
        return nomActivite;
    }
    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }
}