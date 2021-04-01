import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;

/*
* @author marvin zeiter
* Address administration tool
* Project, ZLI
 */
public class App extends Application
{
    private File file = new File("./people.json");
    public ObservableList<Address> persons;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ParseException
    {
        this.persons = FXCollections.observableArrayList();
        readJSON(file);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.init(primaryStage, this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Address administration tool: business education pro edition version 1.1");
        primaryStage.show();
        saveJSON(persons, file);
    }

    public void saveJSON(ObservableList<Address> persons, File file)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            JSONArray json = new JSONArray();
                for (Address address : persons) {
                    json.add(address.toJSON());
                }
            writer.write(json.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJSON(File file)
    {
        try {
        JSONParser parser = new JSONParser();
        JSONArray results = (JSONArray) parser.parse(new FileReader(file));
        this.persons.clear();
            for (Object result : results) {
                JSONObject jsonResult = (JSONObject) result;
                Address newResult = new Address(
                        (String) jsonResult.get("Firstname"),
                        (String) jsonResult.get("Lastname"),
                        (String) jsonResult.get("Streetname"),
                        (String) jsonResult.get("ZIP")
                );
                this.persons.add(newResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}