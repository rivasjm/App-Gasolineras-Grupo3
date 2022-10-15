package es.unican.is.appgasolineras.activities.historialRepostajes;


import java.util.List;

import es.unican.is.appgasolineras.common.Callback;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.model.Repostaje;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;

public class HistorialRepostajesPresenter implements IHistorialRepostajesContract.Presenter{

    private final IHistorialRepostajesContract.View view;

    List<Repostaje> shownRepostajes;
    private IHistorialRepostajesRepository repository;

    public HistorialRepostajesPresenter(IHistorialRepostajesContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        if (repository == null) {
            repository = view.getHistorialRepostajesRepository();
        }

        if (repository != null) {
            doSyncInit();
        }
    }

    private void doSyncInit() {
        shownRepostajes = repository.getHistorialRepostajes();
        if(shownRepostajes == null) {
            view.showLoadError();
        }else {
            view.showHistorialRepostajes(shownRepostajes);
        }
    }

    @Override
    public void onHomeClicked() {
        view.openMainView();

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
