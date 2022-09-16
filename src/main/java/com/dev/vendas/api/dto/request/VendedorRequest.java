package com.dev.vendas.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendedorRequest {

    private String nome;
    private Integer idade;
    private String sexo;
    private Integer numeroCadastro;

}
