package com.example.trabajopracticofinal.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import com.example.trabajopracticofinal.modelo.Inquilino;
import com.example.trabajopracticofinal.modelo.Inmueble;
import com.example.trabajopracticofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {

    private Context context;
    private ArrayAdapter<Inmueble> adapter;
    private MutableLiveData<String> error;
    private MutableLiveData<ArrayList<Inquilino>> inquilinosMutable;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Inquilino>> getInquilinos() {

        if(inquilinosMutable == null)
        {
            inquilinosMutable= new MutableLiveData<>();
        }
        return inquilinosMutable;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public void cargarInquilinos(){

        Call<ArrayList<Inquilino>> inquilinos= ApiClient.getMyApiInterface().ListaInquilino(ApiClient.obtenerToken(context));
        inquilinos.enqueue(new Callback<ArrayList<Inquilino>>() {
            @Override
            public void onResponse(Call<ArrayList<Inquilino>> call, Response<ArrayList<Inquilino>> response) {
                if(response.isSuccessful()){
                    inquilinosMutable.postValue(response.body());
                }
                else {
                    error.setValue("No existen inquilinos");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inquilino>> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error",Toast.LENGTH_LONG).show();
            }
        });



    }
}