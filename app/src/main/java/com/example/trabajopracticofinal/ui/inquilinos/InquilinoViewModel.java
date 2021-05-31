package com.example.trabajopracticofinal.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticofinal.modelo.Inquilino;

public class InquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Inquilino> inquilino;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inquilino> getInquilino() {

        if(inquilino == null)
        {
            inquilino= new MutableLiveData<>();
        }
        return inquilino;
    }

    public void cargarInquilino(Bundle bundle)
    {
        Inquilino inquilino= (Inquilino) bundle.getSerializable("inquilino");
        this.inquilino.setValue(inquilino);

    }
}
