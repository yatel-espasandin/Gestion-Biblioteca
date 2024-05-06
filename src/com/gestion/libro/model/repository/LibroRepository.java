package com.gestion.libro.model.repository;
import com.gestion.libro.model.entity.Libro;
import com.interfaces.IRepository;

import java.util.ArrayList;

public class LibroRepository implements IRepository {
    private ArrayList<Libro> libroList;

    public LibroRepository() {
        this.libroList = new ArrayList<>();
    }

    public boolean returnCheck(Object objeto){
        return objeto != null;
    }

    @Override
    public void registrar(Object objeto) {
        libroList.add((Libro) objeto);
    }

    @Override
    public Object consultar(String id) {
        for(Libro libro : libroList){
            if(libro.getTitulo().equalsIgnoreCase(id)){
                return libro;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(String id, Object objeto) {
        Object update = consultar(id);
        boolean check = returnCheck(update);
        if(check){
            int index = libroList.indexOf((Libro)update);
            libroList.set(index, (Libro) objeto);
        }
        return check;
    }

    @Override
    public boolean eliminar(Object objeto) {
        if(objeto!=null){
            libroList.remove((Libro) objeto);
            return true;
        }
        return false;
    }
}
