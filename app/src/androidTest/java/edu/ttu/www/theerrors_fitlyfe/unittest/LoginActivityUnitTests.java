package edu.ttu.www.theerrors_fitlyfe.unittest;

/**
 * Unit tests for the login activity
 */

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.LoginActivity;
import edu.ttu.www.theerrors_fitlyfe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;

public class LoginActivityUnitTests {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);

    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {
        onView(ViewMatchers.withId(R.id.sign_in_button)).check(matches(withText("Sign in")));
        onView(withId(R.id.register_button)).check(matches(withText("Register")));
    }
}