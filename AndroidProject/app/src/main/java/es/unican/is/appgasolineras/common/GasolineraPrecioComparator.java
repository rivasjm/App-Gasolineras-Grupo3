package es.unican.is.appgasolineras.common;

import java.util.Comparator;

import es.unican.is.appgasolineras.activities.detail.GasolineraDetailPresenter;
import es.unican.is.appgasolineras.model.Gasolinera;

public class GasolineraPrecioComparator implements Comparator<Gasolinera> {
    /**
     * Compara gasolineras por su precio sumario.
     * @param g1 Una
     * @param g2 Otra
     * @return -1 si Una tiene un precio sumario inferior que el de la otra gasolinera
     *          0 si el precio sumario es el mismo
     *          1 si Una tiene un precio sumario superior que el de la otra gasolinera
     */
    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {
        GasolineraDetailPresenter sumarioA = new GasolineraDetailPresenter(g1);
        GasolineraDetailPresenter sumarioB = new GasolineraDetailPresenter(g2);
        return sumarioA.getPrecioSumario().compareToIgnoreCase(sumarioB.getPrecioSumario());
    }
}
