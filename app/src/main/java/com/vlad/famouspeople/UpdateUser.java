package com.vlad.famouspeople;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Vlad on 22.03.2018.
 */

public class UpdateUser extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    Button buttonUpdate;
    Button buttonDelete;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    final AppDatabase db = App.getInstance().getDatabase();
    List<Point> points;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_update);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        buttonUpdate = (Button)findViewById(R.id.Update);
        buttonDelete = (Button)findViewById(R.id.Delete);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_points) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final int Id =getIntent().getIntExtra("Id",1);
        final User user = db.userDao().getById(Id);
        points = db.pointDao().getUserPoints(Id);

        getParam();
        adapter = new PointAdapter(points);
        recyclerView.setAdapter(adapter);


       buttonUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               user.setFirstName(firstName.getText().toString());
               user.setLastName(lastName.getText().toString());
               user.setEmail(email.getText().toString());
               db.userDao().update(user);
               startActivity(new Intent(UpdateUser.this, MainActivity.class));
           }
        });

       buttonDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               db.userDao().delete(user);
               startActivity(new Intent(UpdateUser.this, MainActivity.class));
           }
       });
    }

    public void getParam(){
        String FName = getIntent().getStringExtra("FName");
        String LName = getIntent().getStringExtra("LName");
        String Email = getIntent().getStringExtra("Email");

        firstName.setText(FName);
        lastName.setText(LName);
        email.setText(Email);

    }
}
