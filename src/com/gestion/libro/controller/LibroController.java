package com.gestion.libro.controller;

import com.gestion.libro.model.entity.Libro;
import com.gestion.libro.model.repository.LibroRepository;
import com.gestion.libro.view.LibroView;

import java.util.Scanner;

public class LibroController {
    private LibroRepository libroRepository;
    private LibroView libroView;

    public LibroController(LibroRepository libroRepository, LibroView libroView) {
        this.libroRepository = libroRepository;
        this.libroView = libroView;
    }

    Scanner scan = new Scanner(System.in);

    public void libroMenuController(){
        while(true) {
            int opcion = libroView.libroMenu();
            scan.nextLine();
            switch(opcion){
                case 1:
                    crearLibro();
                    break;
                case 2:
                    printLibro();
                    break;
                case 3:
                    updateLibro();
                    break;
                case 4:
                    deleteLibro();
                    break;
                case 5:
                    return;
                default:
                    libroView.mensajes(3);
                    break;
            }
        }
    }

    public void crearLibro(){
        Libro libro = this.libroView.crearLibro();
        this.libroRepository.registrar(libro);
    }

    public Libro getLibro(){
        String titulo = libroView.getByTitle();
        return (Libro) libroRepository.consultar(titulo);
    }

    public void printLibro(){
        Libro libro = getLibro();
        libroView.verLibro(libro);
    }

    public void updateLibro(){
        String libroUpdate = libroView.getByTitle();
        String nuevoT = libroView.updateTitle();
        String nuevoA = libroView.updateAuthor();
        Integer nuevoCopias = libroView.updateAmount();
        // TODO: podria agregarse logica para que la actualizacion de copias no solo sume copias si no que las reste, a travez de un switch donde el usuario elija.
        Libro temp = (Libro) libroRepository.consultar(libroUpdate);
        Integer tempAmount = temp.getCantCopias() + nuevoCopias;

        Libro toUpdate = new Libro(nuevoT, nuevoA, tempAmount);

        boolean check = libroRepository.actualizar(libroUpdate, toUpdate);
        if(!check){
            libroView.mensajes(2);
        }else{
            libroView.mensajes(1);
        }
    }

    public void deleteLibro(){
        Object libro = getLibro();
        boolean check = libroRepository.eliminar(libro);
        if(!check){
            libroView.mensajes(2);
        }else{
            libroView.mensajes(1);
        }
    }
}
