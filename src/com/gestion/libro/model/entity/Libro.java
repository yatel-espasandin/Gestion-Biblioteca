package com.gestion.libro.model.entity;

public class Libro {
    private String titulo;
    private String autor;
    private Integer cantCopias;

    public Libro(String titulo, String autor, Integer cantCopias) {
        this.titulo = titulo;
        this.autor = autor;
        this.cantCopias = cantCopias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getCantCopias() {
        return cantCopias;
    }

    public void setCantCopias(Integer cantCopias) {
        this.cantCopias = cantCopias;
    }
}
