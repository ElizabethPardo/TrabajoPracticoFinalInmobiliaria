package com.example.trabajopracticofinal.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticofinal.modelo.Contrato;
import com.example.trabajopracticofinal.modelo.Inmueble;
import com.example.trabajopracticofinal.modelo.Inquilino;
import com.example.trabajopracticofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContratosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> error;
    private ArrayAdapter<Contrato> adapter;
    private MutableLiveData<ArrayList<Contrato>> contratosMutable;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Contrato>> getContratos() {

        if(contratosMutable == null)
        {
            contratosMutable= new MutableLiveData<>();
        }
        return contratosMutable;
    }
    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }


    public void cargarContratos()
    {
        Call<ArrayList<Contrato>> contratos= ApiClient.getMyApiInterface().ListaContrato(ApiClient.obtenerToken(context));
        contratos.enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if (response.isSuccessful())
                 {

                    contratosMutable.postValue(response.body());

                }
                else{
                    error.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contrato>> call, Throwable t) {

                error.setValue("No existen contratos");

            }
        });
    }

}