

package com.task.contactmanagement.api;

import com.task.contactmanagement.mvp.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
/**
 * Created by Rajeesh adambil on 29/01/2017.
 */
public interface ContactApiService {

    @GET("/contacts.json")
    Observable<List<Contact>> getContact();

    @GET("/contacts/{id}.json")
    Call<Contact> getContactDetails();
}
