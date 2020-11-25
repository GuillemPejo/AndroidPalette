package me.guillem.testingpalette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * * Created by Guillem on 21/11/20.
 */
public class PaletteMain extends AppCompatActivity {
    private LinearLayout rootLayout;
    private TextView textViewTitle;
    private TextView textViewBody;
    private ImageView imageView;

    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;

    private int swatchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        rootLayout = findViewById(R.id.root_layout);
        textViewTitle = findViewById(R.id.textTitle);
        textViewBody = findViewById(R.id.textBody);
        imageView = findViewById(R.id.imageView);

        imageView.getLayoutParams().height = 250;
        imageView.getLayoutParams().width = 250;
        imageView.requestLayout();

        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(250,250);
        imageView.setLayoutParams(parms);

        imageView.setImageResource(R.drawable.image);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        Palette.Builder from = Palette.from(bitmap);
        from.maximumColorCount(32);
        from.generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                vibrantSwatch = palette.getVibrantSwatch();
                lightVibrantSwatch = palette.getLightVibrantSwatch();
                darkVibrantSwatch = palette.getDarkVibrantSwatch();
                mutedSwatch = palette.getMutedSwatch();
                lightMutedSwatch = palette.getLightMutedSwatch();
                darkMutedSwatch = palette.getDarkMutedSwatch();

                List<Palette.Swatch> pss;
                List<List<String>> poo = null;
                LinearLayout linearLayout = new LinearLayout(PaletteMain.this);
                setContentView(linearLayout);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                ;
                pss = palette.getSwatches();
                for (int j = 0; j < 5; j++) {
                    Palette.Swatch ps = pss.get(j);
                    System.out.println(ps.toString());


                    //CardView card = new CardView(PaletteMain.this);
                    //LinearLayout cardInner = new LinearLayout(PaletteMain.this);

                    TextView tv_title = new TextView(PaletteMain.this);
                    tv_title.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tv_title.setText("Vibrant");

                    TextView tv_caption = new TextView(PaletteMain.this);
                    tv_caption.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tv_caption.setText("RGB: "+ps.getRgb()+" HSL: "+ps.getHsl()+" Population: "+ps.getPopulation()+" TitleText: "+ps.getTitleTextColor()+" BodyText: " +ps.getBodyTextColor());

                    linearLayout.addView(tv_title);
                    linearLayout.addView(tv_caption);





                }


/*
                System.out.println("LISTA");
                System.out.println("-------------------------");
                for (int i = 0; i <= poo.get(0).size() - 1; i++) {
                    System.out.println("Nom: " + poo.get(i).get(0) + " Color: "
                            + poo.get(i).get(1) + " Population: " + poo.get(i).get(2)
                            + " HSL: " + poo.get(i).get(3) + " BodyTextColor: " + poo.get(i).get(4) + " TitleTextColor: " + poo.get(i).get(4)
                    );
                    System.out.println("-------------------------");
                    }
*/



/*                for(Palette.Swatch model : pss) {
                    String color = "#" + Integer.toHexString(model.getRgb());
                    //textViewBody.setText("Color: " + color);
                    //textViewBody.setTextColor((Color.parseColor(color)));
                    System.out.println(color);

                }*/


        }
        });
    }

    public Bitmap imageFrom(ImageView imageView){
        Bitmap bitmap;
        if(getIntent()!=null){
            Intent intent = getIntent();
            bitmap = ((Bitmap) intent.getParcelableExtra("BitmapImage"));
            //bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        return bitmap;
        }

        else{
            imageView.setImageResource(R.drawable.image);
            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            return bitmap;
        }
    }

    public void next(View v) {
        Palette.Swatch currentSwatch = null;

        switch (swatchNumber) {
            case 0:
                currentSwatch = vibrantSwatch;
                textViewTitle.setText("Vibrant");
                break;
            case 1:
                currentSwatch = lightVibrantSwatch;
                textViewTitle.setText("Light Vibrant");
                break;
            case 2:
                currentSwatch = darkVibrantSwatch;
                textViewTitle.setText("Dark Vibrant");
                break;
            case 3:
                currentSwatch = mutedSwatch;
                textViewTitle.setText("Muted");
                break;
            case 4:
                currentSwatch = lightMutedSwatch;
                textViewTitle.setText("Light Muted");
                break;
            case 5:
                currentSwatch = darkMutedSwatch;
                textViewTitle.setText("Dark Muted");
                break;
        }

        if (currentSwatch != null) {
            rootLayout.setBackgroundColor(currentSwatch.getRgb());
            textViewTitle.setTextColor(currentSwatch.getTitleTextColor());
            textViewBody.setTextColor(currentSwatch.getBodyTextColor());
        } else {
            rootLayout.setBackgroundColor(Color.WHITE);
            textViewTitle.setTextColor(Color.RED);
            textViewBody.setTextColor(Color.RED);
        }

        if (swatchNumber < 5) {
            swatchNumber++;
        } else {
            swatchNumber = 0;
        }
    }
}
