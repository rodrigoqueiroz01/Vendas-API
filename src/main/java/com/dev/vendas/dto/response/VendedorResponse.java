package com.dev.vendas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendedorResponse {

    private Long vendedorId;
    private String nome;
    private Integer idade;
    private String sexo;
    private Integer numeroCadastro;

}
