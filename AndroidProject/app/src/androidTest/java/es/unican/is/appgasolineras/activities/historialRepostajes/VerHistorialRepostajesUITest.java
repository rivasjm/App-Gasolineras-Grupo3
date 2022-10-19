package es.unican.is.appgasolineras.activities.historialRepostajes;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

import static es.unican.is.appgasolineras.utils.Matchers.hasElements;

import android.app.Activity;
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.content.Context;
import android.support.test.espresso.intent.Intents;
import android.util.Log;
import android.widget.AdapterView;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.material.snackbar.BaseTransientBottomBar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.main.MainView;
import es.unican.is.appgasolineras.model.Repostaje;
import es.unican.is.appgasolineras.repository.db.RepostajeDao;
import es.unican.is.appgasolineras.repository.rest.GasolinerasServiceConstants;


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

    @BeforeClass
    public static void setUp() {
        // usar URL estatica para controlar el entorno
        GasolinerasServiceConstants.setStaticURL();
    }

    /**
     Este test se corresponde con el test IGUI464877.g segun el plan de pruebas.

     Desde la vista main se va a la actividad de ver historial repostajes, se obtiene una
     lista vacia y se indica por pantalla. Si se pulsa el boton de atras, se vuelve a la
     actividad principal.
     */
    @Test
    public void verHistoricoVacioVolverInicioTest() {
        // abrir el toolbar desde Main, empieza el test en si
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        // onView(withId(R.menu.main_menu)).perform(click()); // dar al boton de tres puntos
        // cuando se vea el desplegable
        onView(anyOf(withText("Historial Repostajes"),
                withId(R.id.menuHistorialRepostajes))).perform(click());

        // TODO hasta aqui funciona
        // ver que se esta en la actividad nueva, con el texto de lista de repostajes vacio
        // si se ve este mensaje es que se esta en esa actividad
        onView(withId(R.id.tvRepostajesVacios)).check(matches(isDisplayed()));
        // la lista de gasolineras esta vacia
        onView(withId(R.id.lvHistoricoGasolineras)).check(matches(not(hasElements())));

        /*
        Intents.init();
        intended(hasComponent(HistorialRepostajesView.class.getName())); */
        //Context context = ApplicationProvider.getApplicationContext();
        //Log.d("DEBUG IVAN", context.getClass().toString());
        //assert(context.getClass().isInstance(HistorialRepostajesView.class));

        // volver a la vista principal
        //onData(anything()).inAdapterView(withId(R.id.ivLogo)).perform(click());
        pressBack();

        // comprobar que se esta en la pantalla principal, comparando datos de la 1a gasolinera
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onData(anything()).inAdapterView(withId(R.id.lvGasolineras)).atPosition(0).
                onChildView(withId(R.id.tvName)).check(matches(withText("CEPSA")));

    }

    @AfterClass
    public static void tearDown() {
        // volver URL real
        GasolinerasServiceConstants.setMinecoURL();
    }



    /**
        Este test se corresponde con el test IGUI464877.a segun el plan de pruebas.

        Desde la vista main se va a la actividad de ver historial repostajes, sus datos son
        correctos y carga varios repostajes, y vuelve al inicio con el boton de inicio del toolbar.
     */
    /*
    @Test
    public void verHistoricoCorrectoVolverInicioTest() {
        // abrir el toolbar desde Main, empieza el test en si
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        // onView(withId(R.menu.main_menu)).perform(click()); // dar al boton de tres puntos
        // cuando se vea el desplegable
        onView(anyOf(withText("Historial Repostajes"),
                withId(R.id.menuHistorialRepostajes))).perform(click());

        // TODO hasta aqui funciona
        // ver que se esta en la actividad nueva
        intended(hasComponent(HistorialRepostajesView.class.getName()));

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

    } */

}
