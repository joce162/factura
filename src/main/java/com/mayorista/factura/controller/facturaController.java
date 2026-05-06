package com.mayorista.factura.controller;
import com.mayorista.factura.model.factura;
import com.mayorista.factura.service.facturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class facturaController {
    @Autowired
    private facturaService service;

    @GetMapping
    public List<factura> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<factura> obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<factura> crear(@Valid @RequestBody factura f) {
        factura nuevaFactura = service.guardarFactura(f);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }
}
