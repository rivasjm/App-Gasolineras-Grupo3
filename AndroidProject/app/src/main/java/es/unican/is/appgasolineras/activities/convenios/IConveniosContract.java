package es.unican.is.appgasolineras.activities.convenios;

import java.util.List;

import es.unican.is.appgasolineras.model.Convenio;
import es.unican.is.appgasolineras.repository.db.GasolineraDatabase;

/**
 * La Activity Convenios esta compuesta por un Presenter y una View, las cuales deben implementar
 * los metodos definidos en las siguientes interfaces.
 */
public interface IConveniosContract {
    /**
     * Un Presenter para la Activity Convenios debe implementar esta funcionalidad.
     * Estos metodos (menos init) estan destinados a ser utilizados por la View.
     */
    public interface Presenter {

        /**
         * Metodo de inicializacion. Obtiene los convenios de la DAO y los muestra.
         * Si hay un fallo en el acceso a datos se muestra una ventana emergente informando
         * del error, y ofreciendo la opcion de reintentar o volver a la vista principal.
         * Si la lista de convenios esta vacia se muestra un fondo vacio (en blanco) con un
         * texto indicandolo.
         */
        void init();

        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el boton
         * Aceptar de la ventana emergente que notfica el error de acceso a datos ha
         * sido pulsado.
         */
        void onErrorAceptarClicked();

        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el boton
         * Reintentar de la ventana emergente que notfica el error de acceso a datos ha
         * sido pulsado.
         */
        void onErrorReintentarClicked();
    }

    /**
     * Una vista para la Activity Convenios debe implementar esta funcionalidad.
     * Estos metodos estan destinados a ser utilizados por el Presenter.
     */
    public interface View {

        /**
         * Devuelve la base de datos con su contexto.
         * @return el objeto GasolineraDatabase para acceder a la base de datos.
         */
        GasolineraDatabase getDatabase();

        /**
         * Se solicita a la View que muestre una lista de convenios.
         * @param convenios la lista de convenios.
         */
        void showConvenios(List<Convenio> convenios);

        /**
         * Se solicita a la View que muestre una ventana emergente informando de que ha ocurrido
         * un error al acceder a los datos y ofreciendo la opcion de reintentar o volver a la
         * vista principal.
         */
        void showLoadError();

        /**
         * Se solicita a la View que muestre un mensaje informando que la lista de convenios
         * esta vacia.
         */
        void showListaConveniosVacia();

        /**
         * Se solicita a la View que abra la vista principal.
         */
        void openMainView();

        /**
         * Se solicita a la View que vuelva a crear la vista.
         */
        void refresh();
    }
}
