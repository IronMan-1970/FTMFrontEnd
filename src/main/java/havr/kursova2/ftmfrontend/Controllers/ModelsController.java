package havr.kursova2.ftmfrontend.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import havr.kursova2.ftmfrontend.Models.Flight;
import havr.kursova2.ftmfrontend.Models.Plane;
import havr.kursova2.ftmfrontend.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ModelsController {
    public <T> void postOperation(String urlOp, T object)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Серіалізація в JSON-рядок
            String jsonString = objectMapper.writeValueAsString(object);
            System.out.println("Serialized JSON: " + jsonString);

            // Create an instance of HttpClient
            HttpClient httpClient = HttpClientBuilder.create().build();

            // Create an instance of HttpPost with the server endpoint URL
            HttpPost httpPost = new HttpPost("http://localhost:8080/"+urlOp);

            // Set the "Content-Type" header as "application/json"
            httpPost.setHeader("Content-Type", "application/json");

            // Set the JSON payload in the request body
            StringEntity requestEntity = new StringEntity(jsonString);
            httpPost.setEntity(requestEntity);

            // Send the HTTP POST request
            HttpResponse response = httpClient.execute(httpPost);

            // Handle the response as needed
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public <T> ObservableList<T> getOperation(String urlOp, String type){
        ObservableList<T> data = FXCollections.observableArrayList();
        // Perform the HTTP request to fetch the data from the Spring Boot application
        String apiUrl = "http://localhost:8080/"+ urlOp;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
               if (type == "Flight"){
                   ObjectMapper objectMapper = new ObjectMapper();
                   Flight[] dataArray = objectMapper.readValue(response.toString(), Flight[].class);
                   List<T> dataList = (List<T>) Arrays.asList(dataArray);
                   data.addAll(dataList);}


               else if (type == "User"){
                   ObjectMapper objectMapper = new ObjectMapper();
                   User[] dataArray = objectMapper.readValue(response.toString(), User[].class);
                   List<T> dataList = (List<T>) Arrays.asList(dataArray);
                   data.addAll(dataList);
               }

               else{ ObjectMapper objectMapper = new ObjectMapper();
                   Plane[] dataArray = objectMapper.readValue(response.toString(), Plane[].class);
                   List<T> dataList = (List<T>) Arrays.asList(dataArray);
                   data.addAll(dataList);}

                // Add the data to the ObservableList


            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    public <T> void putOperation(String urlOpm, T object){
        ObjectMapper objectMapperS = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapperS.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {


            String apiUrl = "http://localhost:8080/"+urlOpm;
            URL uRl = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) uRl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            connection.getOutputStream().write(requestBody.getBytes(StandardCharsets.UTF_8));
            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Object deleted successfully");
            } else {
                System.out.println("Response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public <T> void delOperation(String urlOp){try {

        String apiUrl = "http://localhost:8080/" + urlOp;
        URL uRl = new URL(apiUrl);

        // Open the connection
        HttpURLConnection connection = (HttpURLConnection) uRl.openConnection();
        connection.setRequestMethod("DELETE");

        // Get the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("Object deleted successfully");
        } else {
            System.out.println("Response code: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


}
