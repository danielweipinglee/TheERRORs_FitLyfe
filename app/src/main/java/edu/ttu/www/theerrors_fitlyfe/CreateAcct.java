package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateAcct extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    User user;
    private FirebaseAuth mAuth;
    EditText name, email, password, username, weight, height, gender, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);

        name = (EditText) findViewById(R.id.name_field);
        email = (EditText) findViewById(R.id.email_field);
        password = (EditText) findViewById(R.id.password_field);
        username = (EditText) findViewById(R.id.username_field);
        weight = (EditText) findViewById(R.id.weight_field);
        height = (EditText) findViewById(R.id.height_field);
        gender = (EditText) findViewById(R.id.gender_field);
        age = (EditText) findViewById(R.id.age_field);


        database=FirebaseDatabase.getInstance("User");
        ref = database.getReference();
        user = new User();
    }
private void getValues(){
        user.setName(name.getText().toString());
        user.setPassword(password.getText().toString());
        user.setUsername(username.getText().toString());
        user.setWeight(weight.getText().toString());
        user.setHeight(height.getText().toString());
        user.setAge(age.getText().toString());
        user.setGender(gender.getText().toString());
}

    /*
     * Go to the login screen on button click.
     */
    public void register(View v) {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ref.child("User02").setValue(user);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Intent intent = new Intent(getBaseContext(), TermandConditionActivity.class);
        startActivity(intent);
    }
}
