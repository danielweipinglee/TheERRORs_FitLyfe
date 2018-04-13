package edu.ttu.www.theerrors_fitlyfe.integrationtest;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.R;
import edu.ttu.www.theerrors_fitlyfe.Water_Consumption;
import edu.ttu.www.theerrors_fitlyfe.Water_Input;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Integration tests for WaterConsumption and WaterInput activities
 */

public class WaterConsumptionTest {

    @Rule
    public IntentsTestRule<Water_Consumption> activityRule =
            new IntentsTestRule<>(Water_Consumption.class);

    /*
     * Verify that clicking the add button launches the sleep input activity
     */
    @Test
    public void intentTest() {

        // Click the add button
        onView(ViewMatchers.withId(R.id.Add)).perform(click());

        // Make sure the appropriate page launches
        Intents.intended(hasComponent(
                Water_Input.class.getName()));
    }

    /*
     * Verify that the GUI updates when waters are logged
     * TODO: Uncomment when functionality exists
     */
    /**
    @Test
    public void guiUpdateTest() {

        // Stash the current GUI values
        final ProgressBar currentProgressBar =
                activityRule.getActivity().findViewById(R.id.currentProgress);
        final ProgressBar previousProgressBar =
                activityRule.getActivity().findViewById(R.id.previousProgress);
        final TextView waterCountView =
                activityRule.getActivity().findViewById(R.id.waterCount);

        final int currentProgressInital = currentProgressBar.getProgress();
        final int previousProgressInitial = previousProgressBar.getProgress();
        final int waterCountInitial = Integer.parseInt((String) waterCountView.getText());

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Add water
        onView(withId(R.id.water)).perform(typeText("1000"));
        onView(withId(R.id.submit)).perform(click());

        // See if the GUI has changed
        final int currentProgressNew = currentProgressBar.getProgress();
        final int previousProgressNew = previousProgressBar.getProgress();
        final int waterCountNew = Integer.parseInt((String) waterCountView.getText());

        Assert.assertEquals(currentProgressNew, currentProgressInital + 1000);
        Assert.assertEquals(previousProgressNew, previousProgressInitial);
        Assert.assertEquals(waterCountNew, waterCountInitial + 1000);
    }
    /**/
}
