package com.mng.sistemadeliveryandroid.modelo;

import java.io.Serializable;

public class User implements Serializable {

    private String Usuario;
    private String Password;


    public User(){}
    public User(String Usuario, String Password) {

        this.Usuario = Usuario;
        this.Password = Password;

    }


    public String getEmail() {
        return Usuario;
    }

    public void setEmail(String email) {
        this.Usuario = email;
    }

    public String getContraseña() {
        return Password;
    }

    public void setContraseña(String Password) {
        this.Password = Password;
    }


}
