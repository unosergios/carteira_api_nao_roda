package br.com.alura.carteira.controller;

//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

// pto de entrada da api colocar as anotações para simular o disparo na api
//

@ExtendWith(SpringExtension.class)   // do junit
@SpringBootTest     // para carregar spring carregar td que o spring precisa
@ActiveProfiles("test")   // para usar a base de teste
@AutoConfigureMockMvc      // para poder usar o MockMvc
@Transactional
class UsuarioControllerTest {

	// simular o Post e para isso o pessoal do Sprng criar uma classe
	// a classe importante para teste do Controller - MockMvc
	
	@Autowired
	private MockMvc mvc;    // simula as requisiçoes post e get tipo mock só que do Spring
	
	// colocar o thorows para o mvc requer o tratamento de errp
	@Test
	void naoDeveriaCadastrarUsuarioComDadosIncompletos() throws Exception {
		String json = "{}";
		
		// como disparar a simulacao do Post
		// para carregar toda a bibliote do MockMvcRequestBuilders.post e outros
		mvc
		 .perform(post("/usuarios")
		 .contentType(MediaType.APPLICATION_JSON) // com cabecalho tipo content type é json
		 .content(json))                          // com conteudo do string JSON
		 .andExpect(status().isBadRequest());     // recebe o erro - testado pelo proprio spring - nao precisa do asseert
		
	}

	
	// a barra \ no jason é para separar a especificacao da definicao
	// do string com o abre e fecha aspas duplas do json
	@Test
	void deveriaCadastrarUsuarioComDadosCompletos() throws Exception {
		String json = "{\"nome\" : \"sergio\","
				+ "     \"login\" : \"tempuno\"}";
		
		// como disparar a simulacao do Post
		// para carregar toda a bibliote do MockMvcRequestBuilders.post e outros
		mvc
		 .perform(post("/usuarios")
		 .contentType(MediaType.APPLICATION_JSON) // com cabecalho tipo content type é json
		 .content(json))                          // com conteudo do string JSON
		 .andExpect(status().isCreated())         // testa o status que deve ser de criado
		 .andExpect(header().exists("Location"))  // devolve o location
		 .andExpect(content().json(json));     // recebe o erro - testado pelo proprio spring - nao precisa do asseert
		
	}	
}
