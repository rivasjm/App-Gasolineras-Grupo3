package es.unican.is.appgasolineras.activities.main;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import es.unican.is.appgasolineras.R;
import es.unican.is.appgasolineras.model.Gasolinera;

public class GasolinerasArrayAdapter extends ArrayAdapter<Gasolinera> {

    public GasolinerasArrayAdapter(@NonNull Context context, @NonNull List<Gasolinera> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gasolinera gasolinera = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_main_item, parent, false);
        }

        // logo
        setLogo(convertView, gasolinera);

        // name
        showInfo(convertView, R.id.tvName, gasolinera.getRotulo());

        // address
        showInfo(convertView, R.id.tvAddress, gasolinera.getDireccion());

        // 95 octanes price
        String label95 = getContext().getResources().getString(R.string.gasolina95label);
        showInfo(convertView, R.id.tv95Label, label95 + ":");
        showInfo(convertView, R.id.tv95, gasolinera.getNormal95());

        // diesel A price
        String labelDieselALabel = getContext().getResources().getString(R.string.dieselAlabel);
        showInfo(convertView, R.id.tvDieselALabel, labelDieselALabel + ":");
        showInfo(convertView, R.id.tvDieselA, gasolinera.getDieselA());

        return convertView;
    }

    private void showInfo(View convertView, int p, String info) {
        TextView tv = convertView.findViewById(p);
        tv.setText(info);
    }

    private void setLogo(View convertView, Gasolinera gasolinera) {
        String rotulo = gasolinera.getRotulo().toLowerCase();
        int imageID = getContext().getResources()
                .getIdentifier(rotulo, "drawable", getContext().getPackageName());

        // Si el rotulo son sólo numeros, el método getIdentifier simplemente devuelve
        // como imageID esos números, pero eso va a fallar porque no tendré ningún recurso
        // que coincida con esos números
        if (imageID == 0 || TextUtils.isDigitsOnly(rotulo)) {
            imageID = getContext().getResources()
                    .getIdentifier("generic", "drawable", getContext().getPackageName());
        }

        if (imageID != 0) {
            ImageView view = convertView.findViewById(R.id.ivLogo);
            view.setImageResource(imageID);
        }
    }
}
