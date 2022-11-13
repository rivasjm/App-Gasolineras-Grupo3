package es.unican.is.appgasolineras.activities.convenios;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter.ANHADIR;

import android.database.sqlite.SQLiteException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

/**
 * Test unitarios del presentador de convenios.
 *
 * @author Micaela Camila Patiño Hermosa y Alina Solonaru
 */
public class ConveniosPresenterTest {
    private ConveniosPresenter sut;
    private List<Convenio> convenios;

    @Mock
    private IConveniosContract.View mockView;
    @Mock
    private ConvenioDao mockConvenioDao;
    @Mock
    private GasolineraDatabase mockDb;
    @Mock
    private GasolineraDao mockGasolineraDao;
    @Mock
    private IPrefs mockPrefs;

    private void llenarDatos(Boolean anomalos){
        Convenio c1 = new Convenio();
        c1.setMarca("Campsa");
        c1.setDescuento(20);
        Convenio c2 = new Convenio();
        c2.setMarca("Galp");
        c2.setDescuento(5);
        convenios.add(c1);
        convenios.add(c2);
        if(anomalos){
            Convenio c3 = new Convenio();
            c3.setMarca("Repsol");
            c3.setDescuento(-10);
            convenios.add(c3);
            Convenio c4 = new Convenio();
            c4.setMarca("");
            convenios.add(c4);
        }
    }
    @Before
    public void setUp()  {
        // Inicializar mocks
        MockitoAnnotations.openMocks(this);
        when(mockPrefs.getInt(ANHADIR)).thenReturn(0);
    }

    @Test
    public void testInitCorrecto(){
        sut = new ConveniosPresenter(mockView, mockPrefs);
        convenios = new ArrayList<Convenio>();
        llenarDatos(false);

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenReturn(convenios);

        sut.init();
        assert(sut.getShownConvenios().equals(convenios));
        verify(mockView).showConvenios(convenios);
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
    }

    @Test
    public void testInitErrorCarga(){
        sut = new ConveniosPresenter(mockView, mockPrefs);

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenThrow(new SQLiteException());

        try{
            sut.init();
        }
        catch (SQLiteException exception){}
        assert(sut.getShownConvenios()==null);
        verify(mockView).showLoadError();
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
    }

    @Test
    public void testInitVacio(){
        sut = new ConveniosPresenter(mockView, mockPrefs);
        convenios = new ArrayList<Convenio>();

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenReturn(convenios);

        sut.init();
        verify(mockView).showListaConveniosVacia();
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
        verify(mockConvenioDao).getAll();
    }

    @Test
    public void testInitDatosAnomalos(){
        sut = new ConveniosPresenter(mockView, mockPrefs);
        convenios = new ArrayList<Convenio>();
        llenarDatos(true);

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenReturn(convenios);

        sut.init();
        assert(sut.getShownConvenios().equals(convenios));
        verify(mockView).showConvenios(convenios);
        verify(mockView).getDatabase();
        verify(mockDb).convenioDao();
    }

    /**
     * Test UPR464971.1a
     * Alina Solonaru
     */
    @Test
    public void testInitErrorCargaMarcas() {
        sut = new ConveniosPresenter(mockView, mockPrefs);

        // Definir comportamiento correcto de convenios para que no lance NullPointerException
        convenios = new ArrayList<Convenio>();
        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenReturn(convenios);

        // Definir comportamiento gasolineras, si hay error lanza excepcion
        when(mockPrefs.getInt(ANHADIR)).thenReturn(1);
        when(mockDb.gasolineraDao()).thenReturn(mockGasolineraDao);
        when(mockGasolineraDao.getAll()).thenThrow(new SQLiteException());

        // Comprobar funcionamiento
        sut.init(); // Recoge SQLiteException
        assert (sut.getGasolineras() == null);
        verify(mockView).getDatabase();
        verify(mockView).showLoadError();
    }

    @Test
    public void testOnErrorAceptarClicked(){
        sut = new ConveniosPresenter(mockView, mockPrefs);

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenReturn(null);

        sut.init();
        sut.onErrorAceptarClicked();
        verify(mockView).openMainView();
    }

    @Test
    public void onErrorReintentarClicked(){
        sut = new ConveniosPresenter(mockView, mockPrefs);

        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockConvenioDao);
        when(mockConvenioDao.getAll()).thenReturn(null);

        sut.init();
        sut.onErrorReintentarClicked();
        verify(mockView).refresh();
    }
}