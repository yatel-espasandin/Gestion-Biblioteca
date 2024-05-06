package com.gestion.libro.view;

import com.gestion.libro.model.entity.Libro;

import java.util.Scanner;

public class LibroView {
    Scanner scan = new Scanner(System.out);

    public Integer libroMenu(){
        System.out.println("""
                >1- Crear libro.
                >2- Ver libro.
                >3- Actualizar libro (todos los campos).
                >4- Eliminar libro.
                >5- Salir.
                """);
        System.out.println("Seleccione una opcion: ");
        return scan.nextInt();
    }

    public Libro crearLibro(){
        System.out.println(">Titulo: ");
        String titulo = scan.nextLine();

        System.out.println(">Autor: ");
        String autor = scan.nextLine();

        System.out.println(">Cantidad de copias:");
        Integer cantCopias = scan.nextInt();

        return new Libro(titulo, autor, cantCopias);
    }

    public void verLibro(Libro libro){
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Cantidad de copias:" + libro.getCantCopias());
    }

    public String getByTitle(){
        System.out.println(">Ingrese el titulo a buscar: ");
        return scan.nextLine();
    }

    public String updateTitle(){
        System.out.println(">Ingrese el titulo: ");
        return scan.nextLine();
    }

    public String updateAuthor(){
        System.out.println(">Ingrese el autor: ");
        return scan.nextLine();
    }

    public Integer updateAmount(){
        System.out.println(">Ingrese la cantidad de copias a agregar: ");
        return scan.nextInt();
    }

    public void mensajes(int opcion){
        switch(opcion) {
            case 1:
                System.out.println("Actualizacion exitosa.");
                break;
            case 2:
                System.out.println("Actualizacion fallida.");
            case 3:
                System.out.println("Opcion invalida, intente nuevamente.");
                break;
        }
    }
}
