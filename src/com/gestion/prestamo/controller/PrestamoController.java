package com.gestion.prestamo.controller;


import com.gestion.libro.controller.LibroController;
import com.gestion.libro.model.entity.Libro;
import com.gestion.libro.model.repository.LibroRepository;
import com.gestion.libro.view.LibroView;
import com.gestion.prestamo.model.entity.Prestamo;
import com.gestion.prestamo.model.repository.PrestamoRepository;
import com.gestion.prestamo.view.PrestamoView;
import com.gestion.usuario.controller.UsuarioController;
import com.gestion.usuario.model.entity.Usuario;
import com.gestion.usuario.model.repository.UsuarioRepository;
import com.gestion.usuario.view.UsuarioView;

import java.time.LocalDate;
import java.util.Scanner;

public class PrestamoController {
    private UsuarioController userController;
    private LibroController libroController;
    UsuarioRepository userRepository;
    UsuarioView userView;
    LibroRepository libroRepository;
    LibroView libroView;
    PrestamoRepository prestamoRepository;
    PrestamoView prestamoView;

    public PrestamoController(UsuarioController userController, LibroController libroController, UsuarioRepository userRepository, UsuarioView userView, LibroRepository libroRepository, LibroView libroView, PrestamoRepository prestamoRepository, PrestamoView prestamoView) {
        this.userController = userController;
        this.libroController = libroController;
        this.userRepository = userRepository;
        this.userView = userView;
        this.libroRepository = libroRepository;
        this.libroView = libroView;
        this.prestamoRepository = prestamoRepository;
        this.prestamoView = prestamoView;
    }

    public PrestamoController(UsuarioController userController, LibroController libroController, PrestamoRepository prestamoRepository, PrestamoView prestamoView) {
        this.userController = userController;
        this.libroController = libroController;
        this.prestamoRepository = prestamoRepository;
        this.prestamoView = prestamoView;
    }

    Scanner scan = new Scanner(System.in);

    public void menuPrestamo(){
        while(true){
            int opcion = prestamoView.prestamoMenu();

            switch (opcion){
                case 1:
                    agregarPrestamo();
                    break;
                case 2:
                    getPrestamo();
                    break;
                case 3:
                    updatePrestamo();
                    break;
                case 4:
                    devolverPrestamo();
                    break;
                case 5:
                    deletePrestamo();
                    break;
                case 6:
                    return;
                default:
                    prestamoView.mensajes(3);
            }
        }
    }

    public Prestamo crearTotalPrestamo(){
        Usuario user = this.userView.crearUsuario();
        Libro libro = this.libroView.crearLibro();
        LocalDate fechaPrestado = LocalDate.now();
        LocalDate fechaDevolucion = null;
        if(user.getTypeUser().equalsIgnoreCase("estudiante")){
            fechaDevolucion = fechaPrestado.plusDays(5);
        }else if(user.getTypeUser().equalsIgnoreCase("profesor")){
            fechaDevolucion = fechaPrestado.plusDays(7);
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
                    fechaDevolucion = fechaPrestado.plusDays(5);
                }else if(userCheck.getTypeUser().equalsIgnoreCase("profesor")){
                    fechaDevolucion = fechaPrestado.plusDays(7);
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
        int suma = prestamoView.updateDatePlus();
        LocalDate temp = toUpdate.getFechaDevolucion().plusDays(suma);
        Prestamo updated = new Prestamo(toUpdate.getUser(), toUpdate.getLibro(),toUpdate.getFechaPrestamo(), temp);
        boolean check = prestamoRepository.actualizar(toUpdate.getUser().getNombre(), updated);
        if(!check){
            prestamoView.mensajes(2);
        }else{
            prestamoView.mensajes(1);
        }
    }

    public void updatePrestamoMinus(){
        Prestamo toUpdate = getPrestamo();
        Integer resta = prestamoView.updateDateMinus();
        LocalDate temp = toUpdate.getFechaDevolucion().plusDays(resta);
        Prestamo updated = new Prestamo(toUpdate.getUser(), toUpdate.getLibro(),toUpdate.getFechaPrestamo(), temp);
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
                    updatePrestamoPlus();
                    break;
                case 2:
                    updatePrestamoMinus();
                    break;
                case 3:
                    return;
                default:
                    prestamoView.mensajes(3);
                    break;
            }
        }
    }

    public boolean checkDate(Prestamo prestamo) {
        LocalDate dateCheck = prestamo.getFechaDevolucion();
        if(dateCheck.isBefore(LocalDate.now())){
            return false;
        }else
            return true;
    }

    public void devolverPrestamo(){
        Prestamo delete = getPrestamo();
        boolean date = checkDate(delete);
        if(date){
            prestamoView.mensajes(5);
            boolean check = prestamoRepository.eliminar(delete);
            if(!check){
                prestamoView.mensajes(2);
            }else
                prestamoView.mensajes(1);
        }else{
            prestamoView.mensajes(4);
            boolean check = prestamoRepository.eliminar(delete);
            if(!check){
                prestamoView.mensajes(2);
            }else
                prestamoView.mensajes(1);
        }
    }

    public void deletePrestamo(){
        Prestamo delete = getPrestamo();
        boolean check = prestamoRepository.eliminar(delete);
        if(!check){
            prestamoView.mensajes(2);
        }else
            prestamoView.mensajes(1);
    }
}

