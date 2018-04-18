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

public class Calorie_Consumption extends AppCompatActivity {

    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tv;
    //Variables to change values of 3 progress bar and this weeks calorie consumptions

    int currentprogress = 25;
    int previousprogress = 50;
    int goalpercentage = 55;
    int calories = 100;

    FirebaseAuth mAuth;
    FirebaseUser curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie__consumption);

        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Resources res = getResources();
    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.custom_progressbar_drawable, null);
    final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);

        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

    tv =(TextView)

    findViewById(R.id.tv);
        new

    Thread(new Runnable() {

        @Override
        public void run () {
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

        //Code to change values of both progress bars and what the this weeks sleep is
        final ProgressBar cProgress = (ProgressBar) findViewById(R.id.currentProgress);
        final ProgressBar pProgress = (ProgressBar) findViewById(R.id.previousProgress);
        final TextView calorieCount = (TextView) findViewById(R.id.calorieCount);

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
        DatabaseReference food = user.child("Food").child(todaySting);

        // Get the total amount of food for today.
        food.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long totalCalories = 0;

                for(DataSnapshot childData : dataSnapshot.getChildren()){
                    Long data = (Long) childData.child("Calories").getValue();
                    if(data != null){
                        totalCalories += data;
                    }
                }

                // Set the progress on the current progress bar.
                cProgress.setProgress((int) ((totalCalories*100)/2000));

                // Set the top text with the total amount of calories.
                calorieCount.setText("" + totalCalories + " calories");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cProgress.setProgress(0);
                calorieCount.setText("0 calories");
            }
        });

        // Get the food data for the current user for yesterday.
        food = user.child("Food").child(yesterdayString);

        // Get the total amount of food for yesterday.
        food.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long totalCalories = 0;

                for(DataSnapshot childData : dataSnapshot.getChildren()){
                    Long data = (Long) childData.child("Calories").getValue();
                    if(data != null){
                        totalCalories += data;
                    }
                }

                // Set the progress on the current progress bar.
                pProgress.setProgress((int) ((totalCalories*100)/2000));
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
        if(id == R.id.action_name){
            Intent intent = new Intent(Calorie_Consumption.this, Calorie_Input.class);
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
