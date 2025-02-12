package com.pruebatecnica.nttdata.clientes_app.service;

import com.pruebatecnica.nttdata.clientes_app.dto.ActualizarClienteRequest;
import com.pruebatecnica.nttdata.clientes_app.dto.ClienteResponse;
import com.pruebatecnica.nttdata.clientes_app.entity.Cliente;
import com.pruebatecnica.nttdata.clientes_app.entity.TipoDocumento;
import com.pruebatecnica.nttdata.clientes_app.exception.ClienteNotFoundException;
import com.pruebatecnica.nttdata.clientes_app.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void obtenerCliente_ClienteExiste_RetornaClienteResponse() {
        Cliente clienteMock = new Cliente();
        clienteMock.setPrimerNombre("Juan");
        clienteMock.setDireccion("Calle 123");
        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(any(), any()))
                .thenReturn(Optional.of(clienteMock));

        ClienteResponse response = clienteService.obtenerCliente(
                TipoDocumento.C, "123456", true
        );

        assertEquals("Juan", response.getPrimerNombre());
        assertEquals("Calle 123", response.getDireccion());
        verify(clienteRepository, times(1)).findByTipoDocumentoAndNumeroDocumento(any(), any());
    }

    @Test
    void obtenerCliente_ClienteNoExiste_LanzaExcepcion() {
        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(any(), any()))
                .thenReturn(Optional.empty());

        assertThrows(ClienteNotFoundException.class, () ->
                clienteService.obtenerCliente(TipoDocumento.C, "000000", false)
        );
    }

    @Test
    void actualizarCliente_ClienteExiste_ActualizaCampos() {
        Cliente clienteExistente = new Cliente();
        clienteExistente.setPrimerNombre("NombreAntiguo");
        when(clienteRepository.findByTipoDocumentoAndNumeroDocumento(any(), any()))
                .thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any())).thenReturn(clienteExistente);

        ActualizarClienteRequest request = new ActualizarClienteRequest();
        request.setPrimerNombre("NuevoNombre");

        ClienteResponse response = clienteService.actualizarCliente(
                TipoDocumento.C, "123456", request
        );

        assertEquals("NuevoNombre", response.getPrimerNombre());
        verify(clienteRepository, times(1)).save(any());
    }
}