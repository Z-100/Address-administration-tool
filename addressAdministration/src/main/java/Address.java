package main.java;

import javafx.beans.property.SimpleStringProperty;
import org.json.simple.JSONObject;

public class Address {
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty streetname;
    private SimpleStringProperty zip;

    private JSONObject jsonObject;

    public Address(String firstname, String lastname, String streetname, String zip) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.streetname = new SimpleStringProperty(streetname);
        this.zip = new SimpleStringProperty(zip);
        this.jsonObject = new JSONObject();
    }

    public JSONObject toJSON() {
        this.jsonObject.put("Firstname", this.firstname.get());
        this.jsonObject.put("Lastname", this.lastname.get());
        this.jsonObject.put("Streetname", this.streetname.get());
        this.jsonObject.put("ZIP", this.zip.get());

        return this.jsonObject;
    }


    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public void setStreetName(String streetName) {
        this.streetname.set(streetName);
    }

    public void setZip(String zip) {
        this.zip.set(zip);
    }
}