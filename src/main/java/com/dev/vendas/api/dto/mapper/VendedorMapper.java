package com.dev.vendas.api.dto.mapper;

import com.dev.vendas.api.dto.request.VendedorRequest;
import com.dev.vendas.api.dto.response.VendedorResponse;
import com.dev.vendas.domain.model.VendedorModel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Objects.isNull;

@Component
public class VendedorMapper {

    public static VendedorModel toModel(VendedorRequest vendedorRequest) {
        return VendedorModel
                .builder()
                .nome(vendedorRequest.getNome())
                .idade(vendedorRequest.getIdade())
                .sexo(vendedorRequest.getSexo())
                .numeroCadastro(vendedorRequest.getNumeroCadastro())
                .build();
    }

    public static VendedorResponse toResponse(VendedorModel vendedorModel) {
        return VendedorResponse
                .builder()
                .vendedorId(vendedorModel.getVendedorId())
                .nome(vendedorModel.getNome())
                .idade(vendedorModel.getIdade())
                .sexo(vendedorModel.getSexo())
                .numeroCadastro(vendedorModel.getNumeroCadastro())
                .build();
    }

    public static List<VendedorResponse> toResponseList(List<VendedorModel> vendedorModelList) {
        if (isNull(vendedorModelList) || vendedorModelList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return vendedorModelList
                    .stream()
                    .map(VendedorMapper::toResponse)
                    .collect(Collectors.toList());
        }
    }

}
