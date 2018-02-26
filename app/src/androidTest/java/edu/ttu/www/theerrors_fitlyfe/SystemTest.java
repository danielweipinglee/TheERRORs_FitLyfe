package edu.ttu.www.theerrors_fitlyfe;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Full system tests (to be done when we have a full system)
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SystemTest {

    /*
     * Trivial example test: Make sure pressing the 'go to login' button starts
     * the login activity
     */
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // Stand up main activity
        ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);
        MainActivity activity = rule.getActivity();

        // Stand up login activity monitor
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, true);

        // Push the button
        Button button = (Button) activity.findViewById(R.id.goto_login_button);
        button.performClick();

        // See if the the activity started
        Activity res = am.waitForActivityWithTimeout(1000);
        assertEquals(1, am.getHits());
    }
}
