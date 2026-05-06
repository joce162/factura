package com.mayorista.factura.service;

import com.mayorista.factura.model.factura;
import com.mayorista.factura.repository.facturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class facturaService {
    @Autowired
    private facturaRepository repository;

    public List<factura> listarTodas() {
        return repository.findAll();
    }
    public Optional<factura> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public factura guardarFactura(factura f) {

        if (f.getMonto_neto() != null) {
            double calculoIva = f.getMonto_neto() * 0.19;
            f.setIva(calculoIva);


            f.setMonto_final(f.getMonto_neto() + calculoIva);
        }

        if (f.getEstado() == null || f.getEstado().isEmpty()) {
            f.setEstado("EMITIDA");
        }

        return repository.save(f);
    }


    public void eliminarFactura(Long id) {
        repository.deleteById(id);
    }
}

