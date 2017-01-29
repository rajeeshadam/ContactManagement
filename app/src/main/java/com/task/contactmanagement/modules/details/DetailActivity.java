

package com.task.contactmanagement.modules.details;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.task.contactmanagement.R;
import com.task.contactmanagement.base.BaseActivity;
import com.task.contactmanagement.helper.ImageHandler;
import com.task.contactmanagement.mvp.model.Contact;

import butterknife.Bind;

public class DetailActivity extends BaseActivity {

    public static final String CONTACT = "contact";

    @Bind(R.id.contact_icon) protected ImageView mContactImage;
    @Bind(R.id.textview_name) protected TextView mContactName;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContactImage.setTransitionName("cakeImageAnimation");
        }

        showBackArrow();

        Contact contact = (Contact) intent.getSerializableExtra(CONTACT);
        setTitle("Contact Detail");

        mContactName.setText(contact.getFirst_name());


        Glide.with(this).load(contact.getProfile_pic())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(mContactImage));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
