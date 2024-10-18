package com.kiqueee11.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputLayout loginUsuario = findViewById(R.id.loginUsernameTIL);
        TextInputLayout loginContrasena = findViewById(R.id.loginPasswordTIL);
        Button loginButton = findViewById(R.id.LoginButtonTIL);
        TextView loginRegisterText = findViewById(R.id.loginTexto);
        SharedPreferences sharedPreferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("userName","anonimo");
        String password = sharedPreferences.getString("userPassword","contraseña");


        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               String loginUser = String.valueOf(loginUsuario.getEditText().getText());
                                               String loginPassword = String.valueOf(loginContrasena.getEditText().getText());

                                               if (name.equals(loginUser) && password.equals(loginPassword)) {
                                                   launchMain();
                                               }else{
                                                   Toast toast = Toast.makeText(getApplicationContext(), "Tu usuario o tu contraseña son erroneos", Toast.LENGTH_SHORT);
                                                   toast.show();
                                               }
                                           }

                                           ;
                                       });
        loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });
        }
    public void launchMain(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void launchRegister(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
    }
