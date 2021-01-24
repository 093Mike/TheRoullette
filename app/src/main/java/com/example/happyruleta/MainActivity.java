package com.example.happyruleta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Debug;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> premios;
    ArrayList <EditText> jugadores;
    LinearLayout linearLayout;
    TextView num_premios_text;
    int num_premios;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.scrollEdits);
        premios =  new ArrayList<>();
        jugadores = new ArrayList<>();
        num_premios_text = findViewById(R.id.textView2);
        EditText jug = createEditText();
        jugadores.add(jug);
        EditText jug2 = createEditText();
        jugadores.add(jug2);
        num_premios=2;
        num_premios_text.setText(String.valueOf(num_premios));
        items();
    }


    public void empezarRuleta(View v) {
        boolean continuar=false;
        Log.d("SIZE",""+jugadores.size());
        for (int i=0 ; i < jugadores.size() ; i++){
            Log.d("NAME", jugadores.get(i).getText().toString());
            if(jugadores.get(i).getText().toString().equals("")){
                continuar = true;
            }
        }
        if(!continuar){
            premios.clear();
            for(int i=0;i<jugadores.size();i++){
                if(i<num_premios){
                    premios.add((premios.size()+1)+" - "+jugadores.get(i).getText().toString());
                }
            }
            Intent startRoulete = new Intent(this,LarouletteClass.class);
            startRoulete.putExtra("array",premios);
            startRoulete.putExtra("numeroPremios",num_premios);
            startActivity(startRoulete);
        }
        else{
            Toast.makeText(this, "RELLENA TODOS LOS ITEMS / PREMIOS", Toast.LENGTH_SHORT).show();
        }

    }

    public EditText  createEditText () {
        EditText jug = new EditText(getApplication());
        jug.setId(jugadores.size()+1);
        jug.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        jug.setLayoutParams(p);

        return jug;
    }

    public void items(){
        linearLayout.removeAllViews();
        for (int i = 0 ; i < jugadores.size() ; i++){
            linearLayout.addView(jugadores.get(i));
        }

    }

    public void sumar(View view) {
        if(num_premios!=10) {
            num_premios++;
            num_premios_text.setText(String.valueOf(num_premios));
            EditText jug = createEditText();
            jugadores.add(jug);
            items();

        }
    }

    public void restar(View view) {
        if(num_premios!=2){
            num_premios--;
            num_premios_text.setText(String.valueOf(num_premios));
            jugadores.remove(jugadores.size()-1);

            items();
        }
    }
}
