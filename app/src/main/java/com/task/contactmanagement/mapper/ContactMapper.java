

package com.task.contactmanagement.mapper;

import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.model.Storage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
/**
 * Created by rajeesh on 29/1/17.
 */

public class ContactMapper {
    @Inject
    public ContactMapper() {
    }
    //public  List<Contact> cList;
    public void mapContacts(Storage storage, List<Contact> contactList) {


//        if (contactList != null) {
//
//                for (Contact contacts : contactList) {
//                    Contact contact = new Contact();
//                    contact.setId(contacts.getId());
//                    contact.setFirst_name(contacts.getFirst_name());
//                    contact.setLast_name(contacts.getLast_name());
//                    contact.setProfile_pic(contacts.getProfile_pic());
//                    contact.setFavorite(contacts.getFavourite());
//                    contact.setUrl(contacts.getUrl());
//                    storage.addContact(contact);
//
//                }
//
//        }

    }
}
