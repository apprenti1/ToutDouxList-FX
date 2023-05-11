package graphicController;

import application.Main;
import controller.listeController.ListeController;
import controller.typeController.TypeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.todolist.Liste;
import model.todolist.Type;
import model.utilisateur.Utilisateur;
import services.bdd.Format;

public class EditType {
    private final Utilisateur utilisateur;
    private final Type type;
    @FXML private ColorPicker color;
    @FXML private Text erreur;
    @FXML private TextField titre;
    @FXML private Button modifier;



    public EditType(Utilisateur utilisateur, Type type) {
        this.utilisateur = utilisateur;
        this.type = type;
    }
    public EditType(Utilisateur utilisateur) {
        this(utilisateur, null);
    }
    public void initialize(){
        if (this.type != null) {
            titre.setText(this.type.getLibelle());
            color.setValue(Color.web("#"+this.type.getCode_couleur()));
        } else {
            this.modifier.setText("Créer");
        }
    }
    @FXML void modification(ActionEvent event) {
        if (Format.verif(new String[]{this.titre.getText(), this.color.getValue().toString().substring(2)})) {
            System.out.println(this.color.getValue().toString().substring(2));
            if (this.type != null) {
                this.type.setLibelle(this.titre.getText());
                this.type.setCode_couleur(this.color.getValue().toString().substring(2));
                TypeController.update(this.type);
                Main.changeScene("MineTypes", new MineTypes(this.utilisateur), "Modifiez vos différents types de listes ;)");
            } else {
                Type type = TypeController.uploadType(new Type(this.utilisateur.getId(), this.titre.getText(), this.color.getValue().toString().substring(2), this.utilisateur));
                this.utilisateur.getTypes().add(type);
                Main.changeScene("MineTypes", new MineTypes(this.utilisateur), "Modifiez vos différents types de listes ;)");
            }
        } else {
            this.erreur.setText("Erreur, les charactères suivants sont interdits: \n '\"' | \"'\" | ' ' | '(' | ')'");
        }
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {}
}
