package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

public class OperadorMatematico {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperadorMatematico.class);

    public static void average() {
        Flux.fromIterable(Util.personas())
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(x -> LOGGER.info("Promedio de edad de las personas es {} años", x));
    }

    public static void count() {
        Flux.fromIterable(Util.personas())
                .count()
                .subscribe(x -> LOGGER.info("Cantidad de personas es {}", x));
    }

    public static void min() {
        Flux.fromIterable(Util.personas())
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(x -> LOGGER.info("La edad minima es {}", x.get().getEdad()));
    }

    public static void sum() {
        Flux.fromIterable(Util.personas())
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(x -> LOGGER.info("La suma de las edades es {}", x));
    }

    public static void summarize() {
        Flux.fromIterable(Util.personas())
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(x -> LOGGER.info("Summarizing es {}", x));
    }


}
