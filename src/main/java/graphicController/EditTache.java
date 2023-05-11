package graphicController;

import application.Main;
import controller.tacheController.TacheController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.todolist.Liste;
import model.todolist.Tache;
import model.todolist.Type;
import model.utilisateur.Utilisateur;
import services.bdd.Format;

import java.util.ArrayList;

public class EditTache {

    private Utilisateur utilisateur;
    private Liste liste;
    private Tache tache;
    @FXML private TextField description;
    @FXML private Text erreur;
    @FXML private TextField titre;
    @FXML private ComboBox<Node> type;
    @FXML private Pane color;
    @FXML private Label typeview;
    @FXML private Button modifier;
    private Type item;



    public EditTache(Utilisateur utilisateur, Liste liste, Tache tache) {
        this.utilisateur = utilisateur;
        this.liste = liste;
        this.tache = tache;
        this.item = (tache!=null) ? tache.getType() : new Type(1, "Default", "fff");
    }
    public EditTache(Utilisateur utilisateur, Liste liste){
        this(utilisateur, liste, null);
    }
    public void initialize(){
        ArrayList<Type> types = (ArrayList<Type>) this.utilisateur.getTypes().clone();
        System.out.println("tailleee: "+this.utilisateur.getTypes().size());
        System.out.println("taille: "+types.size());
        types.add(0, new Type("Default", "fff"));
        System.out.println("taille: "+types.size());
        for (Type type : types) {
            Pane color = new Pane();
            color.setMinSize(20, 20);
            color.setStyle("-fx-border-color: #"+type.getCode_couleur()+"; -fx-border-radius: 50%; -fx-border-width: 10px");
            Label titre = new Label(type.getLibelle());
            ToolBar container = new ToolBar(titre, color);
            container.setId(Integer.toString(type.getId()));
            container.setBackground(Background.EMPTY);
            this.type.getItems().add(container);
        }
        if (this.tache != null) {
            this.color.setStyle("-fx-border-color: #" + this.tache.getType().getCode_couleur() + "; -fx-border-width: 10px; -fx-border-radius: 50%;");
            this.typeview.setText(this.tache.getType().getLibelle());
            titre.setText(this.tache.getNom());
            description.setText(this.tache.getDescription());
        } else {
            this.color.setStyle("-fx-border-color: #fff; -fx-border-width: 10px; -fx-border-radius: 50%;");
            this.typeview.setText("Default");
            this.modifier.setText("Créer");
        }
    }
    @FXML void modification(ActionEvent event) {
        if (Format.verif(new String[]{this.titre.getText(), this.description.getText()})) {
            if (this.tache != null) {
                this.tache.setNom(this.titre.getText());
                this.tache.setDescription(this.description.getText());
                this.tache.setType(this.item);
                TacheController.update(this.tache);
                Main.changeScene("ViewListe", new ViewListe(this.liste, this.utilisateur), "Vôtre liste est avancée :)");
            } else {
                Tache tache = TacheController.uploadTache(new Tache(this.titre.getText(), this.description.getText(), this.item, this.liste));
                this.liste.getTaches().add(tache);
                Main.changeScene("ViewListe", new ViewListe(this.liste, this.utilisateur), "Vôtre liste est avancée :)");
            }
        } else {
            this.erreur.setText("Erreur, les charactères suivants sont interdits: \n '\"' | \"'\" | ' ' | '(' | ')'");
        }
    }
    @FXML void typeSelected(ActionEvent event) {
        Type item = null;
        for (Type tipe : this.utilisateur.getTypes()) {
            if (tipe.getId() == Integer.parseInt(((ToolBar) event.getSource()).getId())){
                item = tipe;
                this.item = item;
            }
        }
        this.color.setStyle("-fx-border-color: #"+item.getCode_couleur()+"; -fx-border-radius: 50%; -fx-border-width: 10px");
        this.typeview.setText(item.getLibelle());
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {Main.changeScene("MineTypes", new MineTypes(this.utilisateur), "Modifiez vos différents types de listes ;)");}
}
