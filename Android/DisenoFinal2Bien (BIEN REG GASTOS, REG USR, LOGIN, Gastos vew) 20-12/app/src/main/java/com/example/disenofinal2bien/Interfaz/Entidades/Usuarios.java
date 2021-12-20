package com.example.disenofinal2bien.Interfaz.Entidades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class Usuarios extends ViewModel {

    private MutableLiveData<Integer> vmIdusuario = new MutableLiveData<Integer>();
    private MutableLiveData<String> vmNombreuser = new MutableLiveData<String>();
    private MutableLiveData<String> vmNombre = new MutableLiveData<String>();
    private MutableLiveData<String> vmDepartamento = new MutableLiveData<String>();
    private MutableLiveData<String> vmCorreo = new MutableLiveData<String>();
    private MutableLiveData<String> vmUltimaconexion = new MutableLiveData<String>();

    public LiveData<Integer> getIdusuario() {

        return vmIdusuario;
    }

    public void setIdusuario(Integer id) {

        vmIdusuario.setValue(id);
    }

    public LiveData<String> getNombre() {
        return vmNombre;
    }

    public void setNombre(String nombre) {
        vmNombre.setValue(nombre);
    }

    public LiveData<String> getNombreuser() {

        return vmNombreuser;
    }

    public void setNombreuser(String nombreuser) {

        vmNombreuser.setValue(nombreuser);
    }

    public LiveData<String> getDepartamento() {

    return vmDepartamento;

    }

    public void setDepartamento(String departamento) {

        vmDepartamento.setValue(departamento);
    }

    public LiveData<String> getCorreo() {

        return vmCorreo;
    }

    public void setCorreo(String correo) {

        vmCorreo.setValue(correo);
    }

    public LiveData<String> getDate() {

        return vmUltimaconexion;
    }

    public void setDate(String date) {

        vmUltimaconexion.setValue(date);
    }
}
