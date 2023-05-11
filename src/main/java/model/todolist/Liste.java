package model.todolist;

import services.bdd.Bdd;
import services.bdd.Format;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Liste {




    private int id;
    private String nom;
    private String description;
    private ArrayList<Tache> taches;



    private int refUtilisateur;



    public Liste(int id, String nom, String description, ArrayList<Tache> taches, int refUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.taches = taches;
        this.refUtilisateur = refUtilisateur;
    }
    public Liste(int ref_utilisateur, String nom, String description){
        this(0, nom, description, new ArrayList<Tache>(), ref_utilisateur);
    }
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public void setId(int id) {this.id = id;}
    public ArrayList<Tache> getTaches() {return taches;}
    public int getId() {return id;}
    public int getRefUtilisateur() {return refUtilisateur;}
}


