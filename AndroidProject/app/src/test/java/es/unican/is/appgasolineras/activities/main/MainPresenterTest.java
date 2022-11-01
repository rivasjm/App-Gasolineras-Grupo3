package es.unican.is.appgasolineras.activities.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.location.Location;

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

//TODO: añadir argumento de Location a los show

public class MainPresenterTest {
    /*private MainPresenter sut;
    private List<Gasolinera> gasolineras;
    private List<Gasolinera> gasolinerasPrecio;
    private List<Gasolinera> gasolinerasDistancia;
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
        gasolinerasDistancia = new LinkedList<Gasolinera>();
        gasolinerasSinPrecio = new LinkedList<Gasolinera>();

        //Solo se da valor a los campos utilizados durante el test (precio, distancia, id)

        //Lista generica de gasolineras (precio mas alto a mas bajo; distancia mas cercana a mas lejana)
        double j = 0.10;
        for (int i = 0; i < 4; i++) {
            Gasolinera g = new Gasolinera();
            g.setId(String.valueOf(i));
            g.setNormal95(String.valueOf(10-i));
            g.setDieselA(String.valueOf(20-i));
            g.setLatitud(String.valueOf(43.46 + j));
            g.setLongitud(String.valueOf(-3.8 - j));
            j += 0.05;
            gasolineras.add(g);
        }

        //Para conseguir algo de variacion en el test, se introducen valores manualmente en la ultima gasolinera
        //De esta forma ordenar la lista no implica solo darle la vuelta
        Gasolinera g = new Gasolinera();
        g.setId(String.valueOf(4));
        g.setNormal95(String.valueOf(10-4));
        g.setDieselA(String.valueOf(20-4));
        g.setLatitud(String.valueOf(43.46 + 5.60));
        g.setLongitud(String.valueOf(-3.8 - 5.60));
        gasolineras.add(g);

        //Lista de gasolineras ordenada por precio
        gasolinerasPrecio.add(gasolineras.get(4));
        gasolinerasPrecio.add(gasolineras.get(3));
        gasolinerasPrecio.add(gasolineras.get(2));
        gasolinerasPrecio.add(gasolineras.get(1));
        gasolinerasPrecio.add(gasolineras.get(0));

        //Lista de gasolineras sin precio
        for (int i = 0; i < 5; i++) {
            g = new Gasolinera();
            g.setId(String.valueOf(i));
            gasolinerasSinPrecio.add(g);
        }

        //Lista de gasolineras ordenada por distancia
        gasolinerasDistancia.add(gasolineras.get(4));
        gasolinerasDistancia.add(gasolineras.get(0));
        gasolinerasDistancia.add(gasolineras.get(1));
        gasolinerasDistancia.add(gasolineras.get(2));
        gasolinerasDistancia.add(gasolineras.get(3));

        //Comportamiento de los mocks
        when(viewMock.getGasolineraRepository()).thenReturn(repositoryMock);
        when(repositoryMock.getGasolineras()).thenReturn(gasolineras); //en la mayoria de los casos
    }

    //Como doSyncInit() es un método privado, se llama al método init() en los tests
    // porque es el método público que lo contiene


    //Corresponde a UPR465236.1a
    @Test
    public void doSyncInitOrdenarDistancia() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(1);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolinerasDistancia));
        verify(repositoryMock).getGasolineras();
        verify(viewMock).showGasolineras(gasolinerasDistancia);
    }

    //Corresponde a UPR465236.1b
    @Test
    public void doSyncInitOrdenarPrecio() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(2);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
        verify(viewMock).showGasolineras(gasolinerasPrecio);
    }

    //Corresponde a UPR465236.1c
    @Test
    public void doSyncInitNoOrdenar() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(0);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolineras));
        verify(repositoryMock).getGasolineras();
        verify(viewMock).showGasolineras(gasolineras);
    }

    //Corresponde a UPR465236.1d
    @Test
    public void doSyncInitOrdenarErrorPref() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(5);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolineras));
        verify(repositoryMock).getGasolineras();
        verify(viewMock).showGasolineras(gasolineras);
    }

    //Corresponde a UPR465236.1e
    //TODO: no se muestra el error
    /*@Test
    public void doSyncInitErrorCarga() {
        //Comportamiento de los mocks
        when(repositoryMock.getGasolineras()).thenReturn(new LinkedList<Gasolinera>());
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolinerasPrecio));
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

        assert(sut.getShownGasolineras().equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
        verify(prefsMock).getInt(BarraHerramientasPresenter.ORDENAR);
        verify(viewMock).showGasolineras(gasolineras);
    }*/
}
