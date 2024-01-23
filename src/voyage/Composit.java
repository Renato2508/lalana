package voyage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import generalisation.genericDAO.GenericDAO;
import voyage.exception.LackException;

public class Composit {
    Activite activite;
    int frequence;

    
    public Composit(Activite activite, int frequence) {
        this.activite = activite;
        this.frequence = frequence;
    }

    public void checkStock(int nbpers)throws LackException, Exception{
        try {
            
            int stock = this.getStock();
            int delta = (stock - nbpers*frequence);
            if(delta < 0){
                throw new LackException(new Composit(this.activite, delta));
            }
            
        } catch (Exception e) {
            throw e;
        }
    }

    public int getStock()throws Exception{
        int res = -1;
        String query = "select sum(qte_mvt) as stock from mouvement where idactivite = ?";
        
        // Préparation de la requête SQL paramétrée
        try {
            Connection c = GenericDAO.getConnection();
            PreparedStatement statement = c.prepareStatement(query);
            // Récupération des attributs pour l'insertion

            // Attribution des valeurs aux paramètres de la requête
            statement.setInt(1, this.activite.getIdActivite());
            
            // Exécution de la requête d'insertion
            ResultSet rst =statement.executeQuery();
            while(rst.next())
                res = rst.getInt("stock");
            return res;
        }
        catch(Exception e){
            throw e;
        }
        
    }
    
    public void reserver(int nbpers, Date d, Connection c)throws LackException, Exception{
        try {
            // check des reéserve sur cette activite          
            this.checkStock(nbpers);
            this.activite.reserver(nbpers*frequence, d, c);
        } 
        catch(LackException le){
            throw le;
        }
        catch (Exception e) {
           throw e;
        }
    }

    public Activite getActivite() {
        return activite;
    }
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
}
