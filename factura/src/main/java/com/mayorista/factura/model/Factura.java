package com.mayorista.factura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @Column(name = "id_factura")
    private Long id; // Sin anotaciones de generación abajo para que use el tuyo

    private String estado;

    @Column(name = "fecha_emision")
    private String fechaEmision;

    @Column(name = "id_cliente")
    @NotNull
    private Long idCliente;

    @Column(name = "id_pedido")
    @NotNull
    private Long idPedido;

    private Double iva;

    @Column(name = "monto_final")
    @NotNull
    private Double montoFinal;

    @Column(name = "monto_neto")
    private Double montoNeto;

    public Factura() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }
    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }
    public Double getIva() { return iva; }
    public void setIva(Double iva) { this.iva = iva; }
    public Double getMontoFinal() { return montoFinal; }
    public void setMontoFinal(Double montoFinal) { this.montoFinal = montoFinal; }
    public Double getMontoNeto() { return montoNeto; }
    public void setMontoNeto(Double montoNeto) { this.montoNeto = montoNeto; }
}