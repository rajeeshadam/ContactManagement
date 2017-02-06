package com.task.contactmanagement.modules.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.task.contactmanagement.R;
import com.task.contactmanagement.base.BaseActivity;
import com.task.contactmanagement.di.components.DaggerContactComponent;
import com.task.contactmanagement.di.module.ContactModule;
import com.task.contactmanagement.modules.details.DetailActivity;
import com.task.contactmanagement.modules.home.adapters.ContactAdapter;
import com.task.contactmanagement.modules.home.adapters.ContactListAdapter;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.mvp.presenter.ContactPresenter;
import com.task.contactmanagement.mvp.view.MainView;
import com.task.contactmanagement.utilities.NetworkUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import butterknife.Bind;

/**
 * Created by Rajeesh adambil on 29/01/2017.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainView {
    @Bind(R.id.contact_list)
    protected ListView mListView;
    @Bind(R.id.header_text)
    protected TextView mHeaderText;
    @Bind(R.id.nav_view)
    protected NavigationView navigationView;
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    protected DrawerLayout drawer ;

    @Inject
    protected ContactPresenter mPresenter;
    private ContactListAdapter mContactAdapter;
    private  List<Contact> contactList;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        loadContact();
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
    private void loadContact() {
        if(NetworkUtils.isNetAvailable(this)) {
            mPresenter.getContact();
        } else {
            mPresenter.getContactFromDatabase();
        }


    }


    private void initializeList() {


        setSupportActionBar(toolbar);
        setTitle("Contact Management System");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

          mContactAdapter = new ContactListAdapter();
       mListView.setAdapter(mContactAdapter);

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
            loadContact();
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
         contactList=contacts;

        System.out.println("contactsize1"+contactList.size());

        Collections.sort(contactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact lhs, Contact rhs) {
                char lhsFirstLetter = TextUtils.isEmpty(lhs.getFirst_name()+lhs.getLast_name()) ? ' ' : lhs.getFirst_name().charAt(0);
                char rhsFirstLetter = TextUtils.isEmpty(rhs.getFirst_name()+rhs.getLast_name()) ? ' ' : rhs.getFirst_name().charAt(0);
                int firstLetterComparison = Character.toUpperCase(lhsFirstLetter) - Character.toUpperCase(rhsFirstLetter);

                if (firstLetterComparison == 0) {
                    return lhs.getFirst_name().compareTo(rhs.getFirst_name());
                }

                return firstLetterComparison;
            }
        });

        System.out.println("contactsize2"+contactList.size());
        mContactAdapter.addContacts(contactList,this);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
               // if(contactList.get(i).getStart_alphabet()!=""){
                    mHeaderText.setText(String.valueOf(Character.toUpperCase(contactList.get(i).getFirst_name().charAt(0))));
               // }
                if(i==0){
                    mHeaderText.setVisibility(View.GONE);
                }else{
                    mHeaderText.setVisibility(View.VISIBLE);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this ,DetailActivity.class);
                System.out.println("ssss"+contactList.get(i).getId());
                intent.putExtra("contact_id",contactList.get(i).getId());
                startActivity(intent);

            }
        });
    }

    @Override
    public void onContactDetailsLoaded(Contact contact) {

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
