package edu.ttu.www.theerrors_fitlyfe;

/**
 * Created by Daniel on 2/15/18.
 */

public class TermandConditionActivity {

    public void goToMain(View v) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }
}