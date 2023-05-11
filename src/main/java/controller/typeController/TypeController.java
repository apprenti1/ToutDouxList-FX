package controller.typeController;

import model.todolist.Liste;
import model.todolist.Type;
import services.bdd.Bdd;
import services.bdd.Format;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeController {
    public static ArrayList<Type> getTypes(int idUtilisateur) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("SELECT id_type, libelle, code_couleur from type where ref_utilisateur = ? ");
            req.setInt(1, idUtilisateur);
            ResultSet rs = req.executeQuery();
            ArrayList<Type> types = new ArrayList<Type>();
            while (rs.next()){
                types.add(new Type(rs.getInt("id_type"), rs.getString("libelle"), rs.getString("code_couleur")));
            }
            return types;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Type getType(int id) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("SELECT id_type, libelle, code_couleur from type where id_type = ? ");
            req.setInt(1, id);
            ResultSet rs = req.executeQuery();
            rs.next();
            return new Type(rs.getInt("id_type"), rs.getString("libelle"), rs.getString("code_couleur"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Type type) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("UPDATE type SET libelle = ?, code_couleur = ? WHERE id_type = ?");
            req.setString(1, type.getLibelle());
            req.setString(2, type.getCode_couleur());
            req.setInt(3, type.getId());
            req.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Type uploadType(Type type) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("insert into type(libelle, code_couleur, ref_utilisateur) values (?,?,?) ;");
            req.setString(1, type.getLibelle());
            req.setString(2, type.getCode_couleur());
            req.setInt(3, type.getUtilisateur().getId());
            req.executeUpdate();
            req = Bdd.getBdd().prepareStatement("Select max(id_type) from type where ref_utilisateur = ?");
            req.setInt(1, type.getUtilisateur().getId());
            ResultSet res = req.executeQuery();
            res.next();
            type.setId(res.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return type;
    }

    public static void deleteType(Type type) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("DELETE FROM type WHERE id_type = ? ;");
            req.setInt(1, type.getId());
            req.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createType(String libelle, String code_couleur) throws SQLException {
        if (Format.verif(libelle) && Format.verif(code_couleur)){
            PreparedStatement requetePrepare = Bdd.getBdd().prepareStatement("INSERT INTO type (libelle, code_couleur) VALUES (?,?)");
            requetePrepare.setString(1, libelle);
            requetePrepare.setString(2, code_couleur);
            requetePrepare.executeUpdate();
        }
    }
}
