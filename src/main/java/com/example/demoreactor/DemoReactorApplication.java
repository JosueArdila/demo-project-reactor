package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static com.example.demoreactor.model.Persona.Sexo.FEMENINO;
import static com.example.demoreactor.model.Persona.Sexo.MASCULINO;

@SpringBootApplication
public class DemoReactorApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoReactorApplication.class);


    public void reactor() {
        Mono.just(new Persona("423534", "Josue", 26, MASCULINO))
                .doOnNext(
                        p -> p.setUppercaseNombre()
                )
                .subscribe(p -> LOGGER.info("[reactor] " + p.toString()));
    }

    public void rxjava2() {
        Observable.just(new Persona("66423423", "Josue", 26, MASCULINO))
                .doOnNext(p ->
                        p.setUppercaseNombre()
                )
                .subscribe(p -> LOGGER.info("[rxjava2] " + p.toString()));
    }

    public void flux() {
        List<Persona> personas = Arrays.asList(
                new Persona("123", "Andres", 30, MASCULINO),
                new Persona("456", "Ruth", 10, FEMENINO),
                new Persona("987", "Julia", 7, FEMENINO));
        Flux.fromIterable(personas)
                .subscribe(persona -> LOGGER.info(persona.toString()));
    }

    public void fluxMono() {
        List<Persona> personas = Arrays.asList(
                new Persona("123", "Andres", 30, MASCULINO),
                new Persona("456", "Ruth", 10, FEMENINO),
                new Persona("987", "Julia", 7, FEMENINO));

        Flux<Persona> flux = Flux.fromIterable(personas);
        flux.collectList()
                .subscribe(persona -> LOGGER.info(persona.toString()));
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoReactorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        reactor();
        rxjava2();
        flux();
        fluxMono();

//      operadores de creación
        OperadorCreacion.range();
        OperadorCreacion.repeat();

//      operadores de tranformación
        OperadorTransformacion.map();
        OperadorTransformacion.flatMap();
        OperadorTransformacion.groupBy();

//		operadores de filtrado
        OperadorFiltrado.filter();
        OperadorFiltrado.distinct();
        OperadorFiltrado.take();
        OperadorFiltrado.skip();

//      Operadores de combinación
        OperadorCombinacion.merge();
        OperadorCombinacion.zip();

//      Operadores de control de errores
        OperadorControlError.retry();
        OperadorControlError.onErrorReturn();
        OperadorControlError.onErrorResume();
        OperadorControlError.onErrorMap();

//      Operadores de condicion
        OperadorCondicional.defaultIfEmpty();
        OperadorCondicional.takeUntil();
        OperadorCondicional.timeout();

//      Operadores matematicos
        OperadorMatematico.min();
        OperadorMatematico.summarize();
        OperadorMatematico.average();
        OperadorMatematico.sum();
        OperadorMatematico.count();
    }
}
