package com.example.clinica.service;

import com.example.clinica.dao.IDao;
import com.example.clinica.dao.OdontologoDAOH2;
import com.example.clinica.domain.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo>  odontologoIDao;

    @Autowired
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> buscarTodos(){
        return odontologoIDao.buscarTodos();
    }

    public Odontologo buscarOdontologo(int id){
        return odontologoIDao.buscar(id);
    }

    public void eliminarOdontologo(int id){
        odontologoIDao.eliminar(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoIDao.actualizar(odontologo);
    }
}
