package es.unican.is.appgasolineras.activities.historialRepostajes;

import java.util.List;

import es.unican.is.appgasolineras.model.Gasolinera;
import es.unican.is.appgasolineras.model.Repostaje;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;

public interface IHistorialRepostajesContract {

    public interface Presenter {
        /**
         * Initialization method
         */
        void init();

        /**
         * This method should be used by the View to notifiy the Presenter that the
         * home button has been clicked
         */
        void onHomeClicked();

        /**
         * This method should be used by the View to notifiy the Presenter that the
         * acceptar button has been clicked
         */
        void onAceptarClicked();

        /**
         * This method should be used by the View to notifiy the Presenter that the
         * reintentar button has been clicked
         */
        void onReintentarClicked();



    }

    public interface View {
        /**
         * Initialization method
         */
        void init();

        /**
         * The View is requested to open the main view
         */
        void openMainView();

        /**
         * The View is requested to show
         */
        void showLoadError();

        /**
         * Returns the HistorialRepostajes Repository object.
         * This object can be used to access all the historial de repostajes from the db
         * This method is in the View because it generally requires access to the
         * Android Context, and this is available in the View
         * @return the Repository object to access the historial
         */
        IHistorialRepostajesRepository getHistorialRepostajesRepository();

        /**
         * Esta vista muestra los repostajes
         * @param historialRepostajes la lista de repostajes
         */
        void showHistorialRepostajes(List<Repostaje> historialRepostajes);
    }
}
