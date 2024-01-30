package voyage.sprint7;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import generalisation.genericDAO.GenericDAO;

@DBTable(name = "client", autoIncrement = true)
public class Client {

    @DBField(name="idclient", isPrimaryKey = true)
    Integer idClient;
    @DBField(name="nomclient")
    String nom;
    @DBField(name="sexe")
    String sexe;

    
    public Client(String nom, String sexe) {
        this.nom = nom;
        this.sexe = sexe;
    }


    public Client() {
    }


    public void save() throws Exception{
        try {
            GenericDAO.save(this);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Integer getIdClient() {
        return idClient;
    }
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


}
