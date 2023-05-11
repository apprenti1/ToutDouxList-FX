package graphicController;

import application.Main;
import controller.listeController.ListeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.todolist.Liste;
import model.utilisateur.Utilisateur;
import services.bdd.Format;

public class EditListe {

    private final Utilisateur utilisateur;
    private final Liste liste;
    @FXML private TextField description;
    @FXML private Text erreur;
    @FXML private TextField titre;
    @FXML private Button modifier;



    public EditListe(Utilisateur utilisateur, Liste liste) {
        this.utilisateur = utilisateur;
        this.liste = liste;
    }
    public EditListe(Utilisateur utilisateur) {
        this(utilisateur, null);
    }
    public void initialize(){
        if (this.liste != null) {
            titre.setText(this.liste.getNom());
            description.setText(this.liste.getDescription());
        } else {
            this.modifier.setText("Créer");
        }
    }
    @FXML void modification(ActionEvent event) {
        if (Format.verif(new String[]{this.titre.getText(), this.description.getText()})) {
            if (this.liste != null) {
                this.liste.setNom(this.titre.getText());
                this.liste.setDescription(this.description.getText());
                ListeController.update(this.liste);
                Main.changeScene("ViewListe", new ViewListe(this.liste, this.utilisateur), "Vôtre liste est avancée :)");
            } else {
                Liste liste = ListeController.uploadListe(new Liste(this.utilisateur.getId(), this.titre.getText(), this.description.getText()));
                this.utilisateur.getListes().add(liste);
                Main.changeScene("ViewListe", new ViewListe(liste, this.utilisateur), "Vôtre liste est avancée :)");
            }
        } else {
            this.erreur.setText("Erreur, les charactères suivants sont interdits: \n '\"' | \"'\" | ' ' | '(' | ')'");
        }
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {Main.changeScene("MineTypes", new MineTypes(this.utilisateur), "Modifiez vos différents types de listes ;)");}
}
