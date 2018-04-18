package edu.ttu.www.theerrors_fitlyfe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class GoalSetting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    /*
    Use variable "goaltype" when deciding what kind of goal to store the value as
     */
    String goaltype;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);

        //initializes the spinner
        Spinner spinner = findViewById(R.id.spinner);
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

        finish();
    }
}
