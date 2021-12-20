package com.example.disenofinal2bien.Interfaz.Inicio;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewInicio extends ViewModel {

    Button b1;

    private MutableLiveData<String> mText;

    public ViewInicio() {
        mText = new MutableLiveData<>();
        mText.setValue("Movies del inicio");

    }

    public LiveData<String> getText() {
        return mText;
    }
}