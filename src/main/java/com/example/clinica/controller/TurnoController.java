package com.example.clinica.controller;

import com.example.clinica.domain.Turno;
import com.example.clinica.service.OdontologoService;
import com.example.clinica.service.PacienteService;
import com.example.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;


    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
      ResponseEntity<Turno> respuesta;
      if(pacienteService.buscarPaciente(turno.getPaciente().getId()) != null &&
      odontologoService.buscarOdontologo(turno.getOdontologo().getId()) != null){
          respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));
      }else {
          respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        ResponseEntity<List<Turno>> respuesta;
        if(!turnoService.listarTurnos().isEmpty()){
            respuesta = ResponseEntity.ok(turnoService.listarTurnos());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id){
        ResponseEntity<Turno> respuesta;

        if(turnoService.buscarTurno(id) != null){
            respuesta = ResponseEntity.ok(turnoService.buscarTurno(id));
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return respuesta;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTurno(@PathVariable int id){
        ResponseEntity respuesta;

        if(turnoService.buscarTurno(id) != null){
            turnoService.eliminarTurno(id);
            respuesta = ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;

        if(turnoService.buscarTurno(turno.getId()) != null){
            respuesta = ResponseEntity.ok(turnoService.actualizarTurno(turno));
        }else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

}
