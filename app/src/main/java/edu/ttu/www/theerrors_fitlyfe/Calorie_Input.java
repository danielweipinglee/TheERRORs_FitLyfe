package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

public class Calorie_Input extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie__input);


        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();

        Button Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log();
            }
        });

        //Added back button to the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    }

    private void Log(){
        EditText Calories = (EditText) findViewById(R.id.calories);
        String CaloriesCount = Calories.getText().toString();
        // Enter CalorieInt into the database
        int CalorieInt = Integer.parseInt(CaloriesCount);
        Calories.setText("");

        EditText Name = (EditText) findViewById(R.id.foodname);
        String FoodName = Name.getText().toString();
        Name.setText("");

        EditText Sugar = (EditText) findViewById(R.id.sugar);
        String SugarCount = Sugar.getText().toString();
        int SugarInt = Integer.parseInt(SugarCount);
        Sugar.setText("");

        // Get the current date and time.
        Date c = Calendar.getInstance().getTime();

        // Get just the current date.
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String dateString = df.format(c);

        // Get just the current time.
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss", Locale.US);
        String timeString = tf.format(c);



        // Get the spot to add the entry to location /<userID>/Food/<current date>/<current time>
        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child(curUser.getUid());
        DatabaseReference calorieEntry = userDB.child("Food").child(dateString).child(timeString).child("Calories");
        DatabaseReference sugarEntry = userDB.child("Food").child(dateString).child(timeString).child("Sugar");
        DatabaseReference foodEntry = userDB.child("Food").child(dateString).child(timeString).child("Name");

        // Save the entry.
        calorieEntry.setValue(CalorieInt);
        sugarEntry.setValue(SugarInt);
        foodEntry.setValue(FoodName);

        // Finish the activity and return to the last activity.
        finish();




        // Use CalorieInt, FoodName, and SugarInt to add to database

    }

    //Method functionality for back button
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
