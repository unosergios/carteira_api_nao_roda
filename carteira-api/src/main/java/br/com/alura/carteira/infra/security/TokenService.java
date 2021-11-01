package br.com.alura.carteira.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.carteira.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

 
//	@Value("${jjwt.secret}")
//	private String secret;
	
// vamos utilizar o jjwt para gerar o token
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();

// dentro de senha esta a senha do spring		
		return Jwts.builder()
				.setId(logado.getId().toString())
				.signWith(SignatureAlgorithm.HS256, "BpxMSNY8MuLjhAu3wyYp5VWwDA73U8vNUJh88DePmDdNZJ6AsZzZxWXJ4TBX26Pc9cvXfmAtYvj3udp9CtX2bMc3n7JDWS88SdeevvXCs9GTWRbGaTwaKGedEDAZbCTXXfzsYcsRqfETJWDVJaK3ZT6fJMVnACeeXTgFzJND98Ac4tmPzDtKUdrhHpphKmNvwcaqCGnFMyrp9bKnUvDhKNYhARSu4qNUYfngS6ARCJk7PBBLBWH5daLWHg7vgynt")
				.compact();
	}

	
}
