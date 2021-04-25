package cat.itb.pixiv.Fragments.LoginFragments;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import cat.itb.pixiv.MainActivity;
import cat.itb.pixiv.R;

import static androidx.test.espresso.Espresso.onView;
import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.not;


public class FragmentLoginTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void login(){
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withId(R.id.input_text_login_username))
                .perform(typeText("test"))
                .check(matches(withText("test")))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.input_layout_login_password))
                .perform(click());
        onView(withText(R.id.input_text_login_password))
                .perform(typeText("123456789"))
                .check(matches(withText("123456789")))
                .perform(closeSoftKeyboard());;
        onView(withText(R.id.loginButton))
                .check(matches(isClickable()))
                .perform(click());

    }

    @Test
    public void register(){
        onView(withId(R.id.registerButton))
                .perform(click());
        onView(withId(R.id.input_text_username))
                .perform(typeText("test2"))
                .check(matches(withText("test2")))
                .perform(closeSoftKeyboard());
        onView(withText(R.id.input_text_password))
                .perform(typeText("123456789"))
                .check(matches(withText("123456789")))
                .perform(closeSoftKeyboard());
        onView(withText(R.id.input_text_password_repeat))
                .perform(typeText("123456789"))
                .check(matches(withText("123456789")))
                .perform(closeSoftKeyboard());
        onView(withText(R.id.registerButton))
                .check(matches(isClickable()))
                .perform(click());
    }



}