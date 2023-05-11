package controller.utilisateurController;
import controller.listeController.ListeController;
import controller.typeController.TypeController;
import model.utilisateur.Utilisateur;
import services.bdd.Bdd;
import services.security.Security;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurController {

    public static Utilisateur connect(String email, String mdp) {
        PreparedStatement req = null;
        try {
            req = Bdd.getBdd().prepareStatement("select id_utilisateur, nom, prenom, email, mdp ,valid from utilisateur where email = ? and mdp = ? ;");
            req.setString(1, email);
            req.setString(2, Security.encrypt(mdp));
            ResultSet rep = req.executeQuery();
            if (rep.next()) {
                return new Utilisateur(rep.getInt(1), rep.getString(2), rep.getString(3), rep.getString(4), Security.decrypt(rep.getString(5)), ListeController.getListes(rep.getInt(1)), TypeController.getTypes(rep.getInt(1)), rep.getBoolean(6));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Utilisateur getByEmail(String email){
        try {
            PreparedStatement req = new Bdd().getBdd().prepareStatement("select id_utilisateur, nom, prenom, email, mdp, valid from utilisateur where email = ?;");
            req.setString(1, email);
            ResultSet rep = req.executeQuery();
            if(rep.next()){return new Utilisateur(rep.getInt(1), rep.getString(2), rep.getString(3), rep.getString(4), Security.decrypt(rep.getString(5)), ListeController.getListes(rep.getInt(1)), TypeController.getTypes(rep.getInt(1)), rep.getBoolean(6));}
            else {return null;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int countEmail(String email){
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("SELECT count(id_utilisateur) from utilisateur where email = ?;");
            req.setString(1, email);
            ResultSet rs = req.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean inscription(Utilisateur utilisateur) {
        try {
            if (countEmail(utilisateur.getEmail())==0) {
                PreparedStatement req = new Bdd().getBdd().prepareStatement("INSERT INTO utilisateur (nom, prenom, email, mdp) VALUES (?,?,?,?) ");
                req.setString(1, utilisateur.getNom());
                req.setString(2, utilisateur.getPrenom());
                req.setString(3, utilisateur.getEmail());
                req.setString(4, Security.encrypt(utilisateur.getMdp()));
                req.executeUpdate();
                return true;
            }else{return false;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void valid(Utilisateur utilisateur){
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("UPDATE utilisateur SET valid = 1 WHERE id_utilisateur = ? ");
            req.setInt(1, utilisateur.getId());
            req.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Utilisateur utilisateur) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, mdp = ? WHERE id_utilisateur = ?");
            req.setString(1, utilisateur.getNom());
            req.setString(2, utilisateur.getPrenom());
            req.setString(3, utilisateur.getEmail());
            req.setString(4, Security.encrypt(utilisateur.getMdp()));
            req.setInt(5, utilisateur.getId());
            req.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}