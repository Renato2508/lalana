package voyage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import generalisation.genericDAO.GenericDAO;
import voyage.exception.LackException;
import voyage.exception.LackExceptions;

public class Voyage {
    Bouquet bouquet;
    Localisation localisation;
    Duree duree;
    double revient;
    double vente;
    double benefice;




    public double getRevient() {
        return revient;
    }
    public void setRevient(double revient) {
        this.revient = revient;
    }
    ArrayList<Composit> composition = new ArrayList<Composit>();
    static HashMap<String, Voyage> all_voyages;



    public Voyage(){}
    public Voyage(Bouquet bouquet, Localisation localisation, Duree duree) {
        this.bouquet = bouquet;
        this.localisation = localisation;
        this.duree = duree;
    }

    public static  List<Voyage>  get_all_with_benefice_bet(String min, String max) throws Exception{
        String sql = "select * from v_benefice_all where benefice >= ? and benefice <=  ?";
        ArrayList<Voyage> res = new ArrayList<Voyage>();
        Connection c = null;
        PreparedStatement pst = null;
        try {
             c = GenericDAO.getConnection();
            pst = c.prepareStatement(sql);
            pst.setDouble(1, Double.valueOf(min));
            pst.setDouble(2, Double.valueOf(max));

            ResultSet rst = pst.executeQuery();

            Bouquet bouquet;
            Localisation localisation;
            Duree duree;
            Voyage v; 
            while(rst.next()){

                bouquet = new Bouquet(rst.getInt("idbouquet") , rst.getString("nombouquet"));
                localisation = new Localisation(rst.getInt("idlocalisation"), rst.getString("nomlocalisation"));
                duree = new Duree(rst.getInt("idduree"), rst.getString("nomduree"));
                v = new Voyage(bouquet, localisation, duree);
                v.setRevient(rst.getDouble("revient"));
                v.setPrix_vente(rst.getDouble("prix_vente"));
                v.setBenefice(rst.getDouble("benefice"));
                res.add(v);

            } 
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            try {
                pst.close();
                c.close();
            } catch (Exception e2) {
                throw e2;
            }

        }

        return res;

    }
    public static void setPrixVente(String idvoyage, String vente)throws Exception{
                 Connection c = null;
                PreparedStatement pst = null;
                PreparedStatement pst1 = null;
                String[] id = Voyage.restoreBDL(idvoyage);
                System.out.println(String.format("Liste: %s %s %s",id[0], id[1], id[2]));
                
                String query = "INSERT INTO voyageprix(idbouquet, idduree, idLocalisation, prix_vente) VALUES (?, ?, ?, ?)";

                String query1 = "update voyageprix prix set prix_vente = ? where idbouquet = ? and idduree = ? and idlocalisation = ?";
            
                // Préparation de la requête SQL paramétrée
                try {
                    c = GenericDAO.getConnection();
                    pst1 = c.prepareStatement(query1);
                    pst = c.prepareStatement(query);
                    
                    int idBouquet = Integer.valueOf(id[0]);
                    int idLocalisation = Integer.valueOf(id[2]);
                    int idDuree =Integer.valueOf(id[1]);

                    // Attribution des valeurs aux paramètres de la requête
                    pst1.setInt(1, idBouquet);
                    pst1.setInt(2, idDuree);
                    pst1.setInt(3, idLocalisation);
                    pst1.setDouble(4,Double.valueOf(vente));

                    
                    // Exécution de la requête d'insertion
                    int insert = pst1.executeUpdate();
                    if(insert == 0){
                        pst = c.prepareStatement(query);
                        pst.setDouble(4,Double.valueOf(vente) );
                        pst.setInt(1, idBouquet);
                        pst.setInt(2, idDuree);
                        pst.setInt(3, idLocalisation);
                    
                    // Exécution de la requête d'insertion
                        pst.executeUpdate();

                    }
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
                try {
                    pst1.close();
                    pst.close();
                    c.close();
                } catch (Exception e2) {
                    throw e2;
                }

        }
    }

    public String getBDLId(){
        String res = null;
        res= String.format("%s#%s#%s", this.bouquet.getIdBouquet(), this.duree.getIdDuree(), this.localisation.getIdLocalisation());
        return res;
    }

    public static String[] restoreBDL(String id){
        return id.split( "#");
    }

    public String getBDLNom(){
        String res = null;
        res= String.format("Voyage %s %s %s", this.bouquet.getNomBouquet(), this.duree.getNomDuree(), this.localisation.getNomLocalisation());
        return res;
    }


    public static List<Voyage> get_all_with_revient()throws Exception{
        String sql = "select * from v_revient_all";
        ArrayList<Voyage> res = new ArrayList<Voyage>();
        Connection c = null;
        PreparedStatement pst = null;
        try {
             c = GenericDAO.getConnection();
            pst = c.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();

            Bouquet bouquet;
            Localisation localisation;
            Duree duree;
            double revient;
            Voyage v; 
            while(rst.next()){

                bouquet = new Bouquet(rst.getInt("idbouquet") , rst.getString("nombouquet"));
                localisation = new Localisation(rst.getInt("idlocalisation"), rst.getString("nomlocalisation"));
                duree = new Duree(rst.getInt("idduree"), rst.getString("nomduree"));
                v = new Voyage(bouquet, localisation, duree);
                v.setRevient(rst.getDouble("revient"));
                res.add(v);

            } 
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            try {
                pst.close();
                c.close();
            } catch (Exception e2) {
                throw e2;
            }

        }

        return res;
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
    
public double getPrix_vente() {
    return vente;
}
public void setPrix_vente(double prix_vente) {
    this.vente = prix_vente;
}
public double getBenefice() {
    return benefice;
}
public void setBenefice(double benefice) {
    this.benefice = benefice;
}


}
