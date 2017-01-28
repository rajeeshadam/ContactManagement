
package com.task.contactmanagement.application;

import android.app.Application;

import com.task.contactmanagement.di.components.ApplicationComponent;
import com.task.contactmanagement.di.components.DaggerApplicationComponent;
import com.task.contactmanagement.di.module.ApplicationModule;
/**
 * Created by Rajeesh adambil on 29/01/2017.
 */

public class ContactApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "http://gojek-contacts-app.herokuapp.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
