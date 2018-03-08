package edu.ttu.www.theerrors_fitlyfe;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Integration tests for the Login Activity
 */

public class LoginActivityTest {
    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {

        onView(withId(R.id.sign_in_button)).perform(click()).check(matches(withText("Sign In")));
        onView(withId(R.id.register_button)).perform(click()).check(matches(withText("Register")));
    }
}
