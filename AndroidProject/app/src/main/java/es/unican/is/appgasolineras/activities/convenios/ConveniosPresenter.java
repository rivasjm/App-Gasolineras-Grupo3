package es.unican.is.appgasolineras.activities.convenios;

import java.util.List;

import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class ConveniosPresenter implements IConveniosContract.Presenter {

    private final IConveniosContract.View view;
    private List<Convenio> shownConvenios;

    public ConveniosPresenter(IConveniosContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        final GasolineraDatabase db = view.getDatabase();
        final ConvenioDao conveniosDao = db.convenioDao();

        //insertaDatosTemp(conveniosDao);

        List<Convenio> data = conveniosDao.getAll();

        if (data != null) {
            view.showConvenios(data);
            shownConvenios = data;
            view.showLoadCorrect(data.size());
        } else {
            shownConvenios = null;
            view.showLoadError();
        }
    }

    private void insertaDatosTemp(ConvenioDao conveniosDao) {
        Convenio c1 = new Convenio();
        c1.setMarca("Campsa");
        c1.setDescuento(20);
        Convenio c2 = new Convenio();
        c2.setMarca("Galp");
        c2.setDescuento(5);
        conveniosDao.insertConvenio(c1);
        conveniosDao.insertConvenio(c2);
    }
}
