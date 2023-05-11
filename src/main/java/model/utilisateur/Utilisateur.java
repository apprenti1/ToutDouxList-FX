package model.utilisateur;

import model.todolist.Liste;
import model.todolist.Type;

import java.util.ArrayList;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private ArrayList<Liste> listes;
    private ArrayList<Type> types;
    private boolean valid;

    public Utilisateur(int id, String nom, String prenom, String email, String mdp, ArrayList<Liste> listes, ArrayList<Type> types, boolean valid) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.listes = listes;
        this.types = types;
        this.valid = valid;
        if (types!=null) {
            for (Type type : this.types) {
                type.setUtilisateur(this);
            }
        }
    }
    public Utilisateur(String nom, String prenom, String email, String mdp){
        this(0, nom, prenom, email, mdp, null, null, false);
    }


    public int getId() {return id;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public String getMdp() {return mdp;}
    public ArrayList<Liste> getListes() {return listes;}
    public ArrayList<Type> getTypes() {return types;}
    public void setMdp(String mdp) {this.mdp = mdp;}
    public boolean isValid() {return valid;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail(String email) {this.email = email;}
    public void setNom(String nom) {this.nom = nom;}
    public void setInfos(String nom, String prenom, String email, String mdp){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setMdp(mdp);
    }
}

