package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAcct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);
    }

    /*
     * Go to the login screen on button click.
     */
    public void register(View v) {

        //Get user info and put it into the database.

        Intent intent = new Intent(getBaseContext(), TermandConditionActivity.class);
        startActivity(intent);
    }
}
