package com.brianfranco.Persona.modelo;

import com.brianfranco.Persona.Repositorio.PersonaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServiceTest {

    @MockBean
    PersonaRepositorio personaRepositorio;

    @DisplayName("Test cuando no encuentra una persona")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void buscarPersonaPorIdNoEncuentra(int id) {
        when(this.personaRepositorio.findById(id)).thenReturn(Optional.empty());
        Optional persona = personaRepositorio.findById(id);
        String resultado;
        if(persona.isPresent()){
            resultado = "Encontrado";
        }else {
            resultado = "No encontrado";
        }
        assertEquals("No encontrado", resultado);
    }

    @Mock
    Persona persona = new Persona();

    @DisplayName("Test cuando encuentra una persona")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void buscarPersonaPorIdEncuentra(int id) {
        Persona Persona;
        when(this.personaRepositorio.findById(id)).thenReturn(Optional.of(persona));
        Optional persona = personaRepositorio.findById(id);
        String resultado;
        if(persona.isPresent()){
            resultado = "Encontrado";
        }else {
            resultado = "No encontrado";
        }
        assertEquals("Encontrado", resultado);
    }
}