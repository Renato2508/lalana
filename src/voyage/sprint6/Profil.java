package voyage.sprint6;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;
import java.util.List;

@DBTable(name = "profil", autoIncrement = true)
public class Profil {
    @DBField(name="idprofil", isPrimaryKey = true)
    Integer idprofil;

    @DBField(name="nomprofil")
    String nom;
    
    @DBField(name="expmin")
    Double expericenceMin;
    
    @DBField(name="expmax")
    Double experienceMax;

    
    @DBField(name="tauxaug")
    Double tauxAugmente;
    
    @DBField(name="tipe")
    String type;
    
    @DBField(name="tauxhor")
    Double tauxHoraire;


    

    public Profil() {
    }



    public Profil(String nom, String expericenceMin, String experienceMax, String tauxAugmente, String type, String tauxHoraire) {
        try {
            this.tauxHoraire = Double.valueOf(tauxHoraire);
            this.type = type;
            this.tauxAugmente = Double.valueOf(tauxAugmente);
            this.experienceMax = Double.valueOf(experienceMax);
            this.expericenceMin = Double.valueOf(expericenceMin);
            this.nom = nom;


        } catch (Exception e1) {
           
        }

        try {
                    this.type = type;

        } catch (Exception e2) {
        }

        try {
                    this.tauxAugmente = Double.valueOf(tauxAugmente);

        } catch (Exception e3) {
        }

        try {
                    this.experienceMax = Double.valueOf(experienceMax);

        } catch (Exception e4) {
        }

        try {
                    this.expericenceMin = Double.valueOf(expericenceMin);

        } catch (Exception e5) {
        }


        try {
                    this.nom = nom;

        } catch (Exception e6) {
        }
    }



    public void save() throws Exception{
        try {
            GenericDAO.save(this);
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<Profil> getAll()throws Exception{
        List<Profil> lp = GenericDAO.getAll(Profil.class);
        System.out.println("-------> Profils: "+lp);
        return lp;
    }

    public Integer getIdprofil() {
        return idprofil;
    }

    public void setIdprofil(Integer idprofil) {
        this.idprofil = idprofil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getExpericenceMin() {
        return expericenceMin;
    }

    public void setExpericenceMin(Double expericenceMin) {
        this.expericenceMin = expericenceMin;
    }

    public Double getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(Double experienceMax) {
        this.experienceMax = experienceMax;
    }

    public Double getTauxAugmente() {
        return tauxAugmente;
    }

    public void setTauxAugmente(Double tauxAugmente) {
        this.tauxAugmente = tauxAugmente;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(Double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }






}
