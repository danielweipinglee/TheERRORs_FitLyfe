package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Weight_Tracking extends AppCompatActivity {
    //Variable creation
    private int pStatus = 0;
    private Handler handler = new Handler();
    private TextView tv;
    //Variables to change values of 3 progress bar and this weeks calorie consumptions

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    //Assign the default value to 55
    private long goalpercentage = 55;
    //Override the following function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get the Firebase Authenticator.
        mAuth = FirebaseAuth.getInstance();

        // Get the current user of the app.
        curUser = mAuth.getCurrentUser();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight__tracking);

        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.custom_progressbar_drawable, null);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        // Get the current user from the database.
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference user = database.child(curUser.getUid());

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get the text view that displays the percentage.
                tv = (TextView) findViewById(R.id.tv);

                // Set the progress circle and text to 0%.
                mProgress.setProgress(0);
                tv.setText("0%");

                // Get the user's current weight, starting weight, and goal weight from the database.
                Long startWeight = (Long) dataSnapshot.child("Goals").child("Weight").child("Start").getValue();
                Long goalWeight = (Long) dataSnapshot.child("Goals").child("Weight").child("Goal").getValue();
                Long curWeight = (Long) dataSnapshot.child("weight").getValue();

                // If all the variables were in the database.
                if(startWeight != null && goalWeight != null && curWeight != null){

                    // Calculate the percentage accomplished towards the goal.
                    goalpercentage = ((curWeight - startWeight) * 100) / (goalWeight - startWeight);

                    // Reset pStatus.
                    pStatus = 0;

                    // If goalpercentage is less than zero, no progress has been made.
                    if(goalpercentage < 0){
                        goalpercentage = 0;
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Indicates when the progress bar will stop
                            while (pStatus < goalpercentage) {
                                pStatus += 1;

                                handler.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        mProgress.setProgress(pStatus);
                                        tv.setText(pStatus + "%");

                                    }
                                });
                                try {
                                    // Sleep for 200 milliseconds.
                                    // Just to display the progress slowly
                                    Thread.sleep(16); //thread will take approx 1.5 seconds to finish
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final ProgressBar cProgress = (ProgressBar) findViewById(R.id.currentProgress);
        final ProgressBar pProgress = (ProgressBar) findViewById(R.id.previousProgress);
        final TextView weightCount = (TextView) findViewById(R.id.avgWeight);

        // Get the weight data for the current user for today.
        DatabaseReference weight = user.child("weight");

        // Get the total amount of sleep for today.
        weight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Create a variable called totalWeight to track the total weight
                Long totalWeight = (Long) dataSnapshot.getValue();
                // Set the text on its user interface
                // Set the top text with the total amount of sleep.
                weightCount.setText("" + totalWeight + " lbs");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                weightCount.setText("0 lbs");
            }
        });
    }

    //Links this xml file with the Menu xml file so that all pages will have the same menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    //Method functionality for action bar
    public boolean onOptionsItemSelected(MenuItem item){

        //Gets the id of the button that was pressed
        int id = item.getItemId();

        //Back Button for user to click
        if(id == android.R.id.home){
            this.finish();
        }

        //Add Button for user to click
        if(id == R.id.Add){
            Intent intent = new Intent(Weight_Tracking.this, Weight_Input.class);
            startActivity(intent);

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
