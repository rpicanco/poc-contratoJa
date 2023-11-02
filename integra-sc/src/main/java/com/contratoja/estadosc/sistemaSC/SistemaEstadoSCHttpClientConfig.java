package com.contratoja.estadosc.sistemaSC;

import com.contratoja.estadosc.exception.IntegrationEstadoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
public class SistemaEstadoSCHttpClientConfig {

    @Value("${sistemaEstadoRJ.url}")
    private String url;

    @Bean
    public SistemaEstadoSCHttpClient createClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultStatusHandler(
                        HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new IntegrationEstadoException(HttpStatus.resolve(response.statusCode().value()), "Erro gerado pelo cliente.")))
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new IntegrationEstadoException(HttpStatus.resolve(response.statusCode().value()), "Erro inesperado no sistema do estado do RJ.")))
                .build();

        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build()
                .createClient(SistemaEstadoSCHttpClient.class);
    }
}
