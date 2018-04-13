package edu.ttu.www.theerrors_fitlyfe.unittest;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.Exercise_Input;
import edu.ttu.www.theerrors_fitlyfe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Unit tests for the ExerciseInput activity
 */

public class ExerciseInputUnitTest {

    @Rule
    public ActivityTestRule<Exercise_Input> activityRule =
            new ActivityTestRule<>(Exercise_Input.class);

    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {
        onView(ViewMatchers.withId(R.id.workoutname)).check(matches(withHint("What did you do?")));
        onView(withId(R.id.exercise)).check(matches(withHint("Enter workout length")));
        onView(withId(R.id.submit)).check(matches(withText("Submit")));

    }
}
