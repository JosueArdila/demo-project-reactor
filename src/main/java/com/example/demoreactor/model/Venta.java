package com.example.demoreactor.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Venta {

    private String id;
    private double valor;
    private LocalDate date;
}
