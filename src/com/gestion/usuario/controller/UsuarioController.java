package com.gestion.usuario.controller;

import com.gestion.usuario.model.entity.Usuario;
import com.gestion.usuario.model.repository.UsuarioRepository;
import com.gestion.usuario.view.UsuarioView;

public class UsuarioController {
    private UsuarioRepository userRepository;
    private UsuarioView userView;

    public UsuarioController(UsuarioRepository userRepository, UsuarioView userView) {
        this.userRepository = userRepository;
        this.userView = userView;
    }

    public void userMenuController(){
        while(true){
            Integer opcion = userView.userMenu();
            switch(opcion){
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    printUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    return;
                default:
                    userView.mensajes(3);
                    break;
            }
        }
    }

    public void crearUsuario(){
        Usuario user = this.userView.crearUsuario();
        this.userRepository.registrar(user);
    }

    public Usuario getUsuario(){
        String name = userView.getByName();
        return (Usuario) userRepository.consultar(name);
    }

    public void printUser(){
        Usuario toPrint = getUsuario();
        userView.printUsuario(toPrint);
    }

    public void updateUser(){
        String updateUser = userView.getByName();
        String nuevoN = userView.updateName();
        String nuevoE = userView.updateApellido();
        String nuevoT = userView.updateUserType();
        Usuario updated = new Usuario(nuevoN, nuevoE, nuevoT);

        boolean check = userRepository.actualizar(updateUser, updated);
        if(!check){
            userView.mensajes(2);
        }else{
            userView.mensajes(1);
        }
    }

    public void deleteUser(){
        Object userObject = getUsuario();//TODO: OPCION DE BORRADO CON EXPIRACION DE TIEMPO (LISTO) - ANALIZAR LOGICA PARA ELIMINACION POR PRESTAMO
        boolean check = userRepository.eliminar(userObject);
        if(!check){
            userView.mensajes(2);
        }else{
            userView.mensajes(1);
        }
    }
}
