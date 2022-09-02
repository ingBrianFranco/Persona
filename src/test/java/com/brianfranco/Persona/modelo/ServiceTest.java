package com.brianfranco.Persona.modelo;

import com.brianfranco.Persona.Repositorio.PersonaRepositorio;
import com.brianfranco.Persona.data.FactoryPersonaTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ServiceTest {

    @InjectMocks
    Service service;

    @Mock
    PersonaRepositorio personaRepositorio;

    @Test
    void seDebeEncontrarUnaPersonaPorId() {
        //Given
        Persona expectedPersona = FactoryPersonaTestData.getPersona();
        //When
        when(personaRepositorio.findById(anyInt())).thenReturn(Optional.of(expectedPersona));

        Persona persona = service.buscarPersonaPorId(expectedPersona.idPersona);

        //Then
        Assertions.assertNotNull(persona);
        Assertions.assertEquals(persona.idPersona, expectedPersona.getIdPersona());
    }

    @Test
    void seDebeRetornarNullNoEncuentraPersonaPorId() {
        //Given
        Persona expectedPersona = null;

        //When
        when(personaRepositorio.findById(anyInt())).thenReturn(Optional.ofNullable(expectedPersona));

        Persona persona = service.buscarPersonaPorId(anyInt());

        //Then
        Assertions.assertEquals(null, persona);
    }

    @Test
    void seDebeGuardarUnaPersona() throws Exception {
        //Given
        Persona expectedPersona = FactoryPersonaTestData.getPersona();

        //When
        when(personaRepositorio.save(any(Persona.class))).thenReturn(expectedPersona);

        Persona persona = service.guardarPersona(FactoryPersonaTestData.getPersona());

        //Then
        verify(personaRepositorio).save(any(Persona.class));
    }

    @Test
    void seDebeGuardarUnaPersonaNoNula() throws Exception {
        //Given
        Persona expectedPersona = FactoryPersonaTestData.getPersona();

        //When
        when(personaRepositorio.save(any(Persona.class))).thenReturn(expectedPersona);

        //Then
        Persona persona = service.guardarPersona(FactoryPersonaTestData.getPersona());

        Assertions.assertNotNull(persona);
        //Esto es otro test
        Assertions.assertEquals(persona.idPersona, expectedPersona.getIdPersona());
        Assertions.assertEquals(persona.nombre, expectedPersona.getNombre());
    }

    @Test
    void seDebeArrojarUnNullPointerExceptionAlGuardarPersona() {
        //Given
        Persona savePersona = null;

        //When
        when(personaRepositorio.save(any())).thenThrow(new NullPointerException());

        //Then
        Assertions.assertThrows(NullPointerException.class,
                () -> {
                    service.guardarPersona(savePersona);
                }
        );
    }

    @Test
    void seDebeEliminarUnaPersonaPorId() {
        //Given

        //When
        doNothing().when(personaRepositorio).deleteById(anyInt());

        service.eliminarPersonaPorId(anyInt());

        //Then
        verify(personaRepositorio).deleteById(anyInt());
    }

    @Test
    void seDebenObtenerLasDireccionesEnMayusculaAlBuscarTodasLasPersonas() {
        //Given
        List<Persona> expectedPersonaList = FactoryPersonaTestData.getPersonaList();

        //When
        when(personaRepositorio.findAll()).thenReturn(FactoryPersonaTestData.getPersonaIterable());

        List<Persona> personaList = service.buscarTodasLasPersonas();

        //Then
        Assertions.assertEquals(personaList.get(0).direccion, expectedPersonaList.get(0).getDireccion().toUpperCase());
        //Assertions.assertEquals(personaList.get(0).direccion, "Avenida siempre viva 124");
    }
}