package br.com.alura.carteira.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

// os testes em Spring tenta encontrar o bco de dados em memoria
// um dos bcos em memoria é o h2 e para isso configurar o arquivo pom

// outra estrategia é criar um outro database exe carteira-teste
// para isso vamos duplicar a 
@ExtendWith(SpringExtension.class)
@DataJpaTest    // traz td que for necessario para o processo de bco de dados-- subir as classes de persistencia do Jpa
@AutoConfigureTestDatabase(replace=Replace.NONE)   // usa o mesmo bco mas não atualiza
@ActiveProfiles("test")  // o Spring vai procurara o arquivo properties do banco 
class TransacaoRepositoryTest {

// precisa pedir para inicializar spring inejtar o repository
	
	@Autowired
	private TransacaoRepository repository;

// cadas metodo de teste igual a @Test	

// para poder popular a base de teste
	
	@Autowired
	private TestEntityManager em;

	// primeiro cenario de teste
	@Test
	void deveriaRetornarRelatorioDeCarteiraDeInvestimentos() {
		
// popular os dados
	   Usuario usuario = new Usuario("Sergio", "unox", "123456");
	   em.persist(usuario);
	   
	   Transacao t1 = new Transacao("ITSA4",
			   new BigDecimal("10.00"), 
			   50,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t1);
	   
	   Transacao t2 = new Transacao("BBSE3",
			   new BigDecimal("22.80"), 
			   80,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t2);
	   
	   Transacao t3 = new Transacao("EGIE3",
			   new BigDecimal("40.00"), 
			   25,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t3);
	   
	   Transacao t4 = new Transacao("ITSA4",
			   new BigDecimal("11.20"), 
			   40,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t4);	   
	   
	   Transacao t5 = new Transacao("SAPR4",
			   new BigDecimal("4.02"), 
			   120,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t5);	  	   
		
	   List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();
// so testa se lista não veio null
	   
//	   assertNotNull(relatorio);
	   
//	   assertEquals(4, relatorio.size());

// vamos utilizar uma biblioteca para facilitar os testes assertj e não o assert do Junit 
// ja vem o modulo de teste do spring-test, então não precisa mexer no pom
// 	   
	   Assertions.assertThat(relatorio)    
	      .hasSize(4)
	      .extracting(ItemCarteiraDto::getTicker,ItemCarteiraDto::getQuantidade,ItemCarteiraDto::getPercentual)
//	      .contains( -- ou em qq ordem
	      .containsExactlyInAnyOrder(
//	    		    Assertions.tuple("ITSA4",90l,0.285714),
//	    		    Assertions.tuple("BBSE3",80l,0.253968),
//	    		    Assertions.tuple("EGIE3",25l,0.079365),
//	    		    Assertions.tuple("SAPR4",120l,0.380952)	  
// ajuste me função de voltar o percentual com duas casas decimais	    		  
	    		    Assertions.tuple("ITSA4",90l,new BigDecimal("28.57")),
	    		    Assertions.tuple("BBSE3",80l,new BigDecimal("25.40")),
	    		    Assertions.tuple("EGIE3",25l,new BigDecimal("7.94")),
	    		    Assertions.tuple("SAPR4",120l,new BigDecimal("38.10"))	    	    		  
	    		  );
	}
//---------------------------------------------------------------------------------------------
	
	// Segundo cenario de teste
	@Test
	void deveriaRetornarRelatorioDeCarteiraDeInvestimentosConsiderandoVendas() {
		
// popular os dados
	   Usuario usuario = new Usuario("Sergio", "unox", "123456");
	   em.persist(usuario);
	   
	   Transacao t1 = new Transacao("ITSA4",
			   new BigDecimal("10.00"), 
			   50,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t1);
	   
	   Transacao t2 = new Transacao("BBSE3",
			   new BigDecimal("22.80"), 
			   80,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t2);
	   
	   Transacao t3 = new Transacao("EGIE3",
			   new BigDecimal("40.00"), 
			   25,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t3);
	   
	   Transacao t4 = new Transacao("ITSA4",
			   new BigDecimal("11.20"), 
			   40,
			   LocalDate.now(),
			   TipoTransacao.VENDA,
			   usuario);
	   em.persist(t4);	   
	   
	   Transacao t5 = new Transacao("SAPR4",
			   new BigDecimal("4.02"), 
			   120,
			   LocalDate.now(),
			   TipoTransacao.COMPRA,
			   usuario);
	   em.persist(t5);	  	   
		
	   List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();
// so testa se lista não veio null
	   
//	   assertNotNull(relatorio);
	   
//	   assertEquals(4, relatorio.size());

	   
//	    assertNotNull(relatorio);
//	    assertFalse(relatorio.isEmpty());   	   
// vamos utilizar uma biblioteca para facilitar os testes assertj e não o assert do Junit 
// ja vem o modulo de teste do spring-test, então não precisa mexer no pom
// 	   
	   Assertions.assertThat(relatorio)    
	      .hasSize(4)
	      .extracting(ItemCarteiraDto::getTicker,ItemCarteiraDto::getQuantidade,ItemCarteiraDto::getPercentual)
//	      .contains( -- ou em qq ordem
	      .containsExactlyInAnyOrder(
//	    		    Assertions.tuple("ITSA4",10l,0.042553),
//	    		    Assertions.tuple("BBSE3",80l,0.340425),
//	    		    Assertions.tuple("EGIE3",25l,0.106382),
//	    		    Assertions.tuple("SAPR4",120l,0.510638)	  
	    		// ajuste me função de voltar o percentual com duas casas decimais
	    		  
	    		    Assertions.tuple("ITSA4",10l,new BigDecimal("4.26")),
	    		    Assertions.tuple("BBSE3",80l,new BigDecimal("34.04")),
	    		    Assertions.tuple("EGIE3",25l,new BigDecimal("10.64")),
	    		    Assertions.tuple("SAPR4",120l,new BigDecimal("51.06"))	 	    		  
	    		  );
	}
	
}
