package com.wo.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

   private TextInputEditText editValor;
   private TextView txtPorcentagem;
   private TextView txtGorjeta;
   private TextView txtTotal;
   private SeekBar seekBarGorjeta;

   private double porcentagem = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      editValor = findViewById(R.id.edit_campo_valor);
      txtPorcentagem = findViewById(R.id.percent_gorjeta);
      txtGorjeta = findViewById(R.id.txt_result_percent_gorjeta);
      txtTotal = findViewById(R.id.txt_result_total);
      seekBarGorjeta = findViewById(R.id.seekbar_percent_gorjeta);

      seekBarListener();

   }

   private void seekBarListener() {
      seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean seekbar) {
            porcentagem = progress;
            txtPorcentagem.setText(Math.round(porcentagem) + " %");
            calcularGorjeta();
         }



         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {}

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {}
      });
   }

   private void calcularGorjeta() {
      String valorRecuperado = editValor.getText().toString();
      if(valorRecuperado.equals("")) {
         Toast.makeText(
                 getApplicationContext(),
                 "Digite um valor primeiro",
                 Toast.LENGTH_LONG
         ).show();
      } else {
         double valoDigitado = Double.parseDouble(valorRecuperado);
         double gorjeta = valoDigitado * (porcentagem/100);
         double total = gorjeta + valoDigitado;

         DecimalFormat df = new DecimalFormat("0.##");
         txtGorjeta.setText("R$ "+ df.format(gorjeta));
         txtTotal.setText("R$ "+ df.format(total));
      }

   }
}