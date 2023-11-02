package com.contratoja.estadorj.service;

import com.contratoja.estadorj.domain.Contrato;

import com.contratoja.estadorj.exception.IntegrationEstadoException;
import com.contratoja.estadorj.mapper.DomainMapper;
import com.contratoja.estadorj.sistemaRJ.ContratoSistemaEstadoRJRequest;
import com.contratoja.estadorj.sistemaRJ.ContratoSistemaEstadoRJResponse;
import com.contratoja.estadorj.sistemaRJ.SistemaEstadoRJHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.http.HttpStatusCode;

@RequiredArgsConstructor
@Service
@Slf4j
public class EstadoRJServiceImpl implements EstadoRJService {

    @Autowired
    private final SistemaEstadoRJHttpClient sistemaEstadoRJHttpClient;

    @Autowired
    private final DomainMapper domainMapper;
    @Override
    public void registrarContrato(Contrato contrato) throws IntegrationEstadoException {
        log.info("Convertendo contrato para o contrato do sistema do estado do RJ...");
        ContratoSistemaEstadoRJRequest contratoSistemaEstadoRJRequest = domainMapper.contratoToContratoSistemaEstadoRJRequest(contrato);
        log.info("Contrato Sistema Estado RJ convertido: {}", contratoSistemaEstadoRJRequest );

        log.info("Enviando o contrato para o sistema do estado do RJ...");
        ContratoSistemaEstadoRJResponse response = this.sistemaEstadoRJHttpClient.sendContratoToSistemaEstadoRJ(contratoSistemaEstadoRJRequest);

        if(response == null || response.getRegistroId().isEmpty()) {
            throw new IntegrationEstadoException(HttpStatus.resolve(HttpStatusCode.BAD_GATEWAY), "Identificador do registro não recuperado!");
        }

        log.info("Contrato registrado com sucesso!!! Número contrato [{}] ID do registro do sistema do estado RJ [{}]", contratoSistemaEstadoRJRequest.getNumero(), response.getRegistroId());
    }
}
