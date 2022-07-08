package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demoreactor.model.Persona.Sexo.FEMENINO;
import static com.example.demoreactor.model.Persona.Sexo.MASCULINO;

public class OperadorTransformacion {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperadorTransformacion.class);

    public static void map() {
        Persona persona = Persona.builder()
                .nombre("Rosa")
                .cedula("CC5448795")
                .edad(85)
                .sexo(FEMENINO)
                .build();

        Mono<Persona> personaMono = Mono.just(persona);
        personaMono.doOnNext(pm -> LOGGER.info("Persona original -> {}", pm.toString()))
                .subscribe();

        Mono<Persona> personaMonoChangeEdad = personaMono.map(p -> {
            p.setEdad(99);
            return p;
        });

        personaMonoChangeEdad.subscribe(p ->
                LOGGER.info("Persona modificada -> {}", p.toString())
        );
    }

    public static void flatMap() {
        List<Persona> personas = Arrays.asList(
                new Persona("CC123", "Teresa", 43, FEMENINO),
                new Persona("CC87329", "Alexis", 24, MASCULINO)
        );

        Flux.fromIterable(personas)
                .flatMap(p-> {
                    p.setUppercaseNombre();
                    return Mono.just(p);
                })
                .subscribe(pm -> LOGGER.info("Persona con upperCase con flux -> {}", pm.toString()));


        Flux.fromIterable(personas)
                .flatMap(p-> {
                    p.setUppercaseNombre();
                    return Mono.just(p);
                })
                .collectList()
                .subscribe(pm -> LOGGER.info("Persona con upperCase en mono -> {}", pm.toString()));
    }

    public static void groupBy() {
        List<Persona> personas = Arrays.asList(
                new Persona("CC123", "Teresa", 43, FEMENINO),
                new Persona("CC87329", "Alexis", 24, MASCULINO),
                new Persona("CC8732549", "Daniel", 24, MASCULINO)
        );

        // agrupar mediante api stream
        Map<Persona.Sexo, List<Persona>> map = personas.stream()
                .collect(Collectors.groupingBy(p-> p.getSexo()));

        LOGGER.info("el mapa es {}", map);


        // agrupación en un flux
        Flux.fromIterable(personas)
                .groupBy(Persona::getSexo)
                .flatMap(flux -> flux.collectList())
                .subscribe(ps -> {
                    LOGGER.info("group by Personas con sexo {} son -> {}", ps.get(0).getSexo(), ps);
                });
    }
}
