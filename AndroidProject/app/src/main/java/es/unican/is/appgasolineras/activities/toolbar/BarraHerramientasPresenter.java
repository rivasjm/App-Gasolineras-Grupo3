package es.unican.is.appgasolineras.activities.toolbar;

import es.unican.is.appgasolineras.common.prefs.IPrefs;
import es.unican.is.appgasolineras.common.prefs.Prefs;

public class BarraHerramientasPresenter implements IBarraHerramientasContract.Presenter {
    private final IBarraHerramientasContract.View view;
    private final IPrefs prefs;
    public final static String ORDENAR="Ordenar";
    public BarraHerramientasPresenter(IBarraHerramientasContract.View view, final IPrefs  prefs ) {
        this.view = view;
        this.prefs = prefs;
    }

    @Override
    public void onInfoClicked() {
        view.openInfoView();
    }

    @Override
    public void onRefreshClicked() {
        view.getActivity().recreate();
    }

    @Override
    public void onConveniosClicked() {
        view.openConveniosView();
    }

    @Override
    public void onHistorialRepostajesClicked() {
        view.openHistorialRepostajeView();
    }

    @Override
    public void onLogoClicked() {
        view.openMainView();
        this.prefs.putInt(ORDENAR,0);
    }

    @Override
    public void onOrdenarDistanciaClicked() {
        view.showOrdenarDistanciaSelected();
        view.showOrdenarPrecioDeselected();
        // Creo que aqui iria el codigo de la funcionalidad

    }

    @Override
    public void onOrdenarPrecioClicked() {
        if(this.prefs.getInt(ORDENAR)!=2){
            view.showOrdenarPrecioSelected();
            view.showOrdenarDistanciaDeselected();
            this.prefs.putInt(ORDENAR,2);
            view.openMainView();
        }
        else{
            if(this.prefs.getInt(ORDENAR)==2){
                view.showOrdenarPrecioDeselected();
                this.prefs.putInt(ORDENAR,0);
                view.openMainView();
            }
        }
    }

    @Override
    public void onOrdenarDistanciaClicked() {
        view.showOrdenarDistanciaSelected();
        view.showOrdenarPrecioDeselected();
        // Creo que aqui iria el codigo de la funcionalidad

    }

    @Override
    public void onOrdenarPrecioClicked() {
        view.showOrdenarPrecioSelected();
        view.showOrdenarDistanciaDeselected();
        // Creo que aqui iria el codigo de la funcionalidad

    }
}
