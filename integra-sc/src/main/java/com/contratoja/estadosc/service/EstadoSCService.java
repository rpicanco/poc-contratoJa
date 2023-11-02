package com.contratoja.estadosc.service;

import com.contratoja.estadosc.domain.Contrato;
import com.contratoja.estadosc.exception.IntegrationEstadoException;

public interface EstadoSCService {
    void registrarContrato(Contrato contrato) throws IntegrationEstadoException;
}
