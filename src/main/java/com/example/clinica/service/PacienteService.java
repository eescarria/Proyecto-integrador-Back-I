package com.example.clinica.service;
import com.example.clinica.dao.PacienteDAOH2;
import com.example.clinica.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private PacienteDAOH2 pacienteIDao;

    @Autowired
    public PacienteService(PacienteDAOH2 pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }

    public Paciente buscarPaciente(int id){
        return pacienteIDao.buscar(id);
    }

    public void eliminarPaciente(int id){
        pacienteIDao.eliminar(id);
    }

    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteIDao.actualizar(paciente);
    }

    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }

    public Paciente buscarXEmail(String email){
        return pacienteIDao.buscarXCriterioString(email);
    }


}
