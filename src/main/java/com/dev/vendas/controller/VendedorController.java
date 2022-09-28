package com.dev.vendas.controller;

import com.dev.vendas.dto.mapper.VendedorMapper;
import com.dev.vendas.dto.request.VendedorRequest;
import com.dev.vendas.dto.response.VendedorResponse;
import com.dev.vendas.service.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendas")
@Validated
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public ResponseEntity<VendedorResponse> save(@Valid @RequestBody VendedorRequest vendedorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VendedorMapper.toResponse(vendedorService.save(VendedorMapper.toModel(vendedorRequest))));
    }

    @GetMapping("/{vendedorId}")
    public ResponseEntity<VendedorResponse> findById(@PathVariable String vendedorId) {
        return ResponseEntity.ok().body(VendedorMapper.toResponse(vendedorService.findById(Long.valueOf(vendedorId))));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendedorResponse>> findAll() {
        return ResponseEntity.ok().body(VendedorMapper.toResponseList(vendedorService.findAll()));
    }

    @PutMapping("/{vendedorId}")
    public ResponseEntity<VendedorResponse> update(@RequestBody VendedorRequest vendedorRequest, @PathVariable String vendedorId) {
        return ResponseEntity.ok(VendedorMapper.toResponse(vendedorService.update(VendedorMapper.toModel(vendedorRequest),
                Long.valueOf(vendedorId))));
    }

    @DeleteMapping("/{vendedorId}")
    public ResponseEntity<Object> delete(@PathVariable String vendedorId) {
        String code = String.valueOf(vendedorService.delete(Long.valueOf(vendedorId)));

        return ResponseEntity.ok().body("Vendedor de código " + code + " deletado na base de dados!");
    }

}
