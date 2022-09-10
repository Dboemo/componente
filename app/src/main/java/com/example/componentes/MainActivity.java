package com.example.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btcidade;
Spinner comboestado,combocidade;
ArrayAdapter <String> adaptador1,adaptador2;
ListView listagem;
Switch chave;
SeekBar barra;
TextView txtvalor;
public String[] retornaCidade(String estado){
    String cid[]=null;
    if(estado.equals("RS"))
        cid=new String[]{"Santiago","Cacequi","São Vicente" };
    if(estado.equals("SC"))
        cid=new String[]{"Garopaba","Laguna","Florianópolis" };
    if(estado.equals("PR"))
        cid=new String[]{"Cascavel","Foz do Iguaçu","Curitiba" };
    if(estado.equals("-"))
        cid=new String[]{"-"};

    return cid;
}
    public void mensagem(String titulo, String mensagem, Context contexto){
        AlertDialog.Builder alertdialogBuiler=new
                AlertDialog.Builder(contexto);
        alertdialogBuiler.setTitle(titulo);
        alertdialogBuiler.setMessage(mensagem);
        alertdialogBuiler.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        AlertDialog alertDialog=alertdialogBuiler.create();
        alertDialog.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] VetorEstado =new String[]{"-","RS","SC","PR"};
        btcidade=(Button)findViewById(R.id.btmostracidade);
        combocidade=(Spinner) findViewById(R.id.combocidade);
        comboestado=(Spinner) findViewById(R.id.comboestado);
        adaptador1=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,VetorEstado);
       comboestado.setAdapter(adaptador1);

       comboestado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               adaptador2=new ArrayAdapter<String>(comboestado.getContext(),
                       android.R.layout.simple_spinner_item,
                       retornaCidade(comboestado.getSelectedItem().toString()));
               combocidade.setAdapter(adaptador2);
           }
           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
           }
       });

        btcidade.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Toast.makeText(MainActivity.this,"Cidade :"+
               //        combocidade.getSelectedItem().toString(),
               //        Toast.LENGTH_LONG).show();
               mensagem("Cidade selecionada",
                       "Cidade :"+combocidade.getSelectedItem().toString(),
                       MainActivity.this);
         }
       });
        listagem=(ListView) findViewById(R.id.listagem);
        String [] listagemsetores = new String[]{"Saúde","Educação","Saneamento"};
        ArrayAdapter <String> adapter=
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,listagemsetores);
        listagem.setAdapter(adapter);
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                mensagem("Item da lista",
                        "valor : "+listagem.getItemAtPosition(i),
                        MainActivity.this);
            }
        });
        chave=(Switch) findViewById(R.id.switch1);
        chave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado="";

                if(chave.isChecked()) resultado="ligado";
                else resultado="desligado";

                mensagem("uso do switch",
                        "Chave :"+resultado,MainActivity.this);
            }
        });
        txtvalor=(TextView) findViewById(R.id.txtseek);
        barra=(SeekBar) findViewById(R.id.seekBar2);
        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtvalor.setText("Valor : "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}