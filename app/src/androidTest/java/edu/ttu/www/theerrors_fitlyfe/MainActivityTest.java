package edu.ttu.www.theerrors_fitlyfe;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.*;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.*;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

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
     * Ensure each button launches the correct intent
     */
    @Test
    public void calorieConsumptionIntentTest() {
        // Click calorie consumption button
        onView(withId(R.id.calorie_consumption_button))
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
        onView(withId(R.id.calorie_consumption_button))
                .perform(click());

        // Make sure the exercise log page is launched
        Intents.intended(
                hasComponent("edu.ttu.www.theerrors_fitlyfe.Exercise_log"));
    }

    @Test
    public void sleepTrackingIntentTest() {
        // Click sleep tracking button
        onView(withId(R.id.calorie_consumption_button))
                .perform(click());

        // Make sure the sleep tracking page is launched
        Intents.intended(
                hasComponent("edu.ttu.www.theerrors_fitlyfe.Sleep_Tracking"));
    }
}
