package es.unican.is.appgasolineras.activities.main;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;
import static es.unican.is.appgasolineras.utils.Matchers.hasNElements;

import android.view.View;

import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.repository.rest.GasolinerasServiceConstants;

public class OrdenarGasolinerasPorDistanciaTest {
    @Rule
    public ActivityScenarioRule<MainView> activityRule = new
            ActivityScenarioRule<>(MainView.class);

    private View decorView;

    @BeforeClass
    public static void setUp() {
        GasolinerasServiceConstants.setStaticURL();
    }

    @Before
    public void setUp2() { activityRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView()); }

    @Test
    public void ordenarGasolinerasPorPrecioTest() {

        onView(withId(R.id.menuPrecio)).perform(click());

        onView(withId(R.id.lvGasolineras)).check(matches(hasNElements(156)));

        onData(anything()).inAdapterView(withId(R.id.lvGasolineras)).atPosition(0).
                onChildView(withId(R.id.tvName)).check(matches(withText("AVIA")));
        onData(anything()).inAdapterView(withId(R.id.lvGasolineras)).atPosition(1).
                onChildView(withId(R.id.tvName)).check(matches(withText("COMBUSCAN")));
        onData(anything()).inAdapterView(withId(R.id.lvGasolineras)).atPosition(2).
                onChildView(withId(R.id.tvName)).check(matches(withText("E.S.SOLBAS")));

        onView(withText(R.string.ordenarDistanciaAplicado)).inRoot(RootMatchers.withDecorView(not(decorView))).check(matches(isDisplayed()));
    }
}
