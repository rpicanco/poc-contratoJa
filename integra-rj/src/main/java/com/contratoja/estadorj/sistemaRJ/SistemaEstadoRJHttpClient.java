package com.contratoja.estadorj.sistemaRJ;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface SistemaEstadoRJHttpClient {

    @PostExchange("/rj/contratos")
    ContratoSistemaEstadoRJResponse sendContratoToSistemaEstadoRJ(@RequestBody ContratoSistemaEstadoRJRequest contratoSistemaEstadoRJRequest);
}
