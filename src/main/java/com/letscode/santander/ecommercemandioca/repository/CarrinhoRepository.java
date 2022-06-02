package com.letscode.santander.ecommercemandioca.repository;

import com.letscode.santander.ecommercemandioca.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
}
