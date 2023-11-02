package com.contratoja.estadosc.sistemaSC;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface SistemaEstadoSCHttpClient {

    @PostExchange("/sc/contratos")
    ContratoSistemaEstadoSCResponse sendContratoToSistemaEstadoSC(@RequestBody ContratoSistemaEstadoSCRequest contratoSistemaEstadoSCRequest);
}
