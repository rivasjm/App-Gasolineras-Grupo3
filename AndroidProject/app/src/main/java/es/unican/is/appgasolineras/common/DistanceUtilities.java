package es.unican.is.appgasolineras.common;

import android.location.Location;

/**
 * Clase estatica para utilidades con distancias.
 */
public class DistanceUtilities {

    /**
     * Devuelve la distancia entre dos ubicaciones.
     * @param l1
     * @param l2
     * @return La distancia en km, con 2 decimales y coma decimal, entre las 2
     *         "-" si alguna de las ubicaciones es nula
     */
    public static String distanceBetweenLocations(Location l1, Location l2) {
        if (l1 == null || l2 == null) {
            return "-";
        }
        double distancia = l1.distanceTo(l2);
        String txt = String.format("%.2f", distancia).replace('.', ',');
        return txt;
    }
}
