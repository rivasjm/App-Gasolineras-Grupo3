package es.unican.is.appgasolineras.activities.convenios;

import static es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter.ANHADIR;

import android.database.sqlite.SQLiteException;

import java.util.List;

import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class ConveniosPresenter implements IConveniosContract.Presenter {

    private final IConveniosContract.View view;
    private List<Convenio> shownConvenios;
    private IPrefs prefs;

    public ConveniosPresenter(IConveniosContract.View view, IPrefs prefs) {
        this.view = view;
        this.prefs = prefs;
    }

    public ConveniosPresenter(IConveniosContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        final GasolineraDatabase db = view.getDatabase();
        final ConvenioDao conveniosDao = db.convenioDao();
        // Ejecutar solo la primera vez que se ejecuta la app
        List<Convenio> data = null;

        // Fallo en el acceso a datos
        try {
            data = conveniosDao.getAll();
        } catch (SQLiteException e) {
            view.showLoadError();
        }
        if (data!=null && !data.isEmpty()) {
            // Caso exito
            view.showConvenios(data);
            shownConvenios = data;
        } else {
            // No existen convenios para mostrar
            view.showListaConveniosVacia();
            shownConvenios = null;
        }

        if (prefs.getInt(ANHADIR) == 1) {
            onConvenioAnhadirClicked();
        }
    }

    @Override
    public void onErrorAceptarClicked() {
        view.openMainView();
    }

    @Override
    public void onErrorReintentarClicked() {
        view.refresh();
    }

    @Override
    public void onConvenioAnhadirClicked() {
        view.showAnhadirConvenio();
    }

    @Override
    public void onConvenioCancelarClicked() {

    }

    @Override
    public void onSiSobreescribirClicked() {

    }

    @Override
    public void onNoSobreescribirClicked() {

    }

    @Override
    public void onErrorDescuentoAceptarClicked() {

    }

    /**
     * Inserta dos convenios en la DAO para poder probar la historia de usuario "Ver
     * convenios de precios".
     * @param conveniosDao la DAO de convenios.
     */
    public void insertaDatosTemp(ConvenioDao conveniosDao) {
        Convenio c1 = new Convenio();
        c1.setMarca("Campsa");
        c1.setDescuento(20);
        Convenio c2 = new Convenio();
        c2.setMarca("Galp");
        c2.setDescuento(5);
        Convenio c3 = new Convenio();
        c3.setMarca("Repsol");
        c3.setDescuento(-10);
        Convenio c4 = new Convenio();
        c4.setMarca("");
        conveniosDao.insertConvenio(c1);
        conveniosDao.insertConvenio(c2);
        conveniosDao.insertConvenio(c3);
        conveniosDao.insertConvenio(c4);
    }

    public List<Convenio> getShownConvenios(){
        return shownConvenios;
    }
}