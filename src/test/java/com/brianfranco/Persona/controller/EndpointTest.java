package com.brianfranco.Persona.controller;

import com.brianfranco.Persona.modelo.Persona;
import com.brianfranco.Persona.modelo.Service;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class EndpointTest {

    private MockMvc mvc;
    @InjectMocks
    private Endpoint endpoint;

    @Mock
    private Service service;

    @BeforeEach
    void setUp() {
        mvc = standaloneSetup(endpoint).build();
    }

    /*@Test
    void buscarPersonaPorId() {
        Persona persona = new Persona();
        when(service.buscarPersonaPorId(anyInt())).thenReturn(persona);
        ResponseEntity<Persona> response = endpoint.buscarPersonaPorId(123);
        Assertions.assertNotNull(response);

    }*/

    @Test
    void buscarPersonaPorId() throws Exception {
        //Given
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");
        persona.setDireccion("Avenida siempre viva 124");
        persona.setTipoDocumento(1);
        persona.setDocumento(10971234);
        persona.setTelefono("3117894561");

        //When
        when(service.buscarPersonaPorId(anyInt())).thenReturn(persona);

        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/persona/1")
                                //.param("idPersona", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPersona", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is("Brian")))
        ;
    }

    /*@Test
    void whenPersonaNullbuscarPersonaPorId() {
        Persona persona = null;
        when(service.buscarPersonaPorId(anyInt())).thenReturn(persona);
        ResponseEntity<Persona> responseEntity = endpoint.buscarPersonaPorId(123);
        Assertions.assertNull(responseEntity.getBody());
    }*/

    @Test
    void whenPersonaNullbuscarPersonaPorId() throws Exception {
        //Given
        Persona persona = null;

        //When
        when(service.buscarPersonaPorId(anyInt())).thenReturn(persona);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/persona/3")
                        //.param("idPersona", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist())
                .andDo(MockMvcResultHandlers.print())
        ;
    }

    /*@Test
    void guardarPersona() {
        Persona persona = new Persona();
        when(service.guardarPersona(persona)).thenReturn(persona);
        ResponseEntity<Persona> responseEntity = endpoint.guardarPersona(persona);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }*/

    @Test
    void guardarPersona() throws Exception {
        //Given
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");
        persona.setDireccion("Avenida siempre viva 124");
        persona.setTipoDocumento(1);
        persona.setDocumento(10971234);
        persona.setTelefono("3112589324");

        //When
        when(service.guardarPersona(any(Persona.class))).thenReturn(persona);

        mvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/persona/guardar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nombre\": \"Brian\", \"tipoDocumento\": 1, \"documento\": 10971284, \"direccion\": \"Avenida siempre viva 124\", \"telefono\": \"3112589324\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPersona", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is("Brian")))
                .andDo(MockMvcResultHandlers.print())
        ;

        verify(service).guardarPersona(any(Persona.class));

    }

    /*@Test
    void listarTodas() {
        List<Persona> personaList = new ArrayList<Persona>();
        when(service.buscarTodasLasPersonas()).thenReturn(personaList);
        ResponseEntity<Persona> responseEntity = endpoint.listarTodas();
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
    }*/

    @Test
    void listarTodas() throws Exception {
        //Given
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");
        List<Persona> personaList = new ArrayList<Persona>();
        personaList.add(persona);

        //When
        when(service.buscarTodasLasPersonas()).thenReturn(personaList);

        //Then
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/persona/todas/")
                        .header("ClientSecret", "asdf1234")
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idPersona", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", Matchers.is("Brian")));
    }
}