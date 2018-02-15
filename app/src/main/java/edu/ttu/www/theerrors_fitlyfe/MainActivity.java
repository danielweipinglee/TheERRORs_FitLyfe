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

public class MainActivity extends AppCompatActivity {

    Button Agreebutton;
    Button Disagreebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Agreebutton = (Button) findViewById(R.id.Agree);
        Disagreebutton = (Button) findViewById(R.id.Disagree);

        Agreebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AgreeIntent = new Intent ();
                startActivity(AgreeIntent);
            }
        });

        Disagreebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DisIntent = new Intent ();
                startActivity(DisIntent);
            }
        });

    }
    /*
     * Go to the login screen on button click.
     */
    public void goToLogin(View v) {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }



}
