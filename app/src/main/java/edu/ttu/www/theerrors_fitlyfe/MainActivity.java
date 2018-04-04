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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button advancetocalories = (Button) findViewById(R.id.calorie_consumption_button);
        advancetocalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calorie_Consumption.class);
                startActivity(intent);

            }
        });

        Button advancetowater = (Button) findViewById(R.id.water_consumption_button);
        advancetowater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = new Intent(MainActivity.this, Water_Consumption.class);
                startActivity(intents);

            }
        });

        Button advancetosleep = (Button) findViewById(R.id.sleep_tracking_button);
        advancetosleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sleep_Tracking.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    /*
     * Go to the login screen on button click.
     */
    public void goToLogin(View v) {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }

}
