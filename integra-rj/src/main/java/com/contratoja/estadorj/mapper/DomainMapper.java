package com.contratoja.estadorj.mapper;

import com.contratoja.estadorj.domain.Contrato;
import com.contratoja.estadorj.event.Data;
import com.contratoja.estadorj.sistemaRJ.ContratoSistemaEstadoRJRequest;
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
            @Mapping(source = "customer", target = "cliente"),
            @Mapping(source = "seller", target = "vendedor")
    })
    ContratoSistemaEstadoRJRequest contratoToContratoSistemaEstadoRJRequest(Contrato contrato);
}


