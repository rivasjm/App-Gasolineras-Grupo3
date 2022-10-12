package es.unican.is.appgasolineras.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* Clase que representa a la tabla "convenios" de la base de datos. Cada convenio tiene los siguientes campos:
*   Marca
*   Descuento*/

@Entity(tableName = "convenios")
public class Convenio {
    @PrimaryKey
    private String id;

    private String marca;
    private int descuento;

    public Convenio() { id = "0";}

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
