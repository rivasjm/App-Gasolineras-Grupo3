package es.unican.is.appgasolineras.activities.historialRepostajes;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.database.sqlite.SQLiteException;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.LinkedList;
import java.util.List;

import es.unican.is.appgasolineras.model.Repostaje;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;
import es.unican.is.appgasolineras.repository.db.RepostajeDao;

/**
 * Test de integracion del presentador del historial de repostajes
 * junto con su DAO y la base de datos.
 *
 * @author Ivan Ortiz del Noval
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class HistorialRepostajesPresenterITest {
    private HistorialRepostajesPresenter sut;

    private List<Repostaje> repostajes;

    @Mock // sigue siendo un mock
    private IHistorialRepostajesContract.View viewMock;

    private RepostajeDao dao;

    @Before
    public void setUp() throws Exception {
        // inicializar mocks siempre
        MockitoAnnotations.openMocks(this);
        // no defino aqui comportamiento porque varia en cada caso
    }

    @Test
    public void initCorrectoTest() {
        sut = new HistorialRepostajesPresenter(viewMock);

        // datos reales en la DAO, asi que no hay mocks para ella
        // definir comportamiento mock
        // obtiene el contexto del mock de la vista
        when(viewMock.getGasolineraDb()).thenReturn(
                GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));

        // ver que funciona
        sut.init();
        assert (sut.shownRepostajes.equals(repostajes)); // TODO comprobacion distinta
        verify (viewMock).getGasolineraDb();
        verify (viewMock).showHistorialRepostajes(repostajes);
    }

    @Test
    public void initCorrectoAnomaloTest() { // TODO como hago que tenga datos incorrectos
        sut = new HistorialRepostajesPresenter(viewMock);

        // lista de repostajes con datos anomalos, con 10 repostajes
        repostajes = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Repostaje r = new Repostaje();
            r.setId(i+100);
            r.setFechaRepostaje(String.format("%d/%d/2023", i, 3+i)); // algunas fechas mal
            r.setLitros(Double.toString(-23 + i*8)); // algun litro negativo
            r.setPrecio(Double.toString(2 * (-3 + i))); // algun precio negativo y poco realista
            if (i > 2) {
                r.setLocalizacion(String.format("Direccion %d", i)); // alguna direccion vacia
            }
            repostajes.add(r);
        }
        // datos reales en la DAO, asi que no hay mocks para ella
        // definir comportamiento mock
        // obtiene el contexto del mock de la vista
        when(viewMock.getGasolineraDb()).thenReturn(
                GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));

        // ver que funciona
        sut.init();
        assert (sut.shownRepostajes.equals(repostajes));
        verify (viewMock).getGasolineraDb();
        verify (viewMock).showHistorialRepostajes(repostajes);
    }

    @Test
    public void initListaVaciaTest() { // TODO como hago que este vacia
        sut = new HistorialRepostajesPresenter(viewMock);
        // datos reales en la DAO, asi que no hay mocks para ella
        // definir comportamiento mock
        // obtiene el contexto del mock de la vista
        when(viewMock.getGasolineraDb()).thenReturn(
                GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));

        // ver que funciona
        sut.init();

        verify (viewMock).getGasolineraDb();
        // el metodo del show vacio
        verify (viewMock).showHistorialVacio();
    }

    @Test
    public void initErrorCargaTest() { // TODO como hago que de un error la DAO de verdad, simulo desde view???
        sut = new HistorialRepostajesPresenter(viewMock);
        // datos reales en la DAO, asi que no hay mocks para ella
        // definir comportamiento mock
        // obtiene el contexto del mock de la vista
        when(viewMock.getGasolineraDb()).thenReturn(
                GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));

        // ver que funciona correctamente
        try {
            sut.init();
            fail();
        } catch (SQLiteException e) {
        }
        assert (sut.shownRepostajes == null);
        verify (viewMock).getGasolineraDb();
        verify (viewMock).showLoadError();
    }

    @Test
    public void onAceptarClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // datos reales en la DAO, asi que no hay mocks para ella
        // definir comportamiento mock
        // obtiene el contexto del mock de la vista
        when(viewMock.getGasolineraDb()).thenReturn(
                GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));
        sut.init();
        // ver que si se llama al metodo se llama a la vista para volver
        sut.onAceptarClicked();
        verify(viewMock).openMainView();
    }

    @Test
    public void onReintentarClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // datos reales en la DAO, asi que no hay mocks para ella
        // definir comportamiento mock
        // obtiene el contexto del mock de la vista
        when(viewMock.getGasolineraDb()).thenReturn(
                GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));
        sut.init();
        // ver que si se llama al metodo se llama otra vez a init
        sut.onReintentarClicked();
        verify(sut, times(2)).init();
    }
}