package net.alaindonesia.silectric;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AgregarAcuarioTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void agregarAcuarioTest() {
        //pressBack();  //hay que presionar atrás cuando la aplicacion corre por primera vez en el dispositivo

        onView(withId(R.id.addUsageButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("AC 0.5 PK")).check(matches(isDisplayed())).perform(click());
        onView(withText("Aquarium (Big)")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.saveUsageButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.usageListView)).check(matches(isDisplayed()));
    }
}
