package es.unican.is.appgasolineras.activities.detail;


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
        double precioSumario;
        double precioDiesel;
        double precio95;
        try {
             precioDiesel = Double.parseDouble(
                    gasolinera.getDieselA().replace(',', '.'));
        } catch (NumberFormatException e){
            precioDiesel = 0;
        }
        try {
            precio95 = Double.parseDouble(
                    gasolinera.getNormal95().replace(',', '.'));
        } catch (NumberFormatException e){
            precio95 = 0;
        }
        // gestionar que se devuelve dependiendo de los valores disponibles
        if (precio95 <= 0 && precioDiesel <= 0) {
            return "-";
        } else if (precio95 <= 0) {
            precioSumario = precioDiesel;
        } else if (precioDiesel <= 0) {
            precioSumario = precio95;
        } else {
            precioSumario = (precio95 * 2+ precioDiesel * 1) / 3.0;
        }
        // mostrar precio con coma
        String precio = Double.toString(precioSumario);
        precio = precio.replace('.', ',');
        precio = precio.substring(0, 4);
        return precio;
    }

    public String getRotulo() {
        if (gasolinera.getRotulo() == null || gasolinera.getRotulo().equals("")) {
            return "-";
        }
        return gasolinera.getRotulo();
    }

    public String getCp() {
        if (gasolinera.getCp() == null || gasolinera.getCp().equals("")) {
            return "-";
        }
        return gasolinera.getCp();
    }

    public String getDireccion() {
        if (gasolinera.getDireccion() == null || gasolinera.getDireccion().equals("")) {
        return "-";
    }
        return gasolinera.getDireccion(); }

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
}
