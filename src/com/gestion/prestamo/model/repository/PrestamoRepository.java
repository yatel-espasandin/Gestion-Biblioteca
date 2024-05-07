package com.gestion.prestamo.model.repository;

import com.gestion.prestamo.model.entity.Prestamo;
import com.interfaces.IRepository;

import java.util.ArrayList;

public class PrestamoRepository implements IRepository {
    private ArrayList<Prestamo> listPrestamo;

    public PrestamoRepository(){
        listPrestamo = new ArrayList<>();
    }

    public boolean returnCheck(Object objeto){
        return objeto != null;
    }

    @Override
    public void registrar(Object objeto) {
        this.listPrestamo.add((Prestamo) objeto);
    }

    @Override
    public Object consultar(String id) {
        for(Prestamo prestamos : listPrestamo){
            if(prestamos.getUser().getNombre().equalsIgnoreCase(id) || prestamos.getLibro().getTitulo().equalsIgnoreCase(id)){
                return prestamos;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(String id, Object objeto) {
        Object update = consultar(id);
        boolean check = returnCheck(update);
        if(check){
            int index = listPrestamo.indexOf((Prestamo)update);
            this.listPrestamo.set(index, (Prestamo) objeto);
        }
        return check;
    }

    @Override
    public boolean eliminar(Object objeto) {
        if(objeto!=null){
            listPrestamo.remove((Prestamo) objeto);
            return true;
        }
        return false;
    }
}
