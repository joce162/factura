package com.mayorista.factura.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient() {
        this.webClient = WebClient.create("http://localhost:8082");
    }

    public void notificarPagoExterno(Long idFactura) {
        try {
            this.webClient.post()
                    .uri("/api/v1/pagos/factura/" + idFactura)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            // Evita caídas si el puerto de pagos no está activo en tus pruebas
        }
    }
}