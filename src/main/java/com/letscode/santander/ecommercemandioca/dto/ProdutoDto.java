package com.letscode.santander.ecommercemandioca.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProdutoDto {

    private Integer id;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    private LocalDate validade;

}
