package es.unican.is.appgasolineras.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase que representa la tabla "convenios" de la base de datos. Cada convenio tiene los siguientes campos:
 * La tabla almacena en cada fila un determinado repostaje, con los siguientes datos:
 *      Marcha
 *      Descuento
 */

@Entity(tableName = "convenios")
public class Convenio {
    @PrimaryKey
    private String marca;
    private int descuento;

    public Convenio() {}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
}
