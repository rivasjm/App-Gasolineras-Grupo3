package es.unican.is.appgasolineras.activities.historialRepostajes;

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
import es.unican.is.appgasolineras.model.Repostaje;

public class HistorialRepostajesArrayAdapter extends ArrayAdapter<Repostaje> {
    public HistorialRepostajesArrayAdapter(@NonNull Context context, @NonNull List<Repostaje> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Repostaje repostajes = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_historial_repostajes_view, parent, false);
            //check
        }


        // localizacion
        showInfo(convertView, R.id.tvLocalizacion, repostajes.getLocalizacion());

        // fecha repostaje
        showInfo(convertView, R.id.tvFecha, repostajes.getFechaRepostaje());

        //Litros
        showInfo(convertView, R.id.tvLitros, repostajes.getLitros());

        // Precio
        showInfo(convertView, R.id.tvPrecio, repostajes.getPrecio());

        return convertView;
    }

    private void showInfo(View convertView, int p, String info) {
        TextView tv = convertView.findViewById(p);
        tv.setText(info);
    }


}
