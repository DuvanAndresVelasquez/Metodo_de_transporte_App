package com.appsaprendizaje.metododetransporte;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.ads.mediationtestsuite.MediationTestSuite;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class CalculadoraTransporte extends AppCompatActivity implements RewardedVideoAdListener {


    RequestQueue requestQueue;
    StringRequest stringRequest;
    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;
    private AdView adViewc;
    private int VerifiSolutionInt = 0;
    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int REQUEST_IMAGE_CAMERA = 101;
    GridLayout TablaCalculadora2X2, TablaCalculadoraSL;
    EditText Nproveedores, Ncompradores;
    ScrollView Contenido;
    //Definición de la tabla de ingreso de datos.
    TextView P1, P2, P3, P4, P5,P6, C1, C2,C3, C4, C5, C6, Resultado, ResultadoOptimoOperacion, MensajeResultado;
    EditText Casilla22, Casilla23, Casilla24,Casilla25, Casilla26,Casilla27, Casilla28,
        Casilla32, Casilla33, Casilla34,Casilla35, Casilla36,Casilla37,Casilla38,
        Casilla42, Casilla43, Casilla44,Casilla45, Casilla46,Casilla47,Casilla48,
        Casilla52, Casilla53, Casilla54,Casilla55, Casilla56,Casilla57, Casilla58,
        Casilla62, Casilla63, Casilla64,Casilla65, Casilla66,Casilla67,Casilla68,
        Casilla72, Casilla73, Casilla74,Casilla75, Casilla76,Casilla77, Casilla78,
        Casilla82, Casilla83, Casilla84,Casilla85, Casilla86,Casilla87;

    // Definición de las casillas de la tabla de solución.
    TextView P1SL, P2SL, P3SL, P4SL, P5SL,P6SL, C1SL, C2SL,C3SL, C4SL, C5SL, C6SL, ResultadoSL;

    TextView Casilla22SL, Casilla23SL, Casilla24SL,Casilla25SL, Casilla26SL,Casilla27SL, Casilla28SL,
            Casilla32SL, Casilla33SL, Casilla34SL,Casilla35SL, Casilla36SL,Casilla37SL,Casilla38SL,
            Casilla42SL, Casilla43SL, Casilla44SL,Casilla45SL, Casilla46SL,Casilla47SL,Casilla48SL,
            Casilla52SL, Casilla53SL, Casilla54SL,Casilla55SL, Casilla56SL,Casilla57SL, Casilla58SL,
            Casilla62SL, Casilla63SL, Casilla64SL,Casilla65SL, Casilla66SL,Casilla67SL,Casilla68SL,
            Casilla72SL, Casilla73SL, Casilla74SL,Casilla75SL, Casilla76SL,Casilla77SL, Casilla78SL,
            Casilla82SL, Casilla83SL, Casilla84SL,Casilla85SL, Casilla86SL,Casilla87SL;
    LinearLayout NumeroProveedores, NumeroCompradores;
    Button GenerarTabla, GenerarNuevaTabla, SolucionOptima;
    ImageView foto_sistema;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_transporte);
        getSupportActionBar().hide();


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);



        adViewc = findViewById(R.id.adViewc);
        AdRequest adRequest = new AdRequest.Builder().build();
      //adViewc.loadAd(adRequest);



        mInterstitialAd = new InterstitialAd(CalculadoraTransporte.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1778393545986901/8660655625");
        AdRequest adRequeste = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequeste);
        mInterstitialAd.setAdListener(new AdListener() {


            public void onAdLoaded(){
                if (mInterstitialAd.isLoaded()) {
                  // mInterstitialAd.show();
                }
            }

        });
        foto_sistema = findViewById(R.id.foto_sistema);
        TablaCalculadora2X2 = (GridLayout)findViewById(R.id.TablaCalculadora);
        TablaCalculadoraSL = (GridLayout)findViewById(R.id.TablaCalculadoraSL);
        MensajeResultado = (TextView)findViewById(R.id.MensajeResultado);
        ResultadoOptimoOperacion = (TextView)findViewById(R.id.ResultadoOptimoOperacion);
        Nproveedores = (EditText)findViewById(R.id.Nproveedores);
        Ncompradores = (EditText)findViewById(R.id.Ncompradores);
        Contenido =(ScrollView)findViewById(R.id.ContenidoTablas);
        SolucionOptima = (Button)findViewById(R.id.BtnSolucion);
        Resultado = (TextView)findViewById(R.id.ochoocho);
        P1 = (TextView)findViewById(R.id.unodos);
        P2 = (TextView)findViewById(R.id.unotres);
        P3 = (TextView)findViewById(R.id.unocuatro);
        P4 = (TextView)findViewById(R.id.unocinco);
        P5 = (TextView)findViewById(R.id.unoseis);
        P6 = (TextView)findViewById(R.id.unosiete);
        C1 = (TextView)findViewById(R.id.dosuno);
        C2 = (TextView)findViewById(R.id.tresuno);
        C3 = (TextView)findViewById(R.id.cuatrouno);
        C4 = (TextView)findViewById(R.id.cincouno);
        C5 = (TextView)findViewById(R.id.seisuno);
        C6 = (TextView)findViewById(R.id.sieteuno);
        Casilla22 = (EditText)findViewById(R.id.dosdos);
        Casilla23 = (EditText)findViewById(R.id.dostres);
        Casilla24 = (EditText)findViewById(R.id.doscuatro);
        Casilla25 = (EditText)findViewById(R.id.doscinco);
        Casilla26 = (EditText)findViewById(R.id.dosseis);
        Casilla27 = (EditText)findViewById(R.id.dossiete);
        Casilla28 = (EditText)findViewById(R.id.dosocho);
        Casilla32 = (EditText)findViewById(R.id.tresdos);
        Casilla33 = (EditText)findViewById(R.id.trestres);
        Casilla34 = (EditText)findViewById(R.id.trescuatro);
        Casilla35 = (EditText)findViewById(R.id.trescinco);
        Casilla36 = (EditText)findViewById(R.id.tresseis);
        Casilla37 = (EditText)findViewById(R.id.tressiete);
        Casilla38 = (EditText)findViewById(R.id.tresocho);
        Casilla42 = (EditText)findViewById(R.id.cuatrodos);
        Casilla43 = (EditText)findViewById(R.id.cuatrotres);
        Casilla44 = (EditText)findViewById(R.id.cuatrocuatro);
        Casilla45 = (EditText)findViewById(R.id.cuatrocinco);
        Casilla46 = (EditText)findViewById(R.id.cuatroseis);
        Casilla47 = (EditText)findViewById(R.id.cuatrosiete);
        Casilla48 = (EditText)findViewById(R.id.cuatroocho);
        Casilla52 = (EditText)findViewById(R.id.cincodos);
        Casilla53 = (EditText)findViewById(R.id.cincotres);
        Casilla54 = (EditText)findViewById(R.id.cincocuatro);
        Casilla55 = (EditText)findViewById(R.id.cincocinco);
        Casilla56 = (EditText)findViewById(R.id.cincoseis);
        Casilla57 = (EditText)findViewById(R.id.cincosiete);
        Casilla58 = (EditText)findViewById(R.id.cincoocho);
        Casilla62 = (EditText)findViewById(R.id.seisdos);
        Casilla63 = (EditText)findViewById(R.id.seistres);
        Casilla64 = (EditText)findViewById(R.id.seiscuatro);
        Casilla65 = (EditText)findViewById(R.id.seiscinco);
        Casilla66 = (EditText)findViewById(R.id.seisseis);
        Casilla67 = (EditText)findViewById(R.id.seissiete);
        Casilla68 = (EditText)findViewById(R.id.seisocho);
        Casilla72 = (EditText)findViewById(R.id.sietedos);
        Casilla73 = (EditText)findViewById(R.id.sietetres);
        Casilla74 = (EditText)findViewById(R.id.sietecuatro);
        Casilla75 = (EditText)findViewById(R.id.sietecinco);
        Casilla76 = (EditText)findViewById(R.id.sieteseis);
        Casilla77 = (EditText)findViewById(R.id.sietesiete);
        Casilla78 = (EditText)findViewById(R.id.sieteocho);
        Casilla82 = (EditText)findViewById(R.id.ochodos);
        Casilla83 = (EditText)findViewById(R.id.ochotres);
        Casilla84 = (EditText)findViewById(R.id.ochocuatro);
        Casilla85 = (EditText)findViewById(R.id.ochocinco);
        Casilla86 = (EditText)findViewById(R.id.ochoseis);
        Casilla87 = (EditText)findViewById(R.id.ochosiete);





        ResultadoSL = (TextView)findViewById(R.id.ochoochoSL);
        P1SL = (TextView)findViewById(R.id.unodosSL);
        P2SL = (TextView)findViewById(R.id.unotresSL);
        P3SL = (TextView)findViewById(R.id.unocuatroSL);
        P4SL = (TextView)findViewById(R.id.unocincoSL);
        P5SL = (TextView)findViewById(R.id.unoseisSL);
        P6SL = (TextView)findViewById(R.id.unosieteSL);
        C1SL = (TextView)findViewById(R.id.dosunoSL);
        C2SL = (TextView)findViewById(R.id.tresunoSL);
        C3SL = (TextView)findViewById(R.id.cuatrounoSL);
        C4SL = (TextView)findViewById(R.id.cincounoSL);
        C5SL = (TextView)findViewById(R.id.seisunoSL);
        C6SL = (TextView)findViewById(R.id.sieteunoSL);
        Casilla22SL = (TextView)findViewById(R.id.dosdosSL);

        Casilla23SL = (TextView)findViewById(R.id.dostresSL);
        Casilla24SL = (TextView)findViewById(R.id.doscuatroSL);
        Casilla25SL = (TextView)findViewById(R.id.doscincoSL);
        Casilla26SL = (TextView)findViewById(R.id.dosseisSL);
        Casilla27SL = (TextView)findViewById(R.id.dossieteSL);
        Casilla28SL = (TextView)findViewById(R.id.dosochoSL);
        Casilla32SL = (TextView)findViewById(R.id.tresdosSL);
        Casilla33SL = (TextView)findViewById(R.id.trestresSL);
        Casilla34SL = (TextView)findViewById(R.id.trescuatroSL);
        Casilla35SL = (TextView)findViewById(R.id.trescincoSL);
        Casilla36SL = (TextView)findViewById(R.id.tresseisSL);
        Casilla37SL = (TextView)findViewById(R.id.tressieteSL);
        Casilla38SL = (TextView)findViewById(R.id.tresochoSL);
        Casilla42SL = (TextView)findViewById(R.id.cuatrodosSL);
        Casilla43SL = (TextView)findViewById(R.id.cuatrotresSL);
        Casilla44SL = (TextView)findViewById(R.id.cuatrocuatroSL);
        Casilla45SL = (TextView)findViewById(R.id.cuatrocincoSL);
        Casilla46SL = (TextView)findViewById(R.id.cuatroseisSL);
        Casilla47SL = (TextView)findViewById(R.id.cuatrosieteSL);
        Casilla48SL = (TextView)findViewById(R.id.cuatroochoSL);
        Casilla52SL = (TextView)findViewById(R.id.cincodosSL);
        Casilla53SL = (TextView)findViewById(R.id.cincotresSL);
        Casilla54SL = (TextView)findViewById(R.id.cincocuatroSL);
        Casilla55SL = (TextView)findViewById(R.id.cincocincoSL);
        Casilla56SL = (TextView)findViewById(R.id.cincoseisSL);
        Casilla57SL = (TextView)findViewById(R.id.cincosieteSL);
        Casilla58SL = (TextView)findViewById(R.id.cincoochoSL);
        Casilla62SL = (TextView)findViewById(R.id.seisdosSL);
        Casilla63SL = (TextView)findViewById(R.id.seistresSL);
        Casilla64SL = (TextView)findViewById(R.id.seiscuatroSL);
        Casilla65SL = (TextView)findViewById(R.id.seiscincoSL);
        Casilla66SL = (TextView)findViewById(R.id.seisseisSL);
        Casilla67SL = (TextView)findViewById(R.id.seissieteSL);
        Casilla68SL = (TextView)findViewById(R.id.seisochoSL);
        Casilla72SL = (TextView)findViewById(R.id.sietedosSL);
        Casilla73SL = (TextView)findViewById(R.id.sietetresSL);
        Casilla74SL = (TextView)findViewById(R.id.sietecuatroSL);
        Casilla75SL = (TextView)findViewById(R.id.sietecincoSL);
        Casilla76SL = (TextView)findViewById(R.id.sieteseisSL);
        Casilla77SL = (TextView)findViewById(R.id.sietesieteSL);
        Casilla78SL = (TextView)findViewById(R.id.sieteochoSL);
        Casilla82SL = (TextView)findViewById(R.id.ochodosSL);
        Casilla83SL = (TextView)findViewById(R.id.ochotresSL);
        Casilla84SL = (TextView)findViewById(R.id.ochocuatroSL);
        Casilla85SL = (TextView)findViewById(R.id.ochocincoSL);
        Casilla86SL = (TextView)findViewById(R.id.ochoseisSL);
        Casilla87SL = (TextView)findViewById(R.id.ochosieteSL);
        NumeroProveedores = (LinearLayout)findViewById(R.id.NumeroProveedores);
        NumeroCompradores = (LinearLayout)findViewById(R.id.NumeroCompradores);
        GenerarTabla =(Button)findViewById(R.id.button);
        GenerarNuevaTabla = (Button)findViewById(R.id.buttonnuevo);
        //MediationTestSuite.launch(CalculadoraTransporte.this);
    }


    private void loadRewardedVideoAd(){
        if(!mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.loadAd("ca-app-pub-1778393545986901/4727897432",  new AdRequest.Builder().build());
        }
    }
    public void SolucionOptima(View view){

        SharedPreferences preferences = getSharedPreferences("SolucionOptima", Context.MODE_PRIVATE);
        String VerifiSolution = preferences.getString("nivel", "Not Result");

        if(VerifiSolution.equals("Not Result")){
             VerifiSolutionInt = 0;
        }else{
             VerifiSolutionInt = Integer.parseInt(VerifiSolution);
        }


        if(VerifiSolutionInt >= 5){
            /*AlertDialog.Builder Bien = new AlertDialog.Builder(CalculadoraTransporte.this);
            Bien.setMessage("La soluciones optimas están limitadas, puedes ver máximo 5 al dia, puedes ver un video para desbloquear otras 5 y así continuar con la solución de problemas.")
                    .setCancelable(false)
                    .setNegativeButton("¿Por que está limitada?", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("Ver video", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            loadRewardedVideoAd();
                            if(mRewardedVideoAd.isLoaded()){
                                mRewardedVideoAd.show();
                            }
                        }
                    });
            AlertDialog Titulo = Bien.create();
            Titulo.setTitle("Oh no!");
            Titulo.show();*/
            VerifiSolutionInt = 1;
            Toast.makeText(CalculadoraTransporte.this, "Por favor, confirma la acción de nuevo", Toast.LENGTH_LONG).show();
        }else {
            int VerifiSolutionFinal = VerifiSolutionInt + 1;
            String VSS = String.valueOf(VerifiSolutionFinal);
            SharedPreferences preferencess = getSharedPreferences("SolucionOptima", Context.MODE_PRIVATE);
            String accion = VSS;
            SharedPreferences.Editor editor = preferencess.edit();
            editor.putString("nivel", accion);
            editor.commit();




            mInterstitialAd = new InterstitialAd(CalculadoraTransporte.this);
            mInterstitialAd.setAdUnitId("ca-app-pub-1778393545986901/8932118368");
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener() {


                public void onAdLoaded() {
                    if (mInterstitialAd.isLoaded()) {
                        //mInterstitialAd.show();
                    }
                }

            });
            String vNproveedores = Nproveedores.getText().toString();
            String vNcompradores = Ncompradores.getText().toString();
            String casilla22 = Casilla22.getText().toString();
            String casilla23 = Casilla23.getText().toString();
            String casilla24 = Casilla24.getText().toString();
            String casilla25 = Casilla25.getText().toString();
            String casilla26 = Casilla26.getText().toString();
            String casilla27 = Casilla27.getText().toString();
            String casilla28 = Casilla28.getText().toString();
            String casilla32 = Casilla32.getText().toString();
            String casilla33 = Casilla33.getText().toString();
            String casilla34 = Casilla34.getText().toString();
            String casilla35 = Casilla35.getText().toString();
            String casilla36 = Casilla36.getText().toString();
            String casilla37 = Casilla37.getText().toString();
            String casilla38 = Casilla38.getText().toString();
            String casilla42 = Casilla42.getText().toString();
            String casilla43 = Casilla43.getText().toString();
            String casilla44 = Casilla44.getText().toString();
            String casilla45 = Casilla45.getText().toString();
            String casilla46 = Casilla46.getText().toString();
            String casilla47 = Casilla47.getText().toString();
            String casilla48 = Casilla48.getText().toString();
            String casilla52 = Casilla52.getText().toString();
            String casilla53 = Casilla53.getText().toString();
            String casilla54 = Casilla54.getText().toString();
            String casilla55 = Casilla55.getText().toString();
            String casilla56 = Casilla56.getText().toString();
            String casilla57 = Casilla57.getText().toString();
            String casilla58 = Casilla58.getText().toString();
            String casilla62 = Casilla62.getText().toString();
            String casilla63 = Casilla63.getText().toString();
            String casilla64 = Casilla64.getText().toString();
            String casilla65 = Casilla65.getText().toString();
            String casilla66 = Casilla66.getText().toString();
            String casilla67 = Casilla67.getText().toString();
            String casilla68 = Casilla68.getText().toString();
            String casilla72 = Casilla72.getText().toString();
            String casilla73 = Casilla73.getText().toString();
            String casilla74 = Casilla74.getText().toString();
            String casilla75 = Casilla75.getText().toString();
            String casilla76 = Casilla76.getText().toString();
            String casilla77 = Casilla77.getText().toString();
            String casilla78 = Casilla78.getText().toString();
            String casilla82 = Casilla82.getText().toString();
            String casilla83 = Casilla83.getText().toString();
            String casilla84 = Casilla84.getText().toString();
            String casilla85 = Casilla85.getText().toString();
            String casilla86 = Casilla86.getText().toString();
            String casilla87 = Casilla87.getText().toString();


            if (vNproveedores.equals("2") && vNcompradores.equals("2")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty()) {
                    deshabilitarTabla();
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                    MensajeResultado.setVisibility(View.VISIBLE);
                    TablaCalculadoraSL.setVisibility(View.VISIBLE);
                    C1SL.setVisibility(View.VISIBLE);
                    C2SL.setVisibility(View.VISIBLE);
                    P1SL.setVisibility(View.VISIBLE);
                    P2SL.setVisibility(View.VISIBLE);
                    Casilla22SL.setVisibility(View.VISIBLE);
                    Casilla23SL.setVisibility(View.VISIBLE);
                    Casilla32SL.setVisibility(View.VISIBLE);
                    Casilla33SL.setVisibility(View.VISIBLE);
                    Casilla82SL.setVisibility(View.VISIBLE);
                    Casilla83SL.setVisibility(View.VISIBLE);
                    Casilla28SL.setVisibility(View.VISIBLE);
                    Casilla38SL.setVisibility(View.VISIBLE);
                    int casilla22num = Integer.parseInt(casilla22);
                    int casilla23num = Integer.parseInt(casilla23);
                    int casilla32num = Integer.parseInt(casilla32);
                    int casilla33num = Integer.parseInt(casilla33);
                    int casilla28num = Integer.parseInt(casilla28);
                    int casilla82num = Integer.parseInt(casilla82);
                    int casilla38num = Integer.parseInt(casilla38);
                    int casilla83num = Integer.parseInt(casilla83);
                    int Cres = casilla28num + casilla38num;
                    int Pres = casilla82num + casilla83num;

                    if (casilla28num < casilla82num) {
                        String casilla28numtext = String.valueOf(casilla28num);
                        Casilla22SL.setText(casilla28numtext);
                        Casilla23SL.setText("0");
                        int casilla28res = casilla28num - casilla28num;
                        Casilla28SL.setText(casilla28 + " / " + casilla28res);
                        int casilla82res = casilla82num - casilla28num;
                        if (casilla38num < casilla82res) {
                            Casilla32SL.setText(casilla38);
                            int casilla38res = casilla38num - casilla38num;
                            int casilla82resres = casilla82res - casilla38num;
                            Casilla38SL.setText(casilla38 + " / " + casilla38res);
                            Casilla82SL.setText(casilla82 + " / " + casilla82res + " / " + casilla82resres);
                            Casilla33SL.setText("0");
                            if (casilla82resres > 0) {
                                C3SL.setVisibility(View.VISIBLE);
                                Casilla42SL.setVisibility(View.VISIBLE);
                                Casilla43SL.setVisibility(View.VISIBLE);
                                Casilla48SL.setVisibility(View.VISIBLE);
                                String casilla82resrestext = String.valueOf(casilla82resres);
                                Casilla42SL.setText(casilla82resrestext);
                                Casilla43SL.setText(casilla83);
                                int casilla83res = casilla83num - casilla83num;
                                Casilla83SL.setText(casilla83 + " / " + casilla83res);
                                int Cresres = Cres + (casilla82resres + casilla83num);
                                ResultadoSL.setText(Pres + " / " + Cresres);

                                int casilla48res = casilla83num + casilla82resres;
                                int casilla48resres = casilla48res - casilla82resres;
                                int casilla48resresres = casilla48resres - casilla48resres;
                                Casilla48SL.setText(casilla48res + " / " + casilla48resres + " / " + casilla48resresres);

                                int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * casilla38num) + (casilla33num * 0);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "*" + casilla38num + ") + (" + casilla33num + "* 0)  = " + resultadototal);

                            }
                        } else if (casilla82res < casilla38num) {
                            String casilla82restext = String.valueOf(casilla82res);
                            Casilla32SL.setText(casilla82restext);
                            int casilla38res = casilla38num - casilla82res;
                            int casilla82resres = casilla82res - casilla82res;
                            Casilla82SL.setText(casilla82 + " / " + casilla82res + " / " + casilla82resres);
                            Casilla38SL.setText(casilla38 + " / " + casilla38res);
                            if (casilla38res < casilla83num) {
                                String casilla38restext = String.valueOf(casilla38res);
                                Casilla33SL.setText(casilla38restext);
                                int casilla38resres = casilla38res - casilla38res;
                                Casilla38SL.setText(casilla38 + " / " + casilla38res + " / " + casilla38resres);
                                C3SL.setVisibility(View.VISIBLE);
                                Casilla42SL.setVisibility(View.VISIBLE);
                                Casilla43SL.setVisibility(View.VISIBLE);
                                Casilla48SL.setVisibility(View.VISIBLE);
                                String casilla82resrestext = String.valueOf(casilla82resres);
                                Casilla42SL.setText(casilla82resrestext);
                                int casilla43res = casilla83num - casilla38res;
                                String casilla43restext = String.valueOf(casilla43res);
                                Casilla43SL.setText(casilla43restext);
                                int casilla83res = casilla83num - casilla38res;
                                int casilla48res = casilla82resres + casilla83res;
                                int casilla48resres = casilla48res - casilla82resres;
                                int casilla48resresres = casilla48resres - casilla48resres;
                                Casilla48SL.setText(casilla48res + " / " + casilla48resresres);
                                Casilla83SL.setText(casilla83 + " / " + casilla83res);
                                int Cresres = Cres + (casilla82resres + casilla43res);
                                ResultadoSL.setText(Pres + " / " + Cresres);
                                int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * casilla82res) + (casilla33num * casilla38res);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "*" + casilla82num + ") + (" + casilla33num + "*" + casilla38res + ")  = " + resultadototal);

                            } else if (casilla83num < casilla38res) {
                                Casilla33SL.setText(casilla83);
                                int casilla38resres = casilla38res - casilla83num;
                                Casilla38SL.setText(casilla38 + " / " + casilla38res + " / " + casilla38resres);
                                Casilla83SL.setText(casilla83 + " / 0");
                                P3SL.setVisibility(View.VISIBLE);
                                Casilla24SL.setVisibility(View.VISIBLE);
                                Casilla34SL.setVisibility(View.VISIBLE);
                                Casilla84SL.setVisibility(View.VISIBLE);
                                Casilla24SL.setText(casilla28res + "");
                                Casilla34SL.setText(casilla38resres + "");
                                Casilla84SL.setText(casilla38resres + " / 0");
                                int Presres = Pres + (casilla38resres);
                                ResultadoSL.setText(Presres + " / " + Cres);
                                int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * casilla82res) + (casilla33num * casilla83num);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "*" + casilla82num + ") + (" + casilla33num + "*" + casilla83num + ")  = " + resultadototal);

                            } else if (casilla83num == casilla38res) {

                                Casilla33SL.setText(casilla83num + "");
                                Casilla83SL.setText(casilla83num + " / 0");
                                Casilla38SL.setText(casilla38num + " / " + casilla38res);
                                ResultadoSL.setText(Pres + " / " + Cres);
                                int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * casilla82res) + (casilla33num * casilla83num);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "*" + casilla82num + ") + (" + casilla33num + "*" + casilla83num + ")  = " + resultadototal);

                            }
                        } else if (casilla82res == casilla38num) {
                            Casilla32SL.setText(casilla38num + "");
                            Casilla33SL.setText("0");
                            Casilla82SL.setText(casilla82num + " / " + casilla82res + " / 0");
                            Casilla38SL.setText(casilla38num + " / 0");
                            ResultadoSL.setText(Pres + " / " + Cres);
                            int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * casilla38num) + (casilla33num * 0);
                            ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "*" + casilla38num + ") + (" + casilla33num + "* 0 )  = " + resultadototal);

                        }

                    } else if (casilla82num < casilla28num) {
                        Casilla22SL.setText(casilla82num + "");
                        Casilla32SL.setText("0");
                        Casilla82SL.setText(casilla82 + "/ 0");
                        int casilla28res = casilla28num - casilla82num;
                        Casilla28SL.setText(casilla28 + " / " + casilla28res);
                        if (casilla83num < casilla28res) {
                            Casilla23SL.setText(casilla83num + "");
                            Casilla33SL.setText("0");
                            Casilla83SL.setText(casilla83 + " / 0");
                            int casilla28resres = casilla28res - casilla83num;
                            Casilla28SL.setText(casilla28 + " / " + casilla28res + " / " + casilla28resres);
                            P3SL.setVisibility(View.VISIBLE);
                            Casilla24SL.setVisibility(View.VISIBLE);
                            Casilla34SL.setVisibility(View.VISIBLE);
                            Casilla84SL.setVisibility(View.VISIBLE);
                            Casilla24SL.setText(casilla28resres + "");
                            Casilla34SL.setText(casilla38);
                            Casilla38SL.setText(casilla38 + " / 0");
                            int casilla84res = casilla28resres + casilla38num;
                            int casilla84resres = casilla38num;
                            Casilla84SL.setText(casilla84res + " / " + casilla84resres + " / 0");
                            int Presres = Pres + (casilla28resres + casilla38num);
                            ResultadoSL.setText(Presres + " / " + Cres);
                            int resultadototal = (casilla22num * casilla82num) + (casilla23num * casilla83num) + (casilla32num * 0) + (casilla33num * 0);
                            ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla82num + ") + (" + casilla23num + "*" + casilla83num + ") + (" + casilla32num + "* 0) + (" + casilla33num + "* 0 )  = " + resultadototal);


                        } else if (casilla28res < casilla83num) {
                            Casilla23SL.setText(casilla28res + "");
                            int casilla83res = casilla83num - casilla28res;
                            Casilla83SL.setText(casilla83 + " / " + casilla83res);
                            Casilla28SL.setText(casilla28 + " / " + casilla28res + " / 0");
                            if (casilla38num < casilla83res) {
                                Casilla33SL.setText(casilla38num + "");
                                int casilla83resres = casilla83res - casilla38num;
                                Casilla83SL.setText(casilla83 + " / " + casilla83res + " / " + casilla83resres);
                                Casilla38SL.setText(casilla38 + " / 0");
                                C3SL.setVisibility(View.VISIBLE);
                                Casilla42SL.setVisibility(View.VISIBLE);
                                Casilla43SL.setVisibility(View.VISIBLE);
                                Casilla48SL.setVisibility(View.VISIBLE);
                                Casilla42SL.setText("0");
                                Casilla43SL.setText(casilla83resres + "");
                                int casilla48res = casilla83resres;
                                Casilla48SL.setText(casilla48res + " / 0");
                                int Cresres = Cres + casilla48res;
                                ResultadoSL.setText(Pres + " / " + Cresres);
                                int resultadototal = (casilla22num * casilla82num) + (casilla23num * casilla28res) + (casilla32num * 0) + (casilla33num * casilla38num);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla82num + ") + (" + casilla23num + "*" + casilla28 + ") + (" + casilla32num + "* 0) + (" + casilla33num + "*" + casilla38num + " )  = " + resultadototal);

                            } else if (casilla83res < casilla38num) {
                                Casilla33SL.setText(casilla83res + "");
                                int casilla38res = casilla38num - casilla83res;
                                Casilla38SL.setText(casilla38 + " / " + casilla38res);
                                Casilla83SL.setText(casilla83 + " / 0");
                                P3SL.setVisibility(View.VISIBLE);
                                Casilla24SL.setVisibility(View.VISIBLE);
                                Casilla34SL.setVisibility(View.VISIBLE);
                                Casilla84SL.setVisibility(View.VISIBLE);
                                Casilla24SL.setText("0");
                                Casilla34SL.setText(casilla38res + "");
                                Casilla84SL.setText(casilla38res + "");
                                int Presres = Pres + casilla38res;
                                ResultadoSL.setText(Presres + " / " + Cres);
                                int resultadototal = (casilla22num * casilla82num) + (casilla23num * casilla28res) + (casilla32num * 0) + (casilla33num * casilla83res);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla82num + ") + (" + casilla23num + "*" + casilla28res + ") + (" + casilla32num + "* 0) + (" + casilla33num + "*" + casilla83res + " )  = " + resultadototal);

                            } else if (casilla83res == casilla38num) {
                                Casilla33SL.setText(casilla83res + "");
                                Casilla83SL.setText(casilla83num + " / " + casilla83res + " / 0");
                                Casilla38SL.setText(casilla38num + " / 0");
                                ResultadoSL.setText(Pres + " / " + Cres);
                                int resultadototal = (casilla22num * casilla82num) + (casilla23num * casilla28res) + (casilla32num * 0) + (casilla33num * casilla83res);
                                ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla82num + ") + (" + casilla23num + "*" + casilla28res + ") + (" + casilla32num + "* 0) + (" + casilla33num + "*" + casilla83res + " )  = " + resultadototal);

                            }
                        } else if (casilla28res == casilla83num) {
                            Casilla23SL.setText(casilla28res + "");
                            Casilla83SL.setText(casilla83num + " / 0");
                            Casilla28SL.setText(casilla28num + " / " + casilla28res + " / 0");
                            Casilla33SL.setText("0");
                            ResultadoSL.setText(Pres + " / " + Cres);
                            int resultadototal = (casilla22num * casilla82num) + (casilla23num * casilla28res) + (casilla32num * 0) + (casilla33num * 0);
                            ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla82num + ") + (" + casilla23num + "*" + casilla28res + ") + (" + casilla32num + "* 0) + (" + casilla33num + "* 0)  = " + resultadototal);

                        }
                    } else if (casilla28num == casilla82num) {
                        Casilla22SL.setText(casilla28num + "");
                        Casilla28SL.setText(casilla28 + " / 0");
                        Casilla82SL.setText(casilla82 + " / 0");
                        Casilla23SL.setText("0");
                        Casilla32SL.setText("0");
                        if (casilla38num < casilla83num) {
                            Casilla33SL.setText(casilla38num + "");
                            int casilla83res = casilla83num - casilla38num;
                            Casilla83SL.setText(casilla83num + " / " + casilla83res);
                            Casilla38SL.setText(casilla38num + " / 0");
                            C3SL.setVisibility(View.VISIBLE);
                            Casilla42SL.setVisibility(View.VISIBLE);
                            Casilla43SL.setVisibility(View.VISIBLE);
                            Casilla48SL.setVisibility(View.VISIBLE);
                            Casilla42SL.setText("0");
                            Casilla43SL.setText(casilla83res + "");
                            Casilla83SL.setText(casilla83num + " / " + casilla83res + " / 0");
                            int Presres = Pres + casilla83res;
                            ResultadoSL.setText(Presres + " / " + Cres);
                            int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * 0) + (casilla33num * casilla38num);
                            ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "* 0) + (" + casilla33num + "*" + casilla38num + ") = " + resultadototal);

                        } else if (casilla83num < casilla38num) {
                            Casilla33SL.setText(casilla83num + "");
                            int casilla38res = casilla38num - casilla83num;
                            Casilla38SL.setText(casilla38num + " / " + casilla38res);
                            Casilla83SL.setText(casilla83num + " / 0");
                            P3SL.setVisibility(View.VISIBLE);
                            Casilla24SL.setVisibility(View.VISIBLE);
                            Casilla34SL.setVisibility(View.VISIBLE);
                            Casilla84SL.setVisibility(View.VISIBLE);
                            Casilla24SL.setText("0");
                            Casilla34SL.setText(casilla38res + "");
                            Casilla38SL.setText(casilla38num + " / " + casilla38res + " / 0");
                            int Cresres = Cres + casilla38res;
                            ResultadoSL.setText(Pres + " / " + Cresres);
                            int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * 0) + (casilla33num * casilla83num);
                            ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "* 0) + (" + casilla33num + "*" + casilla83num + ") = " + resultadototal);

                        } else if (casilla83num == casilla38num) {
                            Casilla33SL.setText(casilla83num + "");
                            Casilla83SL.setText(casilla83num + " / 0");
                            Casilla38SL.setText(casilla38num + " / 0");
                            ResultadoSL.setText(Pres + " / " + Cres);
                            int resultadototal = (casilla22num * casilla28num) + (casilla23num * 0) + (casilla32num * 0) + (casilla33num * casilla83num);
                            ResultadoOptimoOperacion.setText("(" + casilla22num + "*" + casilla28num + ") + (" + casilla23num + "* 0) + (" + casilla32num + "* 0) + (" + casilla33num + "*" + casilla83num + ") = " + resultadototal);
                        }
                    }


                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("2") && vNcompradores.equals("3")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla84.isEmpty()) {
                    deshabilitarTabla();
                    MensajeResultado.setVisibility(View.VISIBLE);
                    TablaCalculadoraSL.setVisibility(View.VISIBLE);
                    C1SL.setVisibility(View.VISIBLE);
                    C2SL.setVisibility(View.VISIBLE);
                    P1SL.setVisibility(View.VISIBLE);
                    P2SL.setVisibility(View.VISIBLE);
                    P3SL.setVisibility(View.VISIBLE);
                    Casilla22SL.setVisibility(View.VISIBLE);
                    Casilla23SL.setVisibility(View.VISIBLE);
                    Casilla24SL.setVisibility(View.VISIBLE);
                    Casilla32SL.setVisibility(View.VISIBLE);
                    Casilla33SL.setVisibility(View.VISIBLE);
                    Casilla34SL.setVisibility(View.VISIBLE);
                    Casilla82SL.setVisibility(View.VISIBLE);
                    Casilla83SL.setVisibility(View.VISIBLE);
                    Casilla84SL.setVisibility(View.VISIBLE);
                    Casilla28SL.setVisibility(View.VISIBLE);
                    Casilla38SL.setVisibility(View.VISIBLE);

                    int casilla28num = Integer.parseInt(casilla28);
                    int casilla38num = Integer.parseInt(casilla38);
                    int suma_demanda = casilla28num + casilla38num;

                    int casilla82num = Integer.parseInt(casilla82);
                    int casilla83num = Integer.parseInt(casilla83);
                    int casilla84num = Integer.parseInt(casilla84);
                    int suma_oferta = casilla82num + casilla83num + casilla84num;
                    Casilla28SL.setText(casilla28);
                    Casilla38SL.setText(casilla38);
                    Casilla82SL.setText(casilla82);
                    Casilla83SL.setText(casilla83);
                    Casilla84SL.setText(casilla84);
                    if (suma_demanda == suma_oferta) {
                        ResultadoSL.setText(" " + suma_oferta + " / " + suma_demanda);
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla28num);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    //final de la rama...
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        //final de la rama...
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        //final de la rama...
                                    }
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                //final de la rama...
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        //final de la rama...
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        //final de la rama...
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        //final de la rama...
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        //final de la rama...
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    //final de la rama...
                                }
                            }
                        }
                        MensajeResultado.setText("La tabla dada la asignación está balanceada, así que su número de filas y columnas se mantiene, " +
                                "ahora la asignación en la tabla de solución, es la asignación más optima para un resultado.");
                    } else if (suma_demanda > suma_oferta) {
                        int sobrante_demanda_int = suma_demanda - suma_oferta;
                        int resultado = sobrante_demanda_int + suma_oferta;
                        String sobrante_demanda = String.valueOf(sobrante_demanda_int);
                        P4SL.setVisibility(View.VISIBLE);
                        Casilla25SL.setVisibility(View.VISIBLE);
                        Casilla35SL.setVisibility(View.VISIBLE);
                        Casilla85SL.setVisibility(View.VISIBLE);
                        Casilla85SL.setText(sobrante_demanda);
                        ResultadoSL.setText(" " + resultado + " / " + suma_demanda);

                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    //final de la rama...
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        //final de la rama...
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo3;
                                            //final de la rama...
                                        } else if (casilla38numresiduo3 >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            int casilla38numresiduo4 = casilla38numresiduo3 - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                //final de la rama...
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        //final de la rama...
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo2;
                                            //final de la rama...
                                        } else if (casilla38numresiduo2 >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            int casilla38numresiduo3 = casilla38numresiduo2 - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo;
                                            //final de la rama...
                                        } else if (casilla38numresiduo >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            int casilla38numresiduo2 = casilla38numresiduo - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        //final de la rama...
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= sobrante_demanda_int) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla28numresiduo3;
                                        if (sobrante_demanda_int_residuo <= casilla38num) {
                                            Casilla35SL.setText("" + sobrante_demanda_int_residuo);
                                            int casilla38numresiduo = casilla38num - sobrante_demanda_int_residuo;
                                            //final de la rama
                                        } else if (sobrante_demanda_int_residuo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla38num;
                                            //final de la rama...
                                        }
                                    } else if (casilla28numresiduo3 >= sobrante_demanda_int) {
                                        Casilla25SL.setText("" + sobrante_demanda_int);
                                        Casilla35SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        }
                        MensajeResultado.setText("la suma de las ofertas es menor a la suma de las demandas, en este caso es necesario añadir una columna ficticia a la tabla, que solo se tendrá en cuenta " +
                                " para la asignación más optima.");

                    } else if (suma_oferta > suma_demanda) {
                        int sobrante_oferta_int = suma_oferta - suma_demanda;
                        int resultado = sobrante_oferta_int + suma_demanda;
                        String sobrante_oferta = String.valueOf(sobrante_oferta_int);
                        C3SL.setVisibility(View.VISIBLE);
                        Casilla42SL.setVisibility(View.VISIBLE);
                        Casilla43SL.setVisibility(View.VISIBLE);
                        Casilla44SL.setVisibility(View.VISIBLE);
                        Casilla48SL.setVisibility(View.VISIBLE);
                        Casilla48SL.setText(sobrante_oferta);

                        ResultadoSL.setText(" " + suma_oferta + " / " + resultado);


                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (sobrante_oferta_int <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + sobrante_oferta_int);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - sobrante_oferta_int;
                                    //final de la rama
                                } else if (sobrante_oferta_int >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla82numresiduo2;
                                    if (sobrante_oferta_int_residuo <= casilla83num) {
                                        Casilla43SL.setText("" + sobrante_oferta_int_residuo);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo = casilla83num - sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    } else if (sobrante_oferta_int_residuo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla83num;
                                        if (sobrante_oferta_int_residuo2 <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo2);
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo2;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo2 >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla84num;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo;
                                        if (sobrante_oferta_int_residuo <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo);
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo >= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + sobrante_oferta_int);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        int casilla84numresiduo = casilla38numresiduo2 - casilla84num;
                                        if (casilla84numresiduo <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            //final de la rama...
                                        } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        //final de la rama...
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        //final de la rama...
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        if (casilla84numresiduo2 <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo2);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo2;
                                            //final de la rama...
                                        } else if (casilla84numresiduo2 >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            int casilla84numresiduo3 = casilla84numresiduo2 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        if (casilla84numresiduo <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            //final de la rama...
                                        } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            int casilla84numresiduo2 = casilla84numresiduo - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        //final de la rama...
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo2;
                                        if (sobrante_oferta_int_residuo <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo);
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo2 >= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + sobrante_oferta_int);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - sobrante_oferta_int;
                                        //final de la rama
                                    }
                                }
                            }
                        }
                        MensajeResultado.setText("La suma de las demandas es menor a la suma de las ofertas, en este caso es necesario añadir una fila ficticia que solo se tendrá en cuenta para la asignación más optima.");
                    }
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("2") && vNcompradores.equals("4")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla85.isEmpty()) {
                    deshabilitarTabla();
                    MensajeResultado.setVisibility(View.VISIBLE);
                    TablaCalculadoraSL.setVisibility(View.VISIBLE);
                    C1SL.setVisibility(View.VISIBLE);
                    C2SL.setVisibility(View.VISIBLE);
                    P1SL.setVisibility(View.VISIBLE);
                    P2SL.setVisibility(View.VISIBLE);
                    P3SL.setVisibility(View.VISIBLE);
                    P4SL.setVisibility(View.VISIBLE);
                    Casilla22SL.setVisibility(View.VISIBLE);
                    Casilla23SL.setVisibility(View.VISIBLE);
                    Casilla24SL.setVisibility(View.VISIBLE);
                    Casilla25SL.setVisibility(View.VISIBLE);
                    Casilla32SL.setVisibility(View.VISIBLE);
                    Casilla33SL.setVisibility(View.VISIBLE);
                    Casilla34SL.setVisibility(View.VISIBLE);
                    Casilla35SL.setVisibility(View.VISIBLE);
                    Casilla82SL.setVisibility(View.VISIBLE);
                    Casilla83SL.setVisibility(View.VISIBLE);
                    Casilla84SL.setVisibility(View.VISIBLE);
                    Casilla85SL.setVisibility(View.VISIBLE);
                    Casilla28SL.setVisibility(View.VISIBLE);
                    Casilla38SL.setVisibility(View.VISIBLE);
                    int casilla28num = Integer.parseInt(casilla28);
                    int casilla38num = Integer.parseInt(casilla38);
                    int suma_demanda = casilla28num + casilla38num;
                    int casilla82num = Integer.parseInt(casilla82);
                    int casilla83num = Integer.parseInt(casilla83);
                    int casilla84num = Integer.parseInt(casilla84);
                    int casilla85num = Integer.parseInt(casilla85);
                    int suma_oferta = casilla82num + casilla83num + casilla84num + casilla85num;
                    Casilla28SL.setText(casilla28);
                    Casilla38SL.setText(casilla38);
                    Casilla82SL.setText(casilla82);
                    Casilla83SL.setText(casilla83);
                    Casilla84SL.setText(casilla84);
                    Casilla85SL.setText(casilla85);
                    if (suma_demanda == suma_oferta) {
                        if (casilla28num <= casilla82num) {
                            MensajeResultado.setText("La tabla dada la asignación está balanceada, así que su número de filas y columnas se mantiene, " +
                                    "ahora la asignación en la tabla de solución, es la asignación más optima para un resultado.");
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    //final de la rama...
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        //final de la rama...
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            int sobrante_demanda_int_residuo = casilla85num - casilla38numresiduo3;
                                            //final de la rama...
                                        } else if (casilla38numresiduo3 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo4 = casilla38numresiduo3 - casilla85num;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                //final de la rama...
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        //final de la rama...
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            int sobrante_demanda_int_residuo = casilla85num - casilla38numresiduo2;
                                            //final de la rama...
                                        } else if (casilla38numresiduo2 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo3 = casilla38numresiduo2 - casilla85num;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            int sobrante_demanda_int_residuo = casilla85num - casilla38numresiduo;
                                            //final de la rama...
                                        } else if (casilla38numresiduo >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo2 = casilla38numresiduo - casilla85num;
                                            //final de la rama...
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        //final de la rama...
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= casilla85num) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        int sobrante_demanda_int_residuo = casilla85num - casilla28numresiduo3;
                                        if (sobrante_demanda_int_residuo <= casilla38num) {
                                            Casilla35SL.setText("" + sobrante_demanda_int_residuo);
                                            int casilla38numresiduo = casilla38num - sobrante_demanda_int_residuo;
                                            //final de la rama
                                        } else if (sobrante_demanda_int_residuo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla38num;
                                            //final de la rama...
                                        }
                                    } else if (casilla28numresiduo3 >= casilla85num) {
                                        Casilla25SL.setText("" + casilla85num);
                                        Casilla35SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - casilla85num;
                                        //final de la rama...
                                    }
                                }
                            }
                        }
                        ResultadoSL.setText(" " + suma_oferta + " / " + suma_demanda);
                    } else if (suma_demanda < suma_oferta) {
                        int sobrante_oferta_int = suma_oferta - suma_demanda;
                        int resultado = sobrante_oferta_int + suma_demanda;
                        String sobrante_oferta = String.valueOf(sobrante_oferta_int);
                        C3SL.setVisibility(View.VISIBLE);
                        Casilla42SL.setVisibility(View.VISIBLE);
                        Casilla43SL.setVisibility(View.VISIBLE);
                        Casilla44SL.setVisibility(View.VISIBLE);
                        Casilla45SL.setVisibility(View.VISIBLE);
                        Casilla48SL.setVisibility(View.VISIBLE);
                        Casilla48SL.setText(sobrante_oferta);
                        ResultadoSL.setText(" " + suma_oferta + " / " + resultado);
                        MensajeResultado.setText("La suma de las demandas es menor a la suma de las ofertas, en este caso es necesario añadir" +
                                " una fila ficticia que solo se tendrá en cuenta para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (sobrante_oferta_int <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + sobrante_oferta_int);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    Casilla45SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - sobrante_oferta_int;
                                    //final de la rama
                                } else if (sobrante_oferta_int >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla82numresiduo2;
                                    if (sobrante_oferta_int_residuo <= casilla83num) {
                                        Casilla43SL.setText("" + sobrante_oferta_int_residuo);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla83numresiduo = casilla83num - sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    } else if (sobrante_oferta_int_residuo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla83num;
                                        if (sobrante_oferta_int_residuo2 <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo2);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo2;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo2 >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla84num;
                                            if (sobrante_oferta_int_residuo3 <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo3);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo3;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo3 >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo4 = sobrante_oferta_int_residuo3 - casilla85num;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo;
                                        if (sobrante_oferta_int_residuo <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                            if (sobrante_oferta_int_residuo2 <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo2);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo2;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo2 >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla85num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla83numresiduo >= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + sobrante_oferta_int);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla38numresiduo2 - casilla84num;
                                        if (casilla84numresiduo <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                //final de la rama...
                                            }
                                        } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            Casilla45SL.setText("0");
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo3;
                                            if (casilla85numresiduo <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo;
                                                //final de la rama...
                                            } else if (casilla85numresiduo >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo2 = casilla85numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo3 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo4 = casilla38numresiduo3 - casilla85num;
                                            //final de la rama...
                                        }
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo;
                                            if (casilla85numresiduo <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla38numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo;
                                                //final de la rama...
                                            } else if (casilla85numresiduo >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo2 = casilla85numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo2 = casilla38numresiduo - casilla85num;
                                            //final de la rama...
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        if (casilla84numresiduo2 <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo2);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo2;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                //final de la rama...
                                            }
                                        } else if (casilla84numresiduo2 >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo3 = casilla84numresiduo2 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= casilla85num) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        int casilla85numresiduo = casilla85num - casilla28numresiduo3;
                                        if (casilla85numresiduo <= casilla38num) {
                                            Casilla35SL.setText("" + casilla85numresiduo);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo = casilla38num - casilla85numresiduo;
                                            //final de la rama...
                                        } else if (casilla85numresiduo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            int casilla85numresiduo2 = casilla85numresiduo - casilla38num;
                                            if (casilla85numresiduo2 <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo2);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo2;
                                                //final de la rama...
                                            } else if (casilla85numresiduo2 >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo3 = casilla85numresiduo2 - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla28numresiduo3 >= casilla85num) {
                                        Casilla25SL.setText("" + casilla85num);
                                        Casilla35SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - casilla85num;
                                        //final de la rama...
                                    }
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        if (casilla84numresiduo <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                //final de la rama...
                                            }
                                        } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo2 = casilla84numresiduo - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo2;
                                            if (casilla85numresiduo <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo;
                                                //final de la rama...
                                            } else if (casilla85numresiduo >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo2 = casilla85numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo2 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo3 = casilla38numresiduo2 - casilla85num;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo2;
                                        if (sobrante_oferta_int_residuo <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                            if (sobrante_oferta_int_residuo2 <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo2);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo2;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo2 >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla85num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla83numresiduo2 >= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + sobrante_oferta_int);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - sobrante_oferta_int;
                                        //final de la rama
                                    }
                                }
                            }
                        }
                    } else if (suma_demanda > suma_oferta) {
                        int sobrante_demanda_int = suma_demanda - suma_oferta;
                        int resultado = sobrante_demanda_int + suma_oferta;
                        String sobrante_demanda = String.valueOf(sobrante_demanda_int);
                        P5SL.setVisibility(View.VISIBLE);
                        Casilla26SL.setVisibility(View.VISIBLE);
                        Casilla36SL.setVisibility(View.VISIBLE);
                        Casilla86SL.setVisibility(View.VISIBLE);
                        Casilla86SL.setText(sobrante_demanda);
                        ResultadoSL.setText(" " + resultado + " / " + suma_demanda);
                        MensajeResultado.setText("la suma de las ofertas es menor a la suma de las demandas, en este caso es necesario añadir una columna ficticia a la tabla, que solo se tendrá en cuenta " +
                                " para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            Casilla26SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    //final de la rama...
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        //final de la rama...
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo3;
                                            //final de la rama...
                                        } else if (casilla38numresiduo3 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo4 = casilla38numresiduo3 - casilla85num;
                                            if (casilla38numresiduo4 <= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + casilla38numresiduo4);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo4;
                                                //final de la rama...
                                            } else if (casilla38numresiduo4 >= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + sobrante_demanda_int);
                                                int casilla38numresiduo5 = casilla38numresiduo4 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                Casilla36SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                //final de la rama...
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                Casilla26SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        //final de la rama...
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo2;
                                            //final de la rama...
                                        } else if (casilla38numresiduo2 >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            int casilla38numresiduo3 = casilla38numresiduo2 - casilla85num;
                                            if (casilla38numresiduo3 <= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + casilla38numresiduo3);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo3;
                                                //final de la rama...
                                            } else if (casilla38numresiduo3 >= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + sobrante_demanda_int);
                                                int casilla38numresiduo4 = casilla38numresiduo3 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    Casilla26SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo;
                                            //final de la rama...
                                        } else if (casilla38numresiduo >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo2 = casilla38numresiduo - casilla85num;
                                            if (casilla38numresiduo2 <= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + casilla38numresiduo2);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo2;
                                                //final de la rama...
                                            } else if (casilla38numresiduo2 >= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + sobrante_demanda_int);
                                                int casilla38numresiduo3 = casilla38numresiduo2 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        //final de la rama...
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= casilla85num) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        Casilla26SL.setText("0");
                                        int casilla85numresiduo = casilla85num - casilla28numresiduo3;
                                        if (casilla85numresiduo <= casilla38num) {
                                            Casilla35SL.setText("" + casilla85numresiduo);
                                            int casilla38numresiduo = casilla38num - casilla85numresiduo;
                                            if (casilla38numresiduo <= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + casilla38numresiduo);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo;
                                                //final de la rama...
                                            } else if (casilla38numresiduo >= sobrante_demanda_int) {
                                                Casilla36SL.setText("" + sobrante_demanda_int);
                                                int casilla38numresiduo2 = casilla38numresiduo - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla85numresiduo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo2 = casilla85numresiduo - casilla38num;
                                            //final de la rama...
                                        }
                                    } else if (casilla28numresiduo3 >= casilla85num) {
                                        Casilla25SL.setText("" + casilla85num);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - casilla85num;
                                        //final de la rama...
                                    }
                                }
                            }
                        }

                    }

                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("2") && vNcompradores.equals("5")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla86.isEmpty()) {
                    deshabilitarTabla();
                    MensajeResultado.setVisibility(View.VISIBLE);
                    TablaCalculadoraSL.setVisibility(View.VISIBLE);
                    C1SL.setVisibility(View.VISIBLE);
                    C2SL.setVisibility(View.VISIBLE);
                    P1SL.setVisibility(View.VISIBLE);
                    P2SL.setVisibility(View.VISIBLE);
                    P3SL.setVisibility(View.VISIBLE);
                    P4SL.setVisibility(View.VISIBLE);
                    P5SL.setVisibility(View.VISIBLE);
                    Casilla22SL.setVisibility(View.VISIBLE);
                    Casilla23SL.setVisibility(View.VISIBLE);
                    Casilla24SL.setVisibility(View.VISIBLE);
                    Casilla25SL.setVisibility(View.VISIBLE);
                    Casilla26SL.setVisibility(View.VISIBLE);
                    Casilla32SL.setVisibility(View.VISIBLE);
                    Casilla33SL.setVisibility(View.VISIBLE);
                    Casilla34SL.setVisibility(View.VISIBLE);
                    Casilla35SL.setVisibility(View.VISIBLE);
                    Casilla36SL.setVisibility(View.VISIBLE);
                    Casilla82SL.setVisibility(View.VISIBLE);
                    Casilla83SL.setVisibility(View.VISIBLE);
                    Casilla84SL.setVisibility(View.VISIBLE);
                    Casilla85SL.setVisibility(View.VISIBLE);
                    Casilla86SL.setVisibility(View.VISIBLE);
                    Casilla28SL.setVisibility(View.VISIBLE);
                    Casilla38SL.setVisibility(View.VISIBLE);
                    int casilla28num = Integer.parseInt(casilla28);
                    int casilla38num = Integer.parseInt(casilla38);
                    int suma_demanda = casilla28num + casilla38num;
                    int casilla82num = Integer.parseInt(casilla82);
                    int casilla83num = Integer.parseInt(casilla83);
                    int casilla84num = Integer.parseInt(casilla84);
                    int casilla85num = Integer.parseInt(casilla85);
                    int casilla86num = Integer.parseInt(casilla86);
                    int suma_oferta = casilla82num + casilla83num + casilla84num + casilla85num + casilla86num;
                    Casilla28SL.setText(casilla28);
                    Casilla38SL.setText(casilla38);
                    Casilla82SL.setText(casilla82);
                    Casilla83SL.setText(casilla83);
                    Casilla84SL.setText(casilla84);
                    Casilla85SL.setText(casilla85);
                    Casilla86SL.setText(casilla86);
                    if (suma_oferta == suma_demanda) {
                        MensajeResultado.setText("La tabla dada la asignación está balanceada, así que su número de filas y columnas se mantiene, " +
                                "ahora la asignación en la tabla de solución, es la asignación más optima para un resultado.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            Casilla26SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    //final de la rama...
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        //final de la rama...
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo3;
                                            //final de la rama...
                                        } else if (casilla38numresiduo3 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo4 = casilla38numresiduo3 - casilla85num;
                                            if (casilla38numresiduo4 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo4);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo4;
                                                //final de la rama...
                                            } else if (casilla38numresiduo4 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo5 = casilla38numresiduo4 - casilla86num;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                Casilla36SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                //final de la rama...
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                Casilla26SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        //final de la rama...
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo2;
                                            //final de la rama...
                                        } else if (casilla38numresiduo2 >= casilla86num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo3 = casilla38numresiduo2 - casilla85num;
                                            if (casilla38numresiduo3 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo3);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo3;
                                                //final de la rama...
                                            } else if (casilla38numresiduo3 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo4 = casilla38numresiduo3 - casilla86num;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    Casilla26SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo;
                                            //final de la rama...
                                        } else if (casilla38numresiduo >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo2 = casilla38numresiduo - casilla85num;
                                            if (casilla38numresiduo2 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo2);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo2;
                                                //final de la rama...
                                            } else if (casilla38numresiduo2 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo3 = casilla38numresiduo2 - casilla86num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        //final de la rama...
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= casilla85num) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        Casilla26SL.setText("0");
                                        int casilla85numresiduo = casilla85num - casilla28numresiduo3;
                                        if (casilla85numresiduo <= casilla38num) {
                                            Casilla35SL.setText("" + casilla85numresiduo);
                                            int casilla38numresiduo = casilla38num - casilla85numresiduo;
                                            if (casilla38numresiduo <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo;
                                                //final de la rama...
                                            } else if (casilla38numresiduo >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo2 = casilla38numresiduo - casilla86num;
                                                //final de la rama...
                                            }
                                        } else if (casilla85numresiduo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo2 = casilla85numresiduo - casilla38num;
                                            //final de la rama...
                                        }
                                    } else if (casilla28numresiduo3 >= casilla85num) {
                                        Casilla25SL.setText("" + casilla85num);
                                        Casilla35SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - casilla85num;
                                        if (casilla28numresiduo4 <= casilla86num) {
                                            Casilla26SL.setText("" + casilla85num);
                                            int casilla86numresiduo = casilla86num - casilla28numresiduo4;
                                            if (casilla86numresiduo <= casilla38num) {
                                                Casilla36SL.setText("" + casilla86numresiduo);
                                                int casilla38numresiduo = casilla38num - casilla86numresiduo;
                                                //final de la rama...
                                            } else if (casilla86numresiduo >= casilla38num) {
                                                Casilla36SL.setText("" + casilla38num);
                                                int casilla86numresiduo2 = casilla86numresiduo - casilla38num;
                                                //final de la rama...
                                            }
                                        } else if (casilla28numresiduo4 >= casilla86num) {
                                            Casilla26SL.setText("" + casilla85num);
                                            Casilla36SL.setText("0");
                                            int casilla28numresiduo5 = casilla28numresiduo4 - casilla86num;
                                            //final de la rama...
                                        }
                                    }
                                }
                            }
                        }
                        ResultadoSL.setText(" " + suma_oferta + " / " + suma_demanda);

                    } else if (suma_oferta < suma_demanda) {
                        int sobrante_demanda_int = suma_demanda - suma_oferta;
                        int resultado = sobrante_demanda_int + suma_oferta;
                        String sobrante_demanda = String.valueOf(sobrante_demanda_int);
                        P6SL.setVisibility(View.VISIBLE);
                        Casilla27SL.setVisibility(View.VISIBLE);
                        Casilla37SL.setVisibility(View.VISIBLE);
                        Casilla87SL.setVisibility(View.VISIBLE);
                        Casilla87SL.setText(sobrante_demanda);
                        ResultadoSL.setText(" " + resultado + " / " + suma_demanda);
                        MensajeResultado.setText("la suma de las ofertas es menor a la suma de las demandas, en este caso es necesario añadir una columna ficticia a la tabla, que solo se tendrá en cuenta " +
                                " para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            Casilla26SL.setText("0");
                            Casilla27SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    Casilla37SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    //final de la rama...
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        Casilla37SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        //final de la rama...
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            Casilla36SL.setText("0");
                                            Casilla37SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo3;
                                            //final de la rama...
                                        } else if (casilla38numresiduo3 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo4 = casilla38numresiduo3 - casilla85num;
                                            if (casilla38numresiduo4 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo4);
                                                Casilla37SL.setText("0");
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo4;
                                                //final de la rama...
                                            } else if (casilla38numresiduo4 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo5 = casilla38numresiduo4 - casilla86num;
                                                if (casilla38numresiduo5 <= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + casilla38numresiduo5);
                                                    int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo5;
                                                    //final de la rama...
                                                } else if (casilla38numresiduo5 >= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + sobrante_demanda_int);
                                                    int casilla38numresiduo6 = casilla38numresiduo5 - sobrante_demanda_int;
                                                    //final de la rama...
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                Casilla36SL.setText("0");
                                Casilla37SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                //final de la rama...
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                Casilla26SL.setText("0");
                                Casilla27SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        Casilla37SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        //final de la rama...
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            Casilla36SL.setText("0");
                                            Casilla37SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo2;
                                            //final de la rama...
                                        } else if (casilla38numresiduo2 >= casilla86num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo3 = casilla38numresiduo2 - casilla85num;
                                            if (casilla38numresiduo3 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo3);
                                                Casilla37SL.setText("0");
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo3;
                                                //final de la rama...
                                            } else if (casilla38numresiduo3 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo4 = casilla38numresiduo3 - casilla86num;
                                                if (casilla38numresiduo4 <= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + casilla38numresiduo4);
                                                    int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo4;
                                                    //final de la rama...
                                                } else if (casilla38numresiduo4 >= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + sobrante_demanda_int);
                                                    int casilla38numresiduo5 = casilla38numresiduo4 - sobrante_demanda_int;
                                                    //final de la rama...
                                                }
                                            }
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    Casilla37SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    Casilla26SL.setText("0");
                                    Casilla27SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            Casilla36SL.setText("0");
                                            Casilla37SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo;
                                            //final de la rama...
                                        } else if (casilla38numresiduo >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            int casilla38numresiduo2 = casilla38numresiduo - casilla85num;
                                            if (casilla38numresiduo2 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo2);
                                                Casilla37SL.setText("0");
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo2;
                                                //final de la rama...
                                            } else if (casilla38numresiduo2 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo3 = casilla38numresiduo2 - casilla86num;
                                                if (casilla38numresiduo3 <= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + casilla38numresiduo3);
                                                    int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo3;
                                                    //final de la rama...
                                                } else if (casilla38numresiduo3 >= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + sobrante_demanda_int);
                                                    int casilla38numresiduo4 = casilla38numresiduo3 - sobrante_demanda_int;
                                                    //final de la rama...
                                                }
                                            }
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        Casilla37SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        //final de la rama...
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= casilla85num) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        Casilla26SL.setText("0");
                                        Casilla27SL.setText("0");
                                        int casilla85numresiduo = casilla85num - casilla28numresiduo3;
                                        if (casilla85numresiduo <= casilla38num) {
                                            Casilla35SL.setText("" + casilla85numresiduo);
                                            int casilla38numresiduo = casilla38num - casilla85numresiduo;
                                            if (casilla38numresiduo <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo);
                                                Casilla37SL.setText("0");
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo;
                                                //final de la rama...
                                            } else if (casilla38numresiduo >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                int casilla38numresiduo2 = casilla38numresiduo - casilla86num;
                                                if (casilla38numresiduo2 <= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + casilla38numresiduo2);
                                                    int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo2;
                                                    //final de la rama...
                                                } else if (casilla38numresiduo2 >= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + sobrante_demanda_int);
                                                    int casilla38numresiduo3 = casilla38numresiduo2 - sobrante_demanda_int;
                                                    //final de la rama...
                                                }
                                            }
                                        } else if (casilla85numresiduo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            Casilla36SL.setText("0");
                                            Casilla37SL.setText("0");
                                            int casilla85numresiduo2 = casilla85numresiduo - casilla38num;
                                            //final de la rama...
                                        }
                                    } else if (casilla28numresiduo3 >= casilla85num) {
                                        Casilla25SL.setText("" + casilla85num);
                                        Casilla35SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - casilla85num;
                                        if (casilla28numresiduo4 <= casilla86num) {
                                            Casilla26SL.setText("" + casilla85num);
                                            Casilla27SL.setText("0");
                                            int casilla86numresiduo = casilla86num - casilla28numresiduo4;
                                            if (casilla86numresiduo <= casilla38num) {
                                                Casilla36SL.setText("" + casilla86numresiduo);
                                                int casilla38numresiduo = casilla38num - casilla86numresiduo;
                                                if (casilla38numresiduo <= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + casilla38numresiduo);
                                                    int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo;
                                                    //final de la rama...
                                                } else if (casilla38numresiduo >= sobrante_demanda_int) {
                                                    Casilla37SL.setText("" + casilla38numresiduo);
                                                    int casilla38numresiduo2 = casilla38numresiduo - sobrante_demanda_int;
                                                    //final de la rama...
                                                }
                                            } else if (casilla86numresiduo >= casilla38num) {
                                                Casilla36SL.setText("" + casilla38num);
                                                Casilla37SL.setText("0");
                                                int casilla86numresiduo2 = casilla86numresiduo - casilla38num;
                                                //final de la rama...
                                            }
                                        } else if (casilla28numresiduo4 >= casilla86num) {
                                            Casilla26SL.setText("" + casilla85num);
                                            Casilla36SL.setText("0");
                                            int casilla28numresiduo5 = casilla28numresiduo4 - casilla86num;
                                            if (casilla28numresiduo5 <= sobrante_demanda_int) {
                                                Casilla27SL.setText("" + casilla28numresiduo5);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla28numresiduo5;
                                                if (sobrante_demanda_int_residuo <= casilla38num) {
                                                    Casilla37SL.setText("" + sobrante_demanda_int_residuo);
                                                    int casilla38numresiduo = casilla38num - sobrante_demanda_int_residuo;
                                                    //final de la rama...
                                                } else if (sobrante_demanda_int_residuo >= casilla38num) {
                                                    Casilla37SL.setText("" + casilla38num);
                                                    int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla38num;
                                                    //final de la rama...
                                                }
                                            } else if (casilla28numresiduo5 >= sobrante_demanda_int) {
                                                Casilla27SL.setText("" + sobrante_demanda_int);
                                                Casilla37SL.setText("0");
                                                int casilla28numresiduo6 = casilla28numresiduo5 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    } else if (suma_oferta > suma_demanda) {
                        int sobrante_oferta_int = suma_oferta - suma_demanda;
                        int resultado = sobrante_oferta_int + suma_demanda;
                        String sobrante_oferta = String.valueOf(sobrante_oferta_int);
                        C3SL.setVisibility(View.VISIBLE);
                        Casilla42SL.setVisibility(View.VISIBLE);
                        Casilla43SL.setVisibility(View.VISIBLE);
                        Casilla44SL.setVisibility(View.VISIBLE);
                        Casilla45SL.setVisibility(View.VISIBLE);
                        Casilla46SL.setVisibility(View.VISIBLE);
                        Casilla48SL.setVisibility(View.VISIBLE);
                        Casilla48SL.setText(sobrante_oferta);
                        ResultadoSL.setText(" " + suma_oferta + " / " + resultado);
                        MensajeResultado.setText("La suma de las demandas es menor a la suma de las ofertas, en este caso es necesario" +
                                " añadir una fila ficticia que solo se tendrá en cuenta para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            Casilla26SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                Casilla36SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (sobrante_oferta_int <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + sobrante_oferta_int);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    Casilla45SL.setText("0");
                                    Casilla46SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - sobrante_oferta_int;
                                    //final de la rama
                                } else if (sobrante_oferta_int >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla82numresiduo2;
                                    if (sobrante_oferta_int_residuo <= casilla83num) {
                                        Casilla43SL.setText("" + sobrante_oferta_int_residuo);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        Casilla46SL.setText("0");
                                        int casilla83numresiduo = casilla83num - sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    } else if (sobrante_oferta_int_residuo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla83num;
                                        if (sobrante_oferta_int_residuo2 <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo2);
                                            Casilla45SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo2;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo2 >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla84num;
                                            if (sobrante_oferta_int_residuo3 <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo3);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo3;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo3 >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo4 = sobrante_oferta_int_residuo3 - casilla85num;
                                                if (sobrante_oferta_int_residuo4 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo4);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo4;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo4 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo5 = sobrante_oferta_int_residuo4 - casilla86num;
                                                    //final de la rama...
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo;
                                        if (sobrante_oferta_int_residuo <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo);
                                            Casilla45SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                            if (sobrante_oferta_int_residuo2 <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo2);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo2;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo2 >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla85num;
                                                if (sobrante_oferta_int_residuo3 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo3);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo3;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo3 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo4 = sobrante_oferta_int_residuo3 - casilla86num;
                                                    //final de la rama...
                                                }
                                            }
                                        }
                                    } else if (casilla83numresiduo >= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + sobrante_oferta_int);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        Casilla46SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo = casilla38numresiduo2 - casilla84num;
                                        if (casilla84numresiduo <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                if (sobrante_oferta_int_residuo2 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo2);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo2;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo2 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla86num;
                                                    //final de la rama...
                                                }
                                            }
                                        } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            Casilla45SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                if (sobrante_oferta_int_residuo2 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo2);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo2;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo2 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla86num;
                                                    //final de la rama...
                                                }
                                            }
                                        }
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo3;
                                            if (casilla85numresiduo <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo;
                                                if (sobrante_oferta_int_residuo <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla86num;
                                                    //final de la rama...
                                                }
                                            } else if (casilla85numresiduo >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo2 = casilla85numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo3 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo4 = casilla38numresiduo3 - casilla85num;
                                            if (casilla38numresiduo4 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo4);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo4;
                                                if (casilla86numresiduo <= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + casilla86numresiduo);
                                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla86numresiduo;
                                                    //final de la rama...
                                                } else if (casilla86numresiduo >= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int);
                                                    int casilla86numresiduo2 = casilla86numresiduo - sobrante_oferta_int;
                                                    //final de la rama...
                                                }
                                            } else if (casilla38numresiduo4 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                Casilla46SL.setText("0");
                                                int casilla38numresiduo5 = casilla38numresiduo4 - casilla86num;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    Casilla26SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            Casilla36SL.setText("0");
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo;
                                            if (casilla85numresiduo <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo;
                                                if (sobrante_oferta_int_residuo <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla86num;
                                                    //final de la rama...
                                                }
                                            } else if (casilla85numresiduo >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo2 = casilla85numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo2 = casilla38numresiduo - casilla85num;
                                            if (casilla38numresiduo2 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo2);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo2;
                                                if (casilla86numresiduo <= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + casilla86numresiduo);
                                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla86numresiduo;
                                                    //final de la rama...
                                                } else if (casilla86numresiduo >= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int);
                                                    int casilla86numresiduo2 = casilla86numresiduo - sobrante_oferta_int;
                                                    //final de la rama...
                                                }
                                            } else if (casilla38numresiduo2 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                Casilla46SL.setText("0");
                                                int casilla38numresiduo3 = casilla38numresiduo2 - casilla86num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        if (casilla84numresiduo2 <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo2);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo2;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                if (sobrante_oferta_int_residuo2 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo2);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo2;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo2 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla86num;
                                                    //final de la rama...
                                                }
                                            }
                                        } else if (casilla84numresiduo2 >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            Casilla45SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int casilla84numresiduo3 = casilla84numresiduo2 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= casilla85num) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        Casilla26SL.setText("0");
                                        int casilla85numresiduo = casilla85num - casilla28numresiduo3;
                                        if (casilla85numresiduo <= casilla38num) {
                                            Casilla35SL.setText("" + casilla85numresiduo);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo = casilla38num - casilla85numresiduo;
                                            if (casilla38numresiduo <= casilla86num) {
                                                Casilla45SL.setText("" + casilla38numresiduo);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo;
                                                if (casilla86numresiduo <= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + casilla86numresiduo);
                                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla86numresiduo;
                                                    //final de la rama...
                                                } else if (casilla86numresiduo >= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int);
                                                    int casilla86numresiduo2 = casilla86numresiduo - sobrante_oferta_int;
                                                    //final de la rama...
                                                }
                                            } else if (casilla38numresiduo >= casilla86num) {
                                                Casilla45SL.setText("" + casilla86num);
                                                Casilla46SL.setText("0");
                                                int casilla38numresiduo2 = casilla38numresiduo - casilla86num;
                                                //final de la rama...
                                            }
                                        } else if (casilla85numresiduo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            int casilla85numresiduo2 = casilla85numresiduo - casilla38num;
                                            if (casilla85numresiduo2 <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo2);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo2;
                                                if (sobrante_oferta_int_residuo <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla86num;
                                                    //final de la rama...
                                                }
                                            } else if (casilla85numresiduo2 >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                int casilla85numresiduo3 = casilla85numresiduo2 - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla28numresiduo3 >= casilla85num) {
                                        Casilla25SL.setText("" + casilla85num);
                                        Casilla35SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - casilla85num;
                                        if (casilla28numresiduo4 <= casilla86num) {
                                            Casilla26SL.setText("" + casilla28numresiduo4);
                                            int casilla86numresiduo = casilla86num - casilla28numresiduo4;
                                            if (casilla86numresiduo <= casilla38num) {
                                                Casilla36SL.setText("" + casilla86numresiduo);
                                                Casilla46SL.setText("0");
                                                int casilla38numresiduo = casilla38num - casilla86numresiduo;
                                                //final de la rama...
                                            } else if (casilla86numresiduo >= casilla38num) {
                                                Casilla36SL.setText("" + casilla38num);
                                                int casilla86numresiduo2 = casilla86numresiduo - casilla38num;
                                                if (casilla86numresiduo2 <= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + casilla86numresiduo2);
                                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla86numresiduo2;
                                                    //final de la rama...
                                                } else if (casilla86numresiduo2 >= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int);
                                                    int casilla86numresiduo3 = casilla86numresiduo2 - sobrante_oferta_int;
                                                    //final de la rama...
                                                }
                                            }
                                        } else if (casilla28numresiduo4 >= casilla86num) {
                                            Casilla26SL.setText("" + casilla86num);
                                            Casilla36SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int casilla28numresiduo5 = casilla28numresiduo4 - casilla86num;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                Casilla25SL.setText("0");
                                Casilla26SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        Casilla35SL.setText("0");
                                        Casilla36SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        if (casilla84numresiduo <= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla85num;
                                                if (sobrante_oferta_int_residuo2 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo2);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo2;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo2 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 = casilla86num;
                                                    //final de la rama...
                                                }
                                            }
                                        } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                            Casilla44SL.setText("" + sobrante_oferta_int);
                                            Casilla45SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int casilla84numresiduo2 = casilla84numresiduo - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= casilla85num) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            int casilla85numresiduo = casilla85num - casilla38numresiduo2;
                                            if (casilla85numresiduo <= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + casilla85numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla85numresiduo;
                                                if (sobrante_oferta_int_residuo <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo;
                                                    //final de la rama...
                                                } else if (sobrante_oferta_int_residuo >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla86num;
                                                    //final de la rama...
                                                }
                                            } else if (casilla85numresiduo >= sobrante_oferta_int) {
                                                Casilla45SL.setText("" + sobrante_oferta_int);
                                                Casilla46SL.setText("0");
                                                int casilla85numresiduo2 = casilla85numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo2 >= casilla85num) {
                                            Casilla35SL.setText("" + casilla85num);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo3 = casilla38numresiduo2 - casilla85num;
                                            if (casilla38numresiduo3 <= casilla86num) {
                                                Casilla36SL.setText("" + casilla38numresiduo3);
                                                int casilla86numresiduo = casilla86num - casilla38numresiduo3;
                                                if (casilla86numresiduo <= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + casilla86numresiduo);
                                                    int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla86numresiduo;
                                                    //final de la rama...
                                                } else if (casilla86numresiduo >= sobrante_oferta_int) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int);
                                                    int casilla86numresiduo2 = casilla86numresiduo - sobrante_oferta_int;
                                                    //final de la rama...
                                                }
                                            } else if (casilla38numresiduo3 >= casilla86num) {
                                                Casilla36SL.setText("" + casilla86num);
                                                Casilla46SL.setText("0");
                                                int casilla38numresiduo4 = casilla38numresiduo3 - casilla86num;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    Casilla36SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo2;
                                        if (sobrante_oferta_int_residuo <= casilla84num) {
                                            Casilla44SL.setText("" + sobrante_oferta_int_residuo);
                                            Casilla45SL.setText("0");
                                            Casilla46SL.setText("0");
                                            int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                            if (sobrante_oferta_int_residuo2 <= casilla85num) {
                                                Casilla45SL.setText("" + sobrante_oferta_int_residuo2);
                                                int casilla85numresiduo = casilla85num - sobrante_oferta_int_residuo2;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo2 >= casilla85num) {
                                                Casilla45SL.setText("" + casilla85num);
                                                int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla85num;
                                                if (sobrante_oferta_int_residuo3 <= casilla86num) {
                                                    Casilla46SL.setText("" + sobrante_oferta_int_residuo3);
                                                    int casilla86numresiduo = casilla86num - sobrante_oferta_int_residuo3;
                                                    //final de la tabla...
                                                } else if (sobrante_oferta_int_residuo3 >= casilla86num) {
                                                    Casilla46SL.setText("" + casilla86num);
                                                    int sobrante_oferta_int_residuo4 = sobrante_oferta_int_residuo3 - casilla86num;
                                                    //final de la tabla...
                                                }
                                            }
                                        }
                                    } else if (casilla83numresiduo2 >= sobrante_oferta_int) {
                                        Casilla43SL.setText("" + sobrante_oferta_int);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        Casilla46SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - sobrante_oferta_int;
                                        //final de la rama
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("2") && vNcompradores.equals("6")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla86.isEmpty() && !casilla27.isEmpty() && !casilla37.isEmpty() && !casilla87.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("3") && vNcompradores.equals("2")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty()) {
                    deshabilitarTabla();
                    MensajeResultado.setVisibility(View.VISIBLE);
                    TablaCalculadoraSL.setVisibility(View.VISIBLE);
                    C1SL.setVisibility(View.VISIBLE);
                    C2SL.setVisibility(View.VISIBLE);
                    C3SL.setVisibility(View.VISIBLE);
                    P1SL.setVisibility(View.VISIBLE);
                    P2SL.setVisibility(View.VISIBLE);
                    Casilla22SL.setVisibility(View.VISIBLE);
                    Casilla23SL.setVisibility(View.VISIBLE);
                    Casilla32SL.setVisibility(View.VISIBLE);
                    Casilla33SL.setVisibility(View.VISIBLE);
                    Casilla42SL.setVisibility(View.VISIBLE);
                    Casilla43SL.setVisibility(View.VISIBLE);
                    Casilla82SL.setVisibility(View.VISIBLE);
                    Casilla83SL.setVisibility(View.VISIBLE);
                    Casilla28SL.setVisibility(View.VISIBLE);
                    Casilla38SL.setVisibility(View.VISIBLE);
                    Casilla48SL.setVisibility(View.VISIBLE);
                    int casilla28num = Integer.parseInt(casilla28);
                    int casilla38num = Integer.parseInt(casilla38);
                    int casilla48num = Integer.parseInt(casilla48);
                    int suma_demanda = casilla28num + casilla38num + casilla48num;

                    int casilla82num = Integer.parseInt(casilla82);
                    int casilla83num = Integer.parseInt(casilla83);
                    int suma_oferta = casilla82num + casilla83num;
                    Casilla28SL.setText(casilla28);
                    Casilla38SL.setText(casilla38);
                    Casilla48SL.setText(casilla48);
                    Casilla82SL.setText(casilla82);
                    Casilla83SL.setText(casilla83);

                    if (suma_demanda == suma_oferta) {
                        MensajeResultado.setText("La tabla dada la asignación está balanceada, así que su número de filas y columnas se mantiene, " +
                                "ahora la asignación en la tabla de solución, es la asignación más optima para un resultado.");
                        ResultadoSL.setText(" " + suma_oferta + " / " + suma_demanda);
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla28num);
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo;
                                        //final de la rama...
                                    } else if (casilla83numresiduo >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        int casilla83numresiduo2 = casilla83numresiduo - casilla48num;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    //final de la rama...
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (casilla82numresiduo2 <= casilla48num) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int casilla48numresiduo = casilla48num - casilla82numresiduo2;
                                    if (casilla48numresiduo <= casilla83num) {
                                        Casilla43SL.setText("" + casilla48numresiduo);
                                        int casilla83numresiduo = casilla83num - casilla48numresiduo;
                                        //final de la rama...
                                    } else if (casilla48numresiduo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int casilla48numresiduo2 = casilla48numresiduo - casilla83num;
                                        //final de la rama...
                                    }
                                } else if (casilla82numresiduo2 >= casilla48num) {
                                    Casilla42SL.setText("" + casilla48num);
                                    Casilla43SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - casilla48num;
                                    //final de la rama...
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    //final de la rama...
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo2;
                                        //final de la rama...
                                    } else if (casilla83numresiduo2 >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        int casilla83numresiduo3 = casilla83numresiduo2 - casilla48num;
                                        //final de la rama...
                                    }
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                //final de la rama...
                            }
                        }
                    } else if (suma_demanda < suma_oferta) {
                        int sobrante_oferta_int = suma_oferta - suma_demanda;
                        int resultado = sobrante_oferta_int + suma_demanda;
                        String sobrante_oferta = String.valueOf(sobrante_oferta_int);
                        C4SL.setVisibility(View.VISIBLE);
                        Casilla52SL.setVisibility(View.VISIBLE);
                        Casilla53SL.setVisibility(View.VISIBLE);
                        Casilla58SL.setVisibility(View.VISIBLE);
                        Casilla52SL.setText(casilla52);
                        Casilla53SL.setText(casilla53);
                        Casilla58SL.setText(sobrante_oferta);
                        ResultadoSL.setText(" " + suma_oferta + " / " + resultado);
                        MensajeResultado.setText("La suma de las demandas es menor a la suma de las ofertas, en este caso es necesario" +
                                " añadir una fila ficticia que solo se tendrá en cuenta para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla82numresiduo <= casilla38num) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                Casilla52SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        Casilla53SL.setText("0");
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo;
                                        //final de la rama...
                                    } else if (casilla83numresiduo >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        int casilla83numresiduo2 = casilla83numresiduo - casilla48num;
                                        if (casilla83numresiduo2 <= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + casilla83numresiduo2);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo2;
                                            //final de la rama...
                                        } else if (casilla83numresiduo2 >= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + sobrante_oferta_int);
                                            int casilla83numresiduo3 = casilla83numresiduo2 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    Casilla53SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    //final de la rama...
                                }
                            } else if (casilla82numresiduo >= casilla38num) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (casilla82numresiduo2 <= casilla48num) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    Casilla52SL.setText("0");
                                    int casilla48numresiduo = casilla48num - casilla82numresiduo2;
                                    if (casilla48numresiduo <= casilla83num) {
                                        Casilla43SL.setText("" + casilla48numresiduo);
                                        int casilla83numresiduo = casilla83num - casilla48numresiduo;
                                        if (casilla83numresiduo <= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + casilla83numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo;
                                            //final de la rama...
                                        } else if (casilla83numresiduo >= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + sobrante_oferta_int);
                                            int casilla83numresiduo2 = casilla83numresiduo - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla48numresiduo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int casilla48numresiduo2 = casilla48numresiduo - casilla83num;
                                        //final de la rama...
                                    }
                                } else if (casilla82numresiduo2 >= casilla48num) {
                                    Casilla42SL.setText("" + casilla48num);
                                    Casilla43SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - casilla48num;
                                    if (casilla82numresiduo3 <= sobrante_oferta_int) {
                                        Casilla52SL.setText("" + casilla82numresiduo3);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla82numresiduo3;
                                        if (sobrante_oferta_int_residuo <= casilla83num) {
                                            Casilla53SL.setText("" + sobrante_oferta_int_residuo);
                                            int casilla83numresiduo = casilla83num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla83num) {
                                            Casilla53SL.setText("" + casilla83num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla83num;
                                            //final de la rama...
                                        }
                                    } else if (casilla82numresiduo3 >= sobrante_oferta_int) {
                                        Casilla52SL.setText("" + sobrante_oferta_int);
                                        Casilla53SL.setText("0");
                                        int casilla82numresiduo4 = casilla82numresiduo3 - sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            Casilla52SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    Casilla53SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    //final de la rama...
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        Casilla53SL.setText("0");
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo2;
                                        //final de la rama...
                                    } else if (casilla83numresiduo2 >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        int casilla83numresiduo3 = casilla83numresiduo2 - casilla48num;
                                        if (casilla83numresiduo3 <= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + casilla83numresiduo3);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo3;
                                            //final de la rama...
                                        } else if (casilla83numresiduo3 >= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + sobrante_oferta_int);
                                            int casilla83numresiduo4 = casilla83numresiduo3 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                Casilla53SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                //final de la rama...
                            }
                        }
                    } else if (suma_demanda > suma_oferta) {
                        int sobrante_demanda_int = suma_demanda - suma_oferta;
                        int resultado = sobrante_demanda_int + suma_oferta;
                        String sobrante_demanda = String.valueOf(sobrante_demanda_int);
                        P3SL.setVisibility(View.VISIBLE);
                        Casilla24SL.setVisibility(View.VISIBLE);
                        Casilla34SL.setVisibility(View.VISIBLE);
                        Casilla44SL.setVisibility(View.VISIBLE);
                        Casilla34SL.setText(casilla34);
                        Casilla44SL.setText(casilla44);
                        Casilla84SL.setVisibility(View.VISIBLE);
                        Casilla84SL.setText(sobrante_demanda);
                        ResultadoSL.setText(" " + resultado + " / " + suma_demanda);
                        MensajeResultado.setText("la suma de las ofertas es menor a la suma de las demandas, en este caso es necesario añadir una columna ficticia a la tabla, que solo se tendrá en cuenta " +
                                " para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (casilla48num <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla48num);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - casilla48num;
                                    //final de la rama
                                } else if (casilla48num >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int casilla48numresiduo = casilla48num - casilla82numresiduo2;
                                    if (casilla48numresiduo <= casilla83num) {
                                        Casilla43SL.setText("" + casilla48numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo = casilla83num - casilla48numresiduo;
                                        //final de la rama...
                                    } else if (casilla48numresiduo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int casilla48numresiduo2 = casilla48numresiduo - casilla83num;
                                        if (casilla48numresiduo2 <= sobrante_demanda_int) {
                                            Casilla44SL.setText("" + casilla48numresiduo2);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo2;
                                            //final de la rama...
                                        } else if (casilla48numresiduo2 >= sobrante_demanda_int) {
                                            Casilla44SL.setText("" + sobrante_demanda_int);
                                            int sobrante_oferta_int_residuo3 = casilla48numresiduo2 - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo;
                                        if (casilla48numresiduo <= sobrante_demanda_int) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo;
                                            //final de la rama...
                                        } else if (casilla48numresiduo >= sobrante_demanda_int) {
                                            Casilla44SL.setText("" + sobrante_demanda_int);
                                            int casilla48numresiduo2 = casilla48numresiduo - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - casilla48num;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= sobrante_demanda_int) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        int sobrante_demanda_int_residuo = casilla38numresiduo2 - sobrante_demanda_int;
                                        if (sobrante_demanda_int_residuo <= casilla48num) {
                                            Casilla44SL.setText("" + sobrante_demanda_int_residuo);
                                            int casilla48numresiduo = casilla48num - sobrante_demanda_int;
                                            //final de la rama...
                                        } else if (sobrante_demanda_int_residuo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int sobrante_oferta_int_residuo = casilla48num - sobrante_demanda_int_residuo;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo2 >= sobrante_demanda_int) {
                                        Casilla34SL.setText("" + sobrante_demanda_int);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= sobrante_demanda_int) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla28numresiduo2;
                                    if (sobrante_demanda_int_residuo <= casilla38num) {
                                        Casilla34SL.setText("" + sobrante_demanda_int_residuo);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo = casilla38num - sobrante_demanda_int_residuo;
                                        //final de la rama...
                                    } else if (sobrante_demanda_int_residuo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla38num;
                                        if (sobrante_demanda_int_residuo2 <= casilla48num) {
                                            Casilla44SL.setText("" + sobrante_demanda_int_residuo2);
                                            int casilla48numresiduo = casilla48num - sobrante_demanda_int_residuo2;
                                            //final de la rama...
                                        } else if (sobrante_demanda_int_residuo2 >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int casilla84numresiduo3 = sobrante_demanda_int_residuo2 - casilla48num;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= sobrante_demanda_int) {
                                    Casilla24SL.setText("" + sobrante_demanda_int);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - sobrante_demanda_int;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= sobrante_demanda_int) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo;
                                        if (sobrante_demanda_int_residuo <= casilla48num) {
                                            Casilla44SL.setText("" + sobrante_demanda_int_residuo);
                                            int casilla48numresiduo = casilla48num - sobrante_demanda_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_demanda_int_residuo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla48num;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo >= sobrante_demanda_int) {
                                        Casilla34SL.setText("" + sobrante_demanda_int);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo2;
                                        if (casilla48numresiduo <= sobrante_demanda_int) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo;
                                            //final de la rama...
                                        } else if (casilla48numresiduo >= sobrante_demanda_int) {
                                            Casilla44SL.setText("" + sobrante_demanda_int);
                                            int casilla48numresiduo2 = casilla48numresiduo - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo2 >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - casilla48num;
                                        //final de la rama
                                    }
                                }
                            }
                        }

                    }
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("3") && vNcompradores.equals("3")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla84.isEmpty()) {
                    deshabilitarTabla();
                    MensajeResultado.setVisibility(View.VISIBLE);
                    TablaCalculadoraSL.setVisibility(View.VISIBLE);
                    C1SL.setVisibility(View.VISIBLE);
                    C2SL.setVisibility(View.VISIBLE);
                    C3SL.setVisibility(View.VISIBLE);
                    P1SL.setVisibility(View.VISIBLE);
                    P2SL.setVisibility(View.VISIBLE);
                    P3SL.setVisibility(View.VISIBLE);
                    Casilla22SL.setVisibility(View.VISIBLE);
                    Casilla23SL.setVisibility(View.VISIBLE);
                    Casilla24SL.setVisibility(View.VISIBLE);
                    Casilla32SL.setVisibility(View.VISIBLE);
                    Casilla33SL.setVisibility(View.VISIBLE);
                    Casilla34SL.setVisibility(View.VISIBLE);
                    Casilla42SL.setVisibility(View.VISIBLE);
                    Casilla43SL.setVisibility(View.VISIBLE);
                    Casilla44SL.setVisibility(View.VISIBLE);
                    Casilla82SL.setVisibility(View.VISIBLE);
                    Casilla83SL.setVisibility(View.VISIBLE);
                    Casilla84SL.setVisibility(View.VISIBLE);
                    Casilla28SL.setVisibility(View.VISIBLE);
                    Casilla38SL.setVisibility(View.VISIBLE);
                    Casilla48SL.setVisibility(View.VISIBLE);
                    int casilla28num = Integer.parseInt(casilla28);
                    int casilla38num = Integer.parseInt(casilla38);
                    int casilla48num = Integer.parseInt(casilla48);
                    int suma_demanda = casilla28num + casilla38num + casilla48num;

                    int casilla82num = Integer.parseInt(casilla82);
                    int casilla83num = Integer.parseInt(casilla83);
                    int casilla84num = Integer.parseInt(casilla84);
                    int suma_oferta = casilla82num + casilla83num + casilla84num;
                    Casilla28SL.setText(casilla28);
                    Casilla38SL.setText(casilla38);
                    Casilla48SL.setText(casilla48);
                    Casilla82SL.setText(casilla82);
                    Casilla83SL.setText(casilla83);
                    Casilla84SL.setText(casilla84);
                    if (suma_demanda == suma_oferta) {
                        MensajeResultado.setText("La tabla dada la asignación está balanceada, así que su número de filas y columnas se mantiene, " +
                                "ahora la asignación en la tabla de solución, es la asignación más optima para un resultado.");
                        ResultadoSL.setText(" " + suma_oferta + " / " + suma_demanda);
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (casilla48num <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla48num);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - casilla48num;
                                    //final de la rama
                                } else if (casilla48num >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int casilla48numresiduo = casilla48num - casilla82numresiduo2;
                                    if (casilla48numresiduo <= casilla83num) {
                                        Casilla43SL.setText("" + casilla48numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo = casilla83num - casilla48numresiduo;
                                        //final de la rama...
                                    } else if (casilla48numresiduo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int casilla48numresiduo2 = casilla48numresiduo - casilla83num;
                                        if (casilla48numresiduo2 <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo2);
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo2;
                                            //final de la rama...
                                        } else if (casilla48numresiduo2 >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla84numresiduo3 = casilla48numresiduo2 - casilla84num;
                                            //final de la rama...
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo;
                                        if (casilla48numresiduo <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo;
                                            //final de la rama...
                                        } else if (casilla48numresiduo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla48numresiduo2 = casilla48numresiduo - casilla84num;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - casilla48num;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        int casilla84numresiduo = casilla38numresiduo2 - casilla84num;
                                        if (casilla84numresiduo <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int casilla48numresiduo = casilla48num - casilla84num;
                                            //final de la rama...
                                        } else if (casilla84numresiduo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int casilla84numresiduo2 = casilla84num - casilla48num;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        //final de la rama...
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        //final de la rama...
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        if (casilla84numresiduo2 <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo2);
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo2;
                                            //final de la rama...
                                        } else if (casilla84numresiduo2 >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int casilla84numresiduo3 = casilla84numresiduo2 - casilla48num;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        if (casilla84numresiduo <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo;
                                            //final de la rama...
                                        } else if (casilla84numresiduo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int sobrante_demanda_int_residuo2 = casilla84numresiduo - casilla48num;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        //final de la rama...
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo2;
                                        if (casilla48numresiduo <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo;
                                            //final de la rama...
                                        } else if (casilla48numresiduo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla48numresiduo2 = casilla48numresiduo - casilla84num;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo2 >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - casilla48num;
                                        //final de la rama
                                    }
                                }
                            }
                        }

                    } else if (suma_demanda < suma_oferta) {
                        int sobrante_oferta_int = suma_oferta - suma_demanda;
                        int resultado = sobrante_oferta_int + suma_demanda;
                        String sobrante_oferta = String.valueOf(sobrante_oferta_int);
                        C4SL.setVisibility(View.VISIBLE);
                        Casilla52SL.setVisibility(View.VISIBLE);
                        Casilla53SL.setVisibility(View.VISIBLE);
                        Casilla54SL.setVisibility(View.VISIBLE);
                        Casilla58SL.setVisibility(View.VISIBLE);
                        Casilla52SL.setText(casilla52);
                        Casilla53SL.setText(casilla53);
                        Casilla54SL.setText(casilla54);
                        Casilla58SL.setText(sobrante_oferta);
                        ResultadoSL.setText(" " + suma_oferta + " / " + resultado);
                        MensajeResultado.setText("La suma de las demandas es menor a la suma de las ofertas, en este caso es necesario" +
                                " añadir una fila ficticia que solo se tendrá en cuenta para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (casilla48num <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla48num);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - casilla48num;
                                    if (casilla82numresiduo3 <= sobrante_oferta_int) {
                                        Casilla52SL.setText("" + casilla82numresiduo3);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla82numresiduo3;
                                        if (sobrante_oferta_int_residuo <= casilla83num) {
                                            Casilla53SL.setText("" + sobrante_oferta_int_residuo);
                                            Casilla54SL.setText("0");
                                            int casilla83numresiduo = casilla83num - sobrante_oferta_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_oferta_int_residuo >= casilla83num) {
                                            Casilla53SL.setText("" + casilla83num);
                                            int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla83num;
                                            if (sobrante_oferta_int_residuo2 <= casilla84num) {
                                                Casilla54SL.setText("" + sobrante_oferta_int_residuo2);
                                                int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo2;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo2 >= casilla84num) {
                                                Casilla54SL.setText("" + casilla84num);
                                                int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2 - casilla84num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla82numresiduo3 >= sobrante_oferta_int) {
                                        Casilla52SL.setText("" + sobrante_oferta_int);
                                        Casilla53SL.setText("0");
                                        Casilla54SL.setText("0");
                                        int casilla82numresiduo4 = casilla82numresiduo3 - sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                } else if (casilla48num >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int casilla48numresiduo = casilla48num - casilla82numresiduo2;
                                    if (casilla48numresiduo <= casilla83num) {
                                        Casilla43SL.setText("" + casilla48numresiduo);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo = casilla83num - casilla48numresiduo;
                                        if (casilla83numresiduo <= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + casilla83numresiduo);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo;
                                            if (sobrante_oferta_int_residuo <= casilla84num) {
                                                Casilla54SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                                Casilla54SL.setText("" + casilla84num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                                //final de la rama...
                                            }
                                        } else if (casilla83numresiduo >= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + sobrante_oferta_int);
                                            Casilla54SL.setText("0");
                                            int casilla83numresiduo2 = casilla83numresiduo - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla48numresiduo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int casilla48numresiduo2 = casilla48numresiduo - casilla83num;
                                        if (casilla48numresiduo2 <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo2);
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo2;
                                            if (casilla84numresiduo <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                                //final de la rama...
                                            } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo2 = casilla84numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla48numresiduo2 >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla84numresiduo3 = casilla48numresiduo2 - casilla84num;
                                            if (casilla84numresiduo3 <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo3);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo3;
                                                //final de la rama...
                                            } else if (casilla84numresiduo3 >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo4 = casilla84numresiduo3 - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                Casilla52SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        Casilla53SL.setText("0");
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo;
                                        if (casilla48numresiduo <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo;
                                            if (casilla84numresiduo <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                                //final de la rama...
                                            } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo2 = casilla84numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla48numresiduo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            Casilla54SL.setText("0");
                                            int casilla48numresiduo2 = casilla48numresiduo - casilla84num;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - casilla48num;
                                        if (casilla83numresiduo2 <= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + casilla83numresiduo2);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo2;
                                            if (sobrante_oferta_int_residuo <= casilla84num) {
                                                Casilla54SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                                Casilla54SL.setText("" + casilla84num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                                //final de la rama...
                                            }
                                        } else if (casilla83numresiduo2 >= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + sobrante_oferta_int);
                                            Casilla54SL.setText("0");
                                            int casilla83numresiduo3 = casilla83numresiduo2 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    Casilla53SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        int casilla84numresiduo = casilla38numresiduo2 - casilla84num;
                                        if (casilla84numresiduo <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            Casilla54SL.setText("0");
                                            int casilla48numresiduo = casilla48num - casilla84num;
                                            //final de la rama...
                                        } else if (casilla84numresiduo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int casilla84numresiduo2 = casilla84num - casilla48num;
                                            if (casilla84numresiduo2 <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo2);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo2;
                                                //final de la rama...
                                            } else if (casilla84numresiduo2 >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo3 = casilla84numresiduo2 - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        //final de la rama...
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                Casilla53SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        Casilla44SL.setText("0");
                                        Casilla54SL.setText("0");
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        //final de la rama...
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        if (casilla84numresiduo2 <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo2);
                                            Casilla54SL.setText("0");
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo2;
                                            //final de la rama...
                                        } else if (casilla84numresiduo2 >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int casilla84numresiduo3 = casilla84numresiduo2 - casilla48num;
                                            if (casilla84numresiduo3 <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo3);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo3;
                                                //final de la rama...
                                            } else if (casilla84numresiduo3 >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo4 = casilla84numresiduo3 - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    Casilla54SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    //final de la rama...
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    Casilla53SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        if (casilla84numresiduo <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            Casilla54SL.setText("0");
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo;
                                            //final de la rama...
                                        } else if (casilla84numresiduo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            int casilla84numresiduo2 = casilla84numresiduo - casilla48num;
                                            if (casilla84numresiduo2 <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo2);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo2;
                                                //final de la rama...
                                            } else if (casilla84numresiduo2 >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo3 = casilla84numresiduo2 - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        Casilla54SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        //final de la rama...
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo2;
                                        if (casilla48numresiduo <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo;
                                            if (casilla84numresiduo <= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + casilla84numresiduo);
                                                int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla84numresiduo;
                                                //final de la rama...
                                            } else if (casilla84numresiduo >= sobrante_oferta_int) {
                                                Casilla54SL.setText("" + sobrante_oferta_int);
                                                int casilla84numresiduo2 = casilla84numresiduo - sobrante_oferta_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla48numresiduo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            Casilla54SL.setText("0");
                                            int casilla48numresiduo2 = casilla48numresiduo - casilla84num;
                                            //final de la rama...
                                        }
                                    } else if (casilla83numresiduo2 >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - casilla48num;
                                        if (casilla83numresiduo3 <= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + casilla83numresiduo3);
                                            int sobrante_oferta_int_residuo = sobrante_oferta_int - casilla83numresiduo3;
                                            if (sobrante_oferta_int_residuo <= casilla84num) {
                                                Casilla54SL.setText("" + sobrante_oferta_int_residuo);
                                                int casilla84numresiduo = casilla84num - sobrante_oferta_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_oferta_int_residuo >= casilla84num) {
                                                Casilla54SL.setText("" + casilla84num);
                                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo - casilla84num;
                                                //final de la rama...
                                            }
                                        } else if (casilla83numresiduo3 >= sobrante_oferta_int) {
                                            Casilla53SL.setText("" + sobrante_oferta_int);
                                            Casilla54SL.setText("0");
                                            int casilla83numresiduo4 = casilla83numresiduo3 - sobrante_oferta_int;
                                            //final de la rama...
                                        }
                                    }
                                }
                            }
                        }


                    } else if (suma_demanda > suma_oferta) {
                        int sobrante_demanda_int = suma_demanda - suma_oferta;
                        int resultado = sobrante_demanda_int + suma_oferta;
                        String sobrante_demanda = String.valueOf(sobrante_demanda_int);
                        P4SL.setVisibility(View.VISIBLE);
                        Casilla25SL.setVisibility(View.VISIBLE);
                        Casilla35SL.setVisibility(View.VISIBLE);
                        Casilla45SL.setVisibility(View.VISIBLE);
                        Casilla85SL.setVisibility(View.VISIBLE);
                        Casilla85SL.setText(sobrante_demanda);
                        ResultadoSL.setText(" " + resultado + " / " + suma_demanda);
                        MensajeResultado.setText("la suma de las ofertas es menor a la suma de las demandas, en este caso es necesario añadir una columna ficticia a la tabla, que solo se tendrá en cuenta " +
                                " para la asignación más optima.");
                        if (casilla28num <= casilla82num) {
                            Casilla22SL.setText("" + casilla28num);
                            Casilla23SL.setText("0");
                            Casilla24SL.setText("0");
                            Casilla25SL.setText("0");
                            int casilla82numresiduo = casilla82num - casilla28num;
                            if (casilla38num <= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla38num);
                                Casilla33SL.setText("0");
                                Casilla34SL.setText("0");
                                Casilla35SL.setText("0");
                                int casilla82numresiduo2 = casilla82numresiduo - casilla38num;
                                if (casilla48num <= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla48num);
                                    Casilla43SL.setText("0");
                                    Casilla44SL.setText("0");
                                    Casilla45SL.setText("0");
                                    int casilla82numresiduo3 = casilla82numresiduo2 - casilla48num;
                                    //final de la rama
                                } else if (casilla48num >= casilla82numresiduo2) {
                                    Casilla42SL.setText("" + casilla82numresiduo2);
                                    int casilla48numresiduo = casilla48num - casilla82numresiduo2;
                                    if (casilla48numresiduo <= casilla83num) {
                                        Casilla43SL.setText("" + casilla48numresiduo);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla83numresiduo = casilla83num - casilla48numresiduo;
                                        //final de la rama...
                                    } else if (casilla48numresiduo >= casilla83num) {
                                        Casilla43SL.setText("" + casilla83num);
                                        int casilla48numresiduo2 = casilla48numresiduo - casilla83num;
                                        if (casilla48numresiduo2 <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo2);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo2;
                                            //final de la rama...
                                        } else if (casilla48numresiduo2 >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla48numresiduo3 = casilla48numresiduo2 - casilla84num;
                                            if (casilla48numresiduo3 <= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + casilla48numresiduo3);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo3;
                                                //final de la rama...
                                            } else if (casilla48numresiduo3 >= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + sobrante_demanda_int);
                                                int casilla48numresiduo4 = casilla48numresiduo3 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    }
                                }
                            } else if (casilla38num >= casilla82numresiduo) {
                                Casilla32SL.setText("" + casilla82numresiduo);
                                Casilla42SL.setText("0");
                                int casilla38numresiduo = casilla38num - casilla82numresiduo;
                                if (casilla38numresiduo <= casilla83num) {
                                    Casilla33SL.setText("" + casilla38numresiduo);
                                    Casilla34SL.setText("0");
                                    Casilla35SL.setText("0");
                                    int casilla83numresiduo = casilla83num - casilla38numresiduo;
                                    if (casilla83numresiduo <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo;
                                        if (casilla48numresiduo <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo;
                                            //final de la rama...
                                        } else if (casilla48numresiduo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla48numresiduo2 = casilla48numresiduo - casilla84num;
                                            if (casilla48numresiduo2 <= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + casilla48numresiduo2);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo2;
                                                //final de la rama...
                                            } else if (casilla48numresiduo2 >= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + sobrante_demanda_int);
                                                int casilla48numresiduo3 = casilla48numresiduo2 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla83numresiduo >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla83numresiduo2 = casilla83numresiduo - casilla48num;
                                        //final de la rama...
                                    }
                                } else if (casilla38numresiduo >= casilla83num) {
                                    Casilla33SL.setText("" + casilla83num);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo - casilla83num;
                                    if (casilla38numresiduo2 <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo2);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo2;
                                        if (casilla84numresiduo <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo;
                                            if (casilla48numresiduo <= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + casilla48numresiduo);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo;
                                                //final de la rama...
                                            } else if (casilla48numresiduo >= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + sobrante_demanda_int);
                                                int casilla48numresiduo2 = casilla48numresiduo - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla84numresiduo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo2 = casilla84numresiduo - casilla48num;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo2 >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo3 = casilla38numresiduo2 - casilla84num;
                                        if (casilla38numresiduo3 <= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + casilla38numresiduo3);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo3;
                                            if (sobrante_demanda_int_residuo <= casilla48num) {
                                                Casilla45SL.setText("" + sobrante_demanda_int_residuo);
                                                int casilla48numresiduo = casilla48num - sobrante_demanda_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_demanda_int_residuo >= casilla48num) {
                                                Casilla45SL.setText("" + casilla48num);
                                                int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla48num;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo3 >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo4 = casilla38numresiduo3 - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    }
                                }
                            }
                        } else if (casilla28num >= casilla82num) {
                            Casilla22SL.setText("" + casilla82num);
                            Casilla32SL.setText("0");
                            Casilla42SL.setText("0");
                            int casilla28numresiduo = casilla28num - casilla82num;
                            if (casilla28numresiduo >= casilla83num) {
                                Casilla23SL.setText("" + casilla83num);
                                Casilla33SL.setText("0");
                                Casilla43SL.setText("0");
                                int casilla28numresiduo2 = casilla28numresiduo - casilla83num;
                                if (casilla28numresiduo2 <= casilla84num) {
                                    Casilla24SL.setText("" + casilla28numresiduo2);
                                    Casilla25SL.setText("0");
                                    int casilla84numresiduo = casilla84num - casilla28numresiduo2;
                                    if (casilla84numresiduo <= casilla38num) {
                                        Casilla34SL.setText("" + casilla84numresiduo);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla38numresiduo = casilla38num - casilla84numresiduo;
                                        if (casilla38numresiduo <= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + casilla38numresiduo);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo;
                                            if (sobrante_demanda_int_residuo <= casilla48num) {
                                                Casilla45SL.setText("" + sobrante_demanda_int_residuo);
                                                int casilla48numresiduo = casilla48num - sobrante_demanda_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_demanda_int_residuo >= casilla48num) {
                                                Casilla45SL.setText("" + casilla48num);
                                                int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla48num;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo2 = casilla38numresiduo - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    } else if (casilla84numresiduo >= casilla38num) {
                                        Casilla34SL.setText("" + casilla38num);
                                        Casilla35SL.setText("0");
                                        int casilla84numresiduo2 = casilla84numresiduo - casilla38num;
                                        if (casilla84numresiduo2 <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo2);
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo2;
                                            if (casilla48numresiduo <= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + casilla48numresiduo);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo;
                                                //final de la rama...
                                            } else if (casilla48numresiduo >= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + sobrante_demanda_int);
                                                int casilla48numresiduo2 = casilla48numresiduo - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla84numresiduo2 >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo3 = casilla84numresiduo2 - casilla48num;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla28numresiduo2 >= casilla84num) {
                                    Casilla24SL.setText("" + casilla84num);
                                    Casilla34SL.setText("0");
                                    Casilla44SL.setText("0");
                                    int casilla28numresiduo3 = casilla28numresiduo2 - casilla84num;
                                    if (casilla28numresiduo3 <= sobrante_demanda_int) {
                                        Casilla25SL.setText("" + casilla28numresiduo3);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla28numresiduo3;
                                        if (sobrante_demanda_int_residuo <= casilla38num) {
                                            Casilla35SL.setText("" + sobrante_demanda_int_residuo);
                                            Casilla44SL.setText("0");
                                            int casilla38numresiduo = casilla38num - sobrante_demanda_int_residuo;
                                            //final de la rama...
                                        } else if (sobrante_demanda_int_residuo >= casilla38num) {
                                            Casilla35SL.setText("" + casilla38num);
                                            int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla38num;
                                            if (sobrante_demanda_int_residuo2 <= casilla48num) {
                                                Casilla45SL.setText("" + sobrante_demanda_int_residuo2);
                                                int casilla48numresiduo = casilla48num - sobrante_demanda_int_residuo2;
                                                //final de la rama...
                                            } else if (sobrante_demanda_int_residuo2 >= casilla48num) {
                                                Casilla45SL.setText("" + casilla48num);
                                                int sobrante_demanda_int_residuo3 = sobrante_demanda_int_residuo2 - casilla48num;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla28numresiduo3 >= sobrante_demanda_int) {
                                        Casilla25SL.setText("" + sobrante_demanda_int);
                                        Casilla35SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla28numresiduo4 = casilla28numresiduo3 - sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            } else if (casilla28numresiduo <= casilla83num) {
                                Casilla23SL.setText("" + casilla28numresiduo);
                                Casilla24SL.setText("0");
                                int casilla83numresiduo = casilla83num - casilla28numresiduo;
                                if (casilla83numresiduo <= casilla38num) {
                                    Casilla33SL.setText("" + casilla83numresiduo);
                                    Casilla43SL.setText("0");
                                    int casilla38numresiduo = casilla38num - casilla83numresiduo;
                                    if (casilla38numresiduo <= casilla84num) {
                                        Casilla34SL.setText("" + casilla38numresiduo);
                                        int casilla84numresiduo = casilla84num - casilla38numresiduo;
                                        if (casilla84numresiduo <= casilla48num) {
                                            Casilla44SL.setText("" + casilla84numresiduo);
                                            int casilla48numresiduo = casilla48num - casilla84numresiduo;
                                            if (casilla48numresiduo <= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + casilla48numresiduo);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo;
                                                //final de la rama...
                                            } else if (casilla48numresiduo >= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + sobrante_demanda_int);
                                                int casilla48numresiduo2 = casilla48numresiduo - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        } else if (casilla84numresiduo >= casilla48num) {
                                            Casilla44SL.setText("" + casilla48num);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo2 = casilla84numresiduo - casilla48num;
                                            //final de la rama...
                                        }
                                    } else if (casilla38numresiduo >= casilla84num) {
                                        Casilla34SL.setText("" + casilla84num);
                                        Casilla44SL.setText("0");
                                        int casilla38numresiduo2 = casilla38numresiduo - casilla84num;
                                        if (casilla38numresiduo2 <= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + casilla38numresiduo2);
                                            int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla38numresiduo2;
                                            if (sobrante_demanda_int_residuo <= casilla48num) {
                                                Casilla45SL.setText("" + sobrante_demanda_int_residuo);
                                                int casilla48numreisduo = casilla48num - sobrante_demanda_int_residuo;
                                                //final de la rama...
                                            } else if (sobrante_demanda_int_residuo >= casilla48num) {
                                                Casilla45SL.setText("" + casilla48num);
                                                int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo - casilla48num;
                                                //final de la rama...
                                            }
                                        } else if (casilla38numresiduo2 >= sobrante_demanda_int) {
                                            Casilla35SL.setText("" + sobrante_demanda_int);
                                            Casilla45SL.setText("0");
                                            int casilla38numresiduo3 = casilla38numresiduo2 - sobrante_demanda_int;
                                            //final de la rama...
                                        }
                                    }
                                } else if (casilla83numresiduo >= casilla38num) {
                                    Casilla33SL.setText("" + casilla38num);
                                    Casilla34SL.setText("0");
                                    int casilla83numresiduo2 = casilla83numresiduo - casilla38num;
                                    if (casilla83numresiduo2 <= casilla48num) {
                                        Casilla43SL.setText("" + casilla83numresiduo2);
                                        int casilla48numresiduo = casilla48num - casilla83numresiduo2;
                                        if (casilla48numresiduo <= casilla84num) {
                                            Casilla44SL.setText("" + casilla48numresiduo);
                                            Casilla45SL.setText("0");
                                            int casilla84numresiduo = casilla84num - casilla48numresiduo;
                                            //final de la rama...
                                        } else if (casilla48numresiduo >= casilla84num) {
                                            Casilla44SL.setText("" + casilla84num);
                                            int casilla48numresiduo2 = casilla48numresiduo - casilla84num;
                                            if (casilla48numresiduo2 <= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + casilla48numresiduo2);
                                                int sobrante_demanda_int_residuo = sobrante_demanda_int - casilla48numresiduo2;
                                                //final de la rama...
                                            } else if (casilla48numresiduo2 >= sobrante_demanda_int) {
                                                Casilla45SL.setText("" + sobrante_demanda_int);
                                                int casilla48numresiduo3 = casilla48numresiduo2 - sobrante_demanda_int;
                                                //final de la rama...
                                            }
                                        }
                                    } else if (casilla83numresiduo2 >= casilla48num) {
                                        Casilla43SL.setText("" + casilla48num);
                                        Casilla44SL.setText("0");
                                        Casilla45SL.setText("0");
                                        int casilla83numresiduo3 = casilla83numresiduo2 - casilla48num;
                                        //final de la rama
                                    }
                                }
                            }
                        }


                    }
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("3") && vNcompradores.equals("4")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla85.isEmpty()) {
                    Tabla3x4();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("3") && vNcompradores.equals("5")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla86.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("3") && vNcompradores.equals("6")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla86.isEmpty() && !casilla27.isEmpty() && !casilla37.isEmpty() && !casilla47.isEmpty() && !casilla87.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("4") && vNcompradores.equals("2")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("4") && vNcompradores.equals("3")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla84.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("4") && vNcompradores.equals("4")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla85.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("4") && vNcompradores.equals("5")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla56.isEmpty() && !casilla86.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("4") && vNcompradores.equals("6")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla56.isEmpty() && !casilla86.isEmpty() && !casilla27.isEmpty() && !casilla37.isEmpty() && !casilla47.isEmpty() && !casilla57.isEmpty() && !casilla87.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("5") && vNcompradores.equals("2")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("5") && vNcompradores.equals("3")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla84.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("5") && vNcompradores.equals("4")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla65.isEmpty() && !casilla85.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("5") && vNcompradores.equals("5")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla65.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla56.isEmpty() && !casilla66.isEmpty() && !casilla86.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("5") && vNcompradores.equals("6")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla65.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla56.isEmpty() && !casilla66.isEmpty() && !casilla86.isEmpty() && !casilla27.isEmpty() && !casilla37.isEmpty() && !casilla47.isEmpty() && !casilla57.isEmpty() && !casilla67.isEmpty() && !casilla87.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("6") && vNcompradores.equals("2")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla72.isEmpty() && !casilla73.isEmpty() && !casilla78.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("6") && vNcompradores.equals("3")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla72.isEmpty() && !casilla73.isEmpty() && !casilla78.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla74.isEmpty() && !casilla84.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("6") && vNcompradores.equals("4")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla72.isEmpty() && !casilla73.isEmpty() && !casilla78.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla74.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla65.isEmpty() && !casilla75.isEmpty() && !casilla85.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("6") && vNcompradores.equals("5")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla72.isEmpty() && !casilla73.isEmpty() && !casilla78.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla74.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla65.isEmpty() && !casilla75.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla56.isEmpty() && !casilla66.isEmpty() && !casilla76.isEmpty() && !casilla86.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            } else if (vNproveedores.equals("6") && vNcompradores.equals("6")) {
                if (!casilla22.isEmpty() && !casilla23.isEmpty() && !casilla28.isEmpty() && !casilla32.isEmpty() && !casilla33.isEmpty() && !casilla38.isEmpty() && !casilla82.isEmpty() && !casilla83.isEmpty() && !casilla42.isEmpty() && !casilla43.isEmpty() && !casilla43.isEmpty() && !casilla52.isEmpty() && !casilla53.isEmpty() && !casilla58.isEmpty() && !casilla62.isEmpty() && !casilla63.isEmpty() && !casilla64.isEmpty() && !casilla68.isEmpty() && !casilla72.isEmpty() && !casilla73.isEmpty() && !casilla78.isEmpty() && !casilla24.isEmpty() && !casilla34.isEmpty() && !casilla44.isEmpty() && !casilla54.isEmpty() && !casilla64.isEmpty() && !casilla74.isEmpty() && !casilla84.isEmpty() && !casilla25.isEmpty() && !casilla35.isEmpty() && !casilla45.isEmpty() && !casilla55.isEmpty() && !casilla65.isEmpty() && !casilla75.isEmpty() && !casilla85.isEmpty() && !casilla26.isEmpty() && !casilla36.isEmpty() && !casilla46.isEmpty() && !casilla56.isEmpty() && !casilla66.isEmpty() && !casilla76.isEmpty() && !casilla86.isEmpty() && !casilla27.isEmpty() && !casilla37.isEmpty() && !casilla47.isEmpty() && !casilla57.isEmpty() && !casilla67.isEmpty() && !casilla77.isEmpty() && !casilla87.isEmpty()) {
                    Toast.makeText(this, "La solución se puede realizar.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Por favor, llena todas las casillas.", Toast.LENGTH_LONG).show();
                }
            }

        }
    }



    public void GenerarNuevaTabla(View view){
        Intent Reset = new Intent(this, CalculadoraTransporte.class);
        startActivity(Reset);
        finish();
    }

    public void GenerarTabla(View view){





        String vNproveedores = Nproveedores.getText().toString();
        String vNcompradores = Ncompradores.getText().toString();


        if(vNproveedores.isEmpty() && vNcompradores.isEmpty()) {
            Toast.makeText(this, "Debes llenar los campos.", Toast.LENGTH_LONG).show();
        }
        else if (vNproveedores.isEmpty() && !vNcompradores.isEmpty()){
            Toast.makeText(this, "Por favor completa el campo de proveedores.", Toast.LENGTH_LONG).show();
        }
        else if (!vNproveedores.isEmpty() && vNcompradores.isEmpty()){
            Toast.makeText(this, "Por favor completa el campo de compradores.", Toast.LENGTH_LONG).show();
        }
        else if(!vNproveedores.isEmpty() && !vNcompradores.isEmpty()){
            NumeroCompradores.setVisibility(View.GONE);
            NumeroProveedores.setVisibility(View.GONE);
            GenerarTabla.setVisibility(View.INVISIBLE);
            GenerarNuevaTabla.setVisibility(View.VISIBLE);
            if(vNproveedores.equals("2") && vNcompradores.equals("2")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();
                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);

            }
            else if(vNproveedores.equals("2") && vNcompradores.equals("3")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();
                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);

                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("2") && vNcompradores.equals("4")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);

                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("2") && vNcompradores.equals("5")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("2") && vNcompradores.equals("6")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                Casilla27.setVisibility(View.VISIBLE);
                Casilla37.setVisibility(View.VISIBLE);
                Casilla87.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
                P6.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("3") && vNcompradores.equals("2")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
        }
            else if(vNproveedores.equals("3") && vNcompradores.equals("3")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("3") && vNcompradores.equals("4")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("3") && vNcompradores.equals("5")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("3") && vNcompradores.equals("6")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                Casilla27.setVisibility(View.VISIBLE);
                Casilla37.setVisibility(View.VISIBLE);
                Casilla47.setVisibility(View.VISIBLE);
                Casilla87.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
                P6.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("4") && vNcompradores.equals("2")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("4") && vNcompradores.equals("3")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("4") && vNcompradores.equals("4")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("4") && vNcompradores.equals("5")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla56.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("4") && vNcompradores.equals("6")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla56.setVisibility(View.VISIBLE);
                Casilla57.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                Casilla27.setVisibility(View.VISIBLE);
                Casilla37.setVisibility(View.VISIBLE);
                Casilla47.setVisibility(View.VISIBLE);
                Casilla87.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
                P6.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("5") && vNcompradores.equals("2")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("5") && vNcompradores.equals("3")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("5") && vNcompradores.equals("4")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla65.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("5") && vNcompradores.equals("5")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla65.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla56.setVisibility(View.VISIBLE);
                Casilla66.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("5") && vNcompradores.equals("6")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla65.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla56.setVisibility(View.VISIBLE);
                Casilla66.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                Casilla27.setVisibility(View.VISIBLE);
                Casilla37.setVisibility(View.VISIBLE);
                Casilla47.setVisibility(View.VISIBLE);
                Casilla57.setVisibility(View.VISIBLE);
                Casilla67.setVisibility(View.VISIBLE);
                Casilla87.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
                P6.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("6") && vNcompradores.equals("2")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generada", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                C6.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla72.setVisibility(View.VISIBLE);
                Casilla73.setVisibility(View.VISIBLE);
                Casilla78.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("6") && vNcompradores.equals("3")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generadá", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                C6.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla72.setVisibility(View.VISIBLE);
                Casilla73.setVisibility(View.VISIBLE);
                Casilla78.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla74.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("6") && vNcompradores.equals("4")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generadá", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                C6.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla72.setVisibility(View.VISIBLE);
                Casilla73.setVisibility(View.VISIBLE);
                Casilla78.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla74.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla65.setVisibility(View.VISIBLE);
                Casilla75.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("6") && vNcompradores.equals("5")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generadá", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                C6.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla72.setVisibility(View.VISIBLE);
                Casilla73.setVisibility(View.VISIBLE);
                Casilla78.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla74.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla65.setVisibility(View.VISIBLE);
                Casilla75.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla56.setVisibility(View.VISIBLE);
                Casilla66.setVisibility(View.VISIBLE);
                Casilla76.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
            }
            else if(vNproveedores.equals("6") && vNcompradores.equals("6")){
                Toast.makeText(this, "Tabla de "+vNcompradores+" compradores y "+vNproveedores+" proveedores generadá", Toast.LENGTH_LONG).show();

                TablaCalculadora2X2.setVisibility(View.VISIBLE);
                Contenido.setVisibility(View.VISIBLE);
                C1.setVisibility(View.VISIBLE);
                C2.setVisibility(View.VISIBLE);
                C3.setVisibility(View.VISIBLE);
                C4.setVisibility(View.VISIBLE);
                C5.setVisibility(View.VISIBLE);
                C6.setVisibility(View.VISIBLE);
                Casilla22.setVisibility(View.VISIBLE);
                Casilla23.setVisibility(View.VISIBLE);
                Casilla32.setVisibility(View.VISIBLE);
                Casilla33.setVisibility(View.VISIBLE);
                Casilla28.setVisibility(View.VISIBLE);
                Casilla38.setVisibility(View.VISIBLE);
                Casilla82.setVisibility(View.VISIBLE);
                Casilla83.setVisibility(View.VISIBLE);
                Casilla42.setVisibility(View.VISIBLE);
                Casilla43.setVisibility(View.VISIBLE);
                Casilla48.setVisibility(View.VISIBLE);
                Casilla52.setVisibility(View.VISIBLE);
                Casilla53.setVisibility(View.VISIBLE);
                Casilla58.setVisibility(View.VISIBLE);
                Casilla62.setVisibility(View.VISIBLE);
                Casilla63.setVisibility(View.VISIBLE);
                Casilla68.setVisibility(View.VISIBLE);
                Casilla72.setVisibility(View.VISIBLE);
                Casilla73.setVisibility(View.VISIBLE);
                Casilla78.setVisibility(View.VISIBLE);
                Casilla24.setVisibility(View.VISIBLE);
                Casilla34.setVisibility(View.VISIBLE);
                Casilla44.setVisibility(View.VISIBLE);
                Casilla54.setVisibility(View.VISIBLE);
                Casilla64.setVisibility(View.VISIBLE);
                Casilla74.setVisibility(View.VISIBLE);
                Casilla25.setVisibility(View.VISIBLE);
                Casilla35.setVisibility(View.VISIBLE);
                Casilla45.setVisibility(View.VISIBLE);
                Casilla55.setVisibility(View.VISIBLE);
                Casilla65.setVisibility(View.VISIBLE);
                Casilla75.setVisibility(View.VISIBLE);
                Casilla26.setVisibility(View.VISIBLE);
                Casilla36.setVisibility(View.VISIBLE);
                Casilla46.setVisibility(View.VISIBLE);
                Casilla56.setVisibility(View.VISIBLE);
                Casilla66.setVisibility(View.VISIBLE);
                Casilla76.setVisibility(View.VISIBLE);
                Casilla27.setVisibility(View.VISIBLE);
                Casilla37.setVisibility(View.VISIBLE);
                Casilla47.setVisibility(View.VISIBLE);
                Casilla57.setVisibility(View.VISIBLE);
                Casilla67.setVisibility(View.VISIBLE);
                Casilla77.setVisibility(View.VISIBLE);
                Casilla84.setVisibility(View.VISIBLE);
                Casilla85.setVisibility(View.VISIBLE);
                Casilla86.setVisibility(View.VISIBLE);
                Casilla87.setVisibility(View.VISIBLE);
                P1.setVisibility(View.VISIBLE);
                P2.setVisibility(View.VISIBLE);
                P3.setVisibility(View.VISIBLE);
                P4.setVisibility(View.VISIBLE);
                P5.setVisibility(View.VISIBLE);
                P6.setVisibility(View.VISIBLE);
            }






        }



}


    public void deshabilitarTabla(){
    SolucionOptima.setVisibility(View.INVISIBLE);
    Casilla22.setEnabled(false);
    Casilla23.setEnabled(false);
    Casilla24.setEnabled(false);
    Casilla25.setEnabled(false);
    Casilla26.setEnabled(false);
    Casilla27.setEnabled(false);
    Casilla28.setEnabled(false);
    Casilla32.setEnabled(false);
    Casilla33.setEnabled(false);
    Casilla34.setEnabled(false);
    Casilla35.setEnabled(false);
    Casilla36.setEnabled(false);
    Casilla37.setEnabled(false);
    Casilla38.setEnabled(false);
    Casilla42.setEnabled(false);
    Casilla43.setEnabled(false);
    Casilla44.setEnabled(false);
    Casilla45.setEnabled(false);
    Casilla46.setEnabled(false);
    Casilla47.setEnabled(false);
    Casilla48.setEnabled(false);
    Casilla52.setEnabled(false);
    Casilla53.setEnabled(false);
    Casilla54.setEnabled(false);
    Casilla55.setEnabled(false);
    Casilla56.setEnabled(false);
    Casilla57.setEnabled(false);
    Casilla58.setEnabled(false);
    Casilla62.setEnabled(false);
    Casilla63.setEnabled(false);
    Casilla64.setEnabled(false);
    Casilla65.setEnabled(false);
    Casilla66.setEnabled(false);
    Casilla67.setEnabled(false);
    Casilla68.setEnabled(false);
    Casilla72.setEnabled(false);
    Casilla73.setEnabled(false);
    Casilla74.setEnabled(false);
    Casilla75.setEnabled(false);
    Casilla76.setEnabled(false);
    Casilla77.setEnabled(false);
    Casilla78.setEnabled(false);
    Casilla82.setEnabled(false);
    Casilla83.setEnabled(false);
    Casilla84.setEnabled(false);
    Casilla85.setEnabled(false);
    Casilla86.setEnabled(false);
    Casilla87.setEnabled(false);
}

    private void Tabla3x4(){
        String vNproveedores = Nproveedores.getText().toString();
        String vNcompradores = Ncompradores.getText().toString();
        String casilla22 = Casilla22.getText().toString();
        String casilla23 = Casilla23.getText().toString();
        String casilla24 = Casilla24.getText().toString();
        String casilla25 = Casilla25.getText().toString();
        String casilla26 = Casilla26.getText().toString();
        String casilla27 = Casilla27.getText().toString();
        String casilla28 = Casilla28.getText().toString();
        String casilla32 = Casilla32.getText().toString();
        String casilla33 = Casilla33.getText().toString();
        String casilla34 = Casilla34.getText().toString();
        String casilla35 = Casilla35.getText().toString();
        String casilla36 = Casilla36.getText().toString();
        String casilla37 = Casilla37.getText().toString();
        String casilla38 = Casilla38.getText().toString();
        String casilla42 = Casilla42.getText().toString();
        String casilla43 = Casilla43.getText().toString();
        String casilla44 = Casilla44.getText().toString();
        String casilla45 = Casilla45.getText().toString();
        String casilla46 = Casilla46.getText().toString();
        String casilla47 = Casilla47.getText().toString();
        String casilla48 = Casilla48.getText().toString();
        String casilla52 = Casilla52.getText().toString();
        String casilla53 = Casilla53.getText().toString();
        String casilla54 = Casilla54.getText().toString();
        String casilla55 = Casilla55.getText().toString();
        String casilla56 = Casilla56.getText().toString();
        String casilla57 = Casilla57.getText().toString();
        String casilla58 = Casilla58.getText().toString();
        String casilla62 = Casilla62.getText().toString();
        String casilla63 = Casilla63.getText().toString();
        String casilla64 = Casilla64.getText().toString();
        String casilla65 = Casilla65.getText().toString();
        String casilla66 = Casilla66.getText().toString();
        String casilla67 = Casilla67.getText().toString();
        String casilla68 = Casilla68.getText().toString();
        String casilla72 = Casilla72.getText().toString();
        String casilla73 = Casilla73.getText().toString();
        String casilla74 = Casilla74.getText().toString();
        String casilla75 = Casilla75.getText().toString();
        String casilla76 = Casilla76.getText().toString();
        String casilla77 = Casilla77.getText().toString();
        String casilla78 = Casilla78.getText().toString();
        String casilla82 = Casilla82.getText().toString();
        String casilla83 = Casilla83.getText().toString();
        String casilla84 = Casilla84.getText().toString();
        String casilla85 = Casilla85.getText().toString();
        String casilla86 = Casilla86.getText().toString();
        String casilla87 = Casilla87.getText().toString();
        deshabilitarTabla();
        MensajeResultado.setVisibility(View.VISIBLE);
        TablaCalculadoraSL.setVisibility(View.VISIBLE);
        C1SL.setVisibility(View.VISIBLE);
        C2SL.setVisibility(View.VISIBLE);
        C3SL.setVisibility(View.VISIBLE);
        P1SL.setVisibility(View.VISIBLE);
        P2SL.setVisibility(View.VISIBLE);
        P3SL.setVisibility(View.VISIBLE);
        P4SL.setVisibility(View.VISIBLE);
        Casilla22SL.setVisibility(View.VISIBLE);
        Casilla23SL.setVisibility(View.VISIBLE);
        Casilla24SL.setVisibility(View.VISIBLE);
        Casilla25SL.setVisibility(View.VISIBLE);
        Casilla32SL.setVisibility(View.VISIBLE);
        Casilla33SL.setVisibility(View.VISIBLE);
        Casilla34SL.setVisibility(View.VISIBLE);
        Casilla35SL.setVisibility(View.VISIBLE);
        Casilla42SL.setVisibility(View.VISIBLE);
        Casilla43SL.setVisibility(View.VISIBLE);
        Casilla44SL.setVisibility(View.VISIBLE);
        Casilla45SL.setVisibility(View.VISIBLE);
        Casilla82SL.setVisibility(View.VISIBLE);
        Casilla83SL.setVisibility(View.VISIBLE);
        Casilla84SL.setVisibility(View.VISIBLE);
        Casilla85SL.setVisibility(View.VISIBLE);
        Casilla28SL.setVisibility(View.VISIBLE);
        Casilla38SL.setVisibility(View.VISIBLE);
        Casilla48SL.setVisibility(View.VISIBLE);
        int casilla28num = Integer.parseInt(casilla28);
        int casilla38num = Integer.parseInt(casilla38);
        int casilla48num = Integer.parseInt(casilla48);
        int suma_demanda = casilla28num + casilla38num + casilla48num;

        int casilla82num = Integer.parseInt(casilla82);
        int casilla83num = Integer.parseInt(casilla83);
        int casilla84num = Integer.parseInt(casilla84);
        int casilla85num = Integer.parseInt(casilla85);
        int suma_oferta = casilla82num + casilla83num + casilla84num + casilla85num;
        Casilla28SL.setText(casilla28);
        Casilla38SL.setText(casilla38);
        Casilla48SL.setText(casilla48);
        Casilla82SL.setText(casilla82);
        Casilla83SL.setText(casilla83);
        Casilla84SL.setText(casilla84);
        Casilla85SL.setText(casilla85);
        if(suma_demanda == suma_oferta){
            if(casilla28num <= casilla82num){
                Casilla22SL.setText("" + casilla28num);
                Casilla23SL.setText("0");
                Casilla24SL.setText("0");
                Casilla25SL.setText("0");
                int casilla82numresiduo = casilla82num-casilla28num;
                if(casilla38num <= casilla82numresiduo){
                    Casilla32SL.setText("" + casilla38num);
                    Casilla33SL.setText("0");
                    Casilla34SL.setText("0");
                    Casilla35SL.setText("0");
                    int casilla82numresiduo2 = casilla82numresiduo-casilla38num;
                    if(casilla48num <= casilla82numresiduo2){
                        Casilla42SL.setText("" + casilla48num);
                        Casilla43SL.setText("0");
                        Casilla44SL.setText("0");
                        Casilla45SL.setText("0");
                        int casilla82numresiduo3 = casilla82numresiduo2-casilla48num;
                        //final de la rama
                    }else if(casilla48num >= casilla82numresiduo2){
                        Casilla42SL.setText("" + casilla82numresiduo2);
                        int casilla48numresiduo =casilla48num-casilla82numresiduo2;
                        if(casilla48numresiduo <= casilla83num){
                            Casilla43SL.setText("" + casilla48numresiduo);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla83numresiduo = casilla83num-casilla48numresiduo;
                            //final de la rama...
                        }else if(casilla48numresiduo >= casilla83num){
                            Casilla43SL.setText("" + casilla83num);
                            int casilla48numresiduo2 = casilla48numresiduo-casilla83num;
                            if(casilla48numresiduo2 <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo2);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo =casilla84num-casilla48numresiduo2;
                                //final de la rama...
                            }else if(casilla48numresiduo2 >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo3 = casilla48numresiduo2-casilla84num;
                                if(casilla48numresiduo3 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo3);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo3;
                                    //final de la rama...
                                }else if(casilla48numresiduo3 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo4 = casilla48numresiduo3-casilla85num;
                                    //final de la rama...
                                }
                            }
                        }
                    }
                }else if(casilla38num >= casilla82numresiduo){
                    Casilla32SL.setText("" + casilla82numresiduo);
                    Casilla42SL.setText("0");
                    int casilla38numresiduo = casilla38num-casilla82numresiduo;
                    if(casilla38numresiduo <= casilla83num){
                        Casilla33SL.setText("" + casilla38numresiduo);
                        Casilla34SL.setText("0");
                        Casilla35SL.setText("0");
                        int casilla83numresiduo = casilla83num-casilla38numresiduo;
                        if(casilla83numresiduo <= casilla48num){
                            Casilla43SL.setText("" + casilla83numresiduo);
                            int casilla48numresiduo = casilla48num-casilla83numresiduo;
                            if(casilla48numresiduo <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo;
                                //final de la rama...
                            }else if(casilla48numresiduo >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo2 = casilla48numresiduo-casilla84num;
                                if(casilla48numresiduo2 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo2);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo2;
                                    //final de la rama...
                                }else if(casilla48numresiduo2 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo3 = casilla48numresiduo2-casilla85num;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla83numresiduo >= casilla48num){
                            Casilla43SL.setText("" + casilla48num);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla83numresiduo2 = casilla83numresiduo-casilla48num;
                            //final de la rama...
                        }
                    }else if(casilla38numresiduo >= casilla83num){
                        Casilla33SL.setText("" + casilla83num);
                        Casilla43SL.setText("0");
                        int casilla38numresiduo2 = casilla38numresiduo-casilla83num;
                        if(casilla38numresiduo2 <= casilla84num){
                            Casilla34SL.setText("" + casilla38numresiduo2);
                            Casilla35SL.setText("0");
                            int casilla84numresiduo = casilla84num-casilla38numresiduo2;
                            if(casilla84numresiduo <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo);
                                int casilla48numresiduo = casilla48num-casilla84numresiduo;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    //final de la rama...
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    //final de la rama...
                                }
                            }else if(casilla84numresiduo >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo2 = casilla84numresiduo-casilla48num;
                                //final de la rama...
                            }
                        }else if(casilla38numresiduo2 >= casilla84num){
                            Casilla34SL.setText("" + casilla84num);
                            Casilla44SL.setText("0");
                            int casilla38numresiduo3 = casilla38numresiduo2-casilla84num;
                            if(casilla38numresiduo3 <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo3);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo3;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo;
                                    //final de la rama...
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    //final de la rama...
                                }
                            }else if(casilla38numresiduo3 >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo4 = casilla38numresiduo3-casilla85num;
                                //final de la rama...
                            }
                        }
                    }
                }
            }
            else if(casilla28num >= casilla82num){
                Casilla22SL.setText("" + casilla82num);
                Casilla32SL.setText("0");
                Casilla42SL.setText("0");
                int casilla28numresiduo = casilla28num-casilla82num;
                if(casilla28numresiduo >= casilla83num){
                    Casilla23SL.setText("" + casilla83num);
                    Casilla33SL.setText("0");
                    Casilla43SL.setText("0");
                    int casilla28numresiduo2 = casilla28numresiduo-casilla83num;
                    if(casilla28numresiduo2 <= casilla84num){
                        Casilla24SL.setText("" + casilla28numresiduo2);
                        Casilla25SL.setText("0");
                        int casilla84numresiduo = casilla84num-casilla28numresiduo2;
                        if(casilla84numresiduo <= casilla38num){
                            Casilla34SL.setText("" + casilla84numresiduo);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla38numresiduo = casilla38num-casilla84numresiduo;
                            if(casilla38numresiduo <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo;
                                    //final de la rama...
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    //final de la rama...
                                }
                            }else if(casilla38numresiduo >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo2 = casilla38numresiduo-casilla85num;
                                //final de la rama...
                            }
                        }else if(casilla84numresiduo >= casilla38num){
                            Casilla34SL.setText("" + casilla38num);
                            Casilla35SL.setText("0");
                            int casilla84numresiduo2 = casilla84numresiduo-casilla38num;
                            if(casilla84numresiduo2 <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo2);
                                int casilla48numresiduo = casilla48num-casilla84numresiduo2;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    //final de la rama...
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    //final de la rama...
                                }
                            }else if(casilla84numresiduo2 >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo3 = casilla84numresiduo2-casilla48num;
                                //final de la rama...
                            }
                        }
                    }else if(casilla28numresiduo2 >= casilla84num){
                        Casilla24SL.setText("" + casilla84num);
                        Casilla34SL.setText("0");
                        Casilla44SL.setText("0");
                        int casilla28numresiduo3 = casilla28numresiduo2-casilla84num;
                        if(casilla28numresiduo3 <= casilla85num){
                            Casilla25SL.setText("" + casilla28numresiduo3);
                            int casilla85numresiduo = casilla85num-casilla28numresiduo3;
                            if(casilla85numresiduo <= casilla38num){
                                Casilla35SL.setText("" + casilla85numresiduo);
                                Casilla44SL.setText("0");
                                int casilla38numresiduo = casilla38num-casilla85numresiduo;
                                //final de la rama...
                            }else if(casilla85numresiduo >= casilla38num){
                                Casilla35SL.setText("" + casilla38num);
                                int casilla85numresiduo2 = casilla85numresiduo-casilla38num;
                                if(casilla85numresiduo2 <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo2);
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo2;
                                    //final de la rama...
                                }else if(casilla85numresiduo2 >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int sobrante_demanda_int_residuo3 = casilla85numresiduo2-casilla48num;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla28numresiduo3 >= casilla85num){
                            Casilla25SL.setText("" + casilla85num);
                            Casilla35SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla28numresiduo4 = casilla28numresiduo3-casilla85num;
                            //final de la rama...
                        }
                    }
                }else if(casilla28numresiduo <= casilla83num){
                    Casilla23SL.setText("" + casilla28numresiduo);
                    Casilla24SL.setText("0");
                    int casilla83numresiduo = casilla83num-casilla28numresiduo;
                    if(casilla83numresiduo <= casilla38num){
                        Casilla33SL.setText("" + casilla83numresiduo);
                        Casilla43SL.setText("0");
                        int casilla38numresiduo = casilla38num-casilla83numresiduo;
                        if(casilla38numresiduo <= casilla84num){
                            Casilla34SL.setText("" + casilla38numresiduo);
                            int casilla84numresiduo = casilla84num-casilla38numresiduo;
                            if(casilla84numresiduo <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo);
                                int casilla48numresiduo = casilla48num-casilla84numresiduo;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    //final de la rama...
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    //final de la rama...
                                }
                            }else if(casilla84numresiduo >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo2 = casilla84numresiduo-casilla48num;
                                //final de la rama...
                            }
                        }else if(casilla38numresiduo >= casilla84num){
                            Casilla34SL.setText("" + casilla84num);
                            Casilla44SL.setText("0");
                            int casilla38numresiduo2 = casilla38numresiduo-casilla84num;
                            if(casilla38numresiduo2 <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo2);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo2;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    int casilla48numreisduo = casilla48num-casilla85numresiduo;
                                    //final de la rama...
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int sobrante_demanda_int_residuo2 = casilla85numresiduo-casilla48num;
                                    //final de la rama...
                                }
                            }else if(casilla38numresiduo2 >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo3 = casilla38numresiduo2-casilla85num;
                                //final de la rama...
                            }
                        }
                    }else if(casilla83numresiduo >= casilla38num){
                        Casilla33SL.setText("" + casilla38num);
                        Casilla34SL.setText("0");
                        int casilla83numresiduo2 = casilla83numresiduo-casilla38num;
                        if(casilla83numresiduo2 <= casilla48num){
                            Casilla43SL.setText("" + casilla83numresiduo2);
                            int casilla48numresiduo = casilla48num-casilla83numresiduo2;
                            if(casilla48numresiduo <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo;
                                //final de la rama...
                            }else if(casilla48numresiduo >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo2 =casilla48numresiduo-casilla84num;
                                if(casilla48numresiduo2 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo2);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo2;
                                    //final de la rama...
                                }else if(casilla48numresiduo2 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo3 = casilla48numresiduo2-casilla85num;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla83numresiduo2 >= casilla48num){
                            Casilla43SL.setText("" + casilla48num);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla83numresiduo3 = casilla83numresiduo2-casilla48num;
                            //final de la rama
                        }
                    }
                }
            }


        }
        else if(suma_demanda > suma_oferta){
            int sobrante_demanda_int = suma_demanda-suma_oferta;
            int resultado = sobrante_demanda_int + suma_oferta;
            String sobrante_demanda = String.valueOf(sobrante_demanda_int);
            P5SL.setVisibility(View.VISIBLE);
            Casilla26SL.setVisibility(View.VISIBLE);
            Casilla36SL.setVisibility(View.VISIBLE);
            Casilla46SL.setVisibility(View.VISIBLE);
            Casilla86SL.setVisibility(View.VISIBLE);
            Casilla86SL.setText(sobrante_demanda);
            ResultadoSL.setText(" "+ resultado + " / " + suma_demanda);
            MensajeResultado.setText("la suma de las ofertas es menor a la suma de las demandas, en este caso es necesario añadir una columna ficticia a la tabla, que solo se tendrá en cuenta " +
                    " para la asignación más optima.");
            if(casilla28num <= casilla82num){
                Casilla22SL.setText("" + casilla28num);
                Casilla23SL.setText("0");
                Casilla24SL.setText("0");
                Casilla25SL.setText("0");
                Casilla26SL.setText("0");
                int casilla82numresiduo = casilla82num-casilla28num;
                if(casilla38num <= casilla82numresiduo){
                    Casilla32SL.setText("" + casilla38num);
                    Casilla33SL.setText("0");
                    Casilla34SL.setText("0");
                    Casilla35SL.setText("0");
                    Casilla36SL.setText("0");
                    int casilla82numresiduo2 = casilla82numresiduo-casilla38num;
                    if(casilla48num <= casilla82numresiduo2){
                        Casilla42SL.setText("" + casilla48num);
                        Casilla43SL.setText("0");
                        Casilla44SL.setText("0");
                        Casilla45SL.setText("0");
                        Casilla46SL.setText("0");
                        int casilla82numresiduo3 = casilla82numresiduo2-casilla48num;
                        //final de la rama
                    }else if(casilla48num >= casilla82numresiduo2){
                        Casilla42SL.setText("" + casilla82numresiduo2);
                        int casilla48numresiduo =casilla48num-casilla82numresiduo2;
                        if(casilla48numresiduo <= casilla83num){
                            Casilla43SL.setText("" + casilla48numresiduo);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            Casilla46SL.setText("0");
                            int casilla83numresiduo = casilla83num-casilla48numresiduo;
                            //final de la rama...
                        }else if(casilla48numresiduo >= casilla83num){
                            Casilla43SL.setText("" + casilla83num);
                            int casilla48numresiduo2 = casilla48numresiduo-casilla83num;
                            if(casilla48numresiduo2 <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo2);
                                Casilla45SL.setText("0");
                                Casilla46SL.setText("0");
                                int casilla84numresiduo =casilla84num-casilla48numresiduo2;
                                //final de la rama...
                            }else if(casilla48numresiduo2 >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo3 = casilla48numresiduo2-casilla84num;
                                if(casilla48numresiduo3 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo3);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo3;
                                }else if(casilla48numresiduo3 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo4 = casilla48numresiduo3-casilla85num;
                                    if(casilla48numresiduo4 <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo4);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo4;
                                        //final de la rama...
                                    }else if(casilla48numresiduo4 >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo5 = casilla48numresiduo4-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        }
                    }
                }else if(casilla38num >= casilla82numresiduo){
                    Casilla32SL.setText("" + casilla82numresiduo);
                    Casilla42SL.setText("0");
                    int casilla38numresiduo = casilla38num-casilla82numresiduo;
                    if(casilla38numresiduo <= casilla83num){
                        Casilla33SL.setText("" + casilla38numresiduo);
                        Casilla34SL.setText("0");
                        Casilla35SL.setText("0");
                        Casilla36SL.setText("0");
                        int casilla83numresiduo = casilla83num-casilla38numresiduo;
                        if(casilla83numresiduo <= casilla48num){
                            Casilla43SL.setText("" + casilla83numresiduo);
                            int casilla48numresiduo = casilla48num-casilla83numresiduo;
                            if(casilla48numresiduo <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo);
                                Casilla45SL.setText("0");
                                Casilla46SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo;
                                //final de la rama...
                            }else if(casilla48numresiduo >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo2 = casilla48numresiduo-casilla84num;
                                if(casilla48numresiduo2 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo2);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo2;
                                    //final de la rama...
                                }else if(casilla48numresiduo2 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo3 = casilla48numresiduo2-casilla85num;
                                    if(casilla48numresiduo3 <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo3);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo3;
                                        //final de la rama...
                                    }else if(casilla48numresiduo3 >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo4 = casilla48numresiduo3-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        }else if(casilla83numresiduo >= casilla48num){
                            Casilla43SL.setText("" + casilla48num);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            Casilla46SL.setText("0");
                            int casilla83numresiduo2 = casilla83numresiduo-casilla48num;
                            //final de la rama...
                        }
                    }else if(casilla38numresiduo >= casilla83num){
                        Casilla33SL.setText("" + casilla83num);
                        Casilla43SL.setText("0");
                        int casilla38numresiduo2 = casilla38numresiduo-casilla83num;
                        if(casilla38numresiduo2 <= casilla84num){
                            Casilla34SL.setText("" + casilla38numresiduo2);
                            Casilla35SL.setText("0");
                            Casilla36SL.setText("0");
                            int casilla84numresiduo = casilla84num-casilla38numresiduo2;
                            if(casilla84numresiduo <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo);
                                int casilla48numresiduo = casilla48num-casilla84numresiduo;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    //final de la rama...
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    if(casilla48numresiduo2 <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo2);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo2;
                                        //final de la rama...
                                    }else if(casilla48numresiduo2 >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo3 = casilla48numresiduo2-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla84numresiduo >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                Casilla46SL.setText("0");
                                int casilla84numresiduo2 = casilla84numresiduo-casilla48num;
                                //final de la rama...
                            }
                        }else if(casilla38numresiduo2 >= casilla84num){
                            Casilla34SL.setText("" + casilla84num);
                            Casilla44SL.setText("0");
                            int casilla38numresiduo3 = casilla38numresiduo2-casilla84num;
                            if(casilla38numresiduo3 <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo3);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo3;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo;
                                    if(casilla48numresiduo <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo;
                                        //final de la rama...
                                    }else if(casilla48numresiduo >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo2 = casilla48numresiduo-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    //final de la rama...
                                }
                            }else if(casilla38numresiduo3 >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo4 = casilla38numresiduo3-casilla85num;
                                if(casilla38numresiduo4 <= sobrante_demanda_int){
                                    Casilla36SL.setText("" + casilla38numresiduo4);
                                    int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla38numresiduo4;
                                    if(sobrante_demanda_int_residuo <= casilla48num){
                                        Casilla46SL.setText("" + sobrante_demanda_int_residuo);
                                        int casilla48numresiduo = casilla48num-sobrante_demanda_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_demanda_int_residuo >= casilla48num){
                                        Casilla46SL.setText("" + casilla48num);
                                        int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo-casilla48num;
                                        //final de la rama...
                                    }
                                }else if(casilla38numresiduo4 >= sobrante_demanda_int){
                                    Casilla36SL.setText("" + sobrante_demanda_int);
                                    Casilla46SL.setText("0");
                                    int casilla38numresiduo5 = casilla38numresiduo4-sobrante_demanda_int;
                                    //final de la rama...
                                }
                            }
                        }
                    }
                }
            }
            else if(casilla28num >= casilla82num){
                Casilla22SL.setText("" + casilla82num);
                Casilla32SL.setText("0");
                Casilla42SL.setText("0");
                int casilla28numresiduo = casilla28num-casilla82num;
                if(casilla28numresiduo >= casilla83num){
                    Casilla23SL.setText("" + casilla83num);
                    Casilla33SL.setText("0");
                    Casilla43SL.setText("0");
                    int casilla28numresiduo2 = casilla28numresiduo-casilla83num;
                    if(casilla28numresiduo2 <= casilla84num){
                        Casilla24SL.setText("" + casilla28numresiduo2);
                        Casilla25SL.setText("0");
                        Casilla26SL.setText("0");
                        int casilla84numresiduo = casilla84num-casilla28numresiduo2;
                        if(casilla84numresiduo <= casilla38num){
                            Casilla34SL.setText("" + casilla84numresiduo);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla38numresiduo = casilla38num-casilla84numresiduo;
                            if(casilla38numresiduo <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo;
                                    if(casilla48numresiduo <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo;
                                        //final de la rama...
                                    }else if(casilla48numresiduo >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo2  = casilla48numresiduo-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    //final de la rama...
                                }
                            }else if(casilla38numresiduo >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo2 = casilla38numresiduo-casilla85num;
                                if(casilla38numresiduo2 <= sobrante_demanda_int){
                                    Casilla36SL.setText("" + casilla38numresiduo2);
                                    int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla38numresiduo2;
                                    if(sobrante_demanda_int_residuo <= casilla48num){
                                        Casilla46SL.setText("" + sobrante_demanda_int_residuo);
                                        int casilla48numresiduo = casilla48num-sobrante_demanda_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_demanda_int_residuo >= casilla48num){
                                        Casilla46SL.setText("" + casilla48num);
                                        int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo-casilla48num;
                                        //final de la rama...
                                    }
                                }else if(casilla38numresiduo2 >= sobrante_demanda_int){
                                    Casilla36SL.setText("" + sobrante_demanda_int);
                                    Casilla46SL.setText("0");
                                    int casilla38numresiduo3 = casilla38numresiduo2-sobrante_demanda_int;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla84numresiduo >= casilla38num){
                            Casilla34SL.setText("" + casilla38num);
                            Casilla35SL.setText("0");
                            Casilla36SL.setText("0");
                            int casilla84numresiduo2 = casilla84numresiduo-casilla38num;
                            if(casilla84numresiduo2 <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo2);
                                int casilla48numresiduo = casilla48num-casilla84numresiduo2;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    //final de la rama...
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    if(casilla48numresiduo2 <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo2);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo2;
                                        //final de la rama...
                                    }else if(casilla48numresiduo2 >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo3 = casilla48numresiduo2-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla84numresiduo2 >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo3 = casilla84numresiduo2-casilla48num;
                                //final de la rama...
                            }
                        }
                    }else if(casilla28numresiduo2 >= casilla84num){
                        Casilla24SL.setText("" + casilla84num);
                        Casilla34SL.setText("0");
                        Casilla44SL.setText("0");
                        int casilla28numresiduo3 = casilla28numresiduo2-casilla84num;
                        if(casilla28numresiduo3 <= casilla85num){
                            Casilla25SL.setText("" + casilla28numresiduo3);
                            Casilla26SL.setText("0");
                            int casilla85numresiduo = casilla85num-casilla28numresiduo3;
                            if(casilla85numresiduo <= casilla38num){
                                Casilla35SL.setText("" + casilla85numresiduo);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo = casilla38num-casilla85numresiduo;
                                if(casilla38numresiduo <= sobrante_demanda_int){
                                    Casilla36SL.setText("" + casilla38numresiduo);
                                    int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla38numresiduo;
                                    if(sobrante_demanda_int_residuo <= casilla48num){
                                        Casilla46SL.setText("" + sobrante_demanda_int_residuo);
                                        int casilla48numresiduo = casilla48num-sobrante_demanda_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_demanda_int_residuo >= casilla48num){
                                        Casilla46SL.setText("" + casilla48num);
                                        int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo-casilla48num;
                                        //final de la rama...
                                    }
                                }else if(casilla38numresiduo >= sobrante_demanda_int){
                                    Casilla36SL.setText("" + sobrante_demanda_int);
                                    Casilla46SL.setText("0");
                                    int casilla38numresiduo2 = casilla38numresiduo-sobrante_demanda_int;
                                    //final de la rama...
                                }
                            }else if(casilla85numresiduo >= casilla38num){
                                Casilla35SL.setText("" + casilla38num);
                                Casilla36SL.setText("0");
                                int casilla85numresiduo2 = casilla85numresiduo-casilla38num;
                                if(casilla85numresiduo2 <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo2);
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo2;
                                    if(casilla48numresiduo <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo;
                                        //final de la rama...
                                    }else if(casilla48numresiduo >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo2 = casilla48numresiduo-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }else if(casilla85numresiduo2 >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo3 = casilla85numresiduo2-casilla48num;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla28numresiduo3 >= casilla85num){
                            Casilla25SL.setText("" + casilla85num);
                            Casilla35SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla28numresiduo4 = casilla28numresiduo3-casilla85num;
                            //final de la rama...
                        }
                    }
                }else if(casilla28numresiduo <= casilla83num){
                    Casilla23SL.setText("" + casilla28numresiduo);
                    Casilla24SL.setText("0");
                    Casilla25SL.setText("0");
                    Casilla26SL.setText("0");
                    int casilla83numresiduo = casilla83num-casilla28numresiduo;
                    if(casilla83numresiduo <= casilla38num){
                        Casilla33SL.setText("" + casilla83numresiduo);
                        Casilla43SL.setText("0");
                        int casilla38numresiduo = casilla38num-casilla83numresiduo;
                        if(casilla38numresiduo <= casilla84num){
                            Casilla34SL.setText("" + casilla38numresiduo);
                            Casilla35SL.setText("0");
                            Casilla36SL.setText("0");
                            int casilla84numresiduo = casilla84num-casilla38numresiduo;
                            if(casilla84numresiduo <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo);
                                int casilla48numresiduo = casilla48num-casilla84numresiduo;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    //final de la rama...
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    if(casilla48numresiduo2 <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo2);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo2;
                                        //final de la rama...
                                    }else if(casilla48numresiduo2 >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo3 = casilla48numresiduo2-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla84numresiduo >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                Casilla46SL.setText("0");
                                int casilla84numresiduo2 = casilla84numresiduo-casilla48num;
                                //final de la rama...
                            }
                        }else if(casilla38numresiduo >= casilla84num){
                            Casilla34SL.setText("" + casilla84num);
                            Casilla44SL.setText("0");
                            int casilla38numresiduo2 = casilla38numresiduo-casilla84num;
                            if(casilla38numresiduo2 <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo2);
                                Casilla36SL.setText("0");
                                int casilla85numresiduo = casilla85num-casilla38numresiduo2;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    int casilla48numreisduo = casilla48num-casilla85numresiduo;
                                    if(casilla48numreisduo <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numreisduo);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numreisduo;
                                        //final de la rama...
                                    }else if(casilla48numreisduo >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numreisduo2 = casilla48numreisduo-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    //final de la rama...
                                }
                            }else if(casilla38numresiduo2 >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                int casilla38numresiduo3 = casilla38numresiduo2-casilla85num;
                                if(casilla38numresiduo3 <= sobrante_demanda_int){
                                    Casilla36SL.setText("" + casilla38numresiduo3);
                                    int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla38numresiduo3;
                                    if(sobrante_demanda_int_residuo <= casilla48num){
                                        Casilla46SL.setText("" + sobrante_demanda_int_residuo);
                                        int casilla48numresiduo = casilla48num-sobrante_demanda_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_demanda_int_residuo >= casilla48num){
                                        Casilla46SL.setText("" + casilla48num);
                                        int sobrante_demanda_int_residuo2 = sobrante_demanda_int_residuo-casilla48num;
                                        //final de la rama...
                                    }
                                }else if(casilla38numresiduo3 >= sobrante_demanda_int){
                                    Casilla36SL.setText("" + sobrante_demanda_int);
                                    Casilla46SL.setText("0");
                                    int casilla38numresiduo4 = casilla38numresiduo3-sobrante_demanda_int;
                                    //final de la rama...
                                }
                            }
                        }
                    }else if(casilla83numresiduo >= casilla38num){
                        Casilla33SL.setText("" + casilla38num);
                        Casilla34SL.setText("0");
                        Casilla35SL.setText("0");
                        Casilla36SL.setText("0");
                        int casilla83numresiduo2 = casilla83numresiduo-casilla38num;
                        if(casilla83numresiduo2 <= casilla48num){
                            Casilla43SL.setText("" + casilla83numresiduo2);
                            int casilla48numresiduo = casilla48num-casilla83numresiduo2;
                            if(casilla48numresiduo <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo);
                                Casilla45SL.setText("0");
                                Casilla46SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo;
                                //final de la rama...
                            }else if(casilla48numresiduo >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo2 =casilla48numresiduo-casilla84num;
                                if(casilla48numresiduo2 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo2);
                                    Casilla46SL.setText("0");
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo2;
                                    //final de la rama...
                                }else if(casilla48numresiduo2 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo3 = casilla48numresiduo2-casilla85num;
                                    if(casilla48numresiduo3 <= sobrante_demanda_int){
                                        Casilla46SL.setText("" + casilla48numresiduo3);
                                        int sobrante_demanda_int_residuo = sobrante_demanda_int-casilla48numresiduo3;
                                        //final de la rama...
                                    }else if(casilla48numresiduo3 >= sobrante_demanda_int){
                                        Casilla46SL.setText("" + sobrante_demanda_int);
                                        int casilla48numresiduo4 = casilla48numresiduo3-sobrante_demanda_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        }else if(casilla83numresiduo2 >= casilla48num){
                            Casilla43SL.setText("" + casilla48num);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            Casilla46SL.setText("0");
                            int casilla83numresiduo3 = casilla83numresiduo2-casilla48num;
                            //final de la rama
                        }
                    }
                }
            }

        }
        else if(suma_demanda < suma_oferta){
            int sobrante_oferta_int = suma_oferta-suma_demanda;
            int resultado = sobrante_oferta_int + suma_demanda;
            String sobrante_oferta = String.valueOf(sobrante_oferta_int);
            C4SL.setVisibility(View.VISIBLE);
            Casilla52SL.setVisibility(View.VISIBLE);
            Casilla53SL.setVisibility(View.VISIBLE);
            Casilla54SL.setVisibility(View.VISIBLE);
            Casilla58SL.setVisibility(View.VISIBLE);
            Casilla52SL.setText(casilla52);
            Casilla53SL.setText(casilla53);
            Casilla54SL.setText(casilla54);
            Casilla58SL.setText(sobrante_oferta);
            ResultadoSL.setText(" "+ suma_oferta + " / " + resultado);
            MensajeResultado.setText("La suma de las demandas es menor a la suma de las ofertas, en este caso es necesario" +
                    " añadir una fila ficticia que solo se tendrá en cuenta para la asignación más optima.");
            if(casilla28num <= casilla82num){
                Casilla22SL.setText("" + casilla28num);
                Casilla23SL.setText("0");
                Casilla24SL.setText("0");
                Casilla25SL.setText("0");
                int casilla82numresiduo = casilla82num-casilla28num;
                if(casilla38num <= casilla82numresiduo){
                    Casilla32SL.setText("" + casilla38num);
                    Casilla33SL.setText("0");
                    Casilla34SL.setText("0");
                    Casilla35SL.setText("0");
                    int casilla82numresiduo2 = casilla82numresiduo-casilla38num;
                    if(casilla48num <= casilla82numresiduo2){
                        Casilla42SL.setText("" + casilla48num);
                        Casilla43SL.setText("0");
                        Casilla44SL.setText("0");
                        Casilla45SL.setText("0");
                        int casilla82numresiduo3 = casilla82numresiduo2-casilla48num;
                        if(casilla82numresiduo3 <= sobrante_oferta_int){
                            Casilla52SL.setText("" + casilla82numresiduo3);
                            int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla82numresiduo3;
                            if(sobrante_oferta_int_residuo <= casilla83num){
                                Casilla53SL.setText("" + sobrante_oferta_int_residuo);
                                Casilla54SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla83numresiduo = casilla83num-sobrante_oferta_int_residuo;
                                //final de la rama...
                            }else if(sobrante_oferta_int_residuo >= casilla83num){
                                Casilla53SL.setText("" + casilla83num);
                                int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla83num;
                                if(sobrante_oferta_int_residuo2 <= casilla84num){
                                    Casilla54SL.setText("" + sobrante_oferta_int_residuo2);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo = casilla84num-sobrante_oferta_int_residuo2;
                                    //final de la rama...
                                }else if(sobrante_oferta_int_residuo2 >= casilla84num){
                                    Casilla54SL.setText("" + casilla84num);
                                    int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2-casilla84num;
                                    if(sobrante_oferta_int_residuo3 <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo3);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo3;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo3 >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo4 = sobrante_oferta_int_residuo3-casilla85num;
                                        //final de la rama...
                                    }
                                }
                            }
                        }else if(casilla82numresiduo3 >= sobrante_oferta_int){
                            Casilla52SL.setText("" + casilla82numresiduo3);
                            Casilla53SL.setText("0");
                            Casilla54SL.setText("0");
                            Casilla55SL.setText("0");
                            int casilla82numresiduo4 = casilla82numresiduo3-sobrante_oferta_int;
                            //final de la rama...
                        }
                    }else if(casilla48num >= casilla82numresiduo2){
                        Casilla42SL.setText("" + casilla82numresiduo2);
                        int casilla48numresiduo =casilla48num-casilla82numresiduo2;
                        if(casilla48numresiduo <= casilla83num){
                            Casilla43SL.setText("" + casilla48numresiduo);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla83numresiduo = casilla83num-casilla48numresiduo;
                            if(casilla83numresiduo <= sobrante_oferta_int){
                                Casilla53SL.setText("" + casilla83numresiduo);
                                int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla83numresiduo;
                                if(sobrante_oferta_int_residuo <= casilla84num){
                                    Casilla54SL.setText("" + sobrante_oferta_int_residuo);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo = casilla84num-sobrante_oferta_int_residuo;
                                    //final de la rama...
                                }else if(sobrante_oferta_int_residuo >= casilla84num){
                                    Casilla54SL.setText("" + casilla84num);
                                    int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla84num;
                                    if(sobrante_oferta_int_residuo2 <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo2);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo2;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo2 >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2-casilla85num;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla83numresiduo >= sobrante_oferta_int){
                                Casilla53SL.setText("" + sobrante_oferta_int);
                                Casilla54SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla83numresiduo2 = casilla83numresiduo-sobrante_oferta_int;
                                //final de la rama...
                            }
                        }else if(casilla48numresiduo >= casilla83num){
                            Casilla43SL.setText("" + casilla83num);
                            int casilla48numresiduo2 = casilla48numresiduo-casilla83num;
                            if(casilla48numresiduo2 <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo2);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo2;
                                if(casilla84numresiduo <= sobrante_oferta_int){
                                    Casilla54SL.setText("" + casilla84numresiduo);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla84numresiduo;
                                    if(sobrante_oferta_int_residuo <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla85num;
                                        //final de la rama...
                                    }
                                }else if(casilla84numresiduo >= sobrante_oferta_int){
                                    Casilla54SL.setText("" + sobrante_oferta_int);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo2 = casilla84numresiduo-sobrante_oferta_int;
                                    //final de la rama...
                                }
                            }else if(casilla48numresiduo2 >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                int casilla48numresiduo3 = casilla48numresiduo2-casilla84num;
                                if(casilla48numresiduo3 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo3);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo3;
                                    if(casilla85numresiduo <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo;
                                        //final de la rama...
                                    }else if(casilla85numresiduo >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo2 = casilla85numresiduo-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }else if(casilla48numresiduo3 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo4 = casilla48numresiduo3-casilla85num;
                                    //final de la rama...
                                }
                            }
                        }
                    }
                }else if(casilla38num >= casilla82numresiduo){
                    Casilla32SL.setText("" + casilla82numresiduo);
                    Casilla42SL.setText("0");
                    Casilla52SL.setText("0");
                    int casilla38numresiduo = casilla38num-casilla82numresiduo;
                    if(casilla38numresiduo <= casilla83num){
                        Casilla33SL.setText("" + casilla38numresiduo);
                        Casilla34SL.setText("0");
                        Casilla35SL.setText("0");
                        int casilla83numresiduo = casilla83num-casilla38numresiduo;
                        if(casilla83numresiduo <= casilla48num){
                            Casilla43SL.setText("" + casilla83numresiduo);
                            Casilla53SL.setText("0");
                            int casilla48numresiduo = casilla48num-casilla83numresiduo;
                            if(casilla48numresiduo <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo;
                                if(casilla84numresiduo <= sobrante_oferta_int){
                                    Casilla54SL.setText("" + casilla84numresiduo);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla84numresiduo;
                                    if(sobrante_oferta_int_residuo <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla85num;
                                        //final de la rama...
                                    }
                                }else if(casilla84numresiduo >= sobrante_oferta_int){
                                    Casilla54SL.setText("" + sobrante_oferta_int);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo2 = casilla84numresiduo-sobrante_oferta_int;
                                    //final de la rama...
                                }
                            }else if(casilla48numresiduo >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                Casilla54SL.setText("0");
                                int casilla48numresiduo2 = casilla48numresiduo-casilla84num;
                                if(casilla48numresiduo2 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo2);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo2;
                                    if(casilla85numresiduo <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo;
                                        //final de la rama...
                                    }else if(casilla85numresiduo >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo2 = casilla85numresiduo-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }else if(casilla48numresiduo2 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo3 = casilla48numresiduo2-casilla85num;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla83numresiduo >= casilla48num){
                            Casilla43SL.setText("" + casilla48num);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla83numresiduo2 = casilla83numresiduo-casilla48num;
                            //final de la rama...
                        }
                    }else if(casilla38numresiduo >= casilla83num){
                        Casilla33SL.setText("" + casilla83num);
                        Casilla43SL.setText("0");
                        Casilla53SL.setText("0");
                        int casilla38numresiduo2 = casilla38numresiduo-casilla83num;
                        if(casilla38numresiduo2 <= casilla84num){
                            Casilla34SL.setText("" + casilla38numresiduo2);
                            Casilla35SL.setText("0");
                            int casilla84numresiduo = casilla84num-casilla38numresiduo2;
                            if(casilla84numresiduo <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo);
                                Casilla54SL.setText("0");
                                int casilla48numresiduo = casilla48num-casilla84numresiduo;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    if(casilla85numresiduo <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo;
                                        //final de la rama...
                                    }else if(casilla85numresiduo >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo2 = casilla85numresiduo-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    //final de la rama...
                                }
                            }else if(casilla84numresiduo >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo2 = casilla84numresiduo-casilla48num;
                                //final de la rama...
                            }
                        }else if(casilla38numresiduo2 >= casilla84num){
                            Casilla34SL.setText("" + casilla84num);
                            Casilla44SL.setText("0");
                            int casilla38numresiduo3 = casilla38numresiduo2-casilla84num;
                            if(casilla38numresiduo3 <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo3);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo3;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo;
                                   //final de la rama...
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    if(casilla85numresiduo2 <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo2);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo2;
                                        //final de la rama...
                                    }else if(casilla85numresiduo2 >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo3 = casilla85numresiduo2-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla38numresiduo3 >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla38numresiduo4 = casilla38numresiduo3-casilla85num;
                                //final de la rama...
                            }
                        }
                    }
                }
            }
            else if(casilla28num >= casilla82num){
                Casilla22SL.setText("" + casilla82num);
                Casilla32SL.setText("0");
                Casilla42SL.setText("0");
                Casilla52SL.setText("0");
                int casilla28numresiduo = casilla28num-casilla82num;
                if(casilla28numresiduo >= casilla83num){
                    Casilla23SL.setText("" + casilla83num);
                    Casilla33SL.setText("0");
                    Casilla43SL.setText("0");
                    Casilla53SL.setText("0");
                    int casilla28numresiduo2 = casilla28numresiduo-casilla83num;
                    if(casilla28numresiduo2 <= casilla84num){
                        Casilla24SL.setText("" + casilla28numresiduo2);
                        Casilla25SL.setText("0");
                        int casilla84numresiduo = casilla84num-casilla28numresiduo2;
                        if(casilla84numresiduo <= casilla38num){
                            Casilla34SL.setText("" + casilla84numresiduo);
                            Casilla44SL.setText("0");
                            Casilla54SL.setText("0");
                            int casilla38numresiduo = casilla38num-casilla84numresiduo;
                            if(casilla38numresiduo <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo;
                                    //final de la rama...
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    if(casilla85numresiduo2 <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo2);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo2;
                                        //final de la rama...
                                    }else if(casilla85numresiduo2 >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo3 = casilla85numresiduo2-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla38numresiduo >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla38numresiduo2 = casilla38numresiduo-casilla85num;
                                //final de la rama...
                            }
                        }else if(casilla84numresiduo >= casilla38num){
                            Casilla34SL.setText("" + casilla38num);
                            Casilla35SL.setText("0");
                            int casilla84numresiduo2 = casilla84numresiduo-casilla38num;
                            if(casilla84numresiduo2 <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo2);
                                Casilla54SL.setText("0");
                                int casilla48numresiduo = casilla48num-casilla84numresiduo2;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    if(casilla85numresiduo <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo;
                                        //final de la rama...
                                    }else if(casilla85numresiduo >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo2 = casilla85numresiduo-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    //final de la rama...
                                }
                            }else if(casilla84numresiduo2 >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo3 = casilla84numresiduo2-casilla48num;
                                if(casilla84numresiduo3 <= sobrante_oferta_int){
                                    Casilla54SL.setText("" + casilla84numresiduo3);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla84numresiduo3;
                                    if(sobrante_oferta_int_residuo <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla85num;
                                        //final de la rama...
                                    }
                                }else if(casilla84numresiduo3 >= sobrante_oferta_int){
                                    Casilla54SL.setText("" + sobrante_oferta_int);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo4 = casilla84numresiduo3-sobrante_oferta_int;
                                    //final de la rama...
                                }
                            }
                        }
                    }else if(casilla28numresiduo2 >= casilla84num){
                        Casilla24SL.setText("" + casilla84num);
                        Casilla34SL.setText("0");
                        Casilla44SL.setText("0");
                        Casilla54SL.setText("0");
                        int casilla28numresiduo3 = casilla28numresiduo2-casilla84num;
                        if(casilla28numresiduo3 <= casilla85num){
                            Casilla25SL.setText("" + casilla28numresiduo3);
                            int casilla85numresiduo = casilla85num-casilla28numresiduo3;
                            if(casilla85numresiduo <= casilla38num){
                                Casilla35SL.setText("" + casilla85numresiduo);
                                Casilla45SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla38numresiduo = casilla38num-casilla85numresiduo;
                                //final de la rama...
                            }else if(casilla85numresiduo >= casilla38num){
                                Casilla35SL.setText("" + casilla38num);
                                int casilla85numresiduo2 = casilla85numresiduo-casilla38num;
                                if(casilla85numresiduo2 <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo2);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo = casilla48num-casilla85numresiduo2;
                                    //final de la rama...
                                }else if(casilla85numresiduo2 >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo3 = casilla85numresiduo2-casilla48num;
                                    if(casilla85numresiduo3 <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo3);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo3;
                                        //final de la rama...
                                    }else if(casilla85numresiduo3 >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo4 = casilla85numresiduo3-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }
                            }
                        }else if(casilla28numresiduo3 >= casilla85num){
                            Casilla25SL.setText("" + casilla85num);
                            Casilla35SL.setText("0");
                            Casilla45SL.setText("0");
                            Casilla55SL.setText("0");
                            int casilla28numresiduo4 = casilla28numresiduo3-casilla85num;
                            //final de la rama...
                        }
                    }
                }else if(casilla28numresiduo <= casilla83num){
                    Casilla23SL.setText("" + casilla28numresiduo);
                    Casilla24SL.setText("0");
                    Casilla25SL.setText("0");
                    int casilla83numresiduo = casilla83num-casilla28numresiduo;
                    if(casilla83numresiduo <= casilla38num){
                        Casilla33SL.setText("" + casilla83numresiduo);
                        Casilla43SL.setText("0");
                        Casilla53SL.setText("0");
                        int casilla38numresiduo = casilla38num-casilla83numresiduo;
                        if(casilla38numresiduo <= casilla84num){
                            Casilla34SL.setText("" + casilla38numresiduo);
                            Casilla35SL.setText("0");
                            int casilla84numresiduo = casilla84num-casilla38numresiduo;
                            if(casilla84numresiduo <= casilla48num){
                                Casilla44SL.setText("" + casilla84numresiduo);
                                Casilla54SL.setText("0");
                                int casilla48numresiduo = casilla48num-casilla84numresiduo;
                                if(casilla48numresiduo <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo;
                                    if(casilla85numresiduo <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo;
                                        //final de la rama...
                                    }else if(casilla85numresiduo >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo2 = casilla85numresiduo-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }else if(casilla48numresiduo >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo2 = casilla48numresiduo-casilla85num;
                                    //final de la rama...
                                }
                            }else if(casilla84numresiduo >= casilla48num){
                                Casilla44SL.setText("" + casilla48num);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo2 = casilla84numresiduo-casilla48num;
                                if(casilla84numresiduo2 <= sobrante_oferta_int){
                                    Casilla54SL.setText("" + casilla84numresiduo2);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla84numresiduo2;
                                    if(sobrante_oferta_int_residuo <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    } else if(sobrante_oferta_int_residuo >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla85num;
                                        //final de la rama...
                                    }
                                }else if(casilla84numresiduo2 >= sobrante_oferta_int){
                                    Casilla54SL.setText("" + sobrante_oferta_int);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo3 = casilla84numresiduo2-sobrante_oferta_int;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla38numresiduo >= casilla84num){
                            Casilla34SL.setText("" + casilla84num);
                            Casilla44SL.setText("0");
                            Casilla54SL.setText("0");
                            int casilla38numresiduo2 = casilla38numresiduo-casilla84num;
                            if(casilla38numresiduo2 <= casilla85num){
                                Casilla35SL.setText("" + casilla38numresiduo2);
                                int casilla85numresiduo = casilla85num-casilla38numresiduo2;
                                if(casilla85numresiduo <= casilla48num){
                                    Casilla45SL.setText("" + casilla85numresiduo);
                                    Casilla55SL.setText("0");
                                    int casilla48numreisduo = casilla48num-casilla85numresiduo;
                                    //final de la rama...
                                }else if(casilla85numresiduo >= casilla48num){
                                    Casilla45SL.setText("" + casilla48num);
                                    int casilla85numresiduo2 = casilla85numresiduo-casilla48num;
                                    if(casilla85numresiduo2 <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo2);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo2;
                                        //final de la rama...
                                    }else if(casilla85numresiduo2 >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo3 = casilla85numresiduo2-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla38numresiduo2 >= casilla85num){
                                Casilla35SL.setText("" + casilla85num);
                                Casilla45SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla38numresiduo3 = casilla38numresiduo2-casilla85num;
                                //final de la rama...
                            }
                        }
                    }else if(casilla83numresiduo >= casilla38num){
                        Casilla33SL.setText("" + casilla38num);
                        Casilla34SL.setText("0");
                        Casilla35SL.setText("0");
                        int casilla83numresiduo2 = casilla83numresiduo-casilla38num;
                        if(casilla83numresiduo2 <= casilla48num){
                            Casilla43SL.setText("" + casilla83numresiduo2);
                            Casilla53SL.setText("0");
                            int casilla48numresiduo = casilla48num-casilla83numresiduo2;
                            if(casilla48numresiduo <= casilla84num){
                                Casilla44SL.setText("" + casilla48numresiduo);
                                Casilla45SL.setText("0");
                                int casilla84numresiduo = casilla84num-casilla48numresiduo;
                                if(casilla84numresiduo <= sobrante_oferta_int){
                                    Casilla54SL.setText("" + casilla84numresiduo);
                                    int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla84numresiduo;
                                    if(sobrante_oferta_int_residuo <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo);
                                        int casilla85numresiduco = casilla85num-sobrante_oferta_int_residuo;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla85num;
                                        //final de la rama...
                                    }
                                }else if(casilla84numresiduo >= sobrante_oferta_int){
                                    Casilla54SL.setText("" + sobrante_oferta_int);
                                    Casilla55SL.setText("0");
                                    int casilla84numresiduo2 = casilla84numresiduo-sobrante_oferta_int;
                                    //final de la rama...
                                }
                            }else if(casilla48numresiduo >= casilla84num){
                                Casilla44SL.setText("" + casilla84num);
                                Casilla54SL.setText("0");
                                int casilla48numresiduo2 =casilla48numresiduo-casilla84num;
                                if(casilla48numresiduo2 <= casilla85num){
                                    Casilla45SL.setText("" + casilla48numresiduo2);
                                    int casilla85numresiduo = casilla85num-casilla48numresiduo2;
                                    if(casilla85numresiduo <= sobrante_oferta_int){
                                        Casilla55SL.setText("" + casilla85numresiduo);
                                        int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla85numresiduo;
                                        //final de la rama...
                                    }else if(casilla85numresiduo >= sobrante_oferta_int){
                                        Casilla55SL.setText("" + sobrante_oferta_int);
                                        int casilla85numresiduo2 = casilla85numresiduo-sobrante_oferta_int;
                                        //final de la rama...
                                    }
                                }else if(casilla48numresiduo2 >= casilla85num){
                                    Casilla45SL.setText("" + casilla85num);
                                    Casilla55SL.setText("0");
                                    int casilla48numresiduo3 = casilla48numresiduo2-casilla85num;
                                    //final de la rama...
                                }
                            }
                        }else if(casilla83numresiduo2 >= casilla48num){
                            Casilla43SL.setText("" + casilla48num);
                            Casilla44SL.setText("0");
                            Casilla45SL.setText("0");
                            int casilla83numresiduo3 = casilla83numresiduo2-casilla48num;
                            if(casilla83numresiduo3 <= sobrante_oferta_int){
                                Casilla53SL.setText("" + casilla83numresiduo3);
                                int sobrante_oferta_int_residuo = sobrante_oferta_int-casilla83numresiduo3;
                                if(sobrante_oferta_int_residuo <= casilla84num){
                                    Casilla54SL.setText("" + sobrante_oferta_int_residuo);
                                    Casilla55SL.setText("0");
                                    //final de la rama...
                                }else if(sobrante_oferta_int_residuo >= casilla84num){
                                    Casilla54SL.setText("" + casilla84num);
                                    int sobrante_oferta_int_residuo2 = sobrante_oferta_int_residuo-casilla84num;
                                    if(sobrante_oferta_int_residuo2 <= casilla85num){
                                        Casilla55SL.setText("" + sobrante_oferta_int_residuo2);
                                        int casilla85numresiduo = casilla85num-sobrante_oferta_int_residuo2;
                                        //final de la rama...
                                    }else if(sobrante_oferta_int_residuo2 >= casilla85num){
                                        Casilla55SL.setText("" + casilla85num);
                                        int sobrante_oferta_int_residuo3 = sobrante_oferta_int_residuo2-casilla85num;
                                        //final de la rama...
                                    }
                                }
                            }else if(casilla83numresiduo3 >= sobrante_oferta_int){
                                Casilla53SL.setText("" + sobrante_oferta_int);
                                Casilla54SL.setText("0");
                                Casilla55SL.setText("0");
                                int casilla83numresiduo4 = casilla83numresiduo3-sobrante_oferta_int;
                                //final de la rama...
                            }
                        }
                    }
                }
            }
        }
    }





    private void ejecutarloginconstante() {
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ejecutar(){
        time time = new time();
        time.execute();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        AlertDialog.Builder Bien = new AlertDialog.Builder(CalculadoraTransporte.this);
        Bien.setMessage("No se ha podido cargar el video, posiblemente es tu conexión a internet, vuelve a intentarlo")
                .setCancelable(true)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Volver a intentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadRewardedVideoAd();
                        if(mRewardedVideoAd.isLoaded()){
                            mRewardedVideoAd.show();
                        }
                    }
                });
        AlertDialog Titulo = Bien.create();
        Titulo.setTitle("Oh no!");
        Titulo.show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        int VerifiSolutionFinal = 0;
        String VSS = String.valueOf(VerifiSolutionFinal);
        SharedPreferences preferencess = getSharedPreferences("SolucionOptima", Context.MODE_PRIVATE);
        String accion = VSS;
        SharedPreferences.Editor editor = preferencess.edit();
        editor.putString("nivel", accion);
        editor.commit();
        AlertDialog.Builder Bien = new AlertDialog.Builder(CalculadoraTransporte.this);
        Bien.setMessage("Has desbloqueado 5 soluciones más, puedes continuar.")
                .setCancelable(true)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog Titulo = Bien.create();
        Titulo.setTitle("Excelente!");
        Titulo.show();
    }

    private class time extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i=1; i<=5;i++){
                ejecutarloginconstante();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            ejecutar();

            //Toast.makeText(CalculadoraTransporte.this, "Proceso en segundo plano.", Toast.LENGTH_SHORT).show();
        }
    }


}