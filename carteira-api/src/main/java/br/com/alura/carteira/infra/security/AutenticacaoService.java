package br.com.alura.carteira.infra.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.dto.LoginFormDto;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	

// processo do token 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	// o username que vem é o usuario da tela
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// logica para buscar o usuario na base de dados
		return repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado"));
	}


// processo usado no token 	
	
	public String autenticar(@Valid LoginFormDto dto) {
	    // autenticar

		// devolver token
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
		
		authentication= authenticationManager.authenticate(authentication);
	
		// gerar token e devolver
		
		
		return tokenService.gerarToken(authentication);
	}

}
