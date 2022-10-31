package es.unican.is.appgasolineras.activities.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter;
import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;

/**
 * Test unitario del presentador general de la aplicación.
 *
 * Casos de prueba implementados: UPR465236.1a-UPR465236.1f
 *
 * @author Irene Zamanillo Zubizarreta
 */

public class MainPresenterTest {
    private MainPresenter sut;
    private List<Gasolinera> gasolineras;
    private List<Gasolinera> gasolinerasPrecio;
    private List<Gasolinera> gasolinerasDistanciaPrecio;
    private List<Gasolinera> gasolinerasSinPrecio;

    @Mock
    private IGasolinerasRepository repositoryMock;

    @Mock
    private IMainContract.View viewMock;

    @Mock
    private IPrefs prefsMock;

    @Before
    public void setUp() {
        //Inicializacion de los mocks
        MockitoAnnotations.openMocks(this);

        //Inicializacion generica
        sut = new MainPresenter(viewMock, prefsMock);
        gasolineras = new LinkedList<Gasolinera>();
        gasolinerasPrecio = new LinkedList<Gasolinera>();
        gasolinerasDistanciaPrecio = new LinkedList<Gasolinera>();
        gasolinerasSinPrecio = new LinkedList<Gasolinera>();

        //Solo se da valor a los campos utilizados durante el test
        for (int i = 0; i < 5; i++) {
            Gasolinera g = new Gasolinera();
            g.setId(String.valueOf(i+1000));
            g.setNormal95(String.valueOf(10-i));
            g.setDieselA(String.valueOf(20-i));
            gasolineras.add(g);
        }

        for (int i = 5; i < 0; i--) {
            Gasolinera g = new Gasolinera();
            g.setId(String.valueOf(i+1000));
            g.setNormal95(String.valueOf(10-i));
            g.setDieselA(String.valueOf(20-i));
            gasolinerasPrecio.add(g);
        }

        for (int i = 0; i < 5; i++) {
            Gasolinera g = new Gasolinera();
            g.setId(String.valueOf(i+1000));
            gasolinerasSinPrecio.add(g);
        }

        //Comportamiento de los mocks
        when(viewMock.getGasolineraRepository()).thenReturn(repositoryMock);
        when(repositoryMock.getGasolineras()).thenReturn(gasolineras); //en la mayoria de los casos
    }

    //Como doSyncInit() es un método privado, se llama al método init() en los tests
    // porque es el método público que lo contiene


    //Corresponde a UPR465236.1a
    //TODO: como pruebo la distancia?? como sé que está más cerca y más lejos? emulo la distancia??
    @Test
    public void doSyncInitOrdenarDistancia() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(1);
        sut.init();

        //assert(sut.shownGasolineras.equals(gasolinerasDistancia));
        verify(repositoryMock).getGasolineras();
        verify(prefsMock).getInt(BarraHerramientasPresenter.ORDENAR);
        verify(viewMock).showGasolineras(gasolineras);
    }

    //Corresponde a UPR465236.1b
    @Test
    public void doSyncInitOrdenarPrecio() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(2);
        sut.init();

        //assert(sut.shownGasolineras.equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
        verify(prefsMock).getInt(BarraHerramientasPresenter.ORDENAR);
        verify(viewMock).showGasolineras(gasolineras);
    }

    //Corresponde a UPR465236.1c
    @Test
    public void doSyncInitNoOrdenar() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(0);
        sut.init();

        //assert(sut.shownGasolineras.equals(gasolineras));
        verify(repositoryMock).getGasolineras();
        verify(prefsMock).getInt(BarraHerramientasPresenter.ORDENAR);
        verify(viewMock).showGasolineras(gasolineras);
    }

    //Corresponde a UPR465236.1d
    @Test
    public void doSyncInitOrdenarErrorPref() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(5);
        sut.init();

        //assert(sut.shownGasolineras.equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
        verify(prefsMock).getInt(BarraHerramientasPresenter.ORDENAR);
        verify(viewMock).showGasolineras(gasolineras);
    }

    //Corresponde a UPR465236.1e
    //TODO: no se muestra el error
    /*@Test
    public void doSyncInitErrorCarga() {
        //Comportamiento de los mocks
        when(repositoryMock.getGasolineras()).thenReturn(new LinkedList<Gasolinera>());
        sut.init();

        //assert(sut.shownGasolineras.equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
        verify(viewMock).showLoadError();
    }

    //Corresponde a UPR465236.1f
    //TODO: nullPointer porque se intenta operar sin precios
    @Test
    public void doSyncInitErrorCargaSinPrecio() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(2);
        when(repositoryMock.getGasolineras()).thenReturn(gasolinerasSinPrecio);
        sut.init();

        //assert(sut.shownGasolineras.equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
        verify(prefsMock).getInt(BarraHerramientasPresenter.ORDENAR);
        verify(viewMock).showGasolineras(gasolineras);
    }*/
}
