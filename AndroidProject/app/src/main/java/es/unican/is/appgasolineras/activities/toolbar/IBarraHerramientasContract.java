package es.unican.is.appgasolineras.activities.toolbar;

import androidx.appcompat.app.AppCompatActivity;

/**
 * La Activity BarraHerramientas esta compuesta por un Presenter y una View, los cuales deben
 * implementar los metodos definidos en las siguientes interfaces.
 */
public interface IBarraHerramientasContract {

    public interface Presenter {
        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el boton
         * Info ha sido pulsado.
         */
        void onInfoClicked();

        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el boton
         * Refrescar ha sido pulsado.
         */
        void onRefreshClicked();

        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el boton
         * Convenios ha sido pulsado.
         */
        void onConveniosClicked();

        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el boton
         * Historial Repostajes ha sido pulsado.
         */
        void onHistorialRepostajesClicked();

        /**
         * Este metodo debe ser usado por la View para notificar al Presenter que el Logo
         * ha sido pulsado.
         */
        void onLogoClicked();
    }

    /**
     * Una vista para la Activity BarraHerramientas debe implementar esta funcionalidad.
     * Estos metodos estan destinados a ser utilizados por el Presenter.
     */
    public interface View {
        /**
         * Se solicita a la View que abra la vista Info.
         */
        void openInfoView();

        /**
         * Se solicia a la View que abra la vista Convenios.
         */
        void openConveniosView();

        /**
         * Se solicita a la View que abra la vista Historial Repostajes.
         */
        void openHistorialRepostajeView();

        /**
         * Se solicita a la View que abra la vista principal.
         */
        void openMainView();

        /**
         * Devuelve la Activity con la que la View esta ligada.
         * @return la Activity con la que la toolbar esta ligada.
         */
        AppCompatActivity getActivity();
    }
}
