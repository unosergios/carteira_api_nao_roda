package br.com.alura.carteira.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "usuario para aparecer na documentacao")
public class UsuarioController {

// tanto a lista que simula o bco como o processo
// do depara do ModelMapper pode ser colocado em uma classe de Service
// é uma boa pratica. Na realidade isso funciona mas a boa pratica é
// colocar em uma classe service	
	
//	private List<Usuario> usuarios = new ArrayList<>();
//	private ModelMapper modelMapper = new ModelMapper();
	
// usar a anotação @Autowired para o spring dar new nessa classe e todos as
// suas dependencias-- CONCEITO DE INJEÇÂO DE DEPENDENCIA -> @Autowired
// A classe service deve ter uma anotação @Service para fazer o link(entendimento
// para o Spring boot	
	
	@Autowired
	private UsuarioService service;
	
@GetMapping
@ApiOperation("Listar para apareer no swagger")
public Page<UsuarioDto> listar(Pageable paginacao) {
	   return service.listar(paginacao);
	   
// pode ir para a classe service	
//	return usuarios.stream()
//			.map(t -> modelMapper.map(t,UsuarioDto.class))
//			.collect(Collectors.toList());

}

// para retornar o codigo 201 o metodo de retornar enão vamos
// tirar a void e retornar um objeto Dto
//public void cadastrar(@RequestBody @Valid UsuarioFormDto dto )
@PostMapping
public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto dto,
		UriComponentsBuilder uriBuilder) {
	UsuarioDto usuarioDto = service.cadastrar(dto);
    URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioDto.getId()).toUri();
    return ResponseEntity.created(uri).body(usuarioDto);
// teste da inclusão do gerenciamento de versao em 27/09/2021    
}	
	
	
}
