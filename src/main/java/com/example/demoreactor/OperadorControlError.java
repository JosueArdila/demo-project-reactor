package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperadorControlError {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperadorControlError.class);

    public static void retry() {
        Flux.fromIterable(Util.personas())
                .take(1)
                .concatWith(Flux.error(new RuntimeException("Error en ejecución")))
                .retry(1)
                .doOnNext(dn -> LOGGER.info(dn.toString()))
                .subscribe();
    }

    public static void onErrorReturn() {
        Flux.fromIterable(Util.personas())
                .take(1)
                .concatWith(Flux.error(new RuntimeException("Error en ejecución")))
                .onErrorReturn(new Persona("xxx", "testxxx", 0, null))
                .subscribe(x -> LOGGER.info(x.toString()));
    }

    public static void onErrorResume() {
        Flux.fromIterable(Util.personas())
                .take(1)
                .concatWith(Flux.error(new RuntimeException("Error en ejecución")))
                .onErrorResume(e-> Mono.just(new Persona("xxx", "testxxx", 0, null)))
                .subscribe(x -> LOGGER.info(x.toString()));
    }

    public static void onErrorMap() {
        Flux.fromIterable(Util.personas())
                .take(1)
                .concatWith(Flux.error(new RuntimeException("Error en ejecución")))
                .onErrorMap(e -> new RuntimeException(e.getMessage()))
                .subscribe(x -> LOGGER.info(x.toString()));
    }

}
