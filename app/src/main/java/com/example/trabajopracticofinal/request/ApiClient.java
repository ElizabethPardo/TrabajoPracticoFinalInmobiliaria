package com.example.trabajopracticofinal.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.trabajopracticofinal.modelo.Contrato;
import com.example.trabajopracticofinal.modelo.Inmueble;
import com.example.trabajopracticofinal.modelo.Inquilino;
import com.example.trabajopracticofinal.modelo.Pago;
import com.example.trabajopracticofinal.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {

    private static final String PATH="http://192.168.0.12:45455/Api/";

    public  static  MyApiInterface myApiInterface;

    public static MyApiInterface getMyApiInterface() {

        Gson gson= new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        myApiInterface= retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInterface;
    }

    public interface MyApiInterface {
        @FormUrlEncoded
        @POST("Propietario/login")
        Call<String> Login(@Field("Usuario") String usuario, @Field("Clave") String clave);

        @GET("Propietario")
        Call<Propietario> MiPerfil(@Header("Authorization") String token);

        @PUT("Propietario")
        Call<Propietario> EditarPerfil(@Body Propietario propietario, @Header("Authorization") String token);

        @GET("Inmueble")
        Call<ArrayList<Inmueble>>ListaInmueble(@Header("Authorization") String token);

        @GET("Inquilino")
        Call<ArrayList<Inquilino>>ListaInquilino(@Header("Authorization") String token);

        @GET("Inmueble/{id}")
        Call<Inmueble>VerInmueble(@Path("id") int id,@Body Inmueble inmueble,@Header("Authorization") String token);

        @PUT("Inmueble/{id}")
        Call<Inmueble>EditarInmueble(@Path("id") int id, @Header("Authorization") String token);

        @GET("Contrato/GetPropietariosVigentes")
        Call<ArrayList<Contrato>>ListaContrato(@Header("Authorization") String token);

        @GET("Pago/{id}")
        Call<ArrayList<Pago>>PagosxContrato(@Path("id") int id,@Header("Authorization") String token);



    }

    public  static String obtenerToken(Context context)
    {
        String token;
        SharedPreferences sp=context.getSharedPreferences("datos.dat",0);
        return  sp.getString("Token", "Token no encontrado");


    }

}
