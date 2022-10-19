package es.unican.is.appgasolineras.activities.historialRepostajes;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.core.app.ApplicationProvider;
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
    public void verHistoricoCorrectoVolverInicioTest() throws InterruptedException {
        // abrir el toolbar desde Main
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        // onView(withId(R.menu.main_menu)).perform(click()); // dar al boton de tres puntos
        // cuando se vea el desplegable
        onView(anyOf(withText("Historial Repostajes"),
                withId(R.id.menuHistorialRepostajes))).perform(click());

        // TODO hasta aqui funciona
        // ver que se esta en la actividad nueva
        intended(hasComponent(HistorialRepostajesView.class.getName()));
        /* si no va lo de arriba, hacer esto
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.androiddrawerexample", appContext.getPackageName());
         */

        // ahora ver que se muestra un listado de repostajes
        // TODO ver si hay que hacer alguna comprobacion mas o se podria hacer

        // con esto deberia ver que cada elemento tiene el texto dado
        // comprobar repostaje 1
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(0).
                onChildView(withId(R.id.tvAddress)).
                check(matches(withText("CARRETERA CASTILLO SIETEVILLAS KM, S/N")));
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(0).
                onChildView(withId(R.id.tvDate)).
                check(matches(withText("18/10/2022")));
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(0).
                onChildView(withId(R.id.tvLiters)).
                check(matches(withText("13")));
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(0).
                onChildView(withId(R.id.tvPrice)).
                check(matches(withText("25.0")));
        // comprobar repostaje 2
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(1).
                onChildView(withId(R.id.tvAddress)).
                check(matches(withText("CARRETERA ARGOÑOS SOMO KM. 28,7")));
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(1).
                onChildView(withId(R.id.tvDate)).
                check(matches(withText("18/10/2022")));
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(1).
                onChildView(withId(R.id.tvLiters)).
                check(matches(withText("13")));
        onData(anything()).inAdapterView(withId(R.id.lvHistoricoGasolineras)).atPosition(1).
                onChildView(withId(R.id.tvPrice)).
                check(matches(withText("25.0")));

        // ahora, volver con el boton del logo a la actividad principal
        //onView(withId(R.id.ivLogo)
    }
}
