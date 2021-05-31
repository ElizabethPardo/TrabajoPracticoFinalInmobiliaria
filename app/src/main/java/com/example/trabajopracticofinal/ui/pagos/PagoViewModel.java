package com.example.trabajopracticofinal.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticofinal.modelo.Contrato;
import com.example.trabajopracticofinal.modelo.Pago;
import com.example.trabajopracticofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> error;
    private MutableLiveData<ArrayList<Pago>> pagosMutable;

    public PagoViewModel(@NonNull Application application) {
        super(application);
       context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Pago>> getPagos()
    {
        if(pagosMutable == null)
        {
            pagosMutable= new MutableLiveData<>();
        }
        return pagosMutable;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }


    public void cargarPagos(int id) {

       Log.d("Salida",id+"");
        Call<ArrayList<Pago>> pagos= ApiClient.getMyApiInterface().PagosxContrato(id,ApiClient.obtenerToken(context));

        pagos.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful())
                {
                 if(response.body().size() != 0) {
                     pagosMutable.postValue(response.body());
                 }
                 else{
                      error.postValue("No tiene pagos");
                 }
                }
                else{
                    error.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {

                error.setValue("No existen Pagos");

            }
        });

    }


}