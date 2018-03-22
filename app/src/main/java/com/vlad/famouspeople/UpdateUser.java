package com.vlad.famouspeople;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Vlad on 22.03.2018.
 */

public class UpdateUser extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_update);
        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        button = (Button)findViewById(R.id.Update);

        getParam();

       button.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               AppDatabase db = App.getInstance().getDatabase();
               int Id =getIntent().getIntExtra("Id",1);
                 User user = db.userDao().getById(Id);
               user.setFirstName(firstName.getText().toString());
               user.setLastName(lastName.getText().toString());
               user.setEmail(email.getText().toString());
               db.userDao().update(user);
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
