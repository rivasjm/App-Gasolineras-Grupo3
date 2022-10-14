package es.unican.is.appgasolineras.repository;

import java.util.List;

import es.unican.is.appgasolineras.common.Callback;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.model.Repostaje;

/**
 * Repositorio para el acceso a los repostajes
 */

public interface IRepostajesRepository {

    public void requestRepostajes(Callback<List<Repostaje>> cb);

    public List<Repostaje> getRepostajes();
}
