package com.letscode.santander.ecommercemandioca.service;

import com.letscode.santander.ecommercemandioca.dto.ProdutoDto;
import com.letscode.santander.ecommercemandioca.entrypoint.ApiProduto;
import com.letscode.santander.ecommercemandioca.model.Carrinho;
import com.letscode.santander.ecommercemandioca.model.Venda;
import com.letscode.santander.ecommercemandioca.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    VendaRepository repository;

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ApiProduto apiProduto;

    @Autowired
    ModelMapper mapper;

    public List<Venda> listar() {
        return repository.findAll();
    }

    public Venda procurar(Integer id) {
        return repository.findById(id).get();
    }

    private Venda salvar(Integer id, Venda paraSalvar) {
        if (id != null && procurar(id) != null) {
            paraSalvar.setId(id);
        }

        return repository.save(paraSalvar);
    }

    public Venda finalizar(Integer idCarrinho) {
        Carrinho carrinho = carrinhoService.procurar(idCarrinho);
        carrinho.setId(null);
        carrinho.setDataHora(null);

        //Calculando Total
        carrinhoService.atualizarTotal(carrinho);

        //Diminuir Estoque
        Venda venda = mapper.map(carrinho, Venda.class);
        diminuirEstoqueProduto(venda);

        return salvar(null, venda);
    }

    public Venda excluir(Integer id) {
        Venda paraExcluir = procurar(id);
        repository.delete(paraExcluir);
        return paraExcluir;
    }

    private void diminuirEstoqueProduto(Venda venda) {
        ProdutoDto produto = apiProduto.procurar(venda.getIdProduto());
        produto.setQuantidade(
                produto.getQuantidade() - venda.getQuantidade()
        );
        apiProduto.atualizarQuantidade(venda.getIdProduto(), produto.getQuantidade());
    }

}
