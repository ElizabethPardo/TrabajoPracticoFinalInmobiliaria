package com.example.trabajopracticofinal.ui.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajopracticofinal.R;


public class LogoutFragment extends Fragment {



    public LogoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View root=inflater.inflate(R.layout.fragment_logout, container, false);
        dialogSalir(root);
         return root;
    }

    public  void dialogSalir(View v)
    {
        new AlertDialog.Builder(getContext())
                .setTitle("Logout")
                .setMessage("Esta seguro que desea cerrar sesión?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.nav_perfil);
                    }
                })
                .show();

    }
}