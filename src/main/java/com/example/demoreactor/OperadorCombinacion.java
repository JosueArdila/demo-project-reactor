package com.example.demoreactor;

import com.example.demoreactor.model.Persona;
import com.example.demoreactor.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class OperadorCombinacion {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperadorCombinacion.class);

    public static void merge() {
        Flux<Persona> personaFlux = Flux.fromIterable(Util.personas());
        Flux<Venta> ventaFlux = Flux.fromIterable(Util.ventas());

        Flux.merge(personaFlux, ventaFlux)
                .subscribe(merge -> {
                    if (merge instanceof Persona) {
                        LOGGER.info("Persona Merge -> {}", merge.toString());
                        return;
                    }
                    LOGGER.info("Ventas Merge -> {}", merge.toString());
                });
    }

    public static void zip() {
        Flux<Persona> pf = Flux.fromIterable(Util.personas());
        Flux<Venta> vf = Flux.fromIterable(Util.ventas());

        Flux.zip(pf, vf, (p, v) -> "ZIP ".concat(String.format("Informmación de la persona %s, Información de la venta %s", p, v)))
                .collectList()
                .subscribe(m -> LOGGER.info(m.toString()));

        pf.zipWith(vf, (p, v) -> String.format("zipWith Informmación de la persona %s, Información de la venta %s", p, v))
                .subscribe(m -> LOGGER.info(m));

        Flux.zip(pf, vf)
                .subscribe(show -> LOGGER.info("Zip -> {}", show.toString()));
    }




}
