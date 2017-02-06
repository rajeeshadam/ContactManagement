package com.task.contactmanagement.modules.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.task.contactmanagement.R;
import com.task.contactmanagement.databinding.ContactBinding;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeesh on 30/1/17.
 */

public class ContactListAdapter extends BaseAdapter {
    private List<Contact> mContactList = new ArrayList<>();
    private StringBuffer CheckFirstchar;
    private Context mContext;
    public ContactListAdapter(){
        CheckFirstchar=new StringBuffer("");
    }

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
    public void addContacts(List<Contact> contacts,Context context) {
        this.mContactList.addAll(contacts);
        this.mContext=context;
        notifyDataSetChanged();
    }
    public void clearContact() {
        mContactList.clear();
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("newn"+i);

        if(CheckFirstchar.indexOf(String.valueOf(mContactList.get(i).getFirst_name().charAt(0)).toUpperCase())==-1){

            CheckFirstchar.append(Character.toUpperCase(mContactList.get(i).getFirst_name().charAt(0)));
            mContactList.get(i).setStart_alphabet(String.valueOf(mContactList.get(i).getFirst_name().charAt(0)).toUpperCase());
        }else{
            CheckFirstchar.append(Character.toUpperCase(mContactList.get(i).getFirst_name().charAt(0)));
        }

        ContactBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.list_item_layout,viewGroup,false);
        binding.setContact(new ContactViewModel(mContactList.get(i),mContext));
        return binding.getRoot();

    }

}
