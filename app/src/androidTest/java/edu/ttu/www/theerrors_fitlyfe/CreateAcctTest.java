package edu.ttu.www.theerrors_fitlyfe;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/*
 * Integration tests for the Create Account Activity
 */

public class CreateAcctTest {

    /*
     * Make sure UI behaves
     */
    @Test
    public void UITest() {
        onView(withId(R.id.name_field)).check(matches(withHint("Name")));
        onView(withId(R.id.username_field)).check(matches(withHint("UserName")));
        onView(withId(R.id.password_field)).check(matches(withHint("Password")));
        onView(withId(R.id.confirm_password_field)).check(matches(withHint("Confirm Password")));
        onView(withId(R.id.email_field)).check(matches(withHint("Email")));
        onView(withId(R.id.age_field)).check(matches(withHint("Age")));
        onView(withId(R.id.weight_field)).check(matches(withHint("Weight")));
        onView(withId(R.id.height_field)).check(matches(withHint("Height")));
        onView(withId(R.id.gender_field)).check(matches(withHint("Gender")));
        onView(withId(R.id.create_account_button)).check(matches(withText("Create Account")));
    }
}
