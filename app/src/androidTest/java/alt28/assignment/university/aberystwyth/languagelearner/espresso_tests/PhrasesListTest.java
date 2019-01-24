package alt28.assignment.university.aberystwyth.languagelearner.espresso_tests;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PhrasesListTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void see_add_phrase_dialog_opens_when_add_fab_clicked() throws Exception {
        onView(withId(R.id.fab_add)).perform(click());
        onView(withText(R.string.add_phrase_title)).check(matches(isDisplayed()));
    }
}
