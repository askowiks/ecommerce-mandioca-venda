package com.letscode.santander.ecommercemandioca.entrypoint;

import com.letscode.santander.ecommercemandioca.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api-usuario", url = "http://localhost:8081/usuario")
public interface ApiUsuario {

    @GetMapping
    public List<UsuarioDto> listar();

    @GetMapping("/{id}")
    public UsuarioDto procurar(
            @PathVariable Integer id
    );

    @PostMapping
    public UsuarioDto adicionar(
            @RequestBody UsuarioDto novo
    );

    @PutMapping("/{id}")
    public UsuarioDto atualizar(
            @PathVariable Integer id,
            @RequestBody UsuarioDto atualizado
    );

    @DeleteMapping("/{id}")
    public UsuarioDto apagar(
            @PathVariable Integer id
    );

}
