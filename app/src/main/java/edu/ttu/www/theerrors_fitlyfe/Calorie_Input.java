package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class Calorie_Input extends AppCompatActivity {


    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie__input);


        ImageButton advance = (ImageButton) findViewById(R.id.Back);
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calorie_Input.this, Calorie_Consumption.class);
                startActivity(intent);

            }
        });

        Button Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log();
            }
        });













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

        // Use CalorieInt, FoodName, and SugarInt to add to database

    }
}
