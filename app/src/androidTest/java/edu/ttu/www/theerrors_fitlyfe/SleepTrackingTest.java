package edu.ttu.www.theerrors_fitlyfe;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Integration tests for the SleepTracking and SleepInput activities
 */

public class SleepTrackingTest {

    @Rule
    public ActivityTestRule<Calorie_Consumption> activityRule =
            new ActivityTestRule<>(Calorie_Consumption.class);

    /*
     * Verify that clicking the add button launches the sleep input activity
     */
    @Test
    public void intentTest() {

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Make sure the appropriate page launches
        Intents.intended(hasComponent(
                "package edu.ttu.www.theerrors_fitlyfe.Sleep_Input"));
    }

    /*
     * Verify that the GUI updates when sleeps are logged
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
        final TextView sleepCountView =
                activityRule.getActivity().findViewById(R.id.avgSleep);

        final int currentProgressInital = currentProgressBar.getProgress();
        final int previousProgressInitial = previousProgressBar.getProgress();
        final int sleepCountInitial = Integer.parseInt((String) sleepCountView.getText());

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Add sleep
        onView(withId(R.id.sleep)).perform(typeText("10"));
        onView(withId(R.id.submit)).perform(click());

        // See if the GUI has changed
        final int currentProgressNew = currentProgressBar.getProgress();
        final int previousProgressNew = previousProgressBar.getProgress();
        final int sleepCountNew = Integer.parseInt((String) sleepCountView.getText());

        Assert.assertEquals(currentProgressNew, currentProgressInital + 10);
        Assert.assertEquals(previousProgressNew, previousProgressInitial);
        Assert.assertEquals(sleepCountNew, sleepCountInitial + 10);
    }
    /**/
}
