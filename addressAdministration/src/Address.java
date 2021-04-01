import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.lang.model.util.SimpleElementVisitor6;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Address
{
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty streetname;
    private SimpleStringProperty zip;

    public Address(String firstname, String lastname, String streetname, String zip)
    {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.streetname = new SimpleStringProperty(streetname);
        this.zip = new SimpleStringProperty(zip);
    }

    public JSONObject toJSON()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Firstname", this.firstname.get());
        jsonObject.put("Lastname", this.lastname.get());
        jsonObject.put("Streetname", this.streetname.get());
        jsonObject.put("ZIP", this.zip.get());

        return jsonObject;
    }

    public String getFirstname()
    {
        return this.firstname.get();
    }
    public void setFirstname(String firstname)
    {
        this.firstname.set(firstname);
    }

    public String getLastname()
    {
        return this.lastname.get();
    }
    public void setLastname(String lastname)
    {
        this.lastname.set(lastname);
    }

    public String getStreetname()
    {
        return this.streetname.get();
    }
    public void setStreetname(String streetname)
    {
        this.streetname.set(streetname);
    }

    public String getZip()
    {
        return this.zip.get();
    }
    public void setZip(String zip)
    {
        this.zip.set(zip);
    }
}