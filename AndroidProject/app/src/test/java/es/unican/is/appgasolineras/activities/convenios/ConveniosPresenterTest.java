package es.unican.is.appgasolineras.activities.convenios;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.database.sqlite.SQLiteException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class ConveniosPresenterTest {
    //Tests unitarios para el presentador de los Convenios
    private ConveniosPresenter sut;
    @Mock
    private IConveniosContract.View mockView;
    @Mock
    private ConvenioDao mockDao;
    @Mock
    private GasolineraDatabase mockDb;
    private List<Convenio> convenios;

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
    @Before
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitCorrecto(){
        sut = new ConveniosPresenter(mockView);
        convenios = new ArrayList<Convenio>();
        llenarDatos();

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockDao);
        when(mockDao.getAll()).thenReturn(convenios);

        sut.init();
        assert(sut.shownConvenios.equals(convenios));
        verify(mockView).showConvenios(convenios);
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
    }

    @Test
    public void testInitErrorCarga(){
        sut = new ConveniosPresenter(mockView);

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockDao);
        when(mockDao.getAll()).thenThrow(new SQLiteException());

        try{
            sut.init();
        }
        catch (SQLiteException exception){}
        assert(sut.shownConvenios==null);
        verify(mockView).showLoadError();
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
    }

    @Test
    public void testInitVacio(){
        sut = new ConveniosPresenter(mockView);
        convenios = new ArrayList<Convenio>();

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockDao);
        when(mockDao.getAll()).thenReturn(convenios);

        sut.init();
        verify(mockView).showListaConveniosVacia();
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
        verify(mockDao).getAll();
    }

}