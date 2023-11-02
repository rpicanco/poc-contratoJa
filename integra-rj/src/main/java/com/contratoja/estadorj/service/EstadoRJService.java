package com.contratoja.estadorj.service;

import com.contratoja.estadorj.domain.Contrato;
import com.contratoja.estadorj.exception.IntegrationEstadoException;

public interface EstadoRJService {
    void registrarContrato(Contrato contrato) throws IntegrationEstadoException;
}
