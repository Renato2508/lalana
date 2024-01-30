package voyage.sprint6;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;

import java.sql.Date;
import java.util.List;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@DBTable(name = "employe", autoIncrement = true)
public class Employe {
    @DBField(name="idemploye", isPrimaryKey = true)
    Integer idemploye;

    @DBField(name="nomprofil")
    String nom;
    
    @DBField(name="dateembauche")
    Date dateEmbauche;
    
    @DBField(name="experiencemax")
    Integer idProfilEmbauche;

    Profil profil;


    

    public Employe(String nom, String dateEmbauche, String idProfilEmbauche) {
        this.nom = nom;
        this.dateEmbauche = Date.valueOf(dateEmbauche);
        this.idProfilEmbauche = Integer.valueOf(idProfilEmbauche);
    }

    public Employe() {
    }

    public static List<Employe> getWithActualProfiles() throws Exception{
        String req = "select * from v_taux_horaire_actuel_final ";
        Connection c;
        ArrayList<Employe> res = new ArrayList<Employe>();

        try {
            c = GenericDAO.getConnection();
             // Preparation de la requête SQL paramétrée
            PreparedStatement statement = c.prepareStatement(req);            
            // Exécution de la requête et récupération des résultats
            ResultSet resultSet = statement.executeQuery();

            Employe e;
            Profil p;
            while(resultSet.next()){
                e = new Employe();
                p = new Profil();

                e.setNom(resultSet.getString("nom"));
                e.setDateEmbauche(resultSet.getDate("dateembauche"));
                p.setNom(resultSet.getString("nomprofil"));
                p.setTauxHoraire(resultSet.getDouble("tauxHoraire"));
                e.setProfil(p);
                res.add(e);
            

            }
            return res;
        } catch (Exception e) {
            throw e;
        }
    }


    public void save() throws Exception{
        try {
            GenericDAO.save(this);
        } catch (Exception e) {
            throw e;
        }
    }

    public Integer getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(Integer idemploye) {
        this.idemploye = idemploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Integer getIdProfilEmbauche() {
        return idProfilEmbauche;
    }

    public void setIdProfilEmbauche(Integer idProfilEmbauche) {
        this.idProfilEmbauche = idProfilEmbauche;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
    

}

