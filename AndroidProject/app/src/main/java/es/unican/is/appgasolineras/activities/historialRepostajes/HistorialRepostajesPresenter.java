package es.unican.is.appgasolineras.activities.historialRepostajes;


import static es.unican.is.appgasolineras.repository.db.GasolineraDatabase.getDB;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import androidx.room.Room;

import java.util.List;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.common.Callback;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.model.Repostaje;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;
import es.unican.is.appgasolineras.repository.db.RepostajeDao;
import es.unican.is.appgasolineras.repository.db.RepostajeDao_Impl;

public class HistorialRepostajesPresenter implements IHistorialRepostajesContract.Presenter{

    private final IHistorialRepostajesContract.View view;

    List<Repostaje> shownRepostajes;


    public HistorialRepostajesPresenter(IHistorialRepostajesContract.View view) {
        this.view = view;
    }


    @Override
    public void init() {
        //Instanciamos la base de datos
        final GasolineraDatabase db = view.getGasolineraDb();
        //a partir de la base de datos podemos obtener la dao de repostajes con laqueinteractuar
        final RepostajeDao repDao = db.repostajeDao();

        // Ejecutar la primera vez que se ejecuta la app para tener algun dato
        // repDao.deleteAll();
        // insertaDatosTemp(repDao);


        //Estructurapara sacarelhistorial de repostajes de nuestra base de datos
        try {
            shownRepostajes = repDao.getAll();
        } catch (SQLiteException e)
        {
            view.showLoadError();
        }

        //Si esta vacía mostramos se lo indicamos al usuario
        if(shownRepostajes == null) {
            view.showHistorialVacio();
        }else {
            view.showHistorialRepostajes(shownRepostajes);
        }
    }


    /**
     * Inserta dos repostajes en la DAO para poder probar la historia de usuario "Ver
     * convenios de precios".
     * @param repostajeDao la DAO de repostajes.
     */
    public void insertaDatosTemp(RepostajeDao repostajeDao) {

        Repostaje r1 = new Repostaje();
        r1.setFechaRepostaje("18/10/2022");
        r1.setPrecio("25.0");
        r1.setLocalizacion("CARRETERA CASTILLO SIETEVILLAS KM, S/N");
        r1.setLitros("13");
        Repostaje r2 = new Repostaje();
        r2.setFechaRepostaje("10/10/2022");
        r2.setPrecio("55.83");
        r2.setLocalizacion("CARRETERA ARGOÑOS SOMO KM. 28,7");
        r2.setLitros("26");
        repostajeDao.insertRepostaje(r1);
        repostajeDao.insertRepostaje(r2);
    }

    public void insertaDatosErroneosTemp(RepostajeDao repostajeDao) {
        for (int i = 0; i < 10; i++) {
            Repostaje r = new Repostaje();
            r.setId(i+100);
            r.setFechaRepostaje(String.format("%d/%d/2023", i, 3+i)); // algunas fechas mal
            if (i != 4) { // poner un litro nulo
                r.setLitros(Double.toString(-23 + i*8)); // algun litro negativo
            }
            r.setPrecio(Double.toString(2 * (-3 + i))); // algun precio negativo y poco realista
            if (i > 2) {
                r.setLocalizacion(String.format("Direccion %d", i)); // alguna direccion vacia
            }
            repostajeDao.insertRepostaje(r); // meter repostaje incorrecto en la DAO
        }
    }

    @Override
    public void onAceptarClicked() {
        view.openMainView();
    }

    @Override
    public void onReintentarClicked() {
        init();
    }


}
