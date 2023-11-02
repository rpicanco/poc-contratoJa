package com.contratoja.estadosc.mapper;

import com.contratoja.estadosc.domain.Contrato;
import com.contratoja.estadosc.event.Data;
import com.contratoja.estadosc.sistemaSC.ContratoSistemaEstadoSCRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DomainMapper {
    Contrato cloudEventDataToContrato(Data data);
    @Mappings({
            @Mapping(source = "number", target = "numero"),
            @Mapping(source = "amount", target = "valor"),
            @Mapping(source = "installments", target = "parcelas"),
            @Mapping(source = "uf", target = "uf"),
            @Mapping(target = "cliente.nomeCompleto", expression = "java(contrato.getCustomer().getFirstName() + \" \" + contrato.getCustomer().getLastName())")
    })
    ContratoSistemaEstadoSCRequest contratoToContratoSistemaEstadoSCRequest(Contrato contrato);
}


