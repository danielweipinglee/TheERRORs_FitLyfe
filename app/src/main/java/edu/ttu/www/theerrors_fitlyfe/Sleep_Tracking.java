package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Sleep_Tracking extends AppCompatActivity {
    private int pStatus = 0;
    private Handler handler = new Handler();
    private TextView tv;

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;

    private int goalpercentage = 55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep__tracking);


        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the drawable progress bars.
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.custom_progressbar_drawable, null);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        tv = (TextView) findViewById(R.id.tv);
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

        // Get the Firebase Authenticator.
        mAuth = FirebaseAuth.getInstance();

        // Get the current user of the app.
        curUser = mAuth.getCurrentUser();

        //Code to change values of both progress bars and what the this weeks sleep is
        final ProgressBar cProgress = (ProgressBar) findViewById(R.id.currentProgress);
        final ProgressBar pProgress = (ProgressBar) findViewById(R.id.previousProgress);
        final TextView sleepcount = (TextView) findViewById(R.id.avgSleep);

        // Get a calendar object.
        Calendar calendar = Calendar.getInstance();

        // Get the current date and time.
        Date today = calendar.getTime();

        // Get just the current date as a String.
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String todaySting = df.format(today);

        // Get yesterday's date and time.
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();

        // Get just yesterday's date as a String.
        String yesterdayString = df.format(yesterday);

        // Get the sleep data for the current user for today.
        DatabaseReference user = FirebaseDatabase.getInstance().getReference().child(curUser.getUid());
        DatabaseReference sleep = user.child("Sleep").child(todaySting);

        // Get the total amount of sleep for today.
        sleep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long totalSleep = 0;

                for(DataSnapshot childData : dataSnapshot.getChildren()){
                    Long data = (Long) childData.getValue();
                    if(data != null){
                        totalSleep += data;
                    }
                }

                // Set the progress on the current progress bar.
                cProgress.setProgress((int) ((totalSleep * 100) / 8));

                // Set the top text with the total amount of sleep.
                sleepcount.setText("" + totalSleep + " hours");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cProgress.setProgress(0);
                sleepcount.setText("0 hours");
            }
        });

        // Get the sleep data for the current user for yesterday.
        sleep = user.child("Sleep").child(yesterdayString);

        // Get the total amount of sleep for yesterday.
        sleep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long totalSleep = 0;

                for(DataSnapshot childData : dataSnapshot.getChildren()){
                    Long data = (Long) childData.getValue();
                    if(data != null){
                        totalSleep += data;
                    }
                }

                // Set the progress on the current progress bar.
                pProgress.setProgress((int) ((totalSleep * 100) / 8));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pProgress.setProgress(0);
            }
        });
    }

    //Links this xml file with the Menu xml file so that all pages will have the same menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    //Method functionality for action buttons
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        //Back Button
        if(id == android.R.id.home){
            this.finish();
        }

        //Add Button
        if(id == R.id.Add){
            Intent intent = new Intent(Sleep_Tracking.this, Sleep_Input.class);
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
