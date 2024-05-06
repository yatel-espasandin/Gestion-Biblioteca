package com.gestion.usuario.model.entity;

import com.gestion.persona.Persona;

public class Usuario extends Persona {
    private String typeUser;

    public Usuario(String nombre, String apellido, String typeUser) {
        super(nombre, apellido);
        this.typeUser = typeUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
