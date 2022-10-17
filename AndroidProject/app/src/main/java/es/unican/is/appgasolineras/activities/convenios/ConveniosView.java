package es.unican.is.appgasolineras.activities.convenios;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.detail.GasolineraDetailView;
import es.unican.is.appgasolineras.activities.main.GasolinerasArrayAdapter;
import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasView;
import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.repository.GasolinerasRepository;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class ConveniosView extends AppCompatActivity implements  IConveniosContract.View {

    private IConveniosContract.Presenter presenter;
    private BarraHerramientasView barraHerramientasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenios_view);

        // Toolbar
        barraHerramientasView = new BarraHerramientasView(findViewById(R.id.toolbar), this);

        presenter = new ConveniosPresenter(this);
        presenter.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return barraHerramientasView.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return barraHerramientasView.onOptionsItemSelected(item);
    }

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
    public void showLoadCorrect(int conveniosCount) {
        String text = getResources().getString(R.string.loadCorrectConvenios);
        Toast.makeText(this, String.format(text, conveniosCount), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadError() {
        String text = getResources().getString(R.string.loadErrorConvenios);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
