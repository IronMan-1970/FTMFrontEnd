package havr.kursova2.ftmfrontend.Controllers.TableControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import havr.kursova2.ftmfrontend.Controllers.ModelsController;
import havr.kursova2.ftmfrontend.Models.Flight;
import havr.kursova2.ftmfrontend.Models.Plane;
import havr.kursova2.ftmfrontend.Models.User;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

public class PlaneTable {
    @FXML
    public void buildUi(TableView<Plane> planeView, TableColumn planeType, TableColumn seatCount, TableColumn techSpec, Button addNewItem, Button deleteItem, Button applyChanges) {
        ModelsController modelsController = new ModelsController();
        ObjectMapper objectMapper = new ObjectMapper();
        planeView.setEditable(true);
        planeType.setCellValueFactory(new PropertyValueFactory<Plane, String>("typeOfPlane"));
        planeType.setCellFactory(TextFieldTableCell.forTableColumn());
        planeType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Plane,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Plane, String> event) {
                Plane plane = event.getRowValue();
                plane.setTypeOfPlane(event.getNewValue());
            }

        });
        seatCount.setCellValueFactory(new PropertyValueFactory<Plane, String>("numberOfSeats"));
        seatCount.setCellFactory(TextFieldTableCell.forTableColumn());
        seatCount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Plane,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Plane, String> event) {
                Plane plane = event.getRowValue();
                plane.setNumberOfSeats(event.getNewValue());
            }

        });
        techSpec.setCellValueFactory(new PropertyValueFactory<Plane, String>("specs"));
        techSpec.setCellFactory(TextFieldTableCell.forTableColumn());
        techSpec.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Plane,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Plane, String> event) {
                Plane plane = event.getRowValue();
                plane.setSpecs(event.getNewValue());
            }
        });


            planeView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            ObservableList<Plane> data = modelsController.getOperation("Planes/GetPlane","Plane");
            planeView.getItems().addAll(data);

            deleteItem.setOnAction(event -> {
                Plane selectedItem = planeView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    modelsController.delOperation("Planes/DelPlane/Plane?num=" + selectedItem.getNum());
                    data.remove(selectedItem);
                    planeView.getItems().clear();
                    planeView.getItems().addAll(data);
                }
            });

      applyChanges.setOnAction(event -> {
          Plane selectedItem = planeView.getSelectionModel().getSelectedItem();
                modelsController.putOperation("Planes/PutPlane/"+ selectedItem.getNum(),selectedItem);
            });
        addNewItem.setOnAction(event1 -> {
            Plane newItem = new Plane();
                newItem.setNum(data.size());// Create a new item with an empty name
                planeView.getItems().addAll(newItem);
                planeView.edit(data.indexOf(newItem), planeType); // Enable immediate editing of the new item
            });


        planeView.setOnKeyPressed(event -> {
                KeyCode keyCode = event.getCode();
                System.out.println("Key Pressed: " + keyCode);

                // Perform additional actions based on the key pressed
                if (keyCode == KeyCode.ADD) {
                    Plane selectedItem = planeView.getSelectionModel().getSelectedItem();
                    data.add(selectedItem);
                    modelsController.postOperation("Planes/PostPlane",selectedItem);
                    System.out.println("Enter key was pressed");
                } else {
                    // Handle Delete key press
                    // ...
                }
            });

    }
    }
