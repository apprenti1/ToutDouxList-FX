package controller.listeController;

import controller.tacheController.TacheController;
import model.todolist.Liste;
import model.todolist.Tache;
import model.utilisateur.Utilisateur;
import services.bdd.Bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListeController {
    public static ArrayList<Liste> getListes(int idUtilisateur) {
        PreparedStatement req = null;
        try {
            req = Bdd.getBdd().prepareStatement("SELECT nom,description,id_liste from liste where ref_utilisateur = ? ;");
            req.setInt(1, idUtilisateur);
            ResultSet rs = req.executeQuery();
            ArrayList<Liste> listes = new ArrayList<Liste>();
            while (rs.next()){
                listes.add(new Liste(rs.getInt("id_liste"), rs.getString("nom"), rs.getString("description"), TacheController.getTaches(rs.getInt("id_liste")), idUtilisateur));
            }
            return listes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Liste liste) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("UPDATE liste SET nom = ?, description = ? WHERE id_liste = ?");
            req.setString(1, liste.getNom());
            req.setString(2, liste.getDescription());
            req.setInt(3, liste.getId());
            req.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Liste uploadListe(Liste liste) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("insert into liste(nom, description, ref_utilisateur) values (?,?,?) ;");
            req.setString(1, liste.getNom());
            req.setString(2, liste.getDescription());
            req.setInt(3, liste.getRefUtilisateur());
            req.executeUpdate();
            req = Bdd.getBdd().prepareStatement("Select max(id_liste) from liste where ref_utilisateur = ?");
            req.setInt(1, liste.getRefUtilisateur());
            ResultSet res = req.executeQuery();
            res.next();
            liste.setId(res.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

    public static void deleteListe(Liste liste, Utilisateur utilisateur) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("DELETE FROM liste WHERE id_liste = ? ;");
            req.setInt(1, liste.getId());
            req.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
