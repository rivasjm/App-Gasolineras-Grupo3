package es.unican.is.appgasolineras.activities.convenios;

import java.util.List;

import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.IGasolinerasRepository;

/**
 * The Convenios Activity is composed of a Presenter and View, which must expose the methods
 * defined in the following interfaces.
 */
public interface IConveniosContract {
    /**
     * A Presenter for the Convenios Activity must implement this functionality
     * These methods (excluding init), are meant to be used by the View.
     */
    public interface Presenter {
        /**
         * Initialization method
         */
        // TODO: void init();
    }

    /**
     * A View for the Convenios Activity must implement this functionality
     * These methods (excluding init), are meant to be used by the Presenter.
     */
    public interface View {

        /**
         * Returns the Convenios Repository object.
         * This object can be used to access convenios from
         * different sources (REST API, persisted DB, etc.)
         * This method is in the View because it generally requires access to the
         * Android Context, and this is available in the View
         *
         * @return the Repository object to access convenios
         */
        // TODO: IConveniosRepository getConveniosRepository();

        /**
         * The View is requested to show a list of convenios
         *
         * @param convenios the list of gas stations
         */
        void showConvenios(List<Convenio> convenios);

        /**
         * The View is requested to show an alert informing that the convenios were loaded
         * correctly
         * @param conveniosCount the number of convenios that were loaded
         */
        void showLoadCorrect(int conveniosCount);

        /**
         * The View is requested to show an alert informing that there was an error while
         * loading the convenio
         */
        void showLoadError();
    }
}
