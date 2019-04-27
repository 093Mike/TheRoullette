package com.example.happyruleta;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class LarouletteClass extends AppCompatActivity implements Animation.AnimationListener {

    boolean blnButtonRotation = true;
    ImageView selected, imageRoulette;
    Button b_start;
    ImageView ruleta;
    TextView miembros;
    int[] id_imagenes = new int[]{R.drawable.roulette_2,R.drawable.roulette_3,R.drawable.roulette_4,R.drawable.roulette_5,
            R.drawable.roulette_6,R.drawable.roulette_7,R.drawable.roulette_8,R.drawable.roulette_9,R.drawable.roulette_10};
    long lngDegrees = 0;
    int intNumber = 6;
    ArrayList<String> premios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laroulette);
        ruleta = findViewById(R.id.rouletteImage);
        b_start = findViewById(R.id.buttonStart);
        selected = findViewById(R.id.imageSelected);
        imageRoulette = findViewById(R.id.rouletteImage);
        miembros = findViewById(R.id.premios);
        premios =(ArrayList<String>) getIntent().getSerializableExtra("array");
        miembros.setText("");
        for (int i=0;i<premios.size();i++){
            miembros.setText(miembros.getText().toString()+premios.get(i)+"\n");
        }
        intNumber = getIntent().getIntExtra("numeroPremios",0);
        ruleta.setImageResource(id_imagenes[intNumber-2]);


    }

    @Override
    public void onAnimationStart(Animation animation) {
        blnButtonRotation = false;
        b_start.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        int valor = (int) (((double) this.intNumber) - Math.floor(((double) this.lngDegrees) / (360.0d / ((double) this.intNumber))));

        Toast toast = Toast.makeText(this, " " + premios.get(valor-1) + " ", Toast.LENGTH_LONG);
        toast.show();
        blnButtonRotation = true;
        b_start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View v) {
        if (this.blnButtonRotation) {
            int ran = new Random().nextInt(1080) + 3600;
            this.lngDegrees = 0;
            Animation rotateAnimation = new RotateAnimation((float) this.lngDegrees, (float)
                    (this.lngDegrees + ((long) ran)), 1, 0.5f, 1, 0.5f);

            this.lngDegrees = (this.lngDegrees + ((long) ran)) % 360;
            rotateAnimation.setDuration((long) ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);

        }
    }

}
