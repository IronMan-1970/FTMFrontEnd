package havr.kursova2.ftmfrontend.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import havr.kursova2.ftmfrontend.Models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import  havr.kursova2.ftmfrontend.Controllers.ModelsController;
import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField usrName;
    @FXML
    private PasswordField usrPsswd;
    @FXML
    private DatePicker usrBrthD;
    @FXML
    private ComboBox<String> UsrCnt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> countryNames = new ArrayList<>();
        String[] locales = Locale.getISOCountries();
        for (String countrylist : locales) {
            Locale obj = new Locale("", countrylist);
            countryNames.add(obj.getDisplayCountry(Locale.ENGLISH));
          
        }
        UsrCnt.setItems(FXCollections.observableArrayList(countryNames));

    }
    public void newUser(){
        //Ініціалізація
        ModelsController modelsController = new ModelsController();
        LocalDate userBirthDay = usrBrthD.getValue();
        Clock cl = Clock.systemUTC();
        LocalDate nowday = LocalDate.now(cl);
        long age = java.time.temporal.ChronoUnit.YEARS.between(userBirthDay  , nowday );
        User user = new User(usrName.getText(), usrPsswd.getText(),usrBrthD.getValue().toString(), age,UsrCnt.getValue().toString());
        modelsController.postOperation("Users/PostOperation",user);
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/havr/kursova2/ftmfrontend/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
