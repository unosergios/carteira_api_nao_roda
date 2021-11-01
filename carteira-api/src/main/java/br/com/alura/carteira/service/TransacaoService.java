package br.com.alura.carteira.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;


@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//private ModelMapper modelMapper = new ModelMapper();	
	
	@Autowired
	private ModelMapper modelMapper;
	// trocar o list para Page
//	public List<TransacaoDto> listar(Pageable paginacao) {
		// para limitar a qtde utilizamos- por default é 20 registros
		// para alterar este limite para o processo de paginação no
		// front end 
		// tem dois caminhos. Ler do link o parametro ou
		// devolve uma pagina via Page
	
	public Page<TransacaoDto> listar(Pageable paginacao) {
		
	    Page<Transacao> transacoes = transacaoRepository.findAll(paginacao);
		
		return transacoes.map(t -> modelMapper.map(t,TransacaoDto.class));
		}	

	// rodar uma transacao de bco de dados- commit - senao fica sobrepondo
	// mas o modelmapper acaba confundindo o id do usuario_id e o id da
	// transacao . 
	
	// tirar o void e retornar a TransacaoDto - para o retorno do cod 201
	@Transactional
	public TransacaoDto cadastrar(TransacaoFormDto dto ) {
		
		Long idUsuario = dto.getUsuarioId(); //*
		
		// vamos colocar o try catch para ver se existe o usuario
		// na base de dados
		try {
		Usuario usuario = usuarioRepository.getById(idUsuario); //*
		Transacao transacao = modelMapper.map(dto, Transacao.class);
		// pode fazer um ajuste no modelmapper
				// para dizer que na tem id e o bco cria um novo id
				
				transacao.setId(null);
				transacao.setUsuario(usuario);  //*
		        transacaoRepository.save(transacao);
		        return modelMapper.map(transacao,TransacaoDto.class);
		   //     return new TransacaoDto - para simular erro no ServiceTransacaoTest
		
		// precisa ver se o usuario foi carregado no bco de dados
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Usuario inexistente");
		}
		
	}	
   //return new TransacaoDto - para simular erro no ServiceTransacaoTest
//	}		
//	Transacao transacao = modelMapper.map(dto, Transacao.class);
//	// pode fazer um ajuste no modelmapper
//			// para dizer que na tem id e o bco cria um novo id
//			
//			transacao.setId(null);
//			transacao.setUsuario(usuario);  //*
//	        transacaoRepository.save(transacao);
//	        return modelMapper.map(transacao,TransacaoDto.class);
//	   //  	
}
