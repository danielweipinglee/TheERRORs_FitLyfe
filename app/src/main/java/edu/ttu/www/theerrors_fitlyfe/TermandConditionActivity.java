package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Daniel on 2/15/18.
 */

public class TermandConditionActivity extends AppCompatActivity {

    public void goToMain(View v) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }
}