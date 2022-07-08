package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import com.example.demoreactor.model.Venta;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.demoreactor.model.Persona.Sexo.FEMENINO;
import static com.example.demoreactor.model.Persona.Sexo.MASCULINO;

public class Util {

    public static List<Persona> personas() {
        return Arrays.asList(
                new Persona("CC123", "Teresa", 43, FEMENINO),
                new Persona("CC87329", "Alexis", 14, MASCULINO),
                new Persona("CC8732549", "Daniel", 24, MASCULINO),
                new Persona("CC07985", "Maria", 37, FEMENINO),
                new Persona("CC34208", "Angie", 3, FEMENINO)
        );
    }

    public static List<Venta> ventas() {
        return  Arrays.asList(
                new Venta("rwerwe-78-ffsd78-rwe", 50.4, LocalDate.now()),
                new Venta("yreyr-55-dgsd55-rwe", 13.99, LocalDate.now()),
                new Venta("mbndhd-88-hfsdh88-rwe", 88.21, LocalDate.now()),
                new Venta("trwetqw-43-dasd43-rwe", 95.00, LocalDate.now())
        );
    }
}
