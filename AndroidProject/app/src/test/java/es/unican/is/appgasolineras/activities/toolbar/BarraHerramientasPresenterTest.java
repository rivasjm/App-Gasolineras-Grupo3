package es.unican.is.appgasolineras.activities.toolbar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import es.unican.is.appgasolineras.activities.main.MainPresenter;
import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter;
import es.unican.is.appgasolineras.common.prefs.IPrefs;

/**
 * Test unitario del presentador de la barra de herramientas.
 *
 * Casos de prueba implementados: UBHPR465236.1a-UBHPR465236.1c
 *
 * @author Irene Zamanillo Zubizarreta
 */

public class BarraHerramientasPresenterTest {
    private BarraHerramientasPresenter sut;

    @Mock
    private IBarraHerramientasContract.View viewMock;

    @Mock
    private IPrefs prefsMock;

    @Before
    public void setUp() {
        //Inicializacion de los mocks
        MockitoAnnotations.openMocks(this);

        //Inicializacion generica
        sut = new BarraHerramientasPresenter(viewMock, prefsMock);
    }

    //Corresponde a UBHPR465236.1a
    //Se pulsa el boton de ordenar por precio sin que hubiese pulsado ninguno anteriormente
    @Test
    public void onOrdenarPrecioAscClickedInicial() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(0);
        sut.onOrdenarPrecioAscClicked();

        verify(viewMock).openMainView();
    }

    //Corresponde a UBHPR465236.1b
    //Se pulsa el boton de ordenar por precio tras pulsar el de ordenar por distancia
    @Test
    public void onOrdenarPrecioAscClickedDistanciaPrevia() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(1);
        sut.onOrdenarPrecioAscClicked();

        verify(viewMock).openMainView();
    }

    //Corresponde a UBHPR465236.1c
    //Se pulsa el boton de ordenar por precio tras haberlo pulsado previamente
    @Test
    public void onOrdenarPrecioAscClickedPrecioPrevio() {
        //Comportamiento de los mocks
        when(prefsMock.getInt(BarraHerramientasPresenter.ORDENAR)).thenReturn(2);
        sut.onOrdenarPrecioAscClicked();

        verify(viewMock).openMainView();
    }
}
