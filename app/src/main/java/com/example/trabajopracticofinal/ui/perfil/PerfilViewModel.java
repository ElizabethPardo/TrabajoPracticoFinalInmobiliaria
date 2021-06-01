package com.example.trabajopracticofinal.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticofinal.modelo.Propietario;
import com.example.trabajopracticofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> error;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<Propietario> getPropietarioMutable() {
        if(propietarioMutable== null)
        {
            propietarioMutable= new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void recuperarPropietario()
    {

        Call<Propietario> propietario=ApiClient.getMyApiInterface().MiPerfil(ApiClient.obtenerToken(context));
        propietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                  }
                else{
                    error.setValue("Perfil no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {


                Toast.makeText(context,"Ha ocurrido un error"+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void editarPerfil(Propietario prop)
    {

        Call<Propietario> propietarios=ApiClient.getMyApiInterface().EditarPerfil(prop,ApiClient.obtenerToken(context));
        Log.d("salida",prop.getApellido());
        propietarios.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                Log.d("salida",response.body().getClave());
                if(response.isSuccessful()){
                    if(response.body() != null) {
                        propietarioMutable.setValue(response.body());
                        Toast.makeText(context,"Datos actualizados!",Toast.LENGTH_LONG).show();
                    }
                    else{

                        Toast.makeText(context,"No hay datos para actualizar",Toast.LENGTH_LONG).show();
                    }


                }
                else{
                    error.setValue("No se pudo modificar el perfil");
                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error"+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}