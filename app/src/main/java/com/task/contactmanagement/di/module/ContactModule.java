

package com.task.contactmanagement.di.module;

import com.task.contactmanagement.api.ContactApiService;
import com.task.contactmanagement.di.scope.PerActivity;
import com.task.contactmanagement.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
/**
 * Created by Rajeesh adambil on 29/01/2017.
 */

@Module
public class ContactModule {

    private MainView mView;

    public ContactModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    ContactApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ContactApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}
