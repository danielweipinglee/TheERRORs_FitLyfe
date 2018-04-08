package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateAcct extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private EditText name, email, password, confirmPassword,
            username, weight, height, gender, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);

        // Get the Firebase authentifier.
        mAuth = FirebaseAuth.getInstance();

        // Get all the inputs from the user.
        email = (EditText) findViewById(R.id.email_field);
        password = (EditText) findViewById(R.id.password_field);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_field);
        username = (EditText) findViewById(R.id.username_field);
        name = (EditText) findViewById(R.id.name_field);
        age = (EditText) findViewById(R.id.age_field);
        height = (EditText) findViewById(R.id.height_field);
        weight = (EditText) findViewById(R.id.weight_field);
        gender = (EditText) findViewById(R.id.gender_field);
    }

    /* addUserToDatabase tries to add the user's information to the Firebase database.
     */
    public void addUserToDatabase(){

        // Get a reference to the root of the Firebase database.
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        // Create the new child node in the database for the user, using the unique id generated
        // by the Firebase authentifier.
        DatabaseReference child = reference.child(curUser.getUid());

        // Put all the data to be input into a HashMap.
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("email", email.getText().toString());
        user.put("username", username.getText().toString());
        user.put("name", name.getText().toString());
        user.put("age", age.getText().toString());
        user.put("height", height.getText().toString());
        user.put("weight", weight.getText().toString());
        user.put("gender", gender.getText().toString());

        // Put the value into the database.
        child.setValue(user);
    }

    /* checkInputs checks the inputs from the user and determines whether they are valid.
     */
    public boolean checkInputs(){

        // inputOk is true until an invalid input is found.
        boolean inputOK = true;

        // If email is not entered or is not in the form of an email, the email input is invalid.
        if(TextUtils.isEmpty(email.getText())){
            email.setError("Email is required.");
            inputOK = false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
            email.setError("This email address is not a valid email.");
            inputOK = false;
        }

        // If the password or confirmation password is not entered, the password is not at least
        // 6 characters long, or the password and the confirmation password do not match,
        // the password and confirmation password is invalid.
        if(TextUtils.isEmpty(password.getText())){
            password.setError("Password is required.");
            inputOK = false;
        }
        else if(password.getText().toString().length() < 6){
            password.setError("The password must be at least 6 characters long");
            inputOK = false;
        }
        else if(TextUtils.isEmpty(confirmPassword.getText())){
            confirmPassword.setError("Confirm password is required.");
            inputOK = false;
        }
        else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            password.setError("Password does not match confirmation password.");
            password.setText("");
            confirmPassword.setText("");
            inputOK = false;
        }

        // If the username is not entered, then the username is invalid.
        if(TextUtils.isEmpty(username.getText())){
            username.setError("Username is required.");
            inputOK = false;
        }

        // If the name is not entered, then the name is invalid.
        if(TextUtils.isEmpty(name.getText())){
            name.setError("Name is required.");
            inputOK = false;
        }

        // If the age is not entered, the age is less than 18, or the age is over 150,
        // then the age is invalid.
        if(TextUtils.isEmpty(age.getText())){
            age.setError("Age is required.");
            inputOK = false;
        }
        else if(Integer.parseInt(age.getText().toString()) < 18){
            age.setError("You must be over 18 to use FitLyfe.");
            inputOK = false;
        }
        else if(Integer.parseInt(age.getText().toString()) > 150){
            age.setError("150 years old is not a valid age.");
            inputOK = false;
        }

        // If the height is not entered, the height is less than 2.0, or the height is greater
        // than 8.0, then the height is invalid.
        if(TextUtils.isEmpty(height.getText())){
            height.setError("Height is required.");
            inputOK = false;
        }
        else if(Double.parseDouble(height.getText().toString()) < 2.0){
            height.setError("2 feet is not a valid height.");
            inputOK = false;
        }
        else if(Double.parseDouble(height.getText().toString()) > 8.0){
            height.setError("8 feet is not a valid height.");
            inputOK = false;
        }

        // If the weight is not entered, the weight is less than 50, or the weight is
        // greater than 700 then the weight is invalid.
        if(TextUtils.isEmpty(weight.getText())){
            weight.setError("Weight is required.");
            inputOK = false;
        }
        else if(Integer.parseInt(weight.getText().toString()) < 50){
            weight.setError("50 pounds is not a valid weight.");
            inputOK = false;
        }
        else if(Integer.parseInt(weight.getText().toString()) > 700){
            weight.setError("700 pounds is not a valid weight.");
            inputOK = false;
        }

        // If the gender is not entered, then the gender is invalid.
        if(TextUtils.isEmpty(gender.getText())){
            gender.setError("Gender password is required.");
            inputOK = false;
        }

        return inputOK;
    }

    /* register tries to register a new user.
     */
    public void register(View v) {
        // Check to make sure the input is valid.
        boolean inputOK = checkInputs();

        if(inputOK) {

            // Create a new user with the Firebase authenticator.
            mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                    password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                /* onCompute gets called when Firebase is done trying to create a new user.
                 */
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        // Get the user that just got created.
                        curUser = mAuth.getCurrentUser();

                        // Add the user's information to the database.
                        addUserToDatabase();

                        // Go to the terms and conditions page.
                        Intent intent = new Intent(getBaseContext(), TermandConditionActivity.class);
                        startActivity(intent);
                    }
                    else{
                        email.setError("This email address is already registered.");
                    }
                }
            });
        }
    }
}
