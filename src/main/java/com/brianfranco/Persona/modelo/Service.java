package com.brianfranco.Persona.modelo;

import com.brianfranco.Persona.Repositorio.PersonaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Persona guardarPersona(Persona persona) throws Exception{
        //Comprobar que la persona no exista en el sistema
        //Guardar la persona
        //Responder si la persona ha sido o no guardada
        //try {
            Persona personaNuevo = personaRepositorio.save(persona);
            return personaNuevo;
        /*}catch (Exception e){
            return null;
        }*/
    }

    public void eliminarPersonaPorId(int idPersona){
        personaRepositorio.deleteById(idPersona);
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
