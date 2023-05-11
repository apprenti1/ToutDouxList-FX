package graphicController;

import application.Main;
import controller.utilisateurController.UtilisateurController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.utilisateur.Utilisateur;
import services.bdd.Format;

public class EditProfil {

    @FXML private TextField conf;
    @FXML private TextField email;
    @FXML private Text erreur;
    @FXML private TextField mdp;
    @FXML private TextField nom;
    @FXML private TextField prenom;
    private Utilisateur utilisateur;
    private boolean connected;

    public EditProfil(Utilisateur utilisateur, boolean connected){
        this.utilisateur = utilisateur;
        this.connected = connected;
    }
    public void initialize(){
        this.nom.setText(this.utilisateur.getNom());
        this.prenom.setText(this.utilisateur.getPrenom());
        this.email.setText(this.utilisateur.getEmail());
        this.mdp.setText(this.utilisateur.getMdp());
    }
    public EditProfil(Utilisateur utilisateur) {this(utilisateur, false);}
    @FXML void modification(ActionEvent event) {
        if (Format.verif(new String[]{this.nom.getText(), this.prenom.getText(), this.email.getText(), this.mdp.getText()})) {
            if (mdp.getText().equals(conf.getText())) {
                if (this.utilisateur.getEmail().equals(email.getText()) || UtilisateurController.countEmail(email.getText()) == 0){
                    this.utilisateur.setInfos(this.nom.getText(), this.prenom.getText(), this.email.getText(), this.mdp.getText());
                    UtilisateurController.update(this.utilisateur);
                    if (this.connected){
                        Main.changeScene("Accueil", new Accueil(utilisateur), "Bienvenue "+utilisateur.getPrenom());
                    } else {
                        Main.changeScene("Connexion", new Connexion(false), "Connectez vous ;)");
                    }
                } else this.erreur.setText("Erreur, un autre compte existe déjà avec cet e-mail");
            } else this.erreur.setText("Erreur, le mot de passe et la confirmation ne sont pas identique");
        } else this.erreur.setText("Erreur, les charactères suivants sont interdits: \n '\"' | \"'\" | ' ' | '(' | ')'");
        erreur.setVisible(true);
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {}

    //todo bar buttons
}
