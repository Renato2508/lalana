package voyage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import generalisation.genericDAO.GenericDAO;
import voyage.exception.LackException;
import voyage.exception.LackExceptions;

public class Voyage {
    Bouquet bouquet;
    Localisation localisation;
    Duree duree;
    ArrayList<Composit> composition = new ArrayList<Composit>();
    static HashMap<String, Voyage> all_voyages;



    public Voyage(){}
    public Voyage(Bouquet bouquet, Localisation localisation, Duree duree) {
        this.bouquet = bouquet;
        this.localisation = localisation;
        this.duree = duree;
    }

    public void reserver(int nb_pers, Date d, String id_client)throws LackExceptions, Exception{
        Connection c = null;
        try {


            try {
            c = GenericDAO.getConnection();
            } catch (Exception e) {
                throw e;
            }

            ArrayList<Composit> compos = new ArrayList<Composit> ();
            for(Composit compo: this.composition){
                try {
                    compo.reserver(nb_pers, d, c);
                } catch (LackException e) {
                    compos.add(e.getCompo());
                }
            }
            if(compos.isEmpty() == false){
                try{
                    c.rollback();
                }catch(Exception e){
                    throw e;
                }

                throw new LackExceptions(compos);

            }else{
                String query = "INSERT INTO reservation(date_reservation,idbouquet, idduree, idLocalisation, qte_reservee, idclient) VALUES (?, ?, ?, ?,?,?)";
            
                // Préparation de la requête SQL paramétrée
                try (PreparedStatement statement = c.prepareStatement(query)) {
                    // Récupération des attributs pour l'insertion
                    int idBouquet = bouquet.getIdBouquet();
                    int idLocalisation = localisation.getIdLocalisation();
                    int idDuree = duree.getIdDuree();

                    // Attribution des valeurs aux paramètres de la requête
                    statement.setDate(1, d);
                    statement.setInt(2, idBouquet);
                    statement.setInt(3, idDuree);
                    statement.setInt(4, idLocalisation);
                    statement.setInt(5, nb_pers);
                    statement.setInt(6,Integer.valueOf(id_client));

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
    
            }
        }catch (Exception e) {
            throw e;
        }
        finally{
            c.close();
        }
        
    }

    public static HashMap<String, Voyage> getAll_voyage(Connection c) throws Exception{
        // transaction interne
        try {
            boolean transact = false;
            if(c == null){
            transact = true;
            c = GenericDAO.getConnection();
            }

        HashMap<String, Voyage> res = new HashMap<String, Voyage>();   
        // exécuter la requête 
                String query = "SELECT * FROM v_composition";
        
        // Preparation de la requête SQL paramétrée
        PreparedStatement statement = c.prepareStatement(query);            
        // Exécution de la requête et récupération des résultats
        ResultSet resultSet = statement.executeQuery();
        

        Voyage v = null; // le voyqge qui sera ajouté à chaque fois au hashmap
        int idbouquet = -1;
        int idlocalisation = -1;
        int idduree=-1;

        int idbouquet_c;
        int idlocalisation_c;
        int idduree_c;

        Bouquet bouquet;
        Localisation localisation;
        Duree duree;
        Activite activite;
        Composit compo;

        // parcourir le resultset

        while (resultSet.next()) {
            idbouquet_c = resultSet.getInt("idbouquet");
            idlocalisation_c = resultSet.getInt("idlocalisation");
            idduree_c=resultSet.getInt("idduree");

            // a chaque changement de la combinaison idbouquet, idduree, idlocalisation,
            if(idbouquet != idbouquet_c || idlocalisation != idlocalisation_c || idduree!= idduree_c){

                idbouquet = idbouquet_c ; idlocalisation = idlocalisation_c ; idduree= idduree_c;
                bouquet = new Bouquet(idbouquet, resultSet.getString("nombouquet"));
                localisation = new Localisation(idlocalisation, resultSet.getString("nomlocalisation"));
                duree = new Duree(idduree, resultSet.getString("nomduree"));
                v = new Voyage(bouquet, localisation, duree);
                res.put(String.format("%s%s%s", idbouquet, idlocalisation, idduree),v);
                System.out.println("AJOUT D'UN VOY :   "+res);
            }

            activite = new Activite(resultSet.getInt("idactivite"),resultSet.getString("nomactivite"));
            compo = new Composit(activite, resultSet.getInt("frequence")); 
            System.out.println("COMPO_C:    "+compo);     
            v.getComposition().add(compo);
            //System.out.println("Liste dee la composition deu voyage: "+this.composition);


            }
            return res;

        } catch (Exception e) {
            throw e;
        }       
        
    }
    public static void setAll_voyage(HashMap<String, Voyage> all_voyage) {
        Voyage.all_voyages = all_voyage;
    }
    public Bouquet getBouquet() {
        return bouquet;
    }
    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
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
    public ArrayList<Composit> getComposition() {
        return composition;
    }
    public void setComposition(ArrayList<Composit> composition) {
        this.composition = composition;
    }
    public static HashMap<String, Voyage> getAll_voyages() {
        return all_voyages;
    }
    public static void setAll_voyages(HashMap<String, Voyage> all_voyages) {
        Voyage.all_voyages = all_voyages;
    }

}
