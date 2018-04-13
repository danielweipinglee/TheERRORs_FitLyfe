package edu.ttu.www.theerrors_fitlyfe;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Integration tests for the CalorieConsumption and CalorieInput activities
 */

public class CalorieConsumptionTest {

    @Rule
    public IntentsTestRule<Calorie_Consumption> activityRule =
            new IntentsTestRule<>(Calorie_Consumption.class);

    /*
     * Verify that clicking the add button launches the calorie input activity
     */
    @Test
    public void intentTest() {

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Make sure the appropriate page launches
        Intents.intended(hasComponent(
                Calorie_Input.class.getName()));
    }

    /*
     * Verify that the GUI updates when calories are logged
     * TODO: Uncomment when functionality exists
     */
    /*
    @Test
    public void guiUpdateTest() {

        // Stash the current GUI values
        final ProgressBar currentProgressBar =
                activityRule.getActivity().findViewById(R.id.currentProgress);
        final ProgressBar previousProgressBar =
                activityRule.getActivity().findViewById(R.id.previousProgress);
        final TextView calorieCountView =
                activityRule.getActivity().findViewById(R.id.caloriecount);

        final int currentProgressInital = currentProgressBar.getProgress();
        final int previousProgressInitial = previousProgressBar.getProgress();
        final int calorieCountInitial = Integer.parseInt((String) calorieCountView.getText());

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Add candy
        onView(withId(R.id.foodname)).perform(typeText("Candy"));
        onView(withId(R.id.sugar)).perform(typeText("30"));
        onView(withId(R.id.calories)).perform(typeText("300"));
        onView(withId(R.id.submit)).perform(click());

        // See if the GUI has changed
        final int currentProgressNew = currentProgressBar.getProgress();
        final int previousProgressNew = previousProgressBar.getProgress();
        final int calorieCountNew = Integer.parseInt((String) calorieCountView.getText());

        Assert.assertEquals(currentProgressNew, currentProgressInital + 300);
        Assert.assertEquals(previousProgressNew, previousProgressInitial);
        Assert.assertEquals(calorieCountNew, calorieCountInitial + 300);
    }
    */
}
