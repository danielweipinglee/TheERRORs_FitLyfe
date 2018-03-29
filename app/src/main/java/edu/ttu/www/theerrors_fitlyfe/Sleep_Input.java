package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class Sleep_Input extends AppCompatActivity {


    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep__input);


        ImageButton advance = (ImageButton) findViewById(R.id.Back);
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sleep_Input.this, Sleep_Tracking.class);
                startActivity(intent);

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

    }
}
