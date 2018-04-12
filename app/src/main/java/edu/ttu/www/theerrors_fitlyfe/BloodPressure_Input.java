/*package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BloodPressure_Input extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_input);

        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();

        ImageButton advance = (ImageButton) findViewById(R.id.Back);
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log();
            }
        });
//  Did not mess with below because I assume its for the database and just copied it over

/*      May be possible to display database in a list so that the user can see there calorie consumption
        logs.




        mListView = (ListView) findViewById(R.id.calorielist);
// 1
        final ArrayList<Calories> recipeList = Calories.getRecipesFromFile("recipes.json", this);
// 2
        String[] listItems = new String[recipeList.size()];
// 3
        for(int i = 0; i < recipeList.size(); i++){
            Calories calorie = recipeList.get(i);
            listItems[i] = recipe.title;
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
        */
  /*  }
    private void Log(){


        EditText Pressure = (EditText) findViewById(R.id.bloodPressure);
        String PressureS = Pressure.getText().toString();
        float PressureInt = Float.parseFloat(PressureS);
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
*/