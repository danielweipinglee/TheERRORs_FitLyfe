package edu.ttu.www.theerrors_fitlyfe;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Integration tests for the Main Activity.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    /*
     * Make sure buttons behave
     */
    public void UITest() {

        onView(withId(R.id.calorie_consumption_button)).perform(click()).check(matches(withText("Calorie Consumption")));
        onView(withId(R.id.water_consumption_button)).perform(click()).check(matches(withText("Water Consumption")));
        onView(withId(R.id.exercise_log_button)).perform(click()).check(matches(withText("Exercise Log")));
        onView(withId(R.id.sleep_tracking_button)).perform(click()).check(matches(withText("Sleep Tracking")));
    }

    /*
     * Make sure the appropriate activities are launched when certain buttons are clicked
     */
    @Test
    public void IntentTest() {
        // Stand up main activity
        ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);
        MainActivity activity = rule.getActivity();

        // Stand up login activity monitor
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, true);

        // Push the button
        Button button = (Button) activity.findViewById(R.id.goto_login);
        button.performClick();

        // See if the the activity started
        Activity res = am.waitForActivityWithTimeout(1000);
        assertEquals(1, am.getHits());
    }
}
