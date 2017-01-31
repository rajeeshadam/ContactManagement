package com.task.contactmanagement.modules.home.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.task.contactmanagement.R;
import com.task.contactmanagement.databinding.ContactBinding;
import com.task.contactmanagement.mvp.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeesh on 30/1/17.
 */

public class ContactListAdapter extends BaseAdapter {
    private List<Contact> mContactList = new ArrayList<>();


    @Override

    public int getCount() {
        return mContactList.size();
    }

    @Override
    public Object getItem(int i) {
        return mContactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void addContacts(List<Contact> contacts) {
        mContactList.addAll(contacts);
        notifyDataSetChanged();
    }
    public void clearContact() {
        mContactList.clear();
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("newn"+i);
        ContactBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.list_item_layout,viewGroup,false);
        binding.setContact(mContactList.get(i));
        return binding.getRoot();
    }

}
