package es.unican.is.appgasolineras.activities.historialRepostajes;


import static es.unican.is.appgasolineras.repository.db.GasolineraDatabase.getDB;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import androidx.room.Room;

import java.util.List;

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
        final GasolineraDatabase db = view.getGasolineraDb();
        final RepostajeDao repDao = db.repostajeDao();

        // Ejecutar la primera vez que se ejecuta la app
        //insertaDatosTemp(repDao);

        try {
            shownRepostajes = repDao.getAll();
        } catch (SQLiteException e)
        {
            view.showLoadError();
        }

        if(shownRepostajes == null) {
            view.showHistorialVacio();
        }else {
            view.showHistorialRepostajes(shownRepostajes);
        }
    }

    private void insertaDatosTemp(RepostajeDao repostajeDao) {
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

    @Override
    public void onAceptarClicked() {
        view.openMainView();
    }

    @Override
    public void onReintentarClicked() {
        init();
    }

}
