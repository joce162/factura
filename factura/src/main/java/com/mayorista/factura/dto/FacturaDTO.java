package com.mayorista.factura.dto;
import lombok.Data;

@Data
public class FacturaDTO {
    private Long idPedido;
    private Long idCliente;
    private Double montoFinal;
}
