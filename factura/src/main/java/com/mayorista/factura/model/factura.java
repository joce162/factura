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
@Data // Esto crea Getters y Setters automáticamente
@NoArgsConstructor // Crea el constructor vacío
@AllArgsConstructor // Crea el constructor con todos los campos

public class factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;

    @NotNull(message = "El ID del pedido no puede ser nulo")
    private Long id_pedido;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Long id_cliente;

    @Positive(message = "El monto neto debe ser mayor a cero")
    private Double monto_neto;

    private Double iva;

    private Double monto_final;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision = LocalDateTime.now();

    private String estado;

}
