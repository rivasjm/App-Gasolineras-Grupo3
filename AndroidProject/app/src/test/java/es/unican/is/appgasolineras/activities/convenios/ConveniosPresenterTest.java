package es.unican.is.appgasolineras.activities.convenios;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;

public class ConveniosPresenterTest {
    private ConveniosPresenter sut;
    @Mock
    private IConveniosContract.View mockView;
    @Mock
    private ConvenioDao mockDao;
    private List<Convenio> convenios;
    // TODO: definir la DAO, el presenter debería tener una ConveniosDao en el constructor

    private void llenarDatos(){
        Convenio c1 = new Convenio();
        c1.setMarca("Campsa");
        c1.setDescuento(20);
        Convenio c2 = new Convenio();
        c2.setMarca("Galp");
        c2.setDescuento(5);
        convenios.add(c1);
        convenios.add(c2);
    }
    @Test
    public void testValidInit(){
        MockitoAnnotations.openMocks(this);
        sut = new ConveniosPresenter(mockView);
        convenios = new ArrayList<Convenio>();
        llenarDatos();
        when(mockDao.getAll()).thenReturn(convenios);
        sut.init();
        verify(mockView).showLoadCorrect(convenios.size());
    }
    @Test
    public void testInvalidInitEmpty(){
        MockitoAnnotations.openMocks(this);
        sut = new ConveniosPresenter(mockView);
        convenios = new ArrayList<Convenio>();
        when(mockDao.getAll()).thenReturn(null);
        sut.init();
        verify(mockView).showLoadError();
    }
}