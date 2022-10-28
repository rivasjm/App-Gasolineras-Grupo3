package es.unican.is.appgasolineras.activities.historialRepostajes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.main.MainView;
import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasView;
import es.unican.is.appgasolineras.model.Repostaje;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

public class HistorialRepostajesView extends AppCompatActivity implements IHistorialRepostajesContract.View {

    private BarraHerramientasView barraHerramientasView;
    private IHistorialRepostajesContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_repostajes_view);

        // Toolbar
        barraHerramientasView = new BarraHerramientasView(findViewById(R.id.toolbar), this);

        presenter = new HistorialRepostajesPresenter(this);
        presenter.init();

        TextView tv = findViewById(R.id.tvHistorialRepostajeMessage);
        tv.setText("HISTORIAL REPOSTAJES");
    }

    /*
     *Metodos de la toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return barraHerramientasView.onCreateOptionsMenu(menu, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return barraHerramientasView.onOptionsItemSelected(item);
    }


    /*
     *Metodos del IHistorialRepostajesContract.View
     */
    @Override
    public void refresh() {
        recreate();
    }

    @Override
    public void openMainView() {
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
    }


    @Override
    public void showLoadError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.repostajesFalloAccesoDatos);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.onAceptarClicked();
            }
        });
        builder.setNegativeButton(R.string.reintentar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.onReintentarClicked();
            }
        });
        builder.setPositiveButton("Aceptar", ((DialogInterface dialogInf1, int which) -> {
            presenter.onAceptarClicked();
        }));
        builder.setNegativeButton(R.string.reintentar, ((DialogInterface dialogInf2, int which) -> {
            presenter.onReintentarClicked();
        }));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public GasolineraDatabase getGasolineraDb() {
        return GasolineraDatabase.getDB(this);
    }

    @Override
    public void showHistorialRepostajes(List<Repostaje> historialRepostajes) {
        HistorialRepostajesArrayAdapter adapter = new HistorialRepostajesArrayAdapter(this, historialRepostajes);
        ListView list = findViewById(R.id.lvHistoricoGasolineras);
        list.setAdapter(adapter);
    }

    @Override
    public void showHistorialVacio() {
        TextView tv = findViewById(R.id.tvRepostajesVacios);
        tv.setText(getResources().getString(R.string.repostajesVacios));
    }
}
