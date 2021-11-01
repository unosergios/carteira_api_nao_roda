package br.com.alura.carteira.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;

@Getter
// tirar o all constructor  e ter um construtor personalizado
//@AllArgsConstructor
public class ItemCarteiraDto {

	private String ticker;
	private Long quantidade;

// processo de arrendondamento para duas casas--- tirar o Double e colocar o BigDecimal	
//	private Double percentual;
	
	private BigDecimal percentual;

	// neste construtor vamos rece
	
public ItemCarteiraDto(String ticker, Long quantidade, Long quantidadeTotal) {
	this.ticker = ticker;
	this.quantidade = quantidade;
	this.percentual = new BigDecimal(quantidade)
			.divide(new BigDecimal(quantidadeTotal), 4 , RoundingMode.HALF_UP)
			.multiply(new BigDecimal("100.00"))
			.setScale(2);
}
	
	
	
	
	
	
}
