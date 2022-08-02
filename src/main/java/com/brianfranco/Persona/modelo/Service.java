package com.brianfranco.Persona.modelo;

import com.brianfranco.Persona.Repositorio.PersonaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {

    @Autowired
    PersonaRepositorio personaRepositorio;

    public Persona buscarPersonaPorId(int id){
        Optional<Persona> persona = personaRepositorio.findById(id);
        if(persona.isPresent()){
            return persona.get();
        }else {
            return null;
        }
    }

    public Persona guardarPersona(Persona persona){
        //Comprobar que la persona no exista en el sistema
        //Guardar la persona
        //Responder si la persona ha sido o no guardada
        Persona personaNuevo = personaRepositorio.save(persona);
        return persona;
    }

    public List<Persona> buscarTodasLasPersonas() {
        Iterable<Persona> personasIterable = personaRepositorio.findAll();
        personasIterable.forEach(persona ->
                {
                    persona.setDireccion(persona.getDireccion().toUpperCase());
                }
                );
        List<Persona> personaList = new ArrayList<>();
        personasIterable.forEach(personaList::add);
        return personaList;
    }

}
