package com.example.jianyang.getcontactsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jianyang.getcontactsapp.DataModels.Contact;
import com.example.jianyang.getcontactsapp.R;

import java.util.ArrayList;

/**
 * Created by JianYang on 10/15/16.
 */
public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> contacts;
    public ContactsAdapter(Context context, ArrayList<Contact> contacts) {
       this.context = context;
        this.contacts = contacts;
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, viewGroup, false);
        TextView tvName = (TextView) rowView.findViewById(R.id.name);
        TextView tvEmail = (TextView) rowView.findViewById(R.id.email);
        TextView tvMobile = (TextView) rowView.findViewById(R.id.mobile);

        tvName.setText(contacts.get(i).getName());
        tvEmail.setText(contacts.get(i).getEmail());
        tvMobile.setText(contacts.get(i).getMobile());
        return rowView;
    }
}
