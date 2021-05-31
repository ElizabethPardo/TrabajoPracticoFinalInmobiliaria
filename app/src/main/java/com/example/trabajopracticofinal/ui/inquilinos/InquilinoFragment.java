package com.example.trabajopracticofinal.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopracticofinal.R;
import com.google.android.material.textfield.TextInputEditText;

import com.example.trabajopracticofinal.modelo.Inquilino;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel vm;
    private  TextInputEditText etDni;
    private  TextInputEditText etApellido;
    private  TextInputEditText etNombre;
    private  TextInputEditText etDireccion;
    private  TextInputEditText etTelefono;
    private  TextInputEditText etEmail;
    private  TextInputEditText etLugarTrabajo;
    private  TextInputEditText etNombreGarante;
    private  TextInputEditText etApellidoGarante;
    private  TextInputEditText etDniGarante;
    private  TextInputEditText etTelefonoGarante;
    private  TextInputEditText etDireccionGarante;

    public InquilinoFragment() {
    }

    public InquilinoFragment(int contentLayoutId, TextInputEditText etDni, TextInputEditText etApellido, TextInputEditText etNombre, TextInputEditText etDireccion, TextInputEditText etTelefono, TextInputEditText etEmail, TextInputEditText etNombreGarante,TextInputEditText etApellidoGarante, TextInputEditText etDniGarante, TextInputEditText etTelefonoGarante, TextInputEditText etDireccionGarante) {
        super(contentLayoutId);
        this.etDni = etDni;
        this.etApellido = etApellido;
        this.etNombre = etNombre;
        this.etDireccion = etDireccion;
        this.etTelefono = etTelefono;
        this.etEmail = etEmail;
        this.etNombreGarante = etNombreGarante;
        this.etApellidoGarante=etApellidoGarante;
        this.etDniGarante = etDniGarante;
        this.etTelefonoGarante = etTelefonoGarante;
        this.etDireccionGarante=etDireccionGarante;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_inquilino, container, false);
        inicializar(root, savedInstanceState);
        return root;
    }

    public void inicializar(View view, Bundle bundle)
    {
        etDni= view.findViewById(R.id.etDni2);
        etApellido= view.findViewById(R.id.etApellido2);
        etNombre= view.findViewById(R.id.etNombre);
        etDireccion =view.findViewById(R.id.etDireccion);
        etTelefono=view.findViewById(R.id.etTelefono);
        etEmail= view.findViewById(R.id.etEmail);
        etLugarTrabajo=view.findViewById(R.id.etLugarTrabajo);
        etDniGarante=view.findViewById(R.id.etDniGarante);
        etNombreGarante= view.findViewById(R.id.etNombreGarante);
        etApellidoGarante=view.findViewById(R.id.etApellidoGarante);
        etTelefonoGarante=view.findViewById(R.id.etTelefonoGarante);
        etDireccionGarante=view.findViewById(R.id.etDireccionGarante);


        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        vm.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {

                etNombre.setText(inquilino.getNombre());
                etApellido.setText(inquilino.getApellido());
                etDni.setText(inquilino.getDni());
                etTelefono.setText(inquilino.getTelefono());
                etDireccion.setText(inquilino.getDireccion());
                etEmail.setText(inquilino.getEmail());
                etLugarTrabajo.setText(inquilino.getLugarTrabajo());
                etNombreGarante.setText(inquilino.getNombreGarante());
                etApellidoGarante.setText(inquilino.getApellidoGarante());
                etDniGarante.setText(inquilino.getDniGarante());
                etTelefonoGarante.setText(inquilino.getTelefonoGarante());
                etDireccionGarante.setText(inquilino.getDireccionGarante());
            }
        });

        vm.cargarInquilino(getArguments());
    }


}
