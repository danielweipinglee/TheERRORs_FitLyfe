package edu.ttu.www.theerrors_fitlyfe.integrationtest;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.R;
import edu.ttu.www.theerrors_fitlyfe.TermandConditionActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Integration tests for Terms and Conditions activity
 */

public class TermsAndConditionsTest {

    // Create activity instance
    @Rule
    public ActivityTestRule<TermandConditionActivity> activityRule = new ActivityTestRule<>(
            TermandConditionActivity.class);

    /*
     * Make sure the user can proceed after accepting the terms and conditions
     */
    @Test
    public void acceptTest() {
        // Accept the T&C
        onView(ViewMatchers.withId(R.id.acceptCheckBox))
                .perform(click());
        onView(withId(R.id.continueButton))
                .perform(click());

        // Make sure activity finishes
        assertTrue(activityRule.getActivity().isFinishing());
    }

    /*
     * Make sure the user can not proceed without accepting the terms and conditions
     */
    @Test
    public void declineTest() {

        // Attempt to proceed without accepting the T&C
        onView(withId(R.id.continueButton))
                .perform(click());

        // Make sure activity isn't finished
        assertFalse(activityRule.getActivity().isFinishing());
    }
}
