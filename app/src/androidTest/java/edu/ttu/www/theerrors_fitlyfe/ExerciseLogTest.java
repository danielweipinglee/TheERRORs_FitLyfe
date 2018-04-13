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
 * Integration tests for the ExerciseLog and ExerciseInput activities
 */

public class ExerciseLogTest {

    @Rule
    public IntentsTestRule<Exercise_log> activityRule =
            new IntentsTestRule<>(Exercise_log.class);

    /*
     * Verify that clicking the add button launches the exercise input activity
     */
    @Test
    public void intentTest() {

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Make sure the appropriate page launches
        Intents.intended(hasComponent(
                Exercise_Input.class.getName()));
    }

    /*
     * Verify that the GUI updates when exercises are logged
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
        final TextView exerciseCountView =
                activityRule.getActivity().findViewById(R.id.exerciseCount);

        final int currentProgressInital = currentProgressBar.getProgress();
        final int previousProgressInitial = previousProgressBar.getProgress();
        final int exerciseCountInitial = Integer.parseInt((String) exerciseCountView.getText());

        // Click the add button
        onView(withId(R.id.Add)).perform(click());

        // Add exercise
        onView(withId(R.id.workoutname)).perform(typeText("Power Cleans"));
        onView(withId(R.id.exercise)).perform(typeText("60"));
        onView(withId(R.id.submit)).perform(click());

        // See if the GUI has changed
        final int currentProgressNew = currentProgressBar.getProgress();
        final int previousProgressNew = previousProgressBar.getProgress();
        final int exerciseCountNew = Integer.parseInt((String) exerciseCountView.getText());

        Assert.assertEquals(currentProgressNew, currentProgressInital + 60);
        Assert.assertEquals(previousProgressNew, previousProgressInitial);
        Assert.assertEquals(exerciseCountNew, exerciseCountInitial + 60);
    }
    /**/
}
