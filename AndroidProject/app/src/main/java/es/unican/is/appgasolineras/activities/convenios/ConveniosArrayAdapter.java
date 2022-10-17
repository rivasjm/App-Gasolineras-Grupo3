package es.unican.is.appgasolineras.activities.convenios;

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
import es.unican.is.appgasolineras.model.Convenio;

public class ConveniosArrayAdapter extends ArrayAdapter<Convenio> {

    public ConveniosArrayAdapter(@NonNull Context context, @NonNull List<Convenio> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Convenio convenio = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_convenios_item, parent, false);
        }

        // logo
        setLogo(convertView, convenio);

        // marca
        showInfo(convertView, R.id.tvMarcaConvenio, convenio.getMarca());

        // descuento
        showInfo(convertView, R.id.tvDescuentoConvenio, String.valueOf(convenio.getDescuento()));

        return convertView;
    }

    private void showInfo(View convertView, int p, String info) {
        TextView tv = convertView.findViewById(p);
        tv.setText(info);
    }

    private void setLogo(View convertView, Convenio convenio) {
        String rotulo = convenio.getRotulo().toLowerCase();
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
            ImageView view = convertView.findViewById(R.id.ivLogoConvenio);
            view.setImageResource(imageID);
        }
    }
}
