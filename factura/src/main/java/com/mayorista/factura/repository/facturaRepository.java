package com.mayorista.factura.repository;
import com.mayorista.factura.model.factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface facturaRepository extends JpaRepository<factura, Long> {
}
