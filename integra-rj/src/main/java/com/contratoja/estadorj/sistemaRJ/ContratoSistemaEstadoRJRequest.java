package com.contratoja.estadorj.sistemaRJ;

import lombok.*;

@Data
@NoArgsConstructor
public class ContratoSistemaEstadoRJRequest {
    private String numero;
    private double valor;
    private String parcelas;
    private String uf;
    private Cliente cliente;
    private String vendedor;
}
