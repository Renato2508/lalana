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

    @DBField(name="tipe")
    String tipe;

    @DBField(name="volHorRef")
    Double volHorRef;

    @DBField(name="tauxAugmente")
    Double augmentation;

    public Duree(String nomDuree, String  dureeMin, String  dureeMax, String tipe, String volHorRef,
            String augmentation) {
        this.nomDuree = nomDuree;
         this.dureeMax = Integer.valueOf(dureeMax);
        this.dureeMin = Integer.valueOf(dureeMin);    
        this.tipe = tipe;


        try {
            this.volHorRef = Double.valueOf(volHorRef);

        } catch (Exception e1) {
             this.volHorRef = null;
        }

        
        try {
             this.augmentation = Double.valueOf(augmentation);
        } catch (Exception e2) {
             this.augmentation  = null;  
        }
    }

    public Duree(String nomDuree, Integer dureeMin, Integer dureeMax, String tipe, Double volHorRef,
            Double augmentation) {
        this.nomDuree = nomDuree;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
        this.tipe = tipe;
        this.volHorRef = volHorRef;
        this.augmentation = augmentation;
    }

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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Double getVolHorRef() {
        return volHorRef;
    }

    public void setVolHorRef(Double volHorRef) {
        this.volHorRef = volHorRef;
    }

    public Double getAugmentation() {
        return augmentation;
    }

    public void setAugmentation(Double augmentation) {
        this.augmentation = augmentation;
    }

}    

