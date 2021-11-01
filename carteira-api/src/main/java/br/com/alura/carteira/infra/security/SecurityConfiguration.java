package br.com.alura.carteira.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// essa anotação serve para o spring carregar
// ao subir a aplicação
// O spring já tem a classe de configurações
// então vamos herdar a classe de configuracao
// do spring e sobreescrever alguns metodos

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	// para ensinar o Scpring como injetar -- dar new é criar um
	// metodo na propria classe dando new- truque 
	//

	// tem classes que o spring não vai saber dar new - então
	// criar um metodo para ensinar o spring e colocar a anotaçaõ @Bean
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
   @Override
   @Bean
     protected AuthenticationManager authenticationManager() throws Exception {
	return super.authenticationManager();
}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(autenticacaoService).passwordEncoder(bCryptPasswordEncoder);
		 
	}
	
	
	// ajustar outro metodo do configura para desabilitar
	// o crossfile  -- crrf
	// requisicoes do tipo post crrf - exige um cabecalho
	// dai o erro 403 - forbiden

// pelo formulatio	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and()
//		.formLogin().and().csrf().disable();
//	//	super.configure(http);
//	}
	
	
	// criado uma classe BeansConfigurations e movido
	// para lah
//	@Bean
//	public BCryptPasswordEncoder getPassordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	// truque para criar o hash - criptografia usando o algorithimo doBCrypt e run as na classe
//	public static void main(String [] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	//}
	
//-----------autenticacao via Token
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/auth").permitAll()
		.anyRequest().authenticated().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();
	//	super.configure(http);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/v2/api-docs","/configuration/ui","/swagger-resources/**","/configuration/secutiry","/swagger-resources/**","/configuration/security","/swagger-ui.html","/webjars/**");
    		   
    		   
	}
	
	
}
