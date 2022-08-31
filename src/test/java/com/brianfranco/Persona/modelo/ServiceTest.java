package com.brianfranco.Persona.modelo;

import com.brianfranco.Persona.Repositorio.PersonaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ServiceTest {

    @InjectMocks
    private Service service;

    @Mock
    PersonaRepositorio personaRepositorio;
    @Test
    void buscarPersonaPorId() {
        //Given
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");

        //When
        when(personaRepositorio.findById(anyInt())).thenReturn(Optional.of(persona));

        //Then
        Optional<Persona> personaOptional = personaRepositorio.findById(1);
        Assertions.assertNotNull(personaOptional.get());
        Assertions.assertEquals(persona.idPersona, 1);
    }

    @Test
    void whenNoEncuentraBuscarPersonaPorId() {
        //Given
        Optional<Persona> persona = Optional.empty();

        //When
        when(personaRepositorio.findById(anyInt())).thenReturn(persona);

        //Then
        Optional<Persona> personaOptional = personaRepositorio.findById(1);
        Assertions.assertEquals(Optional.empty(), personaOptional);
    }

    @Test
    void guardarPersona() {
        //Given
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");

        //When
        when(personaRepositorio.save(any(Persona.class))).thenReturn(persona);

        //Then
        Persona personaNuevo = personaRepositorio.save(persona);
        Assertions.assertNotNull(personaNuevo);
        Assertions.assertEquals(personaNuevo.idPersona, 1);
        Assertions.assertEquals(personaNuevo.nombre, "Brian");
    }

    @Test
    void buscarTodasLasPersonas() {
        //Given
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");
        persona.setDireccion("Avenida siempre viva 124");
        Iterable<Persona> personaIterableResponse = List.of(persona);

        //When
        when(personaRepositorio.findAll()).thenReturn(personaIterableResponse);

        //Then
        Iterable<Persona> personasIterable = personaRepositorio.findAll();
        personasIterable.forEach(p ->
                {
                    p.setDireccion(p.getDireccion().toUpperCase());
                }
        );
        List<Persona> personaList = new ArrayList<>();
        personasIterable.forEach(personaList::add);

        Assertions.assertEquals(personaList.get(0).direccion, "AVENIDA SIEMPRE VIVA 124");
        //Assertions.assertEquals(personaList.get(0).direccion, "Avenida siempre viva 124");
    }
}