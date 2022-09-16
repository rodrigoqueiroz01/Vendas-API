package com.dev.vendas.api.controler;

import com.dev.vendas.api.dto.mapper.VendedorMapper;
import com.dev.vendas.api.dto.request.VendedorRequest;
import com.dev.vendas.api.dto.response.VendedorResponse;
import com.dev.vendas.domain.service.VendedorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vendas")
@AllArgsConstructor
public class VendedorControler {

    private final VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorResponse> save(@RequestBody VendedorRequest vendedorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VendedorMapper.toResponse(vendedorService.save(VendedorMapper.toModel(vendedorRequest))));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendedorResponse>> finAll() {
        return ResponseEntity.ok().body(VendedorMapper.toResponseList(vendedorService.findAll()));
    }

    @GetMapping("/{vendedorId}")
    public ResponseEntity<VendedorResponse> findById(@PathVariable String vendedorId) {
        return ResponseEntity.ok().body(VendedorMapper.toResponse(vendedorService.findById(Long.valueOf(vendedorId))));
    }

    @PutMapping("/{vendedorId}")
    public ResponseEntity<VendedorResponse> update(@RequestBody VendedorRequest vendedorRequest, @PathVariable String vendedorId) {
        return ResponseEntity.ok()
                .body(VendedorMapper.toResponse(
                        vendedorService.update(VendedorMapper.toModel(vendedorRequest), Long.valueOf(vendedorId))));
    }

    @DeleteMapping("/{vendedorId}")
    public ResponseEntity<Object> delete(@PathVariable Long vendedorId) {
        String codigo = String.valueOf(vendedorService.delete(vendedorId));
        return ResponseEntity.ok().body("Vendedor de código " + codigo + " deletado!");
    }

}
