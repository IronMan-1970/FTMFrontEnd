package havr.kursova2.ftmfrontend.Controllers.TableControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import havr.kursova2.ftmfrontend.Controllers.ModelsController;
import havr.kursova2.ftmfrontend.Models.Flight;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

public class FlightTable {
    @FXML
    public void buildUi(TableView<Flight> flightView, TableColumn numCol, TableColumn passageCol, TableColumn startCol, TableColumn finishCol, TableColumn dataAndTimeCol, TableColumn timeCol, TableColumn planeTypeCol, TableColumn seatClassCol, Button addNewItem, Button deleteItem, Button applyChanges)
    {
        ModelsController modelsController = new ModelsController();
        ObjectMapper objectMapper = new ObjectMapper();
        flightView.setEditable(true);
        numCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("num"));

        passageCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("passage"));
        passageCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passageCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setPassage(event.getNewValue());
            }

        });

        startCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("start"));
        startCol.setCellFactory(TextFieldTableCell.forTableColumn());
        startCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setStart(event.getNewValue());
            }

        });

        finishCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("finish"));
        finishCol.setCellFactory(TextFieldTableCell.forTableColumn());
        finishCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setFinish(event.getNewValue());
            }

        });

        dataAndTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("dataAndTime"));
        dataAndTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dataAndTimeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setDataAndTime(event.getNewValue());
            }

        });

        timeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("timeOfFlight"));
        timeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setTimeOfFlight(event.getNewValue());
            }

        });

        planeTypeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("planeType"));
        planeTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        planeTypeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setPlaneType(event.getNewValue());
            }

        });

        seatClassCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("placeClass"));
        seatClassCol.setCellFactory(TextFieldTableCell.forTableColumn());
        seatClassCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Flight,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Flight, String> event) {
                Flight flight = event.getRowValue();
                flight.setPlaceClass(event.getNewValue());
            }

        });

     flightView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Flight> data = modelsController.getOperation("Flight/GetFlight","Flight");
        flightView.getItems().addAll(data);

        deleteItem.setOnAction(event -> {
            Flight selectedItem = flightView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                modelsController.delOperation("Flight/DelFlight/Flight?num=" + selectedItem.getNum());
                data.remove(selectedItem);
                flightView.getItems().clear();
                flightView.getItems().addAll(data);
            }
        });

      applyChanges.setOnAction(event -> {
            Flight selectedItem = flightView.getSelectionModel().getSelectedItem();
            modelsController.putOperation("Flight/PutFlight/"+ selectedItem.getNum(),selectedItem);
        });
        addNewItem.setOnAction(event1 -> {
            Flight newItem = new Flight();
            newItem.setNum(data.size());// Create a new item with an empty name
            flightView.getItems().addAll(newItem);
            flightView.edit(data.indexOf(newItem), passageCol); // Enable immediate editing of the new item
        });


        flightView.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            System.out.println("Key Pressed: " + keyCode);

            // Perform additional actions based on the key pressed
            if (keyCode == KeyCode.ADD) {
                Flight selectedItem = flightView.getSelectionModel().getSelectedItem();
                data.add(selectedItem);
                modelsController.postOperation("Flight/PostFlight",selectedItem);
                System.out.println("Enter key was pressed");
            } else if (keyCode == KeyCode.DELETE) {
                // Handle Delete key press
                // ...
            }
        });

    }
}
