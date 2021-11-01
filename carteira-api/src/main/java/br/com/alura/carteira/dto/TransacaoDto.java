package br.com.alura.carteira.dto;
      
import java.math.BigDecimal;



import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class TransacaoDto {
	
//	vamos tirar a data pois não vamos querer que transferir para o JSON
// colocar a Id para ser usado na montagem do URI
	
	private Long id;
	private String ticker;
	private BigDecimal preco;
	private Integer quantidade;
	private TipoTransacao tipo;
	

// Para se utilizar o stream podemos construir um construtor que tenha já os campos

// como vai ser usado o Model Mapper - ele se encarrega de localizar pelo
// get e setters da classe, podendo então não ter o construtor abaixo	e
// sim o construtor default	
	
//	public TransacaoDto(Transacao transacao) {
//		this.ticker = transacao.getTicker();
//      	this.preco = transacao.getPreco();	
//      	this.quantidade = transacao.getQuantidade();
//      	this.tipo = transacao.getTipo();
//	}
//	
	

// substutuindo pelo Lombok as linhas abaixo
	
//	public String getTicker() {
//		return ticker;
//	}
//	public void setTicker(String ticker) {
//		this.ticker = ticker;
//	}
//	public BigDecimal getPreco() {
//		return preco;
//	}
//	public void setPreco(BigDecimal preco) {
//		this.preco = preco;
//	}
//	public int getQuantidade() {
//		return quantidade;
//	}
//	public void setQuantidade(int quantidade) {
//		this.quantidade = quantidade;
//	}
//	public TipoTransacao getTipo() {
//		return tipo;
//	}
//	public void setTipo(TipoTransacao tipo) {
//		this.tipo = tipo;
//	}


}
