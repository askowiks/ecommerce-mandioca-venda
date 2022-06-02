package com.letscode.santander.ecommercemandioca.repository;

import com.letscode.santander.ecommercemandioca.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
