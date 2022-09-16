package com.dev.vendas.domain.service;

import com.dev.vendas.api.exception.DataAlreadyRegisteredException;
import com.dev.vendas.api.exception.VendedorNotFoundException;
import com.dev.vendas.domain.model.VendedorModel;
import com.dev.vendas.domain.repository.VendedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorModel save(VendedorModel vendedor) {
        if (!Objects.isNull(vendedorRepository.findByNome(vendedor.getNome()))) {
            throw new DataAlreadyRegisteredException("Conflito: Já existe vendedor registrado com esse nome na base de dados.");
        }

        if (!Objects.isNull(vendedorRepository.findByNumeroCadastro(vendedor.getNumeroCadastro()))) {
            throw new DataAlreadyRegisteredException("Conflito: Já existe vendedor registrado com esse número de cadastro na base de dados.");
        }

        return vendedorRepository.save(vendedor);
    }

    public VendedorModel findById(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorNotFoundException());
    }

    public List<VendedorModel> findAll() {
        return vendedorRepository.findAll();
    }

    public VendedorModel update(VendedorModel vendedorModel, Long id) {
        vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorNotFoundException());
        vendedorModel.setVendedorId(id);
        vendedorRepository.save(vendedorModel);
        return vendedorModel;
    }

    public Long delete(Long id) {
        VendedorModel vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorNotFoundException());
        vendedorRepository.delete(vendedor);
        return id;
    }

}
