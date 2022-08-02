package com.brianfranco.Persona.controller;

import com.brianfranco.Persona.modelo.Persona;
import com.brianfranco.Persona.modelo.Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("persona")
@AllArgsConstructor
public class Endpoint {

    @Autowired
    Service service;

    @GetMapping("/{idPersona}")
    public Object buscarPersonaPorId(@PathVariable int idPersona){
        Persona persona = service.buscarPersonaPorId(idPersona);
        if(persona == null){
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.FOUND);
    }

    @PostMapping("/guardar")
    public Object guardarPersona(@RequestBody Persona persona){
        //Llamar a nuestro modelo para que ejecute la lógica de negocio
        //Retornar la respuesta que nos entrega la lógica de negocio
        return new ResponseEntity<Persona>(
                service.guardarPersona(persona),
                HttpStatus.CREATED);
    }

    @GetMapping("/todas")
    public Object listarTodas(){
        return new ResponseEntity(service.buscarTodasLasPersonas(), HttpStatus.FOUND);
    }


}
