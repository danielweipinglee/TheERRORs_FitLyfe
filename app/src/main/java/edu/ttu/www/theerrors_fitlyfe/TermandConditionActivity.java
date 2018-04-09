package edu.ttu.www.theerrors_fitlyfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Daniel on 2/19/18.
 */

public class TermandConditionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private CheckBox accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termandcondition);

        // Get the Firebase authentifier.
        mAuth = FirebaseAuth.getInstance();

        // Get the user that just got created.
        curUser = mAuth.getCurrentUser();

        // Get the accept CheckBox.
        accept = (CheckBox) findViewById(R.id.acceptCheckBox);
    }

    /* accept accepts the terms and conditions for the user if they have accepted them.
     */
    public void accept(View v) {
        // If the terms and conditions were accepted by the user:
        if(accept.isChecked()) {
            // Get a reference to the root of the Firebase database.
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            // Create the new child node in the database for the user, using the unique id generated
            // by the Firebase authentifier.
            DatabaseReference child = reference.child(curUser.getUid()).child("TermsAndConditionsAccepted");

            // Set that the terms and conditions were accepted.
            child.setValue(true);

            // Finish the activity.
            finish();
        }
        else{
            accept.setError("Terms and conditions must be accepted.");
        }
    }

}
