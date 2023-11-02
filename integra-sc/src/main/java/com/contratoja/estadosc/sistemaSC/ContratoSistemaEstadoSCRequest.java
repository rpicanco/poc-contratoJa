package com.contratoja.estadosc.sistemaSC;

import lombok.*;

@Data
@NoArgsConstructor
public class ContratoSistemaEstadoSCRequest {
    private String numero;
    private double valor;
    private int parcelas;
    private String uf;
    private Cliente cliente;
}
