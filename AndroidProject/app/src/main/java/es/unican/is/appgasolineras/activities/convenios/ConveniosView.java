package es.unican.is.appgasolineras.activities.convenios;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import android.content.Intent;

import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasView;
import java.util.List;
import java.util.Set;

import es.unican.is.appgasolineras.activities.main.MainView;
import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.common.prefs.Prefs;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class ConveniosView extends AppCompatActivity implements IConveniosContract.View {

    private IConveniosContract.Presenter presenter;

    private BarraHerramientasView barraHerramientasView;

    private IPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenios_view);
        this.prefs = Prefs.from(this);

        // Toolbar
        barraHerramientasView = new BarraHerramientasView(findViewById(R.id.toolbar), this);

        presenter = new ConveniosPresenter(this, prefs);
        presenter.init();
    }

    /*
    Metodos referentes a la toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return barraHerramientasView.onCreateOptionsMenu(menu, false, true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return barraHerramientasView.onOptionsItemSelected(item);
    }

    /*
    Metodos referentes a IConveniosContract.View
     */
    @Override
    public GasolineraDatabase getDatabase() {
        return GasolineraDatabase.getDB(this);
    }

    @Override
    public void showConvenios(List<Convenio> convenios) {
        ConveniosArrayAdapter adapter = new ConveniosArrayAdapter(this, convenios);
        ListView list = findViewById(R.id.lvConvenios);
        list.setAdapter(adapter);
    }

    @Override
    public void showConvenioAnhadido() {
        String text = getResources().getString(R.string.anhadirConvenioExito);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnhadirConvenio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Se crea una ventana emergente customizada para el convenio
        builder.setView(getLayoutInflater().inflate(R.layout.activity_convenios_anhadir, null));
        builder.setTitle(R.string.anhadirConvenioTitulo);
        builder.setPositiveButton(R.string.anhadir, (dialogInterface, i) ->  presenter.onConvenioAnhadirClicked());
        builder.setNegativeButton(R.string.cancelar, (dialogInterface, i) -> presenter.onConvenioCancelarClicked());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showSobreescribirConvenio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.conveniosFalloAccesoDatos);
        builder.setPositiveButton(R.string.aceptar, (dialogInterface, i) ->  presenter.onSiSobreescribirClicked());
        builder.setNegativeButton(R.string.cancelar, (dialogInterface, i) -> presenter.onNoSobreescribirClicked());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showErrorDescuento() {

    }

    public void cargaMarcas(Set<String> marcas) {
        Spinner spinner = findViewById(R.id.spMarca);
    }

    @Override
    public void showLoadError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.conveniosFalloAccesoDatos);
        builder.setPositiveButton(R.string.aceptar, (dialogInterface, i) ->  presenter.onErrorAceptarClicked());
        builder.setNegativeButton(R.string.reintentar, (dialogInterface, i) -> presenter.onErrorReintentarClicked());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showListaConveniosVacia() {
        TextView tv = findViewById(R.id.tvConveniosVacio);
        tv.setText(getResources().getString(R.string.conveniosListaVacia));
    }

    @Override
    public void openMainView() {
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
    }

    @Override
    public void refresh() {
        recreate();
    }
}
