package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public TextView name;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);

        mAuth = FirebaseAuth.getInstance();

        Button advancetocalories = (Button) findViewById(R.id.calorie_consumption_button);
        advancetocalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calorie_Consumption.class);
                startActivity(intent);

            }
        });

        Button advancetowater = (Button) findViewById(R.id.water_consumption_button);
        advancetowater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = new Intent(MainActivity.this, Water_Consumption.class);
                startActivity(intents);

            }
        });

        Button advancetosleep = (Button) findViewById(R.id.sleep_tracking_button);
        advancetosleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sleep_Tracking.class);
                startActivity(intent);

            }
        });

        Button advancetoexercise = (Button) findViewById(R.id.exercise_log_button);
        advancetoexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Exercise_log.class);
                startActivity(intent);

            }
        });

        Button advancetoweight = (Button) findViewById(R.id.BMI_button);
        advancetoweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Weight_Tracking.class);
                startActivity(intent);

            }
        });

        Button advancetoblood = (Button) findViewById(R.id.blood_pressure_button);
        advancetoblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BloodSurgar_Tracking.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        // Get the current user that is logged in, if there is one.
        FirebaseUser curUser = mAuth.getCurrentUser();

        // If there is no current user logged in, then send them to the login page.
        if(curUser == null) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        else {

            DatabaseReference name = FirebaseDatabase.getInstance().getReference(curUser.getUid());
            name.addValueEventListener(new ValueEventListener() {

                private TextView name = findViewById(R.id.name);

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = (String) dataSnapshot.child("name").getValue();

                    if(data != null) {
                        name.setText(data);
                    }
                    else{
                        name.setText("John Smith");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    name.setText("John Smith");
                }
            });

            // Get the database for the current user.
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userDB = databaseReference.child(curUser.getUid());

            // Check to see if the terms and conditions were accepted.
            userDB.child("TermsAndConditionsAccepted").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // If the terms and conditions weren't accepted, then send the user to the
                    // terms and conditions page.
                    if(dataSnapshot.getValue() == null){
                        Intent intent = new Intent(getBaseContext(), TermandConditionActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("CHILD VALUE :: ", databaseError.toString());
                }
            });

        }

    }
    //Links this xml file with the Menu xml file so that all pages will have the same menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inputmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Sign out Button
        if(id == R.id.sign_out) {
            mAuth.signOut();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
