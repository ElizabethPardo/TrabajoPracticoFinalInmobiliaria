package com.example.trabajopracticofinal.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.trabajopracticofinal.MainActivity;
import com.example.trabajopracticofinal.R;
import com.example.trabajopracticofinal.modelo.Inmueble;
import com.google.android.material.textfield.TextInputEditText;

import com.example.trabajopracticofinal.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel vm;
    private TextInputEditText etDni;
    private TextInputEditText etApellido;
    private TextInputEditText etNombre;
    private TextInputEditText etDireccion;
    private TextInputEditText etTelefono;
   // private TextInputEditText etEmail;
   // private TextInputEditText etPass;
    private Button btAceptar,btEditar;
    private Propietario propietarioActual=null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializar(root);

        return root;
    }
    private  void  inicializar(View view)
    {
        etDni=view.findViewById(R.id.etDni);
        etApellido=view.findViewById(R.id.etApellido);
        etNombre=view.findViewById(R.id.etNombre);
        etDireccion=view.findViewById(R.id.etDireccion);
        etTelefono=view.findViewById(R.id.etTelefono);
        btEditar=view.findViewById(R.id.btEditar);
        btAceptar=view.findViewById(R.id.btAceptar);

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        vm.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etDni.setText(propietario.getDni());
                etDireccion.setText(propietario.getDireccion());
                etTelefono.setText(propietario.getTelefono());


                propietarioActual=propietario;

            }
        });
        vm.recuperarPropietario();

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etNombre.setEnabled(true);
                etApellido.setEnabled(true);
                etDni.setEnabled(true);
                etDireccion.setEnabled(true);
                etTelefono.setEnabled(true);
                btEditar.setVisibility(View.GONE);
                btAceptar.setVisibility(View.VISIBLE);
            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Propietario prop= new Propietario(propietarioActual.getId(),etNombre.getText().toString(),etApellido.getText().toString(),etDni.getText().toString(),etDireccion.getText().toString(),etTelefono.getText().toString(),propietarioActual.getEmail(), propietarioActual.getClave());
                vm.editarPerfil(prop);
                etNombre.setEnabled(false);
                etApellido.setEnabled(false);
                etDni.setEnabled(false);
                etDireccion.setEnabled(false);
                etTelefono.setEnabled(false);
                btEditar.setVisibility(View.VISIBLE);
                btAceptar.setVisibility(View.INVISIBLE);
            }
        });

        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
            }
        });
    }
}