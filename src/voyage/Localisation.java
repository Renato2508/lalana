package voyage;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;

@DBTable(name = "localisation", autoIncrement = true)
public class Localisation {
    @DBField(name="idlocalisation", isPrimaryKey = true)
    Integer idLocalisation;

    @DBField(name="nomlocalisation")
    String nomLocalisation;


    public Localisation(Integer idLocalisation, String nomLocalisation) {
        this.idLocalisation = idLocalisation;
        this.nomLocalisation = nomLocalisation;
    }


    public Localisation(Integer idLocalisation) {
        this.idLocalisation = idLocalisation;
    }


    public Localisation(String nomLocalisation) {
        this.nomLocalisation = nomLocalisation;
    }


    public Localisation() {
    }


    public void save() throws Exception{
        try {
            GenericDAO.save(this);
        } catch (Exception e) {
            throw e;
        }
    }


    public Integer getIdLocalisation() {
        return idLocalisation;
    }


    public void setIdLocalisation(Integer idLocalisation) {
        this.idLocalisation = idLocalisation;
    }


    public String getNomLocalisation() {
        return nomLocalisation;
    }


    public void setNomLocalisation(String nomLocalisation) {
        this.nomLocalisation = nomLocalisation;
    }
}
