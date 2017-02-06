package com.task.contactmanagement.modules.details;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;

import com.task.contactmanagement.R;
import com.task.contactmanagement.base.BaseActivity;
import com.task.contactmanagement.databinding.ContactBinding;
import com.task.contactmanagement.databinding.ContactDetailBinding;
import com.task.contactmanagement.di.components.DaggerContactComponent;
import com.task.contactmanagement.di.module.ContactModule;
import com.task.contactmanagement.modules.home.adapters.ContactListAdapter;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.presenter.ContactPresenter;
import com.task.contactmanagement.mvp.presenter.DetailPresenter;
import com.task.contactmanagement.mvp.view.MainView;
import com.task.contactmanagement.utilities.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rajeesh on 3/2/17.
 */

public class DetailActivity extends BaseActivity implements MainView {
    int id;
    @Inject
    protected DetailPresenter mPresenter;
    @Override
    protected int getContentView() {
       return R.layout.activity_detail;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
       id= intent.getIntExtra("contact_id",0);
        System.out.println("ssssnew"+id);
        showBackArrow();
       initializeList();
      loadContactDetails();
    }
    private void loadContactDetails() {
        if(NetworkUtils.isNetAvailable(this)) {
            mPresenter.getContactDetails(id);
        } else {
           // mPresenter.getContactFromDatabase();
        }


    }
    @Override
    protected void resolveDaggerDependency() {
        DaggerContactComponent.builder()
                .applicationComponent(getApplicationComponent())
                .contactModule(new ContactModule(this))
                .build().inject(this);
    }

//    @Override
//    public void onContactLoaded(List<Contact> contacts) {
//
//    }

    private void initializeList() {



        setTitle("Contact Management System");

    }

    @Override
    public void onContactLoaded(List<Contact> contacts) {

    }

    @Override
    public void onContactDetailsLoaded(Contact contact) {

        ContactDetailBinding binding=DataBindingUtil.setContentView(this, getContentView());
        binding.setContact(contact);

    }

    @Override
    public void onShowDialog(String message) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String message) {

    }

    @Override
    public void onClearItems() {

    }
}
