package com.appsaprendizaje.metododetransporte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.ads.mediationtestsuite.MediationTestSuite;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private AdView adView;
    Button  Comenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //adView.loadAd(adRequest);
        Comenzar = (Button)findViewById(R.id.BtnMain);
        VerifyStartSuccefullApp();
    }



    private void VerifyStartSuccefullApp(){
        SharedPreferences preferences = getSharedPreferences("StartApp", Context.MODE_PRIVATE);
        String VerifyActivity = preferences.getString("StartSave", "Not Result");
        if(VerifyActivity != "Not Result"){
            Intent Start =new Intent(this, CalculadoraTransporte.class);
            startActivity(Start);
            finish();
        }
    }

    public void Comenzar(View view){
        SharedPreferences preferences = getSharedPreferences("StartApp", Context.MODE_PRIVATE);
        String accion = "Start";
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("StartSave", accion);
        editor.commit();
        Intent Iniciado =new Intent(this, CalculadoraTransporte.class);
        startActivity(Iniciado);
        finish();
    }
}