package com.example.trabajopracticofinal.ui.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.trabajopracticofinal.R;

import java.util.ArrayList;
import java.util.List;

import com.example.trabajopracticofinal.modelo.Contrato;
import com.example.trabajopracticofinal.modelo.Pago;

public class PagosFragment extends Fragment {

    private ListView lvContratos;

    private PagosViewModel vm;
    PagoAdapter adapter;
    Context context;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      View root= inflater.inflate(R.layout.pagos_fragment, container, false);
        context= root.getContext();
        inicializar(root);
      return root;

    }

    private void inicializar(View root) {

        lvContratos = root.findViewById(R.id.lvContratos2);

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagosViewModel.class);
        vm.getContratos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>() {
                   @Override
                   public void onChanged(ArrayList<Contrato> contratoes) {
                       adapter= new PagoAdapter(context,R.layout.contrato_fragment,contratoes,getLayoutInflater());
                       lvContratos.setAdapter(adapter);

                       lvContratos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               Contrato contrato =adapter.contratos.get(position);
                               Log.d("Salida", contrato.getFechaDesde());
                               Bundle bundle = new Bundle();
                               bundle.putSerializable("id",contrato.getId());
                               Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.pagoFragment,bundle);

                           }
                       });
                   }
               });
               vm.cargarContratos();





    }


}