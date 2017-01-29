package com.task.contactmanagement.modules.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.task.contactmanagement.R;
import com.task.contactmanagement.base.BaseActivity;
import com.task.contactmanagement.di.components.DaggerContactComponent;
import com.task.contactmanagement.di.module.ContactModule;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.presenter.ContactPresenter;
import com.task.contactmanagement.mvp.view.MainView;
import com.task.contactmanagement.utilities.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
/**
 * Created by Rajeesh adambil on 29/01/2017.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView {
    @Bind(R.id.repo_list)
    protected RecyclerView mRepoList;
    @Bind(R.id.nav_view)
    protected NavigationView navigationView;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    protected DrawerLayout drawer ;

    @Inject
    protected ContactPresenter mPresenter;
    private ContactAdapter mContactAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        loadRepo();
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
    private void loadRepo() {
        if(NetworkUtils.isNetAvailable(this)) {
            mPresenter.getContact();
        } else {
            mPresenter.getContactFromDatabase();
        }


    }


    private void initializeList() {


        setSupportActionBar(toolbar);
        setTitle("User :");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        mRepoList.setHasFixedSize(true);
        mRepoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mContactAdapter = new ContactAdapter(getLayoutInflater());
        mContactAdapter.setOnContactClickListener(mContactClickListener);
        mRepoList.setAdapter(mContactAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //  logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_reload) {
            loadRepo();
        } else if (id == R.id.nav_aboutme) {

            showAbout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAbout() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Developed by Rajeesh adambil")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                .create();
        dialog.show();
    }




    @Override
    public void onContactLoaded(List<Contact> contacts) {
        mContactAdapter.addContacts(contacts);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerContactComponent.builder()
                .applicationComponent(getApplicationComponent())
                .contactModule(new ContactModule(this))
                .build().inject(this);
    }


    @Override
    public void onClearItems() {
        mContactAdapter.clearContact();
    }

    private void loadDetails(final String reponame){
        if (NetworkUtils.isNetAvailable(getApplicationContext())) {
            //    Log.e("urlcheck",GithubApp.API_URL+ GlobalConstants.REPO_DETAIL_URL);
//            Intent intent = new Intent(RepoActivity.this, DetailActivity.class);
//            intent.putExtra("REPO_NAME",reponame);
//            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar
                    .make(getWindow().getCurrentFocus(), "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadDetails(reponame);
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);

            snackbar.show();


        }
    }
    private ContactAdapter.OnContactClickListener mContactClickListener = new ContactAdapter.OnContactClickListener() {
        @Override
        public void onClick(View v, String contact_Id, int position) {
            loadDetails(contact_Id);
        }





    };
}
