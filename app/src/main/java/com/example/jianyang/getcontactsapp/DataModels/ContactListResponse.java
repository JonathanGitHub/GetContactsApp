package com.example.jianyang.getcontactsapp.DataModels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Alcatraz on 10/16/16.
 */
public class ContactListResponse {

    /*
      Matching variable names to JSON keys
      For instance, if our property name matches that of the JSON key, then we do
      not need to annotate the attributes. However, if we have a different name we
      wish to use, we can simply annotate the declaration with @SerializedName:
     */
    @SerializedName("contacts")
    private ArrayList<Contact> contacts;

    public ContactListResponse() {
        contacts = new ArrayList<>();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public static ContactListResponse parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        ContactListResponse contactListResponse = gson.fromJson(response, ContactListResponse.class);
        return contactListResponse;
    }

}
