package graphicController;

import application.Main;
import controller.utilisateurController.UtilisateurController;
import jakarta.mail.MessagingException;
import jakarta.mail.SendFailedException;
import jakarta.mail.internet.AddressException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.utilisateur.Utilisateur;
import services.email.Email;
import org.apache.commons.lang3.RandomStringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ValidEmail {
    @FXML private TextField code;
    @FXML private Text email;
    @FXML private Text erreur;

    private Utilisateur utilisateur;
    private boolean resetMdp;
    private String codeValid;
    private int essai;
    private String emailUtilisateur;

    public ValidEmail(Utilisateur utilisateur, boolean resetMdp) {
        this.utilisateur = utilisateur;
        this.resetMdp = resetMdp;
        this.essai = 3;
    }
    public ValidEmail(Utilisateur utilisateur) {this(utilisateur, false);}
    public ValidEmail(String email, boolean resetMdp) {
        this((Utilisateur) null, resetMdp);
        this.emailUtilisateur = email;
    }
    public void initialize(){
        this.email.setText((this.utilisateur == null)?this.emailUtilisateur : this.utilisateur.getEmail());
        if ((this.utilisateur == null && UtilisateurController.countEmail(this.emailUtilisateur) == 1) || (this.utilisateur != null && UtilisateurController.countEmail(this.utilisateur.getEmail()) == 1)){
            this.codeValid = RandomStringUtils.random(6, true, true);
            System.out.println(this.codeValid);
            try {
                Email.send((this.utilisateur == null)?this.emailUtilisateur : this.utilisateur.getEmail(), ("ToutDouxListFX " + ((this.resetMdp) ? "Réinitialisation de mot de passe" : "Validation de compte")), ("Le code : " + this.codeValid));
            }
            catch (AddressException e) {
                this.codeValid = "emailIncorrect";
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void valid(ActionEvent event) {
        if (this.code.getText().equals(this.codeValid) && !this.codeValid.equals("emailIncorrect")){
            if (this.resetMdp){
                if (!this.utilisateur.isValid()){UtilisateurController.valid(this.utilisateur);}
                Main.changeScene("EditProfil", new EditProfil(this.utilisateur), "Modifions nôtre profil !!");
            } else {
                UtilisateurController.valid(this.utilisateur);
                Main.changeScene("Accueil", new Accueil(utilisateur), "Bienvenue " + this.utilisateur.getPrenom());
            }
        } else if (this.essai <= 0) {
            Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");
        } else {
            erreur.setText("Erreur, code incorrect\n"+this.essai+" essai restant");
            erreur.setVisible(true);
            this.essai --;
        }
    }
}
