package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GoalSetting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*
    Use variable "goaltype" when deciding what kind of goal to store the value as
     */
    private FirebaseAuth mAuth;
    public FirebaseUser curUser;

    String goaltype;

    public Spinner spinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);

        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initializes the spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.goal_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        mAuth = FirebaseAuth.getInstance();

        // Get the current user that is logged in, if there is one.
        curUser = mAuth.getCurrentUser();

        // If there is no current user logged in, then send them to the login page.
        if(curUser == null) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    /*
    When an item is selected it will grab the text and store it in a variable so we will know
    what goal the user is wanting to set
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        //gets the string that is selected in the dropdown
        goaltype = parent.getItemAtPosition(position).toString();
    }

    /*
    Will default to first choice which is "Blood Sugar" if nothing is selected in the dropdown menu
     */
    public void onNothingSelected(AdapterView<?> adapterView) {
        goaltype = "Blood Sugar";
    }

    /*
    Added part of the log functionality but did not add any database things
    I did not add date and time to this since I did not think we needed it for the goals
     */
    private void log(){
        //Should use GoalInt
        EditText goal = (EditText) findViewById(R.id.goalVal);
        String GoalS = goal.getText().toString();
        float GoalInt = Float.parseFloat(GoalS);
        goal.setText("");

        String goalType = spinner.getSelectedItem().toString();

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference goals = database.child(curUser.getUid()).child("Goals");

        switch(goalType){
            case "Blood Sugar":
                goals.child("Blood Sugar").child("Goal").setValue(GoalInt);
                break;
            case "Blood Pressure":
                goals.child("Blood Pressure").child("Goal").setValue(GoalInt);
                break;
            case "Calories":
                goals.child("Calories").child("Goal").setValue(GoalInt);
                break;
            case "Exercise":
                goals.child("Exercise").child("Goal").setValue(GoalInt);
                break;
            case "Sleep":
                goals.child("Sleep").child("Goal").setValue(GoalInt);
                break;
            case "Water":
                goals.child("Water").child("Goal").setValue(GoalInt);
                break;
            case "Weight":
                goals.child("Weight").child("Goal").setValue(GoalInt);

                database.child(curUser.getUid()).child("weight").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Long weight = (Long) dataSnapshot.getValue();
                        if(weight != null){
                            goals.child("Weight").child("Start").setValue(weight);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                break;
        }

        finish();
    }

    //Links this xml file with the Menu xml file so that all pages will have the same menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inputmenu, menu);
        return true;
    }

    //Method functionality for action button
    public boolean onOptionsItemSelected(MenuItem item){
        //Gets the id of the button that was pressed
        int id = item.getItemId();

        //Back Button
        if(id == android.R.id.home){
            this.finish();
        }

        //Sign out Button
        if(id == R.id.sign_out) {
            mAuth.signOut();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
