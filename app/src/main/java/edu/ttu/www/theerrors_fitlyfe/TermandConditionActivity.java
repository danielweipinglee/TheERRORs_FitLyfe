package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Daniel on 2/15/18.
 */

public class TermandConditionActivity extends AppCompatActivity{

    public void goToMain(View v) {
        Intent intent = new Intent (getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}