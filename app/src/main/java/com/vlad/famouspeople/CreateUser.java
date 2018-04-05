package com.vlad.famouspeople;

import android.arch.persistence.room.Room;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by Vlad on 20.03.2018.
 */

public class CreateUser extends AppCompatActivity {
    private static final String TAG = "CreateUser";

    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;
    int points=0;
    AppDatabase db = App.getInstance().getDatabase();
    List<User> userss;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        button = (Button)findViewById(R.id.button);

       final AppDatabase db = App.getInstance().getDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = firstName.getText().toString().trim();
                String surname = lastName.getText().toString().trim();
                if(TextUtils.isEmpty(name))
                {firstName.setError("Enter Firstname!");}
               else if(TextUtils.isEmpty(surname))
                {lastName.setError("Enter Lastname!");}
                else {
                    db.userDao().insertAll(new User(firstName.getText().toString(), lastName.getText().toString(),
                            email.getText().toString()));
                    userss= db.userDao().getAllUsers();
                    String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                    db.pointDao().insert(new Point(points,userss.get(userss.size()-1).getId(),"CREATED",date));
                    //db.userPointDao().insertUserAndPoint(new User(firstName.getText().toString(), lastName.getText().toString(),
                    //        email.getText().toString(), points),new Point(points,1));
                    startActivity(new Intent(CreateUser.this, MainActivity.class));
                }
            }
        });
    }
}
