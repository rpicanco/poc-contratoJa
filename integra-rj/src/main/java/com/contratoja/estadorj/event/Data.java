package com.contratoja.estadorj.event;

import com.contratoja.estadorj.domain.Contrato;
import com.contratoja.estadorj.domain.Customer;
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
