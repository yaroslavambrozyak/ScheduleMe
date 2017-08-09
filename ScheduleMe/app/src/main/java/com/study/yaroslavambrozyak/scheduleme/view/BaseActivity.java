package com.study.yaroslavambrozyak.scheduleme.view;


import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.study.yaroslavambrozyak.scheduleme.App;
import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.model.Remind;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //@Nullable
    //@BindView(R.id.drawer_layout)
    //DrawerLayout drawer;
    @Nullable
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private Handler handler = new Handler();
    private static final int DRAWER_CLOSE_DELAY = 200;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    /*@Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            // next
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        final int id = item.getItemId();
//        if (drawer != null) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (id == R.id.nav_camera) {
//                    // Handle the camera action
//
//                }
//            }
//        }, DRAWER_CLOSE_DELAY);
//        return true;
//    }

    protected void initUI() {
        initToolbar();
//        initDrawer();
//        initNavigation();
    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
    }

    /*private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
    }*/

    /*private void initNavigation() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }*/
}
