package com.pruebatecnica.nttdata.clientes_app.repository;

import com.pruebatecnica.nttdata.clientes_app.entity.Cliente;
import com.pruebatecnica.nttdata.clientes_app.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByTipoDocumentoAndNumeroDocumento(
            TipoDocumento tipoDocumento,
            String numeroDocumento
    );
}