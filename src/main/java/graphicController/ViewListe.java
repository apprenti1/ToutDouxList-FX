package graphicController;

import application.Main;
import controller.listeController.ListeController;
import controller.tacheController.TacheController;
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
import model.utilisateur.Utilisateur;

public class ViewListe {


    @FXML private Pane colorType;
    @FXML private Button connexion;
    @FXML private Text description;
    @FXML private Text descriptionType;
    @FXML private Button editProfil;
    @FXML private Button listeInfo;
    @FXML private Button mineListes;
    @FXML private Button mineTypes;
    @FXML private TableView<Tache> table;
    @FXML private TableColumn<Tache, CheckBox> valid;
    @FXML private TableColumn<Tache, String> nom;
    @FXML private Label titre;
    @FXML private Button add;
    private Liste liste;
    private Utilisateur utilisateur;

    public ViewListe(Liste liste, Utilisateur utilisateur) {
        this.liste = liste;
        this.utilisateur = utilisateur;
    }
    public void initialize(){
        this.titre.setText(this.liste.getNom());
        this.description.setText(this.liste.getDescription());
        this.valid.setCellValueFactory(new PropertyValueFactory<Tache, CheckBox>("checkbox_est_realise"));
        this.nom.setCellValueFactory(new PropertyValueFactory<Tache, String>("nom"));
        this.table.setItems(FXCollections.observableList(this.liste.getTaches()));
    }
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {}
    @FXML void delete(ActionEvent event) {
        if (this.listeInfo.isVisible()){
            for (Tache item:this.table.getSelectionModel().getSelectedItems()) {
                TacheController.deleteTache(item);
                this.table.getItems().remove(item);
            }
            this.table.refresh();
            //Main.changeScene("ViewListe", new ViewListe(this.liste, this.utilisateur), "Vôtre liste est avancée :)");
        } else {
            ListeController.deleteListe(this.liste, this.utilisateur);
            utilisateur.getListes().remove(this.liste);
            Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");
        }
    }
    //todo ajouter les redirrections dans les boutons
    @FXML void edit(ActionEvent event) {
        if (this.listeInfo.isVisible()){
            Main.changeScene("EditTache", new EditTache(this.utilisateur, this.liste, this.table.getSelectionModel().getSelectedItem()), "Modifie ta tâche :)");
        } else {
            Main.changeScene("EditListe", new  EditListe(utilisateur, liste), "Modifie ta liste :)");
        }
    }
    @FXML void showListInfo(ActionEvent event) {
        this.titre.setText(this.liste.getNom());
        this.description.setText(this.liste.getDescription());
        this.descriptionType.setText("");
        this.colorType.setVisible(false);
        this.listeInfo.setVisible(false);
    }
    @FXML void viewTache(MouseEvent event) {
        if (event.getClickCount() == 1){
            this.titre.setText( ((Tache)this.table.getSelectionModel().getSelectedItem()).getNom());
            this.description.setText(((Tache)this.table.getSelectionModel().getSelectedItem()).getDescription());
            this.descriptionType.setText(((Tache)this.table.getSelectionModel().getSelectedItem()).getType().getLibelle());
            this.colorType.setStyle("-fx-border-color: #"+((Tache)this.table.getSelectionModel().getSelectedItem()).getType().getCode_couleur()+";-fx-border-radius: 10px;-fx-border-width: 25px;");
            this.colorType.setVisible(true);
            this.listeInfo.setVisible(true);
        } else if(event.getClickCount()==2){
            TacheController.check(((Tache)this.table.getSelectionModel().getSelectedItem()));
            System.out.println("check : "+ this.table.getSelectionModel().getSelectedIndex() +" | "+ ((Tache)this.table.getSelectionModel().getSelectedItem()).isEst_realise());
            this.table.refresh();
        }
    }
    @FXML void switchAddTache(ActionEvent event) {
        Main.changeScene("EditTache", new EditTache(this.utilisateur, this.liste), "Créez vôtre tâche :)");
    }
}
