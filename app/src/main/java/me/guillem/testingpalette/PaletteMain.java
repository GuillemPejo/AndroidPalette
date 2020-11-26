package me.guillem.testingpalette;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by Guillem on 21/11/20.
 */
public class PaletteMain extends AppCompatActivity {

    private ImageView imageView;

    RecyclerView recyler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        imageView = findViewById(R.id.imageView);

        Bitmap b = imageFrom(imageView);
        imageView.setImageBitmap(b);

        Palette p = Palette.from(b).maximumColorCount(32).generate();

        List<Palette.Swatch> pss;
        pss = p.getSwatches();

        String[] titles = {"Vibrant","Vibrant Dark","Vibrant Light","Muted","Muted Dark","Muted Light"};

        List<List<String>> poo = new ArrayList<List<String>>();

                for (int j = 0; j < 5; j++) {
                    poo.add(new ArrayList<String>());
                    Palette.Swatch ps = pss.get(j);
                    poo.get(j).add(titles[j]);
                    poo.get(j).add(String.valueOf(ps.getRgb()));
                    poo.get(j).add(((ps.getHsl()[0])+", "+(ps.getHsl()[1])+", "+(ps.getHsl()[2])));
                    poo.get(j).add(String.valueOf(ps.getPopulation()));
                    poo.get(j).add(String.valueOf(ps.getTitleTextColor()));
                    poo.get(j).add(String.valueOf(ps.getBodyTextColor()));
                }

                getWindow().setStatusBarColor(pss.get(0).getRgb());
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable((pss.get(0).getRgb())));
                recyler = findViewById(R.id.recylcerview);
                recyler.setLayoutManager(new LinearLayoutManager(this));
                AdapterColors adapter = new AdapterColors(poo);
                recyler.setAdapter(adapter);

    }

    public Bitmap imageFrom(ImageView imageView){
        Bitmap bitmap;
        if(getIntent().getParcelableExtra("BitmapImage")!=null){
            return getIntent().getParcelableExtra("BitmapImage");
        }else{
            imageView.setImageResource(R.drawable.image);
            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            return bitmap;
        }
    }


}
