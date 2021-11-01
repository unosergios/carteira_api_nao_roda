package br.com.alura.carteira.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.alura.carteira.modelo.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor  // para o processo de teste
@NoArgsConstructor   // para o processo de teste
public class TransacaoFormDto {
	
// vamos colocar as validacoes aqui do bin valkidation - as regras
// para rodar a validação temos que invocar a validacao	
	

// para personalizar diretamente  nas anotações em portugues ou outra lingua
//    @NotEmpty(message = "campo obrigatorio xxx")

// mas para todos os NotEmpty - poderia deixar em um so lugar	
// o beam validation consegue ler um arquivo com todas as mensagens
// arquivo para personalizar as mensagens do bean validation
// pode colocar uma key para buscar do arquivo de mensagem 
	
//	para utilizar as msg em varios idiomas - criar orquivo ValidationMessage_PTBR.properties
//  ValidationMessage_ENUS.properties - ingles americano	um arquivo para cada idioma
// O Spring consegue ler o idioma que o usuario está usando	
// processo conhecido como internacioanalizacao	

	
	
	@NotEmpty
    @Size(min=5 , max=6)
  //  @Pattern(regexp="[a-zA-Z]{4}[0-9][0-9]?",message = "Ticker no formato incalido")	
	@Pattern(regexp="[a-zA-Z]{4}[0-9][0-9]?",message="{transacao.ticker.ivalido}")
	private String ticker;
    

	@NotNull
    @DecimalMin("0.01")
	private BigDecimal preco;
	private int quantidade;
	
	@PastOrPresent
	private LocalDate data;
	
	@NotNull(message ="usar esta mensagem que sobrepoe do arquivo de msg")
	private TipoTransacao tipo;	
	
// só que o padrao de nomenclatura é o camelCase - como contornar ?
// vamos utilizar a anotação  JasonAlians	- significa que no jason entra ou sai como usuario_id 
// e aqui no java utilizamos a boa pratica camelcase	
	
	@JsonAlias("usuario_id")
	private Long usuarioId ;
	

//substituido pelo Lombok	
	
//	public LocalDate getData() {
//		return data;
//	}
//	public void setData(LocalDate data) {
//		this.data = data;
//	}
//	
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
//

}
