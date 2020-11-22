package com.example.rnpt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rnpt.fragments.FragmentOpenRNPT;
import com.example.rnpt.fragments.Fragment_list_rnpt;
import com.example.rnpt.fragments.Fragment_master_token;
import com.example.rnpt.fragments.Fragment_settings;
import com.example.rnpt.receivers.BattetyReceiver;
import com.example.rnpt.receivers.NoConnection;
import com.google.android.material.navigation.NavigationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView listView;
    private BroadcastReceiver batteryReceiver = new BattetyReceiver();
    private BroadcastReceiver noConnection = new NoConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment_list_rnpt fragment_list_rnpt = new Fragment_list_rnpt(this);
        fragmentTransaction.replace(R.id.context_main, fragment_list_rnpt);
        fragmentTransaction.commit();
        close_drawer();

        // Программная регистрация ресивера баттареи
        this.registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        // Программная регистрация ресивера нет сети
        this.registerReceiver(noConnection, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        initGetToken();
        initNotificationChannel();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (id){
            case R.id.nav_token_master:
                Fragment_master_token fragment_master_token = new Fragment_master_token();
                fragmentTransaction.replace(R.id.context_main, fragment_master_token);
                fragmentTransaction.commit();
                close_drawer();
                return true;

            case R.id.nav_list_rnpt:

                Fragment_list_rnpt fragment_list_rnpt = new Fragment_list_rnpt(this);
                fragmentTransaction.replace(R.id.context_main, fragment_list_rnpt);
                fragmentTransaction.commit();
                close_drawer();
                return true;

            case R.id.nav_settings:
                Fragment_settings fragment_settings = new Fragment_settings();
                fragmentTransaction.replace(R.id.context_main, fragment_settings);
                fragmentTransaction.commit();
                close_drawer();
                return true;
        }

        close_drawer();
        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unregisterReceiver(batteryReceiver);
        this.unregisterReceiver(noConnection);
    }

    private void initGetToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener((task) -> {
                    if (!task.isSuccessful()){
                        Log.w("PushMessage", "getInstanceId failed", task.getException());
                        return;
                    }
                });

    }

    private void close_drawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    private Toolbar initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel("2", "name", importance);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.add_context:
                return true;
            case R.id.update_context:
                return true;
            case R.id.remove_context:
                return true;
            case R.id.clear_context:
                return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.menu.context_menu, menu);
      return true;
    }

    public void open_RNPT(){

    }

    public void onDialogResult(String edit_rnpt, String ok) {


    }
}


