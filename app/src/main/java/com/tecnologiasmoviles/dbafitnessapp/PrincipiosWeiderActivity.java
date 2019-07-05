package com.tecnologiasmoviles.dbafitnessapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PrincipiosWeiderActivity extends Activity {

    ArrayList<PrincipiosweiderVo> listaPrincipiosweider;
    RecyclerView recyclerPrincipiosweider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principios_weider);

        listaPrincipiosweider = new ArrayList<>();
        recyclerPrincipiosweider = (RecyclerView) findViewById(R.id.RecyclerId);
        recyclerPrincipiosweider.setLayoutManager(new LinearLayoutManager(this));

        llenarPrincipiosweider();

        AdaptadorPrincipiosweider adapter = new AdaptadorPrincipiosweider(listaPrincipiosweider);
        recyclerPrincipiosweider.setAdapter(adapter);
    }

    private void llenarPrincipiosweider() {
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE AISLAMIENTO",R.string.principio_de_aislamiento,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE SOBRECARGA",R.string.PRINCIPIO_DE_SOBRECARGA,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE PIRÁMIDE",R.string.PRINCIPIO_DE_PIRÁMIDE,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE RUTINA DIVIDIDA",R.string.PRINCIPIO_DE_RUTINA_DIVIDIDA,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE BOMBEO",R.string.PRINCIPIO_DE_BOMBEO,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE SERIES COMPUESTAS",R.string.PRINCIPIO_DE_SERIES_COMPUESTAS,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE SUPERSERIES",R.string.PRINCIPIO_DE_SUPERSERIES,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE TRISERIES",R.string.PRINCIPIO_DE_TRISERIES,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE PAUSA-DESCANSO",R.string.PRINCIPIO_DE_PAUSA_DESCANSO,R.drawable.ic_iconomusculos));
        listaPrincipiosweider.add(new PrincipiosweiderVo("PRINCIPIO DE ENTRENAMIENTO INSTINTIVO",R.string.PRINCIPIO_DE_ENTRENAMIENTO_INSTINTIVO,R.drawable.ic_iconomusculos));

    }
}
