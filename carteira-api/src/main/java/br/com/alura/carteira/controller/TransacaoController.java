package br.com.alura.carteira.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.service.TransacaoService;

	// no servlet tinha a anotacao Webservice para indicar o nome do link 
	// no browse..Aqui no sprint temos a anotação  @Controller + a anotação
	
	// essa classe é um controler e responda todas as requiçoes que vier pelo nome
	// / transacoes
	// pode ter varios controller dentro do projeto
	// @RestController - assume que tanto o @GetMapping como o @SetMapping tenha
	// o @ReponseBody podendo então tirar da anotação @GetMaaping e @SetMapping
	
	//@Controller

	// o Jackson utiliza todos os atributos devolvidos para a requisicao , no caso 
    // devolvemos o objeto da Transacao. Se na Transacao tiver um objeto Usuario o
	// jackson iria pegar tbem os atributos da classe Usuario
  	// Para contornar isso vamos criar uma classe

// @RestController para indicar que o spring que é uma classe Controller e que o Spring
// vai gerencias esta classe , receber e devolver e responder aos verbos HTTP
// alem disso é um processo REST que não se comunica com uma JSP e sim devolve o formato JSON por padrao
// @RequestMapping para definir a uri 


@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	
//	private List<Transacao> transacoes = new ArrayList<>();
//	private ModelMapper modelMapper = new ModelMapper();
//	
//    // nessa classe é que vai receber requisicao e devolver resposta	- tipo a servlet
//
//    // vamos utilizar 
//	
//    // se a requisicao - verbos do HTTP vier como GET cai neste enderedo
//
//	
//    // Por padrao o retorno de String é como se fosse o nome da pagina
//	// Nessa caso seria uma pagina chamado "teste serginho", que seria uma pg em html
//	// Para retornar tanta a pagina como o String colocamos a anotação @ResponseBody
//	
//	// Por padrao o spring devolve no formato JSON	
//		
//	// Se for para devolver em formato xml colocar um parametro na anotação
//	// @GetMapping(produces=MediaType.APPLICATION_XML_VAUE)	
//	
//@GetMapping
//   // @ResponseBody
//   //public String listar() {
//   //return "teste serginho x";
//
//   // 1 public Transacao listar() {
//
//   // com o processo da DTO de restringir os campos a ser exportado,criamos
//   // uma classe Dto colocando somente os campos que queremos que o jackson gere
//   // public List<Transacao> listar() 
//
//public List<TransacaoDto> listar() {
//  
//	//	return new Transacao("XPTO",new BigDecimal("34.56"),230,LocalDate.now(),TipoTransacao.VENDA);
//
//	List<TransacaoDto> transacoesDto = new ArrayList<>();
//
//	// 	só que como devolvemos para dto , devemos fazer um depara - isto é um 
//	//  metodo antigo que pode ser substituido pelo
//    //  comando do java 8 -- streams
//    
////   substituido por um comando do java 8
////    for (Transacao transacao : transacoes) {
////    	TransacaoDto dto = new TransacaoDto();
////    	dto.setTicker(transacao.getTicker());
////    	dto.setPreco(transacao.getPreco()); 
////    	dto.setQuantidade(transacao.getQuantidade());
////    	dto.setTipo(transacao.getTipo());
////    
////        transacoesDto.add(dto)	;
////	}
////	
//	
//// comando abaixo do java 8 e para substituir vamos usar uma biblioteca
//// que faça o processo acima do de para
//// será uma biblioteca externa da comunidade
//// vai ser agregado no arquivo pom e buscar o ModelMapper
//	
////    return transacoes.stream().map(TransacaoDto::new).collect(Collectors.toList());
//
//// o objeto modelMapper vai como private na class	
////	ModelMapper modelMapper = new ModelMapper();
//	
//	return transacoes.stream()
//			.map(t -> modelMapper.map(t,TransacaoDto.class))
//			.collect(Collectors.toList());
//	
//	
//	
//// vamos voltar para lista de transacaoDTO	
////    return transacoes;
//    // como construi o stream, já dá o retorno por lah e tira o return transacoesDto;
//   //   return transacoesDto;
//    
//}
//
//
//// efetuar o processo do post usamos a anotação @PostMapping
//
//@PostMapping
//   // terceiro metodo usa o objeto no parametro que utiliza o construtor padrao que é o vazio
//   // o@RequestBody que os dados vem no corpo da requisicao - anotaçoes da aula postman
//
//// com o processo da dto vamos receber agora o Dto no lugar da Transacao
//// public void cadastrar(@RequestBody Transacao transacao )
//
//// para invocar a validação que foi colocado na classe TransacaoFormDtos, usamos
//// o @Valid
//
//public void cadastrar(@RequestBody @Valid TransacaoFormDto dto ) {
//	
//   // tres opçoes para pegar os parametros
//   // primeiro metodo	
//   // public void cadastrar(Tttpservletrequest req)	
//   // String ticker = req.get,,,,
//   // segundo metodo
//   //	public void cadastrar(String ticker, LocalDate date, Integer quantidade)
////	
////     Transacao transacao = new Transacao(
////    		 dto.getTicker(),
////    		 dto.getPreco(),
////    		 dto.getQuantidade(),
////    		 dto.getData(),
////    		 dto.getTipo()    		 
////    		 );
////   
////	ModelMapper modelMapper = new ModelMapper();
//	
//
//	
//	Transacao transacao = modelMapper.map(dto, Transacao.class);
////  Um do modod de validar seria colocar if´s
////    if (transacao.getTicker()==null)	{
////    	msg de erro
////    }
////    if (transacao.getTicker().length() !=5) {
////    	msg de erro
////    }
//    
//    // mas vamos utilizar biblioteca de validação de campos
//	// incorporar no arquivo pom . Esta biblioteca não do spring e sim do Java-(bin validation
//	// o spring boot se integra a esta biblioteca do bin validation do java e o spring faz essa 
//	// interface com a biblioteca do java spring-boot-starter-validation
//	// 
//	transacoes.add(transacao);
//}
	
	@Autowired
	private TransacaoService service;

	
// para efetuar a paginação usamos o Pageable	
// trocar o list por Page	
// para deixar o default de 10 para 2 usar o @pageabledefault	e assim não
// precisoa utilizar o parametro no link transacoes?size=2
	
@GetMapping
public Page<TransacaoDto> listar(@PageableDefault(size=3) Pageable paginacao) {
	   return service.listar(paginacao);
	   
// pode ir para a classe service	
//	return usuarios.stream()
//			.map(t -> modelMapper.map(t,UsuarioDto.class))
//			.collect(Collectors.toList());

}

// processo para voltar 201 que é melhor que 200-- requisicao criado com sucesso
// precisao tirar o void para voltar a 201 
// volta o corpo com o Jason co o recurso criado
// voltara o header com o location do recurso criado

@PostMapping
public ResponseEntity<TransacaoDto> cadastrar(@RequestBody @Valid TransacaoFormDto dto ,
		   UriComponentsBuilder uriBuilder) {
    TransacaoDto transacaoDto = service.cadastrar(dto);
    //URI uri = "http://localhost:8080/trasacaoes/1"
    URI uri = uriBuilder.path("/transacoes/{id}").buildAndExpand(transacaoDto.getId()).toUri();
    return ResponseEntity.created(uri).body(transacaoDto);
// teste da inclusão do gerenciamento de versao em 27/09/2021    
}		
}
