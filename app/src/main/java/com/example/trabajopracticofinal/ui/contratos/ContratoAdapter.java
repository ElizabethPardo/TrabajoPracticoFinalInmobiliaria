package com.example.trabajopracticofinal.ui.contratos;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.trabajopracticofinal.R;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.trabajopracticofinal.modelo.Contrato;

public class ContratoAdapter extends ArrayAdapter<Contrato> {

    private  Context context;
    private List<Contrato> contratos;
    private LayoutInflater li;

    public ContratoAdapter(@NonNull Context context, int resource, @NonNull List<Contrato> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);

        this.context=context;
        this.contratos=objects;
        this.li=layoutInflater;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item= convertView;

        if(item == null)
        {
            item= li.inflate(R.layout.contrato_fragment,parent,false);
        }

        Contrato contrato =contratos.get(position);
        TextInputEditText fechaIngreso= item.findViewById(R.id.etdFechaIngreso);
        TextInputEditText fechaSalida= item.findViewById(R.id.etdFechaSalida);
        TextInputEditText direccionInmueble=item.findViewById(R.id.etdDireccionInmueble);

        LocalDateTime fi = LocalDateTime.parse(contrato.getFechaDesde());
        LocalDate fff = fi.toLocalDate();
        fechaIngreso.setText(fff.toString());

        LocalDateTime fc = LocalDateTime.parse(contrato.getFechaHasta());
        LocalDate hhh = fc.toLocalDate();
        fechaSalida.setText(hhh.toString());

        direccionInmueble.setText(contrato.getInmueble().getDireccion());

        return  item;
    }
}
