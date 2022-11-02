package es.unican.is.appgasolineras.common;

import java.util.Comparator;

import es.unican.is.appgasolineras.activities.detail.GasolineraDetailPresenter;
import es.unican.is.appgasolineras.model.Gasolinera;

public class GasolineraPrecioComparator implements Comparator<Gasolinera> {
    /**
     * Compara gasolineras por su precio sumario.
     * @param g1 Una
     * @param g2 Otra
     * @return -1 si Una esta mas cerca que la otra de la ubicacion base
     *          0 si estan a la misma distancia
     *          1 si otra esta mas cerca que una de la ubicacion base
     */
    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {
        GasolineraDetailPresenter sumarioA = new GasolineraDetailPresenter(g1);
        GasolineraDetailPresenter sumarioB = new GasolineraDetailPresenter(g2);
        return sumarioA.getPrecioSumario().compareToIgnoreCase(sumarioB.getPrecioSumario());
    }
}
