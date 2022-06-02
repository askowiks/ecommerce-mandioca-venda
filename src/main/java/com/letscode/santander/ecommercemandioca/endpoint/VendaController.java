package com.letscode.santander.ecommercemandioca.endpoint;

import com.letscode.santander.ecommercemandioca.model.Venda;
import com.letscode.santander.ecommercemandioca.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    VendaService service;

    @GetMapping
    public List<Venda> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Venda procurar(
            @PathVariable Integer id
    ) {
        return service.procurar(id);
    }

    @PostMapping("/nova/{idCarrinho}")
    public Venda finalizar(
            @PathVariable Integer idCarrinho
    ) {
        return service.finalizar(idCarrinho);
    }

    @DeleteMapping("/{id}")
    public Venda excluir(
            @PathVariable Integer id
    ) {
        return service.excluir(id);
    }

}
