package es.unican.is.appgasolineras.activities.historialRepostajes;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.main.MainView;


/**
 * Instrumented test, which will execute on an Android device.
 * See link if necessary.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @author Ivan Ortiz del Noval
 */
@RunWith(AndroidJUnit4.class)
public class VerHistorialRepostajesUITest {

    @Rule // se empieza desde la actividad Main
    public ActivityScenarioRule<MainView> activityRule = new
            ActivityScenarioRule<>(MainView.class);

    /**
        Este test se corresponde con el test IGUI464877.a segun el plan de pruebas.

        Desde la vista main se va a la actividad de ver historial repostajes, sus datos son
        correctos y carga varios repostajes, y vuelve al inicio con el boton de inicio del toolbar.
     */
    @Test
    public void verHistoricoCorrectoVolverInicioTest() {
        // abrir el toolbar desde Main
        onView(withId(R.id.menuHistorialRepostajes)).perform(click()); // TODO ver si es asi

        // ver que se esta en la actividad nueva
        intended(hasComponent(HistorialRepostajesView.class.getName()));
        /* si no va lo de arriba, hacer esto
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.androiddrawerexample", appContext.getPackageName());
         */

        // ahora ver que se muestra un listado de repostajes
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(0); // TODO



        // onView((withId(R.id.resultado))).check(matches(withText("1")));
    }

}
