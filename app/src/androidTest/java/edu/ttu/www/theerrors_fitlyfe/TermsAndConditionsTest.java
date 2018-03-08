package edu.ttu.www.theerrors_fitlyfe;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Integration tests for Terms and Conditions activity
 */

public class TermsAndConditionsTest {
    /*
     * Make sure UI behaves
     */
    @Test
    public void UITest() {
        onView(withId(R.id.agree_button)).perform(click()).check(matches(withText("I Agree")));
        onView(withId(R.id.disagree_button)).perform(click()).check(matches(withText("I Disagree")));
    }
}
