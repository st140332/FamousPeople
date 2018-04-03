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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vlad.famouspeople.Sort.NameComparator;
import com.vlad.famouspeople.Sort.PointsComparator;
import com.vlad.famouspeople.Sort.SurnameComparator;

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
    List<Point> points = db.pointDao().getAllPoints();
    List<UserPoint> userspoints = db.userDao().getAllInfo();
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view) ;
        search=(EditText)findViewById(R.id.search) ;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(userspoints, new UserAdapter.UserAdapterListener() {
            @Override
            public void onClickAtOKButton(int position,boolean PlusPoint) {
                User user = db.userDao().getById(users.get(position).getId());
                Point point = db.pointDao().getById(points.get(position).getId());
                if(PlusPoint)
                {
                    users.get(position).setPoints(users.get(position).getPoints() + 1);
                    points.get(position).setCount(points.get(position).getCount() + 1);
                    userspoints.get(position).setCount(userspoints.get(position).getCount() + 1);
                    //user.setPoints((users.get(position).getPoints()));
                    point.setPersonId(users.get(position).getId());
                    point.setCount(points.get(position).getCount());
                    db.userDao().update(user);
                    db.pointDao().update(point);
                    Toast.makeText(MainActivity.this,"Plus point! ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    users.get(position).setPoints(users.get(position).getPoints() - 1);
                    points.get(position).setCount(points.get(position).getCount() - 1);
                    userspoints.get(position).setCount(userspoints.get(position).getCount() - 1);
                    //user.setPoints((users.get(position).getPoints()));
                    point.setPersonId(users.get(position).getId());
                    point.setCount(points.get(position).getCount());
                    db.userDao().update(user);
                    db.pointDao().update(point);
                    Toast.makeText(MainActivity.this,"Minus point! ",Toast.LENGTH_SHORT).show();
                }

                adapter.notifyDataSetChanged();

            }
        });

        recyclerView.setAdapter(adapter);

        addTextListener();
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
                Collections.sort(userspoints, new NameComparator());
                break;
            case R.id.bysurname:
                Toast.makeText(MainActivity.this,"Sorted!",Toast.LENGTH_SHORT).show();
                Collections.sort(userspoints, new SurnameComparator());
                break;
            case R.id.bypoints:
                Toast.makeText(MainActivity.this,"Sorted!",Toast.LENGTH_SHORT).show();
                Collections.sort(userspoints, new PointsComparator());
                break;
        }
        //adapter = new UserAdapter(users,null);
        recyclerView.setAdapter(adapter);
        return true;
    }

    public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<UserPoint> filteredList = new ArrayList<>();

                for (int i = 0; i < userspoints.size(); i++) {

                    final String text = userspoints.get(i).getFirstName().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(userspoints.get(i));
                    }
                }

                adapter = new UserAdapter(filteredList, new UserAdapter.UserAdapterListener() {
                    @Override
                    public void onClickAtOKButton(int position, boolean PlusPoint) {

                        User user = db.userDao().getById(filteredList.get(position).getId());
                        Point point = db.pointDao().getById(filteredList.get(position).getId());
                        if(PlusPoint)
                        {
                            filteredList.get(position).setCount(filteredList.get(position).getCount() + 1);
                            users.get(position).setPoints(filteredList.get(position).getCount() + 1);
                            //user.setPoints((filteredList.get(position).getCount()));
                            point.setCount((filteredList.get(position).getCount()));
                            db.userDao().update(user);
                            db.pointDao().update(point);
                            Toast.makeText(MainActivity.this,"Plus point! ",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            filteredList.get(position).setCount(filteredList.get(position).getCount() - 1);
                            users.get(position).setPoints(filteredList.get(position).getCount() - 1);
                           // user.setPoints((filteredList.get(position).getCount()));
                            point.setCount((filteredList.get(position).getCount()));
                            db.userDao().update(user);
                            db.pointDao().update(point);
                            Toast.makeText(MainActivity.this,"Minus point! ",Toast.LENGTH_SHORT).show();
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
                recyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }
        });
    }

}
