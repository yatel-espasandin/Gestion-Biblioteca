package com.gestion.prestamo.view;


import java.util.Scanner;

public class PrestamoView {
    Scanner scan = new Scanner(System.in);

    public int prestamoMenu(){
        System.out.println("""
                1- Generar prestamo.
                2- Consultar prestamo.
                3- Modificar prestamo existente.
                4- Generar devolucion.
                5- Eliminar prestamo.
                6- Salir
                """);
        System.out.println(">Ingrese una opcion.");
        int seleccion = scan.nextInt();
        scan.nextLine();
        return seleccion;
    }

    public int updateMenu(){
        System.out.println("""
                Menu de modificacion
                1- Agregar dias al prestamo.
                2- Quitar dias del prestamo.
                3- Salir.
                """);
        System.out.println(">Ingrese una opcion: ");
        int seleccion = scan.nextInt();
        scan.nextLine();
        return seleccion;
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
            case 4:
                System.out.println("Devolucio fuera de termino, se aplicara una multa.");
                break;
            case 5:
                System.out.println("Devolucion en termino, muchas gracias!");
                break;
        }
    }
}
