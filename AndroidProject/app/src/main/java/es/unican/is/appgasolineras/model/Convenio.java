package es.unican.is.appgasolineras.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Clase que representa la tabla "convenios" de la base de datos. Cada convenio tiene los siguientes campos:
 * La tabla almacena en cada fila un determinado repostaje, con los siguientes datos:
 *      Marcha
 *      Descuento
 */

@Entity(tableName = "convenios")
public class Convenio {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String marca;
    private int descuento;
    @SerializedName("Rótulo")                       private String rotulo;

    public Convenio() {
        id = 0;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

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
