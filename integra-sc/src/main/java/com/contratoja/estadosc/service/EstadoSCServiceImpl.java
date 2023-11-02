package com.contratoja.estadosc.service;

import com.contratoja.estadosc.domain.Contrato;

import com.contratoja.estadosc.exception.IntegrationEstadoException;
import com.contratoja.estadosc.mapper.DomainMapper;
import com.contratoja.estadosc.sistemaSC.ContratoSistemaEstadoSCRequest;
import com.contratoja.estadosc.sistemaSC.ContratoSistemaEstadoSCResponse;
import com.contratoja.estadosc.sistemaSC.SistemaEstadoSCHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.http.HttpStatusCode;

@RequiredArgsConstructor
@Service
@Slf4j
public class EstadoSCServiceImpl implements EstadoSCService {

    @Autowired
    private final SistemaEstadoSCHttpClient sistemaEstadoSCHttpClient;

    @Autowired
    private final DomainMapper domainMapper;
    @Override
    public void registrarContrato(Contrato contrato) throws IntegrationEstadoException {
        log.info("Convertendo contrato para o contrato do sistema do estado do SC...");
        ContratoSistemaEstadoSCRequest contratoSistemaEstadoSCRequest = domainMapper.contratoToContratoSistemaEstadoSCRequest(contrato);
        log.info("Contrato Sistema Estado RJ convertido: {}", contratoSistemaEstadoSCRequest );

        log.info("Enviando o contrato para o sistema do estado do SC...");
        ContratoSistemaEstadoSCResponse response = this.sistemaEstadoSCHttpClient.sendContratoToSistemaEstadoSC(contratoSistemaEstadoSCRequest);

        if(response == null || response.getRegistroId().isEmpty()) {
            throw new IntegrationEstadoException(HttpStatus.resolve(HttpStatusCode.BAD_GATEWAY), "Identificador do registro não recuperado!");
        }

        log.info("Contrato registrado com sucesso!!! Número contrato [{}] ID do registro do sistema do estado SC [{}]", contratoSistemaEstadoSCRequest.getNumero(), response.getRegistroId());
    }
}
