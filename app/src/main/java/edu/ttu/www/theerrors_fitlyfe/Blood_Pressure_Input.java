package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Blood_Pressure_Input extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood__pressure__input);

        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();

        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Button Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log();
            }
        });

    }
    private void Log(){

        float PressureInt;
        EditText Pressure = (EditText) findViewById(R.id.bloodpressure);

        if (Pressure != null && Pressure.length() > 0) {
            String PressureS = Pressure.getText().toString();
            PressureInt = Float.parseFloat(PressureS);
            Pressure.setText("");
            //Use SleptInt

            // Get the current date and time.
            Date c = Calendar.getInstance().getTime();

            // Get just the current date.
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            String dateString = df.format(c);

            // Get just the current time.
            SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss", Locale.US);
            String timeString = tf.format(c);

            // Get the spot to add the entry to location /<userID>/Sleep/<current date>/<current time>
            DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child(curUser.getUid());
            DatabaseReference BPressureEntry = userDB.child("Blood Pressure").child(dateString).child(timeString);

            // Save the entry.
            BPressureEntry.setValue(PressureInt);

            // Finish the activity and return to the last activity.
            finish();
        }
    }

    //Links this xml file with the Menu xml file so that all pages will have the same menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inputmenu, menu);
        return true;
    }
    //Method functionality for action buttons
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
