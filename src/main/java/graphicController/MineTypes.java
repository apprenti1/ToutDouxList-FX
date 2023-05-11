package graphicController;

import application.Main;
import controller.listeController.ListeController;
import controller.tacheController.TacheController;
import controller.typeController.TypeController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.todolist.Liste;
import model.todolist.Tache;
import model.todolist.Type;
import model.utilisateur.Utilisateur;

public class MineTypes {


    @FXML private Pane colorType;
    @FXML private Button connexion;
    @FXML private Button editProfil;
    @FXML private Button mineListes;
    @FXML private Button mineTypes;
    @FXML private TableView<Type> table;
    @FXML private TableColumn<Type, Pane> colcolor;
    @FXML private TableColumn<Type, String> collibelle;
    @FXML private Label titre;
    @FXML private Button add;
    @FXML private Button editbutton;
    @FXML private Button deletebutton;
    private Utilisateur utilisateur;

    public MineTypes(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public void initialize(){
        this.colcolor.setCellValueFactory(new PropertyValueFactory<Type, Pane>("colorPane"));
        this.collibelle.setCellValueFactory(new PropertyValueFactory<Type, String>("libelle"));
        this.table.setItems(FXCollections.observableList(this.utilisateur.getTypes()));
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchInscription(ActionEvent event) {Main.changeScene("Inscription", new Inscription(), "Inscrivez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {}
    @FXML void delete(ActionEvent event) {
        for (Type item:this.table.getSelectionModel().getSelectedItems()) {
            TypeController.deleteType(item);
            this.table.getItems().remove(item);
        }
        this.table.refresh();
        //Main.changeScene("ViewListe", new ViewListe(this.liste, this.utilisateur), "Vôtre liste est avancée :)");


    }
    @FXML void edit(ActionEvent event) {
        Main.changeScene("EditType", new EditType(this.utilisateur, this.table.getSelectionModel().getSelectedItem()), "Modifie ton type :)");
    }
    @FXML void switchAddType(ActionEvent event) {
        Main.changeScene("EditType", new EditType(this.utilisateur), "Modifie ton type :)");
    }
    @FXML void viewType(MouseEvent event) {
        this.titre.setText(((Type)this.table.getSelectionModel().getSelectedItem()).getLibelle());
        this.colorType.setStyle("-fx-border-color: #"+((Type)this.table.getSelectionModel().getSelectedItem()).getCode_couleur()+";-fx-border-radius: 10px;-fx-border-width: 25px;");
        this.editbutton.setVisible(true);
        this.deletebutton.setVisible(true);
    }

    //todo ya tout à faire, c juste une copie de viewListe
}
