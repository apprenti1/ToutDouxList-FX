package controller.tacheController;

import controller.typeController.TypeController;
import model.todolist.Tache;
import services.bdd.Bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TacheController {
    public static ArrayList<Tache> getTaches(int idListe) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("Select * from tache where ref_liste = ?");
            req.setInt(1,idListe);
            ResultSet res = req.executeQuery();
            ArrayList<Tache> taches = new ArrayList<Tache>();
            while (res.next()){
                taches.add(new Tache(res.getInt("id_tache"), res.getString("nom"), res.getString("description"), res.getBoolean("est_realise"), TypeController.getType(res.getInt("ref_type"))));
            }
            return taches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void check(Tache tache) {
        PreparedStatement requetePrepare = null;
        try {
            requetePrepare = Bdd.getBdd().prepareStatement("UPDATE tache SET est_realise = ? WHERE id_tache = ?");
            requetePrepare.setBoolean(1, !tache.isEst_realise());
            requetePrepare.setInt(2, tache.getId());
            requetePrepare.executeUpdate();
            tache.setEst_realise(!tache.isEst_realise());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Tache tache) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("UPDATE tache SET nom = ?, description = ?, ref_type = ? WHERE id_tache = ?");
            req.setString(1, tache.getNom());
            req.setString(2, tache.getDescription());
            req.setInt(3, tache.getType().getId());
            req.setInt(4, tache.getId());
            req.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Tache uploadTache(Tache tache) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("insert into tache (nom, description, ref_type, ref_liste) values (?,?,?,?) ;");
            req.setString(1, tache.getNom());
            req.setString(2, tache.getDescription());
            req.setInt(3, tache.getType().getId());
            req.setInt(4, tache.getParentListe().getId());
            req.executeUpdate();
            req = Bdd.getBdd().prepareStatement("Select max(id_tache) from tache where ref_liste = ?");
            req.setInt(1, tache.getParentListe().getId());
            ResultSet res = req.executeQuery();
            res.next();
            tache.setId(res.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tache;
    }

    public static void deleteTache(Tache tache) {
        try {
            PreparedStatement req = Bdd.getBdd().prepareStatement("DELETE FROM tache WHERE id_tache = ? ;");
            req.setInt(1, tache.getId());
            req.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
