package br.com.alura.carteira.infra;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfigurations {

    // personalizar o cabeçallho principal-- dspois do build    
   @Bean
     public Docket api() { 
     return new Docket(DocumentationType.SWAGGER_2)  
                .select()                                  
                .apis(RequestHandlerSelectors.any())    // para expor ou nao todos os pacotes          
                .paths(PathSelectors.any())             // para expor ou nao todos os paths           
                .build()
                
                .apiInfo(apiInfo())   // para personalizar o titulo principal
                ;       
     
     // acessar a documentação padrao do swagger 
     // http://localhost:8080/swagger-ui.html
}
   
//   private ApiInfo apiInfo() {
//	    return new ApiInfo(
//	      "My REST API", 
//	      "Some custom description of API.", 
//	      "API TOS", 
//	      "Terms of service", 
//	      new Contact("John Doe", "www.example.com", "myeaddress@company.com"), 
//	      "License of API", "API license URL", Collections.emptyList());
//	}   
   
   private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Api Carteira de Investimentos", 
	      "Some custom description of API.", 
	      "Termos de Uso", 
	      "Terms of service", 
	      new Contact("Sergio ", "www.example.com", "bootcamp@company.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}   
}

                                    

