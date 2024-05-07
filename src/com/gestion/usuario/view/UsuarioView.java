package com.gestion.usuario.view;
import com.gestion.usuario.model.entity.Usuario;
import java.util.Scanner;

public class UsuarioView {
    Scanner scan = new Scanner(System.in);

    public Integer userMenu(){
        System.out.println("""
                > 1- Crear usuario.
                > 2- Ver usuario.
                > 3- Modificar usuario (todos los campos).
                > 4- Eliminar usuario.
                > 5- Salir
                """);
        System.out.println("Seleccione una opcion: ");
        int seleccion = scan.nextInt();
        scan.nextLine();
        return seleccion;
    }

    public Usuario crearUsuario(){
        System.out.println(">Ingrese el nombre: ");
        String nombre = scan.nextLine();


        System.out.println(">Ingrese el apellido: ");
        String apellido = scan.nextLine();


        System.out.println(">Ingrese el tipo de Usuario (estudiante/profesor): ");
        String userType = null;
        while (userType == null || (!userType.equalsIgnoreCase("profesor") && !userType.equalsIgnoreCase("estudiante"))) {
            String temp = scan.nextLine();
            if (temp.equalsIgnoreCase("profesor") || temp.equalsIgnoreCase("estudiante")) {
                userType = temp;
            } else {
                System.out.println("Tipo de usuario invalido, intente nuevamente.");
            }
        }
        return new Usuario(nombre, apellido, userType);
    }

    public void printUsuario(Usuario user){
        System.out.println("Nombre: " + user.getNombre());
        System.out.println("Apellido: " + user.getApellido());
        System.out.println("ID: " + user.getIdentificacion());
        System.out.println("Tipo de usuario: " + user.getTypeUser());
    }

    public String getByName(){
        System.out.println(">Ingrese el nombre del usuario que desea buscar: ");
        return scan.nextLine();
    }

    public String updateName(){
        System.out.println(">Ingrese el nuevo nombre: ");
        return scan.nextLine();
    }

    public String updateApellido(){
        System.out.println(">Ingrese el nuevo apellido: ");
        return scan.nextLine();
    }

    public String updateUserType(){
        System.out.println(">Ingrese el nuevo tipo de usuario: ");
        return scan.nextLine();
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
