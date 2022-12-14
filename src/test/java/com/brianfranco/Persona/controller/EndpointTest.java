package com.brianfranco.Persona.controller;

import com.brianfranco.Persona.data.FactoryPersonaTestData;
import com.brianfranco.Persona.modelo.Persona;
import com.brianfranco.Persona.modelo.Service;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

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
    void seDebeDevolverUnaPersonaComoMensajeJSON() throws Exception {
        //Given
        Persona expectedPersona = FactoryPersonaTestData.getPersona();

        //When
        when(service.buscarPersonaPorId(anyInt())).thenReturn(expectedPersona);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/persona/1")
                                //.param("idPersona", "1")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPersona", Matchers.is(expectedPersona.getIdPersona())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is(expectedPersona.getNombre())))
        ;
    }

    @Test
    void seDebeDevolverStatusFound() throws Exception {
        //Given
        Persona expectedPersona = FactoryPersonaTestData.getPersona();

        //When
        when(service.buscarPersonaPorId(anyInt())).thenReturn(expectedPersona);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/persona/1")
                        //.param("idPersona", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
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
    void seDebeDevolverUnJSONVacioAlNoEncontrarPersonaPorId() throws Exception {
        //Given
        Persona expectedPersona = null;

        //When
        when(service.buscarPersonaPorId(anyInt())).thenReturn(expectedPersona);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/persona/3")
                        //.param("idPersona", "1")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist())
        ;
    }

    @Test
    void seDebeDevolverUnStatusNotFoundAlNoEncontrarPersonaPorId() throws Exception {
        //Given
        Persona expectedPersona = null;

        //When
        when(service.buscarPersonaPorId(anyInt())).thenReturn(expectedPersona);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/persona/3")
                        //.param("idPersona", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
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
    void seDebeGuardarUnaPersona() throws Exception {
        //Given
        var expectedResponse = FactoryPersonaTestData.getPersona();

        //When
        when(service.guardarPersona(any(Persona.class))).thenReturn(expectedResponse);

        mvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/persona/guardar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(FactoryPersonaTestData.getJSONPersona())
                )
        ;

        //Then
        verify(service).guardarPersona(any(Persona.class));
    }

    @Test
    void seDebeDevolverLaPersonaGuardadaAlGuardarUnaPersona() throws Exception {
        //Given
        var expectedResponse = FactoryPersonaTestData.getPersona();

        //When
        when(service.guardarPersona(any(Persona.class))).thenReturn(expectedResponse);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/persona/guardar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(FactoryPersonaTestData.getJSONPersona())
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPersona", Matchers.is(expectedResponse.getIdPersona())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", Matchers.is(expectedResponse.getNombre())))
        ;
    }

    @Test
    void seDebeDevolverStatusCreatedAlGuardarUnaPersona() throws Exception {
        //Given
        var expectedResponse = FactoryPersonaTestData.getPersona();

        //When
        when(service.guardarPersona(any(Persona.class))).thenReturn(expectedResponse);

        //Then
        mvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/persona/guardar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(FactoryPersonaTestData.getJSONPersona())
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
        ;
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
    void seDebeDevolverUnaListaDePersonasEncontradas() throws Exception {
        //Given
        List<Persona> personaList = FactoryPersonaTestData.getPersonaList();

        //When
        when(service.buscarTodasLasPersonas()).thenReturn(personaList);

        //Then
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/persona/todas/")
                        .header("ClientSecret", "asdf1234")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
        ;
    }

    @Test
    void seDebeDevolverStatusFoundListaDePersonas() throws Exception {
        //Given
        List<Persona> personaList = FactoryPersonaTestData.getPersonaList();

        //When
        when(service.buscarTodasLasPersonas()).thenReturn(personaList);

        //Then
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/persona/todas/")
                        .header("ClientSecret", "asdf1234")
                )
                .andExpect(MockMvcResultMatchers.status().isFound())
        ;
    }

    @Test
    void seDebeDevolverPersonasEncontradasEnSistema() throws Exception {
        //Given
        List<Persona> personaList = FactoryPersonaTestData.getPersonaList();
        Persona expectedPersona = FactoryPersonaTestData.getPersona();

        //When
        when(service.buscarTodasLasPersonas()).thenReturn(personaList);

        //Then
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/persona/todas/")
                        .header("ClientSecret", "asdf1234")
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idPersona", Matchers.is(expectedPersona.getIdPersona())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", Matchers.is(expectedPersona.getNombre())))
        ;
    }
}