package com.mayorista.factura.service;

import com.mayorista.factura.model.Factura;
import com.mayorista.factura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  FacturaService{

    @Autowired
    private FacturaRepository repository;

    public List<Factura> listarTodas() {
        return repository.findAll();
    }

    public Optional<Factura> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Factura guardarFactura(Factura f) {
        if (f.getMontoNeto() != null) { // Cambiado a CamelCase
            double calculoIva = f.getMontoNeto() * 0.19;
            f.setIva(calculoIva);
            f.setMontoFinal(f.getMontoNeto() + calculoIva);
        }
        if (f.getEstado() == null || f.getEstado().isEmpty()) {
            f.setEstado("EMITIDA");
        }
        return repository.save(f);
    }
    public void eliminarFactura(Long id) {
        repository.deleteById(id);
    }

    public void procesarPagoFactura(Long id) {
        Factura f = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
        f.setEstado("PAGADA");
        repository.save(f);
    }


    public Factura generarFacturaDesdePedido(Long idPedido, Long idCliente, Double montoFinal) {
        Factura factura = new Factura();
        factura.setIdPedido(idPedido);
        factura.setIdCliente(idCliente);
        factura.setMontoFinal(montoFinal);

        double neto = montoFinal / 1.19;
        double iva = montoFinal - neto;

        factura.setMontoNeto(Math.round(neto * 100.0) / 100.0);
        factura.setIva(Math.round(iva * 100.0) / 100.0);
        factura.setEstado("EMITIDA");

        return repository.save(factura);
    }
}