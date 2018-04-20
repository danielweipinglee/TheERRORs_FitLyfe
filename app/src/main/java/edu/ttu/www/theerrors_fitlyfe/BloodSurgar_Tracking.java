package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.content.res.Resources;
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

//import com.google.firebase.auth.FirebaseAuth;

public class BloodSurgar_Tracking extends AppCompatActivity {
    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;
    //Variables to change values of 3 progress bar and this weeks calorie consumptions
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;

    int currentprogress = 25;
    int previousprogress = 50;
    int goalpercentage = 55;
    float bloodsurgar = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        //Needs a activity_blood_surgar_tracking
        setContentView(R.layout.activity_blood_surgar_input);



        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.custom_progressbar_drawable, null);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/


        tv = (TextView) findViewById(R.id.tv);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                //Indicates when the progress bar will stop
                while (pStatus < goalpercentage) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
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

        //Code to change values of both progress bars and what the this weeks calorie count is
        final ProgressBar cProgress = (ProgressBar) findViewById(R.id.currentProgress);
        final ProgressBar pProgress = (ProgressBar) findViewById(R.id.previousProgress);
        final TextView bloodsugarCount = (TextView) findViewById(R.id.bloodsurgar);


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
        DatabaseReference BloodSugar = user.child("Blood Sugar").child(todaySting);

        // Get the total amount of sleep for today.
        BloodSugar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long totalBloodSugar = 0;

                for(DataSnapshot childData : dataSnapshot.getChildren()){
                    Long data = (Long) childData.getValue();
                    if(data != null){
                        totalBloodSugar += data;
                    }
                }

                // Set the progress on the current progress bar.
                cProgress.setProgress((int) ((totalBloodSugar * 100) / 8));

                // Set the top text with the total amount of sleep.
                bloodsugarCount.setText("" + totalBloodSugar + " hours");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cProgress.setProgress(0);
                bloodsugarCount.setText("0 mool/L");
            }
        });

        // Get the sleep data for the current user for yesterday.
        BloodSugar = user.child("Blood Sugar").child(yesterdayString);

        // Get the total amount of sleep for yesterday.
        BloodSugar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long totalBloodSugar = 0;

                for(DataSnapshot childData : dataSnapshot.getChildren()){
                    Long data = (Long) childData.getValue();
                    if(data != null){
                        totalBloodSugar += data;
                    }
                }

                // Set the progress on the current progress bar.
                pProgress.setProgress((int) ((totalBloodSugar * 100) / 8));
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

    //Method functionality for action button
    public boolean onOptionsItemSelected(MenuItem item){

        //Gets the id of the button that was pressed
        int id = item.getItemId();

        //Back Button
        if(id == android.R.id.home){
            this.finish();
        }

        //Add Button
        if(id == R.id.Add){
            Intent intent = new Intent(BloodSurgar_Tracking.this, BloodSurgar_Input.class);
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
