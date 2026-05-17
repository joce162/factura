package com.mayorista.factura.controller;

import com.mayorista.factura.dto.FacturaDTO;
import com.mayorista.factura.model.Factura;
import com.mayorista.factura.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService service;

    @GetMapping
    public List<Factura> listar() {
        return service.listarTodas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Factura> crear(@Valid @RequestBody Factura f) {
        Factura nuevaFactura = service.guardarFactura(f);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/pagar")
    public ResponseEntity<String> pagar(@PathVariable Long id) {
        try {
            service.procesarPagoFactura(id);
            return ResponseEntity.ok("Factura N° " + id + " actualizada a PAGADA de forma automatica.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/generar")
    public ResponseEntity<Factura> generarFacturaRemota(@RequestBody FacturaDTO dto) {
        try {
            Factura nuevaFactura = service.generarFacturaDesdePedido(
                    dto.getIdPedido(),
                    dto.getIdCliente(),
                    dto.getMontoFinal()
            );
            return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}