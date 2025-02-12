package com.pruebatecnica.nttdata.clientes_app.service;

import com.pruebatecnica.nttdata.clientes_app.dto.ActualizarClienteRequest;
import com.pruebatecnica.nttdata.clientes_app.dto.ClienteResponse;
import com.pruebatecnica.nttdata.clientes_app.entity.Cliente;
import com.pruebatecnica.nttdata.clientes_app.entity.TipoDocumento;
import com.pruebatecnica.nttdata.clientes_app.exception.ClienteNotFoundException;
import com.pruebatecnica.nttdata.clientes_app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse obtenerCliente(TipoDocumento tipoDocumento, String numeroDocumento, boolean withAddress) {
        Cliente cliente = clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado"));
        return mapToClienteResponse(cliente, withAddress);
    }

    private ClienteResponse mapToClienteResponse(Cliente cliente, boolean withAddress) {
        ClienteResponse response = ClienteResponse.builder()
                .primerNombre(cliente.getPrimerNombre())
                .segundoNombre(cliente.getSegundoNombre())
                .primerApellido(cliente.getPrimerApellido())
                .segundoApellido(cliente.getSegundoApellido())
                .telefono(cliente.getTelefono())
                .ciudadResidencia(cliente.getCiudadResidencia())
                .email(cliente.getEmail())
                .build();

        if (withAddress && cliente.getDireccion() != null) {
            response.setDireccion(cliente.getDireccion());
        }

        return response;
    }

    public ClienteResponse actualizarCliente(TipoDocumento tipoDocumento, String numeroDocumento, ActualizarClienteRequest request) {
        Cliente cliente = clienteRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado"));

        actualizarCampo(request.getPrimerNombre(), cliente::setPrimerNombre);
        actualizarCampo(request.getSegundoNombre(), cliente::setSegundoNombre);
        actualizarCampo(request.getPrimerApellido(), cliente::setPrimerApellido);
        actualizarCampo(request.getSegundoApellido(), cliente::setSegundoApellido);
        actualizarCampo(request.getTelefono(), cliente::setTelefono);
        actualizarCampo(request.getDireccion(), cliente::setDireccion);
        actualizarCampo(request.getCiudadResidencia(), cliente::setCiudadResidencia);
        actualizarCampo(request.getEmail(), cliente::setEmail);

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return mapToClienteResponse(clienteActualizado, false);
    }

    private <T> void actualizarCampo(T valor, java.util.function.Consumer<T> setter) {
        Optional.ofNullable(valor).ifPresent(setter);
    }

}