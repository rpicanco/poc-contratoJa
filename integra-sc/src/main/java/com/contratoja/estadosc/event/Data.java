package com.contratoja.estadosc.event;

import com.contratoja.estadosc.domain.Customer;
import lombok.*;

@lombok.Data
@NoArgsConstructor
public class Data {
    @NonNull
    private String number;
    @NonNull
    private double amount;
    private int installments;
    @NonNull
    private String uf;
    private Customer customer;
    private String seller;
}
