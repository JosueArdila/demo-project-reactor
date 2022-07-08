package com.example.demoreactor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private String cedula;
    private String nombre;
    private Integer edad;
    private Persona.Sexo sexo;

    public void setUppercaseNombre() {
        this.nombre = this.nombre.toUpperCase();
    }

    public enum Sexo {
        MASCULINO, FEMENINO
    }
}
