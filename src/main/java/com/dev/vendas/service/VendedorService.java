package com.dev.vendas.service;

import com.dev.vendas.exception.DataAlreadyRegistedException;
import com.dev.vendas.exception.NotFoundException;
import com.dev.vendas.model.Vendedor;
import com.dev.vendas.repository.VendedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public Vendedor save(Vendedor vendedor) {
        if (!Objects.isNull(vendedorRepository.findById(String.valueOf(vendedor.getVendedorId())))) {
            throw new DataAlreadyRegistedException("Conflito: Esse vendedor já é cadastrado na base de dados.");
        }

        vendedorRepository.save(vendedor);
        return vendedor;
    }

    public Vendedor findById(Long id) {
        try {
            return vendedorRepository.findById(String.valueOf(id));
        } catch (RuntimeException e) {
            throw new NotFoundException("Vendedor não encontrado com esse ID");
        }
    }

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor update(Vendedor vendedor, Long id) {
        try {
            vendedorRepository.findById(String.valueOf(id));
        } catch (RuntimeException e) {
            throw new NotFoundException("Vendedor não encontrado com esse ID");
        }

        if (!Objects.isNull(vendedorRepository.findById(String.valueOf(vendedor.getVendedorId())))) {
            throw new DataAlreadyRegistedException("Conflito: Esse vendedor já é cadastrado na base de dados.");
        }

        vendedorRepository.save(vendedor);
        return vendedor;
    }

    public Long delete(Long id) {
        Vendedor vendedor = new Vendedor();

        try {
            vendedor = vendedorRepository.findById(String.valueOf(id));
        } catch (RuntimeException e) {
            throw new NotFoundException("Vendedor não encontrado com esse ID");
        }

        vendedorRepository.delete(vendedor);

        return id;
    }

}
