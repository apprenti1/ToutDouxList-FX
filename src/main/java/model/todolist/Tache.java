package model.todolist;
import javafx.scene.control.CheckBox;
import services.bdd.Bdd;
import services.bdd.Format;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tache {


    private int id;
    private String nom;
    private String description;
    private boolean est_realise;
    private Type type;
    private Liste parentListe;


    public Tache(int id, String nom, String description, boolean est_realise, Type type, Liste liste) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.est_realise = est_realise;
        this.type = type;
        this.parentListe = liste;
    }
    public Tache(int id, String nom, String description, boolean est_realise, Type type){
        this(id, nom, description, est_realise, type, null);
    }
    public Tache(String nom, String description, Type type, Liste liste){
        this(0, nom, description, false, type, liste);
    }

    public String getNom() {return nom;}
    public String getDescription() {return description;}
    public boolean isEst_realise() {return est_realise;}
    public Type getType() {return type;}
    public CheckBox getCheckbox_est_realise() {
        CheckBox check = new CheckBox();
        check.setSelected(this.est_realise);
        check.setText("");
        check.setDisable(true);
        check.setOpacity(1);
        return check;
    }

    public int getId() {return id;}
    public void setNom(String nom) {this.nom = nom;}
    public void setDescription(String description) {this.description = description;}
    public void setEst_realise(boolean est_realise) {this.est_realise = est_realise;}
    public void setType(Type type) {this.type = type;}
    public Liste getParentListe() {return parentListe;}
    public void setParentListe(Liste parentListe) {this.parentListe = parentListe;}
    public void setId(int id) {this.id = id;}


}
