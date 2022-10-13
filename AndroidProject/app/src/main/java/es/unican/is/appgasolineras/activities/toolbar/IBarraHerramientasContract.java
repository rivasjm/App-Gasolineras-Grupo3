package es.unican.is.appgasolineras.activities.toolbar;

import androidx.appcompat.app.AppCompatActivity;

public interface IBarraHerramientasContract {
    public interface Presenter {
        /**
         * This method should be used by the View to notifiy the Presenter that the
         * Info button has been clicked
         */
        void onInfoClicked();

        /**
         * This method should be used by the View to notify the Presenter that the
         * Refresh button has been clicked
         */
        void onRefreshClicked();

        /**
         * This method should be used by the View to notify the Presenter that the
         * Convenios button has been clicked
         */
        void onConveniosClicked();

        /**
         * This method should be used by the View to notify the Presenter that the
         * Historial Repostajes button has been clicked
         */
        void onHistorialRepostajesClicked();

        /**
         * This method should be used by the View to notify the Presenter that the
         * Logo button has been clicked
         */
        void onLogoClicked();
    }

    public interface View {
        /**
         * The View is requested to open the Info view
         */
        void openInfoView();

        /**
         * The View is requested to open the Convenios view
         */
        void openConveniosView();

        /**
         * The View is requested to open the Historial Repostaje view
         */
        void openHistorialRepostajeView();

        /**
         * The View is requested to open the Main view
         */
        void openMainView();

        /**
         * The View is requested to return the activity to which it is related.
         */
        AppCompatActivity getActivity();
    }
}
