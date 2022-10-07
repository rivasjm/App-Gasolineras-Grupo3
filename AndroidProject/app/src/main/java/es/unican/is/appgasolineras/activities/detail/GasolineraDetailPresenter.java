package es.unican.is.appgasolineras.activities.detail;

import android.util.Log;

import es.unican.is.appgasolineras.model.Gasolinera;

/**
 * Clase que se encarga de hacer el calculo del precio sumario de la gasolina y
 * pasarselo a la vista, para que esta no haga calculos, y verificar datos erroneos.
 */
public class GasolineraDetailPresenter {

    private Gasolinera gasolinera;

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
        try {
            // reemplazar coma por punto antes de convertir a double
            double precioDiesel = Double.parseDouble(
                    gasolinera.getDieselA().replace(',', '.'));
            double precio95 = Double.parseDouble(
                    gasolinera.getNormal95().replace(',', '.'));
            // si un precio es negativo
            if (precio95 <= 0 || precioDiesel<= 0) {
                return "-";
            }
            double precioSumario = (precio95 * 2+ precioDiesel * 1) / 3.0;
            // mostrar precio con coma
            String precio = Double.toString(precioSumario);
            precio = precio.replace('.', ',');
            precio = precio.substring(0, 4);
            return precio;
        } catch (NumberFormatException e) { // si no se puede parsear el numero
            return  "-";
        }
    }

    public String getRotulo() {
        if (gasolinera.getRotulo() == null || gasolinera.getRotulo() == "") {
            return "-";
        }
        return gasolinera.getRotulo();
    }

    public String getCp() {
        if (gasolinera.getCp() == null || gasolinera.getCp() == "") {
            return "-";
        }
        return gasolinera.getCp();
    }

    public String getDireccion() {
        if (gasolinera.getDireccion() == null || gasolinera.getDireccion() == "") {
        return "-";
    }
        return gasolinera.getDireccion(); }

    public String getMunicipio() {
        if (gasolinera.getMunicipio() == null || gasolinera.getMunicipio() == "") {
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
        if (gasolinera.getSchedule() == null || gasolinera.getSchedule() == "") {
            return "-";
        }
        return gasolinera.getSchedule();
    }
}
