package edu.ttu.www.theerrors_fitlyfe;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Integration tests for the Login Activity
 */

public class LoginActivityTest {

    // Create activity instance
    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule =
            new IntentsTestRule<>(LoginActivity.class);

    /*
     * Test login with existing account
     */
    @Test
    public void loginSuccessTest() {
        String dummyEmail = "potatoboy@gmail.com";
        String dummyPassword = "password";

        // Sign in with test account
        onView(withId(R.id.email)).perform(typeText(dummyEmail));
        onView(withId(R.id.password)).perform(typeText(dummyPassword));
        onView(withId(R.id.sign_in_button)).perform(click());

        // Successful login should end the activity
        assertTrue(activityRule.getActivity().isFinishing());
    }

    /*
     * Test login with existing account but incorrect password
     */
    @Test
    public void loginWrongPasswordTest() {
        String dummyEmail = new String("testyboy@gmail.com");
        String wrongPassword = new String("incorrectpassword");

        // Sign in with test account
        onView(withId(R.id.email)).perform(typeText(dummyEmail));
        onView(withId(R.id.password)).perform(typeText(wrongPassword));
        onView(withId(R.id.sign_in_button)).perform(click());

        // Activity should not finish
        assertFalse(activityRule.getActivity().isFinishing());
    }

    /*
    * Test login with unregistered account
    */
    @Test
    public void loginNoAccountTest() {
        String dummyEmail = new String("notregistered@gmail.com");
        String wrongPassword = new String("password");

        // Attempt sign in
        onView(withId(R.id.email)).perform(typeText(dummyEmail));
        onView(withId(R.id.password)).perform(typeText(wrongPassword));
        onView(withId(R.id.sign_in_button)).perform(click());

        // Activity should not finish
        assertFalse(activityRule.getActivity().isFinishing());
    }

    /*
     * Test new account registration
     */
    @Test
    public void registerTest() {
        // Click the register button
        onView(withId(R.id.register_button)).perform(click());

        // Check if Create Account page launched
        Intents.intended(hasComponent("edu.ttu.www.theerrors_fitlyfe.CreateAcct"));
    }

    /*
    * Make sure buttons behave
    */
    @Test
    public void UITest() {
        onView(withId(R.id.sign_in_button)).check(matches(withText("Sign In")));
        onView(withId(R.id.register_button)).check(matches(withText("Register")));
    }
}
