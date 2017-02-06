

package com.task.contactmanagement.mvp.view;




import com.task.contactmanagement.mvp.model.Contact;

import java.util.List;


public interface DetailView extends BaseView {

    void onContactDetailsLoaded(Contact contact);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
