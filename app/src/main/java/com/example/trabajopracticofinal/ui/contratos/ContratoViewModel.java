package com.example.trabajopracticofinal.ui.contratos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticofinal.modelo.Contrato;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> contrato;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getContrato() {

        if(contrato == null)
        {

            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void cargarContrato(Bundle arguments) {
    Contrato contrato= (Contrato) arguments.getSerializable("contrato");
        this.contrato.setValue(contrato);
    }
}