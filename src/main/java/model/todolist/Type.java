package model.todolist;

import javafx.scene.layout.Pane;
import model.utilisateur.Utilisateur;
import services.bdd.Bdd;
import services.bdd.Format;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Type {

    private int id;
    private String libelle;
    private String code_couleur;
    private Utilisateur utilisateur;

    public Type(int id, String libelle, String code_couleur, Utilisateur utilisateur) {
        this.id = id;
        this.libelle = libelle;
        this.code_couleur = code_couleur;
        this.utilisateur = utilisateur;
    }
    public Type(int id, String libelle, String code_couleur) {
        this(id, libelle, code_couleur, null);
    }
    public Type(String libelle, String code_couleur) {
        this.libelle = libelle;
        this.code_couleur = code_couleur;
    }





    public void updateType() throws SQLException {
        if (Format.verif(this.libelle) && Format.verif(this.code_couleur)){
            PreparedStatement requetePrepare = Bdd.getBdd().prepareStatement("UPDATE type SET libelle = ?, code_couleur = ? WHERE id_type = ?");
            requetePrepare.setString(1, this.libelle);
            requetePrepare.setString(2, this.code_couleur);
            requetePrepare.setInt(3, this.id);
            requetePrepare.executeUpdate();
        }
    }

    public void deleteType() throws SQLException {
        PreparedStatement requetePrepare = Bdd.getBdd().prepareStatement("DELETE FROM type WHERE id_type = ?");
        requetePrepare.setInt(1, this.id);
        requetePrepare.executeUpdate();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getCode_couleur() { return code_couleur; }
    public void setCode_couleur(String code_couleur) { this.code_couleur = code_couleur; }
    public Utilisateur getUtilisateur() {return utilisateur;}
    public void setUtilisateur(Utilisateur utilisateur) {this.utilisateur = utilisateur;}
    public Pane getColorPane() {
        Pane color = new Pane();
        color.setMaxSize(20, 20);;
        color.setStyle("-fx-border-color: #"+this.getCode_couleur()+"; -fx-border-radius: 50%; -fx-border-width: 10px");
        return color;
    }
}
