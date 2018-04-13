package edu.ttu.www.theerrors_fitlyfe.integrationtest;

import android.support.test.espresso.intent.rule.*;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.ttu.www.theerrors_fitlyfe.Calorie_Consumption;
import edu.ttu.www.theerrors_fitlyfe.MainActivity;
import edu.ttu.www.theerrors_fitlyfe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Integration tests for the Main Activity.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Create activity instance
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Rule
    public IntentsTestRule<Calorie_Consumption> intentsTestRule =
            new IntentsTestRule<>(Calorie_Consumption.class);
/*
    // Login before running each test
    @Before
    public void logMeIn() {
        // We might need to login and accept T&C
        if (getCurrentActivity().getLocalClassName() == "LoginActivity") {
            String dummyEmail = new String("testyboy@gmail.com");
            String dummyPassword = new String("password");

            // Sign in with test account
            onView(withId(R.id.email)).perform(typeText(dummyEmail));
            onView(withId(R.id.password)).perform(typeText(dummyPassword));
            onView(withId(R.id.sign_in_button)).perform(click());

            if (getCurrentActivity().getLocalClassName() == "TermandConditionActivity") {
                // Accept EULA
                onView(withId(R.id.acceptCheckBox)).perform(click());
                onView(withId(R.id.continueButton)).perform(click());
            }
        }
    }
*/
    /*
     * Ensure each button launches the correct intent
     */
    @Test
    public void calorieConsumptionIntentTest() {
        // Click calorie consumption button
        onView(ViewMatchers.withId(R.id.calorie_consumption_button))
                .perform(click());

        // Make sure the calorie consumption page is launched
        Intents.intended(
                hasComponent("edu.ttu.www.theerrors_fitlyfe.Calorie_Consumption"));
    }

    @Test
    public void waterConsumptionIntentTest() {
        // Click water consumption button
        onView(withId(R.id.water_consumption_button))
                .perform(click());

        // Make sure the water consumption page is launched
        Intents.intended(
                hasComponent("edu.ttu.www.theerrors_fitlyfe.Water_Consumption"));
    }

    @Test
    public void exerciseLogIntentTest() {
        // Click exercise log button
        onView(withId(R.id.exercise_log_button))
                .perform(click());

        // Make sure the exercise log page is launched
        Intents.intended(
                hasComponent("edu.ttu.www.theerrors_fitlyfe.Exercise_log"));
    }

    @Test
    public void sleepTrackingIntentTest() {
        // Click sleep tracking button
        onView(withId(R.id.sleep_tracking_button))
                .perform(click());

        // Make sure the sleep tracking page is launched
        Intents.intended(
                hasComponent("edu.ttu.www.theerrors_fitlyfe.Sleep_Tracking"));
    }
}
