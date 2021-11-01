package br.com.alura.carteira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarteiraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarteiraApiApplication.class, args);
		
// migração para não usar mais o Servlet que conversao com o jsp
		
// dentro do projeto Maven o server já vem carregado
		
// para mudar a porta ir em src/main/resource e application.properties
// as configurações do Spring do Tomcat  é feito neste arquivo		
		
	}

}
