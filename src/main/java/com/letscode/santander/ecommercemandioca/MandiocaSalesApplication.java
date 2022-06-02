package com.letscode.santander.ecommercemandioca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MandiocaSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MandiocaSalesApplication.class, args);
		System.out.println("\n\nMandiocaSales rodando!!!\n\n");
	}

}
