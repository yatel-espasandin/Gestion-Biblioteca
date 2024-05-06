package com.gestion.usuario.model.repository;

import com.gestion.usuario.model.entity.Usuario;
import com.interfaces.IRepository;

import java.util.ArrayList;

public class UsuarioRepository implements IRepository {

    private ArrayList<Usuario> userList;

    public UsuarioRepository(){
        this.userList = new ArrayList<>();
    }

    @Override
    public void registrar(Object objeto) {
            this.userList.add((Usuario)objeto);
    }

    @Override
    public Object consultar(String id) {
        for(Usuario user : userList){
            if(user.getNombre().equalsIgnoreCase(id)){
                return user;
            }
        }
        return null;
    }

    public boolean returnCheck(Object objeto){
        return objeto != null;
    }

    @Override
    public boolean actualizar(String id, Object objeto) {
        Object update = consultar(id);
        boolean check = returnCheck(update);
        if(check) {
            int index = userList.indexOf((Usuario) update);
            this.userList.add(index, (Usuario) objeto);
        }
        return check;
    }

    @Override
    public boolean eliminar(Object objeto) {
        if(objeto!=null) {
            userList.remove((Usuario) objeto);
            return true;
        }
        return false;
    }
}
