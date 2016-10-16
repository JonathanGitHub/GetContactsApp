package com.example.jianyang.getcontactsapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.jianyang.getcontactsapp.Adapters.ContactsAdapter;
import com.example.jianyang.getcontactsapp.DataModels.Contact;
import com.example.jianyang.getcontactsapp.DataModels.ContactListResponse;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by JianYang on 10/15/16.
 *
 */
public class MainActivity extends AppCompatActivity {


    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/contacts/";

    ArrayList<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
            .url(url)
            .build();


        Call call = okHttpClient.newCall(request);

        // Showing progress dialog
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(true);
        pDialog.show();


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, "Response from url: " + e.getMessage());

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();

                Log.e(TAG, "Response from url: " + str);

                ContactListResponse contactListResponse = ContactListResponse.parseJSON(str);
                contactList = contactListResponse.getContacts();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss the progress dialog
                        if (pDialog.isShowing())
                            pDialog.dismiss();

                        lv.setAdapter(new ContactsAdapter(MainActivity.this, contactList));
                    }
                });

            }
        });

//        new GetContacts().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



//    /**
//     * Async task class to get json by making HTTP call
//     */
//    private class GetContacts extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Showing progress dialog
//            pDialog = new ProgressDialog(MainActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.setCanceledOnTouchOutside(true);
//            pDialog.show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HTTPHandler sh = new HTTPHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url);
//
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            //TODO...USE GSON to shorten code
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = null;
//                    try {
//                        jsonObj = new JSONObject(jsonStr);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj != null ? jsonObj.getJSONArray("contacts") : null;
//
//                    // looping through All Contacts
//                    assert contacts != null;
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//
//                        Contact contact = new Contact();
//
//                        contact.setId(c.getString("id"));
//                        contact.setName(c.getString("name"));
//
//                        contact.setEmail(c.getString("email"));
//                        contact.setAddress(c.getString("address"));
//                        contact.setGender(c.getString("gender"));
//
//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        contact.setMobile(phone.getString("mobile"));
//                        contact.setHome(phone.getString("home"));
//                        contact.setOffice(phone.getString("office"));
//
//                        // adding contact to contact list
//                        contactList.add(contact);
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                "Json parsing error: " + e.getMessage(),
//                                Toast.LENGTH_LONG)
//                                .show();
//                        }
//                    });
//
//                }
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                            "Couldn't get json from server. Check LogCat for possible errors!",
//                            Toast.LENGTH_LONG)
//                            .show();
//                    }
//                });
//
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            // Dismiss the progress dialog
//            if (pDialog.isShowing())
//                pDialog.dismiss();
//
//            /*
//             * Updating parsed JSON data into ListView
//             *
//             * */
//
//            lv.setAdapter(new ContactsAdapter(MainActivity.this, contactList));
//        }
//
//    }

}
