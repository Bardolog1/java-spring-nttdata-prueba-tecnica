package com.pruebatecnica.nttdata.clientes_app.repository;

import com.pruebatecnica.nttdata.clientes_app.entity.Cliente;
import com.pruebatecnica.nttdata.clientes_app.entity.TipoDocumento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void findByTipoDocumentoAndNumeroDocumento_ClienteExiste_RetornaCliente() {
        Cliente cliente = new Cliente();
        cliente.setTipoDocumento(TipoDocumento.C);
        cliente.setNumeroDocumento("123456");
        cliente.setPrimerNombre("Juan");
        cliente.setPrimerApellido("Perez");
        clienteRepository.save(cliente);

        Optional<Cliente> resultado = clienteRepository
                .findByTipoDocumentoAndNumeroDocumento(TipoDocumento.C, "123456");

        assertTrue(resultado.isPresent());
    }
}