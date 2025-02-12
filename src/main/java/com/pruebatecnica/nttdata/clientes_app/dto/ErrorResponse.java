package com.pruebatecnica.nttdata.clientes_app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private int code;
    private String error;
    private String message;

}