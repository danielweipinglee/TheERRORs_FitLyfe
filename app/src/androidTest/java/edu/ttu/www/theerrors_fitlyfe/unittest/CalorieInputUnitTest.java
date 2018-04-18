package edu.ttu.www.theerrors_fitlyfe.unittest;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.Calorie_Input;
import edu.ttu.www.theerrors_fitlyfe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Unit tests for the CalorieInput activity
 */

public class CalorieInputUnitTest {

    @Rule
    public ActivityTestRule<Calorie_Input> activityRule =
            new ActivityTestRule<>(Calorie_Input.class);

    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {
        onView(ViewMatchers.withId(R.id.foodname)).check(matches(withHint("Enter Food Name")));
        onView(withId(R.id.sugar)).check(matches(withHint("Enter Sugar (g)")));
        onView(withId(R.id.calories)).check(matches(withHint("Enter Calories")));
        onView(withId(R.id.submit)).check(matches(withText("Submit")));

    }
}
