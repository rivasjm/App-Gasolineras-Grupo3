package es.unican.is.appgasolineras.common;

/**
 * Interfaz para tener una coordenada o ubicacion.
 * No se puede importar la clase Point y se ha creado esta porque sus valores son enteros.
 * No pongo setters porque se supone que las ubicaciones no cambian.
 */
public interface ILocation {
    double getLatitud();

    double getlongitud();

    // no puedo definir el constructor
}
