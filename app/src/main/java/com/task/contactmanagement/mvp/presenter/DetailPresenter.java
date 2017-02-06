package com.task.contactmanagement.mvp.presenter;

import com.task.contactmanagement.api.ContactApiService;
import com.task.contactmanagement.base.BasePresenter;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.view.DetailView;
import com.task.contactmanagement.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by rajeesh on 2/2/17.
 */

public class DetailPresenter extends BasePresenter<MainView> implements Observer<Contact> {

    @Inject
    protected ContactApiService mApiService;
    public void getContactDetails(int contact_id) {
        getView().onShowDialog("Loading Details....");
        Observable<Contact> contactDetails= mApiService.getContactDetails(contact_id);
        subscribe(contactDetails,this);
    }
    @Inject
    public DetailPresenter() {

    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Contact contact) {
        System.out.println("Contact details"+contact.getProfile_pic());
        getView().onClearItems();
        getView().onContactDetailsLoaded(contact);
    }
}
