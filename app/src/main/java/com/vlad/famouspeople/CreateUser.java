package com.vlad.famouspeople;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Vlad on 20.03.2018.
 */

public class CreateUser extends AppCompatActivity {
    private static final String TAG = "CreateUser";

    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;
    ArrayList<String> users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        button = (Button)findViewById(R.id.button);

      // final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
      //          AppDatabase.class, "productionn")
       //         .allowMainThreadQueries()
       //         .build();

       final AppDatabase db = App.getInstance().getDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 20.03.2018 save user to DB 
               // Log.d(TAG, "onClick: firstName" + firstName.getText().toString());
                db.userDao().insertAll(new User(firstName.getText().toString(),lastName.getText().toString(),
                        email.getText().toString()));
                startActivity(new Intent(CreateUser.this,MainActivity.class));
            }
        });
    }
}
