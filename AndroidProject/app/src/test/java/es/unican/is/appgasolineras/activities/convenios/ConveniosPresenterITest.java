package es.unican.is.appgasolineras.activities.convenios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

//import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class ConveniosPresenterITest {
    //Tests de integración para el presentador de los Convenios
    private ConveniosPresenter sut;
    private ConvenioDao dao;
    @Mock
    private IConveniosContract.View mockView;
    private List<Convenio> convenios_;
    @Before
    public  void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    //Funciona cuando está vacío porque no está cargando bien la base de datos
    public void llenarDatos(){
        Convenio c1 = new Convenio();
        c1.setId(1);
        c1.setMarca("Campsa");
        c1.setDescuento(20);
        Convenio c2 = new Convenio();
        c2.setId(2);
        c2.setMarca("Galp");
        c2.setDescuento(5);
        convenios_.add(c1);
        convenios_.add(c2);
    }
    @Test
    public void testInitVacio(){
        sut = new ConveniosPresenter(mockView);
        when(mockView.getDatabase()).thenReturn(GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));
        dao = GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()).convenioDao();
        sut.init();
        verify(mockView).showListaConveniosVacia();
        verify(mockView).getDatabase();
    }
    //la base de datos ya tiene cargados los datos de convenios, pero al obtener la database, no figuran los convenios
    @Test
    public void testInitCorrecto(){
        sut= new ConveniosPresenter(mockView);
        when(mockView.getDatabase()).thenReturn(GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()));
        dao = GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext()).convenioDao();
        sut.insertaDatosTemp(dao);
        convenios_ = new ArrayList<Convenio>();
        llenarDatos();


        sut.init();
        //assert(sut.shownConvenios.equals(convenios_));
        verify(mockView).getDatabase();
        verify(mockView).showConvenios(convenios_);
        dao.deleteAll();

    }

}

        /*
        when(mockView.getDatabase()).thenReturn(mockDb);
        when(mockDb.convenioDao()).thenReturn(mockDao);
        when(mockDao.getAll()).thenReturn(convenios);

        sut.init();
        //assert(sut.shownConvenios.equals(convenios));
        //verify(mockView).showConvenios(convenios);
        verify(mockView).getDatabase();
        //verify(mockDb).convenioDao();
        verify(mockView).showListaConveniosVacia();*/