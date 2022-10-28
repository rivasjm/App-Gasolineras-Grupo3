package es.unican.is.appgasolineras.common;

/**
 * Implementacion de una coordenada con valores decimales.
 */
public class Location implements ILocation {

    private double latitud;
    private double longitud;

    @Override
    public double getLatitud() {
        return this.latitud;
    }

    @Override
    public double getlongitud() {
        return this.longitud;
    }

    public Location(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }
}
