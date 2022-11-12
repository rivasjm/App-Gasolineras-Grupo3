package es.unican.is.appgasolineras.activities.convenios;

import static es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter.ANHADIR;

import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;
import es.unican.is.appgasolineras.repository.db.ConvenioDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDao;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class ConveniosPresenter implements IConveniosContract.Presenter {

    private final IConveniosContract.View view;
    private List<Convenio> shownConvenios;
    private IPrefs prefs;

    private ConvenioDao conveniosDao;

    public ConveniosPresenter(IConveniosContract.View view, IPrefs prefs) {
        this.view = view;
        this.prefs = prefs;
    }

    @Override
    public void init() {
        final GasolineraDatabase db = view.getDatabase();
        conveniosDao = db.convenioDao();
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
            //Extrae las marcas de todas las gasolineras
            Set<String> marcas = new HashSet<String>();
            List<Gasolinera> gasolineras = db.gasolineraDao().getAll();

            for (Gasolinera g: gasolineras) {
                marcas.add(g.getRotulo());
            }

            view.setMarcas(marcas);
            view.showAnhadirConvenio();
            prefs.putInt(ANHADIR, 0);
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
    public void onConvenioAnhadirClicked(View anhadirView) {
        //Lee los campos del usuario
        Spinner s = (Spinner) anhadirView.findViewById(R.id.spMarca);
        EditText e = (EditText) anhadirView.findViewById(R.id.etConvenioDescuento);
        Convenio c = new Convenio();

        String marca = s.getSelectedItem().toString();
        Integer descuento = Integer.parseInt(e.getText().toString());

        if (descuento == 0 || descuento == 100) {
            view.showErrorDescuento();
            return;
        }

        c.setMarca(marca);
        c.setDescuento(descuento);

        //Comprueba si ya existe un convenio asociado a la marca y persiste el convenio en la BD
        //si no estaba ya insertado
        Convenio convenioAnterior = conveniosDao.buscaConvenioPorMarca(c.getMarca());

        if (convenioAnterior != null) {
            view.showSobreescribirConvenio(c);
            conveniosDao.deleteConvenio(convenioAnterior);
        } else {
            insertaConvenio(c);
        }
    }

    @Override
    public void onConvenioCancelarClicked() {
        //No hace nada (solo cierra la ventana)
    }

    @Override
    public void onSiSobreescribirClicked(Convenio c) {
        insertaConvenio(c);
    }

    private void insertaConvenio(Convenio c) {
        conveniosDao.insertConvenio(c);
        view.refresh();
        view.showConvenioAnhadido();
    }

    @Override
    public void onNoSobreescribirClicked() {
        //No hace nada (solo cierra la ventana)
    }

    @Override
    public void onErrorDescuentoAceptarClicked() {
        view.showAnhadirConvenio();
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