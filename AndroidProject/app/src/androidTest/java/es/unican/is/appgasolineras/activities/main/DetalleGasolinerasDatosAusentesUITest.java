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

public class DetalleGasolinerasDatosAusentesUITest {

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
    public void testUI2() {
        onView(withId(R.id.lvGasolineras)).check(matches(hasElements()));
        onData(anything()).inAdapterView(withId(R.id.lvGasolineras)).onChildView(withText("COOPERATIVA RUISEÑADA"));

        onView(withId(R.id.tvRotulo)).check(matches(withText("COOPERATIVA RUISEÑADA")));
        onView(withId(R.id.tvMunicipio)).check(matches(withText("Comillas")));
        onView(withId(R.id.tvPrecioGasolina95)).check(matches(withText("-")));
        onView(withId(R.id.tvPrecioDieselA)).check(matches(withText("1,848")));
        onView(withId(R.id.tvHorario)).check(matches(withText("L-V: 09:00-19:00")));

        //Media ponderada: (2xGasolina+Diesel)/3
        onView(withId(R.id.tvPrecioSumario)).check(matches(withText("1,848")));
    }

}
