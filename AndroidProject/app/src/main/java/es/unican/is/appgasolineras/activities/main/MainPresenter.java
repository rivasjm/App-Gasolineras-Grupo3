package es.unican.is.appgasolineras.activities.main;

import static es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter.ORDENAR;

import java.util.Collections;
import java.util.List;

import es.unican.is.appgasolineras.common.Callback;
import es.unican.is.appgasolineras.common.GasolineraPrecioComparator;
import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;

public class MainPresenter implements IMainContract.Presenter {

    private final IMainContract.View view;
    private IGasolinerasRepository repository;

    private List<Gasolinera> shownGasolineras;

    private final IPrefs prefs;
    public MainPresenter(IMainContract.View view, IPrefs prefs) {

        this.view = view;
        this.prefs=prefs;
    }

    @Override
    public void init() {
        //TODO gestionar preferencia
        if (repository == null) {
            repository = view.getGasolineraRepository();
        }

        if (repository != null) {
            doSyncInit();
        }
    }

    private void doAsyncInit() {
        repository.requestGasolineras(new Callback<List<Gasolinera>>() {
            @Override
            public void onSuccess(List<Gasolinera> data) {
                view.showGasolineras(data);
                shownGasolineras = data;
                view.showLoadCorrect(data.size());
            }

            @Override
            public void onFailure() {
                shownGasolineras = null;
                view.showLoadError();
            }
        });
    }
    private void ordenarPorPrecio(){
        Collections.sort(shownGasolineras, new GasolineraPrecioComparator(){});
    }
    private void ordenar(int sort){
        if(sort==2){
            ordenarPorPrecio();
        }
    }
    private void doSyncInit() {
        List<Gasolinera> data = repository.getGasolineras();

        if (data != null) {
            int sort = prefs.getInt(ORDENAR);
            shownGasolineras = data;
            if(sort!=0){
                ordenar(sort);
            }

            view.showGasolineras(shownGasolineras);

            view.showLoadCorrect(shownGasolineras.size());

        } else {
            shownGasolineras = null;
            view.showLoadError();
        }
    }

    @Override
    public void onGasolineraClicked(int index) {
        if (shownGasolineras != null && index < shownGasolineras.size()) {
            Gasolinera gasolinera = shownGasolineras.get(index);
            view.openGasolineraDetails(gasolinera);
        }
    }

    @Override
    public void onAceptarGpsClicked() {
        //TODO es el modo de ver sin la distancia
    }

    @Override
    public void onReintentarGpsClicked() {
        view.init(); // TODO ver si es otro metodo, no tiene refresh o recreate
    }

    public List<Gasolinera> getShownGasolineras() {
        return shownGasolineras;
    }
}
