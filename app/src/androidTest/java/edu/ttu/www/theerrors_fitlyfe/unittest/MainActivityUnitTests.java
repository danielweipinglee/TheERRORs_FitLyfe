package edu.ttu.www.theerrors_fitlyfe.unittest;

/**
 * Unit tests for the main activity
 */

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.MainActivity;
import edu.ttu.www.theerrors_fitlyfe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityUnitTests {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {
        onView(ViewMatchers.withId(R.id.calorie_consumption_button)).check(matches(withText("Calorie Consumption")));
        onView(withId(R.id.water_consumption_button)).check(matches(withText("Water Consumption")));
        onView(withId(R.id.exercise_log_button)).check(matches(withText("Exercise Log")));
        onView(withId(R.id.sleep_tracking_button)).check(matches(withText("Sleep Tracking")));
    }
}
