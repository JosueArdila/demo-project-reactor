package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static com.example.demoreactor.model.Persona.Sexo.FEMENINO;
import static com.example.demoreactor.model.Persona.Sexo.MASCULINO;

public class OperadorFiltrado {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperadorFiltrado.class);

    public static void filter() {
        // mayores de edad
        Flux.fromIterable(Util.personas())
                .filter(p -> p.getEdad() > 17)
                .doOnNext(p -> LOGGER.info(p.toString()))
                .subscribe();
    }

    public static void distinct() {
        List<Persona> personas = Arrays.asList(
                new Persona("CC123", "Teresa", 43, FEMENINO),
                new Persona("CC123", "Teresa", 43, FEMENINO),
                new Persona("CC87329", "Alexis", 14, MASCULINO),
                new Persona("CC8732549", "Daniel", 24, MASCULINO)
        );

        Flux.fromIterable(personas)
                .distinct()
                .subscribe(persona -> LOGGER.info("Distinct -> {}", persona.toString()));
    }

    public static void take() {
        Flux.fromIterable(Util.personas())
                .take(2)
                .subscribe(persona -> LOGGER.info("Take los dos primeros -> {}", persona.toString()));

        Flux.fromIterable(Util.personas())
                .takeLast(1)
                .subscribe(persona -> LOGGER.info("Take el primero a partir de atras-> {}", persona.toString()));
    }

    public static void skip() {
        Flux.fromIterable(Util.personas())
                .skip(2)
                .collectList()
                .subscribe(persona -> LOGGER.info("Quita los dos primeros -> {}", persona.toString()));

        Flux.fromIterable(Util.personas())
                .skipLast(1)
                .collectList()
                .subscribe(persona -> LOGGER.info("Quita el primero a partir de atras-> {}", persona.toString()));
    }    
}
