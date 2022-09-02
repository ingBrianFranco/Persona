package com.brianfranco.Persona.data;

import com.brianfranco.Persona.modelo.Persona;

import java.util.ArrayList;
import java.util.List;

public class FactoryPersonaTestData {
    public static Persona getPersona(){
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setNombre("Brian");
        persona.setDireccion("Avenida siempre viva 124");
        persona.setTipoDocumento(1);
        persona.setDocumento(10971234);
        persona.setTelefono("3112589324");

        return persona;
    };

    public static Persona getPesonaNull(){
        return null;
    }

    public static List<Persona> getPersonaList(){
        Persona persona = getPersona();
        List<Persona> personaList = List.of(persona);
        return personaList;
    }

    public static Iterable<Persona> getPersonaListUppercaseAddress(){
        Persona persona = getPersona();
        persona.setDireccion(persona.getDireccion().toUpperCase());
        Iterable<Persona> personaList = List.of(persona);
        return personaList;
    }

    public static Iterable<Persona> getPersonaIterable(){
        Persona persona = getPersona();
        Iterable<Persona> personaIterable = List.of(persona);
        return personaIterable;
    }

    public static String getJSONPersona(){
        return "{\"nombre\": \"Brian\", \"tipoDocumento\": 1, \"documento\": 10971284, \"direccion\": \"Avenida siempre viva 124\", \"telefono\": \"3112589324\"}";
    }
}
