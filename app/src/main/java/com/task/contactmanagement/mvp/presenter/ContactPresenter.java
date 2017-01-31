

package com.task.contactmanagement.mvp.presenter;


import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.task.contactmanagement.api.ContactApiService;
import com.task.contactmanagement.base.BasePresenter;
import com.task.contactmanagement.helper.ImageHandler;
import com.task.contactmanagement.mapper.ContactMapper;
import com.task.contactmanagement.modules.home.adapters.ContactListAdapter;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.model.Storage;
import com.task.contactmanagement.mvp.view.MainView;
import com.task.contactmanagement.utilities.RoundImageTransform;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public class ContactPresenter extends BasePresenter<MainView> implements Observer<List<Contact>> {

    @Inject
    protected ContactApiService mApiService;
    @Inject protected ContactMapper mContactMapper;
    @Inject protected Storage mStorage;
    @Inject
    public ContactPresenter() {

    }
    protected List<Contact> contactList;


    public void getContact() {
        getView().onShowDialog("Loading Contact....");
        Observable<List<Contact>> contactList = mApiService.getContact();
        subscribe(contactList,this);
    }


    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Repos loading complete!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();

    }

    @Override
    public void onNext(List<Contact> contacts) {
        System.out.println("insidenext");
//        mContactMapper.mapContacts(mStorage, contacts);
        getView().onClearItems();
        getView().onContactLoaded(contacts);
        mStorage.addContact(contacts);
    }


    public void getContactFromDatabase() {
        contactList = mStorage.getSavedContacts();
        getView().onClearItems();
        getView().onContactLoaded(contactList);
    }




}
