package havr.kursova2.ftmfrontend.Controllers.TableControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import havr.kursova2.ftmfrontend.Controllers.ModelsController;
import havr.kursova2.ftmfrontend.Models.User;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class UserTable {



    @FXML

    public void buildUi(TableView<User> userView, TableColumn nameCol,TableColumn bdCol, TableColumn ageCol,TableColumn countryCol, Button deleteItem, Button applyChanges) {


        ModelsController modelsController = new ModelsController();
        ObjectMapper objectMapper = new ObjectMapper();
        userView.setEditable(true);
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        bdCol.setCellValueFactory(new PropertyValueFactory<User, String>("birthDay"));
        bdCol.setCellFactory(TextFieldTableCell.forTableColumn());
        bdCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User,String> event) {
                User user = event.getRowValue();
                user.setBirthDay(event.getNewValue());
            }
        });
        ageCol.setCellValueFactory(new PropertyValueFactory<User, Long>("age"));
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        ageCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User,Long> event) {
                User user = event.getRowValue();
                user.setAge(event.getNewValue());
            }
        });
        countryCol.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
        countryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        countryCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User,String> event) {
                User user = event.getRowValue();
                user.setCountry(event.getNewValue());
            }
        });
        userView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<User> data = modelsController.getOperation("Users/GetOperation", "User");
        userView.getItems().addAll(data);
        deleteItem.setOnAction(event -> {
            User selectedItem = userView.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                modelsController.delOperation("Users/DelOperation/User?name=" + selectedItem.getName());
                data.remove(selectedItem);
                userView.getItems().clear();
                userView.getItems().addAll(data);
            }

        });

        applyChanges.setOnAction(event -> {
            User selectedItem = userView.getSelectionModel().getSelectedItem();
            System.out.println("Selected Item: " + selectedItem.getCountry());
            modelsController.putOperation("Users/PUTOperation/"+ selectedItem.getName(),selectedItem);
        });

    }
}


