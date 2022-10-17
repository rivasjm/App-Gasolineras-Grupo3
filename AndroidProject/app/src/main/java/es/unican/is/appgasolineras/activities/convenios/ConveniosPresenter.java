package es.unican.is.appgasolineras.activities.convenios;

import java.util.List;

import es.unican.is.appgasolineras.common.Callback;
import es.unican.is.appgasolineras.model.Convenio;

public class ConveniosPresenter implements IConveniosContract.Presenter {

    private final IConveniosContract.View view;
    // TODO: private IConveniosRepository repository;

    private List<Convenio> shownConvenios;

    public ConveniosPresenter(IConveniosContract.View view) {
        this.view = view;
    }

    // TODO:
//    @Override
//    public void init() {
//        if (repository == null) {
//            repository = view.getConveniosRepository();
//        }
//
//        if (repository != null) {
//            doAsyncInit();
//        }
//    }

    // TODO:
//    private void doAsyncInit() {
//        repository.requestConvenios(new Callback<List<Convenio>>() {
//            @Override
//            public void onSuccess(List<Convenio> data) {
//                view.showConvenios(data);
//                shownConvenios = data;
//                view.showLoadCorrect(data.size());
//            }
//
//            @Override
//            public void onFailure() {
//                shownConvenios = null;
//                view.showLoadError();
//            }
//        });
//    }
}
