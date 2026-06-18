package com.mayorista.factura.controller;

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
    private FacturaService facturaService; // 👈 Corregido el tipo de dato aquí

    @GetMapping
    public List<Factura> listar() {
        return facturaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Factura> crear(@Valid @RequestBody Factura f) {
        Factura nuevaFactura = facturaService.guardarFactura(f);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }
}