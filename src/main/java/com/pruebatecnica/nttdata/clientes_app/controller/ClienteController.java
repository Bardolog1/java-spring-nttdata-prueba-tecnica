package com.pruebatecnica.nttdata.clientes_app.controller;

import com.pruebatecnica.nttdata.clientes_app.dto.ActualizarClienteRequest;
import com.pruebatecnica.nttdata.clientes_app.dto.ClienteResponse;
import com.pruebatecnica.nttdata.clientes_app.entity.TipoDocumento;
import com.pruebatecnica.nttdata.clientes_app.service.ClienteService;
import com.pruebatecnica.nttdata.clientes_app.validation.ValidTipoDocumento;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<ClienteResponse> getCliente(
            @PathVariable @ValidTipoDocumento String tipoDocumento,
            @PathVariable String numeroDocumento,
            @RequestParam(required = false) boolean withAddress) {
        log.info("Consultando cliente: tipoDocumento={}, numeroDocumento={}", tipoDocumento, numeroDocumento);
        TipoDocumento tipoDocEnum = TipoDocumento.valueOf(tipoDocumento.toUpperCase());
        ClienteResponse response = clienteService.obtenerCliente(
                tipoDocEnum,
                numeroDocumento,
                withAddress
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<ClienteResponse> actualizarCliente(
            @PathVariable @ValidTipoDocumento String tipoDocumento,
            @PathVariable String numeroDocumento,
            @Valid @RequestBody ActualizarClienteRequest request) {
        log.info("Actualizando cliente: tipoDocumento={}, numeroDocumento={}, request={}", tipoDocumento, numeroDocumento, request);
        TipoDocumento tipoDocEnum = TipoDocumento.valueOf(tipoDocumento.toUpperCase());
        ClienteResponse response = clienteService.actualizarCliente(tipoDocEnum, numeroDocumento, request);
        return ResponseEntity.ok(response);
    }
}