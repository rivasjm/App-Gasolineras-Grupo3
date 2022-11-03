package es.unican.is.appgasolineras.activities.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.location.Location;
import android.os.Build;

import java.util.LinkedList;
import java.util.List;

import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter;
import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;

/**
 * Test unitario del presentador general de la aplicación.
 *
 * Casos de prueba implementados: UPR465236.1a-UPR465236.1e
 *
 * @author Irene Zamanillo Zubizarreta
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class MainPresenterTest {
    private MainPresenter sut;
    private List<Gasolinera> gasolineras;
    private List<Gasolinera> gasolinerasPrecio;
    private List<Gasolinera> gasolinerasDistancia;
    private List<Gasolinera> gasolinerasSinPrecio;
    private List<Gasolinera> gasolinerasSinPrecioOrdenadas;

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

        //Solo se da valor a los campos utilizados durante el test (precio, distancia, id)

        //Lista generica de gasolineras (precio mas alto a mas bajo; distancia mas cercana a mas lejana)
        double j = 0.0;
        for (int i = 0; i < 4; i++) {
            Gasolinera g = new Gasolinera();
            g.setId(String.valueOf(i));
            g.setNormal95(String.valueOf(200-i));
            g.setDieselA(String.valueOf(100-i));
            g.setLatitud(String.valueOf(43.4635088652 + j));
            g.setLongitud(String.valueOf(-3.83251340152 + j));
            j += 10.0;
            gasolineras.add(g);
        }

        //Para conseguir algo de variacion en el test, se introducen valores manualmente en la ultima gasolinera
        //De esta forma ordenar la lista no implica solo darle la vuelta
        Gasolinera g = new Gasolinera();
        g.setId(String.valueOf(4));
        g.setNormal95(String.valueOf(200-4));
        g.setDieselA(String.valueOf(100-4));
        //Coordenadas en Australia
        g.setLatitud(String.valueOf(-25.274398));
        g.setLongitud(String.valueOf(133.775136));
        gasolineras.add(0, g);

        //Lista de gasolineras ordenada por precio
        gasolinerasPrecio.add(gasolineras.get(0));
        gasolinerasPrecio.add(gasolineras.get(4));
        gasolinerasPrecio.add(gasolineras.get(3));
        gasolinerasPrecio.add(gasolineras.get(2));
        gasolinerasPrecio.add(gasolineras.get(1));

        //Lista de gasolineras ordenada por distancia
        gasolinerasDistancia.add(gasolineras.get(1));
        gasolinerasDistancia.add(gasolineras.get(2));
        gasolinerasDistancia.add(gasolineras.get(3));
        gasolinerasDistancia.add(gasolineras.get(4));
        gasolinerasDistancia.add(gasolineras.get(0));

        //Comportamiento de los mocks
        when(viewMock.getGasolineraRepository()).thenReturn(repositoryMock);
        when(repositoryMock.getGasolineras()).thenReturn(gasolineras); //en la mayoria de los casos

        //Gestion de la distancia
        //Las coordenadas estan tomadas de las que se usan manualmente en el emulador
        // y corresponden a Santander
        when(prefsMock.getString("latitud")).thenReturn("43.4635088652");
        when(prefsMock.getString("longitud")).thenReturn("-3.83251340152");
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
    }

    //Corresponde a UPR465236.1b
    @Test
    public void doSyncInitOrdenarPrecio() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(2);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolinerasPrecio));
        verify(repositoryMock).getGasolineras();
    }

    //Corresponde a UPR465236.1c
    @Test
    public void doSyncInitNoOrdenar() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(0);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolineras));
        verify(repositoryMock).getGasolineras();
    }

    //Corresponde a UPR465236.1d
    @Test
    public void doSyncInitOrdenarErrorPref() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(5);
        sut.init();

        assert(sut.getShownGasolineras().equals(gasolineras));
        verify(repositoryMock).getGasolineras();
    }

    //Corresponde a UPR465236.1e
    @Test
    public void doSyncInitErrorCarga() {
        //Comportamiento de los mocks
        when(repositoryMock.getGasolineras()).thenReturn(null);
        sut.init();

        verify(repositoryMock).getGasolineras();
        verify(viewMock).showLoadError();
    }
}
