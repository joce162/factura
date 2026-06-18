package com.mayorista.factura.service;

import com.mayorista.factura.model.Factura;
import com.mayorista.factura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repository;

    public List<Factura> listarTodas() {
        return repository.findAll();
    }

    public Factura guardarFactura(Factura f) {
        f.setMontoNeto(f.getMontoFinal() / 1.19);
        f.setIva(f.getMontoFinal() - f.getMontoNeto());
        f.setEstado("PENDIENTE");
        return repository.save(f);
    }

    public void eliminarFactura(Long id) {
        repository.deleteById(id);
    }

    public void procesarPagoFactura(Long id) {
        Factura f = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con ID: " + id));
        f.setEstado("PAGADA");
        repository.save(f);
    }
}