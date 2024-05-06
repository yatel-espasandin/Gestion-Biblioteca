package com.gestion.prestamo.controller;


import com.gestion.libro.controller.LibroController;
import com.gestion.libro.model.entity.Libro;
import com.gestion.prestamo.model.entity.Prestamo;
import com.gestion.prestamo.model.repository.PrestamoRepository;
import com.gestion.prestamo.view.PrestamoView;
import com.gestion.usuario.controller.UsuarioController;
import com.gestion.usuario.model.entity.Usuario;

import java.time.LocalDate;

public class PrestamoController {
    private UsuarioController userController;
    private LibroController libroController;
    private PrestamoRepository prestamoRepository;
    private PrestamoView prestamoView;

    public PrestamoController(UsuarioController userController, LibroController libroController, PrestamoRepository prestamoRepository, PrestamoView prestamoView) {
        this.userController = userController;
        this.libroController = libroController;
        this.prestamoRepository = prestamoRepository;
        this.prestamoView = prestamoView;
    }

    public Prestamo crearTotalPrestamo(){
        Usuario user = this.userController.crearUsuario();
        Libro libro = this.libroController.crearLibro();
        LocalDate fechaPrestado = LocalDate.now();
        LocalDate fechaDevolucion = null;
        if(user.getTypeUser().equalsIgnoreCase("estudiante")){
            LocalDate fechaDevolucion = fechaPrestado.plusDays(5);
        }else if(user.getTypeUser().equalsIgnoreCase("profesor")){
            LocalDate fechaDevolucion = fechaPrestado.plusDays(7);
        }
        return new Prestamo(user, libro, fechaPrestado, fechaDevolucion);
    }

    public Prestamo crearPrestamo(){
        Usuario userCheck = this.userController.getUsuario();
        if(userCheck!=null){
            Libro libroCheck = this.libroController.getLibro();
            if(libroCheck!=null){
                LocalDate fechaPrestado = LocalDate.now();
                LocalDate fechaDevolucion = null;
                if(userCheck.getTypeUser().equalsIgnoreCase("estudiante")){
                    LocalDate fechaDevolucion = fechaPrestado.plusDays(5);
                }else if(userCheck.getTypeUser().equalsIgnoreCase("profesor")){
                    LocalDate fechaDevolucion = fechaPrestado.plusDays(7);
                }
                return new Prestamo(userCheck, libroCheck, fechaPrestado, fechaDevolucion);
            }
        }
        return crearTotalPrestamo();
    }

    public void agregarPrestamo(){
        Prestamo prestamo = this.crearPrestamo();
        this.prestamoRepository.registrar(prestamo);
    }

    public Prestamo getPrestamo(){
        Usuario user = userController.getUsuario();
        return (Prestamo) prestamoRepository.consultar(user.getNombre());
    }

    public void updatePrestamoPlus(){
        Prestamo toUpdate = getPrestamo();
        Integer plus = prestamoView.updateDatePlus();
        Prestamo updated = new Prestamo(toUpdate.getUser(), toUpdate.getLibro(),toUpdate.getFechaPrestamo(), plus);
        boolean check = prestamoRepository.actualizar(toUpdate.getUser().getNombre(), updated);
        if(!check){
            prestamoView.mensajes(2);
        }else{
            prestamoView.mensajes(1);
        }
        break;
    }

    public void updatePrestamoMinus(){
        Prestamo toUpdate = getPrestamo();
        Integer minus = prestamoView.updateDateMinus();
        Prestamo updated = new Prestamo(toUpdate.getUser(), toUpdate.getLibro(),toUpdate.getFechaPrestamo(), minus);
        boolean check = prestamoRepository.actualizar(toUpdate.getUser().getNombre(), updated);
        if(!check){
            prestamoView.mensajes(2);
        }else{
            prestamoView.mensajes(1);
        }
    }

    public void updatePrestamo(){
        while(true){
            int opcion = prestamoView.updateMenu();
            switch(opcion){
                case 1:
                    updatePrestamoPlus()
                    break;
                case 2:
                    updatePrestamoMinus()
                    break;
                case 3:
                    return;
                default:
                    prestamoView.mensajes(3);
                    break;
            }
        }
    }

    public void deletePrestamo(){
        Prestamo delete = getPrestamo();
        if(delete.getFechaDevolucion().equals(LocalDate.now())){
            boolean check = prestamoRepository.eliminar(delete);
            if(!check){
                prestamoView.mensajes(2);
            }else{
                prestamoView.mensajes(1);
            }
        }else{

        }
    }
}

