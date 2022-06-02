package com.letscode.santander.ecommercemandioca.endpoint;

import com.letscode.santander.ecommercemandioca.model.Carrinho;
import com.letscode.santander.ecommercemandioca.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda/carrinho")
public class CarrinhoController {

    @Autowired
    CarrinhoService service;

    @GetMapping
    public List<Carrinho> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Carrinho procurar(
            @PathVariable Integer id
    ) {
        return service.procurar(id);
    }

    @PostMapping
    public Carrinho criar(
            @RequestBody Carrinho novo
    ) {
        return service.criar(novo);
    }

    @PatchMapping("/{id}/quantidade/{quantidade}")
    public Carrinho atualizarQuantidade(
            @PathVariable Integer id,
            @PathVariable Integer quantidade
    ) {
        return service.atualizarQuantidade(id, quantidade);
    }

    @PatchMapping("/{id}/produto/{idProduto}")
    public Carrinho atualizarIdProduto(
            @PathVariable Integer id,
            @PathVariable Integer idProduto
    ) {
        return service.atualizarIdProduto(id, idProduto);
    }

    @DeleteMapping("/{id}")
    public Carrinho excluir(
            @PathVariable Integer id
    ) {
        return service.excluir(id);
    }

}
