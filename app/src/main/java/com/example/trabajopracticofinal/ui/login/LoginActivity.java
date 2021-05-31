package com.example.trabajopracticofinal.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabajopracticofinal.MainActivity;
import com.example.trabajopracticofinal.R;

public class LoginActivity extends AppCompatActivity {
 private EditText user;
 private EditText pass;
 private Button login;
 private LoginActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializar();
    }

    public void inicializar() {
        user = findViewById(R.id.etUser);
        pass = findViewById(R.id.etPass);
        login = findViewById(R.id.btnLogin);


        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);

        vm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.autenticar(user.getText().toString(), pass.getText().toString());
            }
        });

        vm.getLoginMutable().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean log) {

                        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                       startActivity(intent);

                    }
                }


        );


    }

}