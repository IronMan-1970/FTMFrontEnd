package havr.kursova2.ftmfrontend.Controllers;

import havr.kursova2.ftmfrontend.Controllers.TableControllers.FlightTable;
import havr.kursova2.ftmfrontend.Controllers.TableControllers.PlaneTable;
import havr.kursova2.ftmfrontend.Controllers.TableControllers.UserTable;
import havr.kursova2.ftmfrontend.Models.Flight;
import havr.kursova2.ftmfrontend.Models.Plane;
import havr.kursova2.ftmfrontend.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingPageController{
    @FXML
    private TableView<User> userView;
    @FXML
    private TableView<Flight> flightView;
    @FXML
    private TableView<Plane> planeView;
    @FXML
    private TableColumn nameCol,bdCol,ageCol,countryCol, numCol,  passageCol,  startCol,  finishCol,  dataAndTimeCol,  timeCol,  planeTypeCol,  seatClassCol, planeType,seatCount,techSpec;
    @FXML
    private Button addNewItem, deleteItem, applyChanges;
    @FXML
    private Accordion adminSpace;
    @FXML
    private TitledPane userPane,flightPane,planePane;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void userTableView(){
            UserTable userTable = new UserTable();
            userTable.buildUi(userView, nameCol, bdCol, ageCol, countryCol, deleteItem, applyChanges);
        userPane.setOnMouseClicked(event -> {
            return;
            // Handle the event when titledPane1 is de-expanded
        });
    }
    public void flightTableView(){
        FlightTable flightTable = new FlightTable();
        flightTable.buildUi(flightView, numCol, passageCol, startCol, finishCol, dataAndTimeCol, timeCol, planeTypeCol, seatClassCol, addNewItem, deleteItem, applyChanges);
        flightPane.setOnMouseClicked(event -> {



            return;
            // Handle the event when titledPane1 is de-expanded
        });

    }
    public void planeTableView(){
        PlaneTable planeTable = new PlaneTable();
        planeTable.buildUi(planeView, planeType, seatCount, techSpec, addNewItem, deleteItem, applyChanges);
        planePane.setOnMouseClicked(event -> {
            planeView.getItems().clear();
            return;

            // Handle the event when titledPane1 is de-expanded
        });
    }
    public void switchToMainMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/havr/kursova2/ftmfrontend/mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
