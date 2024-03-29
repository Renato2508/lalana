package voyage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Vector;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@DBTable(name = "activite", autoIncrement = true)
public class Activite{
    @DBField(name="idactivite", isPrimaryKey = true)
    Integer idActivite;

    @DBField(name="nomactivite")
    String nomActivite;

    List<Bouquet> bouquets;
    double prix;
    Date date_maj;
    static HashMap<Integer, Activite> all;    
    

    public Activite(String nomActivite, String prix, String date_maj) {
        this(nomActivite, Double.valueOf(prix), Date.valueOf(date_maj) );
    }

    public Activite(String nomActivite, double prix, Date date_maj) {
        this.nomActivite = nomActivite;
        this.prix = prix;
        this.date_maj = date_maj;
    }

    public Activite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public Activite() {
    }

    public Activite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public Activite(Integer idActivite, String nomActivite) {
        this.idActivite = idActivite;
        this.nomActivite = nomActivite;
    }

    // METHODS

    public void achat(int nb, Date d) throws Exception{
        Connection c = null;
        try{
            c = GenericDAO.getConnection();
            String query = "INSERT INTO mouvement (date_mvt, idactivite, qte_mvt) VALUES (?, ?, ?)";
        
        // Préparation de la requête SQL paramétrée
            PreparedStatement statement = c.prepareStatement(query);
            // Récupération des attributs pour l'insertion
            
            // Attribution des valeurs aux paramètres de la requête
            statement.setDate(1, d);
            statement.setInt(2, idActivite);
            statement.setInt(3, nb);
            
            // Exécution de la requête d'insertion
            statement.executeUpdate();
            c.commit();
        }
        catch(Exception e){
            try {
                c.rollback();
            } catch (Exception e2) {
                throw e2;
            }
            throw e;
        }
        finally{
            c.close();        
        }
    }

    public static HashMap<Integer,Activite> generateHashMap()throws Exception {
        HashMap<Integer,Activite> hm;
        try {
            hm = new HashMap<Integer,Activite>();
            List<Activite> bouquets = GenericDAO.getAll(Activite.class);
            for(Activite a : bouquets){
                hm.put(a.getIdActivite(), a);
            }
            return hm;
        } catch (Exception e) {
            throw e;
        }

    }

    public void reserver(int nb, Date d, Connection c)throws Exception{
        String query = "INSERT INTO mouvement (date_mvt, idactivite, qte_mvt) VALUES (?, ?, ?)";
        
        // Préparation de la requête SQL paramétrée
        try (PreparedStatement statement = c.prepareStatement(query)) {
            // Récupération des attributs pour l'insertion
            
            // Attribution des valeurs aux paramètres de la requête
            statement.setDate(1, d);
            statement.setInt(2, idActivite);
            statement.setInt(3, -1*nb);
            
            // Exécution de la requête d'insertion
            statement.executeUpdate();
        }
        catch(Exception e){
            try {
                c.rollback();
            } catch (Exception e2) {
                throw e2;
            }
            throw e;
        }
        
    }

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

    public void save() throws Exception{
        Connection conn = GenericDAO.getConnection();
        String sql1 = String.format("insert into activite(nomactivite) values('%s')", this.nomActivite);
        System.out.println("#### INSERTION ACTIVITE: "+sql1);
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql1);

            int id = Activite.getMaxId(conn);
            for(Bouquet bouquet: this.bouquets){
                String sql2 = String.format("insert into activitebouquet(idactivite, idbouquet) values(%d, %d)", id, bouquet.getIdBouquet());
                stmt.executeUpdate(sql2);
            }

            // insertion du prix
            String insertQuery = "INSERT INTO activite_prix (idactivite, datemaj, prixunitaire) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setDate(2, this.date_maj);
                preparedStatement.setDouble(3, prix);
                preparedStatement.executeUpdate();
            }

            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
                throw e;
            } catch (Exception e1) {
                throw e1;
            }
        }
        finally{
            conn.close();
        }
    }


    // GET SET

    public static HashMap<Integer, Activite> getAll() {
        return all;
    }

    public static void setAll(HashMap<Integer, Activite> all) {
        Activite.all = all;
    }

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

    public List<Bouquet> getBouquets() {
        return bouquets;
    }

    public void setBouquets(ArrayList<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    public void setBouquets(List<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate_maj() {
        return date_maj;
    }

    public void setDate_maj(Date date_maj) {
        this.date_maj = date_maj;
    }


}