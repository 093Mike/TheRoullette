package com.example.happyruleta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> premios;
    EditText edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8,edit9,edit10;
    EditText[] editTexts = new EditText[]{edit1,edit2,edit3,edit4,edit5,edit6,edit7,edit8,edit9,edit10};
    int[] id_edit = new int[]{R.id.editText,R.id.editText2,R.id.editText3,R.id.editText4,R.id.editText5,R.id.editText6,R.id.editText7,R.id.editText8,R.id.editText9,R.id.editText10};
    TextView num_premios_text;
    int num_premios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0 ; i<editTexts.length;i++){
            editTexts[i] = findViewById(id_edit[i]);
        }
        premios =  new ArrayList<>();
        num_premios_text = findViewById(R.id.textView2);
        num_premios=2;
        num_premios_text.setText(String.valueOf(num_premios));
        items();
    }


    public void empezarRuleta(View v) {
        premios.clear();
        for(int i=0;i<editTexts.length;i++){
            if(i<num_premios){
                premios.add(editTexts[i].getText().toString());
            }
        }
        Intent startRoulete = new Intent(this,LarouletteClass.class);
        startRoulete.putExtra("array",premios);
        startRoulete.putExtra("numeroPremios",num_premios);
        startActivity(startRoulete);
    }

    public void items(){
        for (int i = 0 ; i <  10 ;i++){
            if(i< num_premios){
                editTexts[i].setVisibility(View.VISIBLE);
            }
            else{
                editTexts[i].setVisibility(View.INVISIBLE);
            }
        }

    }

    public void sumar(View view) {
        if(num_premios!=10) {
            num_premios++;
            num_premios_text.setText(String.valueOf(num_premios));
            items();
        }
    }

    public void restar(View view) {
        if(num_premios!=2){
            num_premios--;
            num_premios_text.setText(String.valueOf(num_premios));
            items();
        }
    }
}
