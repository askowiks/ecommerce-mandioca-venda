package com.letscode.santander.ecommercemandioca.entrypoint;

import com.letscode.santander.ecommercemandioca.dto.PrecoDto;
import com.letscode.santander.ecommercemandioca.dto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api-produto", url = "http://localhost:8082/produto")
public interface ApiProduto {

    @GetMapping
    public List<ProdutoDto> listar();

    @GetMapping("/{id}")
    public ProdutoDto procurar(
            @PathVariable Integer id
    );

    @PostMapping
    public ProdutoDto adicionar(
            @RequestBody ProdutoDto novo
    );

    @PutMapping("/{id}")
    public ProdutoDto atualizar(
            @PathVariable Integer id,
            @RequestBody ProdutoDto atualizado
    );

    @PatchMapping("/{id}/{quantidade}")
    public ProdutoDto atualizarQuantidade(
            @PathVariable Integer id,
            @PathVariable Integer quantidade
    );

    @PatchMapping("/{id}")
    public ProdutoDto atualizarPreco(
            @PathVariable Integer id,
            @RequestBody PrecoDto novoPreco
    );

    @DeleteMapping("/{id}")
    public ProdutoDto apagar(
            @PathVariable Integer id
    );

}
