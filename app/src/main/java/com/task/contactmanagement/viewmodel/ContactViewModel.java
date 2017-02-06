package com.task.contactmanagement.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import com.task.contactmanagement.mvp.model.Contact;

/**
 * Created by rajeesh on 6/2/17.
 */

public class ContactViewModel extends BaseObservable {
    private Contact contact;
    private Context mContext;

    public ContactViewModel(Contact contact, Context mContext) {
        this.contact = contact;
        this.mContext = mContext;
    }
    public String getProfile_pic() {
        return contact.getProfile_pic();
    }

    public String getLast_name() {
        return contact.getLast_name();
    }

    public int getId() {
        return contact.getId();
    }

    public String getFirst_name() {
        return contact.getFirst_name();
    }

    public String getUrl() {
        return contact.getUrl();
    }
    public String getStart_alphabet() {
        return contact.getStart_alphabet();
    }
    public View.OnClickListener onItemClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Opens article detail", Toast.LENGTH_SHORT).show();

            }
        };
    }



}
