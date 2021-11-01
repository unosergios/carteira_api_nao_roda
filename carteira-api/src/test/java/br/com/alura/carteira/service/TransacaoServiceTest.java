package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;

// teste dos processos de injeção direta
// teste do processo de cadastro

// para o mockito rodar no ambiente de teste simulando
// os mocks e injectmocks temos que usar a anotação abaixao 
// na classe de test - A Classe MockitoExtension.class
// vai montar a simulação das dependencias externas no TransacaoService

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {


    @Mock
	private TransacaoRepository transacaoRepository;
	
    @Mock
	private UsuarioRepository usuarioRepository;	
    
    @InjectMocks
	private TransacaoService service;

// tirar a duplicacao do processo de cadastramento
// melhorias no codigo
    
   private TransacaoFormDto criarTransacaoFormDto() {
  		 
	   TransacaoFormDto formDto = new TransacaoFormDto(
	   "XPTO01",
  		  new BigDecimal("23.67"),
  		  100,
  		  LocalDate.now(),
  		  TipoTransacao.COMPRA,
  		  1l);
	   
	   return formDto;
    }    
    
	@Test
	void deveriaCadastrarUmaTransacao() {


//	    TransacaoFormDto formDto = new TransacaoFormDto(
//	    		  "XPTO01",
//	    		  new BigDecimal("23.67"),
//	    		  100,
//	    		  LocalDate.now(),
//	    		  TipoTransacao.COMPRA,
//	    		  1l
//	    		  
//	     );
//	    
		
		TransacaoFormDto formDto = criarTransacaoFormDto();

// foi utilizado o Mockito para as duas chamadas (repository)
// só que as duas chamadas requerem os parametros declarados
// nas repositorys . Para isto vamos tirar o 	TransacaoService service = new TransacaoService()
// abaixo e passar como uma variavel 	    
	    
//		TransacaoService service = new TransacaoService();	    
	    TransacaoDto dto = service.cadastrar(formDto);

// precisamos simular o autowired dos repositorys
// pois os asserts abaixo dara um null pointer devido ao
// id do usuario
// como passar passar pelos repositorys no transacaoservice
// vamos utilizar o Mock para simular a dependencia
// ver testes utilizando Mocks 14:00 min
// o mockito já vem no test do spring boot	    
	    
// teste para er se passou no transacaoRepository.sava
	    
	    Mockito.verify(transacaoRepository).save(Mockito.any());
	    
	    assertEquals(formDto.getTicker(), dto.getTicker());
	    assertEquals(formDto.getPreco(), dto.getPreco());
	    assertEquals(formDto.getQuantidade(), dto.getQuantidade());
	    assertEquals(formDto.getTipo(), dto.getTipo());	    
	}

	
	@Test
	void naoDeveriaCadastrarTransacaoComUsuarioInexistente() {

// o mock não vai ao banco
// para simular o id do usuario

// como tinha repeticao de codigos
	
//	    TransacaoFormDto formDto = new TransacaoFormDto(
//	    		  "XPTO01",
//	    		  new BigDecimal("23.67"),
//	    		  100,
//	    		  LocalDate.now(),
//	    		  TipoTransacao.COMPRA,
//	    		  1000l
//	    		  
//	     );
	    
		TransacaoFormDto formDto = criarTransacaoFormDto();
	    
// aula testes utilizando Mocks 33:00
	    
        Mockito.when(usuarioRepository.getById(formDto.getUsuarioId()))
	   // Mockito.when(usuarioRepository.getById(1000l))
       //   .thenReturn(null);
            .thenThrow(EntityNotFoundException.class);
	    TransacaoDto dto = service.cadastrar(formDto);

     // para pegar o exception 
	    assertThrows(EntityNotFoundException.class,() -> service.cadastrar(formDto));
	    assertEquals(formDto.getTicker(), dto.getTicker());
	    assertEquals(formDto.getPreco(), dto.getPreco());
	    assertEquals(formDto.getQuantidade(), dto.getQuantidade());
	    assertEquals(formDto.getTipo(), dto.getTipo());	    
	}
	
}
