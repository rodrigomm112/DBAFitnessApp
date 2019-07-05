package com.tecnologiasmoviles.dbafitnessapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class comidasActivity extends AppCompatActivity {

    //declarando la lista
    private List<NotasRutina> listNotasRutina = new ArrayList<NotasRutina>();
    ArrayAdapter<NotasRutina> arrayAdapterNotasRutina;

    EditText blocnotas1;
    EditText nombreNota1;
    ListView lv_Rutinas1;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    NotasRutina notaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina);

        this.setTitle(R.string.titulodieta);

        blocnotas1 = findViewById(R.id.blocnotas);
        nombreNota1 = findViewById(R.id.nombreNota);
        lv_Rutinas1 = findViewById(R.id.lv_Rutinas);
        //metodo principal creado para correr firebase
        inicializarFirebase();
        //otrosmetodos
        listarNotas();

        //accedemos a la lista para actualizar
        lv_Rutinas1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notaSelecionada = (NotasRutina) parent.getItemAtPosition(position);
                blocnotas1.setText(notaSelecionada.getNotas());
                nombreNota1.setText(notaSelecionada.getNombreNota());
            }
        });
    }

    private void listarNotas() {
        databaseReference.child("Comidas/Dieta").child("Notas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listNotasRutina.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    NotasRutina p = objSnaptshot.getValue(NotasRutina.class);
                    listNotasRutina.add(p);

                    arrayAdapterNotasRutina = new ArrayAdapter<NotasRutina>(comidasActivity.this, android.R.layout.simple_list_item_1, listNotasRutina);
                    lv_Rutinas1.setAdapter(arrayAdapterNotasRutina);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rutina,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String notas = blocnotas1.getText().toString();
        String nomNotas = nombreNota1.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                if (nomNotas.equals("")){
                    validacionVacio();
                }
                else {
                    NotasRutina nota1 = new NotasRutina();
                    nota1.setUid(UUID.randomUUID().toString());
                    nota1.setNombreNota(nomNotas);
                    nota1.setNotas(notas);
                    databaseReference.child("Comidas/Dieta").child("Notas").child(nota1.getUid()).setValue(nota1);
                    Toast.makeText(this, "Nota Agregada", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.icon_save:{
                NotasRutina p = new NotasRutina();
                p.setUid(notaSelecionada.getUid());
                p.setNotas(blocnotas1.getText().toString().trim());
                p.setNombreNota(nombreNota1.getText().toString().trim());
                databaseReference.child("Comidas/Dieta").child("Notas").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Nota Guardada/Actualizada", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete:{
                NotasRutina p = new NotasRutina();
                p.setUid(notaSelecionada.getUid());
                databaseReference.child("Comidas/Dieta").child("Notas").child(p.getUid()).removeValue();
                Toast.makeText(this,"Nota borrada", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
        }
        return true;
    }

    private void limpiarCajas() {
        nombreNota1.setText("");
        blocnotas1.setText("");
    }

    private void validacionVacio() {
        String nomNotas = nombreNota1.getText().toString();

        if (nomNotas.equals("")){
            nombreNota1.setError("Nombre Requerido");
        }
    }
}
