package com.pruebatecnica.nttdata.clientes_app.controller;

import com.pruebatecnica.nttdata.clientes_app.dto.ClienteResponse;
import com.pruebatecnica.nttdata.clientes_app.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void getCliente_ValoresValidos_Retorna200() throws Exception {
        ClienteResponse responseMock = new ClienteResponse();
        responseMock.setPrimerNombre("Juan");
        given(clienteService.obtenerCliente(any(), anyString(), anyBoolean()))
                .willReturn(responseMock);

        mockMvc.perform(get("/api/clientes/C/123456?withAddress=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primerNombre").value("Juan"));
    }

    @Test
    void getCliente_TipoDocumentoInvalido_Retorna400() throws Exception {
        mockMvc.perform(get("/api/clientes/X/123456"))
                .andExpect(status().isBadRequest());
    }


}
