package com.mng.sistemadeliveryandroid.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<LeerMapa> mutableLeerMapa;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }
    public LiveData<LeerMapa> getMutableLeerMapa(){
        if(mutableLeerMapa==null){
            mutableLeerMapa=new MutableLiveData<>();
        }
        return mutableLeerMapa;
    }

    public void leerMapa(){

        mutableLeerMapa.setValue(new LeerMapa(context));
    }


}