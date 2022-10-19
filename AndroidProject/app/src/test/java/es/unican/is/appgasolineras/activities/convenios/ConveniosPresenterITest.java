package es.unican.is.appgasolineras.activities.convenios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
    private static GasolineraDatabase db;
    @Before
    public void set(){
        MockitoAnnotations.openMocks(this);
    }
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
    public void testInitCorrecto(){
        sut= new ConveniosPresenter(mockView);
        db = GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext());
        when(mockView.getDatabase()).thenReturn(GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext(),true));
        dao = db.convenioDao();
        sut.insertaDatosTemp(dao);
        convenios_ = new ArrayList<Convenio>();
        llenarDatos();

        sut.init();
        //para implementar esto deberiamos poder hacer el equals por valor, elemento a elemento, sobre escribir
        //assert(sut.shownConvenios.equals(convenios_));
        verify(mockView).getDatabase();
        verify(mockView).showConvenios(sut.shownConvenios);
    }
    @Test
    public void testInitVacio(){
        sut = new ConveniosPresenter(mockView);
        db = GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext());
        when(mockView.getDatabase()).thenReturn(GasolineraDatabase.getDB(ApplicationProvider.getApplicationContext(),true));
        sut.init();
        verify(mockView).showListaConveniosVacia();
        verify(mockView).getDatabase();
    }
}