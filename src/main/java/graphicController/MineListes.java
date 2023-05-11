package graphicController;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.todolist.Liste;
import model.utilisateur.Utilisateur;

import java.util.ArrayList;

public class MineListes {

    private Utilisateur utilisateur;
    @FXML private Button add;
    @FXML private Button connexion;
    @FXML private Button del;
    @FXML private Button editProfil;
    @FXML private Button mineListes;
    @FXML private Button mineTypes;
    @FXML private ScrollPane principalPanel;
    @FXML private Button select;
    @FXML private GridPane tab;
    private ArrayList<Node> listes;

    public MineListes(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.listes = new ArrayList<Node>();
        for (Liste liste :
                this.utilisateur.getListes()) {
            System.out.println(liste.getNom());
            VBox node = new VBox();
            node.setPrefHeight(125);
            node.setAlignment(Pos.CENTER);
            node.setFillWidth(false);
            VBox innerBox = new VBox();
            innerBox.setAlignment(Pos.CENTER);
            innerBox.setStyle("""
                -fx-border-color: #636fcf;
                -fx-border-radius: 10%;
                -fx-border-width: 60px;
                -fx-background-color: transparent;
                """);
            innerBox.setPrefSize(120, 120);
            innerBox.setMinSize(120, 120);
            innerBox.setMaxSize(120, 120);
            innerBox.setOnMouseClicked(event->{viewListe(liste);});
            Label listName = new Label(liste.getNom());
            listName.setFont(Font.font("Calibri", FontWeight.BOLD, 17));
            listName.setAlignment(Pos.CENTER);
            listName.setMinSize(100, 25);
            listName.setStyle("-fx-background-color: transparent;");
            listName.setTextFill(Paint.valueOf("#fff"));
            Text description = new Text();
            description.setWrappingWidth(100);
            description.setFill(Paint.valueOf("#dddddd"));
            description.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
            description.setTextAlignment(TextAlignment.CENTER);
            description.setText(((liste.getDescription().length()<60)?liste.getDescription():liste.getDescription().substring(0, 60)+"..."));

            node.getChildren().add(innerBox);
            innerBox.getChildren().add(listName);
            innerBox.getChildren().add(description);
            this.listes.add(node);
        }
    }
    public void initialize(){
        for (int i = 0; Math.ceil(this.listes.size()/4)>=i ; i++){
            tab.addRow(i,
                    ((i*4+0<this.listes.size())?this.listes.get(i*4+0):new Label("")),
                    ((i*4+1<this.listes.size())?this.listes.get(i*4+1):new Label("")),
                    ((i*4+2<this.listes.size())?this.listes.get(i*4+2):new Label("")),
                    ((i*4+3<this.listes.size())?this.listes.get(i*4+3):new Label("")));
        }
        //tab.addRow(1, );
        System.out.println(tab.getRowCount());

    }
    public void viewListe(Liste liste){Main.changeScene("ViewListe", new ViewListe(liste, this.utilisateur), "Vôtre liste est avancée :)");}
    @FXML void add(ActionEvent event) {Main.changeScene("EditListe", new EditListe(this.utilisateur), "créez vôtre liste ;)");}
    @FXML void switchAccueil(MouseEvent event) {Main.changeScene("Accueil", new Accueil(this.utilisateur), "Bienvenue dans ToutDouxList-FX");}
    @FXML void switchConnexion(ActionEvent event) {Main.changeScene("Connexion", new Connexion(), "Connectez vous ;)");}
    @FXML void switchEditProfil(ActionEvent event) {Main.changeScene("EditProfil", new EditProfil(this.utilisateur, true), "Modifions nôtre profil !!");}
    @FXML void switchMineListes(ActionEvent event) {Main.changeScene("MineListes", new MineListes(this.utilisateur), "D'ici vous pouvez accéder à toutes vos listes ;)");}
    @FXML void switchMineTypes(ActionEvent event) {}
    //todo switchMinetype
}
