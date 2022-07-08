package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static com.example.demoreactor.model.Persona.Sexo.FEMENINO;

public class OperadorCreacion {

    private static final Logger log = LoggerFactory.getLogger(OperadorCreacion.class);

    public void empty() {
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    public static void range() {
         Flux.range(0, 5)
                .subscribe(integer -> log.info("i -> " + integer ));
    }

    public void justFrom() {
        Mono.just(1);
        Observable.just(1);
        Flux.just(Arrays.asList(1,2,3,4));
    }

    public static void repeat() {
        Mono.just(new Persona("123456", "Pepa", 55, FEMENINO))
                .repeat(4)
                .doOnNext(p -> log.info(p.toString()))
                .subscribe();
    }




}
