package es.unican.is.appgasolineras.activities.convenios;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.main.MainView;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.rest.GasolinerasServiceConstants;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static es.unican.is.appgasolineras.utils.Matchers.hasElements;

public class MostrarConveniosUITest {
    @BeforeClass
    public static void setUp() {
        GasolinerasServiceConstants.setStaticURL();
    }

    @AfterClass
    public static void clean() {
        GasolinerasServiceConstants.setMinecoURL();
    }

    @Rule
    public ActivityScenarioRule<ConveniosView> activityRule =
            new ActivityScenarioRule(ConveniosView.class);

    @Test
    public void openDetailViewTest() {
        onView(withId(R.id.lvConvenios)).check(matches(isDisplayed()));
        //onView(withId(R.id.)).check(matches(withText("CONVENIOS")));
    }
    @Test
    public void homeIcon(){
        //onView(withId(R.id.menuHome)).perform(click());
        onView(withId(R.id.lvGasolineras)).check(matches(isDisplayed()));
    }
    public void backButton(){


    }

}
