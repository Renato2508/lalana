package voyage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import generalisation.genericDAO.GenericDAO;


public class Composition {
    Bouquet bouquet;
    Activite activite;
    Localisation localisation;
    Duree duree;
    int frequence;
    double prix;


    public Composition(int idbouquet, int idactivite, int idlocalisation, int idduree, int frequence) {
        this.setBouquet(new Bouquet(idbouquet));
        this.setActivite(new Activite(idactivite));
        this.setLocalisation(new Localisation(idlocalisation));
        this.setDuree(new Duree(idduree));
        this.frequence = frequence;
    }


    
    public Composition(Bouquet bouquet, Activite activite, Localisation localisation, Duree duree, int frequence) {
        this.bouquet = bouquet;
        this.activite = activite;
        this.localisation = localisation;
        this.duree = duree;
        this.frequence = frequence;
    }


    public Composition() {
    }

    public static List<Composition> getByFourchette(double prix1, double prix2, Connection c) throws Exception {
        List<Composition> compositions = new ArrayList<>();
        String query = "SELECT * FROM v_bouquet_prix WHERE prix BETWEEN ? AND ? order by prix asc";
        
        // Préparation de la requête SQL paramétrée
        try (PreparedStatement statement = c.prepareStatement(query)) {
            statement.setDouble(1, prix1);
            statement.setDouble(2, prix2);
            
            // Exécution de la requête et récupération des résultats
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Composition composition = new Composition();
                    
                    // Alimentation de la classe Composition à partir du ResultSet en utilisant les setters
                    composition.setPrix(resultSet.getDouble("prix"));
                    
                    // Exemple pour d'autres attributs, à adapter selon les types et noms de colonnes de votre ResultSet
                    Bouquet bouquet = new Bouquet();
                    bouquet.setIdBouquet(resultSet.getInt("idbouquet"));
                    bouquet.setNomBouquet(resultSet.getString("nombouquet"));
                    composition.setBouquet(bouquet);
                    
                    Localisation localisation = new Localisation();
                    localisation.setIdLocalisation(resultSet.getInt("idlocalisation"));
                    localisation.setNomLocalisation(resultSet.getString("nomlocalisation"));
                    composition.setLocalisation(localisation);
                    
                    Duree duree = new Duree();
                    duree.setIdDuree(resultSet.getInt("idduree"));
                    duree.setNomDuree(resultSet.getString("nomduree"));
                    composition.setDuree(duree);
                    
                    compositions.add(composition);
                }
            } catch (Exception e) {
                // Gérer l'exception liée à la récupération des résultats
                e.printStackTrace();
            }
        } catch (Exception e) {
            // Gérer l'exception liée à la préparation de la requête
            e.printStackTrace();
        }
        
        return compositions;
    }

    public static List<Composition> getListByAct(String id, Connection connection) throws Exception {
        int idActivite = Integer.valueOf(id);
        List<Composition> compositions = new ArrayList<>();
        String query = "SELECT * FROM v_composition WHERE idactivite = ?";
        
        // Préparation de la requête SQL paramétrée
        try (
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idActivite); // Paramètre pour l'id de l'activité
            
            // Exécution de la requête et récupération des résultats
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Composition composition = new Composition();
                
                // Alimentation de la classe Composition à partir du ResultSet enutilisant les setters
                composition.setFrequence(resultSet.getInt("frequence"));
                
                // Exemple pour d'autres attributs, à adapter selon les types et noms decolonnes de votre ResultSet
                Bouquet bouquet = new Bouquet();
                bouquet.setIdBouquet(resultSet.getInt("idbouquet"));
                bouquet.setNomBouquet(resultSet.getString("nombouquet"));
                composition.setBouquet(bouquet);
                
                Activite activite = new Activite();
                activite.setIdActivite(resultSet.getInt("idactivite"));
                activite.setNomActivite(resultSet.getString("nomactivite"));
                composition.setActivite(activite);
                
                Localisation localisation = new Localisation();
                localisation.setIdLocalisation(resultSet.getInt("idLocalisation"));
                localisation.setNomLocalisation(resultSet.getString("nomlocalisation"));
                composition.setLocalisation(localisation);
                
                Duree duree = new Duree();
                duree.setIdDuree(resultSet.getInt("idduree"));
                duree.setNomDuree(resultSet.getString("nomduree"));
                duree.setDureeMin(resultSet.getInt("dureeMin"));
                duree.setDureeMax(resultSet.getInt("dureeMax"));
                composition.setDuree(duree);
                    
                    compositions.add(composition);
                
            }
        }
        catch(Exception e){
            throw e;

        }
        
        return compositions;
    }

    public void save() throws Exception {
        Connection c = GenericDAO.getConnection();
        String query = "INSERT INTO composition (idbouquet, idactivite, idLocalisation, idduree, frequence) VALUES (?, ?, ?, ?, ?)";
        
        // Préparation de la requête SQL paramétrée
        try (PreparedStatement statement = c.prepareStatement(query)) {
            // Récupération des attributs pour l'insertion
            int idBouquet = bouquet.getIdBouquet();
            int idActivite = activite.getIdActivite();
            int idLocalisation = localisation.getIdLocalisation();
            int idDuree = duree.getIdDuree();
            
            // Attribution des valeurs aux paramètres de la requête
            statement.setInt(1, idBouquet);
            statement.setInt(2, idActivite);
            statement.setInt(3, idLocalisation);
            statement.setInt(4, idDuree);
            statement.setInt(5, frequence);
            
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

    public double getPrix() {
        return prix;
    }



    public void setPrix(double prix) {
        this.prix = prix;
    }

    
    public Bouquet getBouquet() {
        return bouquet;
    }
    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }
    public Activite getActivite() {
        return activite;
    }
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    public Localisation getLocalisation() {
        return localisation;
    }
    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }
    public Duree getDuree() {
        return duree;
    }
    public void setDuree(Duree duree) {
        this.duree = duree;
    }
    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

}
