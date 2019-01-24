package alt28.assignment.university.aberystwyth.languagelearner.espresso_tests;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationDrawerTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void nagivation_drawer_opens_when_selected() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));

        String navigateUpDesc = activityActivityTestRule.getActivity().getString(R.string.nav_open_navigation_drawer);
        onView(withContentDescription(navigateUpDesc)).perform(click());

        onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT)));
    }

    @Test
    public void about_in_navigation_drawer_takes_to_about() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));

        String navigateUpDesc = activityActivityTestRule.getActivity().getString(R.string.nav_open_navigation_drawer);
        onView(withContentDescription(navigateUpDesc)).perform(click());

        String aboutMenuName = activityActivityTestRule.getActivity().getString(R.string.nav_about);
        onView(withText(aboutMenuName)).perform(click());

        String aboutText = activityActivityTestRule.getActivity().getString(R.string.about_body);
        onView(withText(aboutText)).check(matches(isDisplayed()));
    }

    @Test
    public void reset_in_navigation_drawer_shows_dialogbox() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)));

        String navigateUpDesc = activityActivityTestRule.getActivity().getString(R.string.nav_open_navigation_drawer);
        onView(withContentDescription(navigateUpDesc)).perform(click());

        String resetMenuName = activityActivityTestRule.getActivity().getString(R.string.nav_change_language);
        onView(withText(resetMenuName)).perform(click());

        String resetText = activityActivityTestRule.getActivity().getString(R.string.title_reset_confirm);
        onView(withText(resetText)).check(matches(isDisplayed()));
    }
}
