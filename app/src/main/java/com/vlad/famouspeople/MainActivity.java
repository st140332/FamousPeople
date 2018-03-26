package com.vlad.famouspeople;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.sort;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    AppDatabase db = App.getInstance().getDatabase();
    List<User> users = db.userDao().getAllUsers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view) ;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(users);
        recyclerView.setAdapter(adapter);

        //MenuItem item = (MenuItem)findViewById(R.id.settings);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateUser.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.byname:
                Toast.makeText(MainActivity.this,"Sorted!",Toast.LENGTH_SHORT).show();
                Collections.sort(users, new NameComparator());
                break;
            case R.id.bysurname:
                Toast.makeText(MainActivity.this,"Sorted!",Toast.LENGTH_SHORT).show();
                Collections.sort(users, new SurnameComparator());
                break;
            case R.id.bypoints:
                Toast.makeText(MainActivity.this,"Sorted!",Toast.LENGTH_SHORT).show();
                Collections.sort(users, new PointsComparator());
                break;
        }
        adapter = new UserAdapter(users);
        recyclerView.setAdapter(adapter);
        return true;
    }


}
