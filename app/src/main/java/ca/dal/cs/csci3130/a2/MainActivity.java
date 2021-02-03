package ca.dal.cs.csci3130.a2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";

    FirebaseDatabase database = null;
    DatabaseReference userNameRef = null;
    DatabaseReference emailRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attaching the event handler
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        //initiating the Firebase
        initializeDatabase();

    }

    protected void initializeDatabase() {
        //initialize your database and related fields here
    }

    protected String getUserName() {
        EditText userName = findViewById(R.id.userName);
        return userName.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailAddress);
        return emailAddress.getText().toString().trim();
    }

    protected boolean isEmptyUserName(String userName) {
        return userName.isEmpty();
    }

    protected boolean isAlphanumericUserName(String userName) {
        /*
        ^ --> meaning the starting of the string
        a-z and A-Z --> meaning the lowercase and uppercase character matches in the username is allowed
        0-9 --> meaning matches any number from 0 to 9 is allowed
        + --> meaning that matches one or more quantifier
        $ -->  meaning the end of the string

        Below code do if the username does not matches any non alphanumeric in the username
        then it show the empty string in the message.

        if it contains any non alphanumeric then it will show the appropriate error in the message.
        */
        return userName.matches("^[a-zA-Z0-9]+$");
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        //your business logic goes here!
        /*
        ^ --> meaning the starting of the string
        . --> meaning that matches any character except the newline symbol
        () --> meaning that these brackets are used for the grouping characters
        + --> meaning that matches one or more quantifier
        $ -->  meaning the end of the string

        Below code do if the email is valid then it show the empty string in the message.

        if it email is not valid then it will show the appropriate error in the message.
        */
        return emailAddress.matches("^(.+)@(.+)$");
    }

    protected void switch2WelcomeWindow(String userName, String emailAddress) {
        //your business logic goes here!
    }

    protected void saveUserNameToFirebase(String userName) {
        //save user name to Firebase
    }

    protected void saveEmailToFirebase(String emailAddress) {
        //save email to Firebase
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    @Override
    public void onClick(View view) {

        String userName = getUserName();
        String emailAddress = getEmailAddress();
        String errorMessage = new String();

        if (isEmptyUserName(userName)) {
            errorMessage = getResources().getString(R.string.EMPTY_USER_NAME);
        }
        else {
            //check for valid user name and valid email email address
            if (isAlphanumericUserName(userName)) {
                if(isValidEmailAddress(emailAddress)){
                    errorMessage = getResources().getString(R.string.EMPTY_STRING);
                }
                else{
                    errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS);
                }
            }
            else{
                errorMessage = getResources().getString(R.string.NON_ALPHA_NUMERIC_USER_NAME);
            }
        }

        if (errorMessage.isEmpty()) {
            //no errors were found!
            //much of the business logic goes here!
            setStatusMessage(errorMessage);
        } else {
            setStatusMessage(errorMessage);
        }

        //below line is only for check (below line is taken from the firebase lab)
        //Toast.makeText(MainActivity.this,"Firebaseconnection success", Toast.LENGTH_LONG).show();
    }
}