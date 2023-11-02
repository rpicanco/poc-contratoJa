package com.contratoja.estadorj.consumer;

import com.contratoja.estadorj.domain.Contrato;
import com.contratoja.estadorj.event.CloudEvent;
import com.contratoja.estadorj.mapper.DomainMapper;
import com.contratoja.estadorj.service.EstadoRJService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ContratoMessageConsumer {
    private final EstadoRJService estadoRJService;
    private final DomainMapper domainMapper;
    @SqsListener(value = "${spring.cloud.aws.sqs.endpoint}")
    public void processMessage(Message<CloudEvent> message)  {
        try {
            log.info("Mensagem SQS recebida: {}", message.getPayload() );
            CloudEvent cloudEvent = message.getPayload();

            log.info("Convertendo o evento para contrato...");
            Contrato contrato = domainMapper.cloudEventDataToContrato(cloudEvent.getData());
            log.info("Contrato convertido: {}", contrato );

            this.estadoRJService.registrarContrato(contrato);
            Acknowledgement.acknowledge(message);
        } catch ( Exception e) {
            throw new RuntimeException("Erro ao processar a mensagem do AWS SQS", e);
        }
    }
}
