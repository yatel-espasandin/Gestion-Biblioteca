package com.gestion.prestamo.view;


import java.util.Scanner;

public class PrestamoView {
    Scanner scan = new Scanner(System.out);

    public int updateMenu(){
        System.out.println("""
                Menu de modificacion
                1- Agregar dias al prestamo.
                2- Quitar dias del prestamo.
                3- Salir.
                """);
        System.out.println(">Ingrese una opcion: ");
        return scan.nextInt();
    }

    public Integer updateDatePlus(){
        System.out.println(">Ingrese la cantidad de dias que desea agregar.");
        return scan.nextInt();
    }

    public Integer updateDateMinus(){
        System.out.println(">Ingrese la cantidad de dias a quitar.");
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
