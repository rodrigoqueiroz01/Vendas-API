package com.dev.vendas.dto.mapper;

import com.dev.vendas.dto.request.VendedorRequest;
import com.dev.vendas.dto.response.VendedorResponse;
import com.dev.vendas.model.Vendedor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Objects.isNull;

@Component
public class VendedorMapper {
    public static Vendedor toModel(VendedorRequest vendedorRequest) {
        return Vendedor.builder()
                .nome(vendedorRequest.getNome())
                .idade(vendedorRequest.getIdade())
                .sexo(vendedorRequest.getSexo())
                .numeroCadastro(vendedorRequest.getNumeroCadastro())
                .build();
    }

    public static VendedorResponse toResponse(Vendedor vendedor) {
        return VendedorResponse.builder()
                .vendedorId(vendedor.getVendedorId())
                .nome(vendedor.getNome())
                .idade(vendedor.getIdade())
                .sexo(vendedor.getSexo())
                .numeroCadastro(vendedor.getNumeroCadastro())
                .build();
    }

    public static List<VendedorResponse> toResponseList(List<Vendedor> listaVendedores) {
        if (isNull(listaVendedores) || listaVendedores.isEmpty()) {
            return new ArrayList<>();
        }

        return listaVendedores
                .stream()
                .map(VendedorMapper::toResponse)
                .collect(Collectors.toList());
    }
}
