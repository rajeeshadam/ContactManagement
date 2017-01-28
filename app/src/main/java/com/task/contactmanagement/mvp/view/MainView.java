

package com.task.contactmanagement.mvp.view;


import com.task.contactmanagement.mvp.model.Contact;

import java.util.List;


public interface MainView extends BaseView {

    void onContactLoaded(List<Contact> contacts);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
