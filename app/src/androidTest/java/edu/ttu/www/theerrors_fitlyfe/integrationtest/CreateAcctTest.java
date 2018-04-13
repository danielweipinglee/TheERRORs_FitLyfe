package edu.ttu.www.theerrors_fitlyfe.integrationtest;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import edu.ttu.www.theerrors_fitlyfe.CreateAcct;
import edu.ttu.www.theerrors_fitlyfe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;

/*
 * Integration tests for the Create Account Activity
 */

public class CreateAcctTest {

    // Create activity instance
    @Rule
    public ActivityTestRule<CreateAcct> activityRule = new ActivityTestRule<>(
            CreateAcct.class);

    /*
     * Test successful account creation
     */
    @Test
    public void successTest() {

    }

    /*
     * Test attempted creation of existing account
     */
    @Test
    public void existingAccountTest() {
        String existingName = "Ryan Faulkenberry";
        String existingUname = "PotatoBoyTellEm";
        String existingPassword = "password";
        String existingEmail = "potatoboy@gmail.com";
        String existingAge = "21";
        String existingWeight = "150";
        String existingHeight = "6";
        String existingGender = "Male";

        // Fill out form with existing credentials and attempt account creation
        onView(ViewMatchers.withId(R.id.name_field)).perform(typeText(existingName));
        onView(withId(R.id.username_field)).perform(typeText(existingUname));
        onView(withId(R.id.password_field)).perform(typeText(existingPassword));
        onView(withId(R.id.confirm_password_field)).perform(typeText(existingPassword));
        onView(withId(R.id.email_field)).perform(typeText(existingEmail));
        onView(withId(R.id.age_field)).perform(typeText(existingAge));
        onView(withId(R.id.weight_field)).perform(typeText(existingWeight))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.height_field)).perform(typeText(existingHeight))
                .perform(closeSoftKeyboard()); // Keyboard gets in the way of next click
        onView(withId(R.id.gender_field)).perform(typeText(existingGender))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.create_account_button)).perform(click());
        
        // Activity should not finish
        assertFalse(activityRule.getActivity().isFinishing());
    }
    
    /*
     * Test behavior when field left blank
     */
    @Test
    public void blankFieldTest() {
        String name = "Ryan Faulkenberry";
        String uname = "PotatoBoyTellEm";
        String password = "password";
        String email = "potatoboy@gmail.com";
        String age = "21";
        String weight = "150";
        String height = "6";

        // Fill out form with existing credentials and attempt account creation
        onView(withId(R.id.name_field)).perform(typeText(name));
        onView(withId(R.id.username_field)).perform(typeText(uname));
        onView(withId(R.id.password_field)).perform(typeText(password));
        onView(withId(R.id.confirm_password_field)).perform(typeText(password));
        onView(withId(R.id.email_field)).perform(typeText(email));
        onView(withId(R.id.age_field)).perform(typeText(age));
        onView(withId(R.id.weight_field)).perform(typeText(weight))
                .perform(closeSoftKeyboard()); // Keyboard gets in the way of next click;
        onView(withId(R.id.height_field)).perform(typeText(height))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.create_account_button)).perform(click());

        // Activity should not finish
        assertFalse(activityRule.getActivity().isFinishing());
    }

    /*
     * Test behavior when field invalid
     */
    @Test
    public void invalidFieldTest() {
        String name = "test Faulkenberry";
        String uname = "test";
        String password = "password";
        String email = "test@gmail.com";
        String age = "21";
        String weight = "150";
        String height = "9";
        String gender = "Male";

        // Fill out form with existing credentials and attempt account creation
        onView(withId(R.id.name_field)).perform(typeText(name));
        onView(withId(R.id.username_field)).perform(typeText(uname));
        onView(withId(R.id.password_field)).perform(typeText(password));
        onView(withId(R.id.confirm_password_field)).perform(typeText(password));
        onView(withId(R.id.email_field)).perform(typeText(email));
        onView(withId(R.id.age_field)).perform(typeText(age));
        onView(withId(R.id.weight_field)).perform(typeText(weight))
                .perform(closeSoftKeyboard()); // Keyboard gets in the way of next click;
        onView(withId(R.id.height_field)).perform(typeText(height))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.gender_field)).perform(typeText(gender))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.create_account_button)).perform(click());

        // Activity should not finish
        assertFalse(activityRule.getActivity().isFinishing());
    }
}
