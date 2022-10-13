package es.unican.is.appgasolineras.activities.historialRepostajes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasView;

public class HistorialRepostajesView extends AppCompatActivity {

    private BarraHerramientasView barraHerramientasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_repostajes_view);

        // Toolbar
        barraHerramientasView = new BarraHerramientasView(findViewById(R.id.toolbar), this);

        // Temporal
        TextView tv = findViewById(R.id.tvHistorialRepostajeMessage);
        tv.setText("HISTORIAL REPOSTAJES");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return barraHerramientasView.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return barraHerramientasView.onOptionsItemSelected(item);
    }
}
