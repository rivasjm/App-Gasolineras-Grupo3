package es.unican.is.appgasolineras.activities.toolbar;

public class BarraHerramientasPresenter implements IBarraHerramientasContract.Presenter {

    private final IBarraHerramientasContract.View view;

    public BarraHerramientasPresenter(IBarraHerramientasContract.View view) {
        this.view = view;
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
    }
}