import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controller
{
    File file = new File("./people.json");

    @FXML
    TableView<Address> table;
    @FXML
    TextField newFirstname;
    @FXML
    TextField newLastname;
    @FXML
    TextField newStreetname;
    @FXML
    TextField newZIP;
    @FXML
    ProgressBar progressBar;
    @FXML
    Label time;
    @FXML
    MenuItem deleteButton;
    @FXML
    MenuItem editButton1;
    @FXML
    MenuItem editButton2;
    @FXML
    Slider saveSlider;
    @FXML
    MenuItem closeButton;
    @FXML
    MenuItem aboutButton;
    @FXML
    MenuItem saveButton;
    @FXML
    MenuItem refreshButton;

    public void init(Stage stage, App app)
    {
        //table controls
        TableColumn<Address, String> firstname = new TableColumn("firstname");
        firstname.setCellFactory(TextFieldTableCell.forTableColumn());
        firstname.setCellValueFactory(
                new PropertyValueFactory<Address, String>("firstname")
        );
        firstname.setOnEditCommit(cellEditEvent -> cellEditEvent.getRowValue().setFirstname(cellEditEvent.getNewValue()));

        TableColumn<Address, String> lastname = new TableColumn("lastname");
        lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        lastname.setCellValueFactory(
                new PropertyValueFactory<Address, String>("lastname")
        );
        lastname.setOnEditCommit(cellEditEvent -> cellEditEvent.getRowValue().setLastname(cellEditEvent.getNewValue()));

        TableColumn<Address, String> streetname = new TableColumn("streetname");
        streetname.setCellFactory(TextFieldTableCell.forTableColumn());
        streetname.setCellValueFactory(
                new PropertyValueFactory<Address, String>("streetname")
        );
        streetname.setOnEditCommit(cellEditEvent -> cellEditEvent.getRowValue().setStreetname(cellEditEvent.getNewValue()));

        TableColumn<Address, String> zip = new TableColumn("zip");
        zip.setCellFactory(TextFieldTableCell.forTableColumn());
        zip.setCellValueFactory(
                new PropertyValueFactory<Address, String>("zip")
        );
        zip.setOnEditCommit(cellEditEvent -> cellEditEvent.getRowValue().setZip(cellEditEvent.getNewValue()));

        table.setItems(app.persons);
        table.getColumns().addAll(firstname, lastname, streetname, zip);

        //Clock
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E, HH.mm");
        time.setText(String.valueOf(formatter.format(date)));

        //Menu items
        deleteButton.setOnAction(actionEvent -> {
            app.persons.remove(table.getSelectionModel().getSelectedItem());
        });
        saveButton.setOnAction(actionEvent -> {
            app.saveJSON(app.persons, file);
        });
        editButton1.setOnAction(actionEvent -> {
            table.setEditable(!false);
        });
        editButton2.setOnAction(actionEvent -> {
            table.setEditable(!true);
        });
        closeButton.setOnAction(actionEvent -> {
            stage.close();
        });
        aboutButton.setOnAction(actionEvent -> {
            System.out.println("Aury you annoying <3");
        });
        refreshButton.setOnAction(actionEvent -> {
            app.readJSON(file);
        });

        //Save slider
        saveSlider.onDragDetectedProperty();
        saveSlider.setMin(0);
        saveSlider.setMax(100);
        saveSlider.setValue(0);

        double sliderPos = saveSlider.getValue();
        saveSlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                double newValuee = (double) newValue;
                if(newValuee > 80 && newValuee < 90) {
                    app.persons.add(new Address(newFirstname.getText(), newLastname.getText(), newStreetname.getText(), newZIP.getText()));
                } else if (newValuee == 100) {
                    saveSlider.setValue(0);
                }
            }
        });
    }
}