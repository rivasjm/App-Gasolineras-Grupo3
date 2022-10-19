package es.unican.is.appgasolineras.activities.toolbar;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Field;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.activities.convenios.ConveniosView;
import es.unican.is.appgasolineras.activities.historialRepostajes.HistorialRepostajesView;
import es.unican.is.appgasolineras.activities.info.InfoView;
import es.unican.is.appgasolineras.activities.main.MainView;

public class BarraHerramientasView extends AppCompatActivity implements IBarraHerramientasContract.View {

    private IBarraHerramientasContract.Presenter presenter;
    private Toolbar toolbar;
    private AppCompatActivity activity;

    public BarraHerramientasView(Toolbar toolbar, AppCompatActivity activity) {
        this.toolbar = toolbar;
        this.activity = activity;
        presenter = new BarraHerramientasPresenter(this);
        setUpToolBar();
    }

    /**
     * Establece una Toolbar como ActionBar de la activity.
     * Establece el logo de la aplicacion y le da la funcionalidad de volver a
     * la ventana principal al pulsarlo.
     */
    private void setUpToolBar() {
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setLogo(R.drawable.logo_repost_app_50);
        setLogoOnClickListener(toolbar, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogoClicked();
            }
        });
        activity.getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    /**
     * Metodo para poder asignar un listener al logo de la toolbar.
     * @param toolbar Toolbar de la activity.
     * @param listener funcion que se quiere asignar al logo.
     */
    private void setLogoOnClickListener(Toolbar toolbar, View.OnClickListener listener) {
        try {
            Class<?> toolbarClass = Toolbar.class;
            Field logoField = toolbarClass.getDeclaredField("mLogoView");
            logoField.setAccessible(true);
            ImageView logoView = (ImageView) logoField.get(toolbar);

            if(logoView != null) {
                logoView.setOnClickListener(listener);
            }
        }
        catch (NoSuchFieldException |
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crear un menu en la activity (menu de tres puntos arriba a la izquierda).
     * @param menu
     * @return
     */
<<<<<<< HEAD
    @Override
=======
>>>>>>> feature/464974-verConvenioPrecios
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = activity.getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Listener para el menu de tres puntos arriba a la izquierda.
     * @param item
     * @return
     */
<<<<<<< HEAD
    @Override
=======
>>>>>>> feature/464974-verConvenioPrecios
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuInfo:
                presenter.onInfoClicked();
                return true;
            case R.id.menuRefresh:
                presenter.onRefreshClicked();
                return true;
            case R.id.menuConvenios:
                presenter.onConveniosClicked();
                return true;
            case R.id.menuHistorialRepostajes:
                presenter.onHistorialRepostajesClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void openInfoView() {
        Intent intent = new Intent(activity, InfoView.class);
        activity.startActivity(intent);
    }

    @Override
    public void openConveniosView() {
        Intent intent = new Intent(activity, ConveniosView.class);
        activity.startActivity(intent);
    }

    @Override
    public void openHistorialRepostajeView() {
        Intent intent = new Intent(activity, HistorialRepostajesView.class);
        activity.startActivity(intent);
    }

    @Override
    public void openMainView() {
        Intent intent = new Intent(activity, MainView.class);
        activity.startActivity(intent);
    }

    @Override
    public AppCompatActivity getActivity() {
        return activity;
    }
}
