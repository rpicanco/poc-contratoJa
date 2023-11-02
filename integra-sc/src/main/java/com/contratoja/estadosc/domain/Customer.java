package com.contratoja.estadosc.domain;

import lombok.*;
@Data
@NoArgsConstructor
public class Customer {
    @NonNull
    private String firstName;
    private String lastName;
}
