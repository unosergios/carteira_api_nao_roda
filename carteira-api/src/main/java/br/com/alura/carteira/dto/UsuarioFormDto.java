package br.com.alura.carteira.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
    @NotBlank                           // essa anotação é para o campo não ser not null e vazio
	private String nome;
    @NotBlank
	private String login;
    
// vai ser tirado daqui porque a senha vai gerado pelo modulo de serviço    
//    @NotBlank
//	private String senha;
}