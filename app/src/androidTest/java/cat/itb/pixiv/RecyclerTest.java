package cat.itb.pixiv;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recyclerTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.loginButton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.input_text_login_username),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_layout_login_username),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.input_text_login_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.input_layout_login_password),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("123456789"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.loginButton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

//        ViewInteraction appCompatImageView = onView(
//                allOf(withId(R.id.image_view_illustrations_recommended),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.recycler_view_illustrations_recommended),
//                                        1),
//                                0),
//                        isDisplayed()));
//        appCompatImageView.perform(click());

        onView(withId(R.id.recycler_view_illustrations_recommended)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

        onView(withId(R.id.illustration_text_view_oc_tittle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.illustration_text_view_oc_username))
                .check(matches(isDisplayed()));
        onView(withId(R.id.illustration_oc_ProfileImage))
                .check(matches(isDisplayed()));
        onView(withId(R.id.illustratrion_oc_Image))
                .check(matches(isDisplayed()));
        onView(withId(R.id.floatingActionButton_illustration))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));
        onView(withId(R.id.followButtonIllustration))
                .check(matches(isDisplayed()))
                .check(matches(isClickable()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
