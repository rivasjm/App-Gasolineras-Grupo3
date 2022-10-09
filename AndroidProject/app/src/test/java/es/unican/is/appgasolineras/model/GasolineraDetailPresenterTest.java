package es.unican.is.appgasolineras.model;

import static org.junit.Assert.assertEquals;

import android.util.Log;

import org.junit.Test;
import junit.framework.TestCase;

import es.unican.is.appgasolineras.activities.detail.GasolineraDetailPresenter;
import es.unican.is.appgasolineras.activities.detail.GasolineraDetailView;

public class GasolineraDetailPresenterTest {
    private static final String REPSOL = "REPSOL";

    @Test
    public void testGetRotulo() {
        Gasolinera gasolinera = new Gasolinera();
        // Caso 1 → datos correctos
        gasolinera.setDieselA("1.739");
        gasolinera.setNormal95("1.859");
        GasolineraDetailPresenter sumario = new GasolineraDetailPresenter(gasolinera);
        String sumarioS = sumario.getPrecioSumario();
        assertEquals(sumarioS,"1,81");

        // Caso 2 → Datos ausentes
        //Dato del diesel ausente
        gasolinera.setDieselA("-");
        sumarioS=sumario.getPrecioSumario();
        assertEquals(sumarioS,"1,85");
        //Dato de ambos ausente
        gasolinera.setNormal95("-");
        sumarioS=sumario.getPrecioSumario();
        assertEquals(sumarioS,"-");

        //Caso 3 → Valores negativos
        gasolinera.setNormal95("1.739");
        gasolinera.setDieselA("-2.14");
        sumarioS=sumario.getPrecioSumario();
        assertEquals(sumarioS,"1,73");
    }
}
