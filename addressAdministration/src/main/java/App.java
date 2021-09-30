/**
 * @author marvin zeiter
 * main.java.Address administration tool
 * Project, ZLI
 */

package main.java;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class App extends Application {

    private File file = new File("main/resources/App.fxml");
    public ObservableList<Address> persons;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException {
        this.persons = FXCollections.observableArrayList();
        readJSON(file);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.init(primaryStage, this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("main.java.Address Administration Tool: Business Education Pro Edition Version 1.1");
        primaryStage.show();

        saveJSON(persons, file);
    }

    public void saveJSON(ObservableList<Address> persons, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            JSONArray json = new JSONArray();
            persons.forEach(address ->
                    json.add(address.toJSON()));

            writer.write(json.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJSON(File file) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray results = (JSONArray) parser.parse(new FileReader(file));

            this.persons.clear();

            results.forEach(result -> {
                JSONObject jsonResult = (JSONObject) result;
                Address newResult = new Address(
                        (String) jsonResult.get("Firstname"),
                        (String) jsonResult.get("Lastname"),
                        (String) jsonResult.get("Streetname"),
                        (String) jsonResult.get("ZIP")
                );
                this.persons.add(newResult);
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}