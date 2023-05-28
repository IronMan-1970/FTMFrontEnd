package havr.kursova2.ftmfrontend.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ComboBox MenuBox;

    private void loadFXMLScene(String fxmlPath) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switcher() throws IOException {
        MenuBox.setOnAction(event -> {
            String selectedItem = MenuBox.getSelectionModel().getSelectedItem().toString();
            switch (selectedItem)
            {
                case "Налаштування":
                {
                    loadFXMLScene("/havr/kursova2/ftmfrontend/settings.fxml");
                    break;
                }
                case "Кабінет":
                {
                    loadFXMLScene("/havr/kursova2/ftmfrontend/cabinet.fxml");
                    break;
                }
                default:
                    break;
            }

        });

    }
}
