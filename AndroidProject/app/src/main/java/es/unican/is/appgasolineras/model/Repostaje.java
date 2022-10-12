package es.unican.is.appgasolineras.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase que representa la tabla "repostaje" en la BD de la app.
 * La tabla almacena en cada fila un determinado repostaje, con los siguientes datos:
 *      Fecha del repostaje
 *      Localizacion
 *      Precio
 *      Litros
 */
@Entity(tableName = "repostajes", primaryKeys = {"fechaRepostaje", "localizacion"})
public class Repostaje {

    private String fechaRepostaje;
    private String localizacion;
    private String precio;
    private String litros;

    public Repostaje() {
        fechaRepostaje = "12/10/22";
        localizacion = "Localizacion";
    }

    public String getFechaRepostaje() {
        return fechaRepostaje;
    }

    public void setFechaRepostaje(String fechaRepostaje) {
        this.fechaRepostaje = fechaRepostaje;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getLitros() {
        return litros;
    }

    public void setLitros(String litros) {
        this.litros = litros;
    }
}