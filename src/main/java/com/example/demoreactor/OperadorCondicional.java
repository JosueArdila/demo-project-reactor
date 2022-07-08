package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class OperadorCondicional {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperadorCondicional.class);

    public static void defaultIfEmpty() {
        Flux.empty()
                .defaultIfEmpty(new Persona())
                .subscribe(x -> LOGGER.info(x.toString()));
    }

    public static void takeUntil() {
        Flux.fromIterable(Util.personas())
                .takeUntil(tu-> tu.getEdad() < 18)
                .collectList()
                .subscribe(x -> LOGGER.info("Información de Take Until -> {}", x));
    }

    public static void timeout() throws InterruptedException {
        Flux.fromIterable(Util.personas())
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2))
                .subscribe(x -> LOGGER.info("Time out -> {}", x));

        Thread.sleep(10000);
    }
}
