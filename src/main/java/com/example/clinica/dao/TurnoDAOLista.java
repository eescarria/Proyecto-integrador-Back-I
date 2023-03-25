package com.example.clinica.dao;

import com.example.clinica.domain.Turno;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDAOLista implements IDao<Turno>{

    private static final Logger logger = Logger.getLogger(TurnoDAOLista.class);
    private List<Turno> turnos = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        logger.debug("Se ha agregado un turno");
        return turno;
    }

    @Override
    public Turno buscar(int id) {
        Turno turnoBuscado = null;
        for (Turno turno : turnos) {
            if(turno.getId() == id){
                turnoBuscado = turno;
            }
        }
        return turnoBuscado;
    }

    @Override
    public void eliminar(int id) {
        Turno buscado = buscar(id);
        if(buscado!=null){
            turnos.remove(buscado);
            logger.debug("Se ha eliminado el turno con id: " + id);
        }else {
            logger.debug("No se encontró ese turno");
        }
    }

    @Override
    public Turno actualizar(Turno turno) {
//        int indice = turnos.indexOf(turno);
//        turnos.set(indice, turno);
//        return buscar(turno.getId());
        eliminar(turno.getId());
        return guardar(turno);

    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarXCriterioString(String criterio) {
        return null;
    }
}
