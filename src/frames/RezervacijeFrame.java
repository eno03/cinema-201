/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAdminAction;
import data.Karta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author nikol
 */
public class RezervacijeFrame extends HBox {
    
    private final TextField idField = new TextField();
    private final TextField projekcijaIdField = new TextField();
    private final TextField usernameField = new TextField();
    private final TextField popustField = new TextField();
    private final TextField redField = new TextField();
    private final TextField sedisteField = new TextField();
    private final TextField cenaField = new TextField();
    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Karta> karteTableView;
    private final Button backButton = new Button("Back");
    
    public RezervacijeFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        gridPane.add(new Label("Id: "), 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(new Label("Projekcija id: "), 0, 1);
        gridPane.add(projekcijaIdField, 1, 1);
        gridPane.add(new Label("username: "), 0, 2);
        gridPane.add(usernameField, 1, 2);
        gridPane.add(new Label("Popust: "), 0, 3);
        gridPane.add(popustField, 1, 3);
        gridPane.add(new Label("Red: "), 0, 4);
        gridPane.add(redField, 1, 4);
        gridPane.add(new Label("Sediste: "), 0, 5);
        gridPane.add(sedisteField, 1, 5);
        gridPane.add(new Label("Cena: "), 0, 6);
        gridPane.add(cenaField, 1, 6);
        
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        idField.setAlignment(Pos.CENTER_RIGHT);
        projekcijaIdField.setAlignment(Pos.CENTER_RIGHT);
        usernameField.setAlignment(Pos.CENTER_RIGHT);
        popustField.setAlignment(Pos.CENTER_RIGHT);
        sedisteField.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);
        
        TableColumn<Karta, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Karta, Integer> projekcijaColumn = new TableColumn<>("Projekcija id");
        projekcijaColumn.setCellValueFactory(new PropertyValueFactory<>("projekcija_id"));
        TableColumn<Karta, String> usernameColumn = new TableColumn<>("username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<Karta, String> parterColumn = new TableColumn<>("Popust");
        parterColumn.setCellValueFactory(new PropertyValueFactory<>("popust"));
        TableColumn<Karta, Integer> redColumn = new TableColumn<>("Red");
        redColumn.setCellValueFactory(new PropertyValueFactory<>("red"));
        TableColumn<Karta, Integer> sedisteColumn = new TableColumn<>("Sediste");
        sedisteColumn.setCellValueFactory(new PropertyValueFactory<>("sediste"));
        TableColumn<Karta, Float> cenaColumn = new TableColumn<>("Cena");
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        
        
        karteTableView = new TableView<>();
        karteTableView.getColumns().addAll(idColumn, projekcijaColumn, usernameColumn, parterColumn, redColumn, sedisteColumn,cenaColumn);
        this.getChildren().addAll(karteTableView);
        backButton.setOnAction(new BackAdminAction());
        updateButton.setOnAction(e -> {
            controller.Connector.getInstance().updateKarta();
            controller.Connector.getInstance().refreshKartas();
        });
        insertButton.setOnAction(e -> {
            controller.Connector.getInstance().addKarta();
            controller.Connector.getInstance().refreshKartas();
        });
        deleteButton.setOnAction((event) -> {
            controller.Connector.getInstance().deleteKarta();
            controller.Connector.getInstance().refreshKartas();
        });
        
        karteTableView.setRowFactory(tv -> {
            TableRow<Karta> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Karta rowData = row.getItem();
                    idField.setText(rowData.getId() + "");
                    projekcijaIdField.setText(rowData.getProjekcija_id() + "");
                    usernameField.setText(rowData.getUsername());
                    popustField.setText(rowData.getPopust());
                    sedisteField.setText(rowData.getSediste() + "");
                    redField.setText(rowData.getRed() + "");
                    cenaField.setText(rowData.getCena() + "");
                }
            });
            return row;
        });
    }
    
    public TableView<Karta> getKarteTableView() {
        return karteTableView;
    }
    
    public void setKarteTableView(TableView<Karta> karteTableView) {
        this.karteTableView = karteTableView;
    }
    
    public TextField getIdField() {
        return idField;
    }
    
    public TextField getProjekcijaIdField() {
        return projekcijaIdField;
    }
    
    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPopustField() {
        return popustField;
    }

    public TextField getCenaField() {
        return cenaField;
    }
    
   
    
    public TextField getSedisteField() {
        return sedisteField;
    }
    
    public TextField getRedField() {
        return redField;
    }
    
}
