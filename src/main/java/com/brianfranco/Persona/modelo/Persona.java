package com.brianfranco.Persona.modelo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_persona")
    int idPersona;
    @Column
    String nombre;
    @Column(name = "tipo_documento")
    int tipoDocumento;
    @Column
    int documento;
    @Column
    String direccion;
    @Column
    String telefono;
}
