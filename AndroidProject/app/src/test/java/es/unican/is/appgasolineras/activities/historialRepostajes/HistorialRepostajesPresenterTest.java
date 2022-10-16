package es.unican.is.appgasolineras.activities.historialRepostajes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import es.unican.is.appgasolineras.model.Repostaje;

/**
 * Test del presentador del historial de repostajes.
 *
 * @author Ivan Ortiz del Noval
 */
public class HistorialRepostajesPresenterTest {

    private HistorialRepostajesPresenter sut;

    @Mock
    private IHistorialRepostajesContract.View viewMock;
    // TODO: ver como es la interaccion en la DAO, si se usa repository, DAO o que, si no no puedo avanzar
    @Mock
    private IHistorialRepostajesRepository repositoryMock;

    @Before
    public void setUp() throws Exception {
        // inicializar mocks siempre
        MockitoAnnotations.openMocks(this);
        // no defino aqui comportamiento porque varia en cada caso
    }

    @Test
    public void initCorrectoTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // definir comportamiento mock
        //TODO
        when(viewMock.getHistorialRepostajesRepository()).thenReturn();
    }



    @Test
    public void onHomeClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // ver que si se llama al metodo se llama a la vista para volver
        sut.onHomeClicked();
        verify(viewMock).openMainView();
    }

    @Test
    public void onAceptarClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // ver que si se llama al metodo se llama a la vista para volver
        sut.onAceptarClicked();
        verify(viewMock).openMainView();
    }

    @Test
    public void onReintentarClickedTest() {
        sut = new HistorialRepostajesPresenter(viewMock);
        // ver que si se llama al metodo se llama otra vez a init
        sut.onReintentarClicked();
        verify(sut).init();
    }
}