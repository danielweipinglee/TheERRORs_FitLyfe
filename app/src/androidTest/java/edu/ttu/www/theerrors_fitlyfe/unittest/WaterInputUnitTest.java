package edu.ttu.www.theerrors_fitlyfe.unittest;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.R;
import edu.ttu.www.theerrors_fitlyfe.Water_Input;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Unit tests for the WaterInput function
 */

public class WaterInputUnitTest {

    @Rule
    public ActivityTestRule<Water_Input> activityRule =
            new ActivityTestRule<>(Water_Input.class);

    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {
        onView(ViewMatchers.withId(R.id.water)).check(matches(withHint("Water (ml)")));
        onView(withId(R.id.submit)).check(matches(withText("Submit")));
    }
}
