package com.example.fireusersystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db =FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference userRef = rootRef.child("User");

    EditText username,name,email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);

        button=findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String myUserName = username.getText().toString().trim();
                String myName= name.getText().toString().trim();
                String myEmail = email.getText().toString().trim();


                HashMap<String,String> userMap = new HashMap<String, String>();
                userMap.put("Username",myUserName);
                userMap.put("Name",myName);
                userMap.put("Email",myEmail);

                userRef.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        }
                        
                    }
                });
            }
        }
        );

    }
}
