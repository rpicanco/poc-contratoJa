package com.contratoja.estadosc.domain;

import lombok.*;

@Data
public class Contrato {
    private String number;
    private double amount;
    private int installments;
    private String uf;
    private Customer customer;
    private String seller;
}
