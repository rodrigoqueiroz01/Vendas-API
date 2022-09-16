package com.dev.vendas.api.dto.response;

import lombok.*;

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
