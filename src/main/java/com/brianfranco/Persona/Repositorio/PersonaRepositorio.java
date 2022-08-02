package com.brianfranco.Persona.Repositorio;

import com.brianfranco.Persona.modelo.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends CrudRepository<Persona, Integer> {
}
