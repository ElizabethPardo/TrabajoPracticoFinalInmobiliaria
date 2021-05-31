package com.example.trabajopracticofinal.ui.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.trabajopracticofinal.R;
import com.example.trabajopracticofinal.modelo.Contrato;
import com.example.trabajopracticofinal.modelo.Pago;
import com.example.trabajopracticofinal.ui.contratos.ContratoViewModel;
import com.example.trabajopracticofinal.ui.contratos.ContratosViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class PagosAdapter extends ArrayAdapter<Pago> {

    private Context context;
    private List<Pago> pagos;
    private LayoutInflater li;
    private PagosViewModel vm;


    public PagosAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects,LayoutInflater li) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.li = li;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item= convertView;

        if(item == null)
        {
            item= li.inflate(R.layout.item_pago,parent,false);
        }

        Pago pago =pagos.get(position);


        TextInputEditText etNroPago=  item.findViewById(R.id.etdNroPago);
        TextInputEditText etFechaPago=  item.findViewById(R.id.etdFechaPago);
        TextInputEditText etImportePago= item.findViewById(R.id.etdImportePago);

        etNroPago.setText((""+ pago.getNroPago()));
        etFechaPago.setText(pago.getFechaPago());
        etImportePago.setText("$" +pago.getImporte());

        return  item;

    }
}