package es.unican.is.appgasolineras.activities.main;

import static es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasPresenter.ORDENAR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import java.util.List;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.toolbar.BarraHerramientasView;
import es.unican.is.appgasolineras.common.Callback;
import es.unican.is.appgasolineras.common.prefs.Prefs;
import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.repository.GasolinerasRepository;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;
import es.unican.is.appgasolineras.activities.detail.GasolineraDetailView;

public class MainView extends AppCompatActivity implements IMainContract.View {

    private IMainContract.Presenter presenter;
    private BarraHerramientasView barraHerramientasView;
    private Prefs prefs;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;

    /*
    Activity lifecycle methods
     */

    /**
     * This method is automatically called when the activity is created
     * It fills the activity with the widgets (buttons, lists, etc.)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.prefs = Prefs.from(this);

        // obtener la ubicacion (el cliente para obtenerla, se recoge con el metodo)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Toolbar
        barraHerramientasView = new BarraHerramientasView(findViewById(R.id.toolbar), this);

        presenter = new MainPresenter(this, prefs);
        presenter.init();
        this.init();
    }

    /**
     * Respuesta para el presenter de la ubicacion del dispositivo.
     * @param cb Callback
     * @return null si no se tienen permisos de ubicacion o hay un fallo raro, la ultima ubicacion
     * del dispositivo si se tienen permisos y va bien.
     */
    public Location getLocation(Callback<Location> cb) {
        // ver si se tiene alguno de los permisos
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO ver si hacer permission request de Android, es mejor que la emergente
            return null; // devuelvo null si no tiene los permisos
        }
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(this,
                location -> {
                    // Got current location. In some rare situations this can be null.
                    if (location != null) {
                        // devolver la ubicacion obtenida
                        currentLocation = location;
                        cb.onSuccess(currentLocation); // pasar al success la ubicacion
                    }
                });
        return currentLocation;
    }

    @Override
    protected void onDestroy(){
        this.prefs.putInt(ORDENAR,0);
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return barraHerramientasView.onCreateOptionsMenu(menu, true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return barraHerramientasView.onOptionsItemSelected(item);
    }

    /*
    IMainContract.View methods
     */

    @Override
    public void init() {
        // init UI listeners
        ListView lvGasolineras = findViewById(R.id.lvGasolineras);
        lvGasolineras.setOnItemClickListener((parent, view, position, id) -> {
            presenter.onGasolineraClicked(position);
        });
    }

    @Override
    public IGasolinerasRepository getGasolineraRepository() {
        return new GasolinerasRepository(this);
    }

    @Override
    public void showGasolineras(List<Gasolinera> gasolineras) {
        GasolinerasArrayAdapter adapter = new GasolinerasArrayAdapter(this, gasolineras,
                currentLocation);
        ListView list = findViewById(R.id.lvGasolineras);
        list.setAdapter(adapter);
    }

    @Override
    public void showLoadCorrect(int gasolinerasCount) {
        String text = getResources().getString(R.string.loadCorrect);
        Toast.makeText(this, String.format(text, gasolinerasCount), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadError() {
        String text = getResources().getString(R.string.loadError);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGpsError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog;

        builder.setMessage(R.string.gpsError);
        builder.setPositiveButton(R.string.aceptar, (dialogInterface, i)
                -> dialogInterface.cancel());
        builder.setNegativeButton(R.string.reintentar, (dialogInterface, i)
                -> presenter.onReintentarGpsClicked());

        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void openGasolineraDetails(Gasolinera gasolinera) {
        Intent intent = new Intent(this, GasolineraDetailView.class);
        intent.putExtra(GasolineraDetailView.INTENT_GASOLINERA, gasolinera);
        startActivity(intent);
    }
}
