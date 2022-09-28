package com.dev.vendas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long vendedorId;
    private String nome;
    private Integer idade;
    private String sexo;
    private Integer numeroCadastro;

    @Override
    public String toString() {
        return "Vendedor{" +
                "vendedorId=" + vendedorId +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", numeroCadastro=" + numeroCadastro +
                '}';
    }

}
