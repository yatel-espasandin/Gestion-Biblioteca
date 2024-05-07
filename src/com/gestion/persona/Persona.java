package com.gestion.persona;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private static Integer contador = 0;
    private Integer identificacion;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = ++contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

}
