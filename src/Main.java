import com.gestion.libro.controller.LibroController;
import com.gestion.libro.model.repository.LibroRepository;
import com.gestion.libro.view.LibroView;
import com.gestion.prestamo.controller.PrestamoController;
import com.gestion.prestamo.model.repository.PrestamoRepository;
import com.gestion.prestamo.view.PrestamoView;
import com.gestion.usuario.controller.UsuarioController;
import com.gestion.usuario.model.repository.UsuarioRepository;
import com.gestion.usuario.view.UsuarioView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioRepository userRepository = new UsuarioRepository();
        UsuarioView userView = new UsuarioView();
        LibroRepository libroRepository = new LibroRepository();
        LibroView libroView = new LibroView();
        PrestamoRepository prestamoRepository = new PrestamoRepository();
        PrestamoView prestamoView = new PrestamoView();

        UsuarioController userController = new UsuarioController(userRepository, userView);
        LibroController libroController = new LibroController(libroRepository, libroView);
        PrestamoController prestamoController = new PrestamoController(userController, libroController, prestamoRepository, prestamoView);

        mainMenu(userController, libroController, prestamoController);
    }

//TODO: AGREGAR VISUALIZACION DE REPOSITORIOS EN TODAS LAS ENTIDADES

    public static void mainMenu(UsuarioController userController,LibroController libroController,PrestamoController prestamoController){
        Scanner scan = new Scanner(System.in);

        int opcion;
        while(true){
            System.out.println("""
                    \t\t--------------
                    \t\tMENU PRINCIPAL
                    \t\t--------------
                    
                    1- Menu usuarios.
                    2- Menu libros.
                    3- Menu prestamos.
                    """);
            System.out.println(">Ingrese una opcion.");
            opcion = scan.nextInt();
            switch(opcion){
                case 1:
                    userController.userMenuController();
                    break;
                case 2:
                    libroController.libroMenuController();
                    break;
                case 3:
                    prestamoController.menuPrestamo();
                    break;
            }
        }

    }
}