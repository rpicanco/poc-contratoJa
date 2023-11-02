package com.contratoja.estadorj.config;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementResultCallback;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import org.springframework.messaging.Message;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;

@Configuration
@Slf4j
public class AwsSQSConfiguration {
    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Bean
    SqsAsyncClient sqsAsyncClient(){
        return SqsAsyncClient
                .builder()
                .endpointOverride(URI.create("http://localstack:4566"))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient){
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
    }

    @Bean
    SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(
            SqsAsyncClient sqsAsyncClient) {
        return SqsMessageListenerContainerFactory.builder()
                .configure(options -> options.acknowledgementMode(AcknowledgementMode.MANUAL)
                        .acknowledgementInterval(
                                Duration.ofSeconds(3))
                        .acknowledgementThreshold(0)
                )
                .acknowledgementResultCallback(new AckResultCallback()).sqsAsyncClient(sqsAsyncClient)
                .build();
    }
    static class AckResultCallback implements AcknowledgementResultCallback<Object> {

        @Override
        public void onSuccess(Collection<Message<Object>> messages) {
            log.info("Removido (Ack) da fila SQS com sucesso {}", OffsetDateTime.now());
        }

        @Override
        public void onFailure(Collection<Message<Object>> messages, Throwable t) {
            log.info("Erro ao remover (Ack) da fila SQS", t);
        }
    }
}
