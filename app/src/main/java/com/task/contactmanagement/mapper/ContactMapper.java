

package com.task.contactmanagement.mapper;

import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.model.Storage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ContactMapper {
    @Inject
    public ContactMapper() {
    }

    public List<Contact> mapContacts(Storage storage, List<Contact> contactList) {
        List<Contact> cList = new ArrayList<>();

        if (contactList != null) {

                for (Contact contacts : contactList) {
                    Contact contact = new Contact();
                    contact.setId(contacts.getId());
                    contact.setFirst_name(contacts.getFirst_name());
                    contact.setLast_name(contacts.getLast_name());
                    contact.setProfile_pic(contacts.getProfile_pic());
                    contact.setFavorite(contacts.getFavourite());
                    contact.setUrl(contacts.getUrl());
                    storage.addContact(contact);
                    cList.add(contact);
                }

        }
        return cList;
    }
}
