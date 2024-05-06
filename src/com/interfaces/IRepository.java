package com.interfaces;

public interface IRepository {
    void registrar(Object objeto);
    Object consultar(String id);
    boolean actualizar(String id, Object objeto);
    boolean eliminar(Object objeto);
}
