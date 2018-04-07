package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private EditText name, email, password, username, weight, height, gender, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email_field);
        password = (EditText) findViewById(R.id.password_field);
        username = (EditText) findViewById(R.id.username_field);
        name = (EditText) findViewById(R.id.name_field);
        age = (EditText) findViewById(R.id.age_field);
        height = (EditText) findViewById(R.id.height_field);
        weight = (EditText) findViewById(R.id.weight_field);
        gender = (EditText) findViewById(R.id.gender_field);
    }

    public void addUserToDatabase(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference child = reference.child(curUser.getUid());

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("email", email.getText().toString());
        user.put("username", username.getText().toString());
        user.put("name", name.getText().toString());
        user.put("age", age.getText().toString());
        user.put("height", height.getText().toString());
        user.put("weight", weight.getText().toString());
        user.put("gender", gender.getText().toString());

        child.setValue(user);
    }

    /*
     * Go to the login screen on button click.
     */
    public void register(View v) {

        mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()){
                    curUser = mAuth.getCurrentUser();

                    addUserToDatabase();

                    Intent intent = new Intent(getBaseContext(), TermandConditionActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CreateAcct.this,"Could not create account. Please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
