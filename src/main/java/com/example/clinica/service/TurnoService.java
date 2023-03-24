package com.example.clinica.service;

import com.example.clinica.dao.IDao;
import com.example.clinica.dao.TurnoDAOLista;
import com.example.clinica.domain.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoIDao;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public List<Turno> listarTurnos(){
        return turnoIDao.buscarTodos();
    }

    public void eliminarTurno(int id){
        turnoIDao.eliminar(id);
    }

    public Turno buscarTurno(int id){
        return turnoIDao.buscar(id);
    }

    public Turno actualizarTurno(Turno turno){
        return turnoIDao.actualizar(turno);
    }
}
