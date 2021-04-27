package cat.itb.pixiv;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions;
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;


import cat.itb.pixiv.Fragments.LoginFragments.FragmentLoginTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UseCaseTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    public void fastLogin() {
        FragmentLoginTest login=new FragmentLoginTest();
        login.login();
    }

    @Test
    public void likeAnImage(){
        fastLogin();
        BaristaSleepInteractions.sleep(3000);
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.image_view_illustrations_recommended_like),
                        childAtPosition(
                                allOf(withId(R.id.illustrationlayoutitem),
                                        childAtPosition(
                                                withId(R.id.recycler_view_illustrations_recommended),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click())
        ;
        BaristaImageViewAssertions.assertHasDrawable(R.id.image_view_illustrations_recommended_like,R.drawable.likeheartred);
    }

    @Test
    public void logout(){
        fastLogin();
        BaristaSleepInteractions.sleep(3000);
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigation Drawer open"),
                        childAtPosition(
                                allOf(withId(R.id.top_appbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.logout),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigator_view),
                                                0)),
                                10),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        onView(withId(R.id.loginfragment))
                .check(matches(isDisplayed()));
    }

    @Test
    public void uploadImage(){
        fastLogin();
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigation Drawer open"),
                        childAtPosition(
                                allOf(withId(R.id.top_appbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.submitWork),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigator_view),
                                                0)),
                                5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.illustrations_button), withText("Illustrations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.design_bottom_sheet),
                                        0),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.edit_text_title_submit_illustration),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_text_title_submit_illustration),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("Magia"), closeSoftKeyboard());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.edit_description_title_submit_illustration),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_text_description_submit_illustration),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(replaceText("Kalafina"), closeSoftKeyboard());

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.radio_button_public_illustrations), withText("Make public"),
                        childAtPosition(
                                allOf(withId(R.id.group_radio_button_submit_illustrations),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                7)),
                                0)));
        materialRadioButton.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.submitIllustrationManga), withText("submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                8)));
        materialButton5.perform(scrollTo(), click());

        BaristaSleepInteractions.sleep(3000);
        onView(withId(R.id.recycler_view_illustrations_recommended)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));

        onView(withId(R.id.illustration_text_view_oc_tittle))
                .check(matches(isDisplayed()));


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