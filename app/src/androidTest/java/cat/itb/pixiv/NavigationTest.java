package cat.itb.pixiv;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class NavigationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    public void fastLogin() {
        FragmentLoginTest login=new FragmentLoginTest();
        login.login();
    }
    @Test
    public void toMangaLayout(){
        fastLogin();
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Manga"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());
        onView(withId(R.id.mangafragment))
                .check(matches(isDisplayed()));
    }

    @Test
    public void toNovelLayout(){
        fastLogin();
        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Novels"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout),
                                        0),
                                2),
                        isDisplayed()));
        tabView2.perform(click());
        onView(withId(R.id.novelsfragment))
                .check(matches(isDisplayed()));

    }

    @Test
    public void toCollectionLayout(){
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
                allOf(withId(R.id.collection),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigator_view),
                                                0)),
                                7),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        onView(withId(R.id.collectionfragment))
                .check(matches(isDisplayed()));
    }

    @Test
    public void toYourWorksLayout(){
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
                allOf(withId(R.id.yourWorks),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigator_view),
                                                0)),
                                6),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        onView(withId(R.id.yourWorksfragment))
                .check(matches(isDisplayed()));
    }

    @Test
    public void toFollowers(){
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
                allOf(withId(R.id.following),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigator_view),
                                                0)),
                                9),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
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