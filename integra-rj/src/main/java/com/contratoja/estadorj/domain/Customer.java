package com.contratoja.estadorj.domain;

import lombok.*;
@Data
@NoArgsConstructor
public class Customer {
    @NonNull
    private String firstName;
    private String lastName;
}
