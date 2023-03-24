package com.example.clinica.controller;

import com.example.clinica.domain.Odontologo;
import com.example.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable int id){
        return odontologoService.buscarOdontologo(id);
    }

    @PostMapping
    public Odontologo registrarPaciente(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @PutMapping
    public Odontologo actualizarPaciente(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping("/{id}")
//    public void eliminarOdontologo(@PathVariable int id){
//        odontologoService.eliminarOdontologo(id);
//    }
    public ResponseEntity eliminarOdontologo(@PathVariable int id){
        if(odontologoService.buscarOdontologo(id) != null){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.buscarTodos();
    }
}
