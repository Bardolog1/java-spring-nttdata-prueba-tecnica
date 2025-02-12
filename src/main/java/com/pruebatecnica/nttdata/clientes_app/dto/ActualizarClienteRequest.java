package com.pruebatecnica.nttdata.clientes_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActualizarClienteRequest {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    @Pattern(regexp = "^\\d{7,15}$", message = "Teléfono inválido")
    private String telefono;
    private String direccion;
    private String ciudadResidencia;

    @Email(message = "Email inválido")
    private String email;


}