package es.unican.is.appgasolineras.activities.historialRepostajes;

import android.os.Build;

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

    /*
        Hay que usar Roboelectric para tener, con el mock de la vista, un contexto que pasarle
        con la dao real o ya implementada, porque aqui no se puede
     */

    @Mock
    private IHistorialRepostajesContract.View viewMock;

    private RepostajeDao dao; // ahora a la vista(mock) se le pasa la DAO de verdad

    @Before
    public void setUp() throws Exception {
        // inicializar mocks siempre
        MockitoAnnotations.openMocks(this);
        // no defino aqui comportamiento de la vista
    }

    @Test
    public void initCorrectoTest() {
        sut = new HistorialRepostajesPresenter(viewMock);

        // definir comportamiento mock
        when(viewMock.getHistorialRepostajesRepository()).thenCall();

        // ver que funciona
        sut.init();
        assert (sut.shownRepostajes.equals(repostajes));
        verify (viewMock).getHistorialRepostajesRepository();
        verify (viewMock).showHistorialRepostajes(repostajes);
    }

    @Test
    public void initCorrectoAnomaloTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // definir comportamiento mock
        when(viewMock.getHistorialRepostajesRepository()).thenReturn(repostajes);

        // lista de repostajes con datos anomalos, con 10 repostajes
        List<Repostaje> repostajes = new LinkedList<>();
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

        // ver que funciona correctamente
        sut.init();
        assert (sut.shownRepostajes.equals(repostajes));
        verify (viewMock).getHistorialRepostajesRepository();
        verify (viewMock).showHistorialRepostajes(repostajes);
    }

    @Test
    public void initListaVaciaTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // definir comportamiento mock
        when(viewMock.getHistorialRepostajesRepository()).thenReturn(repostajes);

        // lista de repostajes vacia
        List<Repostaje> repostajes = new LinkedList<>();

        // ver que funciona correctamente
        sut.init();
        assert (sut.shownRepostajes.equals(repostajes));
        verify (viewMock).getHistorialRepostajesRepository();
        // TODO el metodo del show vacio
    }

    @Test
    public void initErrorCargaTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // definir comportamiento mock, si hay error devuelve null
        when(viewMock.getHistorialRepostajesRepository()).thenReturn(null);

        // ver que funciona correctamente
        sut.init();
        assert (sut.shownRepostajes == null);
        verify (viewMock).getHistorialRepostajesRepository();
        verify (viewMock).showLoadError();
    }

    @Test
    public void onHomeClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        sut.init();
        // ver que si se llama al metodo se llama a la vista para volver
        sut.onHomeClicked();
        verify(viewMock).openMainView();
    }

    @Test
    public void onAceptarClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        sut.init();
        // ver que si se llama al metodo se llama a la vista para volver
        sut.onAceptarClicked();
        verify(viewMock).openMainView();
    }

    @Test
    public void onReintentarClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        sut.init();
        // ver que si se llama al metodo se llama otra vez a init
        sut.onReintentarClicked();
        verify(sut, times(2)).init();
    }
}