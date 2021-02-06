package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    static String extractedUserName=new String();
    static String extractedEmailAddress=new String();
    FirebaseDatabase database = null;
    DatabaseReference userNameRef;
    DatabaseReference emailRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /*
        below code is take from the lab and made some change
        personally i dont use toast because i dont like so that is why i used alert box to make more clear to the TA.
         */

        //-----------------------------start from here mycode--------------------------------------------
        Intent myintent = getIntent();
        //alert square box that shows the answer
        AlertDialog.Builder alert_answer = new AlertDialog.Builder(this);
        // change the integer value into string value
        alert_answer.setMessage(myintent.getStringExtra(MainActivity.WELCOME_MESSAGE));
        alert_answer.setPositiveButton("ok", null);
        alert_answer.create();
        alert_answer.show();
        //---------------------------------end here---------------------------------------------------------

        //attaching the event handler to retrieve button
        Button retrieveButton = findViewById(R.id.accessDBButton);
        retrieveButton.setOnClickListener(this);

        //initializing the database instance and others!
        this.initializeDatabase();
    }


    protected void initializeDatabase() {
        //code for database initialization and accessing the credentials
        database =  FirebaseDatabase.getInstance();
        userNameRef = database.getReference();
        emailRef = database.getReference();

        //read the values from the database
        emailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = (String) snapshot.child("email").getValue();
                extractedEmailAddress = email;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user = (String) snapshot.child("user").getValue();
                extractedUserName = user;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        //i change here to make it clear in the alert box
        String message = "name: " + extractedUserName+"\nemail: "+extractedEmailAddress;
        //show the message with Toast or AlertDialog
        //alert square box that shows the answer
        AlertDialog.Builder alert_answer = new AlertDialog.Builder(this);
        // change the integer value into string value
        alert_answer.setMessage(message);
        alert_answer.setPositiveButton("ok", null);
        alert_answer.create();
        alert_answer.show();
    }
}