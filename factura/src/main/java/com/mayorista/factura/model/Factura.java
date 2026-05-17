package com.mayorista.factura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    @NotNull(message = "El ID del pedido no puede ser nulo")
    @Column(name = "id_pedido")
    private Long idPedido;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    @Column(name = "id_cliente")
    private Long idCliente;

    @Positive(message = "El monto neto debe ser mayor a cero")
    @Column(name = "monto_neto")
    private Double montoNeto;

    private Double iva;

    @Column(name = "monto_final")
    private Double montoFinal;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision = LocalDateTime.now();

    private String estado;
}