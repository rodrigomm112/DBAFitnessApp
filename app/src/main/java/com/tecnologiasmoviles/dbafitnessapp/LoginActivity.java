package com.tecnologiasmoviles.dbafitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends Activity {
    private Button btninvitado;
    private Button btnregistrologin;
    private Button btnIniciarSesion;
    private EditText TextEmailLogin;
    private EditText TextPasswordLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        btninvitado=findViewById(R.id.btninvitado);
        btnregistrologin=findViewById(R.id.btnregistrologin);
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion);
        TextEmailLogin=findViewById(R.id.TextEmailLogin);
        TextPasswordLogin=findViewById(R.id.TextPasswordLogin);

        progressDialog = new ProgressDialog(this);
        //ocupa toda la pantalla hasta los tools de arriba como bateria,etc
        //falta cambiar el color a png de esa barra
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguearUsuario();
            }
        });

        btninvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnregistrologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        //animacion del fondo
        LinearLayout linearLayout = findViewById(R.id.layoutAnimado); //id del xml
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    private void loguearUsuario(){
        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmailLogin.getText().toString().trim();
        final String password = TextPasswordLogin.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Verificando...");
        progressDialog.show();

        //iniciando sesion
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(LoginActivity.this, "Bienvenido: " + TextEmailLogin.getText(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.user, user);
                            startActivity(intent);
                        }
                        else {
                                Toast.makeText(LoginActivity.this, "Email y password inexistentes o incorrectos", Toast.LENGTH_SHORT).show();
                                limpiarCajas();
                            }
                        progressDialog.dismiss();
                    }
                });


    }

    private void limpiarCajas() {
        TextPasswordLogin.setText("");
    }

    private void validacionVacio() {
        final String email = TextEmailLogin.getText().toString().trim();
        final String password = TextPasswordLogin.getText().toString().trim();

        if (email.equals("")){
            TextEmailLogin.setError("Email Requerido");
        }
        else if (password.equals("")){
            TextPasswordLogin.setError("Password Requerido");
        }
    }
}
