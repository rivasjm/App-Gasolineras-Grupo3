package es.unican.is.appgasolineras.activities.detail;


import android.location.Location;

import es.unican.is.appgasolineras.common.DistanceUtilities;
import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Gasolinera;

/**
 * Clase que se encarga de hacer el calculo del precio sumario de la gasolina y
 * pasarselo a la vista, para que esta no haga calculos, y verificar datos erroneos.
 */
public class GasolineraDetailPresenter {

    private Gasolinera gasolinera;
    private IPrefs prefs;

    /**
     * Constructor, pasa la gasolinera.
     * @param gasolinera la gasolinera
     * @param prefs Preferencias de la aplicacion para recoger la ubicacion
     */
    public GasolineraDetailPresenter(Gasolinera gasolinera, IPrefs prefs) {
        this.gasolinera = gasolinera;
        this.prefs = prefs;
    }
    /**
     * Constructor, pasa la gasolinera.
     * @param gasolinera la gasolinera
     */
    public GasolineraDetailPresenter(Gasolinera gasolinera) {
        this.gasolinera = gasolinera;
    }

    /**
     * Retorna el precio sumario de una gasolinera (media del diesel A y gasolina 95).
     * @return String con el precio sumario en dos decimales, - si un precio es negativo
     */
    public String getPrecioSumario() {
        if (gasolinera.getPrecioSumario() == null || gasolinera.getPrecioSumario().equals("")) {
            return "-";
        }
        return gasolinera.getPrecioSumario();
    }

    public String getRotulo() {
        if (gasolinera.getRotulo() == null || gasolinera.getRotulo().equals("")) {
            return "-";
        }
        return gasolinera.getRotulo();
    }

    public String getMunicipio() {
        if (gasolinera.getMunicipio() == null || gasolinera.getMunicipio().equals("")) {
            return "-";
        }
        return gasolinera.getMunicipio();
    }

    public String getDieselA() {
        if (gasolinera.getDieselA() == null ||
                Double.parseDouble(gasolinera.getDieselA().replace(',', '.')) <= 0) {
        return "-";
        }
        return gasolinera.getDieselA(); }

    public String getNormal95() {
        if (gasolinera.getNormal95() == null ||
                Double.parseDouble(gasolinera.getNormal95().replace(',', '.')) <= 0) {
        return "-";
        }
        return gasolinera.getNormal95();
    }

    public String getSchedule() {
        if (gasolinera.getSchedule() == null || gasolinera.getSchedule().equals("")) {
            return "-";
        }
        return gasolinera.getSchedule();
    }

    public String getDistancia() {
        String txt = "-";
        Location actual;
        try {
            actual = new Location("");
            actual.setLongitude(Double.parseDouble(prefs.getString("longitud")));
            actual.setLatitude(Double.parseDouble(prefs.getString("latitud")));
        } catch (NumberFormatException e) {
            // si no recoge un numero (la preferencia no existe)
            return txt;
        }
        Location gasLoc = gasolinera.getLocation();
        if (gasLoc != null) {
            txt = DistanceUtilities.distanceBetweenLocations(gasLoc, actual) + " km";
        }
        return txt;
    }
}