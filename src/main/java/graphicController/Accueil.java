package graphicController;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.utilisateur.Utilisateur;

public class Accueil {

    @FXML private Button connexion;
    @FXML private Button editProfil;
    @FXML private Button inscription;
    @FXML private Button mineListes;
    @FXML private Button mineTypes;
    private Utilisateur utilisateur;



    public Accueil(Utilisateur utilisateur){this.utilisateur = utilisateur;}
    public Accueil(){this(null);}
    public void initialize(){
        if (this.utilisateur!=null){
            this.editProfil.setVisible(true);
            this.mineListes.setVisible(true);
            this.mineTypes.setVisible(true);
            this.inscription.setOnAction(this::switchMineListes);
            this.inscription.setText("Ajoute en une !");
            this.connexion.setText("Se déco...");
        }
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchInscription(ActionEvent event) {Main.changeScene("Inscription", new Inscription(), "Inscrivez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {Main.changeScene("MineTypes", new MineTypes(this.utilisateur), "Modifiez vos différents types de listes ;)");}
}
