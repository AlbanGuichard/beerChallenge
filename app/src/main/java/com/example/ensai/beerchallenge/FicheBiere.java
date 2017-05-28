package com.example.ensai.beerchallenge;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.*;


/**
 * Created by ensai on 27/05/17.
 */
public class FicheBiere  extends AppCompatActivity {

    final ArrayList<Biere> listeBieres = new ArrayList<Biere>();
    public static String nomBiere="";
    public static int idBiere;
    public static Biere biere = new Biere();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();

        int idBiere = intent.getIntExtra("Non_ID",0);
        Toast.makeText(this.getApplicationContext(), "id : "+idBiere, Toast.LENGTH_SHORT).show();


        /* Recherche de la bière dans la base de données */
        String myurl = "http://alban.guichard.free.fr/BeerChallenge/rechercheBiereParID.php?" + "id=" + idBiere;

        OkHttpClient okhttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder().url(myurl).build();



            okhttpClient.newCall(myGetRequest).enqueue(new Callback() {

                                                           public void onFailure(Request request, IOException e) {
                                                           }

                                                           public void onResponse(Response response) throws IOException {

                                                               try {
                                                                   String text = response.body().string();
                                                                   JSONArray json = new JSONArray(text);

                                                                   for (int i = 0; i < json.length(); i++) {

                                                                       JSONObject jsonobject = json.getJSONObject(i);

                                                                       //Biere biere = new Biere() ;

                                                                       FicheBiere.biere.setId(jsonobject.getInt("id_biere"));
                                                                       FicheBiere.biere.setNom(jsonobject.getString("nom"));
                                                                       FicheBiere.biere.setBrasserie(jsonobject.getString("nom_brasserie"));
                                                                       FicheBiere.biere.setCouleur(jsonobject.getString("libelle_couleur"));
                                                                       FicheBiere.biere.setDegre_alcool(jsonobject.getDouble("degre_alcool"));
                                                                       FicheBiere.biere.setFermentation(jsonobject.getString("libelle_fermentation"));
                                                                       FicheBiere.biere.setType(jsonobject.getString("libelle_type"));

                                                                       listeBieres.add(biere);
                                                                   }
                                                               } catch (JSONException exc) {
                                                                   exc.printStackTrace();
                                                               }
                                                           }
                                                       }
            );



        Biere biere = new Biere();

        Log.i("Inofo",FicheBiere.getNom());
        Log.i("**",FicheBiere.biere.getNom());

        setContentView(R.layout.fichebiere);

        final TextView tv1 = (TextView) findViewById(R.id.NomDeLaBiere);
        tv1.setText(FicheBiere.getNom());

        final TextView tv2 = (TextView) findViewById(R.id.CouleurDeLaBiere);
        tv2.setText(biere.getCouleur());

        final TextView tv3 = (TextView) findViewById(R.id.FermentationDeLaBiere);
        tv3.setText(biere.getFermentation());

        final TextView tv4 = (TextView) findViewById(R.id.DegreDeLaBiere);
        tv4.setText(""+biere.getDegre_alcool());

        final TextView tv5 = (TextView) findViewById(R.id.TypeDeLaBiere);
        tv5.setText(biere.getType());

        final TextView tv6 = (TextView) findViewById(R.id.BrasserieDeLaBiere);
        tv6.setText(biere.getType());


    }

    public void clickButtonBue(View v){
//
//        Intent i1 = new Intent(v.getContext(), FicheBiereBue.class);
//        final TextView tv1 = (TextView) findViewById(R.id.NomDeLaBiere);
//        i1.putExtra(Non_BIEREpb, tv1.getText());
//        final TextView tv2 = (TextView) findViewById(R.id.CouleurDeLaBiere);
//        i1.putExtra(Non_COULEURpb, tv2.getText());
//        final TextView tv3 = (TextView) findViewById(R.id.FermentationDeLaBiere);
//        i1.putExtra(Non_FERMENTATIONpb, tv3.getText());
//        final TextView tv4 = (TextView) findViewById(R.id.DegreDeLaBiere);
//        i1.putExtra(Non_DEGREpb, tv4.getText());
//        final TextView tv5 = (TextView) findViewById(R.id.TypeDeLaBiere);
//        i1.putExtra(Non_TYPEpb, tv5.getText());
//        final TextView tv6 = (TextView) findViewById(R.id.BrasserieDeLaBiere);
//        i1.putExtra(Non_BRASSERIEpb, tv6.getText());
//        TextView tv7 = (TextView) findViewById(R.id.IdDeLaBiere);
//        String idString=tv7.getText().toString();
//        Toast.makeText(v.getContext(), idString, Toast.LENGTH_SHORT).show();
//        i1.putExtra(Non_IDpb, idString);
//        startActivityForResult(i1, 6);

    }

    public void setIdBiere(int idBiere){
        this.idBiere = idBiere ;
    }

    public int getIdBiere(){
        return idBiere ;
    }

    public static void setNom(String nom){
        Log.i("AA",nom);
        nomBiere=nom ;
    }

    public static String getNom(){
        Log.i("AAAA",nomBiere);
        return nomBiere ;
    }
}