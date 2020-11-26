package me.guillem.testingpalette;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * * Created by Guillem on 25/11/20.
 */
public class AdapterColors extends RecyclerView.Adapter<AdapterColors.ViewHolderDatos> {

    private final List<List<String>> poo;

    public AdapterColors(List<List<String>> poo) {
        this.poo = poo;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_layout, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.assignData(poo.get(position));

    }

    @Override
    public int getItemCount() {
        return poo.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView rgb, hsl, population, title;
        CardView card;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            rgb = itemView.findViewById(R.id.t_color_rgb);
            hsl = itemView.findViewById(R.id.t_color_hsl);
            population = itemView.findViewById(R.id.t_population);
            card = itemView.findViewById(R.id.card_view);
            title = itemView.findViewById(R.id.t_title);

        }
        public void assignData(List<String> s) {
            title.setText(s.get(0));

            String RGBcolor = "#" + Integer.toHexString(Integer.parseInt(s.get(1))).toUpperCase();
            rgb.setText("RGB Color: " + RGBcolor);

            hsl.setText("HSL Color: " + s.get(2));

            population.setText("Population: "+ s.get(3));

            card.setCardBackgroundColor((Color.parseColor(RGBcolor)));

            int textColor = Color.parseColor("#" + Integer.toHexString(Integer.parseInt(s.get(4))));
            int bodyColor = Color.parseColor("#" + Integer.toHexString(Integer.parseInt(s.get(5))));

            title.setTextColor(textColor);
            rgb.setTextColor(bodyColor);
            hsl.setTextColor(bodyColor);
            population.setTextColor(bodyColor);

            // Swatch [RGB: #ff808890] [HSL: [210.0, 0.067226894, 0.53333336]] [Population: 20] [Title Text: #8b000000] [Body Text: #c2000000]
        }
    }
}
