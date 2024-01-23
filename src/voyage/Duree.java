package voyage;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;

@DBTable(name = "duree", autoIncrement = true)
public class Duree{
    @DBField(name="idDuree", isPrimaryKey = true)
    Integer idDuree;

    @DBField(name="nomDuree")
    String nomDuree;

    @DBField(name="dureeMin")
    Integer dureeMin;

    @DBField(name="dureeMax")
    Integer dureeMax;   


    public Duree(Integer idDuree, String nomDuree) {
        this.idDuree = idDuree;
        this.nomDuree = nomDuree;
    }

    public Duree(int idduree){
        this.idDuree = idduree;
    }

    public Duree(String nomDuree, Integer dureeMin, Integer dureeMax) {
        this.nomDuree = nomDuree;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
    }


    public Duree() {
    }


    public void save() throws Exception{
        try {
            GenericDAO.save(this);
        } catch (Exception e) {
            throw e;
        }
    }


    public Integer getIdDuree() {
        return idDuree;
    }


    public void setIdDuree(Integer idDuree) {
        this.idDuree = idDuree;
    }


    public String getNomDuree() {
        return nomDuree;
    }


    public void setNomDuree(String nomDuree) {
        this.nomDuree = nomDuree;
    }


    public Integer getDureeMin() {
        return dureeMin;
    }


    public void setDureeMin(Integer dureeMin) {
        this.dureeMin = dureeMin;
    }


    public Integer getDureeMax() {
        return dureeMax;
    }


    public void setDureeMax(Integer dureeMax) {
        this.dureeMax = dureeMax;
    }

}    

