package voyage;

import java.util.Vector;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;

@DBTable(name = "bouquet", autoIncrement = true)
public class Bouquet {
    @DBField(name="idbouquet", isPrimaryKey = true)
    Integer idBouquet;

    @DBField(name="nombouquet")
    String nomBouquet;

    Vector<Activite> activites = new Vector<Activite>();


    
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
    public Vector<Activite> getActivites() {
        return activites;
    }
    public void setActivites(Vector<Activite> activites) {
        this.activites = activites;
    }

    
}
