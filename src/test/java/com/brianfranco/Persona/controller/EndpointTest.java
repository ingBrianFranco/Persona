package com.brianfranco.Persona.controller;

import com.brianfranco.Persona.modelo.Persona;
import com.brianfranco.Persona.modelo.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EndpointTest {

    @InjectMocks
    private Endpoint endpoint;

    @Mock
    private Service service;

    @Test
    void buscarPersonaPorId() {
        Persona persona = new Persona();
        when(service.buscarPersonaPorId(anyInt())).thenReturn(persona);
        Object response = endpoint.buscarPersonaPorId(123);
        Assertions.assertNotNull(response);

    }

    @Test
    void whenPersonaNullbuscarPersonaPorId() {
        Persona persona = null;
        when(service.buscarPersonaPorId(anyInt())).thenReturn(persona);
        Object response = endpoint.buscarPersonaPorId(123);
        ResponseEntity<Persona> responseEntity = (ResponseEntity)response;
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    void guardarPersona() {
        Persona persona = new Persona();
        when(service.guardarPersona(persona)).thenReturn(persona);
        Object response = endpoint.guardarPersona(persona);
        ResponseEntity<Persona> responseEntity = (ResponseEntity)response;
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void listarTodas() {
        List<Persona> personaList = new ArrayList<Persona>();
        when(service.buscarTodasLasPersonas()).thenReturn(personaList);
        Object response = endpoint.listarTodas();
        ResponseEntity<Persona> responseEntity = (ResponseEntity)response;
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
    }
}