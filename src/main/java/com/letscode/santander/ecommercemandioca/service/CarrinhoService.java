package com.letscode.santander.ecommercemandioca.service;

import com.letscode.santander.ecommercemandioca.entrypoint.ApiProduto;
import com.letscode.santander.ecommercemandioca.model.Carrinho;
import com.letscode.santander.ecommercemandioca.repository.CarrinhoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    CarrinhoRepository repository;

    @Autowired
    ApiProduto apiProduto;

    @Autowired
    ModelMapper mapper;

    public List<Carrinho> listar() {
        return repository.findAll();
    }

    public Carrinho procurar(Integer id) {
        return repository.findById(id).get();
    }

    private Carrinho salvar(Integer id, Carrinho paraSalvar) {
        if (id != null && procurar(id) != null) {
            paraSalvar.setId(id);
        }

        return repository.save(paraSalvar);
    }

    public Carrinho criar(Carrinho novo) {
        return salvar(null, novo);
    }

    public Carrinho atualizar(Integer id, Carrinho atualizado) {
        return salvar(id, atualizado);
    }

    public Carrinho atualizarQuantidade(Integer id, Integer quantidade) {
        Carrinho procurado = procurar(id);
        procurado.setQuantidade(quantidade);
        atualizarTotal(procurado);
        return salvar(id, procurado);
    }

    public Carrinho atualizarIdProduto(Integer id, Integer idProduto) {
        Carrinho procurado = procurar(id);
        procurado.setIdProduto(idProduto);
        atualizarTotal(procurado);
        return salvar(id, procurado);
    }

    public Carrinho excluir(Integer id) {
        Carrinho paraExcluir = procurar(id);
        repository.delete(paraExcluir);
        return paraExcluir;
    }

    public void atualizarTotal(Carrinho carrinho) {
        BigDecimal precoProduto = apiProduto.procurar(carrinho.getIdProduto()).getPreco();
        carrinho.setTotal(
                precoProduto.multiply(
                        BigDecimal.valueOf(carrinho.getQuantidade())
                )
        );
    }

}
