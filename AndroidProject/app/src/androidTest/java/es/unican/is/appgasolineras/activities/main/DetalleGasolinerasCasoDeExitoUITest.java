package es.unican.is.appgasolineras.activities.main;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static es.unican.is.appgasolineras.utils.Matchers.hasElements;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.repository.rest.GasolinerasServiceConstants;

public class DetalleGasolinerasCasoDeExitoUITest {

    @BeforeClass
    public static void setUp() {
        GasolinerasServiceConstants.setStaticURL();
    }

    @AfterClass
    public static void clean() {
        GasolinerasServiceConstants.setMinecoURL();
    }

    @Rule
    public ActivityScenarioRule<MainView> activityRule =
            new ActivityScenarioRule(MainView.class);

    @Test
    public void testUI1() {
        onView(withId(R.id.lvGasolineras)).check(matches(hasElements()));
        onData(anything()).inAdapterView(withId(R.id.lvGasolineras)).atPosition(0).perform(click());

        onView(withId(R.id.tvRotulo)).check(matches(withText("CEPSA")));
        onView(withId(R.id.tvMunicipio)).check(matches(withText("Alfoz de Lloredo")));
        onView(withId(R.id.tvPrecioGasolina95)).check(matches(withText("1,739")));
        onView(withId(R.id.tvPrecioDieselA)).check(matches(withText("1,859")));
        onView(withId(R.id.tvHorario)).check(matches(withText("L-D: 08:00-21:00")));

        //Media ponderada: (2xGasolina+Diesel)/3
        onView(withId(R.id.tvPrecioSumario)).check(matches(withText("1,78")));
    }

}
