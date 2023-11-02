package com.contratoja.estadosc.consumer;

import com.contratoja.estadosc.domain.Contrato;
import com.contratoja.estadosc.event.CloudEvent;
import com.contratoja.estadosc.mapper.DomainMapper;
import com.contratoja.estadosc.service.EstadoSCService;
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
    private final EstadoSCService estadoSCService;
    private final DomainMapper domainMapper;
    @SqsListener(value = "${spring.cloud.aws.sqs.endpoint}")
    public void processMessage(Message<CloudEvent> message)  {
        try {
            log.info("Mensagem SQS recebida: {}", message.getPayload() );
            CloudEvent cloudEvent = message.getPayload();

            log.info("Convertendo o evento para contrato...");
            Contrato contrato = domainMapper.cloudEventDataToContrato(cloudEvent.getData());
            log.info("Contrato convertido: {}", contrato );

            this.estadoSCService.registrarContrato(contrato);
            Acknowledgement.acknowledge(message);
        } catch ( Exception e) {
            throw new RuntimeException("Erro ao processar a mensagem do AWS SQS", e);
        }
    }
}
