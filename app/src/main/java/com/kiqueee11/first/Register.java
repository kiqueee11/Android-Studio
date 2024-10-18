package com.kiqueee11.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.regex.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import kotlin.text.Regex;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout registerUsername = findViewById(R.id.registerUsernameTIL);
        TextInputLayout registerPassword = findViewById(R.id.registerPasswordTIL);
        Button registerButton = findViewById(R.id.registerButtonTIL);
        TextInputLayout registerPassword2 = findViewById(R.id.registerPassword2TIL);
        TextInputLayout email = findViewById(R.id.registerCorreoTIL);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(registerUsername.getEditText().getText());
                String password = String.valueOf(registerPassword.getEditText().getText());
                String userPasswordCheck = String.valueOf(registerPassword2.getEditText().getText());
                String correo = String.valueOf(email.getEditText().getText());
                if(!password.equals(userPasswordCheck)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Tus contrasenas no coinciden", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!validate(correo)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Correo no valido", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    SharedPreferences preferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userName", userName);
                    editor.putString("userPassword", password);
                    editor.putString("correo", correo);
                    editor.apply();
                    launchMain();
                }
            }
        });

    }
    public void launchMain(){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}