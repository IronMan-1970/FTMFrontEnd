module havr.kursova2.ftmfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;
    requires org.mongodb.bson;


    opens havr.kursova2.ftmfrontend to javafx.fxml;
    exports havr.kursova2.ftmfrontend;
    exports havr.kursova2.ftmfrontend.Models;
    opens havr.kursova2.ftmfrontend.Models to javafx.fxml;
    exports havr.kursova2.ftmfrontend.Controllers;
    opens havr.kursova2.ftmfrontend.Controllers to javafx.fxml;

}