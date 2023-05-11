package graphicController;

import application.Main;
import controller.utilisateurController.UtilisateurController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.utilisateur.Utilisateur;
import services.bdd.Format;

import java.util.ArrayList;

public class Inscription {

    @FXML private TextField conf;
    @FXML private TextField email;
    @FXML private Text erreur;
    @FXML private TextField mdp;
    @FXML private TextField nom;
    @FXML private TextField prenom;



    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void inscription(ActionEvent event) {
        System.out.println(Format.verif(new String[]{this.nom.getText(), this.prenom.getText(), this.email.getText(), this.mdp.getText()}) && this.mdp.getText().equals(this.conf.getText()));
        if (Format.verif(new String[]{this.nom.getText(), this.prenom.getText(), this.email.getText(), this.mdp.getText()}) && this.mdp.getText().equals(this.conf.getText())){
            if ((UtilisateurController.inscription(new Utilisateur(this.nom.getText(), this.prenom.getText(), this.email.getText(), this.mdp.getText())))) {
                Main.changeScene("Connexion", new Connexion(true), "Connectez vous ;)");
            }
            else {erreur.setText("Erreur, un compte existe déjà avec cet email !!");}
        }else{erreur.setText((mdp.getText().equals(conf.getText())?"Erreur, les charactères suivants sont interdits: \n '\"' | \"'\" | ' ' | '(' | ')'":"Erreur, le mot de passe et la confirmation ne sont pas identiques"));}
            erreur.setVisible(true);

    }
}
