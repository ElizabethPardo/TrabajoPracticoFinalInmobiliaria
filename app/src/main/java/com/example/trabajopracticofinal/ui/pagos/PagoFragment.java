package com.example.trabajopracticofinal.ui.pagos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopracticofinal.R;
import com.example.trabajopracticofinal.modelo.Pago;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class PagoFragment extends Fragment {

    private PagoViewModel vm;
    private TextInputEditText etNroPago;
    private TextInputEditText etFechaPago;
    private  TextInputEditText etImportePago;
    private ListView lvPagos;
    PagosAdapter adapterPagos;

    public PagoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.pago_fragment, container, false);

        inicializar(root, savedInstanceState);


        return root;
    }

    private void inicializar(View root, Bundle savedInstanceState) {

        lvPagos=root.findViewById(R.id.lvPagos);
        etNroPago=  root.findViewById(R.id.etdNroPago);
        etFechaPago=  root.findViewById(R.id.etdFechaPago);
        etImportePago= root.findViewById(R.id.etdImportePago);


      vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagoViewModel.class);
        int id= getArguments().getInt("id");

        vm.getPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                adapterPagos= new PagosAdapter(getContext(),R.layout.item_pago,pagos,getLayoutInflater());
                lvPagos.setAdapter(adapterPagos);
            }
        });

        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
            }
        });
        vm.cargarPagos(id);
    }


}

