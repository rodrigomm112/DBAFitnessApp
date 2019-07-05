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

public class RutinaActivity extends AppCompatActivity {

    //declarando la lista
    private List<NotasRutina> listNotasRutina = new ArrayList<NotasRutina>();
    ArrayAdapter<NotasRutina> arrayAdapterNotasRutina;

    EditText blocnotas;
    EditText nombreNota;
    ListView lv_Rutinas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    NotasRutina notaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina);

        this.setTitle(R.string.titulorutina);

        blocnotas = findViewById(R.id.blocnotas);
        nombreNota = findViewById(R.id.nombreNota);
        lv_Rutinas = findViewById(R.id.lv_Rutinas);
        //metodo principal creado para correr firebase
        inicializarFirebase();
        //otrosmetodos
        listarNotas();

        //accedemos a la lista para actualizar
        lv_Rutinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notaSelecionada = (NotasRutina) parent.getItemAtPosition(position);
                blocnotas.setText(notaSelecionada.getNotas());
                nombreNota.setText(notaSelecionada.getNombreNota());
            }
        });
    }

    private void listarNotas() {
        databaseReference.child("Rutinas_Entrenamiento").child("Notas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listNotasRutina.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    NotasRutina p = objSnaptshot.getValue(NotasRutina.class);
                    listNotasRutina.add(p);

                    arrayAdapterNotasRutina = new ArrayAdapter<NotasRutina>(RutinaActivity.this, android.R.layout.simple_list_item_1, listNotasRutina);
                    lv_Rutinas.setAdapter(arrayAdapterNotasRutina);
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

        String notas = blocnotas.getText().toString();
        String nomNotas = nombreNota.getText().toString();

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
                    databaseReference.child("Rutinas_Entrenamiento").child("Notas").child(nota1.getUid()).setValue(nota1);
                    Toast.makeText(this, "Nota Agregada", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.icon_save:{
                NotasRutina p = new NotasRutina();
                p.setUid(notaSelecionada.getUid());
                p.setNotas(blocnotas.getText().toString().trim());
                p.setNombreNota(nombreNota.getText().toString().trim());
                databaseReference.child("Rutinas_Entrenamiento").child("Notas").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Nota Guardada/Actualizada", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete:{
                NotasRutina p = new NotasRutina();
                p.setUid(notaSelecionada.getUid());
                databaseReference.child("Rutinas_Entrenamiento").child("Notas").child(p.getUid()).removeValue();
                Toast.makeText(this,"Nota borrada", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
        }
        return true;
    }

    private void limpiarCajas() {
        nombreNota.setText("");
        blocnotas.setText("");
    }

    private void validacionVacio() {
        String nomNotas = nombreNota.getText().toString();

        if (nomNotas.equals("")){
            nombreNota.setError("Nombre Requerido");
        }
    }
}
