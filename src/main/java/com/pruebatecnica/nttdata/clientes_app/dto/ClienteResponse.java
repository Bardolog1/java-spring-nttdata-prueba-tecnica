package com.pruebatecnica.nttdata.clientes_app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String direccion;

    private String ciudadResidencia;
    private String email;


}
