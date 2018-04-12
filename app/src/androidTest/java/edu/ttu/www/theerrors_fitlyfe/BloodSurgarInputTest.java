package edu.ttu.www.theerrors_fitlyfe;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Integration tests for the Blood sugar input activity
 */

public class BloodSurgarInputTest {

    @Rule
    public ActivityTestRule<BloodSurgar_Input> activityRule =
            new ActivityTestRule<>(BloodSurgar_Input.class);

    /*
     * Ensure blood sugar entry works properly
     */
    @Test
    public void dataEntryTest() {
        // Enter data and click button
        onView(withId(R.id.bloodsurgar)).perform(typeText("4.5"));
        onView(withId(R.id.submit)).perform(click());

        // Ensure activity completes
        assertTrue(activityRule.getActivity().isFinishing());
    }

    /*
     * Tests behavior when no data is entered for submission
     */
    @Test
    public void noDataTest() {

        // Click button without entering data
        onView(withId(R.id.submit)).perform(click());

        // Activity should not finish
        assertFalse(activityRule.getActivity().isFinishing());
    }
}
