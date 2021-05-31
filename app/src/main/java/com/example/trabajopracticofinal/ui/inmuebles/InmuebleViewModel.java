package com.example.trabajopracticofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticofinal.modelo.Inmueble;
import com.example.trabajopracticofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmuebleMutable;
    private Context context;
    private MutableLiveData<String> error;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Inmueble> getInmueble() {

        if(inmuebleMutable== null)
        {
            inmuebleMutable= new MutableLiveData<>();
        }
        return inmuebleMutable;
    }
    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public void cargarInmueble(Bundle bundle)
    {
        Inmueble inmueble= (Inmueble) bundle.getSerializable("inmueble");
        this.inmuebleMutable.setValue(inmueble);

    }

    public void editarInmueble(Inmueble inmu)
    {
        Call<Inmueble> inmueble= ApiClient.getMyApiInterface().EditarInmueble(inmu.getId(),ApiClient.obtenerToken(context));
        inmueble.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {

                if(response.isSuccessful()){
                    inmuebleMutable.setValue(response.body());
                }
                else {
                    error.setValue("No existen inmuebles");
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error",Toast.LENGTH_LONG).show();
            }
        });


    }
}
