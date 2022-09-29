package com.dev.vendas.repository;

import com.dev.vendas.model.Vendedor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class VendedorRepository {

    private final JdbcTemplate jdbcTemplate;

    public VendedorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public class VendedorRowMapper implements RowMapper<Vendedor> {
        @Override
        public Vendedor mapRow(ResultSet rs, int rowNum) throws SQLException {
            var vendedor = new Vendedor();
            vendedor.setVendedorId(rs.getLong("id"));
            vendedor.setNome(rs.getString("nome"));
            vendedor.setIdade(rs.getInt("idade"));
            vendedor.setSexo(rs.getString("sexo"));
            vendedor.setNumeroCadastro(rs.getInt("numero_cadastro"));

            return vendedor;
        }
    }

    public String save(Vendedor vendedor) {
        String createQuery = "insert into vendedor (nome, idade, sexo, numero_cadastro) values (?, ?, ?, ?)";

        return String.valueOf(jdbcTemplate.update(createQuery, new Object[]{vendedor.getNome(), vendedor.getIdade(),
                vendedor.getSexo(), vendedor.getNumeroCadastro()}));
    }

    public Vendedor findById(String id) {
        String readQuery = "select * from vendedor where id = ?";

        try {
            return jdbcTemplate.queryForObject(readQuery, new Object[]{id}, new int []{Types.VARCHAR}, new VendedorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vendedor> findAll() {
        return jdbcTemplate.query("select * from vendedor", new VendedorRowMapper());
    }

    public Long delete(Vendedor vendedor) {
        String deleteQuery = "delete from vendedor where id = ?";

        try {
            return Long.valueOf(jdbcTemplate.update(deleteQuery, vendedor.getVendedorId()));
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
