package com.dev.vendas.domain.repository;

import com.dev.vendas.domain.model.VendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel, Long> {

    VendedorModel findByNome(String nome);

    VendedorModel findByNumeroCadastro(Integer numeroCadastro);

}
