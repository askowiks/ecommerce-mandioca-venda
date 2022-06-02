package com.letscode.santander.ecommercemandioca.dto;

import lombok.Data;

@Data
public class UsuarioDto {

    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String senha;

}
