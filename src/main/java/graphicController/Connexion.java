package graphicController;

import application.Main;
import controller.utilisateurController.UtilisateurController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import model.utilisateur.Utilisateur;
import services.bdd.Format;

public class Connexion {

    @FXML private TextField email;
    @FXML private TextField mdp;
    @FXML private Text erreur;
    private Boolean inscrit;

    public Connexion(){this(null);}
    public Connexion(Boolean inscrit){this.inscrit = inscrit;}
    public void initialize(){
        if (this.inscrit!=null){
            erreur.setText("Utilisateur "+(this.inscrit?"créé":"modifié")+" avec succès !");
            erreur.setFill(Color.web("#00aa33"));
            erreur.setVisible(true);
        }
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchInscription(ActionEvent event) {Main.changeScene("Inscription", new Inscription(), "Inscrivez vous ;)");}
    @FXML void connexion(ActionEvent event) {
        if (Format.verif(new String[]{email.getText(), mdp.getText()})){
            Utilisateur utilisateur = UtilisateurController.connect(email.getText(), mdp.getText());
            if (utilisateur == null){
                erreur.setText("Erreur, e-mail ou mot de passe incorrect !");
                erreur.setFill(Color.web("#940000"));
                erreur.setVisible(true);
            } else if (utilisateur.isValid()) {
                Main.changeScene("Accueil", new Accueil(utilisateur), "Bienvenue "+utilisateur.getPrenom());
            } else {
                Main.changeScene("ValidEmail", new ValidEmail(utilisateur), "Validez vôtre compte pour l'utiliser ;)");
            }
        } else {erreur.setFill(Color.web("#940000"));erreur.setText("Erreur, les charactères suivants sont interdits: \n '\"' | \"'\" | ' ' | '(' | ')'");}
    }
    @FXML void mdpOubli(MouseEvent event) {
        if (this.email.getText().equals("")){
            erreur.setFill(Color.web("#940000"));
            erreur.setText("Erreur, veuillez indiquer vôtre e-mail");
            erreur.setVisible(true);
        } else {
            Main.changeScene("ValidEmail", new ValidEmail(this.email.getText(), true), "");
        }
    }

}
