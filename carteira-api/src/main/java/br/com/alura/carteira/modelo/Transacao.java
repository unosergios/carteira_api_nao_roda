package br.com.alura.carteira.modelo;


// transacao do carteira-api
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// para colocar o getter e setter para todos os atributos da class Transacao

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"data","quantidade"})

// criacao da tabela via JPA - utilizacao da anotação @Entity - carregar do javax
// a JPA irá criar o nome da tabela como Transacao
// para mudar o nome padrao utiliza a anotação 
@Entity
@Table(name="transacoes")
public class Transacao {

// se somente o getter e seeter do ticker pelo lombok
//	@Getter @Setter
	
// vamos utilizar as anotaçoes do bin validation	, mas como os processo ocorrem nas classes dto , vamos como na classe DTo

// para indicar o JPA que é a chave primaria - utilizar a anotação @Id
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

// para ser um nome diferente do campo na tabela do banco	
//	@Column(name="ticker1")	
	private String ticker;
	private BigDecimal preco;
	private Integer quantidade;  // antes era int - usaur a classe não os primitivos
	private LocalDate data;
	
	// com essa anotação @JsonIgnore o TipoTransacao não será gerado no JSON pelo
	// Jackson. Mas isto restringe para NUNCA levar este atributo para o JSON
	// seria um processo muito radical, Seria para algo jamias quer ser gerado
	// pelo jackson- essa anotaçãoignora sempre
	
	// o legal é vc ter flexibilidade de quais campos quero mandar
	// pra fazer isso existe uma boa pratica é criar uma outra classe "clone"
	// somente com os campos que eu quero enviar
	// um padrão de projeto para isso é o DTO - Data Transfer Object
	// só serve para transferir dados no api REST
	
	// @JsonIgnore
	
	
	// o jpa iria criar a tabela como inteiro e colocar o 0,1,2 para constante dp
	// enumerated
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	// para linkar com a tabela usuario - relacionamento da transacao com usuario
	// cada transaçao tem um usuario
	// de transacoes para usuario 
	
	@ManyToOne
	// @JoinColumn(name="id_usuario")
	// no caso o relacionamento sera o proprio id
	private Usuario usuario;



	// toda codificacao abaixo pode ser "escondido" pelo Lombok
	// o Lombok é um biblioteca que tira essa visibilidade no
	// codigo fonte. 
	// não é que deixar de existir , se não o objeto da aplicação não
	// funcionaria . Ele vai estar no bitecode. Só não vai aparecer
	// no codigo fonte
	// tanto o construtor, to string e o getter e setters
	// vamos incorporar mais dependencias no arquivo tom
	
	
	
//	public Transacao() {
//	}
//	
//	public Transacao(String ticker, BigDecimal preco, int quantidade, LocalDate data, TipoTransacao tipo) {
//		this.ticker = ticker;
//		this.preco = preco;
//		this.quantidade = quantidade;
//		this.data = data;
//		this.tipo = tipo;
//	}
//
//	
//	
//
//	
//	@Override
//	public String toString() {
//		return "Transacao [ticker=" + ticker + ", preco=" + preco + ", quantidade=" + quantidade + ", data=" +
//	            data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", tipo=" + tipo + "]";
//	}
//
//	public String getTicker() {
//		return ticker;
//	}
//
//	public void setTicker(String ticker) {
//		this.ticker = ticker;
//	}
//
//	public BigDecimal getPreco() {
//		return preco;
//	}
//
//	public void setPreco(BigDecimal preco) {
//		this.preco = preco;
//	}
//
//	public int getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(int quantidade) {
//		this.quantidade = quantidade;
//	}
//
//	public LocalDate getData() {
//		return data;
//	}
//
//	public void setData(LocalDate data) {
//		this.data = data;
//	}
//
//	public TipoTransacao getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(TipoTransacao tipo) {
//		this.tipo = tipo;
//	}
  
	// para poder popular a tabela de testes foi construido o construtor abaixo
	
	public Transacao(String ticker, BigDecimal preco, Integer quantidade, LocalDate data, TipoTransacao tipo,
			Usuario usuario) {
		this.ticker = ticker;
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
		this.tipo = tipo;
		this.usuario = usuario;
	}	
	
}
