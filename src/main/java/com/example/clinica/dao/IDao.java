package com.example.clinica.dao;

import java.util.List;

public interface IDao <T>{
    T guardar(T t);
    T buscar(int id);
    void eliminar(int id);
    T actualizar(T t);
    List<T> buscarTodos();
    T buscarXCriterioString(String criterio);
}
